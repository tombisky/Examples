package com.lucatic.tiendacamisetas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lucatic.tiendacamisetas.beans.Camiseta;
import com.lucatic.tiendacamisetas.beans.Producto;
import com.lucatic.tiendacamisetas.model.Categoria;
import com.lucatic.tiendacamisetas.model.Color;
import com.lucatic.tiendacamisetas.model.Genero;
import com.lucatic.tiendacamisetas.model.Talla;

public class ProductoDAOImp implements ProductoDAO{
	
	private Connection con = null;
	
	public ProductoDAOImp(){
		 String driverClassName = "com.mysql.jdbc.Driver";
	        String url = "jdbc:mysql://localhost/tiendacamiseta";
	        String username = "root";
	        String password = "1111";
	        
	        try {
	            Class.forName(driverClassName);
	            con = DriverManager.getConnection(url, username, password);
	        } catch (SQLException se) {
	            System.out.println("Error obtaining connection with the database: " + se);
	            Logger.getLogger(ProductoDAOImp.class.getName()).log(Level.INFO, null, se);
	        
	            System.exit(-1);
	        } catch (ClassNotFoundException ex) {
	            Logger.getLogger(ProductoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
	
	@Override
	public Producto findById(int id) throws DAOException {
		try (Statement st =  con.createStatement()){
			String query = "SELECT * FROM producto WHERE idproducto="+id;
			ResultSet rs = st.executeQuery(query);
			if(!rs.next()){
				return null;
			}
			
			if(rs.getInt("categoria") == 1)
			{		
				String query2 = "SELECT * FROM producto ,camiseta ,talla ,categoria ,color ,genero "
						+ "WHERE  idproducto = prodid "
						+ "AND categoria = idcategoria "
						+ "AND genero = idgenero "
						+ "AND talla = idtalla "
						+ "AND color = idcolor "
						+ "AND idproducto="+id;
				
				ResultSet rs2 = st.executeQuery(query2);
				Camiseta c = null;
				while(rs2.next())
				{
					Categoria categoria = new Categoria(rs2.getInt(14),rs2.getString(15));
					Genero genero = new Genero(rs2.getInt(18),rs2.getString(19));
					Talla tal = new Talla(rs2.getInt(12),rs2.getString(13));
					Color col = new Color(rs2.getInt(16),rs2.getString(17));
				   c = new Camiseta(rs2.getInt(1),rs2.getString(2),categoria,genero,tal,col,rs2.getFloat(7),rs2.getInt(8),rs2.getString(9),rs2.getString(10));
				}
				return c;
			}		
			else
			{
				return null;
			}
		
		} catch (SQLException se) {
            se.printStackTrace();
            throw new DAOException("Error finding product in DAO", se);
		}
	}

	@Override
	public ArrayList<Producto> findCamisetaByColor(int color) throws DAOException {
		ArrayList<Producto> productos = new ArrayList<>();
		try (Statement st = con.createStatement()){
			String query =  "SELECT * FROM producto ,camiseta ,talla ,categoria ,color ,genero "
					+ "WHERE  idproducto = prodid "
					+ "AND categoria = idcategoria "
					+ "AND genero = idgenero "
					+ "AND talla = idtalla "
					+ "AND color = idcolor "
					+ "AND color="+color;
			
			ResultSet rs = st.executeQuery(query);
		
			while(rs.next())
			{
				if(rs.getInt("categoria") == 1)
				{
					Categoria categoria = new Categoria(rs.getInt(14),rs.getString(15));
					Genero genero = new Genero(rs.getInt(18),rs.getString(19));
					Talla tal = new Talla(rs.getInt(12),rs.getString(13));
					Color col = new Color(rs.getInt(16),rs.getString(17));
					Camiseta c = new Camiseta(rs.getInt(1),rs.getString(2),categoria,genero,tal,col,rs.getFloat(7),rs.getInt(8),rs.getString(9),rs.getString(10));
					productos.add(c);	
					
				}	
			}
			
			return productos;
			
		} catch (SQLException se) {
            //se.printStackTrace();
            throw new DAOException("Error finding product in DAO", se);
		}
	}

	@Override
	public ArrayList<Producto> findCamisetaByTalla(int talla) throws DAOException {
		
		ArrayList<Producto> productos = new ArrayList<>();
		try (Statement st = con.createStatement()){
			String query =  "SELECT * FROM producto ,camiseta ,talla ,categoria ,color ,genero "
					+ "WHERE  idproducto = prodid "
					+ "AND categoria = idcategoria "
					+ "AND genero = idgenero "
					+ "AND talla = idtalla "
					+ "AND color = idcolor "
					+ "AND talla="+talla;
			
			ResultSet rs = st.executeQuery(query);
		
			while(rs.next())
			{
				if(rs.getInt("categoria") == 1)
				{
					Categoria categoria = new Categoria(rs.getInt(14),rs.getString(15));
					Genero genero = new Genero(rs.getInt(18),rs.getString(19));
					Talla tal = new Talla(rs.getInt(12),rs.getString(13));
					Color col = new Color(rs.getInt(16),rs.getString(17));
					Camiseta c = new Camiseta(rs.getInt(1),rs.getString(2),categoria,genero,tal,col,rs.getFloat(7),rs.getInt(8),rs.getString(9),rs.getString(10));
					productos.add(c);	
					
				}	
			}
			
			return productos;
			
		} catch (SQLException se) {
            //se.printStackTrace();
            throw new DAOException("Error finding product in DAO", se);
		}
	}
	@Override
	public void addCamiseta(Producto item) throws DAOException{
		 try (PreparedStatement stmt = con.prepareStatement("INSERT INTO camiseta (nombre,dibujo,prodid) VALUES (?, ?, ?)")){
			 stmt.setString(1,((Camiseta) item).getNombre() );
			 stmt.setString(2,((Camiseta) item).getDibujo());
			 System.out.println("--- ID: "+item.getIdProducto());
			 stmt.setInt(3,item.getIdProducto());
			
			if(stmt.executeUpdate() !=1){
				throw new DAOException("Error adding camiseta");
			}
		 }catch(SQLException se) {
	            throw new DAOException("Error adding camiseta in DAO", se);
		 }
	 }
	
	@Override
	public void addItem(Producto item) throws DAOException{
		try (PreparedStatement stmt = con.prepareStatement("INSERT INTO producto (descripcion,categoria,genero,talla,color,precio) VALUES (?, ?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS)){
			  stmt.setString(1,item.getDescripcion());   
		      stmt.setInt(2,item.getIdCategoria().getIdCategoria());
		      stmt.setInt(3,item.getIdGenero().getIdGenero()); 
		      stmt.setInt(4,item.getIdTalla().getIdTalla()); 
		      stmt.setInt(5,item.getIdColor().getIdColor()); 
		      stmt.setFloat(6,item.getPrecio());
			
		     
			if(stmt.executeUpdate() !=1){
				throw new DAOException("Error adding product");
			}
			
			//esta opcion no es la mas logica
			 ResultSet rs = stmt.getGeneratedKeys();
			 while(rs.next())
			 {
				 item.setIdProducto(rs.getInt(1));
			 }
			 
			 System.out.println("ID despues de insert: "+item.getIdProducto());
			
			if(item instanceof Camiseta)
			{		
				addCamiseta((Camiseta)item);		
			}
			
		}catch(SQLException se) {
            throw new DAOException("Error adding product in DAO", se);
		}		
	}
	
	@Override
	public void updateCamiseta(Producto item) throws DAOException {
		try (PreparedStatement stmt = con.prepareStatement("UPDATE Camiseta SET nombre = ?, dibujo = ? WHERE prodid="+item.getIdProducto())){
			 stmt.setString(1,((Camiseta) item).getNombre() );
			 stmt.setString(2,((Camiseta) item).getDibujo());
			 
			 if(stmt.executeUpdate() !=1){
					throw new DAOException("Error update camiseta");
				}
			 
		}catch(SQLException se){
			 throw new DAOException("Error updating camiseta in DAO", se);
		}
	}
	
	@Override
	public void updateItem(Producto item) throws DAOException {
		try (PreparedStatement stmt = con.prepareStatement("UPDATE Producto SET descripcion = ?, categoria = ?, genero = ?, talla = ?, "
				+ " color = ?, precio = ? WHERE idproducto="+item.getIdProducto())){
			stmt.setString(1, item.getDescripcion());
			stmt.setInt(2,item.getIdCategoria().getIdCategoria());
		    stmt.setInt(3,item.getIdGenero().getIdGenero()); 
		    stmt.setInt(4,item.getIdTalla().getIdTalla()); 
		    stmt.setInt(5,item.getIdColor().getIdColor()); 
		    stmt.setFloat(6,item.getPrecio());
			
			if (stmt.executeUpdate() != 1) {
                throw new DAOException("Error updating product");
            }
			
			if(item instanceof Camiseta)
			{
				updateCamiseta(item);
			}
			
		}catch(SQLException se){
			se.printStackTrace();
			 throw new DAOException("Error updating product in DAO", se);
		}
		
	}
	
	@Override
	public void removeItem(int item) throws DAOException {
		Producto p;
		p = findById(item);
		if(p == null)
			 throw new DAOException("Product id: " + item + " does not exist to delete.");
		
		try(Statement st = con.createStatement()){
			if(p instanceof Camiseta)
			{
				String query = "DELETE FROM camiseta WHERE idcamiseta="+item;
				if(st.executeUpdate(query) !=1){
					throw new DAOException("Error delete product");
				}
			}
		}catch(SQLException se) {
            throw new DAOException("Error delete product in DAO", se);
		}
	}

	@Override
	public Producto getItem() throws DAOException {
	
		return null;
	}
	
	@Override
	public ArrayList<Producto> AllCamiseta() throws DAOException {
		Statement st = null;
		
		ArrayList<Producto> camisetas = new ArrayList<>();
		try{
			st = con.createStatement();
			
			String query = "SELECT * FROM producto ,camiseta ,talla ,categoria ,color ,genero "
					+ "WHERE  idproducto = prodid "
					+ "AND categoria = idcategoria "
					+ "AND genero = idgenero "
					+ "AND talla = idtalla "
					+ "AND color = idcolor ";
			
			ResultSet rs = st.executeQuery(query);
			
			if(!rs.next())
				return null;
			else
			{
				while(rs.next())
				{
					Categoria categoria = new Categoria(rs.getInt(14),rs.getString(15));
					Genero genero = new Genero(rs.getInt(18),rs.getString(19));
					Talla tal = new Talla(rs.getInt(12),rs.getString(13));
					Color col = new Color(rs.getInt(16),rs.getString(17));
		
					Camiseta c = new Camiseta(rs.getInt(1),rs.getString(2),categoria,genero,tal,col,rs.getFloat(7),rs.getInt(8),rs.getString(9),rs.getString(10));
					camisetas.add(c);
				}
				return camisetas;
			}
			
		}catch(SQLException se){
			 throw new DAOException("Error getting all employees in DAO: " + se.getMessage(), se);
		}
		
	}
	
	public void close() {
	        try {
	            con.close();
	        } catch (SQLException se) {
	            System.out.println ("Exception closing Connection: " + se);
	        }
	 }
}
