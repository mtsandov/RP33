/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.juego_reguntas_bdt_gui;

import com.mycompany.modelo.Partida;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author diegomartinez
 */
public class InformePartidaController implements Initializable {

    @FXML
    private TableView<String> tablaInforme;
    @FXML
    private TableColumn<String,String> preguntas;
    @FXML
    private TableColumn<String,String> respuestas;
    
    static Queue<String> respuestasCola=new LinkedList<>();
    static Queue<String> preguntasCola=new LinkedList<>();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*
        if(!imagen.getPersonas().isEmpty()){
            for(String p:imagen.getPersonas()){
                lviewPersonas.getItems().add(p);
            }
        }*/
        preguntas.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        //respuestas.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        while(preguntasCola.peek()!=null){
            tablaInforme.getItems().add(preguntasCola.poll());
            tablaInforme.getItems().add(respuestasCola.poll());
        }
        //respuestas.setCellValueFactory(new PropertyValueFactory<>("respuestasCola"));
        //preguntas.setCellValueFactory(new PropertyValueFactory<>("preguntasCola"));
        
        //tablaInforme.getItems().add(e)
        
    }    

    @FXML
    private void regresar(ActionEvent event) {
    }

    @FXML
    private void salir(ActionEvent event) throws IOException {
        App.setRoot("InicioJuego");
    }
    
}
