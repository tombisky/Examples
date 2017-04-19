package com.lucatic.agenda.dao;

import java.util.List;


import com.lucatic.agenda.beans.Categoria;

public interface GestorDAO<T> extends AutoCloseable{
	//Definimos los metodos que tendrán todos los dao que implementen esta interface
	
	//Listamos todos
		public List <T> list();
		//obtenemos un registro
		public T get(int id);
		//Guardamos o actualizamos
		public void saveOrUpdate(T item);
		//Borramos un registro según un ID
		public void delete(int id);
}
