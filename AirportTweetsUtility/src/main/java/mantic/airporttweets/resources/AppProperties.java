package mantic.airporttweets.resources;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class AppProperties {


	private static final Logger log = LoggerFactory.getLogger(AppProperties.class);
	
	/**
	 * Get the property file as per resource name
	 * 
	 * @param resourceName
	 * @return
	 * @throws Exception
	 */
	public static Properties getPropertyFile(String resourceName) throws Exception {
		Properties appProperties = new Properties();
		try {


				ClassLoader loader = Thread.currentThread().getContextClassLoader();           
				InputStream stream = loader.getResourceAsStream(resourceName);
				appProperties.load(stream);
		} catch (Exception e) {
	        log.error("Error while getPropertyFile()",e.getMessage());

		}
		return appProperties;
	}
	

}
