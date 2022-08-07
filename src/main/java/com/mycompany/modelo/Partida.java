package com.mycompany.modelo;

import com.mycompany.TDAS.BinaryTree;
import com.mycompany.utilidades.Utilidades;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Partida {

    BinaryTree<String> preguntas;

    public Partida(BinaryTree<String> preguntas) {
        this.preguntas = preguntas;
    }
    

    public BinaryTree<String> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(BinaryTree<String> preguntas) {
        this.preguntas = preguntas;
    }
    
    public static BinaryTree<String> generarArbolJuego(String rutaPregs, String rutaResp){
        Stack<BinaryTree<String>> pilaPregs = Utilidades.construirPilaPreguntas(rutaPregs);
        
        BinaryTree<String> BinaryTreeQuestion = Utilidades.crearBinaryTreePreguntas(pilaPregs);
        Map<String, Queue<String>> mapaRespuestas = Utilidades.createMapSheets(rutaResp);
        /*
        for(String animal : mapaRespuestas.keySet()){
            System.out.println(animal + " : " + mapaRespuestas.get(animal));
        }*/
        
        for (String clave : mapaRespuestas.keySet()){
            Queue<String> answerTemp = mapaRespuestas.get(clave);
        
            Utilidades.chargeAnswers(BinaryTreeQuestion, new BinaryTree(clave), answerTemp);
        }
        
        return BinaryTreeQuestion;
    }
    
    public static void empezarJuego(BinaryTree<String> BinaryTreeQuestion){
        //IMPLEMENTACION DEL JUEGO
        System.out.println("//////////////////////// ADIVINA EL ANIMAL  ////////////////////////");
        System.out.println("\nA continuación se le mostrará una serie de preguntas a la cuales deberá responder con Si o No\n");

        
        BinaryTree<String> arbolActual= BinaryTreeQuestion;
        boolean gameOver= false;
        while(gameOver==false){
            
            System.out.println(arbolActual.getRootContent());
            
            Scanner sc = new Scanner(System.in); 
            System.out.println("Ingrese su respuesta: ");
            String resp = sc.nextLine().toLowerCase();

            if(resp.equals("si")){

                if(arbolActual.getRight()==null){
                    System.out.println("\nNo se ha encontrado un animal de tales características\n");
                    System.out.println("//////////////////////// GAME OVER ////////////////////////");
                    gameOver=true;
                }else{
                    arbolActual=arbolActual.getRight();
                }
            }else{

                if(arbolActual.getLeft()==null){
                    System.out.println("\nNo se ha encontrado un animal de tales características\n");
                    System.out.println("//////////////////////// GAME OVER ////////////////////////");
                    gameOver=true;
                }else{
                    arbolActual=arbolActual.getLeft();
                }
            }

            
            if(arbolActual.isLeaf()){
                System.out.println("\n¡El animal que estás pensando es un "+arbolActual.getRootContent()+"!\n");
                System.out.println("//////////////////////// GAME OVER ////////////////////////");
                gameOver=true;
            }

        }
        
    }
    
    




}
