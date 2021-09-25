/**
 * 
 */
package com.base.pages;

import com.base.pagefactory.MobileDriverPage;
import com.base.utils.ElementUtil;
import com.base.utils.WaitUtil;
import com.base.utils.WaitUtil.Locator;

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
	//@AndroidFindBy(id = "com.tajawal:id/tvItemText")
	private MobileElement departureDateInput;

	@AndroidFindBy(xpath = "//*[@text='Return date']")
	//@AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[@index='1']//*[@text='Return date']")
	private MobileElement returnDateInput;

	@AndroidFindBy(id = "com.tajawal:id/flightPaxView")
	private MobileElement addPassengersCard;
	
	@AndroidFindBy(id = "com.tajawal:id/btnFlightSearch")
	private MobileElement searchFlightButton;
	
	
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

	 
	public MobileElement getAddPassengersCard() {
		return addPassengersCard;
	}

	
	public MobileElement getSearchFlightButton() {
		return searchFlightButton;
	}

	public SearchOriginDestinationPage clickFrom(AppiumDriver<MobileElement> driver,  SearchFlightsPage searchFlightsPage) {
		ElementUtil.clickElement(driver, searchFlightsPage.getFromInput());
		return new SearchOriginDestinationPage(driver);
	}
	
	public SearchOriginDestinationPage clickTo(AppiumDriver<MobileElement> driver,  SearchFlightsPage searchFlightsPage) {
		ElementUtil.clickElement(driver, searchFlightsPage.getToInput());
		return new SearchOriginDestinationPage(driver);
	}
	
	public DatePickerPage clickDepartureDay(AppiumDriver<MobileElement> driver,  SearchFlightsPage searchFlightsPage) {
		ElementUtil.clickElement(driver, searchFlightsPage.getDepartureDateInput());
		return new DatePickerPage(driver);
	}
	
	public DatePickerPage clickReturnDay(AppiumDriver<MobileElement> driver,  SearchFlightsPage searchFlightsPage) {
		ElementUtil.clickElement(driver, searchFlightsPage.getReturnDateInput());
		return new DatePickerPage(driver);
	}
	
	public AddPassengerAndClassPage clickAddPassengers(AppiumDriver<MobileElement> driver,  SearchFlightsPage searchFlightsPage) {
		ElementUtil.clickElement(driver, searchFlightsPage.getAddPassengersCard());
		return new AddPassengerAndClassPage(driver);
	}
	public FlightResultPage clickSearchFlights(AppiumDriver<MobileElement> driver,  SearchFlightsPage searchFlightsPage) {
		ElementUtil.clickElement(driver, searchFlightsPage.getSearchFlightButton());
		WaitUtil.waitForElementToDisplay(driver, FlightResultPage.header, Locator.Id, 60L);
		WaitUtil.waitForElementToDisplay(driver, FlightResultPage.resultFilters, Locator.Id, 60L);
		return new FlightResultPage(driver);
	}
	
}
