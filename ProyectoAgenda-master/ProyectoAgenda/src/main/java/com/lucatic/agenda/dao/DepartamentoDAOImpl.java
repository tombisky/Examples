package com.lucatic.agenda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.lucatic.agenda.beans.Departamento;

@Repository
public class DepartamentoDAOImpl implements DepartamentoDAO{
	@Autowired
	private SessionFactory sessionFactory;

	public  DepartamentoDAOImpl() {

	}

	public DepartamentoDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	//Devuelve un listado de todos los departamentos sin repeticiones
	@Transactional
	public List<Departamento> list() {
		@SuppressWarnings("unchecked")
		List<Departamento> listDepartamento = (List<Departamento>) sessionFactory.getCurrentSession().createCriteria(Departamento.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listDepartamento;
	}
	//Guarda o actualiza un registro si no hay un item con el mismo id se guarda en un nuevo registro, y si ya hay una concurrencia del ID actualizará dichos datos
	@Transactional
	public void saveOrUpdate(Departamento item) {
		sessionFactory.getCurrentSession().saveOrUpdate(item);
	}

	//Borra según el id
	@Transactional
	public void delete(int id) {
		Departamento userToDelete = new Departamento();
		userToDelete.setIddepartamento(id);
		sessionFactory.getCurrentSession().delete(userToDelete);
	}

	//Obtendremos un departamento según el id que indiquemos (mostramos solamente el primero de los datos que obtenemos)
	@Transactional
	public Departamento get(int id) {
		String hql = "from Departamento where iddepartamento=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Departamento> listDepartamento = (List<Departamento>) query.list();

		if (listDepartamento != null && !listDepartamento.isEmpty()) {
			return listDepartamento.get(0);
		}

		return null;
	}

	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
