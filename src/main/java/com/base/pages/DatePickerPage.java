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
 * @apiNote DatePickerPage page class for choosing the departure and return date of travel and apply.
 *           mobile elements, getters and click methods which returns the respective pages. 
 */
public class DatePickerPage extends MobileDriverPage{

	public DatePickerPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	public static String checkedDateText = "//b.a.d.g.t.j.a[@index='1']/b.a.d.g.t.j.b[@index='0']//android.widget.CheckedTextView[@index='xxx']" ;
			
			//"//b.a.d.g.t.j.a//b.a.d.g.t.j.b//android.widget.CheckedTextView[@text='xxx']" ;
	
	public static final String departureDateCardViewId = "com.tajawal:id/fromDateTV";
	
	public static final String returnDateCardViewId = "com.tajawal:id/toDateTab";
	
	public static final String dateField = "//b.a.d.g.t.j.b[@index='0']//android.widget.CheckedTextView[@index='xxx']" ;
	
	
	
	
	@AndroidFindBy(xpath = "//*[@content-desc='month_name_view' and @index='0']")
	private MobileElement currentMonthViewText;

	@AndroidFindBy(id = departureDateCardViewId)
	private MobileElement departureDateCardView;
	
	@AndroidFindBy(id = returnDateCardViewId)
	private MobileElement returnDateCardView;
	
	@AndroidFindBy(id = "com.tajawal:id/datesSelected")
	private MobileElement datesSelected;
	
	@AndroidFindBy(id = "com.tajawal:id/confirmTV")
	private MobileElement confirmButton;
	
	@AndroidFindBy(id = "com.tajawal:id/extraSubtitle")
	private MobileElement extraSubtitleText;
	
	
	
	public MobileElement getCurrentMonthViewText() {
		return currentMonthViewText;
	}


	public static String getCheckedDateText() {
		return checkedDateText;
	}


	public MobileElement getDepartureDateCardView() {
		return departureDateCardView;
	}


	public static String getDeparturedatecardviewid() {
		return departureDateCardViewId;
	}


	public static String getReturndatecardviewid() {
		return returnDateCardViewId;
	}


	public MobileElement getReturnDateCardView() {
		return returnDateCardView;
	}


	public static String getDatefield() {
		return dateField;
	}


	public MobileElement getDatesSelected() {
		return datesSelected;
	}


	public MobileElement getConfirmButton() {
		return confirmButton;
	}


	public MobileElement getExtraSubtitleText() {
		return extraSubtitleText;
	}

	
	public SearchFlightsPage clickDateSelectConfirmation(AppiumDriver<MobileElement> driver, DatePickerPage datePickerPage) {
		ElementUtil.clickElement(driver, datePickerPage.getConfirmButton());
		return new SearchFlightsPage(driver);
		
	}
	
	
	
}
