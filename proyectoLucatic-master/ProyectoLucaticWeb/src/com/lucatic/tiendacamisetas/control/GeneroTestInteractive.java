package com.lucatic.tiendacamisetas.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.lucatic.tiendacamisetas.dao.DAOException;
import com.lucatic.tiendacamisetas.dao.GeneroDAOFactory;
import com.lucatic.tiendacamisetas.dao.GestorDAO;
import com.lucatic.tiendacamisetas.model.Genero;

public class GeneroTestInteractive {

	public void IniciarMenuGenero() {
		GeneroDAOFactory factory = new GeneroDAOFactory();
		boolean timeToQuitGenero = false;
		try (GestorDAO dao = factory.createGeneroDAO()) {
			do {
				try {
					timeToQuitGenero = executeMenuGenero(dao);
				} catch (DAOException e) {
					System.out.println("Error " + e.getClass().getName());
					System.out.println("Message: " + e.getMessage());
				}
			} while (!timeToQuitGenero);
		} catch (IOException e) {
			System.out.println("Error " + e.getClass().getName() + " , quiting.");
			System.out.println("Message: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error closing resource " + e.getClass().getName());
			System.out.println("Message: " + e.getMessage());
		}
	}

	// MENÚ***************************************************************************
	public static boolean executeMenuGenero(GestorDAO dao) throws IOException, DAOException {
		int idGenero;
		Genero gen;
		String action;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("\n\n----Tabla Género----");
		System.out.println("\n\n[C]rear | [E]ncontrar | [M]odificar | [B]orrar | [L]istar | [A]tras: \n");
		action = in.readLine();
		if ((action.length() == 0) || action.toUpperCase().charAt(0) == 'A') {
            
			return true;
		}

		switch (action.toUpperCase().charAt(0)) {
		
		// MENÚ CREAR GENERO **************************************************
		case 'C':
			gen = inputGenero(in, null, true);
			dao.addItem(gen);

			System.out.println("\n\nGénero " + gen.getNombreGenero() + " introducido correctamente:");

			break;

		// MENÚ LEER GENERO ****************************************************
		case 'E':
			System.out.println("Por favor, introduce el valor del ID a localizar:");
			idGenero = new Integer(in.readLine().trim());

			gen = (Genero)dao.findById(idGenero);
			if (gen != null) {
				System.out.println(gen + "\n");
			} else {
				System.out.println("\n\nGénero " + idGenero + " no encontrado");
				}

			break;

		// MENÚ MODIFICAR GENERO ***********************************************
		case 'M':

			System.out.println("Por favor, introduce el valor del ID a modificar:");
			idGenero = new Integer(in.readLine().trim());

			gen = null;
			gen = (Genero)dao.findById(idGenero);
			if (gen == null) {
				System.out.println("\n\nGénero " + idGenero + " no encontrado");
			}
			gen = inputGenero(in, gen);
			dao.updateItem(gen);
			System.out.println("Género modificado a [" + gen.getNombreGenero() + "] correctamente.");
			break;

		// MENÚ BORRAR GENERO ***************************************************
		case 'B':
			System.out.println("Introduce el ID que desea borrar: ");
			idGenero = new Integer(in.readLine().trim());

			dao.removeItem(idGenero);
			System.out.println("ID " + idGenero + " borrada correctamente.");
			break;

		// MENÚ LISTAR GENEROS
		/*case 'L':
			Genero[] allGenero = dao.getAllTablas();
			for (Genero genero : allGenero) {
				System.out.println(genero + "\n");
			}
			break;
			*/
		}

		return false;
	}
	// *************************************************************************************
	

	// MODIFICA UN GENERO********************************************************************
	public static Genero inputGenero(BufferedReader in, Genero genDefaults) throws IOException {
		int id = -1;
		String nombre;
		Genero generos;

		try {
			id = genDefaults.getIdGenero();
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
		id = genDefaults.getIdGenero();
		String prompt = "Introduce el valor que modificará el género"
				+ ((genDefaults == null) ? "" : " [" + genDefaults.getNombreGenero() + "]");

		do {
			System.out.println(prompt + " : ");
			nombre = in.readLine().trim();
			if (nombre.equals("") && genDefaults != null) {
				nombre = genDefaults.getNombreGenero();
			}
			if (nombre.length() < 1) {
				System.out.println("Por favor, introduce un género válido");
			}
		} while (nombre.length() < 1);

		generos = new Genero(id, nombre);
		return generos;
	}
	// ***************************************************************************************
	

	// CREA UN NUEVO GENERO********************************************************************

	
	public static Genero inputGenero(BufferedReader in, Genero genDefaults, boolean newGenero) throws IOException {

		String nombre;
		Genero gen;
		String prompt = "Introduce un género" + ((genDefaults == null) ? "" : " [" + genDefaults.getNombreGenero() + "]");

		do {
			System.out.println(prompt + " : ");
			nombre = in.readLine().trim();
			if (nombre.equals("") && genDefaults != null) {
				nombre = genDefaults.getNombreGenero();
			}
			if (nombre.length() < 1) {
				System.out.println("Por favor, introduce un género válido");
			}
		} while (nombre.length() < 1);

		gen = new Genero(nombre);
		return gen;
	}
}