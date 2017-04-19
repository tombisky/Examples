package com.lucatic.tiendacamisetas.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.lucatic.tiendacamisetas.beans.Detalle;
import com.lucatic.tiendacamisetas.beans.Producto;
import com.lucatic.tiendacamisetas.dao.DAOException;
import com.lucatic.tiendacamisetas.dao.DetallesDAO;
import com.lucatic.tiendacamisetas.dao.DetallesDAOFactory;
import com.lucatic.tiendacamisetas.dao.DetallesDAOJDBCImp;
import com.lucatic.tiendacamisetas.dao.GestorDAO;
import com.lucatic.tiendacamisetas.dao.ProductoDAOImp;
import com.lucatic.tiendacamisetas.model.Categoria;

public class DetalleTestInteractive {
	static DetallesDAOJDBCImp jdbc = new DetallesDAOJDBCImp();
	static ProductoDAOImp producto = new ProductoDAOImp();
	public void iniciarMenuDetalle() {
		DetallesDAOFactory factory = new DetallesDAOFactory();
		boolean timeToQuitDetalle = false;
		try (DetallesDAO dao = factory.createDetalleDAO()) {
			do {
				try {
					timeToQuitDetalle = executeMenuDetalle(dao);
				} catch (DAOException e) {
					System.out.println("Error " + e.getClass());
					System.out.println("Mensaje " + e.getMessage());
				}
			} while (!timeToQuitDetalle);
		} catch (IOException e) {
			System.out.println("Error " + e.getClass());
			System.out.println("Mensaje " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error " + e.getClass());
			System.out.println("Mensaje " + e.getMessage());
		}

	}

	public static boolean executeMenuDetalle(GestorDAO dao) throws IOException, DAOException {

		int idDetalle;
		Detalle det;
		String action;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("\n\n----Tabla Detalles----");
		System.out.println("\n\n[C]rear | [E]ncontrar | [M]ostrar Todo | [B]orrar | [A]tras: \n");
		action = in.readLine();

		if ((action.length() == 0) || action.toUpperCase().charAt(0) == 'A') {

			return true;
		}else{
			
		}
		switch (action.toUpperCase().charAt(0)) {
		//MENU CREAR DETALLE**************************************************************
		case 'C':
			System.out.println("Indica el id del producto que quieres agregar");
			int idcreate = new Integer(in.readLine().trim());
			System.out.println("Indica la cantidad");
			int cantidad = new Integer(in.readLine().trim());
			Producto p =producto.findById(idcreate);
			float precio = p.getPrecio();
			Detalle detalle = new Detalle(1,p,cantidad,precio);
			detalle.toString();
			jdbc.addItem(detalle);
			System.out.println(detalle.toString());
			break;
			

		// MENÚ LEER DETALLE ****************************************************
		case 'E':
			System.out.println("Por favor, introduce el valor del ID a localizar:");
			int idfind= new Integer(in.readLine().trim());
			int id1=1;
			Detalle mostrar=jdbc.find(idfind);
			if (mostrar != null) {
				System.out.println(mostrar.toString() + "\n");
			} else {
				System.out.println("\n\n Id  " + id1+ " no encontrado");
				}

			break;

		// MENÚ MODIFICAR CATEGORIA ***********************************************
		case 'M':
			ArrayList<Detalle> d = jdbc.getAllTablas(); 
			for(Detalle de:d){
				System.out.println(de.toString());
			}
			
			break;

		// MENÚ BORRAR DETALLE ***************************************************
		case 'B':
			System.out.println("Introduce el ID que desea borrar: ");
			int idborrar= new Integer(in.readLine().trim());
			jdbc.find(idborrar);
			jdbc.removeItem(idborrar);
			System.out.println("ID " + idborrar + " borrada correctamente.");
			jdbc.find(idborrar);
			break;

		// MENÚ LISTAR COLORES
				
		}
		return false;
	}
}
