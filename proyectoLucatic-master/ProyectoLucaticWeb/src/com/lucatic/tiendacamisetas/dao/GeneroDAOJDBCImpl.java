package com.lucatic.tiendacamisetas.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lucatic.tiendacamisetas.model.Genero;

public class GeneroDAOJDBCImpl implements GestorDAO<Genero> {

    private Connection con = null;

    //CONEXIÓN CON LA BASE DE DATOS**********************************
    GeneroDAOJDBCImpl() {
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/tiendacamiseta";
        String username = "root";
        String password = "1111";
        
        try {
            Class.forName(driverClassName);
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException se) {
            System.out.println("Error en la conexión con la base de datos: " + se);
            Logger.getLogger(GeneroDAOJDBCImpl.class.getName()).log(Level.INFO, null, se);
        
            System.exit(-1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GeneroDAOJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //****************************************************************
    
    
    // AÑADE UN COLOR EN MYSQL
    public void addItem(Genero gen) throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "INSERT INTO GENERO (nombre)VALUES('"+gen.getNombreGenero() + "')";
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error añadiendo género");
            }
        } catch (SQLException se) {
            
            throw new DAOException("Error añadiendo género en DAO", se);
        }
    }
    //****************************************************************

    // ACTUALIZA UN GENERO EN MYSQL
    public void updateItem(Genero gen) throws DAOException {
        try (Statement stmt = con.createStatement()) {
        	String query = "UPDATE GENERO "
                    + "SET NOMBRE='" + gen.getNombreGenero() + "' "
                  
                    + "WHERE IDGENERO='" + gen.getIdGenero()+"'";
            
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error actualizando género");
            }
        } catch (SQLException se) {
            throw new DAOException("Error modificando género en DAO", se);
        }
    }

    // BORRA UN GENERO EN MYSQL
    public void removeItem(int idGenero) throws DAOException {
        Genero gen = findById(idGenero);
        if (gen == null) {
            throw new DAOException("Género id: " + idGenero + " no existe.");
        }
        try (Statement stmt = con.createStatement()) {
            String query = "DELETE FROM GENERO WHERE IDGENERO=" + idGenero;
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error borrando género");
            }
        } catch (SQLException se) {
            
            throw new DAOException("Error borrando género en DAO", se);
        }
    }

    
    // ENCUENTRA UN ID EN MYSQL
    public Genero findById(int idGenero) throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT * FROM GENERO WHERE IDGENERO=" + idGenero;
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return null;
            }
            return (new Genero(rs.getInt("IDGENERO"), rs.getString("NOMBRE")));
        } catch (SQLException se) {
            
            throw new DAOException("Error encontrando género en DAO", se);
        }
    }

    
    public Genero[] getAllTablas() throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT * FROM GENERO";
            ResultSet rs = stmt.executeQuery(query);
            
            ArrayList<Genero> generos = new ArrayList<>();
            
            while (rs.next()) {
                generos.add(new Genero(rs.getInt("IDGENERO"), rs.getString("NOMBRE")));
            }
            return generos.toArray(new Genero[0]);
        } catch (SQLException se) {
            
            throw new DAOException("Error obteniendo géneros en DAO: " + se.getMessage(), se);
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
	public Genero getItem() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


	}