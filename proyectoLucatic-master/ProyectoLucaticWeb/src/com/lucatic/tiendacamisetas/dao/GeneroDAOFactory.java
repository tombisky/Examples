package com.lucatic.tiendacamisetas.dao;

public class GeneroDAOFactory {

    public GestorDAO createGeneroDAO() {
        return new GeneroDAOJDBCImpl();
    }
}