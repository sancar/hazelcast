# New Serialization Format Specification

Every serialized object will consist of a header and the data and the footer.
We will also have a schema seperate from the serialized object. 

## Header 

| Name      | Number of bytes | Explanation                                                                |
|-----------|-----------------|----------------------------------------------------------------------------|
| Hash      | 4 bytes         | BIG_ENDIAN integer, used for key objects. Not applicable to value objects. |
| Type id   | 4 bytes         | integer, determines the serializer to be used. -59 for compact.            |
| Schema id | 8 bytes         | long,  Hash of the schema                                                  |

## Data

| Name                  | Number of bytes | Explanation                                                    |
|-----------------------|-----------------|----------------------------------------------------------------|
| Schema Id             | 8 bytes         | long, Hash of the schema                                       |
| Length                | 4 bytes         | integer, length of the the rest of the data including itself   |
| FixedLength Fields    | .....           | Offsets of these fields can be found on the schema             |
| VariableLength Fields | ......          | Offsets of these field will be written in the footer           |

## Footer

| Name                   | Number of bytes | Explanation                                          |
|------------------------|-----------------|------------------------------------------------------|
| VariableFieldOffset 0  | int             | Offsets of variable length fields.                   |
| VariableFieldOffset 1  | int             | The index of a field offset is written in the Schema |
| VariableFieldOffset n  | int             |                                                      |
| Length                 | 4               | int, length of the footer section                    |

## Schema

| Name                       | Number of bytes | Explanation                       |
|----------------------------|-----------------|-----------------------------------|
| length of class name       | 4               | int                               |
| class name                 | n               | unicode character sequence        |
| number of fields           | 4               | int                               |
| length of the field name 0 | 4               | int, length of the footer section |
| field name 0               | n               | unicode character sequence        |
| type of the field 0        | 1               | byte                              |
| length of the field name 1 | 4               | int, length of the footer section |
| field name 1               | n               | unicode character sequence        |
| type of the field 1        | 1               | byte                              |
| length of the field name n | 4               | int, length of the footer section |
| field name n               | n               | unicode character sequence        |
| type of the field n        | 1               | byte                              |

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

## Data Types

### Fixed Length Types

### Variable Length Fields

### List,Array,Collection

### Map 