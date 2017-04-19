package com.lucatic.agenda.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucatic.agenda.beans.Departamento;
import com.lucatic.agenda.dao.DepartamentoDAO;

@Service
@Transactional

public class DepartamentoServiceImpl implements DepartamentoService {
	@Autowired
	private DepartamentoDAO DepartamentoDAO;

	public List<Departamento> list() {
		// TODO Auto-generated method stub
		return DepartamentoDAO.list();
	}

	public Departamento get(int id) {
		// TODO Auto-generated method stub
		return DepartamentoDAO.get(id);
	}

	public void saveOrUpdate(Departamento departamento) {
		DepartamentoDAO.saveOrUpdate(departamento);

	}

	public void delete(int id) {
		DepartamentoDAO.delete(id);

	}

}
