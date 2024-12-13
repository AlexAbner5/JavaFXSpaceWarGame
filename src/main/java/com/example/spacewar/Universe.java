package com.example.spacewar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Universe {

    // Variables de la clase Universe
    int posX, posY, w, h, speed;
    Color color;
    private static final Random RAND = new Random();

    // Constructor de la clase Universe
    public Universe() {
        this.posX = RAND.nextInt(SpaceWar.WIDTH);
        this.posY = RAND.nextInt(SpaceWar.HEIGHT);
        this.w = RAND.nextInt(5) + 1;
        this.h = w;
        this.color = Color.rgb(200 + RAND.nextInt(56), 200 + RAND.nextInt(56), 255, 0.5 + RAND.nextDouble() * 0.5);
        this.speed = RAND.nextInt(5) + 1;
    }

    // Este metodo dibuja el universo en la pantalla
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(posX, posY, w, h);
        posY += speed;
        if (posY > SpaceWar.HEIGHT) {
            posY = 0;
            posX = RAND.nextInt(SpaceWar.WIDTH);
        }
    }
}

// Esta clase representa el universo en el juego. Son unas estrellas que se mueven hacia abajo en la pantalla.
// Cada estrella tiene un tamaño, color y velocidad aleatoria. Cuando una estrella llega al final de la pantalla,
// se vuelve a colocar en la parte superior de la pantalla en una posición aleatoria.



