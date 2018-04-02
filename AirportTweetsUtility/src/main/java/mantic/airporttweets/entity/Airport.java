package mantic.airporttweets.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** Airport Model/Entity Class
 * 
 * @author Inspiration
 *
 */
@Entity
@Table(name="airport")
public class Airport {
	
	@Id
	@Column(name="airport_id")
	private int airportId;
	
	@Column(name="airport_name")
	private String twitterAirportScreenName;
	
	@Column(name="airport_country")
	private String country;
	
	@Column(name="create_timestamp")
	private Timestamp createTimeStamp;
	
	//Getters and Setters

	public int getAirportId() {
		return airportId;
	}

	public void setAirportId(int airportId) {
		this.airportId = airportId;
	}

	public String getTwitterAirportScreenName() {
		return twitterAirportScreenName;
	}

	public void setTwitterAirportScreenName(String twitterAirportScreenName) {
		this.twitterAirportScreenName = twitterAirportScreenName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Timestamp getCreateTimeStamp() {
		return createTimeStamp;
	}

	public void setCreateTimeStamp(Timestamp createTimeStamp) {
		this.createTimeStamp = createTimeStamp;
	}
	
	
	

}
