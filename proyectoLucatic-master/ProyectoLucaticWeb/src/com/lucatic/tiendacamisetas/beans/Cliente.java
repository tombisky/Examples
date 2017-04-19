package com.lucatic.tiendacamisetas.beans;

public class Cliente {

	private int idUsuario;
	private int idCliente;
	private String nombre;
	private String apellidos;
	private String dni;
	private String direccion;
	private String telefono1;
	private String telefono2=null;
	
	
	public Cliente (){
			}
	public Cliente(int idUsuario, int idCliente, String nombre, String apellidos, String dni, String direccion, String telefono1){
		this.idUsuario=idUsuario;
		this.idCliente=idCliente;
		this.apellidos=apellidos;
		this.direccion=direccion;
		this.nombre=nombre;
		this.dni=dni;
		this.telefono1=telefono1;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono1() {
		return telefono1;
	}
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + idCliente;
		result = prime * result + idUsuario;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((telefono1 == null) ? 0 : telefono1.hashCode());
		result = prime * result + ((telefono2 == null) ? 0 : telefono2.hashCode());
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
		Cliente other = (Cliente) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (idCliente != other.idCliente)
			return false;
		if (idUsuario != other.idUsuario)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono1 == null) {
			if (other.telefono1 != null)
				return false;
		} else if (!telefono1.equals(other.telefono1))
			return false;
		if (telefono2 == null) {
			if (other.telefono2 != null)
				return false;
		} else if (!telefono2.equals(other.telefono2))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cliente [idUsuario=" + idUsuario + ", idCliente=" + idCliente + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", dni=" + dni + ", direccion=" + direccion + ", telefono1=" + telefono1 + ", telefono2="
				+ telefono2 + "]";
	}
	
}
