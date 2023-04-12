package domain;

public class Step {
	private Integer number;
	private String description;
	private String tutorial;

	public Step(Integer number, String description, String tutorial) {
		super();
		this.number = number;
		this.description = description;
		this.tutorial = tutorial;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTutorial() {
		return tutorial;
	}

	public void setTutorial(String tutorial) {
		this.tutorial = tutorial;
	}

}
