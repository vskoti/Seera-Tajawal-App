/**
 * 
 */
package com.base.pages;

import java.util.List;

import org.openqa.selenium.By;

import com.base.pagefactory.MobileDriverPage;
import com.base.utils.ElementUtil;
import com.base.utils.GenericDataUtil.FlightResultSortBy;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * @author Sindhu Koti
 * @apiNote FlightResultPage page class for flight results elements. 
 *           mobile elements, getters and click methods which returns the respective pages. 
 *
 */
public class FlightResultPage extends MobileDriverPage{

	public FlightResultPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	public static final String header = "com.tajawal:id/toolbarFlightResult" ;
	
	public static final String resultFilterSortBy = "//android.widget.TextView[@text='Sort by']" ;
	
	public static final String resultFilters =  "com.tajawal:id/rvQuickFilter" ;
	
	public static final String priceListId =  "com.tajawal:id/tvFinalPrice" ;
	
	
	
	
	public String sortBy = "//*[@text='xxx']" ;
	
	@AndroidFindBy(id = header)
	private MobileElement flightResultPageHeader;
	
	@AndroidFindBy(id = "com.tajawal:id/tvFlightToolbarSubTitle")
	private MobileElement flightResultPageHeaderSubtitle;
	
	@AndroidFindBy(xpath =resultFilterSortBy)
	private MobileElement sortByFilter;

	@AndroidFindBy(id = priceListId)
	private List<MobileElement> flightPriceList;
	
	@AndroidFindBy(xpath = "//*[@text='Cheapest']")
	private MobileElement cheapestText;
	
	
	public MobileElement getCheapestText() {
		return cheapestText;
	}

	public MobileElement getFlightResultPageHeader() {
		return flightResultPageHeader;
	}

	public MobileElement getFlightResultPageHeaderSubtitle() {
		return flightResultPageHeaderSubtitle;
	}

	public MobileElement getSortByFilter() {
		return sortByFilter;
	}
	
	public static String getHeader() {
		return header;
	}

	public List<MobileElement> getFlightPriceList() {
		return flightPriceList;
	}

	public FlightResultPage clickSortBy(AppiumDriver<MobileElement> driver, FlightResultPage flightResultPage, FlightResultSortBy sortByText) {
		ElementUtil.clickElement(driver,flightResultPage.getSortByFilter());
		String xpath = flightResultPage.sortBy.replace("xxx", sortByText.getSortBy());
		ElementUtil.clickElement(driver, driver.findElement(By.xpath(xpath)));
		return new FlightResultPage(driver);		
	}
	
	
}
