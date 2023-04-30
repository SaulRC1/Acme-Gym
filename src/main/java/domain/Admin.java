
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Admin extends Actor {

    // An admin administrates some managers, there may be some cases
    // where an admin does not administrate any. Cardinality of this
    // relationship would be 0..*
    private Collection<Manager> managers;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    public Collection<Manager> getManagers() {
	return this.managers;
    }

    public void setManagers(final Collection<Manager> managers) {
	this.managers = managers;
    }

    public Admin() {

    }

    public Admin(final String firstName, final String lastName, final String address, final String email,
	    final String phoneNumber, final String postalCode, final String city, final String country,
	    final Collection<Annotation> annotations, final Collection<Manager> managers) {
	super(firstName, lastName, address, email, phoneNumber, postalCode, city, country, annotations);
	this.managers = managers;
    }

    /**
     * Will add a manager to this admin list of administrated managers.<br>
     * <br>
     *
     * NOTE THAT USING THIS METHOD WILL ALSO ADD THIS ADMIN IN THE MANAGER<br>
     * INSTANCE. DO NOT CALL MANAGER'S {@link Manager#addAdministrator(Admin)}<br>
     * METHOD AFTER CALLING THIS METHOD.
     *
     * @param manager The manager to be administrated by this admin
     */
    public void addManager(Manager manager) {

	if (manager != null) {

	    if (this.managers == null)
		this.managers = new ArrayList<Manager>();

	    this.managers.add(manager);
	    manager.addAdministrator(this);
	}

    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((this.managers == null) ? 0 : this.managers.hashCode());
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
	Admin other = (Admin) obj;
	if (this.managers == null) {
	    if (other.managers != null)
		return false;
	} else if (!this.managers.equals(other.managers))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Admin [managers=" + this.managers + ", getManagers()=" + this.getManagers() + ", hashCode()="
		+ this.hashCode() + ", getAddress()=" + this.getAddress() + ", getAnnotations()="
		+ this.getAnnotations() + ", getCity()=" + this.getCity() + ", getCountry()=" + this.getCountry()
		+ ", getEmail()=" + this.getEmail() + ", getLastName()=" + this.getLastName() + ", getFirstName()="
		+ this.getFirstName() + ", getPhoneNumber()=" + this.getPhoneNumber() + ", getPostalCode()="
		+ this.getPostalCode() + ", toString()=" + super.toString() + ", getId()=" + this.getId()
		+ ", getVersion()=" + this.getVersion() + ", getClass()=" + this.getClass() + "]";
    }

}
