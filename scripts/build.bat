@echo off

rem Define project directories
set SRC_DIR=src
set OUT_DIR=out
set LIB_DIR=lib

set ARTIFACT=projlab.jar

set FILELIST=sources.txt

dir SRC_DIR /s /b *.java > "%FILELIST%"

rem Compile source files
echo Compiling...
javac -d %INITIAL_WD%\%OUT_DIR% @"%FILELIST%" >NUL 2>&1

del "%FILELIST%"

rem Check if compilation was successful
if %errorlevel% equ 0 (
    echo Compilation successful.

    rem Create a manifest file if necessary (for JAR)
    echo Main-Class: lab.proj.Main > manifest.txt

    rem Create a JAR file
    echo Creating JAR file...
    jar cfm %ARTIFACT% manifest.txt -C %OUT_DIR% .

    rem Clean up temporary files
    del manifest.txt

    echo JAR file created: %ARTIFACT%
) else (
    echo Compilation failed.
)
