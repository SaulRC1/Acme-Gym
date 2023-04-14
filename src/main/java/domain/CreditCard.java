package domain;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class CreditCard {

	private String owner;
	private String brand;
	private String number;
	private Integer expirationMonth;
	private Integer expirationYear;
	private Integer CVV;

	public CreditCard(final String owner, final String brand, final String number, final Integer expirationMonth, final Integer expirationYear,
			final Integer cVV) {
		super();
		this.owner = owner;
		this.brand = brand;
		this.number = number;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		CVV = cVV;
	}

	public String getBrand() {
		return brand;
	}

	public Integer getCVV() {
		return CVV;
	}

	public Integer getExpirationMonth() {
		return expirationMonth;
	}

	public Integer getExpirationYear() {
		return expirationYear;
	}

	@CreditCardNumber
	public String getNumber() {
		return number;
	}

	@NotBlank
	public String getOwner() {
		return owner;
	}

	public void setBrand(final String brand) {
		this.brand = brand;
	}

	public void setCVV(final Integer cVV) {
		CVV = cVV;
	}

	public void setExpirationMonth(final Integer expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public void setExpirationYear(final Integer expirationYear) {
		this.expirationYear = expirationYear;
	}

	public void setNumber(final String number) {
		this.number = number;
	}

	@NotBlank
	public void setOwner(final String owner) {
		this.owner = owner;
	}
}
