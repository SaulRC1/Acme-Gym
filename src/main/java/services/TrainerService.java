
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

    public Trainer create() {
	return new Trainer();
    }

    public Trainer save(final Trainer trainer) {
	return this.trainerRepository.save(trainer);
    }

    public void delete(final Trainer trainer) {
	this.trainerRepository.delete(trainer);
    }

    public Collection<Trainer> findAll() {
	return this.trainerRepository.findAll();
    }

    public Trainer findOne(final int trainerId) {
	return this.trainerRepository.findOne(trainerId);
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

    public Trainer findByUserAccountId(int userAccountId) {
	return this.trainerRepository.findByUserAccountId(userAccountId);
    }
}
