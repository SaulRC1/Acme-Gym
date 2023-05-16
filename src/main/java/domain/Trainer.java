
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Trainer extends Actor {

	// Relationships
	private Curriculum				curriculum;
	private Gym						gym;
	private Collection<Activity>	activities;


	public Trainer(final String firstName, final String lastName, final String address, final String email, final String phoneNumber, final String postalCode, final String city, final String country, final Collection<Annotation> annotations,
		final Curriculum curriculum, final Gym gym, final Collection<Activity> activities) {
		super(firstName, lastName, address, email, phoneNumber, postalCode, city, country, annotations);
		this.curriculum = curriculum;
		this.gym = gym;
		this.activities = activities;
	}

	public Trainer() {

	}

	@ManyToMany(cascade = {
		CascadeType.PERSIST, CascadeType.MERGE
	})
	public Collection<Activity> getActivities() {
		return this.activities;
	}

	public void setActivities(final Collection<Activity> activities) {
		this.activities = activities;
	}

	/**
	 * Adds an activity to be trained by this trainer.<br>
	 * <br>
	 *
	 * NOTE THAT USING THIS METHOD WILL ALSO ADD THIS TRAINER TO THE ACTIVITY PASSED
	 * BY PARAMETER. DO NOT CALL {@linkplain Activity#addTrainer(Trainer)} WHEN
	 * USING THIS METHOD.
	 *
	 * @param activity
	 *            The activity to be added
	 */
	public void addActivity(final Activity activity) {

		if (this.activities == null)
			this.activities = new ArrayList<Activity>();

		this.activities.add(activity);
		activity.addTrainer(this);
	}

	@OneToOne(optional = true)
	public Curriculum getCurriculum() {
		return this.curriculum;
	}

	//CAMBIAR A FALSE
	@ManyToOne(optional = false)
	public Gym getGym() {
		return this.gym;
	}

	public void setCurriculum(final Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	public void setGym(final Gym gym) {
		this.gym = gym;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.activities == null) ? 0 : this.activities.hashCode());
		result = prime * result + ((this.curriculum == null) ? 0 : this.curriculum.hashCode());
		result = prime * result + ((this.gym == null) ? 0 : this.gym.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final Trainer other = (Trainer) obj;
		if (this.activities == null) {
			if (other.activities != null)
				return false;
		} else if (!this.activities.equals(other.activities))
			return false;
		if (this.curriculum == null) {
			if (other.curriculum != null)
				return false;
		} else if (!this.curriculum.equals(other.curriculum))
			return false;
		if (this.gym == null) {
			if (other.gym != null)
				return false;
		} else if (!this.gym.equals(other.gym))
			return false;
		return true;
	}

}
