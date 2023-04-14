
package domain;

import java.time.LocalTime;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Activity {

    private String title;
    private String photo;
    private String description;
    private String weekDays;
    private LocalTime startHour;
    private LocalTime endHour;
    private Integer availablePlaces;

    // Relationships
    private Gym gym;
    private Collection<Trainer> trainers;
    private Collection<Annotation> annotations;
    private Collection<Client> clients;

    public Activity(final String tittle, final String photo, final String description, final String weekDays,
	    final LocalTime startHour, final LocalTime endHour, final Integer availablePlaces) {
	super();
	this.title = tittle;
	this.photo = photo;
	this.description = description;
	this.weekDays = weekDays;
	this.startHour = startHour;
	this.endHour = endHour;
	this.availablePlaces = availablePlaces;
    }

    @OneToMany(mappedBy = "activity")
    public Collection<Annotation> getAnnotations() {
	return this.annotations;
    }

    public Integer getAvailablePlaces() {
	return this.availablePlaces;
    }

    @ManyToMany
    public Collection<Client> getClients() {
	return this.clients;
    }

    @NotBlank
    public String getDescription() {
	return this.description;
    }

    public LocalTime getEndHour() {
	return this.endHour;
    }

    @ManyToOne(optional = false)
    public Gym getGym() {
	return this.gym;
    }

    @URL
    public String getPhoto() {
	return this.photo;
    }

    public LocalTime getStartHour() {
	return this.startHour;
    }

    @NotBlank
    public String getTitle() {
	return this.title;
    }

    @OneToMany(mappedBy = "activity")
    @NotEmpty
    public Collection<Trainer> getTrainers() {
	return this.trainers;
    }

    public String getWeekDays() {
	return this.weekDays;
    }

    public void setAnnotations(final Collection<Annotation> annotations) {
	this.annotations = annotations;
    }

    public void setAvailablePlaces(final Integer availablePlaces) {
	this.availablePlaces = availablePlaces;
    }

    public void setClients(final Collection<Client> clients) {
	this.clients = clients;
    }

    public void setDescription(final String description) {
	this.description = description;
    }

    public void setEndHour(final LocalTime endHour) {
	this.endHour = endHour;
    }

    public void setGym(final Gym gym) {
	this.gym = gym;
    }

    public void setPhoto(final String photo) {
	this.photo = photo;
    }

    public void setStartHour(final LocalTime startHour) {
	this.startHour = startHour;
    }

    public void setTitle(final String title) {
	this.title = title;
    }

    public void setTrainers(final Collection<Trainer> trainers) {
	this.trainers = trainers;
    }

    public void setWeekDays(final String weekDays) {
	this.weekDays = weekDays;
    }

}
