package dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractHibernateDao<T extends Serializable> {
	
	private Class<T> targetClass;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setTargetClass(Class<T> targetClass) {
		this.targetClass = targetClass;
	}

	public T findById(long id) {
		return (T) getCurrentSession().get(targetClass,id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listAll(){
		return getCurrentSession().createQuery("from "+targetClass.getName()+" order by id").list();
	}
	
	public long save(T entity) {
		return (long)getCurrentSession().save(entity);
	}
	
	public void create(T entity) {
		getCurrentSession().persist(entity);
	}
	
	public void update(T entity) {
		getCurrentSession().merge(entity);
	}
	
	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}
	
	public void deleteById(long id) {
		T entity = findById(id);
		getCurrentSession().delete(entity);
	}
	
	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
