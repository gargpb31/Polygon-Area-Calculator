#!/bin/bash

echo "Building Polygon Area Calculator..."
echo

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "Error: Java is not installed or not in PATH"
    echo "Please install Java 8 or higher from: https://www.oracle.com/java/technologies/downloads/"
    exit 1
fi

# Create build directories
mkdir -p build/classes
mkdir -p build/jar

# Compile Java files
echo "Compiling Java files..."
javac -d build/classes -cp src src/gui/*.java src/ExtraFunctions/*.java
if [ $? -ne 0 ]; then
    echo "Error: Compilation failed"
    exit 1
fi

# Create JAR file
echo "Creating JAR file..."
jar cfm build/jar/PolygonAreaCalculator.jar manifest.txt -C build/classes .
if [ $? -ne 0 ]; then
    echo "Error: JAR creation failed"
    exit 1
fi

echo "Build completed successfully!"
echo "JAR file created at: build/jar/PolygonAreaCalculator.jar" 