package domain.enums;

/**
 *
 * This enum is used to have common names for different social networks.
 *
 */
public enum SocialMedia {

    TWITTER("Twitter"), FACEBOOK("Facebook"), INSTAGRAM("Instagram"), LINKEDIN("LinkedIn");

    private final String socialMediaName;

    private SocialMedia(String socialMediaName) {
	this.socialMediaName = socialMediaName;
    }

    public String getSocialMediaName() {
	return this.socialMediaName;
    }

}
