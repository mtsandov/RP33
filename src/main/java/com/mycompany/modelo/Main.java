/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modelo;

import java.util.LinkedList;
import com.mycompany.TDAS.*;
import java.util.Scanner;

/**
 *
 * @author alex_
 */
public class Main {
    public static void main(String Args[]) throws CloneNotSupportedException{

        BinaryTree<String> BinaryTreeQuestion = Partida.generarArbolJuego("documents/preguntasPrueba.txt", "documents/respuestasPrueba.txt");
        
        LinkedList<String> breadthTraversal = BinaryTreeQuestion.breadthTraversal();
        for(String s : breadthTraversal){
            System.out.println(s);
        }
        
        //IMPLEMENTACION DEL JUEGO
        System.out.println("//////////////////////// ADIVINA EL ANIMAL  ////////////////////////");
        System.out.println("\nA continuación se le mostrará una serie de preguntas a la cuales deberá responder con Si o No");

        
        BinaryTree<String> arbolActual= BinaryTreeQuestion;
        boolean gameOver= false;
        while(gameOver==false){
            
            System.out.println(arbolActual.getRootContent());
            
            Scanner sc = new Scanner(System.in); 
            System.out.println("Ingrese su respuesta: ");
            String resp = sc.nextLine().toLowerCase();

            if(resp.equals("si")){

                if(arbolActual.getRight()==null){
                    System.out.println("No se ha encontrado un animal de tales características");
                    System.out.println("//////////////////////// GAME OVER ////////////////////////");
                    gameOver=true;
                }else{
                    arbolActual=arbolActual.getRight();
                }
            }else{

                if(arbolActual.getLeft()==null){
                    System.out.println("No se ha encontrado un animal de tales características");
                    System.out.println("//////////////////////// GAME OVER ////////////////////////");
                    gameOver=true;
                }else{
                    arbolActual=arbolActual.getLeft();
                }
            }

            
            if(arbolActual.isLeaf()){
                System.out.println("¡El animal que estás pensando es un "+arbolActual.getRootContent()+"!");
                System.out.println("//////////////////////// GAME OVER ////////////////////////");
                gameOver=true;
            }

        }
       
    }
    
    
    
}
