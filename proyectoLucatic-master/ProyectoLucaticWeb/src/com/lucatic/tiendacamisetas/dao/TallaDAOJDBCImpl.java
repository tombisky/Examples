package com.lucatic.tiendacamisetas.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lucatic.tiendacamisetas.model.Talla;

public class TallaDAOJDBCImpl implements GestorDAO<Talla> {

    private Connection con = null;

    
    TallaDAOJDBCImpl() {
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/tiendacamiseta";
        String username = "root";
        String password = "1111";
        
        try {
            Class.forName(driverClassName);
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException se) {
            System.out.println("Error en la conexión con la base de datos: " + se);
            Logger.getLogger(TallaDAOJDBCImpl.class.getName()).log(Level.INFO, null, se);
        
            System.exit(-1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TallaDAOJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // AÑADE UNA TALLA EN MYSQL
    public void addItem(Talla tall) throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "INSERT INTO TALLA (nombre)VALUES('"+tall.getNombre() + "')";
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error añadiendo talla");
            }
        } catch (SQLException se) {
            
            throw new DAOException("Error añadiendo talla en DAO", se);
        }
    }

    // ACTUALIZA UNA TALLA EN MYSQL
    public void updateItem(Talla tall) throws DAOException {
        try (Statement stmt = con.createStatement()) {
        	String query = "UPDATE TALLA "
                    + "SET NOMBRE='" + tall.getNombre() + "' "
                  
                    + "WHERE IDTALLA='" + tall.getIdTalla()+"'";
            
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error actualizando talla");
            }
        } catch (SQLException se) {
            throw new DAOException("Error modificando talla en DAO", se);
        }
    }

    // BORRA UNA TALLA EN MYSQL
    public void removeItem(int idtalla) throws DAOException {
        Talla tall = findById(idtalla);
        if (tall == null) {
            throw new DAOException("Talla id: " + idtalla + " no existe.");
        }
        try (Statement stmt = con.createStatement()) {
            String query = "DELETE FROM TALLA WHERE IDTALLA=" + idtalla;
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error borrando talla");
            }
        } catch (SQLException se) {
            
            throw new DAOException("Error borrando talla en DAO", se);
        }
    }

    
    // ENCUENTRA UN ID EN MYSQL
    public Talla findById(int id) throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT * FROM TALLA WHERE IDTALLA=" + id;
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return null;
            }
            return (new Talla(rs.getInt("IDTALLA"), rs.getString("NOMBRE")));
        } catch (SQLException se) {
            //se.printStackTrace();
            throw new DAOException("Error encontrando talla en DAO", se);
        }
    }

    
    public Talla[] getAllTablas() throws DAOException {
        try (Statement stmt = con.createStatement()) {
            String query = "SELECT * FROM TALLA";
            ResultSet rs = stmt.executeQuery(query);
            
            ArrayList<Talla> tallas = new ArrayList<>();
            
            while (rs.next()) {
                tallas.add(new Talla(rs.getInt("IDTALLA"), rs.getString("NOMBRE")));
            }
            return tallas.toArray(new Talla[0]);
        } catch (SQLException se) {
            
            throw new DAOException("Error getting all tallas in DAO: " + se.getMessage(), se);
        }
    }

    public void close() {
        try {
            con.close();
        } catch (SQLException se) {
            System.out.println ("Exception cerrando connexion: " + se);
        }
    }

	@Override
	public Talla getItem() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
}