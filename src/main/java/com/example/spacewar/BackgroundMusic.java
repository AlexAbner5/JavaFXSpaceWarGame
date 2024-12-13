package com.example.spacewar;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BackgroundMusic {

    // Reproductor de música
    private MediaPlayer mediaPlayer;

    // Reproduce la música de fondo
    // Sonido de la sonda espacial Voyager 1 de una ola de plasma en el espacio interestelar
    public void play(String filePath) {
        try {
            // Convierte la ruta absoluta a URI válida
            Media media = new Media(filePath);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Repite en bucle
            mediaPlayer.setVolume(0.5); // Ajusta el volumen (0.0 a 1.0)
            mediaPlayer.play();
        } catch (Exception e) {
            System.err.println("Error loading music: " + e.getMessage());
        }
    }

    // Sonido de las balas disparadas por Player
    public void playSoundEffect(String filePath) {
        try {
            Media sound = new Media(filePath);
            MediaPlayer soundEffectPlayer = new MediaPlayer(sound);
            soundEffectPlayer.setVolume(0.1);
            soundEffectPlayer.play();
        } catch (Exception e) {
            System.err.println("Error playing sound effect: " + e.getMessage());
        }
    }
}

