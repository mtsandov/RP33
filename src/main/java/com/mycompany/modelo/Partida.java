package com.mycompany.modelo;

import com.mycompany.TDAS.BinaryTree;
import com.mycompany.utilidades.Utilidades;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
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
    
    public static BinaryTree<String> generarArbolJuego(String rutaPregs, String rutaResp) throws CloneNotSupportedException{
        Stack<BinaryTree<String>> pilaPregs = Utilidades.construirPilaPreguntas(rutaPregs);
        
        BinaryTree<String> BinaryTreeQuestion = Utilidades.crearBinaryTreePreguntas(pilaPregs);
        Map<String, Queue<String>> mapaRespuestas = Utilidades.createMapSheets(rutaResp);
        
        for(String animal : mapaRespuestas.keySet()){
            System.out.println(animal + " : " + mapaRespuestas.get(animal));
        }
        
        for (String clave : mapaRespuestas.keySet()){
            Queue<String> answerTemp = mapaRespuestas.get(clave);
        
            Utilidades.chargeAnswers(BinaryTreeQuestion, new BinaryTree(clave), answerTemp);
        }
        
        return BinaryTreeQuestion;
    }
    
    




}
