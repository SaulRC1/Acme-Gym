
package domain;

import java.time.LocalDate;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Inscription extends DomainEntity {

    private LocalDate signUpDate;
    private LocalDate signOutDate;

    // Relationships
    private Gym gym;
    private Client client;

    public Inscription(final LocalDate signUpDate, final LocalDate signOutDate) {
	super();
	this.signUpDate = signUpDate;
	this.signOutDate = signOutDate;
    }

    public Inscription() {

    }

    @ManyToOne(optional = false)
    public Client getClient() {
	return this.client;
    }

    @ManyToOne(optional = false)
    public Gym getGym() {
	return this.gym;
    }

    public LocalDate getSignOutDate() {
	return this.signOutDate;
    }

    public LocalDate getSignUpDate() {
	return this.signUpDate;
    }

    public void setClient(final Client client) {
	this.client = client;
    }

    public void setGym(final Gym gym) {
	this.gym = gym;
    }

    public void setSignOutDate(final LocalDate signOutDate) {
	this.signOutDate = signOutDate;
    }

    public void setSignUpDate(final LocalDate signUpDate) {
	this.signUpDate = signUpDate;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((this.client == null) ? 0 : this.client.hashCode());
	result = prime * result + ((this.gym == null) ? 0 : this.gym.hashCode());
	result = prime * result + ((this.signOutDate == null) ? 0 : this.signOutDate.hashCode());
	result = prime * result + ((this.signUpDate == null) ? 0 : this.signUpDate.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (this.getClass() != obj.getClass())
	    return false;
	Inscription other = (Inscription) obj;
	if (this.client == null) {
	    if (other.client != null)
		return false;
	} else if (!this.client.equals(other.client))
	    return false;
	if (this.gym == null) {
	    if (other.gym != null)
		return false;
	} else if (!this.gym.equals(other.gym))
	    return false;
	if (this.signOutDate == null) {
	    if (other.signOutDate != null)
		return false;
	} else if (!this.signOutDate.equals(other.signOutDate))
	    return false;
	if (this.signUpDate == null) {
	    if (other.signUpDate != null)
		return false;
	} else if (!this.signUpDate.equals(other.signUpDate))
	    return false;
	return true;
    }

}
