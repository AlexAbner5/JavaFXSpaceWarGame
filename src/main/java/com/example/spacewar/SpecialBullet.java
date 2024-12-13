package com.example.spacewar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SpecialBullet extends Shot {
    // Constructor de la clase SpecialBullet
    public SpecialBullet(int posX, int posY) {
        super(posX, posY, false, false); // No es un disparo de jefe
    }

    // Este metodo dibuja el disparo especial en la pantalla
    // Es override porque estamos sobreescribiendo el metodo draw de la clase Shot
    @Override
    public void draw(GraphicsContext gc) {
        drawSpecialBullet(gc);
    }

    // Este metodo dibuja el disparo especial en la pantalla
    public void drawSpecialBullet(GraphicsContext gc) {
        int blockSize = 10;
        gc.setFill(Color.DARKGREEN);

        gc.fillRect(posX, posY - 2 * blockSize, blockSize, blockSize);
        gc.fillRect(posX - blockSize, posY - blockSize, blockSize, blockSize);
        gc.fillRect(posX + blockSize, posY - blockSize, blockSize, blockSize);
        gc.fillRect(posX, posY, blockSize, blockSize);
        gc.fillRect(posX, posY + 2 * blockSize, blockSize, blockSize);
        gc.fillRect(posX, posY + 4 * blockSize, blockSize, blockSize);
    }
}

// Esta clase representa un disparo especial en el juego.
// Los cuales son dibujados y puestos con un png.