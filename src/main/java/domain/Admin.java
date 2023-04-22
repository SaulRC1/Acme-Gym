
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Admin extends Actor {

    // Relationships
    private Collection<Manager> managers;
    private Gym gym;

    @OneToMany(mappedBy = "admin")
    public Collection<Manager> getManagers() {
	return this.managers;
    }

    public void setManagers(final Collection<Manager> managers) {
	this.managers = managers;
    }

    @ManyToOne(optional = false)
    public Gym getGym() {
	return this.gym;
    }

    public void setGym(final Gym gym) {
	this.gym = gym;
    }

    public Admin(final String firstName, final String lastName, final String address, final String email,
	    final String phoneNumber, final String postalCode, final String city, final String country,
	    final Collection<Annotation> annotations, final Collection<Manager> managers) {
	super(firstName, lastName, address, email, phoneNumber, postalCode, city, country, annotations);
	this.managers = managers;
    }

}
