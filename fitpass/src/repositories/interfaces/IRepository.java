package repositories.interfaces;

import java.util.Map;


public interface IRepository<T> {
	public Map<String, T> load();
	public boolean save(Map<String, T> map);
}
