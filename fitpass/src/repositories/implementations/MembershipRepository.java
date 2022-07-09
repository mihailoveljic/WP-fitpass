package repositories.implementations;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.models.Membership;
import repositories.interfaces.IRepository;

public class MembershipRepository implements IRepository<Membership>{

	private String contextPath;
	
	public MembershipRepository(String contextPath) {
		super();
		this.contextPath = contextPath;
	}


	@Override
	public Map<String, Membership> load() {
		
		HashMap<String, Membership> map = new HashMap<String, Membership>();
		
		try {
		    ObjectMapper mapper = new ObjectMapper();

		    List<Membership> list = Arrays.asList(mapper.readValue(
		    		Paths.get(contextPath + "data\\memberships.json").toFile(), Membership[].class));
		    
		    for(Membership element : list){
		    	map.put(String.valueOf(element.getId()), element);
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}

	    return map;
	}
		

	@Override
	public boolean save(Map<String, Membership> map) {
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    
		    mapper.writeValue(Paths.get(contextPath + "data\\memberships.json").toFile(), map.values());

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return true;
	}
}
