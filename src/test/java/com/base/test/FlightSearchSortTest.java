/**
 * 
 */
package com.base.test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.base.drivers.factory.DriverFactory;
import com.base.listeners.ExtentListenerAdapter;
import com.base.pagefactory.ObjectRepo;
import com.base.pages.FlightHotelMenuPage;
import com.base.pages.SearchFlightsPage;
import com.base.pages.SearchOriginDestinationPage;
import com.base.pages.TajawalBasePage;
import com.base.utils.CustomLogger;
import com.base.utils.ElementUtil;
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

	/**
	 * @apiNote Login, add products to cart, click on cart image and checkout, enter
	 *          checkout details and click on continue. check for checkout overview
	 *          and click finish. end to end order placement positive scenario. The
	 *          number of products to be added to is to be configured at the
	 *          starting of the method for now. Scrolling to add the number of
	 *          configured products is handled. <b>Verify end to end order placement
	 *          <b>
	 */
	@Test(groups = { "smoke", "sanity"})
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
				System.out.println(" english not selected by default.");
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
				
				SearchFlightsPage searchFlightsPage = flightHotelMenuPage.clickFlightsTab(appiumDriver, flightHotelMenuPage);
				WaitUtil.waitForElementToDisplay(appiumDriver, SearchFlightsPage.searchFlightsTextXpath, Locator.Xpath, 10L);
				boolean isSearchFlightDislayed = searchFlightsPage.getSerchFlightsText().isDisplayed(); 
				if(isSearchFlightDislayed) {
					CustomLogger.logResultData("Passed", "Search Flight page :", " Displayed", 1);
				}else {
					CustomLogger.logResultData("Failed", "Search Flight page :", " Not Displayed", 1);
				}
                
				SearchOriginDestinationPage searchOriginDestinationPage = searchFlightsPage.clickFrom(appiumDriver, searchFlightsPage);
				ElementUtil.setInput(appiumDriver, searchOriginDestinationPage.getOriginDestinationInputSearch(), "DXB");
				
				WaitUtil.waitForElementToDisplay(appiumDriver, SearchOriginDestinationPage.firstSuggestionXpath, Locator.Xpath, 10L);
				searchOriginDestinationPage = searchOriginDestinationPage.clickFirstSuggestion(appiumDriver, searchOriginDestinationPage);
				WaitUtil.waitForElementToDisplay(appiumDriver, SearchOriginDestinationPage.searchDestinationXpath, Locator.Xpath, 10L);
				ElementUtil.setInput(appiumDriver, searchOriginDestinationPage.getOriginDestinationInputSearch(), "BLR");
				WaitUtil.waitForElementToDisplay(appiumDriver, SearchOriginDestinationPage.firstSuggestionXpath, Locator.Xpath, 10L);
				searchOriginDestinationPage.clickFirstSuggestion(appiumDriver, searchOriginDestinationPage);
				searchFlightsPage = new SearchFlightsPage(appiumDriver);
				ElementUtil.clickElement(appiumDriver, searchFlightsPage.getDepartureDateInput());
				
				Thread.sleep(50000);
				// flight search
				// date picker utility
				// wait for page load
				// assert the sorted flights display

			} else {
				CustomLogger.logResultData("Failed", "English language selection by default :",
						" Not Selected/Unable to select!", 1);

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}

	}

	public static String getRandom(String[] array) {
		int randonIndex = new Random().nextInt(array.length);
		return array[randonIndex];
	}

}
