/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modelo;

import com.mycompany.TDAS.BinaryTree;


/**
 *
 * @author alex_
 */
public class Main {
    public static void main(String Args[]) throws CloneNotSupportedException{

        Partida juegoActual = new Partida();

        juegoActual.setPathPreguntas("documents/preguntasPrueba.txt");
        juegoActual.setPathRespuestas("documents/respuestasPrueba.txt");

        juegoActual.generarArbolJuego();
                
        juegoActual.empezarJuego();
        
       
    }
    
    
    
}
