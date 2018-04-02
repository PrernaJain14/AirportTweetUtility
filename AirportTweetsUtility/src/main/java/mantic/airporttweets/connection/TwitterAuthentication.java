package mantic.airporttweets.connection;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import mantic.airporttweets.constants.AirportTweetAppConstants;
import mantic.airporttweets.resources.AppProperties;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Component
public class TwitterAuthentication {

	private static final Logger log = LoggerFactory.getLogger(TwitterAuthentication.class);

	/**gets the configuration builder for the valid twitter user
	 * 
	 * @return
	 */
	public static ConfigurationBuilder getConfigurationBuilder() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		try {
			Properties twitterCredentialProp = AppProperties.getPropertyFile(AirportTweetAppConstants.TWITTER_PROPERTIES_FILE);
			cb.setDebugEnabled(true).setOAuthConsumerKey(twitterCredentialProp.getProperty(AirportTweetAppConstants.TWITTER_OAUTH_CONSUMER_KEY)).
			setOAuthConsumerSecret(twitterCredentialProp.getProperty(AirportTweetAppConstants.TWITTER_OAUTH_CONSUMER_SECRET))
			.setOAuthAccessToken(twitterCredentialProp.getProperty(AirportTweetAppConstants.TWITTER_OAUTH_ACCESS_TOKEN)).
			setOAuthAccessTokenSecret(twitterCredentialProp.getProperty(AirportTweetAppConstants.TWITTER_OAUTH_ACCESS_TOKEN_SECRET));
			return cb;
		} catch (Exception e) {
	        log.error("Error while getConfigurationBuilder()",e.getMessage());
		}
		return null;
	}
	

	/**Returns the instance of twitter
	 * 
	 * @return
	 */
	public Twitter getTwitterInstance() {
		try {

			ConfigurationBuilder cb = getConfigurationBuilder();
			TwitterFactory twitterFactory = new TwitterFactory(cb.build());
			Twitter twitter = twitterFactory.getInstance();
			return twitter;
		} catch (Exception e) {
	        log.error("Error while getTwitterInstance()",e.getMessage());
		}
		return null;
	}
	
}
