package mantic.airporttweets.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** Airport Tweet Model/Entity class
 * 
 * @author Inspiration
 *
 */
@Entity
@Table(name="airport_tweet")
public class AirportTweet {
	
	@Id
	@Column(name="tweet_db_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="tweet_actual_id")
	private long tweet_actual_id;
	
	@Column(name="tweet_airport_name")
	private String user_screen_name;
	
	@Column(name="tweet_db_timestamp")
	private Timestamp createTimestamp;
	
	@Column(name="tweet_created_timestamp")
	private Timestamp tweetCreatedTimestamp;

	@Column(name="tweet_new")
	private boolean tweetFlag;

	@Column(name="tweet_desc")
	private String tweetDescription;
	
	@Column(name="tweet_source_info")
	private String sourceInfo;
	
	@Column(name="is_tweet_media")
	private boolean isTweetMediaType;
	
	@Column(name="tweet_media_url")
	private String mediaUrl;
	
	@Column(name="tweet_media_http_url")
	private String mediaHttpUrl;
	
	@Column(name="tweet_media_type")
	private String mediaType;
	
	@Column(name="tweet_media_text")
	private String mediaText;
	
	//Getters And Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTweet_actual_id() {
		return tweet_actual_id;
	}

	public void setTweet_actual_id(long tweet_actual_id) {
		this.tweet_actual_id = tweet_actual_id;
	}

	public String getUser_screen_name() {
		return user_screen_name;
	}

	public void setUser_screen_name(String user_screen_name) {
		this.user_screen_name = user_screen_name;
	}

	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Timestamp getTweetCreatedTimestamp() {
		return tweetCreatedTimestamp;
	}

	public void setTweetCreatedTimestamp(Timestamp tweetCreatedTimestamp) {
		this.tweetCreatedTimestamp = tweetCreatedTimestamp;
	}

	public boolean isTweetFlag() {
		return tweetFlag;
	}

	public void setTweetFlag(boolean tweetFlag) {
		this.tweetFlag = tweetFlag;
	}

	public String getTweetDescription() {
		return tweetDescription;
	}

	public void setTweetDescription(String tweetDescription) {
		this.tweetDescription = tweetDescription;
	}

	public String getSourceInfo() {
		return sourceInfo;
	}

	public void setSourceInfo(String sourceInfo) {
		this.sourceInfo = sourceInfo;
	}

	public boolean isTweetMediaType() {
		return isTweetMediaType;
	}

	public void setTweetMediaType(boolean isTweetMediaType) {
		this.isTweetMediaType = isTweetMediaType;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public String getMediaHttpUrl() {
		return mediaHttpUrl;
	}

	public void setMediaHttpUrl(String mediaHttpUrl) {
		this.mediaHttpUrl = mediaHttpUrl;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaText() {
		return mediaText;
	}

	public void setMediaText(String mediaText) {
		this.mediaText = mediaText;
	}

	
}
