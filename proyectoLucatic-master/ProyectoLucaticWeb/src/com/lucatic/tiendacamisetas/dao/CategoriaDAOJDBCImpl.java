package com.lucatic.tiendacamisetas.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lucatic.tiendacamisetas.model.Categoria;


public class CategoriaDAOJDBCImpl implements GestorDAO<Categoria> {

    private Connection con = null;

    //CONEXIÓN CON LA BASE DE DATOS**********************************
    CategoriaDAOJDBCImpl() {
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/tiendacamiseta";
        String username = "root";
        String password = "1111";
        
        try {
            Class.forName(driverClassName);
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException se) {
            System.out.println("Error en la conexión con la base de datos: " + se);
            Logger.getLogger(CategoriaDAOJDBCImpl.class.getName()).log(Level.INFO, null, se);
        
            System.exit(-1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoriaDAOJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //****************************************************************
    
    
    // AÑADE UNA CATEGORIA EN MYSQL
    public void addItem(Categoria cat) throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "INSERT INTO CATEGORIA (nombre)VALUES('"+cat.getNombreCategoria() + "')";
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error añadiendo categoría");
            }
        } catch (SQLException se) {
            
            throw new DAOException("Error añadiendo categoría en DAO", se);
        }
    }
    //****************************************************************

    // ACTUALIZA UNA CATEGORIA EN MYSQL
    public void updateItem(Categoria cat) throws DAOException {
        try (Statement stmt = con.createStatement()) {
        	String query = "UPDATE CATEGORIA "
                    + "SET NOMBRE='" + cat.getNombreCategoria() + "' "
                  
                    + "WHERE IDCATEGORIA='" + cat.getIdCategoria()+"'";
            
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error actualizando categoría");
            }
        } catch (SQLException se) {
            throw new DAOException("Error modificando categoría en DAO", se);
        }
    }

    // BORRA UNA CATEGORIA EN MYSQL
    public void removeItem(int idCategoria) throws DAOException {
    	Categoria cat = findById(idCategoria);
        if (cat == null) {
            throw new DAOException("Categoría id: " + idCategoria + " no existe.");
        }
        try (Statement stmt = con.createStatement()) {
            String query = "DELETE FROM CATEGORIA WHERE IDCATEGORIA=" + idCategoria;
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error borrando categoría");
            }
        } catch (SQLException se) {
            
            throw new DAOException("Error borrando categoría en DAO", se);
        }
    }

    
    // ENCUENTRA UN ID EN MYSQL
    public Categoria findById(int idCategoria) throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT * FROM CATEGORIA WHERE IDCATEGORIA=" + idCategoria;
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return null;
            }
            return (new Categoria(rs.getInt("IDCATEGORIA"), rs.getString("NOMBRE")));
        } catch (SQLException se) {
            
            throw new DAOException("Error encontrando categoría en DAO", se);
        }
    }

    
    /*public Categoria[] getItem() throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT * FROM CATEGORIA";
            ResultSet rs = stmt.executeQuery(query);
            
            ArrayList<Categoria> categorias = new ArrayList<>();
            
            while (rs.next()) {
                categorias.add(new Categoria(rs.getInt("IDCATEGORIA"), rs.getString("NOMBRE")));
            }
            return categorias.toArray(new Categoria[0]);
        } catch (SQLException se) {
            
            throw new DAOException("Error obteniendo categorías en DAO: " + se.getMessage(), se);
        }
    }
*/
    public void close() {
        try {
            con.close();
        } catch (SQLException se) {
            System.out.println ("Exception cerrando conexión: " + se);
        }
    }


	@Override
	public Categoria getItem() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
}