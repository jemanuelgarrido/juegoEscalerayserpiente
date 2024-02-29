package com.example.parcial13;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

/*public class HelloApplication extends Application {
    private static final int NUM_CASILLAS = 64;
    private int[] tablero;
    private int[] origenCasillasEspeciales;
    private int[] destinoCasillasEspeciales;
    private int[] jugadores;
    private Random dado;
    private Label[] labelsJugadores;
    private Button btnLanzarDado;
    private GridPane tableroUI;

    @Override
    public void start(Stage primaryStage) {
        tablero = new int[NUM_CASILLAS];
        origenCasillasEspeciales = new int[]{7, 11, 31, 30, 40, 43, 50, 59};
        destinoCasillasEspeciales = new int[]{38, 37, 46, 2, 21, 60, 5, 42};
        for (int i = 0; i < NUM_CASILLAS; i++) {
            tablero[i] = i;
        }
        for (int i = 0; i < origenCasillasEspeciales.length; i++) {
            tablero[origenCasillasEspeciales[i] - 1] = destinoCasillasEspeciales[i] - 1;
        }
        jugadores = new int[]{0, 0}; // Ambos jugadores empiezan en la casilla 0
        dado = new Random();

        // Configuración de la interfaz de usuario
        tableroUI = new GridPane();
        tableroUI.setPadding(new Insets(10));
        tableroUI.setHgap(10);
        tableroUI.setVgap(10);
        labelsJugadores = new Label[jugadores.length];
        for (int i = 0; i < labelsJugadores.length; i++) {
            labelsJugadores[i] = new Label("Jugador " + (i + 1) + ": Casilla 1");
            tableroUI.add(labelsJugadores[i], 0, i);
        }
        btnLanzarDado = new Button("Lanzar Dado");
        btnLanzarDado.setOnAction(event -> {
            lanzarDado();
        });
        tableroUI.add(btnLanzarDado, 0, labelsJugadores.length);

        Scene scene = new Scene(tableroUI, 400, 400);
        primaryStage.setTitle("Juego de Escaleras y Serpientes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void lanzarDado() {
        for (int jugadorActual = 0; jugadorActual < jugadores.length; jugadorActual++) {
            int pasos = tirarDado();
            int consecutivosSeis = 0;
            if (pasos == 6) {
                consecutivosSeis++;
                if (consecutivosSeis >= 3) {
                    System.out.println("El jugador " + (jugadorActual + 1) + " ha sacado tres 6 consecutivos. Vuelve a la posición 1.");
                    jugadores[jugadorActual] = 0;
                    consecutivosSeis = 0;
                }
            } else {
                consecutivosSeis = 0;
            }
            moverJugador(jugadorActual, pasos);
            labelsJugadores[jugadorActual].setText("Jugador " + (jugadorActual + 1) + ": Casilla " + (jugadores[jugadorActual] + 1));
            if (jugadores[jugadorActual] == NUM_CASILLAS - 1) {
                System.out.println("¡El jugador " + (jugadorActual + 1) + " ha ganado!");
                btnLanzarDado.setDisable(true); // Desactivar el botón después de que un jugador gane
            }
        }
    }

    private int tirarDado() {
        return dado.nextInt(6) + 1;
    }

    private void moverJugador(int jugador, int pasos) {
        jugadores[jugador] += pasos;
        if (jugadores[jugador] >= NUM_CASILLAS) {
            jugadores[jugador] = NUM_CASILLAS - (jugadores[jugador] - NUM_CASILLAS) - 2;
        }
        jugadores[jugador] = tablero[jugadores[jugador]];
    }

    public static void main(String[] args) {
        launch();
    }
} */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Random;

public class HelloApplication extends Application {
    private static final int NUM_CASILLAS = 64;
    private int[] tablero;
    private int[] origenCasillasEspeciales;
    private int[] destinoCasillasEspeciales;
    private int[] jugadores;
    private Random dado;
    private Button[] casillas;
    private GridPane tableroUI;
    private int jugadorActual;
    private Button btnLanzarDado;
    private Label lblGanador;

