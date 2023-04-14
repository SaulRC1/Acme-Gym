
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Client extends Actor {

	private CreditCard creditCard;

	// Relationships
	private Collection<Activity> activities;
	private Collection<Inscription> inscriptions;

	public Client(String firstName, String lastName, String address, String email, String phoneNumber,
			String postalCode, String city, String country, Collection<Annotation> annotations, CreditCard creditCard,
			Collection<Activity> activities, Collection<Inscription> inscriptions) {
		super(firstName, lastName, address, email, phoneNumber, postalCode, city, country, annotations);
		this.creditCard = creditCard;
		this.activities = activities;
		this.inscriptions = inscriptions;
	}

	@ManyToMany
	public Collection<Activity> getActivities() {
		return this.activities;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	@OneToMany(mappedBy = "client")
	@NotEmpty
	public Collection<Inscription> getInscriptions() {
		return this.inscriptions;
	}

	public void setActivities(final Collection<Activity> activities) {
		this.activities = activities;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public void setInscriptions(final Collection<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}
}
