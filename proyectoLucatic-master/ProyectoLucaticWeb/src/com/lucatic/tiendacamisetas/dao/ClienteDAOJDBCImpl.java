package com.lucatic.tiendacamisetas.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.lucatic.tiendacamisetas.beans.Cliente;


public class ClienteDAOJDBCImpl implements ClienteDAO {
	
	 private Connection con = null;
	
	 //CONEXIÓN CON LA BASE DE DATOS**********************************
     ClienteDAOJDBCImpl() {
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
    
    
    // AÑADE UN CLIENTE EN MYSQL

	public void addItem(Cliente item) throws DAOException {

		try (Statement stmt = con.createStatement()) {
            String query = "INSERT INTO CLIENTE VALUES ('"+ item.getIdCliente() + "','" 
            											+item.getNombre()+ "','"
            											+item.getApellidos()+"','"
            											+item.getDni() + "','"
            											+item.getDireccion()+"','"
            											+item.getTelefono1()+"','"
            											+item.getTelefono2()+"','"
            											+item.getIdUsuario()+"')";
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error añadiendo cliente");
            }
        } catch (SQLException se) {
            
            throw new DAOException("Error añadiendo cliente en DAO", se);
        }
    }

	
	// BORRA UN CLIENTE EN MYSQL

	public void removeItem(Cliente item) throws DAOException {
		int id = item.getIdCliente();
		
        try (Statement stmt = con.createStatement()) {
            String query = "DELETE FROM CLIENTE WHERE IDCLIENTE=" + id;
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error borrando cliente");
            }
        } catch (SQLException se) {
            
            throw new DAOException("Error borrando cliente en DAO", se);
        }
	}

	// ACTUALIZA UN CLIENTE EN MYSQL
	public void updateItem(Cliente item) throws DAOException {
		try (Statement stmt = con.createStatement()) {
        	String query = "UPDATE CLIENTE "
                    + "SET NOMBRE='" + item.getNombre() + "', "
                    +"SET APELLIDO='"+item.getApellidos()+"',"
                    +"SET DNI='"+item.getDni()+"',"
                    +"SET DIRECCION='"+item.getDireccion()+"',"
                    +"SET TELEFONO1='"+item.getTelefono1()+"',"
                    +"SET TELEFONO2='"+item.getTelefono2()+"',"
                    +"SET IDUSUARIO='"+item.getIdUsuario()+"'"
                    + "WHERE IDCLIENTE='" + item.getIdCliente()+"'";
            
            if (stmt.executeUpdate(query) != 1) {
                throw new DAOException("Error actualizando cliente");
            }
        } catch (SQLException se) {
            throw new DAOException("Error modificando cliente en DAO", se);
        }

	}

	@Override
	public Cliente getItem(Cliente item) throws DAOException {
		 try (Statement stmt = con.createStatement()) {
	            String query = "SELECT * FROM CLIENTE WHERE IDCLIENTE=" + item.getIdCliente();
	            ResultSet rs = stmt.executeQuery(query);
	            if (!rs.next()) {
	                return null;
	            }
	            return (new Cliente(rs.getInt("IDUSUARIO"),
	            					rs.getInt("IDCLIENTE"),
	            					rs.getString("NOMBRE"),
	            					rs.getString("APELLIDO"),
	            					rs.getString("DNI"),
	            					rs.getString("DIRECCION"),
	            					rs.getString("TELEFONO1")
	            					));
	        } catch (SQLException se) {
	            
	            throw new DAOException("Error encontrando tipo de pago en DAO", se);
	        }
	}

}
