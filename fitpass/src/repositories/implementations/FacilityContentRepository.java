package repositories.implementations;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.models.FacilityContent;
import repositories.interfaces.IRepository;

public class FacilityContentRepository implements IRepository<FacilityContent>{
	
private String contextPath;
	
	public FacilityContentRepository(String contextPath) {
		super();
		this.contextPath = contextPath;
	}


	@Override
	public Map<String, FacilityContent> load() {
		
		HashMap<String, FacilityContent> map = new HashMap<String, FacilityContent>();
		
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    List<FacilityContent> facilityContents = Arrays.asList(mapper.readValue(
		    		Paths.get(contextPath + "data\\facility-contents.json").toFile(), FacilityContent[].class));
		    
		    for(FacilityContent element : facilityContents){
		    	if(element.getIsDeleted()) continue;
		    	map.put(String.valueOf(element.getId()), element);
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}

	    return map;
	}
		

	@Override
	public boolean save(Map<String, FacilityContent> map) {
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    mapper.writeValue(Paths.get(contextPath + "data\\facility-contents.json").toFile(), map.values());

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return true;
	}

}
