
package domain;

import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Access(AccessType.PROPERTY)
public class Inscription {

	private Date singUpDate;
	private Date singOutDate;

	// Relationships
	private Gym gym;
	private Client client;

	public Inscription(final Date singUpDate, final Date singOutDate) {
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

	@Temporal(TemporalType.TIMESTAMP)
	public Date getSingOutDate() {
		return this.singOutDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getSingUpDate() {
		return this.singUpDate;
	}

	public void setClient(final Client client) {
		this.client = client;
	}

	public void setGym(final Gym gym) {
		this.gym = gym;
	}

	public void setSingOutDate(final Date singOutDate) {
		this.singOutDate = singOutDate;
	}

	public void setSingUpDate(final Date singUpDate) {
		this.singUpDate = singUpDate;
	}

}
