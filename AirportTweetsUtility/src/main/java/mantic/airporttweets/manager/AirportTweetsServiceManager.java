package mantic.airporttweets.manager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mantic.airporttweets.connection.TwitterAuthentication;
import mantic.airporttweets.constants.AirportTweetAppConstants;
import mantic.airporttweets.dao.AirportTweetAppServiceDao;
import mantic.airporttweets.entity.Airport;
import mantic.airporttweets.entity.AirportTweet;
import mantic.airporttweets.impl.DBLogUtil;
import mantic.airporttweets.resources.AppProperties;
import twitter4j.MediaEntity;
import twitter4j.MediaEntityJSONImpl;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;

/**This class contains the business logic involved with the services for getting tweets and inserting them in DB
 * 
 * @author Inspiration
 *
 */
@Component
public class AirportTweetsServiceManager {
	
	private static final Logger log = LoggerFactory.getLogger(AirportTweetsServiceManager.class);

	@Autowired
	AirportTweetAppServiceDao airportTweetsDao;

	@Autowired
	TwitterAuthentication twitterAuth;

	@Autowired
	DBLogUtil dbLogUtil;
	

	/**Returns the list of AirportTweet entity from the list of status object of twitter
	 * 
	 * @param statusFromTwtrList
	 * @return
	 */
	public List<AirportTweet> getListOfAirportTweetObject(List<Status> statusFromTwtrList) {
		List<AirportTweet> airportTweetList = new ArrayList<>();
		try {
			for (Status status : statusFromTwtrList) {
				AirportTweet airportTweet = new AirportTweet();
				airportTweet.setTweet_actual_id(status.getId());
				airportTweet.setUser_screen_name(status.getUser().getScreenName());
				MediaEntity[] media =status.getMediaEntities();
				if(media!=null && media.length>=1) {
					MediaEntityJSONImpl jsonImp =(MediaEntityJSONImpl) media[0];
					airportTweet.setTweetMediaType(true);
					airportTweet.setMediaType(jsonImp.getType());
					airportTweet.setMediaUrl(jsonImp.getMediaURL());
					airportTweet.setMediaHttpUrl(jsonImp.getMediaURLHttps());
					airportTweet.setMediaText(jsonImp.getText());
				}
				airportTweet.setSourceInfo(status.getSource());
				airportTweet.setTweetFlag(true);
				airportTweet.setTweetCreatedTimestamp(new Timestamp(status.getCreatedAt().getTime()));
				if(status.getText().length()>900)
					airportTweet.setTweetDescription(status.getText().substring(0, 900));
				else
					airportTweet.setTweetDescription(status.getText());
				airportTweetList.add(airportTweet);
			}
    		log.info("getListOfAirportTweetObject() executed successfully");
			return airportTweetList;
		} catch (Exception e) {
			log.error("Error while getListOfAirportTweetObject()", e.getMessage());
			dbLogUtil.insertTweetServiceStatus(AirportTweetAppConstants.ERROR_STATUS,  0,0,e.getMessage());

		}

		return null;

	}
	
	/** Returns the list of all airport tweets after the last they were added
	 * 
	 * @return
	 */
	public List<Status> getListOfTweetsFromTwitter(){
		List<Status> statusList = new ArrayList<Status>();
		try {
    		Twitter twitter = twitterAuth.getTwitterInstance();
    		
			List<Airport> airportList = airportTweetsDao.getAirportsList();
			long lastAccessId = airportTweetsDao.getMaxTweetID();
			String noOfTweets = AppProperties.getPropertyFile(AirportTweetAppConstants.APP_PROPERTIES_FILE).getProperty(AirportTweetAppConstants.NUMBER_OF_TWEETS_PER_ACCOUNT);
			// Provide the no of tweets for retrieval as per page no
			Paging paging = new Paging(1, Integer.parseInt(noOfTweets));
    		for(Airport airport:airportList) {
    			List<Status> curArptList = null;
    			if(lastAccessId!=0) {
    				/*
    				 * Gets the tweets after the lastAccessId tweet
    				 */
        			curArptList = twitter.getUserTimeline(airport.getTwitterAirportScreenName(),paging.sinceId(lastAccessId));

    			}else {
    				/*
    				 * Gets all the number of tweets mentioned in paging for the airport accountin first go
    				 */
        			curArptList = twitter.getUserTimeline(airport.getTwitterAirportScreenName());
    			}
    			statusList.addAll(curArptList);
    		}
    		log.info("getListOfTweetsFromTwitter() executed, Number of new tweets received:" +statusList.size());
		} catch (Exception e) {
			log.error("Error while getListOfTweetsFromTwitter()", e.getMessage());
			dbLogUtil.insertTweetServiceStatus(AirportTweetAppConstants.ERROR_STATUS,  0,0,e.getMessage());

		}
		return statusList;
	}

	/** Inserts the records in DB
	 * 
	 * @param airportTweetListToInsert
	 */
	public void insertTweetsInDB(List<AirportTweet> airportTweetListToInsert) {
		try {
			airportTweetsDao.insertTweetsInDB(airportTweetListToInsert);    
			log.info("insertTweetsInDB() executed, Number of tweets inserted:" +airportTweetListToInsert.size());

		} catch (Exception e) {
			log.error("Error while insertTweetsInDB()", e.getMessage());
		}
		
	}
	
	

}
