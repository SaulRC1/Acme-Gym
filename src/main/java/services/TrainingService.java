
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Gym;
import domain.Training;
import repositories.TrainingRepository;

@Service
@Transactional
public class TrainingService {

	@Autowired
	private TrainingRepository trainingRepository;


	public Training create() {
		return new Training();
	}

	public void delete(Training training) {
		this.trainingRepository.delete(training);
	}
	/*
	 * public Training findTrainingById(final int trainingId) {
	 * final Optional<Training> training = this.trainingRepository.findById(trainingId);
	 * if (training.isPresent())
	 * return training.get();
	 * return null;
	 * }
	 */

	public Training findOne(int trainingId) {
		return this.trainingRepository.findOne(trainingId);
	}

	public Collection<Training> findTrainingsByTitle(String title) {
		return this.trainingRepository.findByTitle(title);
	}

	public Collection<Training> findTrainingsByGym(Gym gym) {
		return this.trainingRepository.findByGym(gym);
	}

	public Collection<Training> findTrainingsByStepDescriptionContaining(String keyword) {
		return this.trainingRepository.findByStepDescriptionContaining(keyword);
	}

	public Collection<Training> findAll() {
		return this.trainingRepository.findAll();
	}

	public Training save(final Training training) {
		return this.trainingRepository.save(training);
	}
}
