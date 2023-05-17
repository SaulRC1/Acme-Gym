
package repositories;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

	@Query("SELECT a FROM Activity a WHERE a.title = ?1")
	public Collection<Activity> findByTitle(String title);

	@Query("SELECT a FROM Activity a WHERE a.description = ?1 OR a.title = ?1")
	public Collection<Activity> findByKeyword(String keyword);

	@Query("SELECT a FROM Activity a WHERE a.description = ?2 OR a.title = ?2 AND a.gym.id = ?1")
	public Collection<Activity> findByGymIdAndKeyword(int gymId, String keyword);

	@Query("SELECT a FROM Activity a WHERE a.daysOfWeek = ?1 AND a.startHour = ?2")
	public Collection<Activity> findByDayRange(Collection<DayOfWeek> days, LocalTime time);

	@Query("SELECT a FROM Activity a WHERE a.availablePlaces > 0 AND a.active = true")
	public Collection<Activity> findAvailableActivities();

	@Query("SELECT a FROM Activity a WHERE a.title = ?1")
	public Activity findByName(String title);

}
