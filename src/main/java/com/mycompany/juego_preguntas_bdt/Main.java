/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.juego_preguntas_bdt;

import java.util.LinkedList;
import com.mycompany.juego_preguntas_bdt.BinaryTree;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author alex_
 */
public class Main {
    public static void main(String Args[]) throws CloneNotSupportedException{

        Stack<BinaryTree<String>> pilaPregs = BinaryTree.construirPilaPreguntas("documents/preguntasPrueba.txt");
        
        BinaryTree<String> BinaryTreeQuestion = BinaryTree.crearBinaryTreePreguntas(pilaPregs);
        Map<String, Queue<String>> mapaRespuestas = BinaryTree.createMapSheets("documents/respuestasPrueba.txt");
        
        for(String animal : mapaRespuestas.keySet()){
            System.out.println(animal + " : " + mapaRespuestas.get(animal));
        }
        
        for (String clave : mapaRespuestas.keySet()){
            Queue<String> answerTemp = mapaRespuestas.get(clave);
        
            BinaryTree.chargeAnswers(BinaryTreeQuestion, new BinaryTree(clave), answerTemp);
        }
        
        LinkedList<String> breadthTraversal = BinaryTreeQuestion.breadthTraversal();
        for(String s : breadthTraversal){
            System.out.println(s);
        }
    }
}
