package com.lucatic.agenda.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucatic.agenda.beans.Direccion;
import com.lucatic.agenda.dao.DireccionesDAO;
@Service
@Transactional
public class DireccionServiceImpl implements DireccionService {
	@Autowired
	private DireccionesDAO DireccionesDAO;

	public List<Direccion> list() {
		// TODO Auto-generated method stub
		return DireccionesDAO.list();
	}

	public Direccion get(int id) {
		// TODO Auto-generated method stub
		return DireccionesDAO.get(id);
	}

	public void saveOrUpdate(Direccion direccion) {
		DireccionesDAO.saveOrUpdate(direccion);

	}

	public void delete(int id) {
		DireccionesDAO.delete(id);

	}

}
