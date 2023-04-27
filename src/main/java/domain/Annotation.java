
package domain;

import java.time.LocalDate;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Annotation extends DomainEntity {

    private LocalDate date;
    private String text;
    private Integer mark;

    // Relationships
    private Gym gym;
    private Activity activity;
    private Training training;
    private Actor actor;

    public Annotation(final LocalDate date, final String text, final Integer mark) {
	super();
	this.date = date;
	this.text = text;
	this.mark = mark;
    }

    public Annotation() {

    }

    @ManyToOne(optional = true)
    public Activity getActivity() {
	return this.activity;
    }

    @ManyToOne(optional = false)
    public Actor getActor() {
	return this.actor;
    }

    public LocalDate getDate() {
	return this.date;
    }

    @ManyToOne(optional = true)
    public Gym getGym() {
	return this.gym;
    }

    @Range(min = 0, max = 3)
    public Integer getMark() {
	return this.mark;
    }

    @NotBlank
    public String getText() {
	return this.text;
    }

    @ManyToOne(optional = true)
    public Training getTraining() {
	return this.training;
    }

    public void setActivity(final Activity activity) {
	this.activity = activity;
    }

    public void setActor(final Actor actor) {
	this.actor = actor;
    }

    public void setDate(final LocalDate date) {
	this.date = date;
    }

    public void setGym(final Gym gym) {
	this.gym = gym;
    }

    public void setMark(final Integer mark) {
	this.mark = mark;
    }

    public void setText(final String text) {
	this.text = text;
    }

    public void setTraining(final Training training) {
	this.training = training;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((this.activity == null) ? 0 : this.activity.hashCode());
	result = prime * result + ((this.actor == null) ? 0 : this.actor.hashCode());
	result = prime * result + ((this.date == null) ? 0 : this.date.hashCode());
	result = prime * result + ((this.gym == null) ? 0 : this.gym.hashCode());
	result = prime * result + ((this.mark == null) ? 0 : this.mark.hashCode());
	result = prime * result + ((this.text == null) ? 0 : this.text.hashCode());
	result = prime * result + ((this.training == null) ? 0 : this.training.hashCode());
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
	Annotation other = (Annotation) obj;
	if (this.activity == null) {
	    if (other.activity != null)
		return false;
	} else if (!this.activity.equals(other.activity))
	    return false;
	if (this.actor == null) {
	    if (other.actor != null)
		return false;
	} else if (!this.actor.equals(other.actor))
	    return false;
	if (this.date == null) {
	    if (other.date != null)
		return false;
	} else if (!this.date.equals(other.date))
	    return false;
	if (this.gym == null) {
	    if (other.gym != null)
		return false;
	} else if (!this.gym.equals(other.gym))
	    return false;
	if (this.mark == null) {
	    if (other.mark != null)
		return false;
	} else if (!this.mark.equals(other.mark))
	    return false;
	if (this.text == null) {
	    if (other.text != null)
		return false;
	} else if (!this.text.equals(other.text))
	    return false;
	if (this.training == null) {
	    if (other.training != null)
		return false;
	} else if (!this.training.equals(other.training))
	    return false;
	return true;
    }

}
