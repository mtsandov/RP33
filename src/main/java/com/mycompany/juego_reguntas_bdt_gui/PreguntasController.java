/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.juego_reguntas_bdt_gui;

import com.mycompany.TDAS.BinaryTree;
import com.mycompany.modelo.Partida;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
/**
 * FXML Controller class
 *
 * @author diegomartinez
 */
public class PreguntasController implements Initializable {
    
    static BinaryTree<String> arbolActual;


    @FXML
    private Label preguntas;
    @FXML
    private Button btnSi;
    @FXML
    private Button btnNo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Partida juegoActual = new Partida();

        juegoActual.setPathPreguntas("documents/preguntasPrueba.txt");
        juegoActual.setPathRespuestas("documents/respuestasPrueba.txt");

        juegoActual.generarArbolJuego();
        arbolActual=juegoActual.getPreguntas();
                
        preguntas.setText(juegoActual.getPreguntas().getRootContent());
        
        
    }    

    @FXML
    private void botonYes(ActionEvent event) {
        if(arbolActual.getRight()==null){
            System.out.println("\nNo se ha encontrado un animal de tales características\n");
            System.out.println("//////////////////////// GAME OVER ////////////////////////");
            System.out.println("Porfavor escriba el animal en que estaba pensando: ");
                    
        }else{
            
            arbolActual=arbolActual.getRight();
            preguntas.setText(arbolActual.getRootContent());
        }
    }

    @FXML
    private void botonNo(ActionEvent event) {
        if(arbolActual.getRight()==null){
            System.out.println("\nNo se ha encontrado un animal de tales características\n");
            System.out.println("//////////////////////// GAME OVER ////////////////////////");
            System.out.println("Porfavor escriba el animal en que estaba pensando: ");
                    
        }else{
            
            arbolActual=arbolActual.getLeft();
            preguntas.setText(arbolActual.getRootContent());
        }
    }
    
    
    
    
    
}
