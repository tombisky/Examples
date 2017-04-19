package com.lucatic.tiendacamisetas.dao;

public class TallaDAOFactory {

    public GestorDAO createTallaDAO() {
        return new TallaDAOJDBCImpl();
    }
}