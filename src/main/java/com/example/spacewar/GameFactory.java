package com.example.spacewar;

public interface GameFactory {
    Rocket createEnemy(int x, int y); // Crea enemigos (por ejemplo, Kamikaze)
    Rocket createBoss(int x, int y); // Crea el jefe final
    Shot createShot(int x, int y, boolean isBossShot); // Crea disparos
    SpecialBullet createSpecialBullet(int x, int y); // Crea disparos especiales
}

// Esta interfaz es para la fabrica del juego. Se encarga de crear enemigos, jefes, disparos y disparos especiales.
// De esta manera, se pueden implementar diferentes tipos de enemigos, jefes, disparos y disparos especiales.
// Implementado asi, el patrón de diseño Factory.