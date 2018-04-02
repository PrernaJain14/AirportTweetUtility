package mantic.airporttweets.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="airport_tweet_activity_log")
public class AirportTweetsActivityLog {
	
	@Id
	@Column(name="log_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int logid;
	
	@Column(name="log_status")
	private String log_status;
	
	@Column(name="log_desc")
	private String log_desc;
	
	@Column(name="success_tweet_count")
	private int successCount;
	
	@Column(name="error_tweet_count")
	private int errorCount;
	
	@Column(name="create_timestamp")
	private Timestamp createTimestamp;

	//Getters and Setters
	
	public int getLogid() {
		return logid;
	}

	public void setLogid(int logid) {
		this.logid = logid;
	}

	public String getLog_status() {
		return log_status;
	}

	public void setLog_status(String log_status) {
		this.log_status = log_status;
	}

	public String getLog_desc() {
		return log_desc;
	}

	public void setLog_desc(String log_desc) {
		this.log_desc = log_desc;
	}

	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	
	
	
}
