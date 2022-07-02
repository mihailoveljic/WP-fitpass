package repositories.implementations;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.models.Buyer;
import repositories.interfaces.IRepository;

public class BuyerRepository implements IRepository<Buyer> {
	
	private String contextPath;
	
	public BuyerRepository(String contextPath) {
		super();
		this.contextPath = contextPath;
	}


	@Override
	public Map<String, Buyer> load() {
		
		HashMap<String, Buyer> map = new HashMap<String, Buyer>();
		
		try {
		    ObjectMapper mapper = new ObjectMapper();

		    List<Buyer> list = Arrays.asList(mapper.readValue(
		    		Paths.get(contextPath + "data\\buyers.json").toFile(), Buyer[].class));
		    
		    for(Buyer element : list){
		    	if(element.getIsDeleted()) continue;
		    	map.put(String.valueOf(element.getId()), element);
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}

	    return map;
	}
		

	@Override
	public boolean save(Map<String, Buyer> map) {
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    
		    mapper.writeValue(Paths.get(contextPath + "data\\buyers.json").toFile(), map.values());

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return true;
	}
}
