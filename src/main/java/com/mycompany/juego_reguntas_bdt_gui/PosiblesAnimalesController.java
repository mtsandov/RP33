/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.juego_reguntas_bdt_gui;

import com.mycompany.TDAS.BinaryTree;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author diegomartinez
 */
public class PosiblesAnimalesController implements Initializable {
    
    static BinaryTree<String> arbolAnimales;


    @FXML
    private ImageView imagenAnimal;
    @FXML
    private Button btnAtras;
    @FXML
    private Button btnNext;
    @FXML
    private Label nombreAnimal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void previous(ActionEvent event) {
    }

    @FXML
    private void next(ActionEvent event) {
    }

}
