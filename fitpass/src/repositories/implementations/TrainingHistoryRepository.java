package repositories.implementations;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.models.TrainingHistory;
import repositories.interfaces.IRepository;

public class TrainingHistoryRepository implements IRepository<TrainingHistory> {
	
	private String contextPath;
	
	public TrainingHistoryRepository(String contextPath) {
		super();
		this.contextPath = contextPath;
	}


	@Override
	public Map<String, TrainingHistory> load() {
		
		HashMap<String, TrainingHistory> map = new HashMap<String, TrainingHistory>();
		
		try {
		    ObjectMapper mapper = new ObjectMapper();

		    List<TrainingHistory> list = Arrays.asList(mapper.readValue(
		    		Paths.get(contextPath + "data\\trainingHistory.json").toFile(), TrainingHistory[].class));
		    
		    for(TrainingHistory element : list){
		    	map.put(String.valueOf(element.getId()), element);
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}

	    return map;
	}
		

	@Override
	public boolean save(Map<String, TrainingHistory> map) {
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    
		    mapper.writeValue(Paths.get(contextPath + "data\\trainingHistory.json").toFile(), map.values());

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return true;
	}
}
