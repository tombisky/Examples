package com.lucatic.tiendacamisetas.model;


public class Talla {

    private int idtalla;
    private String nombre;
    
    //CONSTRUCTORES
    public Talla() {
    }
    
    public Talla(String nombre) {
        
        this.nombre = nombre;      
    }

    public Talla(int idtalla, String nombre) {
        this.idtalla = idtalla;
        this.nombre = nombre;        
    }
    
    
    //MÉTODOS GETTER
    public int getIdTalla() {
        return idtalla;
    }

    public String getNombre() {
        return nombre;
    }
 
    @Override
    public String toString() {
        return "Talla ID:   " + getIdTalla() + "\nNombre: " + getNombre();
    }
}