package daos.implementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import beans.models.Guestbook;
import daos.interfaces.IDAO;
import repositories.interfaces.IRepository;

public class GuestbookDAO implements IDAO<Guestbook> {

	private Map<String, Guestbook> guestbooks = new HashMap<String, Guestbook>();
	private IRepository<Guestbook> guestbookRepository;
	
	public GuestbookDAO(IRepository<Guestbook> GuestbookRepository) {
		super();
		this.guestbookRepository = GuestbookRepository;
		guestbooks = GuestbookRepository.load();
	}

	@Override
	public Collection<Guestbook> getAll() {
		Collection<Guestbook> retVal = new ArrayList<Guestbook>(guestbooks.values());
		retVal.removeIf(x -> (x.getIsDeleted()));
		return retVal;
	}

	@Override
	public Guestbook get(String id) {
		if(guestbooks.containsKey(id)) {
			if(guestbooks.get(id).getIsDeleted() == false){
				return guestbooks.get(id);
			}
		}
		return null;	
	}

	@Override
	public Guestbook create(Guestbook guestbook) {
		long maxId = 0;
		for (String id : guestbooks.keySet()) {
			long idNum = Long.parseLong(id);
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		
		guestbook.setId(maxId);
		guestbooks.put(String.valueOf(guestbook.getId()), guestbook);
		guestbookRepository.save(guestbooks);
		return guestbook;
	}

	@Override
	public boolean update(Guestbook guestbook) {
		if(guestbooks.put(String.valueOf(guestbook.getId()), guestbook) != null) {
			guestbookRepository.save(guestbooks);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String id) {
		if(guestbooks.containsKey(id)) {
			guestbooks.get(id).setIsDeleted(true);
			guestbookRepository.save(guestbooks);
			return true;
		}
		return false;
	}

}
