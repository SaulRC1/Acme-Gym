
package domain;

import java.util.Collection;

import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

/**
 * An Actor represents any kind of user related to the domain model.
 *
 * This can include administrators, common users, etc.
 *
 *
 */

/*
 * @Entity
 * 
 * @Acces(AccesType.PROPERTY)
 * 
 * @Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
 */
public abstract class Actor {

	private String					name;
	private String					lastName;
	private String					address;
	private String					email;
	private String					phoneNumber;
	private String					postalCode;
	private String					city;
	private String					country;

	//Relaciones
	private Collection<Annotation>	anottatiions;


	@OneToMany(mappedBy = "actor")
	public Collection<Annotation> getAnottatiions() {
		return this.anottatiions;
	}

	public void setAnottatiions(final Collection<Annotation> anottatiions) {
		this.anottatiions = anottatiions;
	}

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	@NotBlank
	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(final String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(final String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}
}
