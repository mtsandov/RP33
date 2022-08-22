
package com.mycompany.juego_reguntas_bdt_gui;

import com.mycompany.TDAS.BinaryTree;
import com.mycompany.juego_reguntas_bdt_gui.EmpezarPartidaController;
import com.mycompany.modelo.Animal;
import com.mycompany.modelo.Partida;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    static int intentos=1;

    @FXML
    private Label lblPregunta;
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
                
        lblPregunta.setText(arbolActual.getRootContent());
    }    
    
    @FXML
    private void botonYes(ActionEvent event) throws IOException {
        
        if(arbolActual.getRight()==null){
            System.out.println("\nNo se ha encontrado un animal de tales características\n");
            System.out.println("//////////////////////// GAME OVER ////////////////////////");
            System.out.println("Porfavor escriba el animal en que estaba pensando: ");
            App.setRoot("pierdePartida");
                    
        }else{
            //Partida.respuestasCola.add("Si");
            //Partida.preguntasCola.add(arbolActual.getRootContent());
            InformePartidaController.preguntasCola.offer(arbolActual.getRootContent());
            InformePartidaController.respuestasCola.offer("Si");
            arbolActual=arbolActual.getRight();
            lblPregunta.setText(arbolActual.getRootContent());
            
            
        }
        
        if(arbolActual.isLeaf()){
                System.out.println("\n¡El animal que estás pensando es un "+arbolActual.getRootContent()+"!\n");
                System.out.println("//////////////////////// GAME OVER ////////////////////////");
                //SALTA A OTRA PANTALLA MOSTRANDO EL ANIMAL ADIVINADO
                //animalAdivinado.fxml
                //gameOver=true;
                
                //INTERFAZ
                Animal.cargarAnimales();
                for(Animal a:Animal.getAnimales()){
                    if(arbolActual.getRootContent().equals(a.getNombre())){
                        GanaPartidaController.animalEncontrado=a;
                    }
                }
                
                App.setRoot("ganaPartida");
            }
        
        intentos++;
        
        if(intentos>EmpezarPartidaController.intentosPermitidos ){

            listaPosiblesAnimales(arbolActual);

        }
    }

    @FXML
    private void botonNo(ActionEvent event) throws IOException {
        if(arbolActual.getLeft()==null){
            System.out.println("\nNo se ha encontrado un animal de tales características\n");
            System.out.println("//////////////////////// GAME OVER ////////////////////////");
            System.out.println("Porfavor escriba el animal en que estaba pensando: ");
            //INTERFAZ
            App.setRoot("pierdePartida");
                    
        }else{
            //Partida.respuestasCola.add("No");
            //Partida.preguntasCola.add(arbolActual.getRootContent());
            InformePartidaController.preguntasCola.offer(arbolActual.getRootContent());
            InformePartidaController.respuestasCola.offer("No");
            arbolActual=arbolActual.getLeft();
            lblPregunta.setText(arbolActual.getRootContent());
            
        }
        if(arbolActual.isLeaf()){
                System.out.println("\n¡El animal que estás pensando es un "+arbolActual.getRootContent()+"!\n");
                System.out.println("//////////////////////// GAME OVER ////////////////////////");
                //SALTA A OTRA PANTALLA MOSTRANDO EL ANIMAL ADIVINADO
                //animalAdivinado.fxml
                //gameOver=true;
                //INTERFAZ
                
                Animal.cargarAnimales();
                for(Animal a:Animal.getAnimales()){
                    if(arbolActual.getRootContent().equals(a.getNombre())){
                        GanaPartidaController.animalEncontrado=a;
                    }
                }
                
                App.setRoot("ganaPartida");
                
                
            }
        
        intentos++;
        
        if(intentos>EmpezarPartidaController.intentosPermitidos){

            listaPosiblesAnimales(arbolActual);

        }
    }
    
    private void listaPosiblesAnimales(BinaryTree<String> arbolActual) throws IOException {
        //MUESTRA UNA VENTANA CON LOS POSIBLES ANIMALES CUANDO SE ACABARON LOS INTENTOS
        //posiblesAnimales.fxml
        System.out.println("INTENTOS ACABADOS");
        PosiblesAnimalesController.arbolAnimales=arbolActual;
        App.setRoot("posiblesAnimales");
    }

}
