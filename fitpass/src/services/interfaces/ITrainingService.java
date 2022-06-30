package services.interfaces;

import beans.dtos.TrainingDTO;
import beans.models.Training;

public interface ITrainingService extends ICRUDService<Training> {

	public TrainingDTO transformFromTrainingToTrainingDTO(Training training, TrainingDTO trainingDTO);
}
