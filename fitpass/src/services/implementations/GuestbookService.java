package services.implementations;

import java.util.Collection;

import beans.models.Guestbook;
import daos.interfaces.IDAO;
import services.interfaces.IGuestbookService;

public class GuestbookService implements IGuestbookService {

	private IDAO<Guestbook> guestbookDAO;
	
	
	public GuestbookService(IDAO<Guestbook> guestbookDAO) {
		super();
		this.guestbookDAO = guestbookDAO;
	}

	@Override
	public Collection<Guestbook> getAll() {
		return guestbookDAO.getAll();
	}

	@Override
	public Guestbook get(long id) {
		return guestbookDAO.get(String.valueOf(id));
	}

	@Override
	public Guestbook create(Guestbook guestbook) {
		return guestbookDAO.create(guestbook);
	}

	@Override
	public boolean update(Guestbook guestbook) {
		return guestbookDAO.update(guestbook);
	}

	@Override
	public boolean delete(long id) {
		return guestbookDAO.delete(String.valueOf(id));
	}

}
