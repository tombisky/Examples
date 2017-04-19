package com.lucatic.agenda.beans;

// Generated 30-mar-2017 13:55:39 by Hibernate Tools 5.1.0.Alpha1

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "personas", catalog = "agenda")
public class Persona implements java.io.Serializable {

	@Id
	@GeneratedValue
	@Column(name = "idpersonas")
	private Integer idpersonas;
	private Empleado empleados;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private Date fechaNacimiento;
	private Set<Direccion> direccioneses = new HashSet<Direccion>();
	private Set<Telefono> telefonoses = new HashSet<Telefono>();

	public Persona() {
	}

	public Persona(String nombre, String apellido1) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
	}

	public Persona(Empleado empleados, String nombre, String apellido1, String apellido2, String dni,
		Date fechaNacimiento, Direccion direccioneses, Telefono telefonoses) {
		this.empleados = empleados;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.direccioneses.add(direccioneses);
		this.telefonoses.add(telefonoses);
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idpersonas", unique = true, nullable = false)
	public Integer getIdpersonas() {
		return this.idpersonas;
	}

	public void setIdpersonas(Integer idpersonas) {
		this.idpersonas = idpersonas;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEmpleado")
	public Empleado getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(Empleado empleados) {
		this.empleados = empleados;
	}

	@Column(name = "nombre", nullable = false, length = 45)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "apellido1", nullable = false, length = 45)
	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	@Column(name = "apellido2", length = 45)
	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	@Column(name = "dni", length = 20)
	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaNacimiento", length = 10)
	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "personas")
	public Set<Direccion> getDireccioneses() {
		return this.direccioneses;
	}

	public void setDireccioneses(Set <Direccion> direccion) {
		this.direccioneses = direccion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "personas")
	public Set<Telefono> getTelefonoses() {
		return this.telefonoses;
	}

	public void setTelefonoses(Set <Telefono> telefonoses) {
		this.telefonoses= telefonoses;
	}

}
