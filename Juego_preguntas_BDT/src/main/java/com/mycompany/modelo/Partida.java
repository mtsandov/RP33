package com.mycompany.modelo;

import com.mycompany.TDAS.BinaryTree;
import com.mycompany.utilidades.Utilidades;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Partida {

    BinaryTree<String> preguntas;
    Queue<String> respuestasUser = new LinkedList<>();
    String animalUser;
    String pathPreguntas;
    String pathRespuestas;

    public Partida(){

    }

    public Partida(BinaryTree<String> preguntas) {
        this.preguntas = preguntas;
    }
    

    public BinaryTree<String> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(BinaryTree<String> preguntas) {
        this.preguntas = preguntas;
    }
    
    public Queue<String> getRespuestasUser() {
        return respuestasUser;
    }

    public String getPathPreguntas() {
        return pathPreguntas;
    }

    public void setPathPreguntas(String pathPreguntas) {
        this.pathPreguntas = pathPreguntas;
    }

    public String getPathRespuestas() {
        return pathRespuestas;
    }

    public void setPathRespuestas(String pathRespuestas) {
        this.pathRespuestas = pathRespuestas;
    }

    public String getAnimalUser() {
        return animalUser;
    }

    public void setAnimalUser(String animalUser) {
        this.animalUser = animalUser;
    }

    public void generarArbolJuego(){
        String rutaPregs = this.pathPreguntas;
        String rutaResp = this.pathRespuestas;
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
        
        this.preguntas = BinaryTreeQuestion;
    }

    public void addAnimalToTxt(String animal, Queue<String> respuestas){
        String linea;
        linea = animal + " ";
        while (!respuestas.isEmpty()){
            linea += respuestas.poll() + " ";
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.pathRespuestas,true))) {
            bw.write(linea);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

    }
    
    public void empezarJuego(){
        //IMPLEMENTACION DEL JUEGO
        System.out.println("//////////////////////// ADIVINA EL ANIMAL  ////////////////////////");
        System.out.println("\nA continuación se le mostrará una serie de preguntas a la cuales deberá responder con Si o No\n");

        
        BinaryTree<String> arbolActual = this.preguntas;
        boolean gameOver= false;
        Scanner sc = new Scanner(System.in); 

        while(gameOver==false){
            
            System.out.println(arbolActual.getRootContent());
            
            System.out.println("Ingrese su respuesta: ");
            String resp = sc.nextLine().toLowerCase();
            respuestasUser.offer(resp);

            if(resp.equals("si")){

                if(arbolActual.getRight()==null){
                    System.out.println("\nNo se ha encontrado un animal de tales características\n");
                    System.out.println("//////////////////////// GAME OVER ////////////////////////");
                    System.out.println("Porfavor escriba el animal en que estaba pensando: ");
                    this.setAnimalUser(sc.nextLine());
                    animalUser = animalUser.replaceFirst(String.valueOf(animalUser.charAt(0)), String.valueOf(animalUser.charAt(0)).toUpperCase());
                    System.out.println(this.getAnimalUser());
                    this.addAnimalToTxt(animalUser, this.respuestasUser);
                    gameOver=true;
                }else{
                    arbolActual=arbolActual.getRight();
                }
            }else{

                if(arbolActual.getLeft()==null){
                    System.out.println("\nNo se ha encontrado un animal de tales características\n");
                    System.out.println("//////////////////////// GAME OVER ////////////////////////");
                    System.out.println("Porfavor escriba el animal en que estaba pensando: ");
                    this.setAnimalUser(sc.nextLine());
                    animalUser = animalUser.replaceFirst(String.valueOf(animalUser.charAt(0)), String.valueOf(animalUser.charAt(0)).toUpperCase());
                    System.out.println(this.getAnimalUser());
                    this.addAnimalToTxt(animalUser, this.respuestasUser);
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
        sc.close();
    }
    
    




}
