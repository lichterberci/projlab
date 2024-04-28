#!/bin/bash

# Define project directories
SRC_DIR="src"
OUT_DIR="out"
LIB_DIR="lib"

ARTIFACT="projlab.jar"

# Compile source files
echo "Compiling..."
javac -d $OUT_DIR $(find $SRC_DIR -type f -not -path "$SRC_DIR/lab/proj/testUseCases/*" -iname "*.java")

# Check if compilation was successful
if [ $? -eq 0 ]; then
    echo "Compilation successful."

    # Create a manifest file if necessary (for JAR)
    echo "Main-Class: lab.proj.Main" > manifest.txt

    # Create a JAR file
    echo "Creating JAR file..."
    jar cfm $ARTIFACT manifest.txt -C $OUT_DIR .

    # Clean up temporary files
    rm manifest.txt

    echo "JAR file created: $ARTIFACT"
else
    echo "Compilation failed."
fi
