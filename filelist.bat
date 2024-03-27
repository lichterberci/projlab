@echo off
setlocal enabledelayedexpansion

rem Set the source directory
set "source_dir=src"

rem Find Java files in the source directory recursively
for /r "%source_dir%" %%F in (*.java) do (
    rem Extract file name, size, and date
    for /f "tokens=1,5,6 delims= " %%A in ('dir /-C "%%F" ^| findstr /C:"/"') do (
        rem Extracting just the file name
        set "file_name=%%~nxF"
        rem Extracting file size and date
        set "file_size=%%A"
        set "file_date=%%B %%C"

        rem Print in the desired format
        echo !file_name!,!file_size!,!file_date!
    )
)

endlocal
