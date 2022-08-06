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
    public static void main(String Args[]){

        /*for (BinaryTree<String> binaryTree : listado) {
            System.out.println(binaryTree.getRootContent());
        }

        System.out.println(BinaryTree.contarPreguntas("documents/preguntas.txt"));

        BinaryTree<String> raiz = new BinaryTree<String>("preg1");
        BinaryTree<String> a1 = new BinaryTree<String>("preg2");
        BinaryTree<String> a1b = new BinaryTree<String>("preg2");
        BinaryTree<String> a2 = new BinaryTree<String>("preg3");
        BinaryTree<String> a2b = new BinaryTree<String>("preg3");
        BinaryTree<String> b1 = new BinaryTree<String>("res1");
        BinaryTree<String> b2 = new BinaryTree<String>("res2");
        BinaryTree<String> b3 = new BinaryTree<String>("res3");
        BinaryTree<String> b4 = new BinaryTree<String>("res4");


        a2.setLeft(b1);
        a2b.setLeft(b2);
        

        a1.setLeft(a2);
        a1.setRight(a2);
        a1b.setLeft(a2b);
        a1b.setRight(a2b);

        raiz.setLeft(a1);
        raiz.setRight(a1b);

        System.out.println(BinaryTree.height(raiz));
        System.out.println(raiz.isLeaf());
        */
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
        //Queue<String> answerOso = mapaRespuestas.get("Oso");
        
        //BinaryTree.chargeAnswers(BinaryTreeQuestion, new BinaryTree("Oso"), answerOso);
        
        LinkedList<String> breadthTraversal = BinaryTreeQuestion.breadthTraversal();
        for(String s : breadthTraversal){
            System.out.println(s);
        }
    }
}
