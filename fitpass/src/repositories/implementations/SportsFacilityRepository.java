package repositories.implementations;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.models.SportsFacility;
import repositories.interfaces.IRepository;

public class SportsFacilityRepository implements IRepository<SportsFacility>{
	
private String contextPath;
	
	public SportsFacilityRepository(String contextPath) {
		super();
		this.contextPath = contextPath;
	}


	@Override
	public Map<String, SportsFacility> load() {
		
		HashMap<String, SportsFacility> map = new HashMap<String, SportsFacility>();
		
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    List<SportsFacility> sportsFacilities = Arrays.asList(mapper.readValue(Paths.get(contextPath + "data\\sportsFacilities.json").toFile(), SportsFacility[].class));
		    for(SportsFacility sf : sportsFacilities){
		    	map.put(String.valueOf(sf.getId()), sf);
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}

	    return map;
	}
		

	@Override
	public boolean save(Map<String, SportsFacility> map) {
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    mapper.writeValue(Paths.get(contextPath + "data\\sportsFacilities.json").toFile(), map.values());

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return true;
	}

}
