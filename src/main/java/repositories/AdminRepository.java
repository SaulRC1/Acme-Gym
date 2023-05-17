package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    public Admin getByEmail(String email);

    @Query("SELECT a FROM Admin a WHERE a.userAccount.id = ?1")
    public Admin findByUserAccountId(int userAccountId);
}
