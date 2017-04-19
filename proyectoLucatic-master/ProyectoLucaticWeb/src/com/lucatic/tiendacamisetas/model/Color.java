package com.lucatic.tiendacamisetas.model;

public class Color {

	
	private int idColor;
	private String nombreColor;
	
	//CONSTRUCTORES
	public Color(){
		
	}
	
	public Color(String nombreColor){
		this.nombreColor=nombreColor;
	}
	public Color(int idColor,String nombreColor){
		this.idColor=idColor;
		this.nombreColor=nombreColor;
	}
	
	//MÉTODOS SETTER	
	public void setIdColor(int idColor){
		this.idColor=idColor;
		
	}
	
	public void setNombreColor(String nombreColor){
		this.nombreColor=nombreColor;
	}
	
	//MÉTODOS GETTER
	public int getIdColor(){
		return idColor;
	}
	
	public String getNombreColor(){
		return nombreColor;
	}

	@Override
	public String toString() {
		return "ColorID:    " + getIdColor() + "\nNombre: " + getNombreColor();
	}
	
	
}
