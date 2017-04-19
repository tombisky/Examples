package com.lucatic.agenda.servicios;

import java.util.List;

public interface Service<T> {
	public List<T> list();

	public T get(int id);

	public void saveOrUpdate(T item);

	public void delete(int id);
}
