package com.lucatic.tiendacamisetas.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lucatic.tiendacamisetas.beans.Camiseta;
import com.lucatic.tiendacamisetas.beans.Detalle;
import com.lucatic.tiendacamisetas.beans.Producto;
import com.lucatic.tiendacamisetas.model.Categoria;
import com.lucatic.tiendacamisetas.model.Color;
import com.lucatic.tiendacamisetas.model.Genero;
import com.lucatic.tiendacamisetas.model.Talla;

public class DetallesDAOJDBCImp implements DetallesDAO {
	private Connection con = null;

	public DetallesDAOJDBCImp() {
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/tiendacamiseta";
		String username = "root";
		String password = "1111";
		//creamos la sesion de la bbdd y cargamos el driver
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

	public void addItem(Detalle det) throws DAOException {
		/*
		 * Este metodo es para agregar detalles a nuestra base de datos
		 */
		try (Statement stmt = con.createStatement()) {
			String query = "INSERT INTO detalle_compra (producto,cantidad,precio,factura)VALUES('"
					+ det.getProducto().getIdProducto() + "','" + det.getCantidad() + "','" + det.getPrecio() + "','"
					+ 1 + "')";
			if (stmt.executeUpdate(query) != 1) {
				throw new DAOException("Error añadiendo detalle");
			}
		} catch (SQLException se) {
			se.printStackTrace();
			throw new DAOException("Error añadiendo detalle en DAO", se);
		}
	}

	public void updateItem(Detalle det) throws DAOException {
		//Metodo para realizar modificaciones de detalles en la base de datos (hay que testearlo con la web)
		try (Statement stmt = con.createStatement()) {
			String query = "UPDATE detalle_compra " + "SET producto='" + det.getProducto() + "' and SET cantidad='"
					+ det.getCantidad() + "' and SET precio='" + det.getPrecio()

					+ "WHERE idDetalle_compra='" + det.getIdDetalle() + "'";

			if (stmt.executeUpdate(query) != 1) {
				throw new DAOException("Error actualizando detalle");
			}
		} catch (SQLException se) {
			throw new DAOException("Error actualizando detalle en DAO", se);
		}
	}

	public void removeItem(int idDetalle) throws DAOException {
		/*
		 * Metodo para eliminar un item buscandolo por el id
		 */
		Detalle det = find(idDetalle);
		if (det == null) {
			throw new DAOException("detalle id: " + idDetalle + " no existe.");
		}
		try (Statement stmt = con.createStatement()) {
			String query = "DELETE FROM detalle_compra WHERE idDetalle_compra= " + idDetalle;
			if (stmt.executeUpdate(query) != 1) {
				throw new DAOException("Error borrando detalle");
			}
		} catch (SQLException se) {

			throw new DAOException("Error borrando detalle en DAO", se);
		}
	}

	public Detalle find(int idDetalle) throws DAOException {
		/*
		 * Pasamos un ID de detalle y mostramos el detalle en si
		 * 
		 */
		try (Statement stmt = con.createStatement()) {
			int iddetalle1 = 0;
			int cantidad = 0;
			float precio = 0;
			int producto2 = 0;

			String query = "SELECT * FROM detalle_compra WHERE IDDETALLE_COMPRA = " + idDetalle;
			ResultSet rs1 = stmt.executeQuery(query);

			while (rs1.next()) {

				iddetalle1 = rs1.getInt(1);
				cantidad = rs1.getInt(2);
				precio = rs1.getFloat(3);
				producto2 = rs1.getInt(4);

			}

			String query2 = "SELECT * FROM producto, camiseta WHERE idproducto=prodid and idproducto=" + producto2;
			ResultSet rs2 = stmt.executeQuery(query2);

			Producto producto = null;

			while (rs2.next()) {

				producto = new ProductoDAOImp().findById(rs2.getInt("IDPRODUCTO"));

			}

			Detalle detalle = new Detalle(iddetalle1, producto, cantidad, precio);

			return detalle;
		} catch (SQLException se) {
			se.printStackTrace();
			throw new DAOException("Error encontrando detalle en DAO", se);
		}
	}

	public ArrayList<Detalle> getAllTablas() throws DAOException {
		/*
		 * Listamos todos los detalles que tenemos en nuestra base de datos.
		 */
		try (Statement stmt = con.createStatement()) {
			String query = "SELECT * FROM DETALLE_COMPRA";
			ArrayList<Detalle> detalles = new ArrayList<>();
			int iddetalle1 = 0;
			int cantidad = 0;
			float precio = 0;
			int producto2 = 0;
			ResultSet rs1 = stmt.executeQuery(query);
			Detalle detalle = null;
			while (rs1.next()) {
				detalles.add(find(rs1.getInt("iddetalle_compra")));

			}

			return detalles;

		} catch (SQLException se) {
			se.getMessage();
			se.printStackTrace();

			throw new DAOException("Error obteniendo Detalles en DAO: " + se.getMessage(), se);
		}
	}

	public void close() {
		try {
			con.close();
		} catch (SQLException se) {
			System.out.println("Exception cerrando conexión: " + se);
		}
	}

	@Override
	public Detalle getItem() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Detalle findById(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
