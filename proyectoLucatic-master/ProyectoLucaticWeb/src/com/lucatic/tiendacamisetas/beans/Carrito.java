package com.lucatic.tiendacamisetas.beans;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
	private List <Producto> lista = new ArrayList<Producto>();

	public List<Producto> getLista() {
		return lista;
	}

	public Carrito(){
		
	}
	
	public boolean agregarProducto(Producto p){
		if(this.lista.add(p)){
			
		return true;
		}
		return false;
	}
	
	
	public void setLista(List<Producto> lista) {
		this.lista = lista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
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
		Carrito other = (Carrito) obj;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Carrito [lista=" + lista + "]";
	}
	
	
	
}
