
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

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
public abstract class Actor {

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

	@NotBlank
	public String getAddress() {
		return this.address;
	}

	@OneToMany(mappedBy = "actor")
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
}
