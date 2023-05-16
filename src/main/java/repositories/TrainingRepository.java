
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Gym;
import domain.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Integer> {

	@Query("SELECT t FROM Training t WHERE t.title = ?1")
	public Collection<Training> findByTitle(String title);

	@Query("SELECT t FROM Training t WHERE t.gym = ?1")
	public Collection<Training> findByGym(Gym gym);

	@Query("SELECT t FROM Training t WHERE t.description = ?1 OR t.title = ?1")
	public Collection<Training> findByKeyword(String keyword);

}
