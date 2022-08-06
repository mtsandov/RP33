package com.mycompany.TDAS;

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

}
