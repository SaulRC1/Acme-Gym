
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Manager extends Actor {

	private boolean	estado;

	//Relaciones
	Collection<Gym>	gyms;


	@OneToMany(mappedBy = "manager")
	@NotEmpty
	public Collection<Gym> getGyms() {
		return this.gyms;
	}

	public void setGyms(final Collection<Gym> gyms) {
		this.gyms = gyms;
	}

	public Manager(final boolean estado) {
		super();
		this.estado = estado;
	}

	public boolean isEstado() {
		return this.estado;
	}

	public void setEstado(final boolean estado) {
		this.estado = estado;
	}

}
