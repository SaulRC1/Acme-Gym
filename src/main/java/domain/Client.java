
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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

    public Client() {

    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    public Collection<Activity> getActivities() {
	return this.activities;
    }

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "holder", column = @Column(name = "creditCardHolder")),
	    @AttributeOverride(name = "brand", column = @Column(name = "creditCardBrand")),
	    @AttributeOverride(name = "number", column = @Column(name = "creditCardNumber")),
	    @AttributeOverride(name = "expirationMonth", column = @Column(name = "creditCardExpirationMonth")),
	    @AttributeOverride(name = "expirationYear", column = @Column(name = "creditCardExpirationYear")),
	    @AttributeOverride(name = "CVV", column = @Column(name = "creditCardCVV")), })
    public CreditCard getCreditCard() {
	return this.creditCard;
    }

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
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

    /**
     * Adds an activity to this client. It is like a subscription to an
     * activity.<br>
     * <br>
     *
     * NOTE THAT THIS METHOD WILL ALSO ADD THIS CLIENT TO THE ACTIVITY PASSED BY
     * PARAMETER. DO NOT USE {@link Activity#addClient(Client)} WHEN CALLING THIS
     * METHOD.
     *
     * @param activity The activity to be added.
     */
    public void addActivity(Activity activity) {

	if (activity != null) {

	    if (this.activities == null)
		this.activities = new ArrayList<Activity>();

	    this.activities.add(activity);
	    activity.addClient(this);
	}
    }

    /**
     * Will add an inscription to this client.<br>
     * <br>
     *
     * NOTE THAT THIS METHOD WILL ALSO ADD THIS CLIENT TO THE INSCRIPTION PASSED BY
     * PARAMETER.
     *
     * @param inscription The inscription to be added.
     */
    public void addInscription(Inscription inscription) {

	if (inscription != null) {

	    if (this.inscriptions == null)
		this.inscriptions = new ArrayList<Inscription>();

	    this.inscriptions.add(inscription);
	    inscription.setClient(this);
	}
    }

    /**
     * Will remove an inscription from this client.<br>
     * <br>
     *
     * NOTE THAT THIS METHOD WILL ALSO REMOVE THIS CLIENT FROM THE INSCRIPTION
     * PASSED BY PARAMETER
     *
     * @param inscription
     */
    public void removeInscription(Inscription inscription) {

	if (inscription != null && this.inscriptions != null) {

	    this.inscriptions.remove(inscription);
	    inscription.setClient(null);
	}
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((this.activities == null) ? 0 : this.activities.hashCode());
	result = prime * result + ((this.creditCard == null) ? 0 : this.creditCard.hashCode());
	result = prime * result + ((this.inscriptions == null) ? 0 : this.inscriptions.hashCode());
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
	Client other = (Client) obj;
	if (this.activities == null) {
	    if (other.activities != null)
		return false;
	} else if (!this.activities.equals(other.activities))
	    return false;
	if (this.creditCard == null) {
	    if (other.creditCard != null)
		return false;
	} else if (!this.creditCard.equals(other.creditCard))
	    return false;
	if (this.inscriptions == null) {
	    if (other.inscriptions != null)
		return false;
	} else if (!this.inscriptions.equals(other.inscriptions))
	    return false;
	return true;
    }

    public void removeActivity(final Activity activity) {
	if (activity != null && this.activities != null) {
	    this.activities.remove(activity);
	}
    }
}
