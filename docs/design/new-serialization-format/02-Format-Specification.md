# New Serialization Format Specification

Every serialized user object will consist of a header and the composed data.
We will also have a schema separate from the serialized object. 

This document will first describe the data types that will be used as building blocks, then will continue
with composed data

## Data Types

| Type                                                                                                                                                                                                           | Type id | Fixed-Length| SQL             | Java                     | C++ | Python | Nodejs | C# | Go |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------|------------|-----------------|--------------------------|-----|--------|--------|----|----|
| boolean: true/false, 1-bit packed to one-byte. <br>up-to 8 booleans take 1 byte on a data,up-to 16 booleans take 2 byte on a data so on                                                                        | 0       | Yes        | BOOLEAN         | boolean                  |     |        |        |    |    |
| i8 : 8 bit two's complement signed integer                                                                                                                                                                     | 2       | Yes        | TINYINT         | Byte                     |     |        |        |    |    |
| i16: 16-bit two's-complement signed integer                                                                                                                                                                    | 4       | Yes        | SMALLINT        | short                    |     |        |        |    |    |
| i32: 32-bit two's-complement signed integer                                                                                                                                                                    | 6       | Yes        | INTEGER         | int                      |     |        |        |    |    |
| i64: 64-bit two's-complement signed integer                                                                                                                                                                    | 8       | Yes        | BIGINT          | long                     |     |        |        |    |    |
| float : 32-bit IEEE 754 floating-point number                                                                                                                                                                  | 10      | Yes        | REAL            | float                    |     |        |        |    |    |
| double: 64-bit IEEE 754 floating-point number                                                                                                                                                                  | 12      | No         | DOUBLE          | double                   |     |        |        |    |    |
| utf8 : utf8 string https://tools.ietf.org/html/rfc3629                                                                                                                                                         | 14      | No         | VARCHAR         | String                   |     |        |        |    |    |
| utf16: utf16 string https://tools.ietf.org/html/rfc2781                                                                                                                                                        | 16      | No         | VARCHAR         | String                   |     |        |        |    |    |
| arbitrary precision two's-complement signed integer: represented as series of bytes(i8)                                                                                                                        | 18      | No         | DECIMAL         | java.math.BigInteger     |     |        |        |    |    |
| arbitrary precision and scale floating-point number: represented as unscaledValue x 10 ^ -scale <br>unscaledValue: series of byte(i8)  scale : single i32 for scale                                            | 20      | No         | DECIMAL         | java.math.BigDecimal     |     |        |        |    |    |
| Date YYYY-MM-DD<br>from 1753-Jan-1 to 9999-Dec-31:<br>i16: year, i8: month, i8:dayOfMonth                                                                                                                      | 22      | Yes        | DATE            | java.time.LocalDate      |     |        |        |    |    |
| Time: HH-MI-SS-NN<br>i8: hour, i8: minute, i8: seconds, i32: nanoseconds                                                                                                                                       | 24      | Yes        | TIME            | java.time.LocalTime      |     |        |        |    |    |
| Timestamp: YYYY-MM-DD-HH-MI-SS-NN<br>i16: year, i8: month,  i8:dayOfMonth,<br>i8 : hour, i8: minute, i8: seconds, i32: nanoseconds                                                                             | 26      | Yes        | TIMESTAMP       | java.time.LocalDateTime  |     |        |        |    |    |
| Timestamp: YYYY-MM-DD-HH-MI-SS-MM Zone<br>i16: year, i8: month, i8:dayOfMonth,<br>i8 : hour, i8: minute,i8: seconds, i32: nanoseconds<br>i32 : offsetSeconds. offsetSeconds range between +/-18:00:00 hour     | 28      | Yes        | TIMESTAMP W/ TZ | java.time.OffsetDateTime |     |        |        |    |    |
| Composed Data: A user defined type composed of data & array & map types                                                                                                                                        | 30      | No         | COMPOSED        | java.lang.Object         |     |        |        |    |    |

Bnf description of ansi sql used as a reference for sql types:
http://jakewheat.github.io/sql-overview/sql-2011-foundation-grammar.html

Note that none of the variable sized data types has `length` information in their definition. 
The `length` of them will be deduced from offsets at the end of the Composed Data. 

## Header 

The Hash and Type id is common for all serialization methods at Hazelcast.

| Name      | Type  | Explanation                                                                |
|-----------|------ |----------------------------------------------------------------------------|
| Hash      | i32   | BIG_ENDIAN integer, used for key objects. Not applicable to value objects. |
| Type id   | i32   | integer, determines the serializer to be used. -60 for compact.            |
| Length    | i32   | length of the rest of the data                                             |

## Composed Data

In this section, we will describe how a user defined type will be represented in the wire level.

| Name                                | Type            | Explanation                                                   |
|-------------------------------------|-----------------|---------------------------------------------------------------|
| Schema Id                           | i64             | Hash of the schema                                            |
| Fixed-Length Fields                 | .....           | Offsets of these fields will be deduced from the schema       |
| Variable-Length FieldOffset index 0 | i32             | The index of a field offset is written in the Schema          |
| Variable-Length FieldOffset index 1 | i32             | Offsets of variable length fields. -1 for null                |
| Variable-Length FieldOffset index n | i32             |                                                               |
| Variable-Length Fields              | .....           |                                                               |

