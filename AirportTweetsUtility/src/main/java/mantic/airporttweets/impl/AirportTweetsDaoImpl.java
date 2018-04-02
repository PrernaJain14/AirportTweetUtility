package mantic.airporttweets.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mantic.airporttweets.constants.AirportTweetAppConstants;
import mantic.airporttweets.dao.AirportTweetAppServiceDao;
import mantic.airporttweets.entity.Airport;
import mantic.airporttweets.entity.AirportTweet;

/** This class contains teh implementation of all methods for Dao class and performs all DDL operations in DB
 * 
 * @author Inspiration
 *
 */
@Service
public class AirportTweetsDaoImpl implements AirportTweetAppServiceDao{
	
	private static final Logger log = LoggerFactory.getLogger(AirportTweetsDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	DBLogUtil dbLogUtil;
	
	/** Inserts the tweets in DB
	 * 
	 */
	//@Override
	public void insertTweetsInDB(List<AirportTweet> airportTweetListToInsert){
		Session session= null;
		int successCount=0;	
		int errorCount =airportTweetListToInsert.size();

		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			for(AirportTweet airportTweet:airportTweetListToInsert) {
				
				try {
					Integer id =(Integer) session.save(airportTweet);	
					successCount++;
				} catch (Exception e) {
					log.error("Error while airportTweetListToInsert(): For Airport: "+airportTweet.getUser_screen_name()+" with TweetId: "+airportTweet.getTweet_actual_id()+
							" and Tweet Desc: "+airportTweet.getTweetDescription()+"\nError Desc: ", e);
				}
				
			}
			session.getTransaction().commit();
			errorCount =airportTweetListToInsert.size()-successCount;
			dbLogUtil.insertTweetServiceStatus(AirportTweetAppConstants.SUCCESS_STATUS, successCount,errorCount);
		} catch (Exception e) {
			dbLogUtil.insertTweetServiceStatus(AirportTweetAppConstants.ERROR_STATUS,  successCount,errorCount,e.getMessage());
			log.error("Error while airportTweetListToInsert()", e);
		}finally {
			session.close();
		}
		
	}
	
	
	/** Returns the list of airports from table
	 * 
	 */
	//@Override
	public List<Airport> getAirportsList() {
		Session session= null;
		try {
			session = sessionFactory.openSession();
			 List<Airport> airportList = (List<Airport>) session.createQuery("from Airport").list(); 
			return airportList;
		} catch (Exception e) {
			log.error("Error while getAirportsList()", e);
		}finally {
			session.close();
		}
		return null;
	}


	/** Returns the maximum tweetId from teh table
	 * 
	 */
	@Override
	public long getMaxTweetID() {
		Session session= null;
		    try {
				session = sessionFactory.openSession();
		      String SQL_QUERY = "select max(tweet_actual_id)from AirportTweet airportTweet";
		        Query query = session.createQuery(SQL_QUERY);
		        List list = query.list();
		        if(list !=null && list.get(0) !=null) {
			        return (long) list.get(0);
		        }
		    }catch (Exception e) {
			log.error("Error while getMaxTweetID()", e);
		}finally {
			session.close();
		}
		return 0;
		
	}



}
