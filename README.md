# 🟧 Polygon Area Calculator

## 📌 Overview

The **Polygon Area Calculator** is an interactive Java application that allows users to dynamically modify a square into any valid **3-sided (triangle) or 4-sided (quadrilateral) polygon**. Users can drag and reposition vertex points to reshape the polygon, and the application will automatically compute and display the following:

✅ **Polygon Area**  
✅ **Angle Measurements**  
✅ **Side Lengths**

## 🔧 Features

- 🎯 **Drag & Drop Interface** – Easily modify the shape by moving vertex points
- 📐 **Real-Time Calculations** – Instantly updates area, angles, and side lengths
- 🔄 **Flexible Shape Transformation** – Convert a square into a **triangle** or **quadrilateral** dynamically
- 🖥️ **Modern Java Implementation** – Built using **Java Swing** for interactive visualization
- 🎨 **Professional UI** – Clean, modern interface with intuitive controls

## 🚀 How to Run the Project

### 📋 Prerequisites

Before running the project, ensure you have the following installed:

1. **Java Development Kit (JDK) 8 or higher**

   - Download from: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
   - Verify installation: `java -version`

2. **Git** (for cloning the repository)
   - Download from: [Git](https://git-scm.com/)

### 🎯 Step-by-Step Instructions

#### **Method 1: Quick Start (Recommended)**

1. **Clone or Download the Project**

   ```bash
   # If using Git
   git clone <repository-url>
   cd Polygon-Area-Calculator-main

   # Or download and extract the ZIP file
   ```

2. **Navigate to Project Directory**

   ```bash
   cd "Polygon-Area-Calculator-main 4"
   ```

3. **Build the Application**

   ```bash
   # For Linux/macOS
   ./build.sh

   # For Windows
   build.bat
   ```

4. **Run the Application**

   ```bash
   # For Linux/macOS
   ./run.sh

   # For Windows
   run.bat

   # Or directly with Java
   java -jar build/jar/PolygonAreaCalculator.jar
   ```

#### **Method 2: Manual Build and Run**

1. **Compile the Source Code**

   ```bash
   # Create build directories
   mkdir -p build/classes
   mkdir -p build/jar

   # Compile Java files
   javac -d build/classes -cp src src/gui/*.java src/ExtraFunctions/*.java
   ```

2. **Create JAR File**

   ```bash
   # Create JAR with manifest
   jar cfm build/jar/PolygonAreaCalculator.jar manifest.txt -C build/classes .
   ```

3. **Run the Application**
   ```bash
   java -jar build/jar/PolygonAreaCalculator.jar
   ```

#### **Method 3: Using Ant Build System**

1. **Install Ant** (if not already installed)

   ```bash
   # macOS with Homebrew
   brew install ant

   # Ubuntu/Debian
   sudo apt-get install ant
   ```

2. **Build and Run**

   ```bash
   # Build JAR
   ant jar

   # Run application
   ant run

   # Create distribution
   ant dist
   ```

### 🎮 How to Use the Application

1. **Launch the Application**

   - The application will start with a professional splash screen
   - Click "Get Started" or wait for auto-transition

2. **Interactive Polygon Manipulation**

   - **Drag Vertices**: Click and drag the green circular vertices (A, B, C, D) to reshape the polygon
   - **Real-time Updates**: Watch area and angles update automatically as you move vertices
   - **Visual Feedback**: Cursor changes when hovering over interactive elements

3. **Application Features**

   - **Reset Button**: Returns polygon to original square shape
   - **Go Back Button**: Returns to splash screen
   - **Side Length Input**: Enter specific side lengths for measurements
   - **Real-time Display**: Area and angles shown in real-time

4. **Understanding the Interface**
   - **Area Display**: Shows polygon area in square units
   - **Angle Display**: Shows all four interior angles (A, B, C, D)
   - **Side Length Labels**: Displayed on each side of the polygon
   - **Vertex Labels**: A, B, C, D labels on vertices

### 🔧 Troubleshooting

#### **Common Issues and Solutions**

1. **Java Not Found**

   ```bash
   # Error: 'java' is not recognized
   # Solution: Install Java 8+ and add to PATH
   java -version  # Verify installation
   ```

2. **Permission Denied (Linux/macOS)**

   ```bash
   # Error: Permission denied
   # Solution: Make scripts executable
   chmod +x build.sh run.sh
   ```

3. **Compilation Errors**

   ```bash
   # Error: Cannot find symbol
   # Solution: Ensure all source files are present
   ls src/gui/ src/ExtraFunctions/
   ```

4. **JAR File Not Found**

   ```bash
   # Error: Could not find or load main class
   # Solution: Build the project first
   ./build.sh
   ```

5. **Display Issues**
   - **macOS**: Ensure X11 or XQuartz is installed for GUI
   - **Linux**: Install required GUI libraries
   - **Windows**: Ensure Java is properly installed

### 📁 Project Structure

```
PolygonAreaCalculator/
├── src/                          # Source code
│   ├── gui/                     # GUI components
│   │   ├── Start.java          # Main entry point & splash screen
│   │   └── Plot.java           # Interactive polygon drawing
│   └── ExtraFunctions/         # Mathematical calculations
│       ├── getPoints.java      # Base class for point management
│       ├── getLength.java      # Side length calculations
│       └── getAngles.java      # Angle calculations & area computation
├── build/                       # Compiled files
│   ├── classes/                # Compiled Java classes
│   └── jar/                    # Executable JAR file
├── bin/                        # Original compiled classes
├── README.md                   # This file
├── DEPLOYMENT.md              # Deployment guide
├── build.sh                   # Linux/macOS build script
├── build.bat                  # Windows build script
├── run.sh                     # Linux/macOS launcher
├── run.bat                    # Windows launcher
└── manifest.txt               # JAR manifest
```

### 🎨 User Interface Features

- **Modern Design**: Professional blue color scheme with clean typography
- **Intuitive Controls**: Drag-and-drop vertex manipulation
- **Real-time Feedback**: Instant calculation updates
- **Professional Styling**: Rounded corners, shadows, and modern aesthetics
- **Cross-platform**: Works on Windows, macOS, and Linux

### 📊 Mathematical Algorithms

1. **Area Calculation**: Shoelace formula (Surveyor's formula)
2. **Side Length**: Euclidean distance formula
3. **Angle Calculation**: Law of Cosines with cross product validation
4. **Concave Detection**: Cross product analysis for proper angle adjustment

### 🚀 Deployment Options

The project includes multiple deployment options:

1. **Local JAR**: Direct execution on any Java-enabled system
2. **GitHub Releases**: Easy distribution via GitHub
3. **Web Deployment**: Java Web Start for online access
4. **Docker**: Containerized deployment

See `DEPLOYMENT.md` for detailed deployment instructions.

### 📞 Support

- **College**: Indian Institute of Information Technology, Allahabad
- **Project**: Polygon Area Calculator (OOM C3)
- **Issues**: Check GitHub issues or contact development team

---

⚡ **Fun Fact:** Did you know? The formula for a polygon's area varies depending on its shape, but for any irregular polygon, the **Shoelace Theorem** provides a simple way to compute it using vertex coordinates! 🎯

---

**Happy Calculating! 🎨📐**
