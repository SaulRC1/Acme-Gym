package domain;

import java.sql.Date;

public class Annotation {
	private Date date;
	private String text;
	private Integer mark;

	public Annotation(Date date, String text, Integer mark) {
		super();
		this.date = date;
		this.text = text;
		this.mark = mark;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

}
