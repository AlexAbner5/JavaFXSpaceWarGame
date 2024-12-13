package com.example.spacewar;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Boss extends Rocket {

    // Variables de la clase Boss
    int health = 20;
    int speed = 3;
    boolean movingRight = true;
    List<Shot> _bossShots = new ArrayList<>();
    private int shotCooldown = 0;

    // Constructor de la clase Boss
    public Boss(int posX, int posY, int size) {
        super(posX, posY, size, SpaceWar.BOSS_IMG);
    }

    // Este metodo actualiza la posicion del jefe
    // Funciona para mover al jefe de un lado a otro
    @Override
    public void update() {
        if (!exploding && !destroyed) {
            if (movingRight) {
                posX += speed;
                if (posX + size >= SpaceWar.WIDTH) movingRight = false;
            } else {
                posX -= speed;
                if (posX <= 0) movingRight = true;
            }

            if (shotCooldown <= 0) {
                _bossShots.add(shoot());
                shotCooldown = 15;
            } else {
                shotCooldown--;
            }
        }
        _bossShots.forEach(Shot::update);
        _bossShots.removeIf(shot -> shot.posY > SpaceWar.HEIGHT);
    }

    // Este metodo dibuja el jefe en la pantalla
    // Dibuja la barra de vida del jefe
    @Override
    public void draw(GraphicsContext gc) {
        if (exploding) {
            drawExplosion(gc); // Explosión del jefe
        } else {
            gc.drawImage(img, posX, posY, size, size); // Dibuja el jefe

            // Dibuja la barra de vida
            gc.setFill(Color.RED);
            gc.fillRect(SpaceWar.WIDTH / 2 - 100, 10, health * 10, 20); // Barra roja
            gc.setStroke(Color.WHITE);
            gc.strokeRect(SpaceWar.WIDTH / 2 - 100, 10, 200, 20); // Borde de la barra
        }
    }

    // Esto hace que el jefe dispare
    public Shot shoot() {
        return new Shot(posX + size / 2 - Shot.size / 2, posY + size, false, true);
    }

    // Este metodo hace que el jefe tome daño
    public void takeDamage() {
        health--;
        if (health <= 0) {
            explode(); // Activa la explosión cuando el jefe es destruido
        }
    }

    // Este metodo dibuja los disparos del jefe
    public void drawShots(GraphicsContext gc) {
        _bossShots.forEach(shot -> shot.draw(gc)); // Dibuja los disparos del jefe
    }
}