    @Override
    public void start(Stage primaryStage) {
        tablero = new int[NUM_CASILLAS];
        origenCasillasEspeciales = new int[]{7, 11, 31, 30, 40, 43, 50, 59};
        destinoCasillasEspeciales = new int[]{38, 37, 46, 2, 21, 60, 5, 42};
        for (int i = 0; i < NUM_CASILLAS; i++) {
            tablero[i] = i;
        }
        for (int i = 0; i < origenCasillasEspeciales.length; i++) {
            tablero[origenCasillasEspeciales[i] - 1] = destinoCasillasEspeciales[i] - 1;
        }
        jugadores = new int[]{0, 0}; // Ambos jugadores empiezan en la casilla 0
        dado = new Random();
        casillas = new Button[NUM_CASILLAS];
        jugadorActual = 0;

        // Configuración de la interfaz de usuario
        tableroUI = new GridPane();
        tableroUI.setPadding(new Insets(10));
        tableroUI.setHgap(10);
        tableroUI.setVgap(10);

        for (int i = 0; i < NUM_CASILLAS; i++) {
            casillas[i] = new Button(String.valueOf(i + 1));
            casillas[i].setPrefSize(50, 50);
            casillas[i].setFont(Font.font(20));
            final int casillaIndex = i;
            casillas[i].setOnAction(event -> {
                if (casillaIndex == jugadores[0] || casillaIndex == jugadores[1]) {
                    lanzarDado();
                }
            });
            tableroUI.add(casillas[i], i % 8, i / 8 + 1); // Se añade 1 para que los números aparezcan en la parte inferior
        }

        // Botón para lanzar el dado
        btnLanzarDado = new Button("Lanzar Dado");
        btnLanzarDado.setOnAction(event -> lanzarDado());
        tableroUI.add(btnLanzarDado, 0, NUM_CASILLAS / 8 + 1);

        // Etiqueta para mostrar al ganador
        lblGanador = new Label();
        lblGanador.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        tableroUI.add(lblGanador, 0, NUM_CASILLAS / 8 + 2);

        actualizarTablero();

        Scene scene = new Scene(tableroUI, 400, 650); // Se aumenta la altura de la escena para mostrar los números en la parte inferior y el mensaje de ganador
        primaryStage.setTitle("Juego de Escaleras y Serpientes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void actualizarTablero() {
        for (int i = 0; i < NUM_CASILLAS; i++) {
            if (i == jugadores[0]) {
                casillas[i].setText("X");
                casillas[i].setTextFill(Color.RED); // Color rojo para el jugador 1
            } else if (i == jugadores[1]) {
                casillas[i].setText("O");
                casillas[i].setTextFill(Color.BLUE); // Color azul para el jugador 2
            } else {
                casillas[i].setText(String.valueOf(i + 1));
                casillas[i].setTextFill(Color.BLACK); // Color negro para las casillas vacías
            }
        }
    }

    private void lanzarDado() {
        int pasos = tirarDado();
        int consecutivosSeis = 0;
        if (pasos == 6) {
            consecutivosSeis++;
            if (consecutivosSeis >= 3) {
                System.out.println("El jugador " + (jugadorActual + 1) + " ha sacado tres 6 consecutivos. Vuelve a la posición 1.");
                jugadores[jugadorActual] = 0;
                consecutivosSeis = 0;
            }
        } else {
            consecutivosSeis = 0;
        }
        moverJugador(jugadorActual, pasos);
        actualizarTablero();
        if (jugadores[jugadorActual] == NUM_CASILLAS - 1) {
            lblGanador.setText("¡El jugador " + (jugadorActual + 1) + " ha ganado!");
            for (Button casilla : casillas) {
                casilla.setDisable(true); // Desactivar todas las casillas después de que un jugador gane
            }
            btnLanzarDado.setDisable(true); // Desactivar el botón después de que un jugador gane
        }
        jugadorActual = (jugadorActual + 1) % 2; // Cambiar al siguiente jugador
    }

    private int tirarDado() {
        return dado.nextInt(6) + 1;
    }

    private void moverJugador(int jugador, int pasos) {
        jugadores[jugador] += pasos;
        if (jugadores[jugador] >= NUM_CASILLAS) {
            jugadores[jugador] = NUM_CASILLAS - (jugadores[jugador] - NUM_CASILLAS) - 2;
        }
        jugadores[jugador] = tablero[jugadores[jugador]];
    }

    public static void main(String[] args) {
        launch();
    }
}

