package com.lucatic.agenda.dao;
// Generated 30-mar-2017 13:56:25 by Hibernate Tools 5.1.0.Alpha1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.lucatic.agenda.beans.Categoria;
import com.lucatic.agenda.beans.Persona;

/**
 * Home object for domain model class Categorias.
 * @see com.lucatic.agenda.dao.Categorias
 * @author Hibernate Tools
 */
public interface CategoriaDAO extends  GestorDAO<Categoria>{
	
	//DAO para categoria por si mas adelante necesitamos metodos especificos para las categorías o por si queremos ampliar
}

