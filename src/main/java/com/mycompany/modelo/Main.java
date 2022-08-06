/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modelo;

import java.util.LinkedList;
import com.mycompany.TDAS.*;

/**
 *
 * @author alex_
 */
public class Main {
    public static void main(String Args[]) throws CloneNotSupportedException{

        BinaryTree<String> BinaryTreeQuestion = Partida.generarArbolJuego("documents/preguntas.txt", "documents/respuestas.txt");
        
        LinkedList<String> breadthTraversal = BinaryTreeQuestion.breadthTraversal();
        for(String s : breadthTraversal){
            System.out.println(s);
        }
    }
}
