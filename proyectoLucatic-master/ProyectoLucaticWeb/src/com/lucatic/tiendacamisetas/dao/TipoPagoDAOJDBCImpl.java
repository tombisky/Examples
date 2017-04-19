package com.lucatic.tiendacamisetas.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lucatic.tiendacamisetas.model.TipoPago;


public class TipoPagoDAOJDBCImpl implements GestorDAO<TipoPago> {

    private Connection con = null;

    //CONEXIÓN CON LA BASE DE DATOS**********************************
    TipoPagoDAOJDBCImpl() {
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/tiendacamiseta";
        String username = "root";
        String password = "1111";
        
        try {
            Class.forName(driverClassName);
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException se) {
            System.out.println("Error en la conexión con la base de datos: " + se);
            Logger.getLogger(TipoPagoDAOJDBCImpl.class.getName()).log(Level.INFO, null, se);
        
            System.exit(-1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TipoPagoDAOJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //****************************************************************
    
    
    // AÑADE UNA CATEGORIA EN MYSQL
    public void addItem(TipoPago tip) throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "INSERT INTO TIPO_PAGO (nombre)VALUES('"+tip.getNombreTipoPago() + "')";
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error añadiendo tipo de pago");
            }
        } catch (SQLException se) {
            
            throw new DAOException("Error añadiendo tipo de pago en DAO", se);
        }
    }
    //****************************************************************

    // ACTUALIZA UN TIPO DE PAGO EN MYSQL
    public void updateItem(TipoPago tip) throws DAOException {
        try (Statement stmt = con.createStatement()) {
        	String query = "UPDATE TIPO_PAGO "
                    + "SET NOMBRE='" + tip.getNombreTipoPago() + "' "
                  
                    + "WHERE IDTIPO_PAGO='" + tip.getIdTipoPago()+"'";
            
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error actualizando tipo de pago");
            }
        } catch (SQLException se) {
            throw new DAOException("Error modificando tipo de pago en DAO", se);
        }
    }

    // BORRA UN TIPO DE PAGO EN MYSQL
    public void removeItem(int idTipoPago) throws DAOException {
    	TipoPago tip = findById(idTipoPago);
        if (tip == null) {
            throw new DAOException("TipodePago id: " + idTipoPago + " no existe.");
        }
        try (Statement stmt = con.createStatement()) {
            String query = "DELETE FROM TIPO_PAGO WHERE IDTIPO_PAGO=" + idTipoPago;
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error borrando tipo de pago");
            }
        } catch (SQLException se) {
            
            throw new DAOException("Error borrando tipo de pago en DAO", se);
        }
    }

    
    // ENCUENTRA UN ID EN MYSQL
    public TipoPago findById(int idTipoPago) throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT * FROM TIPO_PAGO WHERE IDTIPO_PAGO=" + idTipoPago;
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return null;
            }
            return (new TipoPago(rs.getInt("IDTIPO_PAGO"), rs.getString("NOMBRE")));
        } catch (SQLException se) {
            
            throw new DAOException("Error encontrando tipo de pago en DAO", se);
        }
    }

    
    public TipoPago[] getAllTablas() throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT * FROM TIPO_PAGO";
            ResultSet rs = stmt.executeQuery(query);
            
            ArrayList<TipoPago> tips = new ArrayList<>();
            
            while (rs.next()) {
                tips.add(new TipoPago(rs.getInt("IDTIPO_PAGO"), rs.getString("NOMBRE")));
            }
            return tips.toArray(new TipoPago[0]);
        } catch (SQLException se) {
            
            throw new DAOException("Error obteniendo tipos de pago en DAO: " + se.getMessage(), se);
        }
    }

    public void close() {
        try {
            con.close();
        } catch (SQLException se) {
            System.out.println ("Exception cerrando conexión: " + se);
        }
    }


	@Override
	public TipoPago getItem() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


	
}