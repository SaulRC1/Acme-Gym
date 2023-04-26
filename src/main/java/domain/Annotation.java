
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

    @ManyToOne
    public Activity getActivity() {
	return this.activity;
    }

    @ManyToOne
    public Actor getActor() {
	return this.actor;
    }

    public LocalDate getDate() {
	return this.date;
    }

    @ManyToOne(optional = false)
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

    @ManyToOne(optional = false)
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

}
