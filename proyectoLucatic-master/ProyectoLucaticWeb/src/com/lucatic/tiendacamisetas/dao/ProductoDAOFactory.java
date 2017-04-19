package com.lucatic.tiendacamisetas.dao;

public class ProductoDAOFactory {
	public ProductoDAO createProductoDAO(){
		return new ProductoDAOImp();
	}
}
