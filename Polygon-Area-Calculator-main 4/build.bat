@echo off
echo Building Polygon Area Calculator...
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Java is not installed or not in PATH
    echo Please install Java 8 or higher from: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

REM Create build directories
if not exist "build\classes" mkdir "build\classes"
if not exist "build\jar" mkdir "build\jar"

REM Compile Java files
echo Compiling Java files...
javac -d "build\classes" -cp "src" src\gui\*.java src\ExtraFunctions\*.java
if %errorlevel% neq 0 (
    echo Error: Compilation failed
    pause
    exit /b 1
)

REM Create JAR file
echo Creating JAR file...
jar cfm "build\jar\PolygonAreaCalculator.jar" manifest.txt -C "build\classes" .
if %errorlevel% neq 0 (
    echo Error: JAR creation failed
    pause
    exit /b 1
)

echo Build completed successfully!
echo JAR file created at: build\jar\PolygonAreaCalculator.jar
pause 