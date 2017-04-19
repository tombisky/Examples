package com.lucatic.tiendacamisetas.dao;

import java.util.ArrayList;
import com.lucatic.tiendacamisetas.beans.Producto;

public interface ProductoDAO extends GestorDAO<Producto>{
	 public ArrayList<Producto> findCamisetaByColor(int color) throws DAOException;
	 public ArrayList<Producto> findCamisetaByTalla(int talla) throws DAOException;
	 public void addCamiseta(Producto c) throws DAOException;
	 public ArrayList<Producto> AllCamiseta() throws DAOException;
	 public void updateCamiseta(Producto item) throws DAOException;
}
