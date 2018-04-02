package mantic.airporttweets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling
public class AirportTweetsAppController  {

	public static void main(String[] args) {
		
		SpringApplication.run(AirportTweetsAppController.class, args);
	}
	
}
