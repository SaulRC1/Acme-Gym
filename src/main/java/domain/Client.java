
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Client extends Actor {

	private String					cardOwner;
	private String					cardBrand;
	private String					cardNumber;
	private Integer					monthExpiration;
	private Integer					yearExpiration;
	private Integer					CVV;

	//Relaciones
	private Collection<Activity>	activities;
	private Collection<Inscription>	inscriptions;


	@ManyToMany
	public Collection<Activity> getActivities() {
		return this.activities;
	}

	public void setActivities(final Collection<Activity> activities) {
		this.activities = activities;
	}

	@OneToMany(mappedBy = "client")
	@NotEmpty
	public Collection<Inscription> getInscriptions() {
		return this.inscriptions;
	}

	public void setInscriptions(final Collection<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

	public Client(final String cardOwner, final String cardBrand, final String cardNumber, final Integer monthExpiration, final Integer yearExpiration, final Integer cVV) {
		super();
		this.cardOwner = cardOwner;
		this.cardBrand = cardBrand;
		this.cardNumber = cardNumber;
		this.monthExpiration = monthExpiration;
		this.yearExpiration = yearExpiration;
		this.CVV = cVV;
	}

	@NotBlank
	public String getCardOwner() {
		return this.cardOwner;
	}

	public void setCardOwner(final String cardOwner) {
		this.cardOwner = cardOwner;
	}

	@NotBlank
	public String getCardBrand() {
		return this.cardBrand;
	}

	public void setCardBrand(final String cardBrand) {
		this.cardBrand = cardBrand;
	}

	@NotBlank
	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(final String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getMonthExpiration() {
		return this.monthExpiration;
	}

	public void setMonthExpiration(final Integer monthExpiration) {
		this.monthExpiration = monthExpiration;
	}

	public Integer getYearExpiration() {
		return this.yearExpiration;
	}

	public void setYearExpiration(final Integer yearExpiration) {
		this.yearExpiration = yearExpiration;
	}

	public Integer getCVV() {
		return this.CVV;
	}

	public void setCVV(final Integer cVV) {
		this.CVV = cVV;
	}

}
