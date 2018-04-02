package mantic.airporttweets.dao;

import java.util.List;
import mantic.airporttweets.entity.Airport;
import mantic.airporttweets.entity.AirportTweet;

/** This class contains the methods communicating with DB
 * 
 * @author Inspiration
 *
 */
public interface AirportTweetAppServiceDao {
	
	public void insertTweetsInDB(List<AirportTweet> airportTweet) ;
	
	public List<Airport> getAirportsList();

	public long getMaxTweetID();

}
