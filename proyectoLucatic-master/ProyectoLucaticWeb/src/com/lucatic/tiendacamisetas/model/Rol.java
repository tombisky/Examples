package com.lucatic.tiendacamisetas.model;

public class Rol {

	private int idRol;
	private String nombreRol;
	
	//CONSTRUCTORES***************************
	public Rol(){
		
	}
	
	public Rol(int idRol, String nombreRol){
		this.idRol=idRol;
		this.nombreRol=nombreRol;
	}
	
	public Rol(String nombreRol){
		this.nombreRol=nombreRol;
	}
	
	//METODOS SETTER*************************
	public void setIdRol(int idRol){
		this.idRol=idRol;
	}
	public void setNombreRol(String nombreRol){
		this.nombreRol=nombreRol;
	}
	
	//METODOS GETTER*************************
	public int getIdRol(){
		return idRol;
	}
	public String getNombreRol(){
		return nombreRol;
	}

	@Override
	public String toString() {
		return "RolID:   " + idRol + "\nNombre: " + nombreRol;
	}
	
	
	
}
