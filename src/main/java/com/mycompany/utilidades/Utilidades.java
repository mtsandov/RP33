/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utilidades;

import com.mycompany.TDAS.BinaryTree;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

/**
 *
 * @author alex_
 */
public class Utilidades {
    
    public static Stack<BinaryTree<String>> construirPilaPreguntas(String ruta){

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String strCurrentLine;
            Stack<BinaryTree<String>> resultado = new Stack<>();
            while ((strCurrentLine = br.readLine()) != null){
                String pregunta = strCurrentLine;
                resultado.push(new BinaryTree<String>(pregunta));
            }
            br.close();
            return resultado;
        } catch (FileNotFoundException ex) {
            System.out.println("archivo no existe");
        } catch (IOException   ex) {
            System.out.println("error io:"+ex.getMessage());
        }

    return null;
    }
    
    
    public static BinaryTree<String> crearBinaryTreePreguntas(Stack<BinaryTree<String>> piloPreguntas){
        
        while(piloPreguntas.size() > 1){
            BinaryTree<String> treeUnder = piloPreguntas.pop();
            BinaryTree<String> treeUp = piloPreguntas.pop();
            
            BinaryTree<String> base = new BinaryTree<>(treeUp);
            BinaryTree<String> c1 = new BinaryTree<>(treeUnder);
            BinaryTree<String> c2 = new BinaryTree<>(treeUnder);
            

            
            base.setLeft(c1.cloneBinaryTree());
            base.setRight(c2.cloneBinaryTree());
            BinaryTree<String> temp = new BinaryTree<String>(base);
            piloPreguntas.push(temp.cloneBinaryTree());
        }
        return piloPreguntas.pop(); 
    }
    
    
    public static Map<String, Queue<String>> createMapSheets(String ruta){
        
        Map<String, Queue<String>> MapAnswers = new TreeMap();
        try(BufferedReader buff = new BufferedReader(new FileReader(ruta));){   
           String respuesta;
           while((respuesta = buff.readLine()) != null){
               
                String[] array = respuesta.split(" ");
                Queue<String> arrayAnswers = new LinkedList();
                for(int i=1 ; i < array.length ; i++)
                    arrayAnswers.add(array[i]);
                MapAnswers.put(array[0], arrayAnswers);
           }
        }   catch (Exception ex) {
            System.out.println(ex.getMessage());
            
        }
        return MapAnswers;
    }
    
    public static void chargeAnswers(BinaryTree<String> treeQuestion, BinaryTree<String> animal, Queue<String> answers){
        BinaryTree<String> raizActual = treeQuestion;
        
        while (!answers.isEmpty()){
            if(raizActual.getRight() == null || raizActual.getLeft() == null){
                if(answers.poll().equals("si")){
                    raizActual.setRight(animal);
                } else {
                    raizActual.setLeft(animal);
                }
            } 
            
            else {
                String resp = answers.poll();
                if (resp.equals("si")){
                    raizActual = raizActual.getRight();
                } else {
                    raizActual = raizActual.getLeft();
                }
            }
            
        }
    }

    public static int contarPreguntas(String ruta){
        int conteo = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null){
                conteo++;
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("archivo no existe");
        } catch (IOException   ex) {
            System.out.println("error io:"+ex.getMessage());
        }

        return conteo;
    }
}
