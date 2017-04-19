package com.lucatic.agenda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucatic.agenda.beans.Telefono;
@Repository
public class TelefonoDAOImpl implements TelefonoDAO{
	@Autowired	
	private SessionFactory sessionFactory;

	public TelefonoDAOImpl() {

	}

	public TelefonoDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//Mostramos una lista detelefonos sin repeticiones
	@Transactional
	public List<Telefono> list() {
		@SuppressWarnings("unchecked")
		List<Telefono> listDepartamento = (List<Telefono>) sessionFactory.getCurrentSession().createCriteria(Telefono.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listDepartamento;
	}

	//Guardamos o actualizamos un telefono
	@Transactional
	public void saveOrUpdate(Telefono item) {
		sessionFactory.getCurrentSession().saveOrUpdate(item);
	}

	//Borramos un telefono por ID 
	@Transactional
	public void delete(int id) {
		Telefono userToDelete = new Telefono();
		userToDelete.setIdtelefonos(id);
		sessionFactory.getCurrentSession().delete(userToDelete);
	}

	//Obtenemos un telefono por un ID
	@Transactional
	public Telefono get(int id) {
		String hql = "from Telefono where idtelefonos=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Telefono> listTelefono = (List<Telefono>) query.list();

		if (listTelefono != null && !listTelefono.isEmpty()) {
			return listTelefono.get(0);
		}

		return null;
	}

	
	public void close() {
		// TODO Auto-generated method stub
		
	}
}
