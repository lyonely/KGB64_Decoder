# KGB64Decoder

A command-line tool for decoding Base64-encoded strings that have been serialized with Kryo and compressed with GZIP.

## What It Does

KGB64Decoder decodes data encoded in the following format:
- **K**ryo serialization
- **G**ZIP compression
- **B**ase**64** encoding

The tool automatically detects and pretty-prints JSON output when applicable.

## Installation

### Using Pre-built Binaries

Download the latest Linux x64 binary from the GitLab CI artifacts.

### Building from Source

Requirements:
- GraalVM 21+
- sbt 1.9.7+

Build the native executable:

```bash
sbt "GraalVMNativeImage / packageBin"
```

The binary will be generated at `target/graalvm-native-image/kgb64-decoder`.

## Usage

```bash
kgb64-decoder <base64-encoded-string>
```

### Example

```bash
kgb64-decoder "H4sIAAAAAAAA/..."
```

The tool will:
1. Decode the Base64 string
2. Decompress with GZIP
3. Deserialize using Kryo
4. Output the result (pretty-printed if JSON)

## Dependencies

- **Kryo 5.5.0**: Object serialization
- **Circe 0.14.6**: JSON parsing and formatting
- **GraalVM Native Image**: Native binary compilation

## CI/CD

The GitLab CI pipeline automatically builds a native Linux x64 executable on commits to `main`, `master`, or tags. Artifacts are available for 30 days.