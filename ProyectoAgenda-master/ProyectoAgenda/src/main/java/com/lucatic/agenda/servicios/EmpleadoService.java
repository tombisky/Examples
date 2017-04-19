package com.lucatic.agenda.servicios;

import java.util.List;

import com.lucatic.agenda.beans.Empleado;



public interface EmpleadoService extends Service<Empleado>{

	public List<Empleado> getDep(String dep);
	
	public List<Empleado> getCat(String cate);
	
	public List<Empleado> getSalario(int salario1, int salario2);
	
}
