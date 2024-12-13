package com.example.spacewar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Kamikaze extends Rocket {

    // Constructor de la clase Kamikaze
    public Kamikaze(int posX, int posY, int size, Image image) {
        super(posX, posY, size, image);
    }

    // Este metodo calcula la velocidad del Kamikaze basada en el puntaje
    // Esto puede ser modificado para hacer el juego más dificil o más fácil
    private int calculateSpeed() {
        int score = GameState.getInstance().getScore();
        return (score / 2) + 5; // Velocidad dinámica basada en el puntaje
    }

    // Este metodo actualiza la posicion del Kamikaze y verifica si sale de la pantalla
    @Override
    public void update() {
        if (!exploding && !destroyed) {
            posY += calculateSpeed(); // Usa la velocidad calculada
            if (posY > SpaceWar.HEIGHT) {
                destroyed = true; // Marca como destruido si sale de la pantalla
            }
        }
    }

    // Este metodo dibuja el Kamikaze en la pantalla
    @Override
    public void draw(GraphicsContext gc) {
        if (exploding) {
            drawExplosion(gc); // Dibuja la explosión
        } else {
            gc.drawImage(img, posX, posY, size, size); // Dibuja el Kamikaze
        }
    }

    // Metodo para disparar nulo, ya que los Kamikazes no disparan
    @Override
    public Shot shoot() {
        return null;
    }
}








