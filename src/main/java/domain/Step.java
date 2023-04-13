
package domain;

import javax.persistence.ManyToOne;

public class Step {

	private Integer		number;
	private String		description;
	private String		tutorial;

	//Relaciones
	private Training	training;


	@ManyToOne(optional = false)
	public Training getTraining() {
		return this.training;
	}

	public void setTraining(final Training training) {
		this.training = training;
	}

	public Step(final Integer number, final String description, final String tutorial) {
		super();
		this.number = number;
		this.description = description;
		this.tutorial = tutorial;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(final Integer number) {
		this.number = number;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getTutorial() {
		return this.tutorial;
	}

	public void setTutorial(final String tutorial) {
		this.tutorial = tutorial;
	}

}
