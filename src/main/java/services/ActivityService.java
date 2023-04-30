
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


	public Collection<Activity> getAllActivities() {
		return this.activityRepository.findAll();
	}

	public Activity saveActivity(Activity activity) {
		return this.activityRepository.save(activity);
	}

	public void deleteActivity(Activity activity) {
		this.activityRepository.delete(activity);
	}
}
