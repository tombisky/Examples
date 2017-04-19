package com.lucatic.tiendacamisetas.beans;

import com.lucatic.tiendacamisetas.model.Rol;

public class Usuario {
	
	private Rol rol;
	private int idUsuario;
	private String nombreUsuario;
	private String password;
	private String cuentaCorreo;
	private int roll;
	public Usuario(){
		
	}
	public Usuario(int idUsuario, String nombreUsuario, String password, String cuentaCorreo, Rol rol){
		this.cuentaCorreo=cuentaCorreo;
		this.idUsuario=idUsuario;
		this.nombreUsuario=nombreUsuario;
		this.password=password;
		this.rol=rol;
	}
	
	public Usuario(int idUsuario, String nombreUsuario, String password, String cuentaCorreo, int roll){
		this.cuentaCorreo=cuentaCorreo;
		this.idUsuario=idUsuario;
		this.nombreUsuario=nombreUsuario;
		this.password=password;
		this.roll=roll;
	}
	public Usuario(String nombreUsuario, String password, String cuentaCorreo, Rol rol){
		this.cuentaCorreo=cuentaCorreo;
		this.nombreUsuario=nombreUsuario;
		this.password=password;
		this.rol=rol;
	}
	
	public Usuario(String nombreUsuario, String password, String cuentaCorreo, int roll){
		this.cuentaCorreo=cuentaCorreo;
		this.nombreUsuario=nombreUsuario;
		this.password=password;
		this.roll=roll;
	}
	
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public int getRoll() {
		return roll;
	}
	public void setRoll(int roll) {
		this.roll = roll;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCuentaCorreo() {
		return cuentaCorreo;
	}
	public void setCuentaCorreo(String cuentaCorreo) {
		this.cuentaCorreo = cuentaCorreo;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuentaCorreo == null) ? 0 : cuentaCorreo.hashCode());
		result = prime * result + idUsuario;
		result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
		
		
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
		Usuario other = (Usuario) obj;
		if (cuentaCorreo == null) {
			if (other.cuentaCorreo != null)
				return false;
		} else if (!cuentaCorreo.equals(other.cuentaCorreo))
			return false;
		if (idUsuario != other.idUsuario)
			return false;
		if (nombreUsuario == null) {
			if (other.nombreUsuario != null)
				return false;
		} else if (!nombreUsuario.equals(other.nombreUsuario))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (rol != other.rol)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UsuarioID:   "+idUsuario+"\nNombreUsuario: "+nombreUsuario+"\nRol: " + rol + "\nPassword: " + password
				+ "\nCuentaCorreo: " + cuentaCorreo;
	}
	
}
