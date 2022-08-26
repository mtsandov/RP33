package com.mycompany.juego_reguntas_bdt_gui;

import com.mycompany.TDAS.BinaryTree;
import com.mycompany.juego_reguntas_bdt_gui.EmpezarPartidaController;
import com.mycompany.modelo.Animal;
import com.mycompany.modelo.Partida;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PreguntasController implements Initializable {

    static BinaryTree<String> arbolActual;
    static Queue<String> respuestasUser = new LinkedList<>();
    static int intentos = 0;
    static int pregunta = 1;
    @FXML
    private Label lblPregunta;
    @FXML
    private Button btnSi;
    @FXML
    private Button btnNo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Partida juegoActual = new Partida();

        juegoActual.setPathPreguntas(InicioJuegoController.rutaPregs);
        juegoActual.setPathRespuestas(InicioJuegoController.rutaResp);
        juegoActual.generarArbolJuego();
        arbolActual = juegoActual.getPreguntas();

        lblPregunta.setText(arbolActual.getRootContent());
    }

    @FXML
    private void botonYes(ActionEvent event) throws IOException {

        InformePartidaController.preguntasCola.offer(arbolActual.getRootContent());
        InformePartidaController.preguntasCola.offer("Si");

        boolean gameOver = false;

        respuestasUser.offer("si");

        System.out.println("PREGUNTA :" + pregunta);

        if (intentos == EmpezarPartidaController.intentosPermitidos) {
            arbolActual = arbolActual.getRight();
            //System.out.println("Se perdio partida");
            App.setRoot("pierdePartida");

        } else if (arbolActual.getRight() == null) {
            gameOver = true;
            arbolActual = arbolActual.getRight();
            App.setRoot("pierdePartida");

        } else if (arbolActual.getRight().isLeaf()) {
            gameOver = true;
            System.out.println("\n¡El animal que estás pensando es un " + arbolActual.getRight().getRootContent() + "!\n");
            System.out.println("//////////////////////// GAME OVER ////////////////////////");

            InformePartidaController.win = true;

            for (Animal a : Animal.animales) {
                if (arbolActual.getRight().getRootContent().equals(a.getNombre())) {
                    GanaPartidaController.animalEncontrado = a;
                }
            }

            App.setRoot("ganaPartida");
            System.out.println("segundo");
        } else {

            arbolActual = arbolActual.getRight();
            lblPregunta.setText(arbolActual.getRootContent());
            System.out.println("primero");
        }

        intentos++;
        System.out.println("Numero intento: " + intentos);
        System.out.println("Intentos Permitidos: " + EmpezarPartidaController.intentosPermitidos);
        pregunta++;

        if (intentos == EmpezarPartidaController.intentosPermitidos && gameOver == false) {
            //arbolActual=arbolActual.getRight();
            //System.out.println("Se perdio partida");
            App.setRoot("pierdePartida");
        }

    }

    @FXML
    private void botonNo(ActionEvent event) throws IOException {

        InformePartidaController.preguntasCola.offer(arbolActual.getRootContent());
        InformePartidaController.preguntasCola.offer("No");

        //App.setRoot("pierdePartida");
        boolean gameOver = false;

        respuestasUser.offer("no");

        System.out.println("PREGUNTA :" + pregunta);
        if (intentos == EmpezarPartidaController.intentosPermitidos) {
            arbolActual = arbolActual.getLeft();
            //System.out.println("Se perdio partida");
            App.setRoot("pierdePartida");

            System.out.println("SE PERDIO POR LIMITE DE INTENTOS");

        } else if (arbolActual.getLeft() == null) {
            gameOver = true;
            System.out.println("SE PERDIO POR ARBOL NULO");
            arbolActual = arbolActual.getLeft();
            App.setRoot("pierdePartida");
        } else if (arbolActual.getLeft().isLeaf()) {
            gameOver = true;
            System.out.println("\n¡El animal que estás pensando es un " + arbolActual.getLeft().getRootContent() + "!\n");
            System.out.println("//////////////////////// GAME OVER ////////////////////////");
            InformePartidaController.win = true;
            for (Animal a : Animal.animales) {
                if (arbolActual.getLeft().getRootContent().equals(a.getNombre())) {
                    GanaPartidaController.animalEncontrado = a;
                }
            }

            App.setRoot("ganaPartida");
            System.out.println("segundo");
        } else {

            arbolActual = arbolActual.getLeft();
            lblPregunta.setText(arbolActual.getRootContent());
            System.out.println("primero");
        }

        intentos++;
        System.out.println("Numero intento: " + intentos);
        System.out.println("Intentos Permitidos: " + EmpezarPartidaController.intentosPermitidos);

        pregunta++;

        if (intentos == EmpezarPartidaController.intentosPermitidos && gameOver == false) {

            App.setRoot("pierdePartida");

            System.out.println("SE PERDIO POR LIMITE DE INTENTOS SEGUNDA VALIDACION");

        }

    }

    private void listaPosiblesAnimales(BinaryTree<String> arbolActual) throws IOException {
        //MUESTRA UNA VENTANA CON LOS POSIBLES ANIMALES CUANDO SE ACABARON LOS INTENTOS
        System.out.println("INTENTOS ACABADOS");
        PosiblesAnimalesController.arbolAnimales = arbolActual;
        App.setRoot("posiblesAnimales");
    }

    private void pierdePartida() throws IOException {
        App.setRoot("pierdePartida");
        intentos = 0;
    }

}
