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
public class SearchOriginDestinationPage extends MobileDriverPage{

	public SearchOriginDestinationPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	public static final String firstSuggestionXpath = "//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup";
	
	public static final String searchDestinationXpath = "//*[@text='Search Destination']";
	
	@AndroidFindBy(xpath = "//*[@text='Search Origin']")
	private MobileElement searchOriginText;
	
	@AndroidFindBy(xpath = searchDestinationXpath)
	private MobileElement searchDestinationText;
	
	@AndroidFindBy(id = "com.tajawal:id/edSearch")
	private MobileElement originDestinationInputSearch;

	@AndroidFindBy(xpath = firstSuggestionXpath)
	private MobileElement firstSuggestion;
	
	public MobileElement getSearchOriginText() {
		return searchOriginText;
	}

	public MobileElement getOriginDestinationInputSearch() {
		return originDestinationInputSearch;
	}

	public MobileElement getFirstSuggestion() {
		return firstSuggestion;
	}
	
	public SearchOriginDestinationPage clickFirstSuggestion(AppiumDriver<MobileElement> driver,  SearchOriginDestinationPage searchOriginDestinationPage) {
		ElementUtil.clickElement(driver, searchOriginDestinationPage.getFirstSuggestion());
		return new SearchOriginDestinationPage(driver);
	}
	
	
	
}
