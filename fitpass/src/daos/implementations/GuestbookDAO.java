package daos.implementations;

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
		return guestbooks.values();
	}

	@Override
	public Guestbook get(String id) {
		return guestbooks.containsKey(id) ? guestbooks.get(id) : null;
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
		if(guestbooks.remove(id) != null) {
			guestbookRepository.save(guestbooks);
			return true;
		}
		return false;
	}

}
