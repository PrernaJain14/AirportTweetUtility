package mantic.airporttweets.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mantic.airporttweets.entity.AirportTweet;
import mantic.airporttweets.entity.AirportTweetsActivityLog;
import mantic.airporttweets.schedular.AirportTweetAppSchedular;

@Component
public class DBLogUtil {
	
	private static final Logger log = LoggerFactory.getLogger(DBLogUtil.class);


	@Autowired
	private SessionFactory sessionFactory;
	
	public void insertTweetServiceStatus(String status,int successCount,int errorCount,String desc) {
			Session session= null;
			try {
				session = sessionFactory.openSession();
				session.beginTransaction();
				AirportTweetsActivityLog airportTweetlog = new AirportTweetsActivityLog();	
				airportTweetlog.setLog_status(status);
				airportTweetlog.setSuccessCount(successCount);
				airportTweetlog.setErrorCount(errorCount);
				if(desc.length()>900) {
					airportTweetlog.setLog_desc(desc.substring(0,900));
				}else
				airportTweetlog.setLog_desc(desc);
				Integer id =(Integer) session.save(airportTweetlog);
				session.getTransaction().commit();
				log.info("Log created with id: "+id);
			} catch (Exception e) {
				log.error("Error while insertTweetServiceStatus()", e);
			}finally {
				session.close();
			}
			
		
		
	}
	
	public void insertTweetServiceStatus(String status,int successCount,int errorCount) {
		Session session= null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			AirportTweetsActivityLog airportTweetlog = new AirportTweetsActivityLog();	
			airportTweetlog.setLog_status(status);
			airportTweetlog.setSuccessCount(successCount);
			airportTweetlog.setErrorCount(errorCount);
			Integer id =(Integer) session.save(airportTweetlog);
			session.getTransaction().commit();
			log.info("Log created with id: "+id);
		} catch (Exception e) {
			log.error("Error while insertTweetServiceStatus()", e);
		}finally {
			session.close();
		}
		
	
	
}

}
