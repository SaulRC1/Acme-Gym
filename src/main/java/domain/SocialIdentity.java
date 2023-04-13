
package domain;

import javax.persistence.ManyToOne;

public class SocialIdentity {

	private String		nick;
	private String		link;
	private String		socialNetwork;

	//Relaciones
	private Curriculum	curriculum;


	@ManyToOne(optional = false)
	public Curriculum getCurriculum() {
		return this.curriculum;
	}

	public void setCurriculum(final Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	public SocialIdentity(final String nick, final String link, final String socialNetwork) {
		super();
		this.nick = nick;
		this.link = link;
		this.socialNetwork = socialNetwork;
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(final String nick) {
		this.nick = nick;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(final String link) {
		this.link = link;
	}

	public String getSocialNetwork() {
		return this.socialNetwork;
	}

	public void setSocialNetwork(final String socialNetwork) {
		this.socialNetwork = socialNetwork;
	}

}
