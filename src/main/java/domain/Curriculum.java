
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
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

	@NotBlank
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	public String getSpeciality() {
		return this.speciality;
	}

	public void setSpeciality(final String speciality) {
		this.speciality = speciality;
	}

	@NotBlank
	public String getEducation() {
		return this.education;
	}

	public void setEducation(final String education) {
		this.education = education;
	}

	@NotBlank
	public String getWorkExperience() {
		return this.workExperience;
	}

	public void setWorkExperience(final String workExperience) {
		this.workExperience = workExperience;
	}

}
