package com.lucatic.tiendacamisetas.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.lucatic.tiendacamisetas.control.*;
import com.lucatic.tiendacamisetas.dao.DAOException;

public class MenuPrincipal {

	public void IniciarPrograma() {

		boolean timeToQuit = false;
		try {
			do {
				try {
					
					timeToQuit = executeMenu();

				} catch (DAOException e) {

					System.out.println("Error " + e.getClass().getName());
					System.out.println("Message: " + e.getMessage());
				}
			} while (!timeToQuit);
		} catch (IOException e) {
			System.out.println("Error " + e.getClass().getName() + " , quiting.");
			System.out.println("Message: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error closing resource " + e.getClass().getName());
			System.out.println("Message: " + e.getMessage());
		}
	}

	public static boolean executeMenu() throws IOException, DAOException {
		String action;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
		System.out.println("\n----Base de Datos de la Tienda----");
		System.out.println("\n\n[C]olor | [T]alla | [G]énero | C[A]tegoría | Tipo de [P]ago | [R]ol | [D]etalle | [S]alir: ");
		action = in.readLine();
		if ((action.length() == 0) || action.toUpperCase().charAt(0) == 'S') {
			System.out.println("\n\n---Fin de la sesión---");
			return true;
		}

		switch (action.toUpperCase().charAt(0)) {
		// ACCEDE A MENÚ COLOR **************************************************
		case 'C':
			new ColorTestInteractive().IniciarMenuColor();

			break;

		// ACCEDE A MENÚ TALLA ****************************************************
		case 'T':
			new TallaTestInteractive().IniciarMenuTalla();

			break;
			

	    // ACCEDE A MENÚ GENERO ****************************************************
		case 'G':
			new GeneroTestInteractive().IniciarMenuGenero();

			break;
				
		// ACCEDE A MENÚ CATEGORIA ****************************************************
		case 'A':
			new CategoriaTestInteractive().IniciarMenuCategoria();

			break;
			
		// ACCEDE A MENÚ GENERO ****************************************************
		case 'P':
			new TipoPagoTestInteractive().IniciarMenuTipoPago();

			break;
			
		// ACCEDE A MENÚ ROL ****************************************************
		case 'R':
			new RolTestInteractive().IniciarMenuRol();

			break;
		case 'D':
			new DetalleTestInteractive().iniciarMenuDetalle();
		}

		return false;
	}

}
