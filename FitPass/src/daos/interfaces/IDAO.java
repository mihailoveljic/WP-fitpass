package daos.interfaces;

import java.util.Collection;

public interface IDAO<T> {
	public Collection<T> getAll();
	public T get(String id);
	public T create(T t);
	public boolean update(T t);
	public boolean delete(String id);
}
