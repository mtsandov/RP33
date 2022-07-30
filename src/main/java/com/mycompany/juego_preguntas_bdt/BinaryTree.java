package com.mycompany.juego_preguntas_bdt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<E> {

    private BinaryTreeNode<E> root;

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

    public static LinkedList<BinaryTree<String>> construirNodosPreguntas(String ruta){
        LinkedList<BinaryTree<String>> resultado = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null){
                String pregunta = strCurrentLine;
                BinaryTree<String> arbolNuevo = new BinaryTree<String>(pregunta);
                resultado.add(arbolNuevo);
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println("archivo no existe");
        } catch (IOException   ex) {
            System.out.println("error io:"+ex.getMessage());
        }

        return resultado;
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
