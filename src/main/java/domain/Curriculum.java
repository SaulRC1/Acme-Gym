
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Curriculum extends DomainEntity {

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

    public Curriculum() {

    }

    @NotBlank
    public String getEducation() {
	return this.education;
    }

    @NotBlank
    public String getName() {
	return this.name;
    }

    @Embedded
    @ElementCollection
    @NotEmpty
    public Collection<SocialIdentity> getSocialIdentities() {
	return this.socialIdentities;
    }

    @NotEmpty
    @ElementCollection
    public Collection<String> getSpecialities() {
	return this.specialities;
    }

    @OneToOne(optional = false, mappedBy = "curriculum")
    public Trainer getTrainer() {
	return this.trainer;
    }

    @NotEmpty
    @ElementCollection
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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((this.education == null) ? 0 : this.education.hashCode());
	result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
	result = prime * result + ((this.socialIdentities == null) ? 0 : this.socialIdentities.hashCode());
	result = prime * result + ((this.specialities == null) ? 0 : this.specialities.hashCode());
	result = prime * result + ((this.trainer == null) ? 0 : this.trainer.hashCode());
	result = prime * result + ((this.workExperience == null) ? 0 : this.workExperience.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (this.getClass() != obj.getClass())
	    return false;
	Curriculum other = (Curriculum) obj;
	if (this.education == null) {
	    if (other.education != null)
		return false;
	} else if (!this.education.equals(other.education))
	    return false;
	if (this.name == null) {
	    if (other.name != null)
		return false;
	} else if (!this.name.equals(other.name))
	    return false;
	if (this.socialIdentities == null) {
	    if (other.socialIdentities != null)
		return false;
	} else if (!this.socialIdentities.equals(other.socialIdentities))
	    return false;
	if (this.specialities == null) {
	    if (other.specialities != null)
		return false;
	} else if (!this.specialities.equals(other.specialities))
	    return false;
	if (this.trainer == null) {
	    if (other.trainer != null)
		return false;
	} else if (!this.trainer.equals(other.trainer))
	    return false;
	if (this.workExperience == null) {
	    if (other.workExperience != null)
		return false;
	} else if (!this.workExperience.equals(other.workExperience))
	    return false;
	return true;
    }

}
