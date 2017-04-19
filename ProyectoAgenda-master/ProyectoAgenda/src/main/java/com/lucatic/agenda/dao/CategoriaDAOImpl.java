package com.lucatic.agenda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucatic.agenda.beans.Categoria;


@Repository
public class CategoriaDAOImpl implements CategoriaDAO{
	@Autowired
	private SessionFactory sessionFactory;

	public CategoriaDAOImpl() {

	}
	//creamos una categoria con una sesionFactory asignada.
	public CategoriaDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//Metodo para listar todas las categorias sin repetir datos.
	@Transactional
	public List<Categoria> list() {
		@SuppressWarnings("unchecked")
		List<Categoria> listUser = (List<Categoria>) sessionFactory.getCurrentSession().createCriteria(Categoria.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listUser;
	}

	//Guardar un registro en la base de datos o actualizarlo si ya se encuentra en el
	@Transactional
	public void saveOrUpdate(Categoria item) {
		sessionFactory.getCurrentSession().saveOrUpdate(item);
	}

	//Metodo con el cual borraremos una categoria de la base de datos indicando cual es el id (asignamos el id a la nueva categoria y despues se borrará el registro que coincida con
	//el mismo id
	
	@Transactional
	public void delete(int id) {
		Categoria userToDelete = new Categoria();
		userToDelete.setIdcategorias(id);
		sessionFactory.getCurrentSession().delete(userToDelete);
	}

	//Obtendremos una categoría según el id que indicamos y mostramos el primero de los resusltados que obtenemos 
	
	@Transactional
	public Categoria get(int id) {
		String hql = "from Categoria where idcategorias=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Categoria> listPersona = (List<Categoria>) query.list();

		if (listPersona != null && !listPersona.isEmpty()) {
			return listPersona.get(0);
		}

		return null;
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}
}
