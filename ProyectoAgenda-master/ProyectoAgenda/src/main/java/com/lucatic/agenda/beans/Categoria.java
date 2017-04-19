package com.lucatic.agenda.beans;
// Generated 30-mar-2017 13:55:39 by Hibernate Tools 5.1.0.Alpha1

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name="categorias" , catalog="agenda")
public class Categoria implements java.io.Serializable {
	private Integer idcategorias;
	private String nombre;
	private String descripcion;
	private Set<Empleado> empleadoses = new HashSet<Empleado>(0);

	public Categoria() {
	}

	public Categoria(String nombre) {
		this.nombre = nombre;
	}

	public Categoria(String nombre, String descripcion, Set<Empleado> empleadoses) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.empleadoses = empleadoses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idcategorias", unique = true, nullable = false)
	public Integer getIdcategorias() {
		return this.idcategorias;
	}

	public void setIdcategorias(Integer idcategorias) {
		this.idcategorias = idcategorias;
	}

	@Column(name = "nombre", nullable = false, length = 45)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 45)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(targetEntity = Empleado.class, mappedBy = "categorias")
	public Set<Empleado> getEmpleadoses() {
		return this.empleadoses;
	}

	public void setEmpleadoses(Set<Empleado> empleadoses) {
		this.empleadoses = empleadoses;
	}

}
