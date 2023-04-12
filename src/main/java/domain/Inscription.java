package domain;

import java.sql.Date;

public class Inscription {
	private Date singUpDate;
	private Date singOutDate;

	public Inscription(Date singUpDate, Date singOutDate) {
		super();
		this.singUpDate = singUpDate;
		this.singOutDate = singOutDate;
	}

	public Date getSingUpDate() {
		return singUpDate;
	}

	public void setSingUpDate(Date singUpDate) {
		this.singUpDate = singUpDate;
	}

	public Date getSingOutDate() {
		return singOutDate;
	}

	public void setSingOutDate(Date singOutDate) {
		this.singOutDate = singOutDate;
	}

}
