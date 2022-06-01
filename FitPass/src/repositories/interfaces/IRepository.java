package repositories.interfaces;

import java.util.Collection;

public interface IRepository<T> {
	public Collection<T> load();
	public boolean save(Collection<T> collection);
}
