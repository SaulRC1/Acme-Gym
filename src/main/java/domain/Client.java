package domain;

public class Client extends Actor {
	private String cardOwner;
	private String cardBrand;
	private String cardNumber;
	private Integer monthExpiration;
	private Integer yearExpiration;
	private Integer CVV;

	public Client(String cardOwner, String cardBrand, String cardNumber, Integer monthExpiration,
			Integer yearExpiration, Integer cVV) {
		super();
		this.cardOwner = cardOwner;
		this.cardBrand = cardBrand;
		this.cardNumber = cardNumber;
		this.monthExpiration = monthExpiration;
		this.yearExpiration = yearExpiration;
		CVV = cVV;
	}

	public String getCardOwner() {
		return cardOwner;
	}

	public void setCardOwner(String cardOwner) {
		this.cardOwner = cardOwner;
	}

	public String getCardBrand() {
		return cardBrand;
	}

	public void setCardBrand(String cardBrand) {
		this.cardBrand = cardBrand;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getMonthExpiration() {
		return monthExpiration;
	}

	public void setMonthExpiration(Integer monthExpiration) {
		this.monthExpiration = monthExpiration;
	}

	public Integer getYearExpiration() {
		return yearExpiration;
	}

	public void setYearExpiration(Integer yearExpiration) {
		this.yearExpiration = yearExpiration;
	}

	public Integer getCVV() {
		return CVV;
	}

	public void setCVV(Integer cVV) {
		CVV = cVV;
	}

}
