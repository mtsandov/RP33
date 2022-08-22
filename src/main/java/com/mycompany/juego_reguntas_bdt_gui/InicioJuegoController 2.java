
package com.mycompany.juego_reguntas_bdt_gui;

import com.mycompany.juego_reguntas_bdt_gui.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class InicioJuegoController implements Initializable {
    
    //static int intentosPermitidos;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 
    }    

    @FXML
    private void empezarPartida(ActionEvent event) throws IOException {
        
        App.setRoot("empezarPartida");
        //int n=EmpezarPartidaController.intentosPermitidos;
        
    }
    
}
