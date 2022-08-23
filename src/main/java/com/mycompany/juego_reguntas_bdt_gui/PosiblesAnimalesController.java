/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.juego_reguntas_bdt_gui;

import com.mycompany.TDAS.BinaryTree;
import com.mycompany.TDAS.CircularDoubleLinkedList;
import com.mycompany.modelo.Animal;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author diegomartinez
 */
public class PosiblesAnimalesController implements Initializable {
    
    static BinaryTree<String> arbolAnimales;
    
    CircularDoubleLinkedList<Animal> animalesPosibles= new  CircularDoubleLinkedList();

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
        
        LinkedList<String> lista=arbolAnimales.breadthTraversalLeaf();
        for(String s:lista){
            System.out.println(s);
        }
        
        //Animal.cargarAnimales();
        
        //CircularDoubleLinkedList<Animal> animalesPosibles= new  CircularDoubleLinkedList();
        for(String s:lista){
            for(Animal a:Animal.getAnimales()){
                if(s.equals(a.getNombre())){
                    animalesPosibles.addLast(a);
                }
            }
        }
        if(!animalesPosibles.isEmpty()){
            setearImageView(animalesPosibles.get(0).getPath());
            nombreAnimal.setText(animalesPosibles.get(0).getNombre());
        }
        //System.out.println(animalesPosibles.get(0).getNombre());
        //setearImageView(animalesPosibles.get(0).getPath());
        //nombreAnimal.setText(animalesPosibles.get(0).getNombre());
    }    
    
    @FXML
    private void previous(ActionEvent event) {
        ListIterator<Animal> it=animalesPosibles.listIterator();
        
        boolean esAnimal=false;
            while(esAnimal==false){
                Animal a=it.next();
                if(a.getNombre().equals(nombreAnimal.getText())){
                    esAnimal=true;
                }
            }
        Animal a=it.previous();
        //System.out.println(it.previous().getNombre());
        //System.out.println(it.previous().getNombre());
        setearImageView(a.getPath());
        nombreAnimal.setText(a.getNombre());
    }

    @FXML
    private void next(ActionEvent event) {
        ListIterator<Animal> it=animalesPosibles.listIterator();
        boolean esAnimal=false;
            while(esAnimal==false){
                Animal a=it.next();
                if(a.getNombre().equals(nombreAnimal.getText())){
                    esAnimal=true;
                }
            }
        Animal a=it.next();
        //System.out.println(it.next().getNombre());
        //System.out.println(it.next().getNombre());
        
        setearImageView(a.getPath());
        nombreAnimal.setText(a.getNombre());
        
    }
    
    private void setearImageView(String ruta){
        InputStream input = null;
        try {
             String fileName ="animales/"+ruta;//armar la ruta de la foto

            // System.out.println("FOTO RUTA"+fileName);


             //abrir el stream de la imagen de la persona
             input = App.class.getResource(fileName).openStream();

             //crear la imagen 
             Image image = new Image(input, 1000, 1000, false, false);
             imagenAnimal.setImage(image);

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
