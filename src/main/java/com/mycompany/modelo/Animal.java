
package com.mycompany.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;


public class Animal {
    String nombre;
    String path;
    static LinkedList<Animal> animales=new LinkedList<>();

    public Animal(String nombre, String path) {
        this.nombre = nombre;
        this.path = path;
    }

    public static LinkedList<Animal> getAnimales() {
        return animales;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
    public static void cargarAnimales2(){
        Animal a1 = new Animal("Oso", "oso.jpg");
        Animal a2 =  new Animal("Lechuza", "lechuza.jpg");
        Animal a3 = new Animal("Venado", "venado.jpg");
        Animal a4 = new Animal("Paloma", "paloma.jpg");
        
        animales.add(a1);
        animales.add(a2);
        animales.add(a3);
        animales.add(a4);
    }
    
    public static void cargarAnimales(){
        try {
            
            FileReader entrada= new FileReader("documents/listaAnimales.txt");
            BufferedReader buffer= new BufferedReader(entrada);
            String linea="";
            
            while(linea!=null){
                linea=buffer.readLine();
                if(linea!=null){
                    String[] datos = linea.split(",");
                    String nombre= datos[0];
                    String ruta=datos[1];
                    Animal a = new Animal(nombre,ruta);
                    animales.add(a);
                
                    
                } 
            }
            
            entrada.close();
            buffer.close();
        } catch (IOException ex) {
            System.out.println("No se ha encontrado el archivo de animales: "+ex);
        }
    }
    
    
    
    
}
