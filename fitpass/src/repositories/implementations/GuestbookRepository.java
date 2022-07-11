package repositories.implementations;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import beans.models.Guestbook;
import repositories.interfaces.IRepository;

public class GuestbookRepository implements IRepository<Guestbook> {
	
	private String contextPath;
	
	public GuestbookRepository(String contextPath) {
		super();
		this.contextPath = contextPath;
	}


	@Override
	public Map<String, Guestbook> load() {
		
		HashMap<String, Guestbook> map = new HashMap<String, Guestbook>();
		
		try {
		    ObjectMapper mapper = new ObjectMapper();

		    List<Guestbook> list = Arrays.asList(mapper.readValue(
		    		Paths.get(contextPath + "data\\guestbooks.json").toFile(), Guestbook[].class));
		    
		    for(Guestbook element : list){
		    	map.put(String.valueOf(element.getId()), element);
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}

	    return map;
	}
		

	@Override
	public boolean save(Map<String, Guestbook> map) {
		try {
		    ObjectMapper mapper = new ObjectMapper();
		    
		    mapper.writeValue(Paths.get(contextPath + "data\\guestbooks.json").toFile(), map.values());

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return true;
	}
}
