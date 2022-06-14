package services.interfaces;

import java.util.Collection;

public interface ICRUDService<T> {
	public Collection<T> getAll();
	public T get(long id);
	public T create(T t);
	public boolean update(T t);
	public boolean delete(long id);
}
