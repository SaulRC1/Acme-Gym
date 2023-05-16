
package domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
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

	private String					title;
	private String					photo;
	private String					description;
	private Set<DayOfWeek>			daysOfWeek;
	private LocalTime				startHour;
	private LocalTime				endHour;
	private Integer					availablePlaces;
	private boolean					active;

	// Relationships
	private Gym						gym;
	private Collection<Trainer>		trainers;
	private Collection<Annotation>	annotations;
	private Collection<Client>		clients;


	public Activity(final String tittle, final String photo, final String description, final Set<DayOfWeek> daysOfWeek, final LocalTime startHour, final LocalTime endHour, final Integer availablePlaces) {
		super();
		this.title = tittle;
		this.photo = photo;
		this.description = description;
		this.daysOfWeek = daysOfWeek;
		this.startHour = startHour;
		this.endHour = endHour;
		this.availablePlaces = availablePlaces;
	}

	public Activity() {

	}

	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true)
	public Collection<Annotation> getAnnotations() {
		return this.annotations;
	}

	@NotNull
	@Min(0) // Comprobar
	public Integer getAvailablePlaces() {
		return this.availablePlaces;
	}

	@ManyToMany(mappedBy = "activities")
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
	public String getPhoto() {
		return this.photo;
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

	public boolean isActive() {
		return this.active;
	}

	public void setActive(final boolean active) {
		this.active = active;
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

	public void setDaysOfWeek(final Set<DayOfWeek> daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.annotations == null) ? 0 : this.annotations.hashCode());
		result = prime * result + ((this.availablePlaces == null) ? 0 : this.availablePlaces.hashCode());
		result = prime * result + ((this.clients == null) ? 0 : this.clients.hashCode());
		result = prime * result + ((this.daysOfWeek == null) ? 0 : this.daysOfWeek.hashCode());
		result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
		result = prime * result + ((this.endHour == null) ? 0 : this.endHour.hashCode());
		result = prime * result + ((this.gym == null) ? 0 : this.gym.hashCode());
		result = prime * result + ((this.photo == null) ? 0 : this.photo.hashCode());
		result = prime * result + ((this.startHour == null) ? 0 : this.startHour.hashCode());
		result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
		result = prime * result + ((this.trainers == null) ? 0 : this.trainers.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (this.getClass() != obj.getClass())
			return false;
		final Activity other = (Activity) obj;
		if (this.annotations == null) {
			if (other.annotations != null)
				return false;
		} else if (!this.annotations.equals(other.annotations))
			return false;
		if (this.availablePlaces == null) {
			if (other.availablePlaces != null)
				return false;
		} else if (!this.availablePlaces.equals(other.availablePlaces))
			return false;
		if (this.clients == null) {
			if (other.clients != null)
				return false;
		} else if (!this.clients.equals(other.clients))
			return false;
		if (this.daysOfWeek == null) {
			if (other.daysOfWeek != null)
				return false;
		} else if (!this.daysOfWeek.equals(other.daysOfWeek))
			return false;
		if (this.description == null) {
			if (other.description != null)
				return false;
		} else if (!this.description.equals(other.description))
			return false;
		if (this.endHour == null) {
			if (other.endHour != null)
				return false;
		} else if (!this.endHour.equals(other.endHour))
			return false;
		if (this.gym == null) {
			if (other.gym != null)
				return false;
		} else if (!this.gym.equals(other.gym))
			return false;
		if (this.photo == null) {
			if (other.photo != null)
				return false;
		} else if (!this.photo.equals(other.photo))
			return false;
		if (this.startHour == null) {
			if (other.startHour != null)
				return false;
		} else if (!this.startHour.equals(other.startHour))
			return false;
		if (this.title == null) {
			if (other.title != null)
				return false;
		} else if (!this.title.equals(other.title))
			return false;
		if (this.trainers == null) {
			if (other.trainers != null)
				return false;
		} else if (!this.trainers.equals(other.trainers))
			return false;
		return true;
	}

	/**
	 * Will add a trainer to train this activity.<br>
	 * <br>
	 *
	 * DO NOT CALL THIS METHOD. TO PROPERLY ADD A TRAINER TO AN ACTIVITY, USE METHOD
	 * {@linkplain Trainer#addActivity(Activity)}}.
	 *
	 * @param trainer
	 *            The trainer to be added.
	 */
	public void addTrainer(final Trainer trainer) {

		if (trainer != null) {

			if (this.trainers == null)
				this.trainers = new ArrayList<Trainer>();

			this.trainers.add(trainer);
		}
	}

	/**
	 * Will add a client to this activity.<br>
	 * <br>
	 *
	 * DO NOT CALL THIS METHOD. TO PROPERLY ADD A CLIENT TO AN ACTIVITY, USE METHOD
	 * {@linkplain Client#addActivity(Activity)}}.
	 *
	 * @param client
	 *            The client to be added.
	 */
	public void addClient(final Client client) {

		if (client != null) {

			if (this.clients == null)
				this.clients = new ArrayList<Client>();

			this.clients.add(client);
		}
	}

	/**
	 * Will add an annotation to this activity.<br>
	 * <br>
	 *
	 * NOTE THAT CALLING THIS METHOD WILL ALSO ADD THIS ACTIVITY TO THE ANNOTATION
	 * PASSED BY PARAMETER.
	 *
	 * @param annotation
	 *            The annotation to be added.
	 */
	public void addAnnotation(final Annotation annotation) {

		if (annotation != null) {

			if (this.annotations == null)
				this.annotations = new ArrayList<Annotation>();

			this.annotations.add(annotation);
			annotation.setActivity(this);
		}
	}

	/**
	 * Will remove an annotation from this activity.<br>
	 * <br>
	 *
	 * NOTE THAT CALLING THIS METHOD WILL ALSO REMOVE THIS ACTIVITY FROM THE
	 * ANNOTATION PASSED BY PARAMETER.
	 *
	 * @param annotation
	 *            The annotation to be removed.
	 */
	public void removeAnnotation(final Annotation annotation) {

		if (annotation != null && this.annotations != null) {
			this.annotations.remove(annotation);
			annotation.setActivity(null);
		}
	}

}
