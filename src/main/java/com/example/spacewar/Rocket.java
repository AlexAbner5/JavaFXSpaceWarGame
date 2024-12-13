package com.example.spacewar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Rocket {
    // Variables de la clase Rocket
    int posX, posY, size;
    boolean exploding, destroyed;
    Image img;
    int explosionStep = 0;

    // Constantes de la clase Rocket para dibujar la explosion
    static final int EXPLOSION_W = 512; // Ancho de cada cuadro
    static final int EXPLOSION_H = 512; // Alto de cada cuadro
    static final int EXPLOSION_COL = 8; // Número de columnas
    static final int EXPLOSION_ROWS = 1; // Número de filas
    static final int EXPLOSION_STEPS = EXPLOSION_COL * EXPLOSION_ROWS;

    // Constructor de la clase Rocket
    public Rocket(int posX, int posY, int size, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.size = size;
        this.img = image;

    }

    // Metodo que hace explotar el cohete
    public void explode() {
        exploding = true;
        explosionStep = 0;
    }

    // Metodo que verifica si el cohete colisiona con otro cohete
    public boolean colide(Rocket other) {
        if (this.destroyed || other.destroyed) return false;
        int distance = (int) Math.sqrt(Math.pow((this.posX + this.size / 2) - (other.posX + other.size / 2), 2) +
                Math.pow((this.posY + this.size / 2) - (other.posY + other.size / 2), 2));
        return distance < (this.size / 2 + other.size / 2);
    }

    // Llama al metodo update que actualiza el cohete
    public abstract void update();

    // Metodo que dibuja la explosion del cohete
    public void drawExplosion(GraphicsContext gc) {
        int frame = explosionStep;
        int col = frame % EXPLOSION_COL;
        int row = frame / EXPLOSION_COL;

        if (frame < EXPLOSION_STEPS) {
            gc.drawImage(SpaceWar.EXPLOSION_IMG,
                    col * EXPLOSION_W, row * EXPLOSION_H,
                    EXPLOSION_W, EXPLOSION_H,
                    posX, posY, size, size);
            explosionStep++;
        } else {
            destroyed = true;
        }
    }

    // LLama al metodo draw que dibuja el cohete
    public abstract void draw(GraphicsContext gc);
    // LLama al metodo shoot que dispara el cohete
    public abstract Shot shoot();
}

// Este es el "molde" de la naves del juego. Las naves pueden ser el jugador, los kamikazes o el jefe final.
// Estos hereadaran de esta clase y sobreescribiran los metodos para definir su comportamiento.





