package domain;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Embeddable
public class CreditCard {

    private String holder;
    private String brand;
    private String number;
    private Integer expirationMonth;
    private Integer expirationYear;
    private Integer CVV;

    public CreditCard(final String owner, final String brand, final String number, final Integer expirationMonth,
	    final Integer expirationYear, final Integer cVV) {
	super();
	this.holder = owner;
	this.brand = brand;
	this.number = number;
	this.expirationMonth = expirationMonth;
	this.expirationYear = expirationYear;
	this.CVV = cVV;
    }

    public CreditCard() {

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
    public String getHolder() {
	return this.holder;
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
    public void setHolder(final String owner) {
	this.holder = owner;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((this.CVV == null) ? 0 : this.CVV.hashCode());
	result = prime * result + ((this.brand == null) ? 0 : this.brand.hashCode());
	result = prime * result + ((this.expirationMonth == null) ? 0 : this.expirationMonth.hashCode());
	result = prime * result + ((this.expirationYear == null) ? 0 : this.expirationYear.hashCode());
	result = prime * result + ((this.number == null) ? 0 : this.number.hashCode());
	result = prime * result + ((this.holder == null) ? 0 : this.holder.hashCode());
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
	CreditCard other = (CreditCard) obj;
	if (this.CVV == null) {
	    if (other.CVV != null)
		return false;
	} else if (!this.CVV.equals(other.CVV))
	    return false;
	if (this.brand == null) {
	    if (other.brand != null)
		return false;
	} else if (!this.brand.equals(other.brand))
	    return false;
	if (this.expirationMonth == null) {
	    if (other.expirationMonth != null)
		return false;
	} else if (!this.expirationMonth.equals(other.expirationMonth))
	    return false;
	if (this.expirationYear == null) {
	    if (other.expirationYear != null)
		return false;
	} else if (!this.expirationYear.equals(other.expirationYear))
	    return false;
	if (this.number == null) {
	    if (other.number != null)
		return false;
	} else if (!this.number.equals(other.number))
	    return false;
	if (this.holder == null) {
	    if (other.holder != null)
		return false;
	} else if (!this.holder.equals(other.holder))
	    return false;
	return true;
    }

}
