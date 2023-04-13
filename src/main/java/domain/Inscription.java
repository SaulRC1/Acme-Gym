
package domain;

import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Inscription {

	private Date	singUpDate;
	private Date	singOutDate;

	//Relaciones
	private Gym		gym;
	private Client	client;


	@ManyToOne(optional = false)
	public Gym getGym() {
		return this.gym;
	}

	public void setGym(final Gym gym) {
		this.gym = gym;
	}

	@ManyToOne(optional = false)
	public Client getClient() {
		return this.client;
	}

	public void setClient(final Client client) {
		this.client = client;
	}

	public Inscription(final Date singUpDate, final Date singOutDate) {
		super();
		this.singUpDate = singUpDate;
		this.singOutDate = singOutDate;
	}

	public Date getSingUpDate() {
		return this.singUpDate;
	}

	public void setSingUpDate(final Date singUpDate) {
		this.singUpDate = singUpDate;
	}

	public Date getSingOutDate() {
		return this.singOutDate;
	}

	public void setSingOutDate(final Date singOutDate) {
		this.singOutDate = singOutDate;
	}

}
