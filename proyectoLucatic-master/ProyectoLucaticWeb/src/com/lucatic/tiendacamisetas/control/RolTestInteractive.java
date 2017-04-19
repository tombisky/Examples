package com.lucatic.tiendacamisetas.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.lucatic.tiendacamisetas.dao.DAOException;
import com.lucatic.tiendacamisetas.dao.GestorDAO;
import com.lucatic.tiendacamisetas.dao.RolDAOFactory;
import com.lucatic.tiendacamisetas.model.Rol;

public class RolTestInteractive {

	public void IniciarMenuRol() {
		RolDAOFactory factory = new RolDAOFactory();
		boolean timeToQuitRol = false;
		try (GestorDAO dao = factory.createRolDAO()) {
			do {
				try {
					timeToQuitRol = executeMenuRol(dao);
				} catch (DAOException e) {
					System.out.println("Error " + e.getClass().getName());
					System.out.println("Message: " + e.getMessage());
				}
			} while (!timeToQuitRol);
		} catch (IOException e) {
			System.out.println("Error " + e.getClass().getName() + " , quiting.");
			System.out.println("Message: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error closing resource " + e.getClass().getName());
			System.out.println("Message: " + e.getMessage());
		}
	}

	// MENÚ***************************************************************************
	public static boolean executeMenuRol(GestorDAO dao) throws IOException, DAOException {
		int idRol;
		Rol rol;
		String action;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("\n\n----Tabla Rol----");
		System.out.println("\n\n[C]rear | [E]ncontrar | [M]odificar | [B]orrar | [L]istar | [A]tras: \n");
		action = in.readLine();
		if ((action.length() == 0) || action.toUpperCase().charAt(0) == 'A') {
            
			return true;
		}

		switch (action.toUpperCase().charAt(0)) {
		
		// MENÚ CREAR ROL **************************************************
		case 'C':
			rol = inputRol(in, null, true);
			dao.addItem(rol);

			System.out.println("\n\nRol " + rol.getNombreRol() + " introducida correctamente:");

			break;

		// MENÚ LEER ROL ****************************************************
		case 'E':
			System.out.println("Por favor, introduce el valor del ID a localizar:");
			idRol = new Integer(in.readLine().trim());

			rol = (Rol)dao.findById(idRol);
			if (rol != null) {
				System.out.println(rol + "\n");
			} else {
				System.out.println("\n\nRol " + idRol + " no encontrado");
				}

			break;

		// MENÚ MODIFICAR ROL ***********************************************
		case 'M':

			System.out.println("Por favor, introduce el valor del ID a modificar:");
			idRol = new Integer(in.readLine().trim());

			rol = null;
			rol = (Rol)dao.findById(idRol);
			if (rol == null) {
				System.out.println("\n\nRol " + idRol + " no encontrado");
			}
			rol = inputRol(in, rol);
			dao.updateItem(rol);
			System.out.println("Rol modificado a [" + rol.getNombreRol() + "] correctamente.");
			break;

		// MENÚ BORRAR ROL ***************************************************
		case 'B':
			System.out.println("Introduce el ID que desea borrar: ");
			idRol = new Integer(in.readLine().trim());

			dao.removeItem(idRol);
			System.out.println("ID " + idRol + " borrado correctamente.");
			break;

		// MENÚ LISTAR COLORES
		/*case 'L':
			Rol[] allRol = dao.getAllTablas();
			for (Rol roll : allRol) {
				System.out.println(roll + "\n");
			}
			break;
			*/
		}
		

		return false;
	}
	// *************************************************************************************
	

	// MODIFICA UN ROL ********************************************************************
	public static Rol inputRol(BufferedReader in, Rol rolDefaults) throws IOException {
		int id = -1;
		String nombre;
		Rol roles;

		try {
			id = rolDefaults.getIdRol();
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
		id = rolDefaults.getIdRol();
		String prompt = "Introduce el valor que modificará la categoría"
				+ ((rolDefaults == null) ? "" : " [" + rolDefaults.getNombreRol() + "]");

		do {
			System.out.println(prompt + " : ");
			nombre = in.readLine().trim();
			if (nombre.equals("") && rolDefaults != null) {
				nombre = rolDefaults.getNombreRol();
			}
			if (nombre.length() < 1) {
				System.out.println("Por favor, introduce un color válido");
			}
		} while (nombre.length() < 1);

		roles = new Rol(id, nombre);
		return roles;
	}
	// ***************************************************************************************
	

	// CREA UN ROL ***************************************************************************

	
	public static Rol inputRol(BufferedReader in, Rol rolDefaults, boolean newRol) throws IOException {

		String nombre;
		Rol rol;
		String prompt = "Introduce un rol" + ((rolDefaults == null) ? "" : " [" + rolDefaults.getNombreRol() + "]");

		do {
			System.out.println(prompt + " : ");
			nombre = in.readLine().trim();
			if (nombre.equals("") && rolDefaults != null) {
				nombre = rolDefaults.getNombreRol();
			}
			if (nombre.length() < 1) {
				System.out.println("Por favor, introduce un color válido");
			}
		} while (nombre.length() < 1);

		rol = new Rol(nombre);
		return rol;
	}
}