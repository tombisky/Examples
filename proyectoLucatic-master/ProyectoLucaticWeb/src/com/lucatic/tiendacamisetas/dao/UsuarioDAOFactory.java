package com.lucatic.tiendacamisetas.dao;

public class UsuarioDAOFactory {

    public GestorDAO createUsuarioDAO() {
        return new UsuarioDAOJDBCImpl();
    }
}