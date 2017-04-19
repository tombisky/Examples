package com.lucatic.agenda.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucatic.agenda.beans.Persona;
@Repository
public class PersonaDAOImpl implements PersonaDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public PersonaDAOImpl() {

	}

	public PersonaDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// Listamos todas las personas sin duplicados
	@Transactional
	public List<Persona> list() {
		System.out.println("Apunto de entrar del List");
		@SuppressWarnings("unchecked")
		List<Persona> listUser = (List<Persona>) sessionFactory.getCurrentSession().createCriteria(Persona.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		System.out.println("Apunto de salir del List");
		return listUser;
	}

	// Guardamos o actualizamos el registro
	@Transactional
	public void saveOrUpdate(Persona item) {
		sessionFactory.getCurrentSession().saveOrUpdate(item);
	}

	// Borramos un registro
	@Transactional
	public void delete(int id) {
		Persona userToDelete = new Persona();
		userToDelete.setIdpersonas(id);
		sessionFactory.getCurrentSession().delete(userToDelete);
	}

	// Obtenemos un registro segun un ID
	@Transactional
	public Persona get(int id) {
		String hql = "from Persona where idpersonas=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Persona> listPersona = (List<Persona>) query.list();

		if (listPersona != null && !listPersona.isEmpty()) {
			return listPersona.get(0);
		}

		return null;
	}

	public void close() {
		// TODO Auto-generated method stub

	}

	// Buscamos por nombre
	public List<Persona> getNombre(String nombre) {
		String hql = "from Persona where nombre like %" + nombre + "%";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Persona> listPersona = (List<Persona>) query.list();

		if (listPersona != null && !listPersona.isEmpty()) {
			return listPersona;
		}

		return null;
	}

	// Buscamos por telefono
	public List<Persona> getTelefono(int telefono) {
		String hql = "from Persona a, Telefono b where a.idpersonas=idPersona and telefono like %" + telefono + "%";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Persona> listPersona = (List<Persona>) query.list();

		if (listPersona != null && !listPersona.isEmpty()) {
			return listPersona;
		}

		return null;
	}

	// Buscamos por direccion
	public List<Persona> getDireccion(String direccion) {
		String hql = "from Persona a, Direccion b where a.idpersona=b.idPersona and direccion like %" + direccion
				+ "%";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Persona> listPersona = (List<Persona>) query.list();

		if (listPersona != null && !listPersona.isEmpty()) {
			return listPersona;
		}

		return null;
	}
}
