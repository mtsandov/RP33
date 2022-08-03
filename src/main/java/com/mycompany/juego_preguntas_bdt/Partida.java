package com.mycompany.juego_preguntas_bdt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Partida {

    BinaryTree<String> preguntas;
    
    public BinaryTree<String> generarArbol(){
        LinkedList<BinaryTree<String>> preguntas = BinaryTree.construirNodosPreguntas("documents/preguntas.txt");

        Queue<BinaryTree<String>> cola = new LinkedList<>();
        BinaryTree<String> raiz;
        raiz = (preguntas.get(0));
        Stack<BinaryTree<String>> pila = new Stack<>();
        BinaryTree<String> preg = cola.poll(); 
        raiz.setLeft(preg);
        raiz.setRight(preg);
        pila.push(raiz);
        pila.push(raiz.getLeft());
        pila.push(raiz.getRight());

        //while (!cola.isEmpty()) {
        //    BinaryTree<String> pregunta = cola.poll();
        //    while (pila.size() > 1){

        //    }
        //}

    return null;
    }

    public static void main(String[] args) {
        Partida p1 = new Partida();
        p1.generarArbol();
    }

}
