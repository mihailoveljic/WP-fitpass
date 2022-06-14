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
		    // create object mapper instance
		    ObjectMapper mapper = new ObjectMapper();

		    // convert JSON file to map
		    List<Buyer> buyers = Arrays.asList(mapper.readValue(Paths.get(contextPath + "data\\buyers.json").toFile(), Buyer[].class));
		    for(Buyer b : buyers){
		    	map.put(String.valueOf(b.getId()), b);
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}

	    return map;
	}
		

	@Override
	public boolean save(Map<String, Buyer> map) {
		try {
		    // create object mapper instance
		    ObjectMapper mapper = new ObjectMapper();
		    
		    // convert map to JSON file
		    mapper.writeValue(Paths.get(contextPath + "data\\buyers.json").toFile(), map.values());

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return true;
	}
}
