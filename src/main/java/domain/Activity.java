
package domain;

import java.sql.Time;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Activity {

	private String					tittle;
	private String					photo;
	private String					description;
	private String					weekDays;
	private Time					startHour;
	private Time					endHour;
	private Integer					availableCapacity;

	//Relaciones
	private Gym						gym;
	private Collection<Trainer>		trainers;
	private Collection<Annotation>	annotations;
	private Collection<Client>		clients;


	@ManyToOne(optional = false)
	public Gym getGym() {
		return this.gym;
	}

	public void setGym(final Gym gym) {
		this.gym = gym;
	}

	@OneToMany(mappedBy = "activity")
	@NotEmpty
	public Collection<Trainer> getTrainers() {
		return this.trainers;
	}

	public void setTrainers(final Collection<Trainer> trainers) {
		this.trainers = trainers;
	}

	@OneToMany(mappedBy = "activity")
	public Collection<Annotation> getAnnotations() {
		return this.annotations;
	}

	public void setAnnotations(final Collection<Annotation> annotations) {
		this.annotations = annotations;
	}

	@ManyToMany
	public Collection<Client> getClients() {
		return this.clients;
	}

	public void setClients(final Collection<Client> clients) {
		this.clients = clients;
	}

	public Activity(final String tittle, final String photo, final String description, final String weekDays, final Time startHour, final Time endHour, final Integer availableCapacity) {
		super();
		this.tittle = tittle;
		this.photo = photo;
		this.description = description;
		this.weekDays = weekDays;
		this.startHour = startHour;
		this.endHour = endHour;
		this.availableCapacity = availableCapacity;
	}

	public String getTittle() {
		return this.tittle;
	}

	public void setTittle(final String tittle) {
		this.tittle = tittle;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getWeekDays() {
		return this.weekDays;
	}

	public void setWeekDays(final String weekDays) {
		this.weekDays = weekDays;
	}

	public Time getStartHour() {
		return this.startHour;
	}

	public void setStartHour(final Time startHour) {
		this.startHour = startHour;
	}

	public Time getEndHour() {
		return this.endHour;
	}

	public void setEndHour(final Time endHour) {
		this.endHour = endHour;
	}

	public Integer getAvailableCapacity() {
		return this.availableCapacity;
	}

	public void setAvailableCapacity(final Integer availableCapacity) {
		this.availableCapacity = availableCapacity;
	}

}
