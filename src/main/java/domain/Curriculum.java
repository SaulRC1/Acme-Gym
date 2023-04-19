
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

	private String name;
	private Collection<String> specialities;
	private String education;
	private Collection<String> workExperience;

	// Relationships
	private Trainer trainer;
	private Collection<SocialIdentity> socialIdentities;

	public Curriculum(final String name, final Collection<String> specialities, final String education,
			final Collection<String> workExperience) {
		super();
		this.name = name;
		this.specialities = specialities;
		this.education = education;
		this.workExperience = workExperience;
	}

	@NotBlank
	public String getEducation() {
		return this.education;
	}

	@NotBlank
	public String getName() {
		return this.name;
	}

	@OneToMany(mappedBy = "curriculum")
	@NotEmpty
	public Collection<SocialIdentity> getSocialIdentities() {
		return this.socialIdentities;
	}

	@NotEmpty
	public Collection<String> getSpecialities() {
		return this.specialities;
	}

	@OneToOne(optional = false)
	public Trainer getTrainer() {
		return this.trainer;
	}

	@NotEmpty
	public Collection<String> getWorkExperience() {
		return this.workExperience;
	}

	public void setEducation(final String education) {
		this.education = education;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setSocialIdentities(final Collection<SocialIdentity> socialIdentities) {
		this.socialIdentities = socialIdentities;
	}

	public void setSpecialities(final Collection<String> specialities) {
		this.specialities = specialities;
	}

	public void setTrainer(final Trainer trainer) {
		this.trainer = trainer;
	}

	public void setWorkExperience(final Collection<String> workExperience) {
		this.workExperience = workExperience;
	}

}
