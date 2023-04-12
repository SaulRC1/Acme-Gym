package domain;

public class Curriculum {
	private String name;
	private String speciality;
	private String education;
	private String workExperience;

	public Curriculum(String name, String speciality, String education, String workExperience) {
		super();
		this.name = name;
		this.speciality = speciality;
		this.education = education;
		this.workExperience = workExperience;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

}
