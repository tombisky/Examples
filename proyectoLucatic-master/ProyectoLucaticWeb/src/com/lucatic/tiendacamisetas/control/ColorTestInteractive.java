package com.lucatic.tiendacamisetas.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.lucatic.tiendacamisetas.dao.ColorDAOFactory;
import com.lucatic.tiendacamisetas.dao.DAOException;
import com.lucatic.tiendacamisetas.dao.GestorDAO;
import com.lucatic.tiendacamisetas.model.Color;

public class ColorTestInteractive {

	public void IniciarMenuColor() {
		ColorDAOFactory factory = new ColorDAOFactory();
		boolean timeToQuitColor = false;
		try (GestorDAO dao = factory.createColorDAO()) {
			do {
				try {
					timeToQuitColor = executeMenuColor(dao);
				} catch (DAOException e) {
					System.out.println("Error " + e.getClass().getName());
					System.out.println("Message: " + e.getMessage());
				}
			} while (!timeToQuitColor);
		} catch (IOException e) {
			System.out.println("Error " + e.getClass().getName() + " , quiting.");
			System.out.println("Message: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error closing resource " + e.getClass().getName());
			System.out.println("Message: " + e.getMessage());
		}
	}

	// MENÚ***************************************************************************
	public static boolean executeMenuColor(GestorDAO dao) throws IOException, DAOException {
		int idColor;
		Color col;
		String action;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\n\n----Tabla Color----");
		System.out.println("\n\n[C]rear | [E]ncontrar | [M]odificar | [B]orrar | [L]istar | [A]tras: \n");
		//******************  ----ERROR---  **********************
		action = in.readLine();
		if ((action.length() == 0) || action.toUpperCase().charAt(0) == 'A') {
           
			return true;
		}

		switch (action.toUpperCase().charAt(0)) {
		
		// MENÚ CREAR COLOR **************************************************
		case 'C':
			col = inputColor(in, null, true);
			dao.addItem(col);

			System.out.println("\n\nColor " + col.getNombreColor() + " introducido correctamente:");

			break;

		// MENÚ LEER COLOR ****************************************************
		case 'E':
			System.out.println("Por favor, introduce el valor del ID a localizar:");
			idColor = new Integer(in.readLine().trim());

			col = (Color)dao.findById(idColor);
			if (col != null) {
				System.out.println(col + "\n");
			} else {
				System.out.println("\n\nColor " + idColor + " no encontrado");
				}

			break;

		// MENÚ MODIFICAR COLOR ***********************************************
		case 'M':

			System.out.println("Por favor, introduce el valor del ID a modificar:");
			idColor = new Integer(in.readLine().trim());

			col = null;
			col = (Color)dao.findById(idColor);
			if (col == null) {
				System.out.println("\n\nColor " + idColor + " no encontrado");
			}
			col = inputColor(in, col);
			dao.updateItem(col);
			System.out.println("Color modificado a [" + col.getNombreColor() + "] correctamente.");
			break;

		// MENÚ BORRAR COLOR ***************************************************
		case 'B':
			System.out.println("Introduce el ID que desea borrar: ");
			idColor = new Integer(in.readLine().trim());

			dao.removeItem(idColor);
			System.out.println("ID " + idColor + " borrada correctamente.");
			break;

		// MENÚ LISTAR COLORES
		/*case 'L':
			Color[] allColor = dao.getAllTablas();
			for (Color colour : allColor) {
				System.out.println(colour + "\n");
			}
			break;
			*/
		}

		return false;
	}
	// *************************************************************************************
	

	// MODIFICA UN COLOR********************************************************************
	public static Color inputColor(BufferedReader in, Color colDefaults) throws IOException {
		int id = -1;
		String nombre;
		Color colours;

		try {
			id = colDefaults.getIdColor();
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
		id = colDefaults.getIdColor();
		String prompt = "Introduce el valor que modificará el color"
				+ ((colDefaults == null) ? "" : " [" + colDefaults.getNombreColor() + "]");

		do {
			System.out.println(prompt + " : ");
			nombre = in.readLine().trim();
			if (nombre.equals("") && colDefaults != null) {
				nombre = colDefaults.getNombreColor();
			}
			if (nombre.length() < 1) {
				System.out.println("Por favor, introduce un color válido");
			}
		} while (nombre.length() < 1);

		colours = new Color(id, nombre);
		return colours;
	}
	// ***************************************************************************************
	

	// CREA UN NUEVO COLOR********************************************************************

	
	public static Color inputColor(BufferedReader in, Color colDefaults, boolean newColor) throws IOException {

		String nombre;
		Color col;
		String prompt = "Introduce un color" + ((colDefaults == null) ? "" : " [" + colDefaults.getNombreColor() + "]");

		do {
			System.out.println(prompt + " : ");
			nombre = in.readLine().trim();
			if (nombre.equals("") && colDefaults != null) {
				nombre = colDefaults.getNombreColor();
			}
			if (nombre.length() < 1) {
				System.out.println("Por favor, introduce un color válido");
			}
		} while (nombre.length() < 1);

		col = new Color(nombre);
		return col;
	}
}