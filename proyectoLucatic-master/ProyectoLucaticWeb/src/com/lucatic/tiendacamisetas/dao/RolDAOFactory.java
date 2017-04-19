package com.lucatic.tiendacamisetas.dao;

public class RolDAOFactory {

    public GestorDAO createRolDAO() {
        return new RolDAOJDBCImpl();
    }
}