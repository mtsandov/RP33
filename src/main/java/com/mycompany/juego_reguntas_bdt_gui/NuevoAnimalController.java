/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.juego_reguntas_bdt_gui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author diegomartinez
 */
public class NuevoAnimalController implements Initializable {

    @FXML
    private TextField nombreAnimal;
    @FXML
    private ImageView imagenAnimal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void agregarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar archivo");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(null);

        // Mostar la imagen
        if (imgFile != null) {
            try{
                Image image = new Image("file:" + imgFile.getAbsolutePath());
                System.out.println(imgFile.getAbsolutePath());
                imagenAnimal.setImage(image);
                //copiar la imagen
                
                Path from = Paths.get(imgFile.toURI());
                Path to = Paths.get("src/main/resources/com/mycompany/juego_reguntas_bdt_gui/animales/"+imgFile.getName());
                Files.copy(from, to);
                System.out.println("INFORMACION FOTO");
                System.out.println(from);
                System.out.println(to);
                
            }catch(FileNotFoundException fe){
                System.out.println("FILE NOT FOUND Exception:" + fe.getMessage()); 
                
            }catch(FileAlreadyExistsException fe){
                System.out.println("FILE AlreadyExistsException:" + fe.getMessage()); 
                //App.mostrarAlerta(Alert.AlertType.ERROR, "Foto repetida");
                
            }catch(Exception e){
                System.out.println("Exception:" + e); 
            }            
        }
    }

    @FXML
    private void guardarAnimal(ActionEvent event) {
        String nomAnimal=nombreAnimal.getText();
        nomAnimal = nomAnimal.replaceFirst(String.valueOf(nomAnimal.charAt(0)), String.valueOf(nomAnimal.charAt(0)).toUpperCase());
        //PROCESO PARA OBTENER RUTA FOTO
        Image im= imagenAnimal.getImage();
        String url= im.getUrl();    
        File file = new File(url);
        String foto = file.getName();
        String ruta=foto;
        System.out.println(ruta);
        
        System.out.println("INFORMACION ANIMAL");
        System.out.println(nomAnimal);
        System.out.println(ruta);
        
        try {
                FileWriter entrada= new FileWriter("documents/listaAnimales.txt",true);
                BufferedWriter bw= new BufferedWriter(entrada);
                bw.write(nomAnimal+","+ruta+"\n");
                //Mascota m=new Mascota(codigo,nombre,tipoMascota,raza,date,foto,dueno);                
                bw.close();
                //App.showAlert(Alert.AlertType.INFORMATION, "Nueva mascota agregada exitosamente");
                //detalleMascota(m);
                
                BufferedWriter bw2 = new BufferedWriter(new FileWriter(InicioJuegoController.rutaResp, true));
                
                String dato = nomAnimal + " ";
                
                Queue<String> temp = PreguntasController.respuestasUser;
                
                System.out.println(temp.size());
                
                while (!temp.isEmpty()){
                    dato += temp.poll() + " ";
                }
                
                bw2.write(dato);
                
                System.out.println(dato);
                
                bw2.close();
         
            } catch (IOException ex) {
                System.out.println("IOException:" + ex.getMessage());
            } 
    }
    
}
