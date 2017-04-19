package com.lucatic.tiendacamisetas.dao;

public class DetallesDAOFactory {

	public DetallesDAO createDetalleDAO(){
		return new DetallesDAOJDBCImp();
	}
}
