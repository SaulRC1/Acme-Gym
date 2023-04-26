
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
    private Curriculum curriculum;
    private Gym gym;
    private Collection<Activity> activities;

    public Trainer(String firstName, String lastName, String address, String email, String phoneNumber,
	    String postalCode, String city, String country, Collection<Annotation> annotations, Curriculum curriculum,
	    Gym gym, Collection<Activity> activities) {
	super(firstName, lastName, address, email, phoneNumber, postalCode, city, country, annotations);
	this.curriculum = curriculum;
	this.gym = gym;
	this.activities = activities;
    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    public Collection<Activity> getActivities() {
	return this.activities;
    }

    public void setActivities(Collection<Activity> activities) {
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
     * @param activity The activity to be added
     */
    public void addActivity(Activity activity) {

	if (this.activities == null)
	    this.activities = new ArrayList<Activity>();

	this.activities.add(activity);
	activity.addTrainer(this);
    }

    @OneToOne(optional = false)
    public Curriculum getCurriculum() {
	return this.curriculum;
    }

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

}
