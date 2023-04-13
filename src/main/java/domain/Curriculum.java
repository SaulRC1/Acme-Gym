
package domain;

import java.util.Collection;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotEmpty;

public class Curriculum {

	private String						name;
	private String						speciality;
	private String						education;
	private String						workExperience;

	//Relaciones
	private Trainer						trainer;
	private Collection<SocialIdentity>	socialIdentities;


	@OneToOne(optional = false)
	public Trainer getTrainer() {
		return this.trainer;
	}

	public void setTrainer(final Trainer trainer) {
		this.trainer = trainer;
	}

	@OneToMany(mappedBy = "curriculum")
	@NotEmpty
	public Collection<SocialIdentity> getSocialIdentities() {
		return this.socialIdentities;
	}

	public void setSocialIdentities(final Collection<SocialIdentity> socialIdentities) {
		this.socialIdentities = socialIdentities;
	}

	public Curriculum(final String name, final String speciality, final String education, final String workExperience) {
		super();
		this.name = name;
		this.speciality = speciality;
		this.education = education;
		this.workExperience = workExperience;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSpeciality() {
		return this.speciality;
	}

	public void setSpeciality(final String speciality) {
		this.speciality = speciality;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(final String education) {
		this.education = education;
	}

	public String getWorkExperience() {
		return this.workExperience;
	}

	public void setWorkExperience(final String workExperience) {
		this.workExperience = workExperience;
	}

}
