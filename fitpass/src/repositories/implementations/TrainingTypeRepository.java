package repositories.implementations;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.models.TrainingType;
import repositories.interfaces.IRepository;

public class TrainingTypeRepository implements IRepository<TrainingType> {

	private String contextPath;
	
	public TrainingTypeRepository(String contextPath) {
		super();
		this.contextPath = contextPath;
	}


	@Override
	public Map<String, TrainingType> load() {
		
		HashMap<String, TrainingType> map = new HashMap<String, TrainingType>();
		
		try {
		    ObjectMapper mapper = new ObjectMapper();

		    List<TrainingType> list = Arrays.asList(mapper.readValue(
		    		Paths.get(contextPath + "data\\trainingTypes.json").toFile(), TrainingType[].class));
		    
		    for(TrainingType element : list){
		    	map.put(String.valueOf(element.getId()), element);
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}

	    return map;
	}
		

	@Override
	public boolean save(Map<String, TrainingType> map) {
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    
		    mapper.writeValue(Paths.get(contextPath + "data\\trainingTypes.json").toFile(), map.values());

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return true;
	}
	
}
