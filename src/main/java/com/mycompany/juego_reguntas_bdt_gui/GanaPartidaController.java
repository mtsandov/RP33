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

public class GanaPartidaController implements Initializable {

    static Animal animalEncontrado;

    @FXML
    private ImageView imgAnimal;
    @FXML
    private Label animalNombre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        animalNombre.setText(animalEncontrado.getNombre());
        setearImageView("animales/" + animalEncontrado.getPath());
    }

    @FXML
    private void mostrarInforme(ActionEvent event) throws IOException {
        App.setRoot("informePartida");
    }

    @FXML
    private void jugarDeNuevo(ActionEvent event) throws IOException {
        App.setRoot("empezarPartida");
        App.resetearIntentosUsuario();
        App.resetearInformePartida();
    }

    @FXML
    private void salir(ActionEvent event) throws IOException {
        App.setRoot("InicioJuego");
        App.resetearIntentosUsuario();
        App.resetearInformePartida();
    }

    private void setearImageView(String ruta) {
        InputStream input = null;
        try {
            String fileName = ruta;

            System.out.println("FOTO RUTA" + fileName);

            input = App.class.getResource(fileName).openStream();

            Image image = new Image(input, 1000, 1000, false, false);
            imgAnimal.setImage(image);

        } catch (Exception ex) {
            System.out.println("No se encuentra archivo de imagen:  " + ex);
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
