package com.mycompany.juego_reguntas_bdt_gui;

import com.mycompany.juego_reguntas_bdt_gui.App;
import com.mycompany.modelo.Animal;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class EmpezarPartidaController implements Initializable {

    static int intentosPermitidos;
    @FXML
    private Pane principalPane;
    @FXML
    private TextField cantPreguntas;

    @FXML

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InformePartidaController.win = false;
        PreguntasController.respuestasUser.clear();
        // TODO
    }

    @FXML
    private void comenzarPartida(ActionEvent event) throws IOException {

        //ESTABLECEMOS LOS INTENTOS PERMITIDOS PARA ESTA PARTIDA
        intentosPermitidos = Integer.parseInt(cantPreguntas.getText());
        App.setRoot("preguntas");
    }

}
