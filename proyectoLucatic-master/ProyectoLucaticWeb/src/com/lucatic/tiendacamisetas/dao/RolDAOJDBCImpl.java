package com.lucatic.tiendacamisetas.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lucatic.tiendacamisetas.model.Rol;

public class RolDAOJDBCImpl implements GestorDAO<Rol> {

    private Connection con = null;

    //CONEXIÓN CON LA BASE DE DATOS**********************************
    RolDAOJDBCImpl() {
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/tiendacamiseta";
        String username = "root";
        String password = "1111";
        
        try {
            Class.forName(driverClassName);
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException se) {
            System.out.println("Error en la conexión con la base de datos: " + se);
            Logger.getLogger(RolDAOJDBCImpl.class.getName()).log(Level.INFO, null, se);
        
            System.exit(-1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RolDAOJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //****************************************************************
    
    
    // AÑADE UN ROL EN MYSQL
    public void addItem(Rol rol) throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "INSERT INTO ROL (nombre)VALUES('"+rol.getNombreRol() + "')";
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error añadiendo rol");
            }
        } catch (SQLException se) {
            
            throw new DAOException("Error añadiendo rol en DAO", se);
        }
    }
    //****************************************************************

    // ACTUALIZA UN ROL EN MYSQL
    public void updateItem(Rol rol) throws DAOException {
        try (Statement stmt = con.createStatement()) {
        	String query = "UPDATE ROL "
                    + "SET NOMBRE='" + rol.getNombreRol() + "' "
                  
                    + "WHERE IDROL='" + rol.getIdRol()+"'";
            
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error actualizando rol");
            }
        } catch (SQLException se) {
            throw new DAOException("Error modificando rol en DAO", se);
        }
    }

    // BORRA UN ROL EN MYSQL
    public void removeItem(int idRol) throws DAOException {
        Rol rol = findById(idRol);
        if (rol == null) {
            throw new DAOException("Rol id: " + idRol + " no existe.");
        }
        try (Statement stmt = con.createStatement()) {
            String query = "DELETE FROM ROL WHERE IDROL=" + idRol;
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error borrando rol");
            }
        } catch (SQLException se) {
            
            throw new DAOException("Error borrando rol en DAO", se);
        }
    }

    
    // ENCUENTRA UN ID EN MYSQL
    public Rol findById(int idRol) throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT * FROM ROL WHERE IDROL=" + idRol;
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return null;
            }
            return (new Rol(rs.getInt("IDROL"), rs.getString("NOMBRE")));
        } catch (SQLException se) {
            
            throw new DAOException("Error encontrando rol en DAO", se);
        }
    }

    
    public Rol[] getAllTablas() throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT * FROM ROL";
            ResultSet rs = stmt.executeQuery(query);
            
            ArrayList<Rol> roles = new ArrayList<>();
            
            while (rs.next()) {
                roles.add(new Rol(rs.getInt("IDROL"), rs.getString("NOMBRE")));
            }
            return roles.toArray(new Rol[0]);
        } catch (SQLException se) {
            
            throw new DAOException("Error obteniendo roles en DAO: " + se.getMessage(), se);
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
	public Rol getItem() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}


	}