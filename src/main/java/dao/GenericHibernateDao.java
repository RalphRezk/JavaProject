package dao;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericHibernateDao<T extends Serializable> 
	extends AbstractHibernateDao<T> implements IGenericDao<T> {
	
	@Override
	public T findById(long id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}

	@Override
	public List<T> listAll() {
		// TODO Auto-generated method stub
		return super.listAll();
	}

	@Override
	public long save(T entity) {
		// TODO Auto-generated method stub
		return super.save(entity);
	}

	@Override
	public void create(T entity) {
		// TODO Auto-generated method stub
		super.create(entity);
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		super.update(entity);
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		super.delete(entity);
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		super.deleteById(id);
	}

	@Override
	public void setTargetClass(Class<T> targetClass) {
		// TODO Auto-generated method stub
		super.setTargetClass(targetClass);	
	}
	
	
}
