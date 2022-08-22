/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
    private Button btnIniciar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void comenzarPartida(ActionEvent event) throws IOException {
        //CARGAMOS LA LISTA ESTATICA DE LOS ANIMALES
        Animal.cargarAnimales();
        intentosPermitidos=Integer.parseInt(cantPreguntas.getText());
        App.setRoot("preguntas");
    }

}
