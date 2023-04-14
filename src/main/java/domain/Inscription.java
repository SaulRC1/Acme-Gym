
package domain;

import java.time.LocalDate;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Inscription {

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

}
