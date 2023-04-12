package domain;

public class Gym {

	private String logo;
	private String address;
	private String name;
	private Double fee;
	private boolean active;

	public Gym(String logo, String address, String name, Double fee, boolean active) {
		super();
		this.logo = logo;
		this.address = address;
		this.name = name;
		this.fee = fee;
		this.active = active;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
