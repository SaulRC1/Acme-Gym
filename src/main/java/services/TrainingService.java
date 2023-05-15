
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

	public void delete(final Training training) {
		Assert.notNull(training);
		Assert.isTrue(training.getId() != 0);
		Assert.isTrue(this.trainingRepository.exists(training.getId()));

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

	public Training findOne(final int trainingId) {
		Assert.isTrue(trainingId != 0);

		Training result;

		result = this.trainingRepository.findOne(trainingId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Training> findTrainingsByTitle(final String title) {
		return this.trainingRepository.findByTitle(title);
	}

	public Collection<Training> findTrainingsByGym(final Gym gym) {
		Assert.isTrue(!gym.getTrainings().isEmpty());

		return this.trainingRepository.findByGym(gym);
	}

	public Collection<Training> findTrainingsByStepDescriptionContaining(final String keyword) {
		return this.trainingRepository.findByStepDescriptionContaining(keyword);
	}

	public Collection<Training> findAll() {
		Collection<Training> result;

		result = this.trainingRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Training save(final Training training) {
		Assert.notNull(training);

		Training result;

		Assert.isTrue(!training.getGym().getTrainings().contains(training));

		result = this.trainingRepository.save(training);

		return result;
	}
}
