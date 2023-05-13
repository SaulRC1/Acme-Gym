
package domain;

import java.time.LocalDate;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Inscription extends DomainEntity {

    private LocalDate singUpDate;
    private LocalDate singOutDate;

    // Relationships
    private Gym gym;
    private Client client;

    public Inscription(final LocalDate singUpDate, final LocalDate singOutDate) {
	super();
	this.singUpDate = singUpDate;
	this.singOutDate = singOutDate;
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

    public LocalDate getSingOutDate() {
	return this.singOutDate;
    }

    public LocalDate getSingUpDate() {
	return this.singUpDate;
    }

    public void setClient(final Client client) {
	this.client = client;
    }

    public void setGym(final Gym gym) {
	this.gym = gym;
    }

    public void setSingOutDate(final LocalDate singOutDate) {
	this.singOutDate = singOutDate;
    }

    public void setSingUpDate(final LocalDate singUpDate) {
	this.singUpDate = singUpDate;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((this.client == null) ? 0 : this.client.hashCode());
	result = prime * result + ((this.gym == null) ? 0 : this.gym.hashCode());
	result = prime * result + ((this.singOutDate == null) ? 0 : this.singOutDate.hashCode());
	result = prime * result + ((this.singUpDate == null) ? 0 : this.singUpDate.hashCode());
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
	if (this.singOutDate == null) {
	    if (other.singOutDate != null)
		return false;
	} else if (!this.singOutDate.equals(other.singOutDate))
	    return false;
	if (this.singUpDate == null) {
	    if (other.singUpDate != null)
		return false;
	} else if (!this.singUpDate.equals(other.singUpDate))
	    return false;
	return true;
    }

}
