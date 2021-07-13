/*
 * Copyright (c) 2008-2021, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.jet.sql.impl.connector.map;

import com.hazelcast.internal.serialization.InternalSerializationService;
import com.hazelcast.internal.serialization.impl.compact.FieldDescriptor;
import com.hazelcast.internal.serialization.impl.compact.Schema;
import com.hazelcast.internal.serialization.impl.compact.SchemaWriter;
import com.hazelcast.jet.sql.impl.connector.keyvalue.KvMetadata;
import com.hazelcast.jet.sql.impl.connector.keyvalue.KvMetadataResolver;
import com.hazelcast.jet.sql.impl.inject.CompactUpsertTargetDescriptor;
import com.hazelcast.jet.sql.impl.schema.MappingField;
import com.hazelcast.nio.serialization.FieldType;
import com.hazelcast.sql.impl.QueryException;
import com.hazelcast.sql.impl.extract.GenericQueryTargetDescriptor;
import com.hazelcast.sql.impl.extract.QueryPath;
import com.hazelcast.sql.impl.schema.TableField;
import com.hazelcast.sql.impl.schema.map.MapTableField;
import com.hazelcast.sql.impl.type.QueryDataType;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import static com.hazelcast.jet.sql.impl.connector.SqlConnector.COMPACT_FORMAT;
import static com.hazelcast.jet.sql.impl.connector.SqlConnector.OPTION_KEY_COMPACT_TYPE_NAME;
import static com.hazelcast.jet.sql.impl.connector.SqlConnector.OPTION_VALUE_COMPACT_TYPE_NAME;
import static com.hazelcast.jet.sql.impl.connector.keyvalue.KvMetadataResolver.extractFields;
import static com.hazelcast.jet.sql.impl.connector.keyvalue.KvMetadataResolver.maybeAddDefaultField;

//TODO sancar written quick and dirty. Needs to be checked
final class MetadataCompactResolver implements KvMetadataResolver {

    static final MetadataCompactResolver INSTANCE = new MetadataCompactResolver();

    private MetadataCompactResolver() {
    }

    @Override
    public Stream<String> supportedFormats() {
        return Stream.of(COMPACT_FORMAT);
    }

    @Override
    public Stream<MappingField> resolveAndValidateFields(
            boolean isKey,
            List<MappingField> userFields,
            Map<String, String> options,
            InternalSerializationService serializationService
    ) {
        Map<QueryPath, MappingField> userFieldsByPath = extractFields(userFields, isKey);
        Schema schema =
                resolveSchema(isKey, options, userFieldsByPath.values());

        return userFields.isEmpty()
                ? resolveFields(isKey, schema)
                : resolveAndValidateFields(isKey, userFieldsByPath, schema);
    }

    Stream<MappingField> resolveFields(boolean isKey, Schema schema) {
        return schema.getFieldNames().stream()
                .map(name -> {
                    QueryPath path = new QueryPath(name, isKey);
                    QueryDataType type = resolveCompactType(schema.getField(name).getType());

                    return new MappingField(name, type, path.toString());
                });
    }

    private static Stream<MappingField> resolveAndValidateFields(
            boolean isKey,
            Map<QueryPath, MappingField> userFieldsByPath,
            Schema clazz
    ) {
        for (String name : clazz.getFieldNames()) {
            QueryPath path = new QueryPath(name, isKey);
            QueryDataType type = resolveCompactType(clazz.getField(name).getType());

            MappingField userField = userFieldsByPath.get(path);
            if (userField != null && !type.getTypeFamily().equals(userField.type().getTypeFamily())) {
                throw QueryException.error("Mismatch between declared and resolved type: " + userField.name());
            }
        }
        return userFieldsByPath.values().stream();
    }

    @SuppressWarnings("checkstyle:ReturnCount")
    private static QueryDataType resolveCompactType(FieldType type) {
        switch (type) {
            case BOOLEAN:
                return QueryDataType.BOOLEAN;
            case BYTE:
                return QueryDataType.TINYINT;
            case SHORT:
                return QueryDataType.SMALLINT;
            case INT:
                return QueryDataType.INT;
            case LONG:
                return QueryDataType.BIGINT;
            case FLOAT:
                return QueryDataType.REAL;
            case DOUBLE:
                return QueryDataType.DOUBLE;
            case DECIMAL:
                return QueryDataType.DECIMAL;
            case CHAR:
                return QueryDataType.VARCHAR_CHARACTER;
            case UTF:
                return QueryDataType.VARCHAR;
            case TIME:
                return QueryDataType.TIME;
            case DATE:
                return QueryDataType.DATE;
            case TIMESTAMP:
                return QueryDataType.TIMESTAMP;
            case TIMESTAMP_WITH_TIMEZONE:
                return QueryDataType.TIMESTAMP_WITH_TZ_OFFSET_DATE_TIME;
            default:
                return QueryDataType.OBJECT;
        }
    }

    @Override
    public KvMetadata resolveMetadata(
            boolean isKey,
            List<MappingField> resolvedFields,
            Map<String, String> options,
            InternalSerializationService serializationService
    ) {
        Map<QueryPath, MappingField> fieldsByPath = extractFields(resolvedFields, isKey);
        Schema schema =
                resolveSchema(isKey, options, fieldsByPath.values());

        return resolveMetadata(isKey, resolvedFields, fieldsByPath, schema);
    }

    KvMetadata resolveMetadata(
            boolean isKey,
            List<MappingField> resolvedFields,
            @Nonnull Schema schema
    ) {
        Map<QueryPath, MappingField> fieldsByPath = extractFields(resolvedFields, isKey);

        return resolveMetadata(isKey, resolvedFields, fieldsByPath, schema);
    }

    private static KvMetadata resolveMetadata(
            boolean isKey,
            List<MappingField> resolvedFields,
            Map<QueryPath, MappingField> fieldsByPath,
            @Nonnull Schema schema
    ) {
        List<TableField> fields = new ArrayList<>();
        for (Entry<QueryPath, MappingField> entry : fieldsByPath.entrySet()) {
            QueryPath path = entry.getKey();
            QueryDataType type = entry.getValue().type();
            String name = entry.getValue().name();

            fields.add(new MapTableField(name, type, false, path));
        }
        maybeAddDefaultField(isKey, resolvedFields, fields);

        return new KvMetadata(
                fields,
                GenericQueryTargetDescriptor.DEFAULT,
                new CompactUpsertTargetDescriptor(schema)
        );
    }

    @Nonnull
    private static Schema resolveSchema(
            boolean isKey,
            Map<String, String> options,
            Collection<MappingField> fields
    ) {
        String typeNameProperty = isKey ? OPTION_KEY_COMPACT_TYPE_NAME : OPTION_VALUE_COMPACT_TYPE_NAME;
        String typeName = options.get(typeNameProperty);

        //TODO sancar lookup if typename exists on the serializationService

        SchemaWriter schemaWriter = new SchemaWriter(typeName);
        for (MappingField field : fields) {
            String name = field.name();
            QueryDataType type = field.type();
            switch (type.getTypeFamily()) {
                case BOOLEAN:
                    schemaWriter.addField(new FieldDescriptor(name, FieldType.BOOLEAN));
                    break;
                case TINYINT:
                    schemaWriter.addField(new FieldDescriptor(name, FieldType.BYTE));
                    break;
                case SMALLINT:
                    schemaWriter.addField(new FieldDescriptor(name, FieldType.SHORT));
                    break;
                case INTEGER:
                    schemaWriter.addField(new FieldDescriptor(name, FieldType.INT));
                    break;
                case BIGINT:
                    schemaWriter.addField(new FieldDescriptor(name, FieldType.LONG));
                    break;
                case REAL:
                    schemaWriter.addField(new FieldDescriptor(name, FieldType.FLOAT));
                    break;
                case DOUBLE:
                    schemaWriter.addField(new FieldDescriptor(name, FieldType.DOUBLE));
                    break;
                case DECIMAL:
                    schemaWriter.addField(new FieldDescriptor(name, FieldType.DECIMAL));
                    break;
                case VARCHAR:
                    schemaWriter.addField(new FieldDescriptor(name, FieldType.UTF));
                    break;
                case TIME:
                    schemaWriter.addField(new FieldDescriptor(name, FieldType.TIME));
                    break;
                case DATE:
                    schemaWriter.addField(new FieldDescriptor(name, FieldType.DATE));
                    break;
                case TIMESTAMP:
                    schemaWriter.addField(new FieldDescriptor(name, FieldType.TIMESTAMP));
                    break;
                case TIMESTAMP_WITH_TIME_ZONE:
                    schemaWriter.addField(new FieldDescriptor(name, FieldType.TIMESTAMP_WITH_TIMEZONE));
                    break;
                default:
                    //TODO sancar add missing fields such as nested or arrays and more ...
                    throw QueryException.error("Type " + type + " is not supported for Portable.");
            }
        }
        return schemaWriter.build();
    }
}
