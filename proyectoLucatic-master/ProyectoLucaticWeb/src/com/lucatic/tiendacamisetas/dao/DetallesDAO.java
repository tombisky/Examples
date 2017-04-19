package com.lucatic.tiendacamisetas.dao;

import java.util.ArrayList;

import com.lucatic.tiendacamisetas.beans.Detalle;


public interface DetallesDAO extends GestorDAO<Detalle>{

    

    public Detalle find(int idDetalle) throws DAOException;

    public ArrayList<Detalle> getAllTablas() throws DAOException;


}