package com.hazelcast.internal.serialization.impl.compact;

import com.hazelcast.internal.nio.BufferObjectDataInput;
import com.hazelcast.nio.serialization.FieldType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;

import static com.hazelcast.nio.serialization.FieldType.UTF;

public class CompactReaderWithoutOffset extends DefaultCompactReader {

    public CompactReaderWithoutOffset(Compact serializer, BufferObjectDataInput in, Schema schema, @Nullable Class associatedClass) {
        super(serializer, in, schema, associatedClass);
    }

    protected int readPosition(@Nonnull String fieldName, FieldType fieldType) {
        try {
            FieldDescriptorImpl fd = getFieldDefinition(fieldName, fieldType);
            int index = fd.getIndex();
            int searchPos = schema.getPrimitiveOffsetEnd() + offset;
            while (searchPos < finalPosition) {
                int indexOfVariableLengthField = in.read(searchPos);
                int length = in.readInt(searchPos + 1);

                if (indexOfVariableLengthField == index) {
                    if (length == NULL_POSITION) {
                        return NULL_POSITION;
                    }
                    return searchPos + 5;
                } else {
                    if (length == NULL_POSITION) {
                        searchPos += 5;
                    } else {
                        searchPos += length;
                    }
                }
            }
        } catch (IOException e) {
            throw illegalStateException(e);
        }
        assert false : "Should not ever happen";
        return NULL_POSITION;
    }

    @Override
    public String readUTF(@Nonnull String fieldName) {
        //in.readUTF already supports nullable
        final int currentPos = in.position();
        try {
            int pos = readPosition(fieldName, UTF);
            if(pos == NULL_POSITION) {
                return null;
            }
            in.position(pos);
            return in.readUTF();
        } catch (IOException e) {
            throw illegalStateException(e);
        } finally {
            in.position(currentPos);
        }
    }
}
