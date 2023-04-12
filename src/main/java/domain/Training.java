package domain;

public class Training {
	private String tittle;
	private String description;

	public Training(String tittle, String description) {
		super();
		this.tittle = tittle;
		this.description = description;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
