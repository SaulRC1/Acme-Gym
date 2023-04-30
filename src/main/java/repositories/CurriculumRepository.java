
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curriculum;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Integer> {

	@Query("SELECT c FROM Curriculum c WHERE c.name = ?1")
	public Collection<Curriculum> findByName(String name);

	@Query("SELECT c FROM Curriculum c JOIN c.specialities s WHERE s = ?1")
	public Collection<Curriculum> findBySpecialitiesContaining(String speciality);

	@Query("SELECT c FROM Curriculum c JOIN c.workExperience w WHERE w = ?1")
	public Collection<Curriculum> findByWorkExperienceContaining(String company);
}
