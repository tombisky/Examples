package com.lucatic.tiendacamisetas.dao;

public class ColorDAOFactory {

    public GestorDAO createColorDAO() {
        return new ColorDAOJDBCImpl();
    }
}