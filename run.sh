#!/bin/bash

echo "Starting Polygon Area Calculator..."
echo

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "Error: Java is not installed or not in PATH"
    echo "Please install Java 8 or higher from: https://www.oracle.com/java/technologies/downloads/"
    exit 1
fi

# Check if the JAR file exists
if [ ! -f "build/jar/PolygonAreaCalculator.jar" ]; then
    echo "Building the application..."
    ./build.sh
    if [ $? -ne 0 ]; then
        echo "Error: Failed to build the application"
        exit 1
    fi
fi

echo "Launching Polygon Area Calculator..."
java -jar "build/jar/PolygonAreaCalculator.jar" 