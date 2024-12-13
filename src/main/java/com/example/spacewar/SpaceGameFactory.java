package com.example.spacewar;

import javafx.scene.image.Image;

public class SpaceGameFactory implements GameFactory {

    // Este metodo crea un enemigo en una posicion dada
    @Override
    public Rocket createEnemy(int x, int y) {
        // Seleccionar una imagen aleatoria para el Kamikaze
        Image enemyImage = SpaceWar.Kamikaze_IMG[(int) (Math.random() * SpaceWar.Kamikaze_IMG.length)];
        return new Kamikaze(x, y, SpaceWar.PLAYER_SIZE, enemyImage);
    }

    // Este metodo crea un jefe en una posicion dada
    @Override
    public Rocket createBoss(int x, int y) {
        // Crear un jefe con el tamaño y posición dados
        return new Boss(x, y, SpaceWar.PLAYER_SIZE * 3);
    }

    // Este metodo crea un disparo en una posicion dada
    @Override
    public Shot createShot(int x, int y, boolean isBossShot) {
        // Crear un disparo, diferenciando entre disparos de jefe y normales
        return new Shot(x, y, false, isBossShot);
    }

    // Este metodo crea un disparo especial en una posicion dada
    @Override
    public SpecialBullet createSpecialBullet(int x, int y) {
        return new SpecialBullet(x, y);
    }
}


