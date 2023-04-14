
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Trainer extends Actor {

	// Relationships
	private Curriculum curriculum;
	private Gym gym;
	private Activity activity;
	
	public Trainer(String firstName, String lastName, String address, String email, String phoneNumber,
			String postalCode, String city, String country, Collection<Annotation> annotations, Curriculum curriculum,
			Gym gym, Activity activity) {
		super(firstName, lastName, address, email, phoneNumber, postalCode, city, country, annotations);
		this.curriculum = curriculum;
		this.gym = gym;
		this.activity = activity;
	}

	@ManyToOne(optional = false)
	public Activity getActivity() {
		return this.activity;
	}

	@OneToOne(optional = false)
	public Curriculum getCurriculum() {
		return this.curriculum;
	}

	@ManyToOne(optional = false)
	public Gym getGym() {
		return this.gym;
	}

	public void setActivity(final Activity activity) {
		this.activity = activity;
	}

	public void setCurriculum(final Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	public void setGym(final Gym gym) {
		this.gym = gym;
	}

}
