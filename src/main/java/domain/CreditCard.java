package domain;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Embeddable
public class CreditCard {

    private String owner;
    private String brand;
    private String number;
    private Integer expirationMonth;
    private Integer expirationYear;
    private Integer CVV;

    public CreditCard(final String owner, final String brand, final String number, final Integer expirationMonth,
	    final Integer expirationYear, final Integer cVV) {
	super();
	this.owner = owner;
	this.brand = brand;
	this.number = number;
	this.expirationMonth = expirationMonth;
	this.expirationYear = expirationYear;
	this.CVV = cVV;
    }

    @NotBlank
    public String getBrand() {
	return this.brand;
    }

    @Range(min = 100, max = 999)
    public Integer getCVV() {
	return this.CVV;
    }

    @Range(min = 1, max = 12)
    public Integer getExpirationMonth() {
	return this.expirationMonth;
    }

    public Integer getExpirationYear() {
	return this.expirationYear;
    }

    @CreditCardNumber
    public String getNumber() {
	return this.number;
    }

    @NotBlank
    public String getOwner() {
	return this.owner;
    }

    public void setBrand(final String brand) {
	this.brand = brand;
    }

    public void setCVV(final Integer cVV) {
	this.CVV = cVV;
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
