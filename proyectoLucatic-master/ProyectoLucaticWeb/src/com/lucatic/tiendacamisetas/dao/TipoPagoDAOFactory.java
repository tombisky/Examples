package com.lucatic.tiendacamisetas.dao;

public class TipoPagoDAOFactory {

    public GestorDAO createTipoPagoDAO() {
        return new TipoPagoDAOJDBCImpl();
    }
}