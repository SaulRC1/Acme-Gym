
package domain;

import java.util.Collection;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Gym extends DomainEntity {

    private String logo;
    private String address;
    private String name;
    private Double fee;
    private boolean active;

    // Relationships
    private Manager manager;
    private Collection<Trainer> trainers;
    private Collection<Activity> activities;
    private Collection<Inscription> inscriptions;
    private Collection<Annotation> annotations;
    private Collection<Training> trainings;
    private Collection<Admin> administrators;

    public Gym(final String logo, final String address, final String name, final Double fee, final boolean active) {
	super();
	this.logo = logo;
	this.address = address;
	this.name = name;
	this.fee = fee;
	this.active = active;
    }

    @OneToMany(mappedBy = "gym")
    public Collection<Activity> getActivities() {
	return this.activities;
    }

    @NotBlank
    public String getAddress() {
	return this.address;
    }

    @OneToMany(mappedBy = "gym")
    public Collection<Annotation> getAnnotations() {
	return this.annotations;
    }

    @Min(0)
    @Digits(integer = 9, fraction = 2)
    public Double getFee() {
	return this.fee;
    }

    @OneToMany(mappedBy = "gym")
    public Collection<Inscription> getInscriptions() {
	return this.inscriptions;
    }

    @URL
    public String getLogo() {
	return this.logo;
    }

    @ManyToOne(optional = false)
    public Manager getManager() {
	return this.manager;
    }

    @NotBlank
    public String getName() {
	return this.name;
    }

    @OneToMany(mappedBy = "gym")
    public Collection<Trainer> getTrainers() {
	return this.trainers;
    }

    @OneToMany(mappedBy = "gym")
    public Collection<Training> getTrainings() {
	return this.trainings;
    }

    public boolean isActive() {
	return this.active;
    }

    public void setActive(final boolean active) {
	this.active = active;
    }

    public void setActivities(final List<Activity> activities) {
	this.activities = activities;
    }

    public void setAddress(final String address) {
	this.address = address;
    }

    public void setAnnotations(final List<Annotation> annotations) {
	this.annotations = annotations;
    }

    public void setFee(final Double fee) {
	this.fee = fee;
    }

    public void setInscriptions(final List<Inscription> inscriptions) {
	this.inscriptions = inscriptions;
    }

    public void setLogo(final String logo) {
	this.logo = logo;
    }

    public void setManager(final Manager manager) {
	this.manager = manager;
    }

    public void setName(final String name) {
	this.name = name;
    }

    public void setTrainers(final List<Trainer> trainers) {
	this.trainers = trainers;
    }

    public void setTrainings(final List<Training> trainings) {
	this.trainings = trainings;
    }

    @OneToMany(mappedBy = "gym")
    public Collection<Admin> getAdministrators() {
	return this.administrators;
    }

    public void setAdministrators(final Collection<Admin> administrators) {
	this.administrators = administrators;
    }

}
