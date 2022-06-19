package repositories.implementations;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.models.Coach;
import repositories.interfaces.IRepository;

public class CoachRepository implements IRepository<Coach> {
	
	private String contextPath;
	
	public CoachRepository(String contextPath) {
		super();
		this.contextPath = contextPath;
	}


	@Override
	public Map<String, Coach> load() {
		
		HashMap<String, Coach> map = new HashMap<String, Coach>();
		
		try {
		    ObjectMapper mapper = new ObjectMapper();

		    List<Coach> list = Arrays.asList(mapper.readValue(Paths.get(contextPath + "data\\coaches.json").toFile(), Coach[].class));
		    for(Coach element : list){
		    	map.put(String.valueOf(element.getId()), element);
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    return map;
	}
		

	@Override
	public boolean save(Map<String, Coach> map) {
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    
		    mapper.writeValue(Paths.get(contextPath + "data\\coaches.json").toFile(), map.values());

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return true;
	}
}
