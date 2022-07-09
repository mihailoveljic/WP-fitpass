package repositories.implementations;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.models.Training;
import repositories.interfaces.IRepository;

public class TrainingRepository implements IRepository<Training>{

private String contextPath;
	
	public TrainingRepository(String contextPath) {
		super();
		this.contextPath = contextPath;
	}


	@Override
	public Map<String, Training> load() {
		
		HashMap<String, Training> map = new HashMap<String, Training>();
		
		try {
		    ObjectMapper mapper = new ObjectMapper();

		    List<Training> list = Arrays.asList(mapper.readValue(
		    		Paths.get(contextPath + "data\\trainings.json").toFile(), Training[].class));
		    
		    for(Training element : list){
		    	map.put(String.valueOf(element.getId()), element);
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}

	    return map;
	}
		

	@Override
	public boolean save(Map<String, Training> map) {
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    
		    mapper.writeValue(Paths.get(contextPath + "data\\trainings.json").toFile(), map.values());

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return true;
	}
}
