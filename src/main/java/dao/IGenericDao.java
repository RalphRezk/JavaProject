package dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T extends Serializable> {
	T findById(final long id);
	List<T> listAll();
	long save(final T entity);
	void create(final T entity);
	void update(final T entity);
	void delete(final T entity);
	void deleteById(final long entityId);
	void setTargetClass(Class<T> targetClass);
}
