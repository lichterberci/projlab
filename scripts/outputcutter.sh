#!/bin/bash

# Check if input file is provided
if [ $# -eq 0 ]; then
    echo "Usage: $0 <input_file>"
    exit 1
fi

input_file=$1
output_dir="tests"
mkdir -p $output_dir

# Counter for output files
count=1

# Read input file line by line
while IFS= read -r line; do
    # Check for 😂 delimiter
    if [[ $line == *😂* ]]; then
        # Remove everything before 😂 including 😂 itself
        line="${line#*😂}"
        # Start capturing section
        in_section=true
        section=""
    fi

    # If inside section, append lines to section content
    if [ "$in_section" = true ]; then
        # Remove blank lines from the beginning of the section
        if [[ ! "$line" =~ ^[[:space:]]*$ ]]; then
            # Check for 😘 delimiter
            if [[ $line == *😘* ]]; then
                in_section=false
                # Write captured section to a new file
                echo "$section" > "$output_dir/output-$count.txt"
                ((count++))
            else
                # Append line to section content
                section="$section"$'\n'"$line"
            fi
        fi
    fi
done < "$input_file"

echo "Sections cut and saved in $output_dir directory."
