package com.lucatic.agenda.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucatic.agenda.beans.Categoria;
import com.lucatic.agenda.dao.CategoriaDAO;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {
	@Autowired
	private CategoriaDAO CategoriaDAO;

	public List<Categoria> list() {
		
		return CategoriaDAO.list();
	}

	public Categoria get(int id) {
		
		return CategoriaDAO.get(id);
	}

	public void saveOrUpdate(Categoria categoria) {
		CategoriaDAO.saveOrUpdate(categoria);

	}

	public void delete(int id) {
		CategoriaDAO.delete(id);

	}

}
