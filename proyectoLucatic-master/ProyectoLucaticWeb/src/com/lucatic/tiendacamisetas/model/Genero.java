package com.lucatic.tiendacamisetas.model;

public class Genero {

	private int idGenero;
	private String nombreGenero;
	
	
	//CONSTRUCTORES************************
	public Genero(){
		
	}
	
	public Genero(int idGenero, String nombreGenero){
		this.idGenero=idGenero;
		this.nombreGenero=nombreGenero;
		
	}
	
	public Genero(String nombreGenero){
		this.nombreGenero=nombreGenero;
	}
	
	//MÉTODOS SETTER***********************
	public void setIdGenero(int idGenero){
		this.idGenero=idGenero;
	}
	
	public void setIdNombre(String nombreGenero){
		this.nombreGenero=nombreGenero;
	}
	
	//MÉTODOS GETTER************************
	public int getIdGenero(){
		return idGenero;
	}
	
	public String getNombreGenero(){
		return nombreGenero;
	}

	@Override
	public String toString() {
		return "GéneroID:   " + idGenero + "\nNombre: " + nombreGenero;
	}
	
	
	
	
}
