package com.lucatic.tiendacamisetas.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lucatic.tiendacamisetas.model.Color;

public class ColorDAOJDBCImpl implements GestorDAO<Color> {

    private Connection con = null;

    //CONEXIÓN CON LA BASE DE DATOS**********************************
    ColorDAOJDBCImpl() {
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/tiendacamiseta";
        String username = "root";
        String password = "1111";
        
        try {
            Class.forName(driverClassName);
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException se) {
            System.out.println("Error en la conexión con la base de datos: " + se);
            Logger.getLogger(ColorDAOJDBCImpl.class.getName()).log(Level.INFO, null, se);
        
            System.exit(-1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ColorDAOJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //****************************************************************
    
    
    // AÑADE UN COLOR EN MYSQL
    public void addItem(Color col) throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "INSERT INTO COLOR (nombre)VALUES('"+col.getNombreColor() + "')";
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error añadiendo color");
            }
        } catch (SQLException se) {
            
            throw new DAOException("Error añadiendo color en DAO", se);
        }
    }
    //****************************************************************

    // ACTUALIZA UN COLOR EN MYSQL
    public void updateItem(Color col) throws DAOException {
        try (Statement stmt = con.createStatement()) {
        	String query = "UPDATE COLOR "
                    + "SET NOMBRE='" + col.getNombreColor() + "' "
                  
                    + "WHERE IDCOLOR='" + col.getIdColor()+"'";
            
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error actualizando color");
            }
        } catch (SQLException se) {
            throw new DAOException("Error modificando color en DAO", se);
        }
    }

    // BORRA UN COLOR EN MYSQL
    public void removeItem(int idColor) throws DAOException {
        Color col = findById(idColor);
        if (col == null) {
            throw new DAOException("Color id: " + idColor + " no existe.");
        }
        try (Statement stmt = con.createStatement()) {
            String query = "DELETE FROM COLOR WHERE IDCOLOR=" + idColor;
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error borrando color");
            }
        } catch (SQLException se) {
            
            throw new DAOException("Error borrando color en DAO", se);
        }
    }

    
    // ENCUENTRA UN ID EN MYSQL
    public Color findById(int idColor) throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT * FROM COLOR WHERE IDCOLOR=" + idColor;
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return null;
            }
            return (new Color(rs.getInt("IDCOLOR"), rs.getString("NOMBRE")));
        } catch (SQLException se) {
            
            throw new DAOException("Error encontrando color en DAO", se);
        }
    }

    
    public Color[] getAllTablas() throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT * FROM COLOR";
            ResultSet rs = stmt.executeQuery(query);
            
            ArrayList<Color> colours = new ArrayList<>();
            
            while (rs.next()) {
                colours.add(new Color(rs.getInt("IDCOLOR"), rs.getString("NOMBRE")));
            }
            return colours.toArray(new Color[0]);
        } catch (SQLException se) {
            
            throw new DAOException("Error obteniendo colores en DAO: " + se.getMessage(), se);
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
	public Color getItem() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


	
		
	
}