package domain;

public class SocialIdentity {
	private String nick;
	private String link;
	private String socialNetwork;

	public SocialIdentity(String nick, String link, String socialNetwork) {
		super();
		this.nick = nick;
		this.link = link;
		this.socialNetwork = socialNetwork;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSocialNetwork() {
		return socialNetwork;
	}

	public void setSocialNetwork(String socialNetwork) {
		this.socialNetwork = socialNetwork;
	}

}
