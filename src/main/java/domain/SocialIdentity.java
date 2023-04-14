
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class SocialIdentity {

	private String nick;
	private String link;
	private String socialMedia;

	// Relationships
	private Curriculum curriculum;

	public SocialIdentity(final String nick, final String link, final String socialMedia, final Curriculum curriculum) {
		super();
		this.nick = nick;
		this.link = link;
		this.socialMedia = socialMedia;
		this.curriculum = curriculum;
	}

	@ManyToOne(optional = false)
	public Curriculum getCurriculum() {
		return this.curriculum;
	}

	@URL
	public String getLink() {
		return this.link;
	}

	@NotBlank
	public String getNick() {
		return this.nick;
	}

	@NotBlank
	public String getSocialMedia() {
		return this.socialMedia;
	}

	public void setCurriculum(final Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	public void setLink(final String link) {
		this.link = link;
	}

	public void setNick(final String nick) {
		this.nick = nick;
	}

	public void setSocialMedia(final String socialMedia) {
		this.socialMedia = socialMedia;
	}

}
