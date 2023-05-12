
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Admin extends Actor {

    public Admin() {

    }

    public Admin(final String firstName, final String lastName, final String address, final String email,
	    final String phoneNumber, final String postalCode, final String city, final String country,
	    final Collection<Annotation> annotations) {
	super(firstName, lastName, address, email, phoneNumber, postalCode, city, country, annotations);
    }

    @Override
    public int hashCode() {
	final int result = super.hashCode();
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
	return true;
    }

    @Override
    public String toString() {
	return "Admin [managers=" + ", hashCode()=" + this.hashCode() + ", getAddress()=" + this.getAddress()
		+ ", getAnnotations()=" + this.getAnnotations() + ", getCity()=" + this.getCity() + ", getCountry()="
		+ this.getCountry() + ", getEmail()=" + this.getEmail() + ", getLastName()=" + this.getLastName()
		+ ", getFirstName()=" + this.getFirstName() + ", getPhoneNumber()=" + this.getPhoneNumber()
		+ ", getPostalCode()=" + this.getPostalCode() + ", toString()=" + super.toString() + ", getId()="
		+ this.getId() + ", getVersion()=" + this.getVersion() + ", getClass()=" + this.getClass() + "]";
    }

}
