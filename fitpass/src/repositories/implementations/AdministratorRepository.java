package repositories.implementations;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.models.Administrator;
import repositories.interfaces.IRepository;

public class AdministratorRepository implements IRepository<Administrator> {
	
	private String contextPath;
	
	public AdministratorRepository(String contextPath) {
		super();
		this.contextPath = contextPath;
	}


	@Override
	public Map<String, Administrator> load() {
		
		HashMap<String, Administrator> map = new HashMap<String, Administrator>();
		
		try {
		    ObjectMapper mapper = new ObjectMapper();

		    List<Administrator> list = Arrays.asList(mapper.readValue(
		    		Paths.get(contextPath + "data\\administrators.json").toFile(), Administrator[].class));
		    
		    for(Administrator element : list){
		    	map.put(String.valueOf(element.getId()), element);
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}

	    return map;
	}

	@Override
	public boolean save(Map<String, Administrator> map) {
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    
		    mapper.writeValue(Paths.get(contextPath + "data\\administrators.json").toFile(), map.values());

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return true;
	}
}
