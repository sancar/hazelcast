package com.hazelcast.nio.serialization;

/**
 * Differently from {@link FieldType} Internal Field Type IDS has more detailed separation.
 */
public class InternalFieldTypeIDS {

    public static int BYTE = 1;
    public static int BOOLEAN = 2;
    public static int CHAR = 3;
    public static int SHORT = 4;
    public static int INT = 5;
    public static int LONG = 6;
    public static int FLOAT = 7;
    public static int DOUBLE = 8;
    public static int UTF = 9;
    public static int PORTABLE_ARRAY = 10;
    public static int BYTE_ARRAY = 11;
    public static int BOOLEAN_ARRAY = 12;
    public static int CHAR_ARRAY = 13;
    public static int SHORT_ARRAY = 14;
    public static int INT_ARRAY = 15;
    public static int LONG_ARRAY = 16;
    public static int FLOAT_ARRAY = 17;
    public static int DOUBLE_ARRAY = 18;
    public static int UTF_ARRAY = 19;

    public static int DECIMAL = 20;
    public static int DECIMAL_ARRAY = 21;
    public static int TIME = 22;
    public static int TIME_ARRAY = 23;
    public static int DATE = 24;
    public static int DATE_ARRAY = 25;
    public static int TIMESTAMP = 26;
    public static int TIMESTAMP_ARRAY = 27;
    public static int TIMESTAMP_WITH_TIMEZONE = 28;
    public static int TIMESTAMP_WITH_TIMEZONE_ARRAY = 29;
    public static int FIXED_SIZE = 30;
    public static int FIXED_SIZE_ARRAY = 31;
    public static int VAR_SIZE = 32;
    public static int VAR_SIZE_ARRAY = 33;

    public static FieldType[] fieldType = new FieldType[34];

    {
        fieldType[BYTE] = FieldType.BYTE;
        ///TODO sancar complete the list
        fieldType[FIXED_SIZE] = FieldType.COMPOSED;
        fieldType[FIXED_SIZE_ARRAY] = FieldType.COMPOSED_ARRAY;
        fieldType[VAR_SIZE] = FieldType.COMPOSED;
        fieldType[VAR_SIZE_ARRAY] = FieldType.COMPOSED_ARRAY;
    }
}
