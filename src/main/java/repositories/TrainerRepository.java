
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

	@Query("SELECT t FROM Trainer t WHERE t.userAccount.id = ?1")
	public Trainer findByUserAccountId(int userAccountId);

	@Query("SELECT t FROM Trainer t WHERE t.firstName = ?1 OR t.lastName = ?1")
	public Collection<Trainer> findByNameOrSurname(String keyword);

	@Query("SELECT t FROM Trainer t WHERE t.gym = null")
	public Collection<Trainer> findTrainerByNoGymAssigned();
}
