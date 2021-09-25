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
import com.base.utils.GenericUtil.CabinClass;
import com.base.utils.GenericUtil.FlightResultSortBy;
import com.base.utils.GenericUtil.Utilkeys;
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

	final String[] origin = { "DXB", "AUH", "SHJ", "JED", "RUH" };

	final String[] destination = { "AMM", "CAI", "DEL", "KHI", "PAR" };

	final int plusMonths = 1;

	/**
	 * @apiNote Login, add products to cart, click on cart image and checkout, enter
	 *          checkout details and click on continue. check for checkout overview
	 *          and click finish. end to end order placement positive scenario. The
	 *          number of products to be added to is to be configured at the
	 *          starting of the method for now. Scrolling to add the number of
	 *          configured products is handled. <b>Verify end to end order placement
	 *          <b>
	 */
	@Test(groups = { "smoke", "sanity" })
	public void testFlightSearchSort() {
		AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver();

		try {
			TajawalBasePage tajawalBasePage = new TajawalBasePage(appiumDriver);
			boolean isTajawalWelcomeTitleDisplayed = tajawalBasePage.getWelcomeTitle().isDisplayed();
			if (isTajawalWelcomeTitleDisplayed) {
				CustomLogger.logResultData("Passed", "Landing page welcome title :", " Displayed", 1);
			} else {
				CustomLogger.logResultData("Failed", "Landing page welcome title :", " Not Displayed", 1);
			}
			boolean isEnglishSelectedByDefault = tajawalBasePage.getWelcomeEnglishButton().isSelected();
			if (!isEnglishSelectedByDefault) {

				ElementUtil.clickElement(appiumDriver, tajawalBasePage.getWelcomeEnglishButton());
			}
			if (tajawalBasePage.getWelcomeEnglishButton().isSelected()) {
				boolean isUAESelected = Boolean.parseBoolean(
						tajawalBasePage.getSelectCountry(appiumDriver, "United Arab Emirates").getAttribute("checked"));
				if (!isUAESelected) {
					ElementUtil.clickElement(appiumDriver,
							tajawalBasePage.getSelectCountry(appiumDriver, "United Arab Emirates"));
				}
				FlightHotelMenuPage flightHotelMenuPage = tajawalBasePage.clickContinueButton(appiumDriver,
						tajawalBasePage);
				WaitUtil.waitForElementToBeClickable(appiumDriver, FlightHotelMenuPage.flightsTabXpath, Locator.Xpath,
						40L);

				SearchFlightsPage searchFlightsPage = flightHotelMenuPage.clickFlightsTab(appiumDriver,
						flightHotelMenuPage);
				WaitUtil.waitForElementToDisplay(appiumDriver, SearchFlightsPage.searchFlightsTextXpath, Locator.Xpath,
						10L);
				boolean isSearchFlightDislayed = searchFlightsPage.getSerchFlightsText().isDisplayed();
				if (isSearchFlightDislayed) {
					CustomLogger.logResultData("Passed", "Search Flight page :", " Displayed", 1);
				} else {
					CustomLogger.logResultData("Failed", "Search Flight page :", " Not Displayed", 1);
				}

				SearchOriginDestinationPage searchOriginDestinationPage = searchFlightsPage.clickFrom(appiumDriver,
						searchFlightsPage);
				ElementUtil.setInput(appiumDriver, searchOriginDestinationPage.getOriginDestinationInputSearch(),
						getRandom(origin));

				WaitUtil.waitForElementToDisplay(appiumDriver, SearchOriginDestinationPage.firstSuggestionXpath,
						Locator.Xpath, 10L);
				searchOriginDestinationPage = searchOriginDestinationPage.clickFirstSuggestion(appiumDriver,
						searchOriginDestinationPage);
				WaitUtil.waitForElementToDisplay(appiumDriver, SearchOriginDestinationPage.searchDestinationXpath,
						Locator.Xpath, 10L);
				ElementUtil.setInput(appiumDriver, searchOriginDestinationPage.getOriginDestinationInputSearch(),
						getRandom(destination));
				WaitUtil.waitForElementToDisplay(appiumDriver, SearchOriginDestinationPage.firstSuggestionXpath,
						Locator.Xpath, 10L);
				searchOriginDestinationPage.clickFirstSuggestion(appiumDriver, searchOriginDestinationPage);
				searchFlightsPage = new SearchFlightsPage(appiumDriver);
				DatePickerPage datePickerPage = searchFlightsPage.clickDepartureDay(appiumDriver, searchFlightsPage);
				WaitUtil.waitForElementToDisplay(appiumDriver, DatePickerPage.departureDateCardViewId, Locator.Id, 5L);
				ElementUtil.clickElement(appiumDriver, datePickerPage.getDepartureDateCardView());
				String monthViewText = datePickerPage.getCurrentMonthViewText().getText();
				boolean isNextDayMonthDateCheckedByDefault = isNextDayMonthDateCheckedByDefault(appiumDriver,
						monthViewText);

				if (isNextDayMonthDateCheckedByDefault) {
					CustomLogger.logResultData("Passed", "Search Flight page :", " Displayed", 1);
				} else {
					CustomLogger.logResultData("Failed", "Search Flight page :", " Not Displayed", 1);
				}
				scrollToAMonthAfterCurrent(appiumDriver);
				ScreenUtil.scrollDown();
				Map<Utilkeys, String> travelDates = selectDepartureReturnDate(appiumDriver, datePickerPage);
				String datesSelected = datePickerPage.getDatesSelected().getText();
				String tripType = datePickerPage.getExtraSubtitleText().getText();
				boolean isTripTypeRound = tripType.equalsIgnoreCase("round-trip");

				boolean isTravelDateSameAsPerChosen = verifyTravelDates(datesSelected, travelDates, false);

				searchFlightsPage = datePickerPage.clickDateSelectConfirmation(appiumDriver, datePickerPage);
				AddPassengerAndClassPage addPassengerAndClassPage = searchFlightsPage.clickAddPassengers(appiumDriver,
						searchFlightsPage);
				addPassengers(appiumDriver, addPassengerAndClassPage, 2, 2, 2);
				addPassengerAndClassPage.clickCabinClass(appiumDriver, addPassengerAndClassPage, CabinClass.ECONOMY);
				searchFlightsPage = addPassengerAndClassPage.clickApplyPassengerMenu(appiumDriver,
						addPassengerAndClassPage);

				FlightResultPage flightResultPage = searchFlightsPage.clickSearchFlights(appiumDriver,
						searchFlightsPage);

				String flightResultHeaderSubtitleDateText = flightResultPage.getFlightResultPageHeaderSubtitle()
						.getText();

				isTravelDateSameAsPerChosen = verifyTravelDates(flightResultHeaderSubtitleDateText, travelDates, true);
				flightResultPage.clickSortBy(appiumDriver, flightResultPage, FlightResultSortBy.CHEAPEST);
				WaitUtil.waitForElementToDisplay(appiumDriver, FlightResultPage.header, Locator.Id, 60L);
				boolean isCheapestTagDisplayed = flightResultPage.getCheapestText().isDisplayed();

				List<Long> actualPriceList = scrollDownAndCaptureFlightPrice(appiumDriver, flightResultPage);
				List<Long> expectedSortedPriceList = actualPriceList.stream().map(p -> p).collect(Collectors.toList());
				Collections.sort(expectedSortedPriceList);
				boolean isPriceSortedAsCheapest = actualPriceList.equals(expectedSortedPriceList);

			} else {
				CustomLogger.logResultData("Failed", "English language selection by default :",
						" Not Selected/Unable to select!", 1);
				Assert.fail("English language selection by default");

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}

	}

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
			e.printStackTrace();
		}
		return isConfirmationDateAsPerSelectedDate;
	}

	public static String getRandom(String[] array) {
		int randonIndex = new Random().nextInt(array.length);
		return array[randonIndex];
	}

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

	public void scrollToAMonthAfterCurrent(AppiumDriver<MobileElement> driver) {
		String aMonthAfterNext = LocalDate.now().getMonth().plus(plusMonths).name().toString();
		String aMonthAfterNextYear = String.valueOf(LocalDate.now().plusMonths(plusMonths).getYear());
		String textToScroll = aMonthAfterNext.concat(" ").concat(aMonthAfterNextYear);
		ScreenUtil.scrollToText(driver, textToScroll);
	}

	private Map<Utilkeys, String> selectDepartureReturnDate(AppiumDriver<MobileElement> driver,
			DatePickerPage datePickerPage) {
		Map<Utilkeys, String> travelDates = new HashMap<Utilkeys, String>();
		int lengthOfMonth = LocalDate.now().plusMonths(plusMonths).lengthOfMonth();
		Random r = new Random();
		int start = 1;
		int mid = 15;
		int high = lengthOfMonth;
		int departureDate = r.nextInt(mid - start) + start;
		int returnDate = r.nextInt(high - mid) + mid;
		travelDates.put(Utilkeys.START, String.valueOf(departureDate));
		travelDates.put(Utilkeys.END, String.valueOf(returnDate));
		travelDates.put(Utilkeys.MONTH,
				LocalDate.now().plusMonths(plusMonths).getMonth().name().substring(0, 3).toLowerCase());

		String dateFieldXpath = DatePickerPage.dateField.replace("xxx", String.valueOf(departureDate - 1));
		ElementUtil.clickElement(driver, driver.findElement(By.xpath(dateFieldXpath)));
		WaitUtil.waitHard(4000);

		dateFieldXpath = DatePickerPage.dateField.replace("xxx", String.valueOf(returnDate - 1));
		WaitUtil.waitForElementToBeClickable(driver, dateFieldXpath, Locator.Xpath, 5L);
		ElementUtil.clickElement(driver, driver.findElement(By.xpath(dateFieldXpath)));

		return travelDates;
	}

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

			int currentInfantCount = Integer.parseInt(addPassengerAndClassPage.getPaxInfant()
					.findElement(By.id(AddPassengerAndClassPage.paxCount)).getText());
			if (currentInfantCount < numberOfInfants) {
				for (int i = currentChildCount; i < numberOfInfants; i++) {
					addPassengerAndClassPage.getPaxInfant().findElement(By.id(AddPassengerAndClassPage.addPax)).click();
				}
			}

		}
	}

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
