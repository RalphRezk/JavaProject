package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.IGenericDao;
import model.Option;

@Service
public class OptionServiceImpl implements OptionService{
	
	IGenericDao<Option> optionDAO;
	
	@Autowired
	public void setGroupDAO(IGenericDao<Option> optionDAO) {
		this.optionDAO = optionDAO;
		this.optionDAO.setTargetClass(Option.class);
	}

	@Transactional
	public Option getOptionById(long id) {
		return optionDAO.findById(id);
	}

	@Transactional
	public void create(Option option) {
		optionDAO.create(option);
	}
	
	@Override
	public void update(Option option) {
		optionDAO.update(option);
	}

	@Transactional
	public void delete(long id) {
		optionDAO.deleteById(id);
	}


}
