package com.lucatic.tiendacamisetas.beans;

import com.lucatic.tiendacamisetas.model.Categoria;
import com.lucatic.tiendacamisetas.model.Color;
import com.lucatic.tiendacamisetas.model.Genero;
import com.lucatic.tiendacamisetas.model.Talla;

public abstract class Producto {
	private int idProducto;
	private String descripcion;
	private Categoria idCategoria;
	private Genero idGenero;
	private Talla idTalla;
	private Color idColor;
	private float precio;
	
	Producto(){
	}
	
	Producto(int idProducto, String descripcion, Categoria idCategoria, Genero idGenero, Talla idTalla, Color idColor, float precio){
		this.idProducto=idProducto;
		this.descripcion=descripcion;
		this.idCategoria=idCategoria;
		this.idGenero=idGenero;
		this.idTalla=idTalla;
		this.idColor=idColor;
		this.precio=precio;
	}
	
	Producto(String descripcion, Categoria idCategoria, Genero idGenero, Talla idTalla, Color idColor, float precio){
		this.descripcion=descripcion;
		this.idCategoria=idCategoria;
		this.idGenero=idGenero;
		this.idTalla=idTalla;
		this.idColor=idColor;
		this.precio=precio;
	}
	
	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Categoria getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Categoria idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Genero getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(Genero idGenero) {
		this.idGenero = idGenero;
	}

	public Talla getIdTalla() {
		return idTalla;
	}

	public void setIdTalla(Talla idTalla) {
		this.idTalla = idTalla;
	}

	public Color getIdColor() {
		return idColor;
	}

	public void setIdColor(Color idColor) {
		this.idColor = idColor;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((idCategoria == null) ? 0 : idCategoria.hashCode());
		result = prime * result + ((idColor == null) ? 0 : idColor.hashCode());
		result = prime * result + ((idGenero == null) ? 0 : idGenero.hashCode());
		result = prime * result + idProducto;
		result = prime * result + ((idTalla == null) ? 0 : idTalla.hashCode());
		result = prime * result + Float.floatToIntBits(precio);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (idCategoria != other.idCategoria)
			return false;
		if (idColor == null) {
			if (other.idColor != null)
				return false;
		} else if (!idColor.equals(other.idColor))
			return false;
		if (idGenero != other.idGenero)
			return false;
		if (idProducto != other.idProducto)
			return false;
		if (idTalla != other.idTalla)
			return false;
		if (Float.floatToIntBits(precio) != Float.floatToIntBits(other.precio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Producto [descripcion=" + descripcion + ", idCategoria=" + idCategoria
				+ ", idGenero=" + idGenero + ", idTalla=" + idTalla + ", idColor=" + idColor + ", precio=" + precio
				+ "]";
	}
	
	
}
