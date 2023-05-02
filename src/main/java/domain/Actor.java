
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * An Actor represents any kind of user related to the domain model.
 *
 * This can include administrators, common users, etc.
 *
 *
 */

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Actor extends DomainEntity {

    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private String postalCode;
    private String city;
    private String country;

    // Relationships
    private Collection<Annotation> annotations;

    public Actor(String firstName, String lastName, String address, String email, String phoneNumber, String postalCode,
	    String city, String country, Collection<Annotation> annotations) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.address = address;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.postalCode = postalCode;
	this.city = city;
	this.country = country;
	this.annotations = annotations;
    }

    public Actor() {

    }

    @NotBlank
    public String getAddress() {
	return this.address;
    }

    @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<Annotation> getAnnotations() {
	return this.annotations;
    }

    public String getCity() {
	return this.city;
    }

    public String getCountry() {
	return this.country;
    }

    public String getEmail() {
	return this.email;
    }

    @NotBlank
    public String getLastName() {
	return this.lastName;
    }

    @NotBlank
    public String getFirstName() {
	return this.firstName;
    }

    @Pattern(regexp = "^(\\+34|0034|34)?[6,7]\\d{8}$")
    public String getPhoneNumber() {
	return this.phoneNumber;
    }

    public String getPostalCode() {
	return this.postalCode;
    }

    public void setAddress(final String address) {
	this.address = address;
    }

    public void setAnnotations(final Collection<Annotation> annotations) {
	this.annotations = annotations;
    }

    public void setCity(final String city) {
	this.city = city;
    }

    public void setCountry(final String country) {
	this.country = country;
    }

    public void setEmail(final String email) {
	this.email = email;
    }

    public void setLastName(final String lastName) {
	this.lastName = lastName;
    }

    public void setFirstName(final String firstName) {
	this.firstName = firstName;
    }

    public void setPhoneNumber(final String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    public void setPostalCode(final String postalCode) {
	this.postalCode = postalCode;
    }

    /**
     * Will add an annotation to this actor.<br>
     * <br>
     *
     * NOTE THAT THIS METHOD WILL ALSO ADD THIS ACTOR TO THE ANNOTATION PASSED BY
     * PARAMETER.
     *
     * @param annotation The annotation to be added.
     */
    public void addAnnotation(Annotation annotation) {

	if (annotation != null) {

	    if (this.annotations == null)
		this.annotations = new ArrayList<Annotation>();

	    this.annotations.add(annotation);
	    annotation.setActor(this);
	}
    }

    /**
     * Will remove an annotation from this actor.<br>
     * <br>
     *
     * NOTE THAT THIS METHOD WILL ALSO REMOVE THIS ACTOR FROM THE ANNOTATION PASSED
     * BY PARAMETER.
     *
     * @param annotation The annotation to be removed.
     */
    public void removeAnnotation(Annotation annotation) {

	if (annotation != null && this.annotations != null) {
	    this.annotations.remove(annotation);
	    annotation.setActor(null);
	}
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((this.address == null) ? 0 : this.address.hashCode());
	result = prime * result + ((this.annotations == null) ? 0 : this.annotations.hashCode());
	result = prime * result + ((this.city == null) ? 0 : this.city.hashCode());
	result = prime * result + ((this.country == null) ? 0 : this.country.hashCode());
	result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
	result = prime * result + ((this.firstName == null) ? 0 : this.firstName.hashCode());
	result = prime * result + ((this.lastName == null) ? 0 : this.lastName.hashCode());
	result = prime * result + ((this.phoneNumber == null) ? 0 : this.phoneNumber.hashCode());
	result = prime * result + ((this.postalCode == null) ? 0 : this.postalCode.hashCode());
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
	Actor other = (Actor) obj;
	if (this.address == null) {
	    if (other.address != null)
		return false;
	} else if (!this.address.equals(other.address))
	    return false;
	if (this.annotations == null) {
	    if (other.annotations != null)
		return false;
	} else if (!this.annotations.equals(other.annotations))
	    return false;
	if (this.city == null) {
	    if (other.city != null)
		return false;
	} else if (!this.city.equals(other.city))
	    return false;
	if (this.country == null) {
	    if (other.country != null)
		return false;
	} else if (!this.country.equals(other.country))
	    return false;
	if (this.email == null) {
	    if (other.email != null)
		return false;
	} else if (!this.email.equals(other.email))
	    return false;
	if (this.firstName == null) {
	    if (other.firstName != null)
		return false;
	} else if (!this.firstName.equals(other.firstName))
	    return false;
	if (this.lastName == null) {
	    if (other.lastName != null)
		return false;
	} else if (!this.lastName.equals(other.lastName))
	    return false;
	if (this.phoneNumber == null) {
	    if (other.phoneNumber != null)
		return false;
	} else if (!this.phoneNumber.equals(other.phoneNumber))
	    return false;
	if (this.postalCode == null) {
	    if (other.postalCode != null)
		return false;
	} else if (!this.postalCode.equals(other.postalCode))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Actor [firstName=" + this.firstName + ", lastName=" + this.lastName + ", address=" + this.address
		+ ", email=" + this.email + ", phoneNumber=" + this.phoneNumber + ", postalCode=" + this.postalCode
		+ ", city=" + this.city + ", country=" + this.country + ", annotations=" + this.annotations + "]";
    }
}
