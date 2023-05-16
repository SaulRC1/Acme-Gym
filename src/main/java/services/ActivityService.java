
package services;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
		Collection<Activity> result;

		result = this.activityRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Activity findOne(final int activityId) {
		Assert.isTrue(activityId != 0);

		Activity result;

		result = this.activityRepository.findOne(activityId);
		Assert.notNull(result);

		return result;
	}

	public Activity save(final Activity activity) {
		Assert.notNull(activity);

		Activity result;

		Assert.isTrue(!activity.getGym().getActivities().contains(activity));

		result = this.activityRepository.save(activity);

		return result;
	}

	public void delete(final Activity activity) {
		Assert.notNull(activity);
		Assert.isTrue(activity.getId() != 0);
		Assert.isTrue(this.activityRepository.exists(activity.getId()));

		this.activityRepository.delete(activity);
	}

	public Collection<Activity> findByDayRange(final Collection<DayOfWeek> days, final LocalTime time) {

		return this.activityRepository.findByDayRange(days, time);

	}

	public Collection<Activity> findByKeyword(final String keyword) {

		return this.activityRepository.findByKeyword(keyword);
	}

	public Collection<Activity> findAvailableActivities() {
		return this.activityRepository.findAvailableActivities();
	}

}
