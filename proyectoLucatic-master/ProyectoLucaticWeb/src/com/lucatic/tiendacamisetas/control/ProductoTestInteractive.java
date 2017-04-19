package com.lucatic.tiendacamisetas.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;

import com.lucatic.tiendacamisetas.beans.Camiseta;
import com.lucatic.tiendacamisetas.beans.Producto;
import com.lucatic.tiendacamisetas.dao.DAOException;
import com.lucatic.tiendacamisetas.dao.ProductoDAO;
import com.lucatic.tiendacamisetas.dao.ProductoDAOFactory;
import com.lucatic.tiendacamisetas.model.Categoria;
import com.lucatic.tiendacamisetas.model.Color;
import com.lucatic.tiendacamisetas.model.Genero;
import com.lucatic.tiendacamisetas.model.Talla;

public class ProductoTestInteractive {
	public void IniciarMenuProducto() {
		ProductoDAOFactory factory = new ProductoDAOFactory();

        boolean timeToQuit = false;
        try (
        		ProductoDAO dao = factory.createProductoDAO();
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                try {
                    timeToQuit = executeMenu(in, dao);
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
	
	public static boolean executeMenu(BufferedReader in, ProductoDAO dao) throws IOException, DAOException {
		 Producto producto;
	        String action;
	        int id;

	        System.out.println("\n\n[C]reate | [R]ead | [U]pdate | [D]elete | [L]ist | [Q]uit: ");
	        action = in.readLine();
	        if ((action.length() == 0) || action.toUpperCase().charAt(0) == 'Q') {
	            return true;
	        }

	        switch (action.toUpperCase().charAt(0)) {
	            // Create a new employee record
	            case 'C':
	               // producto = inputProduct(in);
	                Categoria categoria = new Categoria(1,"camiseta");
	                Genero genero = new Genero(1,"masculino");
	                Talla talla = new Talla(1,"XS");
	                Color color = new Color(1,"Amarillo");
	                
	                Producto p = new Camiseta("camiseta tirantes",categoria,genero,talla,color,39,"camiseta rota","jumanji");
	                dao.addItem(p);
	                System.out.println("Successfully added Producto Record: " + p.getIdProducto());
	                System.out.println("\n\nCreated " + p);
	                break;

	            // Display an product record
	            case 'R':
	                System.out.println("Enter int value for producto id: ");
	                id = new Integer(in.readLine().trim());

	                // Find this product record
	                producto = dao.findById(id);
	                if (producto != null) {
	                    System.out.println(producto + "\n");
	                } else {
	                    System.out.println("\n\nProduct " + id + " not found");
	                    break;
	                }

	                break;

	            // Update an existing product record    
	            case 'U':
	                System.out.println("Enter int value for product id: ");
	                id = new Integer(in.readLine().trim());
	                // Find this Product record
	                Categoria categoria2 = new Categoria(1,"camiseta");
	                Genero genero2 = new Genero(1,"masculino");
	                Talla talla2 = new Talla(1,"XS");
	                Color color2 = new Color(1,"Amarillo");
	                
	                Producto p2 = new Camiseta(id,"camiseta tirantes",categoria2,genero2,talla2,color2,39,23,"camiseta rota","jumanji");
	                producto = dao.findById(id);
	                if (producto == null) {
	                    System.out.println("\n\nProduct " + id + " not found");
	                    break;
	                }
	                // Go through the record to allow changes
	                //p2.setIdProducto(id);
	                //producto = inputProduct(in, producto);
	                dao.updateItem(p2);
	                System.out.println("Successfully updated Product Record: " + p2.getIdProducto());
	                break;

	            // Delete an product record
	            case 'D':
	                System.out.println("Enter int value for product id: ");
	                id = new Integer(in.readLine().trim());

	                // Find this Product record                 
	                dao.removeItem(id);
	                System.out.println("Deleted Product " + id);
	                break;

	            // Display a list (Read the records) of Product
	            case 'L':
	            	int tall = new Integer(in.readLine().trim());
	                ArrayList<Producto> allProd = dao. findCamisetaByColor(tall);
	                for (Producto product : allProd) {
	                    System.out.println(product + "\n");
	                }
	                break;
	        }

	        return false;
	    }

	    public static Producto inputProduct(BufferedReader in) throws IOException {
	        return inputProduct(in, null, true);
	    }

	    public static Producto inputProduct(BufferedReader in, Producto empDefaults) throws IOException {
	        return inputProduct(in, empDefaults, false);
	    }

	    public static Producto inputProduct(BufferedReader in, Producto empDefaults, boolean newProduct) throws IOException {
	    	NumberFormat nf = NumberFormat.getCurrencyInstance();
	    	float precio;
	    	int id = -1;
	    	String descripcion;
	    	String dibujo;
	    	String nombre;
	    	
	    	Producto producto;

	        /*if (newProduct) {
	            do {
	                System.out.println("Enter int value for product id: ");
	                try {
	                    id = new Integer(in.readLine().trim());
	                    if (id < 0) {
	                        System.out.println("Please retry with a valid positive integer id");
	                    }
	                } catch (NumberFormatException e) {
	                    System.out.println("Please retry with a valid positive integer id");
	                }
	            } while (id < 0);
	        } else {
	            id = empDefaults.getId();
	            System.out.println("Modify the fields of Employee record: " + id
	                    + ". Press return to accept current value.");
	        }*/
	    	

	        String prompt = "Enter value for product descripcion" + ((empDefaults == null) ? "" : " [" + empDefaults.getDescripcion() + "]");

	        do {
	            System.out.println(prompt + " : ");
	            descripcion = in.readLine().trim();
	            if (descripcion.equals("") && empDefaults != null) {
	            	descripcion = empDefaults.getDescripcion();
	            }
	            if (descripcion.length() < 1) {
	                System.out.println("Please retry with a valid first name");
	            }
	        } while (descripcion.length() < 1);


	        prompt = "Enter value for product dibujo" + ((empDefaults == null) ? "" : " [" + ((Camiseta) empDefaults).getDibujo() + "]");

	        do {
	            System.out.println(prompt + " : ");
	            dibujo = in.readLine().trim();
	            if (dibujo.equals("") && empDefaults != null) {
	            	dibujo = ((Camiseta) empDefaults).getDibujo();
	            }
	            if (dibujo.length() < 1) {
	                System.out.println("Please retry with a valid dibujo");
	            }
	        } while (dibujo.length() < 1);
	        
	        prompt = "Enter value for product nombre" + ((empDefaults == null) ? "" : " [" + ((Camiseta) empDefaults).getNombre() + "]");
	        do {
	            System.out.println(prompt + " : ");
	            nombre = in.readLine().trim();
	            if (nombre.equals("") && empDefaults != null) {
	                nombre = ((Camiseta) empDefaults).getNombre();
	            }
	            if (nombre.length() < 1) {
	                System.out.println("Please retry with a valid nombre");
	            }
	        } while (nombre.length() < 1);

	        
	        prompt = "Enter float value for product precio"
	                + ((empDefaults == null) ? "" : " [" + nf.format((float) empDefaults.getPrecio()) + "]");
	        do {
	            System.out.println(prompt + " : ");
	            precio = 0;
	            try {
	                String amt = in.readLine().trim();
	                if (!amt.equals("")) {
	                    precio = Float.parseFloat(amt);
	                }
	                if (precio == 0 && empDefaults != null) {
	                    precio = empDefaults.getPrecio();
	                }
	                if (precio < 0) {
	                    System.out.println("Please retry with a positive salary");
	                    precio = 0;
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("Please retry with a valid float salary: " + e.getMessage());
	            }
	        } while (precio == 0);

	        // Create an Employee object
	        Talla talla = new Talla(1,"XS");
	        Categoria categoria = new Categoria(1,"Camiseta");
	        Color col = new Color(1,"Amarillo");
	        Genero genero = new Genero(1,"Masculino");
	        
	        producto = new Camiseta(id, descripcion,categoria,genero,talla,col,precio,90,nombre,dibujo);
	        return producto;
	    }
}
