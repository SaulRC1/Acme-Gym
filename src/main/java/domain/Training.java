
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Training {

	private String					tittle;
	private String					description;

	//Relaciones
	private Gym						gym;
	private Collection<Step>		steps;
	private Collection<Annotation>	annotations;


	@ManyToOne(optional = false)
	public Gym getGym() {
		return this.gym;
	}

	public void setGym(final Gym gym) {
		this.gym = gym;
	}

	@OneToMany(mappedBy = "training")
	@NotEmpty
	public Collection<Step> getSteps() {
		return this.steps;
	}

	public void setSteps(final Collection<Step> steps) {
		this.steps = steps;
	}

	@OneToMany(mappedBy = "training")
	public Collection<Annotation> getAnotations() {
		return this.annotations;
	}

	public void setAnotations(final Collection<Annotation> anotations) {
		this.annotations = anotations;
	}

	public Training(final String tittle, final String description) {
		super();
		this.tittle = tittle;
		this.description = description;
	}

	public String getTittle() {
		return this.tittle;
	}

	public void setTittle(final String tittle) {
		this.tittle = tittle;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

}
