package com.lucatic.tiendacamisetas.beans;

public class Detalle {

	private int idDetalle;
	private Producto producto;
	private int cantidad;
	private float precio;

	public Detalle() {

	}

	public Detalle(int idDetalle, Producto producto, int cantidad, float precio) {
		this.idDetalle = idDetalle;
		this.producto = producto;
		this.cantidad = cantidad;
		
		this.precio = precio*cantidad;
	}

	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
		result = prime * result + cantidad;
		result = prime * result + idDetalle;
		result = prime * result + Float.floatToIntBits(precio);
		result = prime * result + ((producto == null) ? 0 : producto.hashCode());
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
		Detalle other = (Detalle) obj;
		if (cantidad != other.cantidad)
			return false;
		if (idDetalle != other.idDetalle)
			return false;
		if (Float.floatToIntBits(precio) != Float.floatToIntBits(other.precio))
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Detalle [idDetalle=" + idDetalle + ", producto=" + producto + ", cantidad=" + cantidad + ", precio="
				+ precio + "]";
	}
}
