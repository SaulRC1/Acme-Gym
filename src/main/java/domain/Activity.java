package domain;

import java.sql.Time;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Activity {

	private String tittle;
	private String photo;
	private String description;
	private String weekDays;
	private Time startHour;
	private Time endHour;
	private Integer availableCapacity;

	Gym gym;

	@ManyToOne(optional=false)
	public Gym getGym() {
		return gym;
	}

	public void setGym(Gym gym) {
		this.gym = gym;
	}

	public Activity(String tittle, String photo, String description, String weekDays, Time startHour, Time endHour,
			Integer availableCapacity) {
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
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(String weekDays) {
		this.weekDays = weekDays;
	}

	public Time getStartHour() {
		return startHour;
	}

	public void setStartHour(Time startHour) {
		this.startHour = startHour;
	}

	public Time getEndHour() {
		return endHour;
	}

	public void setEndHour(Time endHour) {
		this.endHour = endHour;
	}

	public Integer getAvailableCapacity() {
		return availableCapacity;
	}

	public void setAvailableCapacity(Integer availableCapacity) {
		this.availableCapacity = availableCapacity;
	}

}
