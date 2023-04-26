
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Activity;
import domain.Trainer;
import repositories.TrainerRepository;

@Service
@Transactional
public class TrainerService {

	@Autowired
	private TrainerRepository trainerRepository;


	public Trainer save(Trainer trainer) {
		return this.trainerRepository.save(trainer);
	}

	public void delete(Trainer trainer) {
		this.trainerRepository.delete(trainer);
	}

	public Collection<Trainer> findAll() {
		return this.trainerRepository.findAll();
	}

	public void addActivity(Trainer trainer, Activity activity) {
		if (trainer != null && activity != null) {
			trainer.addActivity(activity);
			this.trainerRepository.save(trainer);
		}
	}

	public void removeActivity(Trainer trainer, Activity activity) {
		if (trainer != null && activity != null) {
			trainer.getActivities().remove(activity);
			this.trainerRepository.save(trainer);
		}
	}
}
