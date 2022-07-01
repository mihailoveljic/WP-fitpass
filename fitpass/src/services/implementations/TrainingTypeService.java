package services.implementations;

import java.util.Collection;

import beans.models.TrainingType;
import daos.interfaces.IDAO;
import services.interfaces.ICRUDService;

public class TrainingTypeService implements ICRUDService<TrainingType> {

	private IDAO<TrainingType> trainingTypeDAO;
	
	
	
	public TrainingTypeService(IDAO<TrainingType> trainingTypeDAO) {
		super();
		this.trainingTypeDAO = trainingTypeDAO;
	}

	@Override
	public Collection<TrainingType> getAll() {
		return trainingTypeDAO.getAll();
	}

	@Override
	public TrainingType get(long id) {
		return trainingTypeDAO.get(String.valueOf(id));
	}

	@Override
	public TrainingType create(TrainingType trainingType) {
		return trainingTypeDAO.create(trainingType);
	}

	@Override
	public boolean update(TrainingType trainingType) {
		return trainingTypeDAO.update(trainingType);
	}

	@Override
	public boolean delete(long id) {
		return trainingTypeDAO.delete(String.valueOf(id));
	}
}
