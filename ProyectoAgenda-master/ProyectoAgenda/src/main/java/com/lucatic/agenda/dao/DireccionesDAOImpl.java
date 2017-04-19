package com.lucatic.agenda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.lucatic.agenda.beans.Direccion;

@Repository
public class DireccionesDAOImpl implements DireccionesDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public  DireccionesDAOImpl() {

	}

	public DireccionesDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//Mostramos un listado de direcciones sin repeticiones
	@Transactional
	public List<Direccion> list() {
		@SuppressWarnings("unchecked")
		List<Direccion> listDepartamento = (List<Direccion>) sessionFactory.getCurrentSession().createCriteria(Direccion.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listDepartamento;
	}

	//guardamos o actualizamos un registro en la base de datos
	@Transactional
	public void saveOrUpdate(Direccion item) {
		sessionFactory.getCurrentSession().saveOrUpdate(item);
	}

	//borramos un registro de la base de datos indicando el ID
	@Transactional
	public void delete(int id) {
		Direccion userToDelete = new Direccion();
		userToDelete.setIddirecciones(id);
		sessionFactory.getCurrentSession().delete(userToDelete);
	}

	//Obtendremos una direccion buscando por un ID
	@Transactional
	public Direccion get(int id) {
		String hql = "from Direccion where iddirecciones=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Direccion> listDireccion = (List<Direccion>) query.list();

		if (listDireccion != null && !listDireccion.isEmpty()) {
			return listDireccion.get(0);
		}

		return null;
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}
}
