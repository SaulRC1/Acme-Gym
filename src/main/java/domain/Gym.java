
package domain;

import java.util.Collection;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Gym {

	private String					logo;
	private String					address;
	private String					name;
	private Double					fee;
	private boolean					active;

	//Relaciones
	private Manager					manager;
	private Collection<Admin>		admins;
	private Collection<Trainer>		trainers;
	private Collection<Activity>	activities;
	private Collection<Inscription>	inscriptions;
	private Collection<Annotation>	annotations;
	private Collection<Training>	trainings;


	@ManyToOne(optional = false)
	public Manager getManager() {
		return this.manager;
	}

	public void setManager(final Manager manager) {
		this.manager = manager;
	}

	@OneToMany(mappedBy = "gym")
	public Collection<Admin> getAdmins() {
		return this.admins;
	}

	public void setAdmins(final List<Admin> admins) {
		this.admins = admins;
	}

	@OneToMany(mappedBy = "gym")
	public Collection<Trainer> getTrainers() {
		return this.trainers;
	}

	public void setTrainers(final List<Trainer> trainers) {
		this.trainers = trainers;
	}

	@OneToMany(mappedBy = "gym")
	public Collection<Activity> getActivities() {
		return this.activities;
	}

	public void setActivities(final List<Activity> activities) {
		this.activities = activities;
	}

	@OneToMany(mappedBy = "gym")
	public Collection<Inscription> getInscriptions() {
		return this.inscriptions;
	}

	public void setInscriptions(final List<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

	@OneToMany(mappedBy = "gym")
	public Collection<Annotation> getAnnotations() {
		return this.annotations;
	}

	public void setAnnotations(final List<Annotation> annotations) {
		this.annotations = annotations;
	}

	@OneToMany(mappedBy = "gym")
	public Collection<Training> getTrainings() {
		return this.trainings;
	}

	public void setTrainings(final List<Training> trainings) {
		this.trainings = trainings;
	}

	public Gym(final String logo, final String address, final String name, final Double fee, final boolean active) {
		super();
		this.logo = logo;
		this.address = address;
		this.name = name;
		this.fee = fee;
		this.active = active;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(final String logo) {
		this.logo = logo;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Double getFee() {
		return this.fee;
	}

	public void setFee(final Double fee) {
		this.fee = fee;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

}
