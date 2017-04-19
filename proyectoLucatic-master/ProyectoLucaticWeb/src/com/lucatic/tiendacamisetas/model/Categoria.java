package com.lucatic.tiendacamisetas.model;

public class Categoria {

	private int idCategoria;
	private String nombreCategoria;
	
	//CONSTRUCTORES*********************************
	public Categoria(){
		
	}
	public Categoria(int idCategoria, String nombreCategoria){
		this.idCategoria=idCategoria;
		this.nombreCategoria=nombreCategoria;
	}
	public Categoria(String nombreCategoria){
		this.nombreCategoria=nombreCategoria;
	}
	
	//METODOS SETTER********************************
	
	public void setCategoria(int idCategoria){
		this.idCategoria=idCategoria;
		
	}
	public void setCategoria(String nombreCategoria){
		this.nombreCategoria=nombreCategoria;
	}
	
	//METODOS GETTER********************************
	public int getIdCategoria(){
		return idCategoria;
	}
	public String getNombreCategoria(){
		return nombreCategoria;
	}
	@Override
	public String toString() {
		return "CategoriaID   " + idCategoria + "\nNombre: " + nombreCategoria;
	}
	
	
	
}
