package repositories;

import java.util.Collection;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Admin;
import security.Authority;
import security.UserAccount;
import security.UserAccountRepository;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml", "classpath:spring/config/packages.xml" })
@Transactional
public class AdminRepositoryTest extends AbstractTest {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Test
    public void testSaveAdmin() {

	Admin admin = new Admin();

	admin.setFirstName("Test");
	admin.setLastName("Test-LastName");
	admin.setAddress("Test-Address");
	admin.setEmail("test@test.com");
	admin.setPhoneNumber("+34611634565");
	admin.setPostalCode("31005");
	admin.setCity("Córdoba");
	admin.setCountry("España");

	Admin savedAdmin = this.adminRepository.save(admin);

	// Retrieve all admins
	Collection<Admin> admins = this.adminRepository.findAll();

	Assert.isTrue(admins.contains(savedAdmin));
    }

    @Test
    public void testDeleteAdmin() {

	Admin admin = new Admin();

	admin.setFirstName("Test");
	admin.setLastName("Test-LastName");
	admin.setAddress("Test-Address");
	admin.setEmail("test@test.com");
	admin.setPhoneNumber("+34611634565");
	admin.setPostalCode("31005");
	admin.setCity("Córdoba");
	admin.setCountry("España");

	Admin savedAdmin = this.adminRepository.save(admin);

	// System.out.println("Saved Admin: " + savedAdmin);

	Admin retrievedFromDB = this.adminRepository.findOne(savedAdmin.getId());

	// System.out.println("Retrieved From DB Admin: " + retrievedFromDB);

	this.adminRepository.delete(retrievedFromDB);

	Collection<Admin> admins = this.adminRepository.findAll();

	Assert.isTrue(!admins.contains(retrievedFromDB));
    }

    @Test
    public void testUserAccount() {

	Admin admin = this.adminRepository.getByEmail("omar.piedrabuena-admin@hyper-mass.es");

	System.out.println(admin.getUserAccount().getUsername());

    }

    @Test
    public void testFindByUserAccountId() {

	Collection<UserAccount> userAccounts = this.userAccountRepository.findAll();

	Iterator<UserAccount> it = userAccounts.iterator();

	while (it.hasNext()) {

	    UserAccount userAccount = it.next();

	    Collection<Authority> userAuthorities = userAccount.getAuthorities();

	    Iterator<Authority> itAuthority = userAuthorities.iterator();

	    while (itAuthority.hasNext()) {

		Authority authority = itAuthority.next();

		if (authority.getAuthority().equals(Authority.ADMIN)) {
		    Admin admin = this.adminRepository.findByUserAccountId(userAccount.getId());

		    System.out.println("Admin by UserAccountId: " + admin);
		}
	    }
	}

    }
}
