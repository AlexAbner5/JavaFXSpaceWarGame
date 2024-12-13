package com.example.spacewar;

import java.io.*;

public class FileManager {

    // Ruta del archivo de puntajes
    private static final String HIGHSCORE_FILE = "highscores.txt";

    // Metodo para guardar el puntaje más alto y el nivel alcanzado
    // Formatea los datos y los guarda en el archivo highscores.txt
    public static void saveHighScore(int gameCount, int score, int level) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGHSCORE_FILE, true))) {
            // Formateamos los datos
            String formattedLine = String.format("Partida %-2d ) Score: %-3d Level: %-3d", gameCount, score, level);
            writer.write(formattedLine);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving high score: " + e.getMessage());
        }
    }
}






