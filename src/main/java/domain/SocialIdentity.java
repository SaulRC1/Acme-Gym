
package domain;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Embeddable
public class SocialIdentity {

	private String	nick;
	private String	link;
	private String	socialMedia;


	public SocialIdentity(final String nick, final String link, final String socialMedia) {
		super();
		this.nick = nick;
		this.link = link;
		this.socialMedia = socialMedia;
	}

	public SocialIdentity() {

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

	public void setLink(final String link) {
		this.link = link;
	}

	public void setNick(final String nick) {
		this.nick = nick;
	}

	public void setSocialMedia(final String socialMedia) {
		this.socialMedia = socialMedia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.link == null) ? 0 : this.link.hashCode());
		result = prime * result + ((this.nick == null) ? 0 : this.nick.hashCode());
		result = prime * result + ((this.socialMedia == null) ? 0 : this.socialMedia.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		final SocialIdentity other = (SocialIdentity) obj;
		if (this.link == null) {
			if (other.link != null)
				return false;
		} else if (!this.link.equals(other.link))
			return false;
		if (this.nick == null) {
			if (other.nick != null)
				return false;
		} else if (!this.nick.equals(other.nick))
			return false;
		if (this.socialMedia == null) {
			if (other.socialMedia != null)
				return false;
		} else if (!this.socialMedia.equals(other.socialMedia))
			return false;
		return true;
	}

}
