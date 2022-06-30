package repositories.implementations;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.models.BuyerType;
import repositories.interfaces.IRepository;

public class BuyerTypeRepository implements IRepository<BuyerType>{
	
	private String contextPath;
	
	public BuyerTypeRepository(String contextPath) {
		super();
		this.contextPath = contextPath;
	}


	@Override
	public Map<String, BuyerType> load() {
		
		HashMap<String, BuyerType> map = new HashMap<String, BuyerType>();
		
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    List<BuyerType> buyerTypes = Arrays.asList(mapper.readValue(Paths.get(contextPath + "data\\buyerTypes.json").toFile(), BuyerType[].class));
		    for(BuyerType bt : buyerTypes){
		    	map.put(String.valueOf(bt.getId()), bt);
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}

	    return map;
	}
		

	@Override
	public boolean save(Map<String, BuyerType> map) {
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    mapper.writeValue(Paths.get(contextPath + "data\\buyerTypes.json").toFile(), map.values());

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return true;
	}

}

