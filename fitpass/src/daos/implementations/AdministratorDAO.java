package daos.implementations;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import beans.models.Administrator;
import daos.interfaces.IDAO;
import repositories.interfaces.IRepository;

public class AdministratorDAO implements IDAO<Administrator> {

	private Map<String, Administrator> administrators = new HashMap<String, Administrator>();
	private IRepository<Administrator> administratorRepository;
	
	public AdministratorDAO(IRepository<Administrator> administratorRepository) {
		super();
		this.administratorRepository = administratorRepository;
		administrators = administratorRepository.load();
	}

	@Override
	public Collection<Administrator> getAll() {
		return administrators.values();
	}

	@Override
	public Administrator get(String id) {
		return administrators.containsKey(id) ? administrators.get(id) : null;
	}

	@Override
	public Administrator create(Administrator administrator) {
		long maxId = 0;
		for (String id : administrators.keySet()) {
			long idNum = Long.parseLong(id);
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		
		administrator.setId(maxId);
		administrators.put(String.valueOf(administrator.getId()), administrator);
		administratorRepository.save(administrators);
		return administrator;
	}

	@Override
	public boolean update(Administrator administrator) {
		if(administrators.put(String.valueOf(administrator.getId()), administrator) != null) {
			administratorRepository.save(administrators);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String id) {
		if(administrators.remove(id) != null) {
			administratorRepository.save(administrators);
			return true;
		}
		return false;
	}

}
