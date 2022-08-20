/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.juego_reguntas_bdt_gui;

import com.mycompany.TDAS.CircularDoubleLinkedList;
import com.mycompany.juego_reguntas_bdt_gui.App;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
/**
 * FXML Controller class
 *
 * @author diegomartinez
 */
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
        intentosPermitidos=Integer.parseInt(cantPreguntas.getText());
        CircularDoubleLinkedList<String> lista = new CircularDoubleLinkedList();
        //LinkedList<String> lista= new LinkedList();
        lista.addFirst("quinto");
        lista.addFirst("cuarto");
        lista.addFirst("tercero");
        lista.addFirst("segundo");
        lista.addFirst("primero");
        
         //lista.addLast("primero");
         //lista.addLast("segundo");
         //lista.addLast("tercero");
         //lista.addLast("cuarto");
         //lista.addLast("quinto");
        ListIterator it=lista.listIterator();
        
        System.out.println(it.previous());
        System.out.println("///////////////////////////");
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println("///////////////////////////");
        System.out.println(it.previous());
        System.out.println(it.previous());
        //System.out.println(it.previous());
        //System.out.println(it.previous());
        App.setRoot("preguntas");
        
        
    }
    
    
    
}
