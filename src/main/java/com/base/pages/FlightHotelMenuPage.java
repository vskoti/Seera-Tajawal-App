/**
 * 
 */
package com.base.pages;

import com.base.pagefactory.MobileDriverPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * @author sindhu.koti
 *
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

	
	
}
