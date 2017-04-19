package com.lucatic.tiendacamisetas.dao;

public class CategoriaDAOFactory {

    public GestorDAO createCategoriaDAO() {
        return new CategoriaDAOJDBCImpl();
    }
}