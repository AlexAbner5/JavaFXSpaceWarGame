package com.example.spacewar;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class SpaceWar extends Application {
    // Constantes
    private static final Random RAND = new Random();
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int PLAYER_SIZE = 60;

    // Variables booleanas
    private boolean isMenu = true;
    Boss boss;
    boolean bossActive = false;

    // Imagenes
    public static final Image BOSS_IMG = new Image(SpaceWar.class.getResource("/images/Boss_blue.png").toExternalForm());
    public static final Image EXPLOSION_IMG = new Image(SpaceWar.class.getResource("/images/explotion2.png").toExternalForm());

    public static final Image[] Kamikaze_IMG = {
            new Image(SpaceWar.class.getResource("/images/kamikaze1.png").toExternalForm()),
            new Image(SpaceWar.class.getResource("/images/kamikaze2.png").toExternalForm()),
            new Image(SpaceWar.class.getResource("/images/kamikaze3.png").toExternalForm()),
            new Image(SpaceWar.class.getResource("/images/kamikaze4.png").toExternalForm()),
            new Image(SpaceWar.class.getResource("/images/kamikaze5.png").toExternalForm()),
            new Image(SpaceWar.class.getResource("/images/kamikaze6.png").toExternalForm())
    };

    // mas variables
    private GraphicsContext gc;
    private Rocket player;
    private List<Shot> _shots;
    private List<Universe> _univ;
    private List<Kamikaze> _Kamikaze;
    private BackgroundMusic backgroundMusic;

    // Variables de movimiento
    private double velocityX = 0;
    private boolean hasMouseMoved = false;
    private boolean isUsingKeyboard = false;
    private double mouseX;

    // Factory para crear los objetos del juego
    private GameFactory factory;

    // Metodo main
    public static void main(String[] args) {
        launch();
    }

    // Metodo start para ininiciar el juego
    @Override
    public void start(Stage stage) {
        // Musica de fondo
        backgroundMusic = new BackgroundMusic();
        String musicPath = getClass().getResource("/Musica/vgertermshock4.wav").toExternalForm();
        backgroundMusic.play(musicPath);

        // Crear el canvas
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        // Crear el timeline para el juego estableciendo la velocidad de actualizacion de los graficos
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(48), e -> run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        // Establecer el cursor del mouse
        canvas.setCursor(Cursor.MOVE);

        // Esto es para que el juego pueda detectar los eventos del teclado y del mouse
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);

        // Eventos del teclado para cuando se presiona una tecla
        scene.setOnKeyPressed(e -> {
            isUsingKeyboard = true;
            hasMouseMoved = false;
            switch (e.getCode()) {
                case A, LEFT -> velocityX = -10;
                case D, RIGHT -> velocityX = 10;
            }
        });

        // Eventos del teclado para cuando se suelta una tecla
        scene.setOnKeyReleased(e -> {
            velocityX = 0;
            isUsingKeyboard = false;
            mouseX = player.posX;
        });

        // Eventos del mouse para cuando se hace click en el canvas
        // Hace la funcion de iniciar el juego, disparar y reiniciar el juego
        // Cuando dispara reproduce un sonido
        canvas.setOnMouseClicked(e -> {
            if (isMenu) {
                isMenu = false;
                setup();
            } else if (GameState.getInstance().isGameOver()) {
                GameState.getInstance().resetState();
                setup();
            } else if (_shots.size() < 2 * 10) {
                if (GameState.getInstance().getScore() >= 25) {
                    _shots.add(factory.createSpecialBullet(player.posX + PLAYER_SIZE / 2, player.posY));
                } else {
                    _shots.add(factory.createShot(player.posX + PLAYER_SIZE / 2, player.posY, false));
                }
                String musicPath2 = getClass().getResource("/Musica/13.FighterShot1.mp3").toExternalForm();
                backgroundMusic.playSoundEffect(musicPath2);
            }
        });

        // Eventos del mouse para cuando se mueve el mouse
        // Hace la funcion de mover la nave del jugador
        canvas.setOnMouseMoved(e -> {
            if (!isUsingKeyboard) {
                mouseX = e.getX();
                mouseX = Math.max(0, Math.min(WIDTH - PLAYER_SIZE, mouseX));
                hasMouseMoved = true;
            }
        });

        //Llamando metodos
        setupBackground();
        stage.setScene(scene);
        stage.setTitle("Space War");
        stage.show();
    }

    // Metodo que inicializa las variables del juego
    // Crea el jugador, el boss y los enemigos
    private void setup() {
        _shots = new ArrayList<>();
        _Kamikaze = new ArrayList<>();
        bossActive = false;

        // Reinicia el estado del juego
        GameState.getInstance().resetState();
        GameState.getInstance().addObserver(new HUDUpdater(gc));

        factory = new SpaceGameFactory();
        player = new Player(WIDTH / 2, HEIGHT - PLAYER_SIZE, PLAYER_SIZE, "/images/player.png");
        boss = (Boss) factory.createBoss(WIDTH / 2 - PLAYER_SIZE, 50);

        // Crea los enemigos kamikaze al azar
        IntStream.range(0, 10)
                .mapToObj(i -> factory.createEnemy(
                        50 + RAND.nextInt(WIDTH - 100),
                        0))
                .forEach(enemy -> _Kamikaze.add((Kamikaze) enemy));
    }

    // Metodo que inicializa el fondo del juego
    private void setupBackground() {
        _univ = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            _univ.add(new Universe());
        }
    }

    // Metodo que se ejecuta continuamente para actualizar los graficos del juego
    private void run(GraphicsContext gc) {
        if (isMenu) {
            drawMenu(gc);
        } else {
            updatePlayerPosition();
            drawGame(gc);
        }
    }

    // Metodo que actualiza la posicion del jugador
    private void updatePlayerPosition() {
        if (isUsingKeyboard) {
            player.posX += velocityX;
        } else if (hasMouseMoved) {
            player.posX = (int) mouseX;
        }
        player.posX = Math.max(0, Math.min(WIDTH - PLAYER_SIZE, player.posX));
    }

    // Metodo que dibuja el menu del juego
    private void drawMenu(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        _univ.forEach(universe -> universe.draw(gc));
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(40));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText("SPACE WAR", WIDTH / 2, HEIGHT / 2 - 100);
        gc.setFont(Font.font(25));
        gc.fillText("Click to Start", WIDTH / 2, HEIGHT / 2 + 50);
    }

    // Metodo que dibuja el juego
    private void drawGame(GraphicsContext gc) {

        // Dibuja el fondo del juego
        gc.setFill(Color.grayRgb(20));
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        _univ.forEach(universe -> universe.draw(gc));
        GameState gameState = GameState.getInstance();
        gameState.notifyObservers();

        // Dibuja el mensaje de Game Over
        if (gameState.isGameOver()) {
            gc.setFont(Font.font(35));
            gc.setFill(Color.WHITE);
            gc.setTextAlign(TextAlignment.CENTER);
            
            String endMessage = bossActive && boss != null && boss.destroyed ? "You won!" : "You lost";
            gc.fillText("Game Over: " + endMessage, WIDTH / 2.0, HEIGHT / 2.5 - 40);
            gc.fillText("Your score is: " + gameState.getScore(), WIDTH / 2.0, HEIGHT / 2.5 + 20);
            gc.fillText("Click to play again", WIDTH / 2.0, HEIGHT / 2.5 + 80);
            return;
        }

        // Dibuja el jugador y los enemigos
        player.update();
        player.draw(gc);

        // Variables para habilitar la bala especial
        boolean specialBulletEnabled = gameState.getScore() >= 25;

        // Aparece el boss cuando el jugador llega a 50 puntos
        if (gameState.getScore() >= 50 && !bossActive) {
            bossActive = true;
            _Kamikaze.clear();
            if (gameState.getLevel() == 1) {
                gameState.levelUp();
            }
        }

        // Esto maneja la logica de dibujo, actualizacion y deteccion de colisiones entre el jugador, el jefe, los kamikaze y disparos.
        if (bossActive) {
            boss.update();
            boss.draw(gc);
            boss.drawShots(gc);
            for (Shot bossShot : boss._bossShots) {
                if (bossShot.colide(player) && !player.exploding) {
                    player.explode();
                    bossShot.toRemove = true;
                }
            }
            if (player.colide(boss) && !player.exploding) {
                player.explode();
            }
            for (Shot shot : _shots) {
                if (shot.colide(boss)) {
                    boss.takeDamage();
                    shot.toRemove = true;
                }
            }
            if (boss.destroyed) {
                gameState.increaseScore(10000);
                gameState.setGameOver(true);
                return;
            }
        } else {
            for (int i = _Kamikaze.size() - 1; i >= 0; i--) {
                Kamikaze k = _Kamikaze.get(i);
                k.update();
                if (k.exploding) {
                    k.drawExplosion(gc);
                    if (k.destroyed) {
                        gameState.increaseScore(1);
                        _Kamikaze.set(i, (Kamikaze) factory.createEnemy(
                                50 + RAND.nextInt(WIDTH - 100),
                                0
                        ));
                    }
                    continue;
                }
                k.draw(gc);
                if (k.posY >= HEIGHT) {
                    _Kamikaze.set(i, (Kamikaze) factory.createEnemy(
                            50 + RAND.nextInt(WIDTH - 100),
                            0
                    ));
                    continue;
                }
                if (player.colide(k) && !player.exploding) {
                    player.explode();
                }
            }
        }

        //Este ciclo actualiza, dibuja y elimina disparos, verificando colisiones con enemigos kamikaze y activando explosiones.
        for (int i = _shots.size() - 1; i >= 0; i--) {
            Shot shot = _shots.get(i);
            if (shot.posY < 0 || shot.toRemove) {
                _shots.remove(i);
                continue;
            }
            shot.update();
            if (specialBulletEnabled && shot instanceof SpecialBullet) {
                ((SpecialBullet) shot).drawSpecialBullet(gc);
            } else {
                shot.draw(gc);
            }
            for (Kamikaze k : _Kamikaze) {
                if (shot.colide(k) && !k.exploding) {
                    k.explode();
                    shot.toRemove = true;
                }
            }
        }

        // Esto hace que el juego termine cuando el jugador es destruido
        if (player.destroyed && !gameState.isGameOver()) {
            gameState.setGameOver(true);
            gameState.saveHighScore();
        }
    }
}