package services;

import model.Option;

public interface OptionService {
	public Option getOptionById(long id);
	public void create(Option option);
	public void update(Option option);
	public void delete(long id);
}
