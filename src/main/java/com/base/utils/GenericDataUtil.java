/**
 * 
 */
package com.base.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

/**
 * @author Sindhu Koti
 * @apiNote Data fetcher methods from config and other test data for  test cases.
 *
 */
public class GenericDataUtil {

	private static final Logger logger = (Logger) LogManager.getLogger(GenericDataUtil.class.getName());
	
	public enum Utilkeys {START,END, MONTH} ;
	
	public static final String[] listOfOrigin = { "DXB", "AUH", "SHJ", "JED", "RUH" };

	public static final String[] listOfDestination = { "AMM", "CAI", "DEL", "KHI", "PAR" };
	
	public static final int plusMonths = 1;
	
	public static final Long explicitWaitTimeForElements = 40L ;
	
	public static final Long explicitWaitForAjaxElements = 10L ;
	
	public static final Long explicitWaitTimeForElementsForPageLoad = 60L ;
	
	public static enum CabinClass {
		ECONOMY("Economy"), PREMIUM_ECONOMY("Premium Economy"), BUSINESS("Business"), FIRST("First");
		private CabinClass(String cabin) {
			this.cabin = cabin;
		}
		private String cabin;
		// getter method
		public String getCabin() {
			return this.cabin;
		}
	};
	
	public static enum Country {
		BAHRAIN("Bahrain"), KSA("Kingdom of Saudi Arabia"), KUWAIT("Kuwait"), UAE("United Arab Emirates"), OTHER("Other");
		private Country(String country) {
			this.country = country;
		}
		private String country;
		// getter method
		public String getCountry() {
			return this.country;
		}
	};
	
	public static enum FlightResultSortBy {
		CHEAPEST("Lowest Price"), SHORTEST_DURATION("Shortest duration"), RECOMMENDED_FLIGHTS("Recommended flights");
		private FlightResultSortBy(String value) {
			this.sortBy = value;
		}
		private String sortBy;
		// getter method
		public String getSortBy() {
			return this.sortBy;
		}
	};
	
	public static String getConfigData(String parameter) throws IOException {
		try {
			File file = new File("./Config.properties");
			FileInputStream properties = new FileInputStream(file);
			Properties prop = new Properties();
			prop.load(properties);
			prop.getProperty(parameter);
			return prop.getProperty(parameter);

		} catch (IOException e) {
			return "";
		}

	}
	

	
}
