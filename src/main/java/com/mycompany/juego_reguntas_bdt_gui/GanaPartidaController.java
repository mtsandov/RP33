/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.juego_reguntas_bdt_gui;

import com.mycompany.modelo.Animal;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author diegomartinez
 */
public class GanaPartidaController implements Initializable {
    
    static Animal animalEncontrado;


    @FXML
    private ImageView imgAnimal;
    @FXML
    private Label animalNombre;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        animalNombre.setText(animalEncontrado.getNombre());
        setearImageView("animales/"+animalEncontrado.getPath());
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
    
    private void setearImageView(String ruta){
        InputStream input = null;
        try {
             String fileName =ruta;//armar la ruta de la foto

             System.out.println("FOTO RUTA"+fileName);


             //abrir el stream de la imagen de la persona
             input = App.class.getResource(fileName).openStream();

             //crear la imagen 
             Image image = new Image(input, 1000, 1000, false, false);
             imgAnimal.setImage(image);

            } catch (Exception ex) {
                 System.out.println("No se encuentra archivo de imagen:  "+ex);
                } finally {
                if (input != null) {
                    try {
                   input.close();
                } catch (IOException ex) {
                System.out.println("No se pudo cerrar");
                }
            }
        }
    }
}
