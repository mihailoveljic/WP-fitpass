package repositories.implementations;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.models.Manager;
import repositories.interfaces.IRepository;

public class ManagerRepository implements IRepository<Manager> {

private String contextPath;
	
	public ManagerRepository(String contextPath) {
		super();
		this.contextPath = contextPath;
	}


	@Override
	public Map<String, Manager> load() {
		
		HashMap<String, Manager> map = new HashMap<String, Manager>();
		
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    List<Manager> managers = Arrays.asList(mapper.readValue(Paths.get(contextPath + "data\\managers.json").toFile(), Manager[].class));
		    for(Manager m : managers){
		    	map.put(String.valueOf(m.getId()), m);
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}

	    return map;
	}
		

	@Override
	public boolean save(Map<String, Manager> map) {
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    mapper.writeValue(Paths.get(contextPath + "data\\managers.json").toFile(), map.values());

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return true;
	}

}
