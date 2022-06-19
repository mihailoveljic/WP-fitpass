package repositories.implementations;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.models.SportsFacilityType;
import repositories.interfaces.IRepository;

public class SportsFacilityTypeRepository implements IRepository<SportsFacilityType> {

private String contextPath;
	
	public SportsFacilityTypeRepository(String contextPath) {
		super();
		this.contextPath = contextPath;
	}


	@Override
	public Map<String, SportsFacilityType> load() {
		
		HashMap<String, SportsFacilityType> map = new HashMap<String, SportsFacilityType>();
		
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    List<SportsFacilityType> sportsFacilitiesType = Arrays.asList(mapper.readValue(Paths.get(contextPath + "data\\sports-facilitiesType.json").toFile(), SportsFacilityType[].class));
		    for(SportsFacilityType sft : sportsFacilitiesType){
		    	map.put(String.valueOf(sft.getId()), sft);
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}

	    return map;
	}
		

	@Override
	public boolean save(Map<String, SportsFacilityType> map) {
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    mapper.writeValue(Paths.get(contextPath + "data\\sports-facilitiesType.json").toFile(), map.values());

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return true;
	}

}
