
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Activity;
import repositories.ActivityRepository;

@Service
@Transactional
public class ActivityService {

	@Autowired
	private ActivityRepository activityRepository;


	public Activity create() {
		return new Activity();
	}

	public Collection<Activity> findAll() {
		return this.activityRepository.findAll();
	}

	public Activity findOne(final int activityId) {
		return this.activityRepository.findOne(activityId);
	}

	public Activity save(final Activity activity) {
		return this.activityRepository.save(activity);
	}

	public void delete(final Activity activity) {
		this.activityRepository.delete(activity);
	}
}
