package com.lucatic.agenda.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucatic.agenda.beans.Telefono;
import com.lucatic.agenda.dao.TelefonoDAO;

@Service
public class TelefonoServiceImpl {
	@Autowired
	private TelefonoDAO telefonoDAO;

	public List<Telefono> list() {
		// TODO Auto-generated method stub
		return telefonoDAO.list();
	}

	public Telefono get(int id) {
		// TODO Auto-generated method stub
		return (Telefono) telefonoDAO.get(id);
	}

	public void saveOrUpdate(Telefono telefono) {
		telefonoDAO.saveOrUpdate(telefono);

	}

	public void delete(int id) {
		telefonoDAO.delete(id);

	}

}
