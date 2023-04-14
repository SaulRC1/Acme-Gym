
package domain;

import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Annotation {

	private Date		date;
	private String		text;
	private Integer		mark;

	//Relaciones
	private Gym			gym;
	private Activity	activity;
	private Training	training;
	private Actor		actor;


	@ManyToOne(optional = false)
	public Gym getGym() {
		return this.gym;
	}

	public void setGym(final Gym gym) {
		this.gym = gym;
	}

	@ManyToOne(optional = false)
	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(final Activity activity) {
		this.activity = activity;
	}

	@ManyToOne(optional = false)
	public Training getTraining() {
		return this.training;
	}

	public void setTraining(final Training training) {
		this.training = training;
	}

	@ManyToOne(optional = false)
	public Actor getActor() {
		return this.actor;
	}

	public void setActor(final Actor actor) {
		this.actor = actor;
	}

	public Annotation(final Date date, final String text, final Integer mark) {
		super();
		this.date = date;
		this.text = text;
		this.mark = mark;
	}

	@Temporal(TemporalType.TIME)
	public Date getDate() {
		return this.date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	@NotBlank
	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public Integer getMark() {
		return this.mark;
	}

	public void setMark(final Integer mark) {
		this.mark = mark;
	}

}
