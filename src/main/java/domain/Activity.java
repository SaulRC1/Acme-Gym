
package domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Activity extends DomainEntity {

    private String title;
    private Collection<String> photos;
    private String description;
    private Set<DayOfWeek> daysOfWeek;
    private LocalTime startHour;
    private LocalTime endHour;
    private Integer availablePlaces;

    // Relationships
    private Gym gym;
    private Collection<Trainer> trainers;
    private Collection<Annotation> annotations;
    private Collection<Client> clients;

    public Activity(final String tittle, final Collection<String> photos, final String description,
	    final Set<DayOfWeek> daysOfWeek, final LocalTime startHour, final LocalTime endHour,
	    final Integer availablePlaces) {
	super();
	this.title = tittle;
	this.photos = photos;
	this.description = description;
	this.daysOfWeek = daysOfWeek;
	this.startHour = startHour;
	this.endHour = endHour;
	this.availablePlaces = availablePlaces;
    }

    @OneToMany(mappedBy = "activity")
    public Collection<Annotation> getAnnotations() {
	return this.annotations;
    }

    @NotNull
    @Min(0) // Comprobar
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

    @NotNull
    public LocalTime getEndHour() {
	return this.endHour;
    }

    @ManyToOne(optional = false)
    public Gym getGym() {
	return this.gym;
    }

    @URL
    @ElementCollection
    public Collection<String> getPhotos() {
	return this.photos;
    }

    @NotNull
    public LocalTime getStartHour() {
	return this.startHour;
    }

    @NotBlank
    public String getTitle() {
	return this.title;
    }

    @ManyToMany(mappedBy = "activities")
    @NotEmpty
    public Collection<Trainer> getTrainers() {
	return this.trainers;
    }

    @NotEmpty
    @ElementCollection
    public Set<DayOfWeek> getDaysOfWeek() {
	return this.daysOfWeek;
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

    public void setPhotos(final Collection<String> photos) {
	this.photos = photos;
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

    public void setDaysOfWeek(final Set<DayOfWeek> daysOfWeek) {
	this.daysOfWeek = daysOfWeek;
    }

}
