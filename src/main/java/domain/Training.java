
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Training {

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

	@OneToMany(cascade = CascadeType.ALL)
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

}
