
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Manager extends Actor {

	private boolean banned;

	// Relationships
	Collection<Gym> gyms;

	public Manager(String firstName, String lastName, String address, String email, String phoneNumber,
			String postalCode, String city, String country, Collection<Annotation> annotations, boolean banned,
			Collection<Gym> gyms) {
		super(firstName, lastName, address, email, phoneNumber, postalCode, city, country, annotations);
		this.banned = banned;
		this.gyms = gyms;
	}

	@OneToMany(mappedBy = "manager")
	@NotEmpty
	public Collection<Gym> getGyms() {
		return this.gyms;
	}

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	public void setGyms(final Collection<Gym> gyms) {
		this.gyms = gyms;
	}

}
