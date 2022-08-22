/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.juego_reguntas_bdt_gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author diegomartinez
 */
public class PierdePartidaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void agregarAnimal(ActionEvent event) throws IOException {
        App.setRoot("nuevoAnimal");
    }

    @FXML
    private void mostrarInforme(ActionEvent event) throws IOException {
        App.setRoot("informePartida");
    }

    @FXML
    private void jugarDeNuevo(ActionEvent event) throws IOException {
        App.setRoot("empezarPartida");
    }

    @FXML
    private void salir(ActionEvent event) throws IOException {
        App.setRoot("InicioJuego");
    }

}
