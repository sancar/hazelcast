package com.hazelcast.internal.serialization.impl.compact;

import com.hazelcast.internal.nio.BufferObjectDataOutput;
import com.hazelcast.nio.serialization.FieldType;

import java.io.IOException;
import java.util.List;

import static com.hazelcast.internal.nio.Bits.INT_SIZE_IN_BYTES;
import static com.hazelcast.nio.serialization.FieldType.OBJECT_ARRAY;
import static com.hazelcast.nio.serialization.FieldType.UTF;

public class CompactWriterWithoutOffset extends DefaultCompactWriter {

    public CompactWriterWithoutOffset(Compact serializer, BufferObjectDataOutput out, SchemaImpl schema) {
        super(serializer, out, schema);
    }

    public void end() {
        try {
            int position = out.position();
            int length = position - offset;
            //write length
            out.writeInt(offset, length);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    protected void setPositionAsNull(String fieldName, FieldType fieldType) {
        FieldDescriptorImpl field = checkFieldDefinition(fieldName, fieldType);
        try {
            out.write(field.getIndex());
            out.writeInt(-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void setPosition(String fieldName, FieldType fieldType) {
        FieldDescriptorImpl field = checkFieldDefinition(fieldName, fieldType);
        try {
            out.write(field.getIndex());
            out.writeZeroBytes(4);//leave room for length
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeUTF(String fieldName, String str) {
        try {
            if (str == null) {
                setPositionAsNull(fieldName, UTF);
            } else {
                int position = out.position();
                setPosition(fieldName, UTF);
                out.writeUTF(str);
                int length = out.position() - position;
                out.writeInt(position + 1, length);
            }
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    protected  <T> void writeNullable(String fieldName, T object, FieldType fieldType, Writer<BufferObjectDataOutput, T> writer) {
        try {
            if (object == null) {
                setPositionAsNull(fieldName, fieldType);
            } else {
                int position = out.position();
                setPosition(fieldName, fieldType);
                writer.write(out, object);
                int length = out.position() - position;
                out.writeInt(position + 1, length);
            }
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    protected  <T> void writeObjectArrayField(String fieldName, FieldType fieldType, T[] values, Writer<BufferObjectDataOutput, T> writer) {
        //TODO sancar make it same with writeArrayList
        if (values == null) {
            setPositionAsNull(fieldName, fieldType);
            return;
        }
        try {
            int beginningPosition = out.position();
            setPosition(fieldName, fieldType);
            int len = values.length;
            out.writeInt(len);

            int offset = out.position();
            out.writeZeroBytes(len * INT_SIZE_IN_BYTES);
            for (int i = 0; i < len; i++) {
                if (values[i] != null) {
                    int position = out.position();
                    out.writeInt(offset + i * INT_SIZE_IN_BYTES, position);
                    writer.write(out, values[i]);
                } else {
                    out.writeInt(offset + i * INT_SIZE_IN_BYTES, -1);
                }
            }

            int length = out.position() - beginningPosition;
            out.writeInt(beginningPosition + 1, length);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }

    @Override
    public <T> void writeObjectList(String fieldName, List<T> values) {
        try {
            if (values == null) {
                setPositionAsNull(fieldName, OBJECT_ARRAY);
                return;
            }
            int beginningPosition = out.position();

            setPosition(fieldName, OBJECT_ARRAY);
            int len = values.size();
            out.writeInt(len);
            int offset = out.position();
            out.writeZeroBytes(len * INT_SIZE_IN_BYTES);
            for (int i = 0; i < len; i++) {
                Object value = values.get(i);
                if (value != null) {
                    int position = out.position();
                    out.writeInt(offset + i * INT_SIZE_IN_BYTES, position);
                    serializer.writeObject(out, value);
                } else {
                    out.writeInt(offset + i * INT_SIZE_IN_BYTES, -1);
                }
            }

            int length = out.position() - beginningPosition;
            out.writeInt(beginningPosition + 1, length);
        } catch (IOException e) {
            throw illegalStateException(e);
        }
    }
}
