@echo off
echo Starting Polygon Area Calculator...
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Java is not installed or not in PATH
    echo Please install Java 8 or higher from: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

REM Check if the JAR file exists
if not exist "build\jar\PolygonAreaCalculator.jar" (
    echo Building the application...
    call build.bat
    if %errorlevel% neq 0 (
        echo Error: Failed to build the application
        pause
        exit /b 1
    )
)

echo Launching Polygon Area Calculator...
java -jar "build\jar\PolygonAreaCalculator.jar"
pause 