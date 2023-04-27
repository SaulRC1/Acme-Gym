
package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Gym extends DomainEntity {

    private String logo;
    private String address;
    private String name;
    private Double fee;
    private boolean active;

    // Relationships
    private Collection<Manager> managers;
    private Collection<Trainer> trainers;
    private Collection<Activity> activities;
    private Collection<Inscription> inscriptions;
    private Collection<Annotation> annotations;
    private Collection<Training> trainings;

    public Gym(final String logo, final String address, final String name, final Double fee, final boolean active) {
	super();
	this.logo = logo;
	this.address = address;
	this.name = name;
	this.fee = fee;
	this.active = active;
    }

    public Gym() {

    }

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<Activity> getActivities() {
	return this.activities;
    }

    @NotBlank
    public String getAddress() {
	return this.address;
    }

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<Annotation> getAnnotations() {
	return this.annotations;
    }

    @Min(0)
    @Digits(integer = 9, fraction = 2)
    public Double getFee() {
	return this.fee;
    }

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<Inscription> getInscriptions() {
	return this.inscriptions;
    }

    @URL
    public String getLogo() {
	return this.logo;
    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    public Collection<Manager> getManagers() {
	return this.managers;
    }

    @NotBlank
    public String getName() {
	return this.name;
    }

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<Trainer> getTrainers() {
	return this.trainers;
    }

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<Training> getTrainings() {
	return this.trainings;
    }

    public boolean isActive() {
	return this.active;
    }

    public void setActive(final boolean active) {
	this.active = active;
    }

    public void setActivities(final List<Activity> activities) {
	this.activities = activities;
    }

    public void setAddress(final String address) {
	this.address = address;
    }

    public void setAnnotations(final List<Annotation> annotations) {
	this.annotations = annotations;
    }

    public void setFee(final Double fee) {
	this.fee = fee;
    }

    public void setInscriptions(final List<Inscription> inscriptions) {
	this.inscriptions = inscriptions;
    }

    public void setLogo(final String logo) {
	this.logo = logo;
    }

    public void setManagers(final Collection<Manager> managers) {
	this.managers = managers;
    }

    public void setName(final String name) {
	this.name = name;
    }

    public void setTrainers(final List<Trainer> trainers) {
	this.trainers = trainers;
    }

    public void setTrainings(final List<Training> trainings) {
	this.trainings = trainings;
    }

    /**
     * Adds a manager to this gym.<br>
     * <br>
     *
     * CALLING THIS METHOD WILL ALSO ADD THIS GYM INSIDE THE MANAGER OBJECT PASSED
     * BY PARAMETER. DO NOT CALL {@link Manager#addGym(Gym)} WHEN USING THIS METHOD.
     *
     * @param manager
     */
    public void addManager(Manager manager) {

	if (manager != null) {

	    if (this.managers == null)
		this.managers = new ArrayList<Manager>();

	    this.managers.add(manager);
	    manager.addGym(this);
	}

    }

    /**
     * Adds a trainer to this gym.
     *
     * THIS METHOD WILL ALSO ADD THIS GYM TO THE TRAINER PASSED BY PARAMETER.
     *
     * @param trainer The trainer to be added.
     */
    public void addTrainer(Trainer trainer) {

	if (trainer != null) {

	    if (this.trainers == null)
		this.trainers = new ArrayList<Trainer>();

	    this.trainers.add(trainer);
	    trainer.setGym(this);
	}

    }

    /**
     * Will remove a trainer from this gym.<br>
     * <br>
     *
     * NOTE THAT THIS WILL ALSO REMOVE THIS GYM FROM THE TRAINER PASSED BY
     * PARAMETER.
     *
     * @param trainer
     */
    public void removeTrainer(Trainer trainer) {

	if (trainer != null && this.trainers != null) {
	    this.trainers.remove(trainer);
	    trainer.setGym(null);
	}
    }

    /**
     * Will add an inscription to this gym.<br>
     * <br>
     *
     * NOTE THAT THIS METHOD WILL ALSO ADD THIS GYM TO THE INSCRIPTION PASSED BY
     * PARAMETER.
     *
     * @param inscription The inscription to be added.
     */
    public void addInscription(Inscription inscription) {

	if (inscription != null) {

	    if (this.inscriptions == null)
		this.inscriptions = new ArrayList<Inscription>();

	    this.inscriptions.add(inscription);
	    inscription.setGym(this);
	}
    }

    /**
     * Will remove an inscription from this gym.<br>
     * <br>
     *
     * NOTE THAT THIS METHOD WILL ALSO REMOVE THIS GYM FROM THE INSCRIPTION PASSED
     * BY PARAMETER
     *
     * @param inscription
     */
    public void removeInscription(Inscription inscription) {

	if (inscription != null && this.inscriptions != null) {

	    this.inscriptions.remove(inscription);
	    inscription.setGym(null);
	}
    }

    /**
     * Will add an activity to this gym.<br>
     * <br>
     *
     * NOTE THAT THIS METHOD WILL ALSO ADD THIS GYM TO THE ACTIVITY PASSED BY
     * PARAMETER
     *
     * @param activity The activity to be added.
     */
    public void addActivity(Activity activity) {

	if (activity != null) {

	    if (this.activities == null)
		this.activities = new ArrayList<Activity>();

	    this.activities.add(activity);
	    activity.setGym(this);
	}
    }

    /**
     * Will remove an activity from this gym<br>
     * <br>
     *
     * NOTE THAT THIS METHOD WILL ALSO DELETE THIS GYM FROM THE ACTIVITY PASSED BY
     * PARAMETER.
     *
     * @param activity The activity to be deleted.
     */
    public void removeActivity(Activity activity) {

	if (activity != null && this.activities != null) {
	    this.activities.remove(activity);
	    activity.setGym(null);
	}
    }

    /**
     * Will add a training/workout to this gym.<br>
     * <br>
     *
     * NOTE THAT THIS METHOD WILL ALSO ADD THIS GYM TO THE TRAINING PASSED BY
     * PARAMETER.
     *
     * @param training The training to be added.
     */
    public void addTraining(Training training) {

	if (training != null) {

	    if (this.trainings == null)
		this.trainings = new ArrayList<Training>();

	    this.trainings.add(training);
	    training.setGym(this);
	}
    }

    /**
     * Will remove a training from this gym.<br>
     * <br>
     *
     * NOTE THAT THIS METHOD WILL ALSO REMOVE THIS GYM FROM THE TRAINING PASSED BY
     * PARAMETER.
     *
     * @param training The training to be removed.
     */
    public void removeTraining(Training training) {

	if (training != null && this.trainings != null) {
	    this.trainings.remove(training);
	    training.setGym(null);
	}
    }

    /**
     * Will add an annotation to this gym.<br>
     * <br>
     *
     * NOTE THAT THIS METHOD WILL ALSO ADD THIS GYM TO THE ANNOTATION PASSED BY
     * PARAMETER.
     *
     * @param annotation The annotation to be added.
     */
    public void addAnnotation(Annotation annotation) {

	if (annotation != null) {

	    if (this.annotations == null)
		this.annotations = new ArrayList<Annotation>();

	    this.annotations.add(annotation);
	    annotation.setGym(this);
	}
    }

    /**
     * Will remove an annotation from this gym.<br>
     * <br>
     *
     * NOTE THAT THIS METHOD WILL ALSO REMOVE THIS GYM FROM THE ANNOTATION PASSED BY
     * PARAMETER.
     *
     * @param annotation
     */
    public void removeAnnotation(Annotation annotation) {

	if (annotation != null && this.annotations != null) {
	    this.annotations.remove(annotation);
	    annotation.setGym(null);
	}
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + (this.active ? 1231 : 1237);
	result = prime * result + ((this.activities == null) ? 0 : this.activities.hashCode());
	result = prime * result + ((this.address == null) ? 0 : this.address.hashCode());
	result = prime * result + ((this.annotations == null) ? 0 : this.annotations.hashCode());
	result = prime * result + ((this.fee == null) ? 0 : this.fee.hashCode());
	result = prime * result + ((this.inscriptions == null) ? 0 : this.inscriptions.hashCode());
	result = prime * result + ((this.logo == null) ? 0 : this.logo.hashCode());
	result = prime * result + ((this.managers == null) ? 0 : this.managers.hashCode());
	result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
	result = prime * result + ((this.trainers == null) ? 0 : this.trainers.hashCode());
	result = prime * result + ((this.trainings == null) ? 0 : this.trainings.hashCode());
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
	Gym other = (Gym) obj;
	if (this.active != other.active)
	    return false;
	if (this.activities == null) {
	    if (other.activities != null)
		return false;
	} else if (!this.activities.equals(other.activities))
	    return false;
	if (this.address == null) {
	    if (other.address != null)
		return false;
	} else if (!this.address.equals(other.address))
	    return false;
	if (this.annotations == null) {
	    if (other.annotations != null)
		return false;
	} else if (!this.annotations.equals(other.annotations))
	    return false;
	if (this.fee == null) {
	    if (other.fee != null)
		return false;
	} else if (!this.fee.equals(other.fee))
	    return false;
	if (this.inscriptions == null) {
	    if (other.inscriptions != null)
		return false;
	} else if (!this.inscriptions.equals(other.inscriptions))
	    return false;
	if (this.logo == null) {
	    if (other.logo != null)
		return false;
	} else if (!this.logo.equals(other.logo))
	    return false;
	if (this.managers == null) {
	    if (other.managers != null)
		return false;
	} else if (!this.managers.equals(other.managers))
	    return false;
	if (this.name == null) {
	    if (other.name != null)
		return false;
	} else if (!this.name.equals(other.name))
	    return false;
	if (this.trainers == null) {
	    if (other.trainers != null)
		return false;
	} else if (!this.trainers.equals(other.trainers))
	    return false;
	if (this.trainings == null) {
	    if (other.trainings != null)
		return false;
	} else if (!this.trainings.equals(other.trainings))
	    return false;
	return true;
    }

}
