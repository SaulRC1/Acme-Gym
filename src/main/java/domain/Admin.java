
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Admin extends Actor {

	// Relationships
	private Collection<Manager> managers;

	@OneToMany(mappedBy = "admin")
	public Collection<Manager> getManagers() {
	    return managers;
	}

	public void setManagers(Collection<Manager> managers) {
	    this.managers = managers;
	}

	public Admin(String firstName, String lastName, String address, String email, String phoneNumber,
		String postalCode, String city, String country, Collection<Annotation> annotations,
		Collection<Manager> managers) {
	    super(firstName, lastName, address, email, phoneNumber, postalCode, city, country, annotations);
	    this.managers = managers;
	}

	

	
}
