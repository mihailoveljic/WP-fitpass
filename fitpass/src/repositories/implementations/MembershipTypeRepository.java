package repositories.implementations;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.models.MembershipType;
import repositories.interfaces.IRepository;

public class MembershipTypeRepository implements IRepository<MembershipType> {

	private String contextPath;
	
	public MembershipTypeRepository(String contextPath) {
		super();
		this.contextPath = contextPath;
	}


	@Override
	public Map<String, MembershipType> load() {
		
		HashMap<String, MembershipType> map = new HashMap<String, MembershipType>();
		
		try {
		    ObjectMapper mapper = new ObjectMapper();

		    List<MembershipType> list = Arrays.asList(mapper.readValue(
		    		Paths.get(contextPath + "data\\membershipTypes.json").toFile(), MembershipType[].class));
		    
		    for(MembershipType element : list){
		    	map.put(String.valueOf(element.getId()), element);
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}

	    return map;
	}
		

	@Override
	public boolean save(Map<String, MembershipType> map) {
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    
		    mapper.writeValue(Paths.get(contextPath + "data\\membershipTypes.json").toFile(), map.values());

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return true;
	}
}
