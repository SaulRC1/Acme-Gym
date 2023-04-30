
package repositories;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Activity;
import domain.Actor;
import domain.Annotation;
import domain.Gym;
import domain.Training;

@Repository
public interface AnnotationRepository extends JpaRepository<Annotation, Integer> {

	@Query("SELECT a FROM Annotation a WHERE a.id = ?1")
	public Collection<Annotation> findById(Long id);

	@Query("SELECT a FROM Annotation a WHERE a.gym = ?1")
	public Collection<Annotation> findByGym(Gym gym);

	@Query("SELECT a FROM Annotation a WHERE a.activity = ?1")
	public Collection<Annotation> findByActivity(Activity activity);

	@Query("SELECT a FROM Annotation a WHERE a.training = ?1")
	public Collection<Annotation> findByTraining(Training training);

	@Query("SELECT a FROM Annotation a WHERE a.actor = ?1")
	public Collection<Annotation> findByActor(Actor actor);

	@Query("SELECT a FROM Annotation a WHERE a.mark = ?1")
	public Collection<Annotation> findByMark(Integer mark);

	@Query("SELECT a FROM Annotation a WHERE a.date = ?1")
	public Collection<Annotation> findByDate(LocalDate date);
}
