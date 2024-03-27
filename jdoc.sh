#!/bin/bash

# Check if a Java file is provided as an argument
if [ $# -ne 1 ]; then
    echo "Usage: $0 <JavaFile>"
    exit 1
fi

# Check if the provided file exists
if [ ! -f "$1" ]; then
    echo "Error: File $1 does not exist"
    exit 1
fi

# Extract class name from Java file
class_name=$(grep -oP "(?<=class\s)\w+" "$1")

# Check if class name is found
if [ -z "$class_name" ]; then
    echo "Error: Unable to extract class name from $1"
    exit 1
fi

# Extract JavaDoc comments for the class and remove asterisks
java_doc=$(awk '/\/\*\*/,/\*\// {gsub(/^\s*\*\s*/, ""); print}' "$1" | sed '/^$/d')

# Check if JavaDoc comments are found
if [ -z "$java_doc" ]; then
    echo "Warning: No JavaDoc comments found for class $class_name in $1"
else
    echo "JavaDoc for class $class_name in $1:"
    echo "$java_doc"
fi
