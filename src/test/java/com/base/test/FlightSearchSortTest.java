/**
 * 
 */
package com.base.test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.base.drivers.factory.DriverFactory;
import com.base.listeners.ExtentListenerAdapter;
import com.base.pages.AddPassengerAndClassPage;
import com.base.pages.DatePickerPage;
import com.base.pages.FlightHotelMenuPage;
import com.base.pages.FlightResultPage;
import com.base.pages.SearchFlightsPage;
import com.base.pages.SearchOriginDestinationPage;
import com.base.pages.TajawalBasePage;
import com.base.utils.CustomLogger;
import com.base.utils.ElementUtil;
import com.base.utils.GenericDataUtil;
import com.base.utils.GenericDataUtil.CabinClass;
import com.base.utils.GenericDataUtil.Country;
import com.base.utils.GenericDataUtil.FlightResultSortBy;
import com.base.utils.GenericDataUtil.Utilkeys;
import com.base.utils.ScreenUtil;
import com.base.utils.WaitUtil;
import com.base.utils.WaitUtil.Locator;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/**
 * @author Sindhu Koti
 *
 */

@Listeners(ExtentListenerAdapter.class)
public class FlightSearchSortTest {

	static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(FlightSearchSortTest.class.getName());

	/**
	 * @author sindhu.koti
	 * @apiNote Main Test Scenario for Tajawal App. 
	 * 
	 * 
	 */
	@Test(groups = { "smoke"})
	public void testFlightSearchSort() {
		AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver();

		try {
			// 1- Open Tajawal or Almosafer Native Mobile App
			TajawalBasePage tajawalBasePage = new TajawalBasePage(appiumDriver);
			boolean isTajawalWelcomeTitleDisplayed = tajawalBasePage.getWelcomeTitle().isDisplayed();

			// Welcome Page with settings display check
			if (isTajawalWelcomeTitleDisplayed) {
				CustomLogger.logResultData("Passed", "Landing page welcome title :", " Displayed", 1);
			} else {
				CustomLogger.logResultData("Failed", "Landing page welcome title :", " Not Displayed", 1);
				Assert.fail("Landing page welcome title : Not Displayed");
			}

			// 2- Check for current set language. If language is already set to english then
			// proceed with next steps. If not, then first change language to english and
			// then proceed.
			boolean isEnglishSelectedByDefault = tajawalBasePage.getWelcomeEnglishButton().isSelected();
			if (!isEnglishSelectedByDefault) {
				ElementUtil.clickElement(appiumDriver, tajawalBasePage.getWelcomeEnglishButton());
				CustomLogger.logResultData("Passed", "English language :",
						"not set by default, explicitly setting to English!", 1);
			} else {
				CustomLogger.logResultData("Passed", "English language :", "set by default", 1);
				
			}

			// Check if UAE selected by default and if not selecting country as UAE
			// Utility method tajawalBasePage.selectCountryElement selects country based on the enum value passed.
			boolean isUAESelected = Boolean.parseBoolean(tajawalBasePage
					.selectCountryElement(appiumDriver, Country.UAE.getCountry()).getAttribute("checked"));
			if (!isUAESelected) {
				ElementUtil.clickElement(appiumDriver,
						tajawalBasePage.selectCountryElement(appiumDriver, Country.UAE.getCountry()));
			}

			// Main Test Scenario 3
			// Navigate to flights-home page, and enter below criteria in flights search
			// form to make flight search:
			// From Flight and Hotel Menu page, click on Flight page and return
			// SearchFlights page object.
			FlightHotelMenuPage flightHotelMenuPage = tajawalBasePage.clickContinueButton(appiumDriver,
					tajawalBasePage);
			WaitUtil.waitForElementToBeClickable(appiumDriver, FlightHotelMenuPage.flightsTabXpath, Locator.Xpath,
					GenericDataUtil.explicitWaitTimeForElements);

			SearchFlightsPage searchFlightsPage = flightHotelMenuPage.clickFlightsTab(appiumDriver,
					flightHotelMenuPage);
			WaitUtil.waitForElementToDisplay(appiumDriver, SearchFlightsPage.searchFlightsTextXpath, Locator.Xpath,
					GenericDataUtil.explicitWaitForAjaxElements);
			boolean isSearchFlightDislayed = searchFlightsPage.getSerchFlightsText().isDisplayed();
			if (isSearchFlightDislayed) {
				CustomLogger.logResultData("Passed", "Search Flight page :", " Displayed", 1);
			} else {
				CustomLogger.logResultData("Failed", "Search Flight page :", " Not Displayed", 1);
				Assert.fail("Search Flight page : Not Displayed");
			}

			// Main Test Scenario 3.a
			// Origin - (from random array of origins - length 5) (Example: DXB, AUH, SHJ,
			// JED, RUH)

			SearchOriginDestinationPage searchOriginDestinationPage = searchFlightsPage.clickFrom(appiumDriver,
					searchFlightsPage);
			ElementUtil.setInput(appiumDriver, searchOriginDestinationPage.getOriginDestinationInputSearch(),
					getRandom(GenericDataUtil.listOfOrigin));

			WaitUtil.waitForElementToDisplay(appiumDriver, SearchOriginDestinationPage.firstSuggestionXpath,
					Locator.Xpath, 10L);
			searchOriginDestinationPage = searchOriginDestinationPage.clickFirstSuggestion(appiumDriver,
					searchOriginDestinationPage);
			WaitUtil.waitForElementToDisplay(appiumDriver, SearchOriginDestinationPage.searchDestinationXpath,
					Locator.Xpath, 10L);

			// Main Test Scenario 3.b
			// Destination - (from random array of destinations - length 5) (Example: AMM,
			// CAI, DEL, KHI, PAR)
			ElementUtil.setInput(appiumDriver, searchOriginDestinationPage.getOriginDestinationInputSearch(),
					getRandom(GenericDataUtil.listOfDestination));
			WaitUtil.waitForElementToDisplay(appiumDriver, SearchOriginDestinationPage.firstSuggestionXpath,
					Locator.Xpath, 10L);
			searchOriginDestinationPage.clickFirstSuggestion(appiumDriver, searchOriginDestinationPage);
			searchFlightsPage = new SearchFlightsPage(appiumDriver);

			// Click on the date picker and validate
			// if current month view is displayed by default
			// if the next day is selected by default as departure date
			// Utility method isNextDayMonthDateCheckedByDefault is written and works for
			// any date.
			DatePickerPage datePickerPage = searchFlightsPage.clickDepartureDay(appiumDriver, searchFlightsPage);
			WaitUtil.waitForElementToDisplay(appiumDriver, DatePickerPage.departureDateCardViewId, Locator.Id, 5L);
			ElementUtil.clickElement(appiumDriver, datePickerPage.getDepartureDateCardView());
			String monthViewText = datePickerPage.getCurrentMonthViewText().getText();
			boolean isNextDayMonthDateCheckedByDefault = isNextDayMonthDateCheckedByDefault(appiumDriver,
					monthViewText);

			if (isNextDayMonthDateCheckedByDefault) {
				CustomLogger.logResultData("Passed", "Default selection of Current month and next day :", " Selected",
						1);
			} else {
				CustomLogger.logResultData("Failed", "Default selection of Current month and next day :",
						" Not Selected", 1);
				Assert.fail("Default selection of Current month and next day : Not Selected");
			}

			// Utility method scrollToAMonthAfterCurrent() is written to select any future
			// date from any month.
			// currently configured as +1 month, (GenericDataUtil.plusMonths)hence, random
			// date from next month is selected.
			scrollToAMonthAfterCurrent(appiumDriver);

			// Utility method to scroll down using touch actions. Current pointoption
			// coordinates are 550, 600
			ScreenUtil.scrollDown();

			// Main Test Scenario 3.c
			// Depart and Return Dates (randomly generated dates in future. Do not select
			// date in
			// current month. Scroll down in calendar view and select future dates)
			// Utility method getRandom is written to get random array index for origin and
			// destination
			// which returns a map used further for validations across all test steps.
			Map<Utilkeys, String> travelDates = selectDepartureReturnDate(appiumDriver, datePickerPage);

			// Validate if the selected trip type and date is as per the selected itinerary
			// in SearchFlight page
			// and confirm.
			// verifyTravelDates method is used at two places and hence the boolean argument
			// to conditionalise
			String datesSelected = datePickerPage.getDatesSelected().getText();
			String tripType = datePickerPage.getExtraSubtitleText().getText();
			boolean isTripTypeRound = tripType.equalsIgnoreCase("round-trip");
			boolean isTravelDateSameAsPerChosen = verifyTravelDates(datesSelected, travelDates, false);
			if (isTripTypeRound) {
				CustomLogger.logResultData("Passed",
						"Validate if the dispalyed trip type in DatePicker page, is as per the selected itinerary in previous SearchFlight page",
						" Same!", 1);
			} else {
				CustomLogger.logResultData("Failed",
						"Validate if the  dispalyed trip type in DatePicker page,is as per the selected itinerary in previous SearchFlight page",
						"NOT Same!", 1);
				Assert.fail("Validate if the  dispalyed trip type in DatePicker page,is as per the selected itinerary in previous SearchFlight page NOT Same!");
			}
			if (isTravelDateSameAsPerChosen) {
				CustomLogger.logResultData("Passed",
						"Validate if the displayed date in DatePicker page, is per the selected detail in previous SearchFlight page",
						" Same!", 1);
			} else {
				CustomLogger.logResultData("Failed",
						"Validate if the displayed date in DatePicker page, is as per the selected detail in previous SearchFlight page",
						"NOT Same!", 1);
				Assert.fail("Validate if the displayed date in DatePicker page, is as per the selected detail in previous SearchFlight page NOT SAME.");
			}
			searchFlightsPage = datePickerPage.clickDateSelectConfirmation(appiumDriver, datePickerPage);

			// Main Test Scenario 3.d
			// Passengers (1Adult,2Children,1Infant)
			// Utility method addPassengers() is written to handle addition of any number of
			// passengers
			// and validate the count displayed
			AddPassengerAndClassPage addPassengerAndClassPage = searchFlightsPage.clickAddPassengers(appiumDriver,
					searchFlightsPage);
			addPassengers(appiumDriver, addPassengerAndClassPage, 2, 2, 1);

			// Main Test Scenario 3.e
			// Cabin Class (Economy)
			// Utility Method clickCabinClass in the page object can be used to select any
			// class using enum cabin class.
			addPassengerAndClassPage.clickCabinClass(appiumDriver, addPassengerAndClassPage, CabinClass.ECONOMY);
			searchFlightsPage = addPassengerAndClassPage.clickApplyPassengerMenu(appiumDriver,
					addPassengerAndClassPage);

			// Main Test Scenario 4 (3 in Test scenario still,missing count!)
			// 4 - Wait for loading to be completed on flight listing page.
			// clickSearchFlights method in the page class is internally handling the loads.
			FlightResultPage flightResultPage = searchFlightsPage.clickSearchFlights(appiumDriver, searchFlightsPage);

			// Main Test Scenario 5 (4 in Test scenario still,missing count!)
			// 5- Assert the Dates in the flight listing page on the header
			// verifyTravelDates method is used at two places and hence the boolean argument
			// to conditionalise
			String flightResultHeaderSubtitleDateText = flightResultPage.getFlightResultPageHeaderSubtitle().getText();
			isTravelDateSameAsPerChosen = verifyTravelDates(flightResultHeaderSubtitleDateText, travelDates, true);
			if (isTravelDateSameAsPerChosen) {
				CustomLogger.logResultData("Passed",
						"Validate if the displayed date in Flightresult page, is as per the selected detail in previous SearchFlight page",
						" Same!", 1);
			} else {
				CustomLogger.logResultData("Failed",
						"Validate if the displayed date in lightresult page, is as per the selected detail in previous SearchFlight page",
						"NOT Same!", 1);
				Assert.fail("Validate if the displayed date in lightresult page, is as per the selected detail in previous SearchFlight page : Not same");
			}

			// Main Test Scenario 6 (5 in Test scenario still,missing count!)
			// Use sort feature to ensure that flights are sorted by 'cheapest'.

			flightResultPage.clickSortBy(appiumDriver, flightResultPage, FlightResultSortBy.CHEAPEST);
			WaitUtil.waitForElementToDisplay(appiumDriver, FlightResultPage.header, Locator.Id, 60L);
			boolean isCheapestTagDisplayed = flightResultPage.getCheapestText().isDisplayed();
			if (isCheapestTagDisplayed) {
				CustomLogger.logResultData("Passed", "Use sort feature to ensure that flights are sorted by 'cheapest'",
						" Dsiplayed.", 1);
			} else {
				CustomLogger.logResultData("Failed", "Use sort feature to ensure that flights are sorted by 'cheapest'",
						"NOT Dsiplayed!", 1);
				Assert.fail("Use sort feature to ensure that flights are sorted by 'cheapest' :not displayed.");
			}
              
			// Main Test Scenario 7 (6 in Test scenario still,missing count!)
			//6- After loading is completed, fetch and save price of first flight
			Long firstFlightPriceAfterSort = null ;
			try {
				List<MobileElement> priceElements = flightResultPage.getFlightPriceList();
				firstFlightPriceAfterSort = Long.parseLong(priceElements.stream().map(p -> p.getText()).collect(Collectors.toList())
						.get(0).split(" ")[1].replace(",", ""));
			} catch (Exception e) {
				CustomLogger.logResultData("Failed", "Failed during flights price fetch : ", e.getMessage(), 1);
				Assert.fail("Failed during flights price fetch : "+ e.getMessage());
			}
			
			// Main Test Scenario 6 (5 in Test scenario still,missing count!)
			// (Scroll down in the flights results view and do this check for multiple
			// flights)
			List<Long> actualPriceList = scrollDownAndCaptureFlightPrice(appiumDriver, flightResultPage);
			List<Long> expectedSortedPriceList =  actualPriceList;
			Collections.sort(expectedSortedPriceList);
			boolean isPriceSortedAsCheapest = actualPriceList.equals(expectedSortedPriceList);
			
			if (isPriceSortedAsCheapest) {
				CustomLogger.logResultData("Passed",
						"Scroll down in the flights results view and do this check for multiple flights", " Dsiplayed.",
						1);
			} else {
				CustomLogger.logResultData("Failed",
						"Scroll down in the flights results view and do this check for multiple flights",
						"NOT Dsiplayed!", 1);
				Assert.fail("Scroll down in the flights results view and do this check for multiple flights : Not dispalyed");
			}
			
			// Main Test Scenario 8 (7 in Test scenario still,missing count!)
			// Assert the minimum-price displayed for price-range-filter equals to price of first-flight in list
			Long priceRangeMinimum = expectedSortedPriceList.get(0);
			boolean isMinimumPriceRangeSameAsFlightFlightPrice = priceRangeMinimum.equals(firstFlightPriceAfterSort) ;
			if(isMinimumPriceRangeSameAsFlightFlightPrice) {
				CustomLogger.logResultData("Passed",
						"Assert the minimum-price displayed for price-range-filter equals to price of first-flight in list :", " Same.",
						1);
			}else {
				CustomLogger.logResultData("Failed",
						"Assert the minimum-price displayed for price-range-filter equals to price of first-flight in list :",
						"NOT Same!", 1);
				Assert.fail("Assert the minimum-price displayed for price-range-filter equals to price of first-flight in list : Not same");
			}
			

		} catch (Exception e) {
			try {
				CustomLogger.logResultData("Failed", "Failed during execution: ", e.getMessage(), 1);
				e.printStackTrace();
				logger.debug(e.getMessage());
				Assert.fail(e.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}

	}

	/**
	 * @apiNote Gets the web elements texts and verifies the dates as per the map values in parameter.
	 *          used across pages to verify if the selected date and detail are consistent across app.
	 * @param datesSelected
	 * @param travelDates
	 * @param isFlightResultPage
	 * @return
	 */
	private boolean verifyTravelDates(String datesSelected, Map<Utilkeys, String> travelDates,
			boolean isFlightResultPage) {
		boolean isConfirmationDateAsPerSelectedDate = false;
		try {
			int index = 0;
			if (isFlightResultPage) {
				index = 1;
			}
			String[] dates = datesSelected.split("-");
			isConfirmationDateAsPerSelectedDate = (dates[index].contains(travelDates.get(Utilkeys.START))
					&& dates[index].toLowerCase().contains(travelDates.get(Utilkeys.MONTH)))
					&& (dates[index + 1].contains(travelDates.get(Utilkeys.END))
							&& dates[index + 1].toLowerCase().contains(travelDates.get(Utilkeys.MONTH)));
		} catch (Exception e) {
			try {
				CustomLogger.logResultData("Failed", "Failed during verifyTravelDates : ", e.getMessage(), 1);
				logger.debug(e.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		return isConfirmationDateAsPerSelectedDate;
	}

	/**
	 * @apiNote gets the random index within the size of the array passed. 
	 * @param array
	 * @return value present in the random index generated. 
	 */
	public static String getRandom(String[] array) {
		int randonIndex = new Random().nextInt(array.length);
		return array[randonIndex];
	}

	/**
	 * @apiNote verifies if the day next is selected by default.
	 * @param driver
	 * @param actualDisplayedMonth
	 * @return boolean isNextDayMonthDateCheckedByDefault
	 */
	public boolean isNextDayMonthDateCheckedByDefault(AppiumDriver<MobileElement> driver, String actualDisplayedMonth) {
		boolean isNextDayMonthDateCheckedByDefault = false;
		Integer nextDayDate = LocalDate.now().plusDays(1).getDayOfMonth();
		String selectedMonth = LocalDate.now().plusDays(1).getMonth().name();
		Integer selectedYear = LocalDate.now().plusDays(1).getYear();
		if (actualDisplayedMonth.toUpperCase().contains(selectedMonth.toUpperCase())
				&& actualDisplayedMonth.contains(selectedYear.toString())) {
			isNextDayMonthDateCheckedByDefault = true;
			String highlightedDate = DatePickerPage.checkedDateText.replaceFirst("xxx",
					String.valueOf(nextDayDate - 1));
			boolean isChecked = driver.findElement(By.xpath(highlightedDate)).isEnabled();
			isNextDayMonthDateCheckedByDefault = isNextDayMonthDateCheckedByDefault && isChecked;
		}
		return isNextDayMonthDateCheckedByDefault;
	}

	/**
	 * @apiNote Scrolls down to the next month to be in month view for dates selection.
	 * @param driver
	 */
	public void scrollToAMonthAfterCurrent(AppiumDriver<MobileElement> driver) {
		String aMonthAfterNext = LocalDate.now().getMonth().plus(GenericDataUtil.plusMonths).name().toString();
		String aMonthAfterNextYear = String.valueOf(LocalDate.now().plusMonths(GenericDataUtil.plusMonths).getYear());
		String textToScroll = aMonthAfterNext.concat(" ").concat(aMonthAfterNextYear);
		ScreenUtil.scrollToText(driver, textToScroll);
	}

	/**
	 * @apiNote Generates random to and from date in (current month + GenericDataUtil.plusmonth) month. 
	 * @param driver
	 * @param datePickerPage
	 * @return Map<Utilkeys, String> with selected departure, return date and month for further validation. 
	 */
	private Map<Utilkeys, String> selectDepartureReturnDate(AppiumDriver<MobileElement> driver,
			DatePickerPage datePickerPage) {
		Map<Utilkeys, String> travelDates = new HashMap<Utilkeys, String>();
		int lengthOfMonth = LocalDate.now().plusMonths(GenericDataUtil.plusMonths).lengthOfMonth();
		Random r = new Random();
		int start = 1;
		int mid = 15;
		int high = lengthOfMonth;
		int departureDate = r.nextInt(mid - start) + start;
		int returnDate = r.nextInt(high - mid) + mid;
		travelDates.put(Utilkeys.START, String.valueOf(departureDate));
		travelDates.put(Utilkeys.END, String.valueOf(returnDate));
		travelDates.put(Utilkeys.MONTH,
				LocalDate.now().plusMonths(GenericDataUtil.plusMonths).getMonth().name().substring(0, 3).toLowerCase());
		String dateFieldXpath = DatePickerPage.dateField.replace("xxx", String.valueOf(departureDate - 1));
		ElementUtil.clickElement(driver, driver.findElement(By.xpath(dateFieldXpath)));
		// necessitated to use sleep here as on click element function is getting fired at the backed leading to ui delay though element is clickable
		WaitUtil.waitHard(6000);
		dateFieldXpath = DatePickerPage.dateField.replace("xxx", String.valueOf(returnDate - 1));
		WaitUtil.waitForElementToBeClickable(driver, dateFieldXpath, Locator.Xpath, 5L);
		ElementUtil.clickElement(driver, driver.findElement(By.xpath(dateFieldXpath)));
		return travelDates;
	}

	/**
	 * @apiNote Adds the number of passengers in adult, child and infant as per the parameters received but a max of 2 infants.
	 *          limiting web driver scope concept is used here to fetch web elements for performance 
	 * @param driver
	 * @param addPassengerAndClassPage
	 * @param numberOfAdults
	 * @param numberOfChildren
	 * @param numberOfInfants
	 */
	private void addPassengers(AppiumDriver<MobileElement> driver, AddPassengerAndClassPage addPassengerAndClassPage,
			int numberOfAdults, int numberOfChildren, int numberOfInfants) {
		if (numberOfAdults != 0 && numberOfAdults >= 1) {
			int currentAdultCount = Integer.parseInt(addPassengerAndClassPage.getPaxAdult()
					.findElement(By.id(AddPassengerAndClassPage.paxCount)).getText());
			if (currentAdultCount < numberOfAdults) {
				for (int i = currentAdultCount; i < numberOfAdults; i++) {
					addPassengerAndClassPage.getPaxAdult().findElement(By.id(AddPassengerAndClassPage.addPax)).click();
				}
			}
			int currentChildCount = Integer.parseInt(addPassengerAndClassPage.getPaxChild()
					.findElement(By.id(AddPassengerAndClassPage.paxCount)).getText());
			if (currentChildCount < numberOfChildren) {
				for (int i = currentChildCount; i < numberOfChildren; i++) {
					addPassengerAndClassPage.getPaxChild().findElement(By.id(AddPassengerAndClassPage.addPax)).click();
				}
			}
			if (numberOfInfants > 2) {
				numberOfInfants = 2;
			}
			int currentInfantCount = Integer.parseInt(addPassengerAndClassPage.getPaxInfant()
					.findElement(By.id(AddPassengerAndClassPage.paxCount)).getText());
			if (currentInfantCount < numberOfInfants) {
				for (int i = currentChildCount; i < numberOfInfants; i++) {
					addPassengerAndClassPage.getPaxInfant().findElement(By.id(AddPassengerAndClassPage.addPax)).click();
				}
			}
			
		}
	}

	/**
	 * @apiNote scrolls down  4 times to fetch the flight prices, split the prices to take only the numeric values and saves in a list.
	 * @param driver
	 * @param flightResultPage
	 * @return List<Long> of flight prices
	 */
	private List<Long> scrollDownAndCaptureFlightPrice(AppiumDriver<MobileElement> driver,
			FlightResultPage flightResultPage) {
		List<Long> capturedPriceList = new LinkedList<Long>();
		List<MobileElement> elements = flightResultPage.getFlightPriceList();
		List<String> listStrings = elements.stream().map(p -> p.getText()).collect(Collectors.toList());
		for (int i = 0; i < 3; i++) {
			ScreenUtil.scrollDown();
			List<MobileElement> temp = driver.findElements(By.id(FlightResultPage.priceListId));
			listStrings.addAll(temp.stream().map(p -> p.getText()).collect(Collectors.toList()));
		}
		capturedPriceList = listStrings.stream().map(p -> Long.parseLong(p.split(" ")[1].replace(",", "")))
				.collect(Collectors.toList());
		return capturedPriceList;
	}

}
