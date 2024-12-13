package com.example.spacewar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Shot {
    // Variables de la clase Shot
    int posX, posY, speed = 10;
    public static final int size = 5;
    boolean toRemove;
    boolean isSpecial;
    boolean isBossShot;

    // Constructor de la clase Shot
    public Shot(int posX, int posY, boolean isSpecial, boolean isBossShot) {
        this.posX = posX;
        this.posY = posY;
        this.isSpecial = isSpecial;
        this.isBossShot = isBossShot;
        System.out.println("Shot created at (" + posX + ", " + posY + ")");
    }

    // Este metodo actualiza la posicion del disparo
    public void update() {
        posY += isBossShot ? speed : -speed;
        System.out.println("Shot updated at (" + posX + ", " + posY + ")");
    }

    // Este metodo dibuja el disparo en la pantalla
    public void draw(GraphicsContext gc) {
        gc.setFill(isBossShot ? Color.ORANGE : Color.DARKSALMON);
        gc.fillOval(posX, posY, size * 2, size * 2);
    }

    // Este metodo verifica si el disparo colisiona con un cohete
    public boolean colide(Rocket rocket) {
        int distance = (int) Math.sqrt(Math.pow((posX + size / 2) - (rocket.posX + rocket.size / 2), 2) +
                Math.pow((posY + size / 2) - (rocket.posY + rocket.size / 2), 2));
        System.out.println("Shot collision distance: " + distance);
        return distance < rocket.size / 2 + size / 2;
    }
}

// Esta clase representa un disparo en el juego. Los disparos pueden ser disparos normales o disparos especiales.



