#!/bin/bash
set -e

# echo "Building native binary..."
# sbt GraalVMNativeImage/packageBin

echo "Building Docker image..."
docker build -f Dockerfile.test -t kgb64-decoder-test .

echo "Testing with sample encoded string..."
# You'll need to replace this with an actual KGB64-encoded string for real testing
SAMPLE_ENCODED="H4sIAAAAAAAAAN1TQU7DMBAUr+DuK2nlpE1V8gKOHHpDlWVip1g4trGdplWUV/IhdhNEQaVSOFLJslbjyezuZPfm/bYje+mDsoYUJJvTOSUJcV5WSkfASdERK/AOkfvIBI8SiTRLZxTOEtjSiDM8B7yUWoPAU7rM1km6XKy2oGwdi0eHXC+DEtLEAFShvCzjWMTOsmgBi6qWlec1clPKa0BZ7mpMeHDSw6uJXJMi+kb2fUL4IIAJO6IMFO+shqrYEO+R2ZGaH9iOO1B83GQPINWYV2Pb4atZtu17KNFLZ30cOm/MGwuq/hYGBqkZdoZgq4yw7Zhy9AeL/uHPJqUFxXNH13B/2vULbVXk9ydan1xSzKcp5meK268/YhqtL7mIOgFwORhQWRsrrjUrj6VWJTu9/tkHOs2HbDHZh0mK+ZniNB+cqLAz0QJ1EGVChejVc4NTdvVTYOO4MKVtTBwG//91io3U3LAX2/hx+Q2GV77Cff8BRpoBwNUFAAA="

echo "Running decoder in container..."
docker run --rm kgb64-decoder-test "$SAMPLE_ENCODED"

echo ""
echo "Test complete! If you saw output above, the binary works in the container."
echo ""
echo "To test with your own encoded string:"
echo "  docker run --rm kgb64-decoder-test \"YOUR_ENCODED_STRING\""
