
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Manager extends Actor {

	private boolean			banned;

	// Relationships
	private Collection<Gym>	gyms;


	public Manager(final String firstName, final String lastName, final String address, final String email, final String phoneNumber, final String postalCode, final String city, final String country, final Collection<Annotation> annotations,
		final boolean banned, final Collection<Gym> gyms) {
		super(firstName, lastName, address, email, phoneNumber, postalCode, city, country, annotations);
		this.banned = banned;
		this.gyms = gyms;
	}

	public Manager() {

	}

	@ManyToMany(mappedBy = "managers")
	public Collection<Gym> getGyms() {
		return this.gyms;
	}

	public boolean isBanned() {
		return this.banned;
	}

	public void setBanned(final boolean banned) {
		this.banned = banned;
	}

	public void setGyms(final Collection<Gym> gyms) {
		this.gyms = gyms;
	}

	/**
	 * Will add a gym to this manager.<br>
	 * <br>
	 *
	 * YOU MUST NOT USE THIS METHOD IF YOU HAVE CALLED
	 * {@link Gym#addManager(Manager)} PREVIOUSLY.
	 *
	 * @param gym
	 *            The gym to be added to this manager.
	 */
	public void addGym(final Gym gym) {

		if (gym != null) {

			if (this.gyms == null)
				this.gyms = new ArrayList<Gym>();

			this.gyms.add(gym);
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (this.banned ? 1231 : 1237);
		result = prime * result + ((this.gyms == null) ? 0 : this.gyms.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final Manager other = (Manager) obj;
		if (this.banned != other.banned)
			return false;
		if (this.gyms == null) {
			if (other.gyms != null)
				return false;
		} else if (!this.gyms.equals(other.gyms))
			return false;
		return true;
	}

}
