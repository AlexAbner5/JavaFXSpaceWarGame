package com.example.spacewar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends Rocket {

    // Constructor de la clase Player
    public Player(int posX, int posY, int size, String imagePath) {
        super(posX, posY, size, new Image(Player.class.getResourceAsStream(imagePath)));
    }

    // Este metodo actualiza la posicion del jugador
    @Override
    public void update() {
        if (exploding) {
            explosionStep++;
            destroyed = explosionStep > EXPLOSION_STEPS;
        }
    }

    // Este metodo dibuja el jugador en la pantalla
    @Override
    public void draw(GraphicsContext gc) {
        if (exploding) {
            drawExplosion(gc);
        } else {
            gc.drawImage(img, posX, posY, size, size);
        }
    }

    // Este metodo dispara
    @Override
    public Shot shoot() {

        return new Shot(posX + size / 2, posY, false, false);
    }
}





