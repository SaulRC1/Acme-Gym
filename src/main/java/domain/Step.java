
package domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

@Embeddable
public class Step {

    private Integer number;
    private String description;
    private String tutorial;

    public Step(final Integer number, final String description, final String tutorial) {
	super();
	this.number = number;
	this.description = description;
	this.tutorial = tutorial;
    }

    public Step() {

    }

    @NotNull
    public String getDescription() {
	return this.description;
    }

    @Min(0)
    public Integer getNumber() {
	return this.number;
    }

    @URL
    public String getTutorial() {
	return this.tutorial;
    }

    public void setDescription(final String description) {
	this.description = description;
    }

    public void setNumber(final Integer number) {
	this.number = number;
    }

    public void setTutorial(final String tutorial) {
	this.tutorial = tutorial;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
	result = prime * result + ((this.number == null) ? 0 : this.number.hashCode());
	result = prime * result + ((this.tutorial == null) ? 0 : this.tutorial.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (this.getClass() != obj.getClass())
	    return false;
	Step other = (Step) obj;
	if (this.description == null) {
	    if (other.description != null)
		return false;
	} else if (!this.description.equals(other.description))
	    return false;
	if (this.number == null) {
	    if (other.number != null)
		return false;
	} else if (!this.number.equals(other.number))
	    return false;
	if (this.tutorial == null) {
	    if (other.tutorial != null)
		return false;
	} else if (!this.tutorial.equals(other.tutorial))
	    return false;
	return true;
    }

}
