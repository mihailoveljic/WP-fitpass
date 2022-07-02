package services.interfaces;

import java.util.Collection;


import beans.models.Guestbook;

public interface IGuestbookService extends ICRUDService<Guestbook> {

	Collection<Guestbook> getAll();

	Collection<Guestbook> getAllApproved();

	
}
