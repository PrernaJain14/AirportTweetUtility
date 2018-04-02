package mantic.airporttweets.schedular;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import mantic.airporttweets.entity.AirportTweet;
import mantic.airporttweets.manager.AirportTweetsServiceManager;
import twitter4j.Status;


@Component
public class AirportTweetAppSchedular {
	
	private static final Logger log = LoggerFactory.getLogger(AirportTweetAppSchedular.class);
	
	
	@Autowired
	AirportTweetsServiceManager airportTweetServiceManager;

    @Scheduled(cron = "${airport.tweet.frequency.cron}")
    public void getAirportTweets() {
    	try {
    		List<Status> statusList = airportTweetServiceManager.getListOfTweetsFromTwitter();
    		List<AirportTweet> airportTweets=airportTweetServiceManager.getListOfAirportTweetObject(statusList);
    		if(airportTweets!=null) {
        		airportTweetServiceManager.insertTweetsInDB(airportTweets);
    		}
    		
			System.out.println("Service Executed at :" +new Timestamp(System.currentTimeMillis()));

		} catch (Exception e) {
	        log.error("Error while getAirportTweets()",e.getMessage());
		}
		
       
    }
   

   

}
