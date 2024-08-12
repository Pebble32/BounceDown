# BounceDown Game

## Overview

BounceDown is a simple clone of the popular game "Bounce Down 2," developed as a practice project. The objective of the game is to control a bouncing ball as it descends through platforms, trying to avoid falling off the screen or getting crushed. The game is built using Java and JavaFX. The graphics are intentionally simple, featuring basic objects and a freely usable background.

## Features

- **Gameplay**: You control a ball that continuously bounces down platforms.
- **Keyboard Controls**: The game is controlled via the keyboard arrow keys.
- **Simple Graphics**: The game features basic graphic elements, including simple shapes for the ball and platforms, along with a freely usable background image.

## Purpose

This project was developed as a practice exercise rather than for any commercial or production use. It serves as a way to explore Java programming, JavaFX for GUI development, and basic game mechanics. The focus was on learning and experimenting with code rather than creating a polished or marketable product.

## Project Structure

- **src/main/java/vidmot**: Contains the controller classes responsible for managing the game's GUI and user interactions.
  - `BounceController.java`: Manages the main game logic and interactions, such as controlling the bouncing ball.
  - Other classes like `Leikbord.java`, `Pallur.java`, and `Lost.java` handle different game components (e.g., the game board, platforms, and game-over scenarios).

- **src/main/java/vinnsla**: Contains the core logic and data handling for the game.
  - `Leikur.java`: Implements the main game mechanics, including the ball's movement and interaction with platforms.
  - `Data.java`: Manages game data, such as the player's score or level progression.

- **src/main/resources**: Includes FXML files for defining the layout of the GUI and stylesheets for customizing the appearance.
  - **vidmot**: Contains FXML files for different views like `bounce-view.fxml`, `leikbord-view.fxml`, and `lost-view.fxml`.
  - **stylesheets**: Contains CSS files and image assets like `background.webp` and `bolti.jpg`, which are used to style the game and its components.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Ensure you have JDK 8 or higher installed on your system.
- **JavaFX**: This project uses JavaFX for the GUI, so make sure you have JavaFX set up.

### Running the Game

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Pebble32/BounceDown.git
   ```
2. **Navigate to the Project Directory**:
   ```bash
   cd BounceDown
   ```
3. **Build the project**:
   * If you're using an IDE like IntelliJ IDEA or Eclipse, simply load the project and build it
   * or run command
     ```bash
     ./mvn clean install
     ```
4. **Run the Game**:
   ```bash
   java -jar target/BounceDown.jar
   ```
#### Controlls
* Arrow Keys: Use the left and right arrow keys to control the bouncing ball as it moves through the platforms.
