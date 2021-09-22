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
 * @author sindhu.koti
 *
 */
public class SearchFlightsPage extends MobileDriverPage {

	public SearchFlightsPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	public static final String searchFlightsTextXpath = "//*[@text='Search flights']";

	public static final String roundTripTabTextXpath = "//*[@text='ROUND-TRIP']";

	@AndroidFindBy(xpath = searchFlightsTextXpath)
	private MobileElement serchFlightsText;

	@AndroidFindBy(xpath = roundTripTabTextXpath)
	private MobileElement roundTripTabText;

	@AndroidFindBy(xpath = "//*[@text='From']")
	private MobileElement fromInput;

	@AndroidFindBy(xpath = "//*[@text='To']")
	private MobileElement toInput;

	@AndroidFindBy(xpath = "//*[@text='Departure date']")
	private MobileElement departureDateInput;

	@AndroidFindBy(xpath = "//*[@text='Return date']")
	private MobileElement returnDateInput;

	public MobileElement getSerchFlightsText() {
		return serchFlightsText;
	}

	public MobileElement getRoundTripTabText() {
		return roundTripTabText;
	}

	public MobileElement getFromInput() {
		return fromInput;
	}

	public MobileElement getToInput() {
		return toInput;
	}

	public MobileElement getDepartureDateInput() {
		return departureDateInput;
	}

	public MobileElement getReturnDateInput() {
		return returnDateInput;
	}

	public SearchOriginDestinationPage clickFrom(AppiumDriver<MobileElement> driver,  SearchFlightsPage searchFlightsPage) {
		ElementUtil.clickElement(driver, searchFlightsPage.getFromInput());
		return new SearchOriginDestinationPage(driver);
	}
	
	public SearchOriginDestinationPage clickTo(AppiumDriver<MobileElement> driver,  SearchFlightsPage searchFlightsPage) {
		ElementUtil.clickElement(driver, searchFlightsPage.getToInput());
		return new SearchOriginDestinationPage(driver);
	}
}
