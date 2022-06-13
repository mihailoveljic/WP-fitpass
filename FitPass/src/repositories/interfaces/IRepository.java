package repositories.interfaces;

import java.util.HashMap;


public interface IRepository<T> {
	public HashMap<Long, T> load();
	public boolean save(HashMap<Long, T> hashMap);
}
