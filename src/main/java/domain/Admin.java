
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Admin extends Actor {

	// Relationships
	private Gym gym;

	@ManyToOne(optional = false)
	public Gym getGym() {
		return this.gym;
	}

	public void setGym(final Gym gym) {
		this.gym = gym;
	}

	public Admin(String firstName, String lastName, String address, String email, String phoneNumber, String postalCode,
			String city, String country, Collection<Annotation> annotations) {
		super(firstName, lastName, address, email, phoneNumber, postalCode, city, country, annotations);
	}
}
