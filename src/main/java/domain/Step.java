
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Step {

	private Integer number;
	private String description;
	private String tutorial;

	// Relationships
	private Training training;

	public Step(final Integer number, final String description, final String tutorial) {
		super();
		this.number = number;
		this.description = description;
		this.tutorial = tutorial;
	}

	@URL
	public String getDescription() {
		return this.description;
	}

	@Min(0)
	public Integer getNumber() {
		return this.number;
	}

	@ManyToOne(optional = false)
	public Training getTraining() {
		return this.training;
	}

	@URL
	public String getTutorial() {
		return this.tutorial;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setNumber(final Integer number) {
		this.number = number;
	}

	public void setTraining(final Training training) {
		this.training = training;
	}

	public void setTutorial(final String tutorial) {
		this.tutorial = tutorial;
	}

}
