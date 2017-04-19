package com.lucatic.tiendacamisetas.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.lucatic.tiendacamisetas.dao.CategoriaDAOFactory;
import com.lucatic.tiendacamisetas.dao.DAOException;
import com.lucatic.tiendacamisetas.dao.GestorDAO;
import com.lucatic.tiendacamisetas.model.Categoria;

public class CategoriaTestInteractive {

	public void IniciarMenuCategoria() {
		CategoriaDAOFactory factory = new CategoriaDAOFactory();
		boolean timeToQuitCategoria = false;
		try (GestorDAO dao = factory.createCategoriaDAO()) {
			do {
				try {
					timeToQuitCategoria = executeMenuCategoria(dao);
				} catch (DAOException e) {
					System.out.println("Error " + e.getClass().getName());
					System.out.println("Message: " + e.getMessage());
				}
			} while (!timeToQuitCategoria);
		} catch (IOException e) {
			System.out.println("Error " + e.getClass().getName() + " , quiting.");
			System.out.println("Message: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error closing resource " + e.getClass().getName());
			System.out.println("Message: " + e.getMessage());
		}
	}

	// MENÚ***************************************************************************
	public static boolean executeMenuCategoria(GestorDAO dao) throws IOException, DAOException {
		int idCategoria;
		Categoria cat;
		String action;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("\n\n----Tabla Categoría----");
		System.out.println("\n\n[C]rear | [E]ncontrar | [M]odificar | [B]orrar | [L]istar | [A]tras: \n");
		action = in.readLine();
		if ((action.length() == 0) || action.toUpperCase().charAt(0) == 'A') {
            
			return true;
		}

		switch (action.toUpperCase().charAt(0)) {
		
		// MENÚ CREAR CATEGORIA **************************************************
		case 'C':
			cat = inputCategoria(in, null, true);
			dao.addItem(cat);

			System.out.println("\n\nCategoría " + cat.getNombreCategoria() + " introducida correctamente:");

			break;

		// MENÚ LEER CATEGORIA ****************************************************
		case 'E':
			System.out.println("Por favor, introduce el valor del ID a localizar:");
			idCategoria = new Integer(in.readLine().trim());

			cat = (Categoria)dao.findById(idCategoria);
			if (cat != null) {
				System.out.println(cat + "\n");
			} else {
				System.out.println("\n\nCategoría " + idCategoria + " no encontrada");
				}

			break;

		// MENÚ MODIFICAR CATEGORIA ***********************************************
		case 'M':

			System.out.println("Por favor, introduce el valor del ID a modificar:");
			idCategoria = new Integer(in.readLine().trim());

			cat = null;
			cat = (Categoria)dao.findById(idCategoria);
			if (cat == null) {
				System.out.println("\n\nCategoría " + idCategoria + " no encontrada");
			}
			cat = inputCategoria(in, cat);
			dao.updateItem(cat);
			System.out.println("Categoría modificada a [" + cat.getNombreCategoria() + "] correctamente.");
			break;

		// MENÚ BORRAR CATEGORIA ***************************************************
		case 'B':
			System.out.println("Introduce el ID que desea borrar: ");
			idCategoria = new Integer(in.readLine().trim());

			dao.removeItem(idCategoria);
			System.out.println("ID " + idCategoria + " borrada correctamente.");
			break;

		// MENÚ LISTAR COLORES
		/*case 'L':
			Categoria[] allCategoria = dao.getAllTablas();
			for (Categoria categoria : allCategoria) {
				System.out.println(categoria + "\n");
			}
			break;
			*/
		}

		return false;
	}
	
	// *************************************************************************************
	

	// MODIFICA UNA CATEGORIA ********************************************************************
	public static Categoria inputCategoria(BufferedReader in, Categoria catDefaults) throws IOException {
		int id = -1;
		String nombre;
		Categoria categorias;

		try {
			id = catDefaults.getIdCategoria();
			if (id < 0) {
				do {
					System.out.println("Por favor, introduce un valor válido para el ID");
					System.out.println("Introduce el valor del ID a modificar: ");
					id = new Integer(in.readLine().trim());
				} while (id < 0);
			}
		} catch (NumberFormatException e) {
			System.out.println("Introduce el valor del ID a modificar: ");
		}
		id = catDefaults.getIdCategoria();
		String prompt = "Introduce el valor que modificará la categoría"
				+ ((catDefaults == null) ? "" : " [" + catDefaults.getNombreCategoria() + "]");

		do {
			System.out.println(prompt + " : ");
			nombre = in.readLine().trim();
			if (nombre.equals("") && catDefaults != null) {
				nombre = catDefaults.getNombreCategoria();
			}
			if (nombre.length() < 1) {
				System.out.println("Por favor, introduce un color válido");
			}
		} while (nombre.length() < 1);

		categorias = new Categoria(id, nombre);
		return categorias;
	}
	// ***************************************************************************************
	

	// CREA UNA NUEVA CATEGORIA ********************************************************************

	
	public static Categoria inputCategoria(BufferedReader in, Categoria catDefaults, boolean newCategoria) throws IOException {

		String nombre;
		Categoria cat;
		String prompt = "Introduce una categoría" + ((catDefaults == null) ? "" : " [" + catDefaults.getNombreCategoria() + "]");

		do {
			System.out.println(prompt + " : ");
			nombre = in.readLine().trim();
			if (nombre.equals("") && catDefaults != null) {
				nombre = catDefaults.getNombreCategoria();
			}
			if (nombre.length() < 1) {
				System.out.println("Por favor, introduce un color válido");
			}
		} while (nombre.length() < 1);

		cat = new Categoria(nombre);
		return cat;
	}
}