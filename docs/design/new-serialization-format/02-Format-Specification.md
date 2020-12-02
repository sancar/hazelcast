# New Serialization Format Specification

Every serialized object will consist of a header and the data and the footer.
We will also have a schema seperate from the serialized object. 

## Header 

| Name      | Number of bytes | Explanation                                                                |
|-----------|-----------------|----------------------------------------------------------------------------|
| Hash      | 4               | BIG_ENDIAN integer, used for key objects. Not applicable to value objects. |
| Type id   | 4               | integer, determines the serializer to be used. -59 for compact.            |
| Schema id | 8               | long,  Hash of the schema                                                  |

## Data

| Name                  | Number of bytes | Explanation                                                    |
|-----------------------|-----------------|----------------------------------------------------------------|
| Schema Id             | 8               | long, Hash of the schema                                       |
| Length                | 4               | integer, length of the data starting and the end of schema id  |
| FixedLength Fields    | .....           | Offsets of these fields can be found on the schema             |
| VariableLength Fields | .....           | Offsets of these field will be written in the footer           |

## Footer

| Name                   | Number of bytes | Explanation                                          |
|------------------------|-----------------|------------------------------------------------------|
| VariableFieldOffset 0  | 4               | Offsets of variable length fields.                   |
| VariableFieldOffset 1  | 4               | The index of a field offset is written in the Schema |
| VariableFieldOffset n  | 4               |                                                      |
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



Flat buffers 
 Each scalar is also always represented in little-endian format, 
 as this corresponds to all commonly used CPUs today. 
 FlatBuffers will also work on big-endian machines, but will be slightly slower because of additional byte-swap intrinsics.
IEEE-754 for floatıng poınt
The two's complemented representation is used for signed integers.
The endianness is the same for floating-point numbers as for integers.
https://google.github.io/flatbuffers/flatbuffers_internals.html

Avro
Primitive Types
The set of primitive type names is:
null: no value
boolean: a binary value
int: 32-bit signed integer
long: 64-bit signed integer
float: single precision (32-bit) IEEE 754 floating-point number
double: double precision (64-bit) IEEE 754 floating-point number
bytes: sequence of 8-bit unsigned bytes
string: unicode character sequence

Capn Proto
The built-in primitive types are encoded as follows:
Void: Not encoded at all. It has only one possible value thus carries no information.
Bool: One bit. 1 = true, 0 = false.
Integers: Encoded in little-endian format. Signed integers use two’s complement.
Floating-points: Encoded in little-endian IEEE-754 format.
Primitive types must always be aligned to a multiple of their size. Note that since the size of a Bool is one bit, this means eight Bool values can be encoded in a single byte – this differs from C++, where the bool type takes a whole byte.

Thrift
Base Types
bool: A boolean value (true or false), one byte
byte: A signed byte
i16: A 16-bit signed integer
i32: A 32-bit signed integer
i64: A 64-bit signed integer
double: A 64-bit floating point number
binary: A byte array
string: Encoding agnostic text or binary string
Note that Thrift does not support unsigned integers because they have no direct translation to native (primitive) types in many of Thrift’s target languages.
Thrift maps the various base and container types to Java types as follows:
bool: boolean
binary: byte[]
byte: byte
i16: short
i32: int
i64: long
double: double
string: String
list<t1>: List<t1>
set<t1>: Set<t1>
map<t1,t2>: Map<t1, t2>
As you can see, the mapping is straight forward and one-to-one for the most part. This is not surprising given that Java was the primary target language when the Thrift project

Protobuf
.proto Type	Notes	C++ Type	Java Type	Python Type[2]	Go Type	Ruby Type	C# Type	PHP Type	Dart Type
double		double	double	float	float64	Float	double	float	double
float		float	float	float	float32	Float	float	float	double
int32	Uses variable-length encoding. Inefficient for encoding negative numbers – if your field is likely to have negative values, use sint32 instead.	int32	int	int	int32	Fixnum or Bignum (as required)	int	integer	int
int64	Uses variable-length encoding. Inefficient for encoding negative numbers – if your field is likely to have negative values, use sint64 instead.	int64	long	int/long[3]	int64	Bignum	long	integer/string[5]	Int64
uint32	Uses variable-length encoding.	uint32	int[1]	int/long[3]	uint32	Fixnum or Bignum (as required)	uint	integer	int
uint64	Uses variable-length encoding.	uint64	long[1]	int/long[3]	uint64	Bignum	ulong	integer/string[5]	Int64
sint32	Uses variable-length encoding. Signed int value. These more efficiently encode negative numbers than regular int32s.	int32	int	int	int32	Fixnum or Bignum (as required)	int	integer	int
sint64	Uses variable-length encoding. Signed int value. These more efficiently encode negative numbers than regular int64s.	int64	long	int/long[3]	int64	Bignum	long	integer/string[5]	Int64
fixed32	Always four bytes. More efficient than uint32 if values are often greater than 228.	uint32	int[1]	int/long[3]	uint32	Fixnum or Bignum (as required)	uint	integer	int
fixed64	Always eight bytes. More efficient than uint64 if values are often greater than 256.	uint64	long[1]	int/long[3]	uint64	Bignum	ulong	integer/string[5]	Int64
sfixed32	Always four bytes.	int32	int	int	int32	Fixnum or Bignum (as required)	int	integer	int
sfixed64	Always eight bytes.	int64	long	int/long[3]	int64	Bignum	long	integer/string[5]	Int64
bool		bool	boolean	bool	bool	TrueClass/FalseClass	bool	boolean	bool
string	A string must always contain UTF-8 encoded or 7-bit ASCII text, and cannot be longer than 232.	string	String	str/unicode[4]	string	String (UTF-8)	string	string	String
bytes	May contain any arbitrary sequence of bytes no longer than 232.	string	ByteString	str	[]byte	String (ASCII-8BIT)	ByteString	string

### Fixed Length Types

### Variable Length Fields

### List,Array,Collection

### Map 