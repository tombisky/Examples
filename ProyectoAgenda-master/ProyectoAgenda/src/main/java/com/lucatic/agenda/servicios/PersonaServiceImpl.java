package com.lucatic.agenda.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucatic.agenda.beans.Persona;
import com.lucatic.agenda.dao.PersonaDAO;

@Service
@Transactional
public class PersonaServiceImpl implements PersonaService {
	@Autowired
	private PersonaDAO personaDAO;

	public Persona get(int id) {
		// TODO Auto-generated method stub
		return personaDAO.get(id);
	}

	public void saveOrUpdate(Persona persona) {
		personaDAO.saveOrUpdate(persona);

	}

	public void delete(int id) {
		personaDAO.delete(id);

	}

	public List<Persona> list() {
		return personaDAO.list();
	}

	public List<Persona> getNombre(String nombre) {
		return personaDAO.getNombre(nombre);
	}

	public List<Persona> getTelefono(int telefono) {
		return personaDAO.getTelefono(telefono);
	}

	public List<Persona> getDireccion(String direccion) {
		return personaDAO.getDireccion(direccion);
	}


}
