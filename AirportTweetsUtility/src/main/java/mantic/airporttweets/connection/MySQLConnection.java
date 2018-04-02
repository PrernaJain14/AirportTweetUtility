package mantic.airporttweets.connection;

import java.sql.Connection;

public class MySQLConnection {
	
	public static Connection connection = null;
	
	public static Connection getMySQLConnection() {
		
		return connection;
		
	}

}
