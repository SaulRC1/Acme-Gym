
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

	@Query("SELECT t FROM Trainer t WHERE t.userAccount.id = ?1")
	public Trainer findByUserAccountId(int userAccountId);

	@Query("SELECT t FROM Trainer t WHERE t.name = ?1 OR t.surname = ?1")
	public Trainer findByNameOrSurname(String keyword);
}
