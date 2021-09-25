/**
 * 
 */
package com.base.pages;

import org.openqa.selenium.By;

import com.base.pagefactory.MobileDriverPage;
import com.base.utils.ElementUtil;
import com.base.utils.GenericUtil.CabinClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * @author sindhu.koti
 *
 */
public class AddPassengerAndClassPage extends MobileDriverPage {
     
	public AddPassengerAndClassPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	public static final String cabinClass = "//android.widget.TextView[@text='xxx']/following-sibling::android.widget.RadioButton" ;
	
	public static final String addPax = "com.tajawal:id/imgAddPax" ;
	
	public static final String paxCount = "com.tajawal:id/tvPaxCount" ;
	
	
	
	@AndroidFindBy(id = "com.tajawal:id/applyMenu")
	private MobileElement applyMenu;
	
	@AndroidFindBy(id = "com.tajawal:id/paxAdult")
	private MobileElement paxAdult;

	@AndroidFindBy(id = "com.tajawal:id/paxChild")
	private MobileElement paxChild;

	@AndroidFindBy(id = "com.tajawal:id/paxInfants")
	private MobileElement paxInfant;

	@AndroidFindBy(id = addPax)
	private MobileElement addPaxButton;
	
	@AndroidFindBy(id = paxCount)
	private MobileElement paxCountText;

	
	public MobileElement getPaxAdult() {
		return paxAdult;
	}

	public MobileElement getPaxChild() {
		return paxChild;
	}

	public MobileElement getPaxInfant() {
		return paxInfant;
	}

	public MobileElement getAddPaxButton() {
		return addPaxButton;
	}
	
	
	public static String getCabinclass() {
		return cabinClass;
	}

	public static String getAddpax() {
		return addPax;
	}

	public static String getPaxcount() {
		return paxCount;
	}

	public MobileElement getApplyMenu() {
		return applyMenu;
	}

	public void clickCabinClass(AppiumDriver<MobileElement> driver, AddPassengerAndClassPage addPassengerAndClassPage,CabinClass cabin) {
		String xpath = cabinClass.replace("xxx", cabin.getCabin());
		ElementUtil.clickElement(driver, driver.findElement(By.xpath(xpath)));
	}
	
	public SearchFlightsPage clickApplyPassengerMenu(AppiumDriver<MobileElement> driver,  AddPassengerAndClassPage addPassengerAndClassPage) {
		ElementUtil.clickElement(driver, addPassengerAndClassPage.getApplyMenu());
		return new SearchFlightsPage(driver);
	}
	
	
	
}
