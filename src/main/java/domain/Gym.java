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

	private String logo;
	private String address;
	private String name;
	private Double fee;
	private boolean active;
	
	Manager manager;
	
	Collection<Admin> admins;
	
	Collection<Trainer> trainers;
	
	Collection<Activity> activities;
	
	Collection<Inscription> inscriptions;
	
	Collection<Annotation> annotations;
	
	Collection<Training> trainings;
		
	@ManyToOne(optional=false)
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@OneToMany(mappedBy="gym")
	public Collection<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}

	@OneToMany(mappedBy="gym")
	public Collection<Trainer> getTrainers() {
		return trainers;
	}

	public void setTrainers(List<Trainer> trainers) {
		this.trainers = trainers;
	}

	@OneToMany(mappedBy="gym")
	public Collection<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	@OneToMany(mappedBy="gym")
	public Collection<Inscription> getInscriptions() {
		return inscriptions;
	}

	public void setInscriptions(List<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

	@OneToMany//Revisar ahora
	public Collection<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

	@OneToMany(mappedBy="gym")
	public Collection<Training> getTrainings() {
		return trainings;
	}

	public void setTrainings(List<Training> trainings) {
		this.trainings = trainings;
	}

	public Gym(String logo, String address, String name, Double fee, boolean active) {
		super();
		this.logo = logo;
		this.address = address;
		this.name = name;
		this.fee = fee;
		this.active = active;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
