
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Manager extends Actor {

    private boolean banned;

    // Relationships
    private Collection<Gym> gyms;
    private Collection<Admin> administrators;

    public Manager(String firstName, String lastName, String address, String email, String phoneNumber,
	    String postalCode, String city, String country, Collection<Annotation> annotations, boolean banned,
	    Collection<Gym> gyms, Collection<Admin> administrators) {
	super(firstName, lastName, address, email, phoneNumber, postalCode, city, country, annotations);
	this.banned = banned;
	this.gyms = gyms;
	this.administrators = administrators;
    }

    public Manager() {

    }

    @ManyToMany(mappedBy = "managers")
    @NotEmpty
    public Collection<Admin> getAdministrators() {
	return this.administrators;
    }

    public void setAdministrators(Collection<Admin> administrators) {
	this.administrators = administrators;
    }

    @ManyToMany(mappedBy = "managers")
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
     * @param admin The administrator to be added.
     */
    public void addAdministrator(Admin admin) {
	if (admin != null) {
	    if (this.administrators == null)
		this.administrators = new ArrayList<Admin>();

	    this.administrators.add(admin);
	}
    }

    /**
     * Will add a gym to this manager.<br>
     * <br>
     *
     * YOU MUST NOT USE THIS METHOD IF YOU HAVE CALLED
     * {@link Gym#addManager(Manager)} PREVIOUSLY.
     *
     * @param gym The gym to be added to this manager.
     */
    public void addGym(Gym gym) {

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
	result = prime * result + ((this.administrators == null) ? 0 : this.administrators.hashCode());
	result = prime * result + (this.banned ? 1231 : 1237);
	result = prime * result + ((this.gyms == null) ? 0 : this.gyms.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (this.getClass() != obj.getClass())
	    return false;
	Manager other = (Manager) obj;
	if (this.administrators == null) {
	    if (other.administrators != null)
		return false;
	} else if (!this.administrators.equals(other.administrators))
	    return false;
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
