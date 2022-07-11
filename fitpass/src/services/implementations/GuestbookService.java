package services.implementations;

import java.util.ArrayList;
import java.util.Collection;

import beans.enums.ApprovalStatus;
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

	@Override
	public Collection<Guestbook> getAllApproved() {
		Collection<Guestbook> guestbooks = new ArrayList<>(guestbookDAO.getAll());
		guestbooks.removeIf(gb -> (gb.getApprovalStatus() != ApprovalStatus.APPROVED));
		return guestbooks;
	}

	@Override
	public Collection<Guestbook> getAllForSportsFacilityId(long sportsFacilityId) {
		Collection<Guestbook> guestbooks = new ArrayList<>(guestbookDAO.getAll());
		guestbooks.removeIf(gb -> (gb.getSportsFacilityId() != sportsFacilityId));
		return guestbooks;
	}

	@Override
	public Collection<Guestbook> getAllApprovedForSportsFacilityId(long sportsFacilityId) {
		Collection<Guestbook> guestbooks = new ArrayList<>(guestbookDAO.getAll());
		guestbooks.removeIf(gb -> (gb.getApprovalStatus() != ApprovalStatus.APPROVED || gb.getSportsFacilityId() != sportsFacilityId));
		return guestbooks;
	}

	@Override
	public void deleteForBuyer(long id) {
		Collection<Guestbook> guestbooks = new ArrayList<>(guestbookDAO.getAll());
		guestbooks.forEach(comment -> {
			if(comment.getBuyerId() == id) {
				guestbookDAO.delete(String.valueOf(comment.getId()));
			}
		});
	}

}
