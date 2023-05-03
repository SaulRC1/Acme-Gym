
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

	public Activity findOne(int activityId) {
		return this.activityRepository.findOne(activityId);
	}

	public Activity saveActivity(Activity activity) {
		return this.activityRepository.save(activity);
	}

	public void deleteActivity(Activity activity) {
		this.activityRepository.delete(activity);
	}
}
