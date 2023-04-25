
package repositories;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Client;
import domain.Gym;
import domain.Inscription;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, LocalDate> {

	/*
	 * To find the inscriptions by a sing up date
	 */
	@Query("SELECT i FROM Inscription i WHERE i.singUpDate = ?1")
	Collection<Inscription> findBySingUpDate(LocalDate date);

	@Query("SELECT * FROM Inscription i WHERE i.gym_id = ?1 AND i.client_id = ?2")
	Collection<Inscription> findByGymAndClient(Gym gym, Client client);

	Object findById(Long inscriptionId);
}
