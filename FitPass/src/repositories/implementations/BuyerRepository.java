package repositories.implementations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import beans.models.Buyer;
import repositories.interfaces.IRepository;

public class BuyerRepository implements IRepository<Buyer> {
	
	private String contextPath;
	
	public BuyerRepository(String contextPath) {
		super();
		this.contextPath = contextPath;
	}


	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Long, Buyer> load() {
		
		HashMap<Long, Buyer> buyers = new HashMap<Long, Buyer>();
		
		try {
			
		    FileInputStream fis = new FileInputStream(contextPath + File.separator + "buyers.txt");
		    ObjectInputStream ois = new ObjectInputStream(fis);
		    buyers = (HashMap<Long, Buyer>) ois.readObject();
		    ois.close();
		    		    
		}
		catch (FileNotFoundException e) {
		    e.printStackTrace();
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}

	    return buyers;
	}
		

	@Override
	public boolean save(HashMap<Long, Buyer> hashMap) {
	
		HashMap<Long, Buyer> buyers = new HashMap<Long, Buyer>();

		try {

			buyers = hashMap;
	
			FileOutputStream fos = new FileOutputStream(contextPath + File.separator + "buyers.txt");
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(buyers);
		    oos.close();
		    return true;
		}
		catch (FileNotFoundException e) {
		    e.printStackTrace();
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
		return false;
	}

}
