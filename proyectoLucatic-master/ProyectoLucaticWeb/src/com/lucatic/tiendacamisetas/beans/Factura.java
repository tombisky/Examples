package com.lucatic.tiendacamisetas.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.lucatic.tiendacamisetas.model.TipoPago;

public class Factura {

	// Creamos una lista de detalles, ya que factura se compone principalmente
	// de detalles
	private List<Detalle> lista = new ArrayList<Detalle>();
	// tenemos el id del cliente que realiza la compra y genera la factura
	private int idCliente;
	// indicamos el tipo de pago (en este caso pertenece a un enum)
	private TipoPago TipoPago;
	// indicamos la fecha en la que se realiza la factura
	private Date fecha;
	// indicamos el precio total de la factura (con el metodo que tenemos mas
	// abajo para ir sumando el precio de todos los detalles)
	private float precioTotal;

	// constructor basico de factura
	public Factura() {

	}

	// constructor completo de factura en el cual asignamos los valores
	public Factura(List<Detalle> detalles, int idCliente, TipoPago tipoPago, Date fecha) {
		this.lista = detalles;
		this.idCliente = idCliente;
		this.TipoPago = tipoPago;
		this.fecha = fecha;
		// para asignar el precio total llamamos a nuestro metodo para que lo
		// calcule y nos devuelva el total
		this.precioTotal = calcularTotal(detalles);
	}

	public float calcularTotal(List<Detalle> detalles) {
		float total = 0;
		// miramos la lista de 1 en 1 y vamos sumando los precios en nuestro
		// total el cual está inicializado a 0
		for (Detalle a : detalles) {
			total = +a.getPrecio();
		}
		// devolvemos el valor de total
		return total;
	}

	// sets and gets de la clase factura
	public List<Detalle> getLista() {
		return lista;
	}

	public void setLista(List<Detalle> lista) {
		this.lista = lista;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public TipoPago getTipoPago() {
		return TipoPago;
	}

	public void setTipoPago(TipoPago tipoPago) {
		TipoPago = tipoPago;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((TipoPago == null) ? 0 : TipoPago.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + idCliente;
		result = prime * result + ((lista == null) ? 0 : lista.hashCode());
		result = prime * result + Float.floatToIntBits(precioTotal);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		if (TipoPago != other.TipoPago)
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (idCliente != other.idCliente)
			return false;
		if (lista == null) {
			if (other.lista != null)
				return false;
		} else if (!lista.equals(other.lista))
			return false;
		if (Float.floatToIntBits(precioTotal) != Float.floatToIntBits(other.precioTotal))
			return false;
		return true;
	}

	/*
	 * metodo mostrar lista para usarlo en nuestro toString y que nos muestre
	 * todos los productos de todos los detalles cargandolos en un StringBuffer y devolviendolo
	 */
	public String listadoDetalles() {
		StringBuffer cadena = new StringBuffer();
		for (Detalle d : this.lista) {
			System.out.println("Descripcion producto: " + d.getProducto().getDescripcion() + " Id producto: "
					+ d.getProducto().getIdProducto()+d.getCantidad());
		}
		return cadena.toString();
	}
	/*
	 * Mostramos por panntalla los datos de factura utilizando nunestro metodo listadoDetalles para mostrar todos los detalles
	 */
	@Override
	public String toString() {
		return "Factura : " + listadoDetalles() + "idCliente=" + idCliente + ", TipoPago=" + TipoPago + ", fecha="
				+ fecha + ", precioTotal=" + precioTotal;
	}

}