Note that if composed data does not include any variable length field in the schema, "Variable-Length FieldOffset"s and 
"Number of Variable-Length Fields" will not exist on the wire.
Similarly, if there is no fixed length field in the schema, "Fixed-Length Fields" will not exist on the wire. 

Offsets are calculated from the end of the length. So an offset is always less than the length of the data.
Length of the variable-Lengths fields is calculated as:
Length of the variable-Length field i = (Variable-Length FieldOffset i+1) - (Variable-Length FieldOffset i) if i < n
Length of the variable-Length field n = (Length) - (Variable-Length FieldOffset n)

Length is written before offsets so that the binary can be skipped even when the schema cannot be found.  
 
A Variable-length FieldOffset is `-1` if a Variable-Length field is `null`.
Fixed-Length Fields cannot be `null`. 
 
### Fixed-Length Fields 

### Variable-Length Fields
 
## Schema

| Name                       | Type |
|----------------------------|------|
| length of the class name   | i32  |
| class name                 | utf8 |
| number of fields           | i32  |
| padding size               | i8   |   
| length of the field name 0 | i32  |
| field name 0               | utf8 |
| type of the field 0        | i8   |
| length of the field name 1 | i32  |
| field name 1               | utf8 |
| type of the field 1        | i8   |
| length of the field name n | i32  |
| field name n               | utf8 |
| type of the field n        | i8   |

When writing a Schema to the wire fields will be ordered so that same structure will result in same byte representation
and produces same schema id. 
The order is as following:
- Fixed-length fields comes before variable-length fields. 
- Variable-length fields will be ordered according to their name.
- Fixed-length fields will be ordered by the size of the field in descending order so that all fixed-length fields will be 
padded according to their size. 

### Schema id 
We are using 64bit [Rabin fingerprint](https://en.wikipedia.org/wiki/Rabin_fingerprint) to create a schema id.  
The schema id is calculated from the byte array representation of the schema described above.
The implementation that we use as follows:

```
long fingerprint64(byte[] buf) {
  if (FP_TABLE == null) initFPTable();
  long fp = EMPTY;
  for (int i = 0; i < buf.length; i++)
    fp = (fp >>> 8) ^ FP_TABLE[(int)(fp ^ buf[i]) & 0xff];
  return fp;
}

static long EMPTY = 0xc15d213aa4d7a795L;
static long[] FP_TABLE = null;

void initFPTable() {
  FP_TABLE = new long[256];
  for (int i = 0; i < 256; i++) {
    long fp = i;
    for (int j = 0; j < 8; j++)
      fp = (fp >>> 1) ^ (EMPTY & -(fp & 1L));
    FP_TABLE[i] = fp;
  }
}
```


### Arrays 

The type id's for array types are as follows:

| Type                                                         | Type id | Fixed Size |
|--------------------------------------------------------------|---------|------------|
| Array of boolean                                             | 1       | No         |
| Array of i8                                                  | 3       | No         |
| Array of i16                                                 | 5       | No         |
| Array of i32                                                 | 7       | No         |
| Array of i64                                                 | 9       | No         |
| Array of float                                               | 11      | No         |
| Array of double                                              | 13      | No         |
| Array of utf8                                                | 15      | No         |
| Array of utf16                                               | 17      | No         |
| Array of arbitrary precision two's-complement signed integer | 19      | No         |
| Array of arbitrary precision and scale floating-point number | 21      | No         |
| Array of Date                                                | 23      | No         |
| Array of Time                                                | 25      | No         |
| Array of Timestamp                                           | 27      | No         |
| Array of Timestamp                                           | 29      | No         |
| Array of Composed Data                                       | 31      | No         |

The binary representation of an array will change depending on whether the contained type is fixed size or variable sized.
An array cannot have `null` item. 

#### Array of fixed-length items 

| Name            | Type      |
|-----------------|-----------|
| Number of items | i32       |
| Item 0          | item type |
| Item 1          | item type |
| Item 2          | item type |
| Item n          | item type |

#### Array of variable-length sized items 

| Name            | Type      |
|-----------------|-----------|
| Number of items | i32       |
| Item 0 offset   | i32       |
| Item 1 offset   | i32       |
| Item 2 offset   | i32       |
| Item n offset   | i32       |
| Last   offset   | i32       | 
| Item 0          | item type |
| Item 1          | item type |
| Item 2          | item type |
| Item n          | item type |

Offsets are calculated from the end of the schema id of the containing Composed Data.

Length of the variable-Length field i = (Variable-Length FieldOffset i+1) - (Variable-Length FieldOffset i) if i < n
Length of the variable-Length field n = (Last Offset) - (Variable-Length FieldOffset n)
The last offset is to calculate the size of nth item. 

Size of each variable length item is calculated via subtracting its offset from the offset of preceding item.  
For the Item 0, it will be subtracted of the offset of the ComposedData.

### Maps

Maps can be represented as two equal size arrays where the same index key and value belongs to the same entry.
This way if key and/or values are fixed-length, the map will have much more compact representation  without needing the offsets.  
