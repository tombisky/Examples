
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name="empleados" , catalog="agenda")
public class Empleado implements java.io.Serializable {

	private Integer idempleados;
	private Categoria categorias;
	private Departamento departamentos;
	private String codEmpleado;
	private String salario;
	private Date fechaAlta;
	private Set<Persona> personases = new HashSet<Persona>(0);

	public Empleado() {
	}

	public Empleado(String codEmpleado) {
		this.codEmpleado = codEmpleado;
	}

	public Empleado(Categoria categorias, Departamento departamentos, String codEmpleado, String salario,
			Date fechaAlta, Set<Persona> personases) {
		this.categorias = categorias;
		this.departamentos = departamentos;
		this.codEmpleado = codEmpleado;
		this.salario = salario;
		this.fechaAlta = fechaAlta;
		this.personases = personases;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idempleados", unique = true, nullable = false)
	public Integer getIdempleados() {
		return this.idempleados;
	}

	public void setIdempleados(Integer idempleados) {
		this.idempleados = idempleados;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCategoria")
	public Categoria getCategorias() {
		return this.categorias;
	}

	public void setCategorias(Categoria categorias) {
		this.categorias = categorias;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idDepartamento")
	public Departamento getDepartamentos() {
		return this.departamentos;
	}

	public void setDepartamentos(Departamento departamentos) {
		this.departamentos = departamentos;
	}

	@Column(name = "codEmpleado", nullable = false, length = 45)
	public String getCodEmpleado() {
		return this.codEmpleado;
	}

	public void setCodEmpleado(String codEmpleado) {
		this.codEmpleado = codEmpleado;
	}

	@Column(name = "salario", length = 45)
	public String getSalario() {
		return this.salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaAlta", length = 19)
	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empleados")
	public Set<Persona> getPersonases() {
		return this.personases;
	}

	public void setPersonases(Set<Persona> personases) {
		this.personases = personases;
	}

}
