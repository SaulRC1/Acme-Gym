
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Activity;
import domain.Trainer;
import repositories.TrainerRepository;

@Service
@Transactional
public class TrainerService {

	@Autowired
	private TrainerRepository trainerRepository;


	public Trainer create() {
		return new Trainer();
	}

	public Trainer save(final Trainer trainer) {
		Assert.notNull(trainer);

		Trainer result;

		Assert.isTrue(!trainer.getGym().getTrainers().contains(trainer));

		result = this.trainerRepository.save(trainer);

		return result;
	}

	public void delete(final Trainer trainer) {
		Assert.notNull(trainer);
		Assert.isTrue(trainer.getId() != 0);
		Assert.isTrue(this.trainerRepository.exists(trainer.getId()));

		this.trainerRepository.delete(trainer);
	}

	public Collection<Trainer> findAll() {
		Collection<Trainer> result;

		result = this.trainerRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Trainer findOne(final int trainerId) {
		Assert.isTrue(trainerId != 0);

		Trainer result;

		result = this.trainerRepository.findOne(trainerId);
		Assert.notNull(result);

		return result;
	}

	public void addActivity(final Trainer trainer, final Activity activity) {
		if (trainer != null && activity != null) {
			trainer.addActivity(activity);
			this.trainerRepository.save(trainer);
		}
	}

	public void removeActivity(final Trainer trainer, final Activity activity) {
		if (trainer != null && activity != null) {
			trainer.getActivities().remove(activity);
			this.trainerRepository.save(trainer);
		}
	}

	public Trainer findByUserAccountId(final int userAccountId) {
		return this.trainerRepository.findByUserAccountId(userAccountId);
	}

	public Collection<Trainer> findByNameOrSurname(final String keyword) {
		return this.trainerRepository.findByNameOrSurname(keyword);
	}

	public Trainer findTrainerByNoGymAssigned() {
		return this.trainerRepository.findTrainerByNoGymAssigned();
	}
}
