# 🚀 Polygon Area Calculator - Deployment Guide

## 📋 Overview
This guide provides multiple deployment options for the Polygon Area Calculator Java application.

## 🎯 Deployment Options

### 1. **Local JAR Deployment** (Recommended)

#### Prerequisites:
- Java 8 or higher installed
- Windows, macOS, or Linux

#### Steps:
1. **Build the application:**
   ```bash
   # Windows
   build.bat
   
   # Linux/macOS
   ./build.sh
   ```

2. **Run the application:**
   ```bash
   # Windows
   run.bat
   
   # Linux/macOS
   ./run.sh
   
   # Or directly with Java
   java -jar build/jar/PolygonAreaCalculator.jar
   ```

### 2. **Web Deployment (Java Web Start)**

#### Prerequisites:
- Web server (Apache, Nginx, etc.)
- Java Web Start support

#### Steps:
1. **Create JNLP file:**
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <jnlp spec="1.0+" codebase="http://your-domain.com/polygon-calculator">
       <information>
           <title>Polygon Area Calculator</title>
           <vendor>IIIT Allahabad</vendor>
           <description>Interactive Polygon Area Calculator</description>
       </information>
       <resources>
           <j2se version="1.8+"/>
           <jar href="PolygonAreaCalculator.jar"/>
       </resources>
       <application-desc main-class="gui.Start"/>
   </jnlp>
   ```

2. **Upload files to web server:**
   - Upload `PolygonAreaCalculator.jar` and `PolygonAreaCalculator.jnlp`
   - Configure MIME types for `.jnlp` files

### 3. **Docker Deployment**

#### Prerequisites:
- Docker installed

#### Steps:
1. **Create Dockerfile:**
   ```dockerfile
   FROM openjdk:8-jre-alpine
   COPY build/jar/PolygonAreaCalculator.jar /app/
   WORKDIR /app
   CMD ["java", "-jar", "PolygonAreaCalculator.jar"]
   ```

2. **Build and run:**
   ```bash
   docker build -t polygon-calculator .
   docker run -it --rm polygon-calculator
   ```

### 4. **GitHub Releases**

#### Steps:
1. **Create release:**
   - Go to GitHub repository
   - Click "Releases" → "Create a new release"
   - Upload `PolygonAreaCalculator.jar`
   - Add release notes

2. **Download and run:**
   - Users download JAR from GitHub
   - Run with: `java -jar PolygonAreaCalculator.jar`

## 🔧 Build Commands

### Manual Build:
```bash
# Compile
javac -d build/classes -cp src src/gui/*.java src/ExtraFunctions/*.java

# Create JAR
jar cfm build/jar/PolygonAreaCalculator.jar manifest.txt -C build/classes .

# Run
java -jar build/jar/PolygonAreaCalculator.jar
```

### Using Ant:
```bash
# Build JAR
ant jar

# Run application
ant run

# Create distribution
ant dist
```

## 📦 Distribution Package

### Contents:
- `PolygonAreaCalculator.jar` - Executable JAR file
- `README.md` - User documentation
- `run.bat` - Windows launcher
- `run.sh` - Linux/macOS launcher
- `LICENSE` - License information

## 🌐 Web Deployment Options

### 1. **GitHub Pages + Web Start**
- Host JNLP file on GitHub Pages
- Users can launch via web browser

### 2. **Heroku**
- Deploy as web application
- Use Java buildpack

### 3. **AWS S3 + CloudFront**
- Host JAR and JNLP files
- Serve via CDN for fast access

## 🔒 Security Considerations

### For Web Deployment:
1. **Code Signing:** Sign JAR with certificate
2. **HTTPS:** Use secure connections
3. **Permissions:** Configure appropriate security policies

### For Local Deployment:
1. **Antivirus:** Ensure JAR is not flagged
2. **Permissions:** Run with appropriate user permissions

## 📞 Support

### Common Issues:
1. **Java not found:** Install Java 8+ from Oracle
2. **Permission denied:** Make scripts executable (`chmod +x *.sh`)
3. **JAR not found:** Run build script first

### Contact:
- **College:** Indian Institute of Information Technology, Allahabad
- **Project:** Polygon Area Calculator (OOM C3)

## 🎯 Quick Start

1. **Download:** Get the latest release
2. **Extract:** Unzip the distribution
3. **Run:** Execute `run.bat` (Windows) or `./run.sh` (Linux/macOS)
4. **Enjoy:** Use the interactive polygon calculator!

---

**Happy Calculating! 🎨📐** 