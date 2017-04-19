package com.lucatic.tiendacamisetas.beans;

import com.lucatic.tiendacamisetas.model.Categoria;
import com.lucatic.tiendacamisetas.model.Color;
import com.lucatic.tiendacamisetas.model.Genero;
import com.lucatic.tiendacamisetas.model.Talla;

public class Camiseta extends Producto{
	private int idCamiseta;
	private String nombre;
	private String dibujo;
	
	public Camiseta(){}
	
	public Camiseta(int idProducto, String descripcion, Categoria idCategoria, Genero idGenero, Talla idTalla, Color idColor, float precio, int idCamiseta,String nombre,String dibujo){
		super(idProducto,descripcion,idCategoria,idGenero,idTalla,idColor,precio);
		this.idCamiseta=idCamiseta;
		this.nombre = nombre;
		this.dibujo=dibujo;
	}

	public Camiseta(String descripcion, Categoria idCategoria, Genero idGenero, Talla idTalla, Color idColor, float precio,String nombre,String dibujo){
		super(descripcion,idCategoria,idGenero,idTalla,idColor,precio);
		this.nombre = nombre;
		this.dibujo=dibujo;
	}
	
	public int getIdCamiseta() {
		return idCamiseta;
	}

	public void setIdCamiseta(int idCamiseta) {
		this.idCamiseta = idCamiseta;
	}

	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public String getDibujo() {
		return dibujo;
	}

	public void setDibujo(String dibujo) {
		this.dibujo = dibujo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dibujo == null) ? 0 : dibujo.hashCode());
		result = prime * result + idCamiseta;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Camiseta other = (Camiseta) obj;
		if (dibujo == null) {
			if (other.dibujo != null)
				return false;
		} else if (!dibujo.equals(other.dibujo))
			return false;
		if (idCamiseta != other.idCamiseta)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString()+" Camiseta [nombre=" + nombre + ", dibujo=" + dibujo + "]";
	}
	
}
