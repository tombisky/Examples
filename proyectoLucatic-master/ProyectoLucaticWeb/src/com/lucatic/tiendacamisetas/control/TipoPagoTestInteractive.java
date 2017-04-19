package com.lucatic.tiendacamisetas.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.lucatic.tiendacamisetas.dao.DAOException;
import com.lucatic.tiendacamisetas.dao.GestorDAO;
import com.lucatic.tiendacamisetas.dao.TipoPagoDAOFactory;
import com.lucatic.tiendacamisetas.model.TipoPago;

public class TipoPagoTestInteractive {

	public void IniciarMenuTipoPago() {
		TipoPagoDAOFactory factory = new TipoPagoDAOFactory();
		boolean timeToQuitTipoPago = false;
		try (GestorDAO dao = factory.createTipoPagoDAO()) {
			do {
				try {
					timeToQuitTipoPago = executeMenuTipoPago(dao);
				} catch (DAOException e) {
					System.out.println("Error " + e.getClass().getName());
					System.out.println("Message: " + e.getMessage());
				}
			} while (!timeToQuitTipoPago);
		} catch (IOException e) {
			System.out.println("Error " + e.getClass().getName() + " , quiting.");
			System.out.println("Message: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error closing resource " + e.getClass().getName());
			System.out.println("Message: " + e.getMessage());
		}
	}

	// MENÚ***************************************************************************
	public static boolean executeMenuTipoPago(GestorDAO dao) throws IOException, DAOException {
		int idTipoPago;
		TipoPago tip;
		String action;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("\n\n----Tabla Tipo de Pago----");
		System.out.println("\n\n[C]rear | [E]ncontrar | [M]odificar | [B]orrar | [L]istar | [A]tras: \n");
		action = in.readLine();
		if ((action.length() == 0) || action.toUpperCase().charAt(0) == 'A') {
           
			return true;
		}

		switch (action.toUpperCase().charAt(0)) {
		
		// MENÚ CREAR TIPO DE PAGO **************************************************
		case 'C':
			tip = inputTipoPago(in, null, true);
			dao.addItem(tip);

			System.out.println("\n\nTipo de pago " + tip.getNombreTipoPago() + " introducido correctamente:");

			break;

		// MENÚ LEER CATEGORIA ****************************************************
		case 'E':
			System.out.println("Por favor, introduce el valor del ID a localizar:");
			idTipoPago = new Integer(in.readLine().trim());

			tip = (TipoPago)dao.findById(idTipoPago);
			if (tip != null) {
				System.out.println(tip + "\n");
			} else {
				System.out.println("\n\nTipo de pago " + idTipoPago + " no encontrado");
				}

			break;

		// MENÚ MODIFICAR CATEGORIA ***********************************************
		case 'M':

			System.out.println("Por favor, introduce el valor del ID a modificar:");
			idTipoPago = new Integer(in.readLine().trim());

			tip = null;
			tip = (TipoPago)dao.findById(idTipoPago);
			if (tip == null) {
				System.out.println("\n\nTipo de pago " + idTipoPago + " no encontrado");
			}
			tip = inputTipoPago(in, tip);
			dao.updateItem(tip);
			System.out.println("Tipo de pago modificado a [" + tip.getNombreTipoPago() + "] correctamente.");
			break;

		// MENÚ BORRAR CATEGORIA ***************************************************
		case 'B':
			System.out.println("Introduce el ID que desea borrar: ");
			idTipoPago = new Integer(in.readLine().trim());

			dao.removeItem(idTipoPago);
			System.out.println("ID " + idTipoPago + " borrada correctamente.");
			break;

		// MENÚ LISTAR COLORES
		/*case 'L':
			TipoPago[] allTipoPago = dao.getAllTablas();
			for (TipoPago tipopago : allTipoPago) {
				System.out.println(tipopago + "\n");
			}
			break;
			*/
		}

		return false;
	}
	// *************************************************************************************
	

	// MODIFICA UNA CATEGORIA ********************************************************************
	public static TipoPago inputTipoPago(BufferedReader in, TipoPago tipDefaults) throws IOException {
		int id = -1;
		String nombre;
		TipoPago tips;

		try {
			id = tipDefaults.getIdTipoPago();
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
		id = tipDefaults.getIdTipoPago();
		String prompt = "Introduce el valor que modificará el tipo de pago"
				+ ((tipDefaults == null) ? "" : " [" + tipDefaults.getNombreTipoPago() + "]");

		do {
			System.out.println(prompt + " : ");
			nombre = in.readLine().trim();
			if (nombre.equals("") && tipDefaults != null) {
				nombre = tipDefaults.getNombreTipoPago();
			}
			if (nombre.length() < 1) {
				System.out.println("Por favor, introduce un color válido");
			}
		} while (nombre.length() < 1);

		tips = new TipoPago(id, nombre);
		return tips;
	}
	// ***************************************************************************************
	

	// CREA UNA NUEVA CATEGORIA ********************************************************************

	
	public static TipoPago inputTipoPago(BufferedReader in, TipoPago tipDefaults, boolean newTipoPago) throws IOException {

		String nombre;
		TipoPago tip;
		String prompt = "Introduce un tipo de pago" + ((tipDefaults == null) ? "" : " [" + tipDefaults.getNombreTipoPago() + "]");

		do {
			System.out.println(prompt + " : ");
			nombre = in.readLine().trim();
			if (nombre.equals("") && tipDefaults != null) {
				nombre = tipDefaults.getNombreTipoPago();
			}
			if (nombre.length() < 1) {
				System.out.println("Por favor, introduce un color válido");
			}
		} while (nombre.length() < 1);

		tip = new TipoPago(nombre);
		return tip;
	}
}