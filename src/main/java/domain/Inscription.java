package domain;

import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Inscription {
	private Date singUpDate;
	private Date singOutDate;
	
	Gym gym;

	@ManyToOne(optional=false)
	public Gym getGym() {
		return gym;
	}

	public void setGym(Gym gym) {
		this.gym = gym;
	}

	public Inscription(Date singUpDate, Date singOutDate) {
		super();
		this.singUpDate = singUpDate;
		this.singOutDate = singOutDate;
	}

	public Date getSingUpDate() {
		return singUpDate;
	}

	public void setSingUpDate(Date singUpDate) {
		this.singUpDate = singUpDate;
	}

	public Date getSingOutDate() {
		return singOutDate;
	}

	public void setSingOutDate(Date singOutDate) {
		this.singOutDate = singOutDate;
	}

}
