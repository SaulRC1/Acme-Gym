
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Manager extends Actor {

	private boolean				banned;

	// Relationships
	private Collection<Gym>		gyms;
	private Collection<Admin>	administrators;


	public Manager(String firstName, String lastName, String address, String email, String phoneNumber, String postalCode, String city, String country, Collection<Annotation> annotations, boolean banned, Collection<Gym> gyms,
		Collection<Admin> administrators) {
		super(firstName, lastName, address, email, phoneNumber, postalCode, city, country, annotations);
		this.banned = banned;
		this.gyms = gyms;
		this.administrators = administrators;
	}

	@ManyToMany(mappedBy = "managers")
	public Collection<Admin> getAdministrators() {
		return this.administrators;
	}

	public void setAdministrators(Collection<Admin> administrators) {
		this.administrators = administrators;
	}

	@OneToMany(mappedBy = "manager")
	@NotEmpty
	public Collection<Gym> getGyms() {
		return this.gyms;
	}

	public boolean isBanned() {
		return this.banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	public void setGyms(final Collection<Gym> gyms) {
		this.gyms = gyms;
	}

	/**
	 * Will add an administrator to this manager.<br>
	 * <br>
	 *
	 * YOU MUST NOT USE THIS METHOD IF YOU HAVE CALLED
	 * {@link Admin#addManager(Manager)} PREVIOUS TO THIS METHOD.
	 *
	 * @param admin
	 *            The administrator to be added.
	 */
	public void addAdministrator(Admin admin) {
		if (admin != null) {
			if (this.administrators == null)
				this.administrators = new ArrayList<Admin>();

			this.administrators.add(admin);
		}
	}

}
