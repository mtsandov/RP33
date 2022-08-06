package com.mycompany.juego_preguntas_bdt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class BinaryTree<E> {

    private BinaryTreeNode<E> root;
    
    public BinaryTree(BinaryTree<E> arbol){
        BinaryTreeNode<E> temp = new BinaryTreeNode<E>(arbol,true);
        this.root = temp;
    }

    public BinaryTree(E rootContent) {
        this.root = new BinaryTreeNode<>(rootContent);
    }

    public E getRootContent() {
        return this.root.getContent();
    }

    public BinaryTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean isLeaf(){
        return (this.getRoot().getLeft() == null && this.getRoot().getRight() == null);
    }

    public void setRootContent(E content) {
        this.root = new BinaryTreeNode<>(content);
    }

    private BinaryTreeNode<E> getRoot() {
        return root;
    }

    private void setRoot(BinaryTreeNode<E> root) {
        this.root = root;
    }

    public void setLeft(BinaryTree tree) {
        this.root.setLeft(tree);
    }

    public void setRight(BinaryTree tree) {
        this.root.setRight(tree);
    }

    public BinaryTree getLeft() {
        return this.root.getLeft();
    }

    public BinaryTree getRight() {
        return this.root.getRight();
    }

    public static int height(BinaryTree root)
    {
        // caso base: el árbol vacío tiene una altura de 0
        if (root == null) {
            return 0;
        }
 
        // recurre para el subárbol izquierdo y derecho y considera la profundidad máxima
        return 1 + Math.max(height(root.getLeft()), height(root.getRight()));
    }

    public LinkedList<E> preOrderTraversalRecursive() {
        LinkedList<E> traversal = new LinkedList<>();
        if (!this.isEmpty()) {
            traversal.add(this.getRootContent());
        }
        if (this.getLeft() != null) {
            traversal.addAll(this.getLeft().preOrderTraversalRecursive());
        }
        if (this.getRight() != null) {
            traversal.addAll(this.getRight().preOrderTraversalRecursive());
        }
        return traversal;
    }

    public LinkedList<E> preOrderTraversalIterative() {
        LinkedList<E> traversal = new LinkedList<>();
        Stack<BinaryTree<E>> s = new Stack<>();
        s.push(this);
        while (!s.isEmpty()) {
            BinaryTree<E> tree = s.pop();
            if (!tree.isEmpty()) {
                traversal.add(tree.getRootContent());
            }
            if (tree.getRight()!= null && !tree.getRight().isEmpty()) {
                s.push(tree.getRight());
            }
            if (tree.getLeft() != null && !tree.getLeft().isEmpty()) {
                s.push(tree.getLeft());
            }
        }
        return traversal;
    }
    
    
    public LinkedList<E> breadthTraversal() {
        LinkedList<E> traversal = new LinkedList<>();
        Queue<BinaryTree<E>> q = new LinkedList<>();
        q.offer(this);
        while (!q.isEmpty()) {
            BinaryTree<E> tree = q.poll();
            if (!tree.isEmpty()) {
                traversal.add(tree.getRootContent());
            }
            if (tree.getLeft() != null && !tree.getLeft().isEmpty()) {
                q.offer(tree.getLeft());
            }
            if (tree.getRight()!= null && !tree.getRight().isEmpty()) {
                q.offer(tree.getRight());
            }
        }
        return traversal;
    }

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
            
            BinaryTree<String> c1 = new BinaryTree<>(treeUnder);
            BinaryTree<String> c2 = new BinaryTree<>(treeUnder);

            
            treeUp.setLeft(c1);
            treeUp.setRight(c2);
            piloPreguntas.push(treeUp);
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
        /*if(treeQuestion.getRight() == null || treeQuestion.getLeft() == null){
            if(answers.poll().equals("si"))
                treeQuestion.setRight(animal);
            else
                treeQuestion.setLeft(animal);
            return;
        }
        else{
            String answer = answers.poll();
            if(answer.equals("si"))
                chargeAnswers(treeQuestion.getRight(), animal, answers);
            else
                chargeAnswers(treeQuestion.getLeft(), animal, answers);
        }*/
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
