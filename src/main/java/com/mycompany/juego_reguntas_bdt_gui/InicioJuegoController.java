
package com.mycompany.juego_reguntas_bdt_gui;

import com.mycompany.juego_reguntas_bdt_gui.App;
import com.mycompany.modelo.Animal;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;


public class InicioJuegoController implements Initializable {

    
    @FXML
    private Button btnJugar;
    @FXML
    private Button btnRespuestaFile;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    void elegirPreguntas(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar archivo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT", "*.txt"));

        File preguntasFile = fileChooser.showOpenDialog(null);
        //System.out.println("file"+preguntasFile.getPath());
        btnRespuestaFile.setDisable(false);
    }
    @FXML
    void elegirRespuestas(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar archivo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT", "*.txt"));

        File respuestasFile = fileChooser.showOpenDialog(null);
        //System.out.println("file"+respuestasFile.getPath());
        btnJugar.setDisable(false);
    }   

    
    @FXML
    private void empezarPartida(ActionEvent event) throws IOException {
        //CARGAMOS LA LISTA ESTATICA DE LOS ANIMALES
        Animal.cargarAnimales();
        App.setRoot("empezarPartida");
        
        
    }
    
}
