
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Training extends DomainEntity {

    private String title;
    private String description;

    // Relationships
    private Gym gym;
    private Collection<Step> steps;
    private Collection<Annotation> annotations;

    public Training(String title, String description, Gym gym, Collection<Step> steps,
	    Collection<Annotation> annotations) {
	super();
	this.title = title;
	this.description = description;
	this.gym = gym;
	this.steps = steps;
	this.annotations = annotations;
    }

    public Training() {
    }

    @OneToMany(mappedBy = "training")
    public Collection<Annotation> getAnotations() {
	return this.annotations;
    }

    @NotBlank
    public String getDescription() {
	return this.description;
    }

    @ManyToOne(optional = false)
    public Gym getGym() {
	return this.gym;
    }

    @Embedded
    @ElementCollection
    @NotEmpty
    public Collection<Step> getSteps() {
	return this.steps;
    }

    @NotBlank
    public String getTittle() {
	return this.title;
    }

    public void setAnotations(final Collection<Annotation> anotations) {
	this.annotations = anotations;
    }

    public void setDescription(final String description) {
	this.description = description;
    }

    public void setGym(final Gym gym) {
	this.gym = gym;
    }

    public void setSteps(final Collection<Step> steps) {
	this.steps = steps;
    }

    public void setTittle(final String title) {
	this.title = title;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((this.annotations == null) ? 0 : this.annotations.hashCode());
	result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
	result = prime * result + ((this.gym == null) ? 0 : this.gym.hashCode());
	result = prime * result + ((this.steps == null) ? 0 : this.steps.hashCode());
	result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
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
	Training other = (Training) obj;
	if (this.annotations == null) {
	    if (other.annotations != null)
		return false;
	} else if (!this.annotations.equals(other.annotations))
	    return false;
	if (this.description == null) {
	    if (other.description != null)
		return false;
	} else if (!this.description.equals(other.description))
	    return false;
	if (this.gym == null) {
	    if (other.gym != null)
		return false;
	} else if (!this.gym.equals(other.gym))
	    return false;
	if (this.steps == null) {
	    if (other.steps != null)
		return false;
	} else if (!this.steps.equals(other.steps))
	    return false;
	if (this.title == null) {
	    if (other.title != null)
		return false;
	} else if (!this.title.equals(other.title))
	    return false;
	return true;
    }

}
