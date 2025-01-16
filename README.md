# Letter Animation Project

## Overview
This project is a Java application that demonstrates animated transformations of letters using the Swing framework. It includes custom animations of two letters (`Z` and `L`) drawn from a set of predefined points loaded from `.txt` files included in the project.

## Features
- Smooth letter animations using Java Swing.
- Dynamic transformations:
  - **Rotation**: Continuous rotation of one letter.
  - **Scaling**: Pulsating scaling effect on one letter.
  - **Color Transitions**: Gradual color shifts.
  - **Position Movement**: Controlled movement of letters across the screen.
- Points defining the letters are read from external `.txt` files (`Z.txt` and `L.txt`).

## Technologies Used
- **Language**: Java
- **Framework**: Swing for GUI and animation
- **Graphics**: Custom rendering with `Graphics2D` for smooth animations

## File Structure
- `AnimationPanel.java`: Core logic for animations, including loading points, transformations, and rendering.
- `Main.java`: Entry point of the application, setting up the JFrame and starting the animation.
- `Z.txt`: Contains coordinate points for the letter "Z".
- `L.txt`: Contains coordinate points for the letter "L".
