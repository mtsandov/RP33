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

public class BinaryTree<E> implements Cloneable {

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

    public void setLeft(BinaryTree<E> tree) {
        this.root.setLeft(tree);
    }

    public void setRight(BinaryTree<E> tree) {
        this.root.setRight(tree);
    }

    public BinaryTree getLeft() {
        return this.root.getLeft();
    }

    public BinaryTree getRight() {
        return this.root.getRight();
    }
    
    @Override
    public BinaryTree<E> clone() throws CloneNotSupportedException{
        
        return (BinaryTree<E>) super.clone();
    }
    
    //Metodo a la solucion de problema de hijos con la misma direccion de memoria a partir del segundo nivel
    public BinaryTree<E> cloneBinaryTree()
    {
        BinaryTreeNode<E> root = this.getRoot();
        // base case
        if (root == null) {
            return null;
        }
 
        // create a new node with the same data as the root node
        BinaryTree<E> root_tree_copy = new BinaryTree<>(root.getContent());
 
        // clone the left and right subtree
        if (root.getLeft() != null && root.getRight() != null){
            root_tree_copy.setLeft(root.getLeft().cloneBinaryTree());
            root_tree_copy.setRight(root.getRight().cloneBinaryTree());
        }
 
        // return cloned root node
        return root_tree_copy;
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
    
    
    public static BinaryTree<String> crearBinaryTreePreguntas(Stack<BinaryTree<String>> piloPreguntas) throws CloneNotSupportedException{
        
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
