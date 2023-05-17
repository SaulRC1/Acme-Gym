
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

	/*
	 * List of managers by email
	 */
	@Query("SELECT m FROM Manager m WHERE m.email = ?1")
	Collection<Manager> findByEmail(String email);

	/*
	 * List of managers banned
	 */
	@Query("SELECT m FROM Manager m WHERE m.banned = false")
	public Collection<Manager> findByBannedFalse();

	@Query("SELECT m FROM Manager m WHERE m.userAccount.id = ?1")
	public Manager findByUserAccountId(int userAccountId);
}
