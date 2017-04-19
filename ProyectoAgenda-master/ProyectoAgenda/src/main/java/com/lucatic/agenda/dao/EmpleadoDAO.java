package com.lucatic.agenda.dao;
// Generated 30-mar-2017 13:56:25 by Hibernate Tools 5.1.0.Alpha1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;


import com.lucatic.agenda.beans.Empleado;


/**
 * Home object for domain model class Empleados.
 * @see com.lucatic.agenda.dao.Empleados
 * @author Hibernate Tools
 */
public interface EmpleadoDAO extends  GestorDAO<Empleado>{
	//Metodo para listar empleados según su departamento
	public List<Empleado> getDep(String dep);
	//listamos Empleados segun su categoría
	public List<Empleado> getCat(String cate);
	//Listamos según una franja salaríal
	public List<Empleado> getSalario(int salario1, int salario2);
}
