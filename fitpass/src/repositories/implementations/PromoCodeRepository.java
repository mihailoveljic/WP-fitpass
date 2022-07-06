package repositories.implementations;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.models.PromoCode;
import repositories.interfaces.IRepository;

public class PromoCodeRepository implements IRepository<PromoCode> {
	
	private String contextPath;
	
	public PromoCodeRepository(String contextPath) {
		super();
		this.contextPath = contextPath;
	}


	@Override
	public Map<String, PromoCode> load() {
		
		HashMap<String, PromoCode> map = new HashMap<String, PromoCode>();
		
		try {
		    ObjectMapper mapper = new ObjectMapper();

		    List<PromoCode> list = Arrays.asList(mapper.readValue(
		    		Paths.get(contextPath + "data\\promoCodes.json").toFile(), PromoCode[].class));
		    
		    for(PromoCode element : list){
		    	if(element.getIsDeleted()) continue;
		    	map.put(String.valueOf(element.getId()), element);
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}

	    return map;
	}
		

	@Override
	public boolean save(Map<String, PromoCode> map) {
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    
		    mapper.writeValue(Paths.get(contextPath + "data\\promoCodes.json").toFile(), map.values());

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return true;
	}
}
