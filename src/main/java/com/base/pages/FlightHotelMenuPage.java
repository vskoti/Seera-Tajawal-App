/**
 * 
 */
package com.base.pages;

import com.base.pagefactory.MobileDriverPage;
import com.base.utils.ElementUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * @author Sindhu Koti
 * @apiNote FlightHotelMenuPage page class for choosing the Flight booking or Hotel booking.
 *           mobile elements, getters and click methods which returns the respective pages. 
 */
public class FlightHotelMenuPage extends MobileDriverPage{
	
	public FlightHotelMenuPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
   
	public static final String flightsTabXpath = "//*[@text='Flights']";
	
	@AndroidFindBy(xpath = flightsTabXpath)
	private MobileElement flightsTab;

	public MobileElement getFlightsTab() {
		return flightsTab;
	}

	  public SearchFlightsPage clickFlightsTab(AppiumDriver<MobileElement> driver, FlightHotelMenuPage flightHotelMenuPage) {
	    	ElementUtil.clickElement(driver, flightHotelMenuPage.getFlightsTab());
	    	return new SearchFlightsPage(driver);
	    }
	
	
}
