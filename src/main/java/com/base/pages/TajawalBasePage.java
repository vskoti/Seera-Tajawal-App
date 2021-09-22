/**
 * 
 */
package com.base.pages;

import org.openqa.selenium.By;

import com.base.pagefactory.MobileDriverPage;
import com.base.utils.ElementUtil;
import com.base.utils.WaitUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * @author sindhu.koti
 *
 */
public class TajawalBasePage extends MobileDriverPage {

	public TajawalBasePage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	@AndroidFindBy(id = "com.tajawal:id/title")
	private MobileElement welcomeTitle;

	@AndroidFindBy(id = "com.tajawal:id/welcomeEnglishButton")
	private MobileElement welcomeEnglishButton;

	public MobileElement getWelcomeTitle() {
		return welcomeTitle;
	}

	@AndroidFindBy(xpath = "//*[@text='Continue']")
	private MobileElement continueButton;
	
	
	public MobileElement getWelcomeEnglishButton() {
		return welcomeEnglishButton;
	}
   
	public MobileElement getSelectCountry(AppiumDriver<MobileElement> driver, String country) {
		return driver.findElement(By.xpath("//android.widget.TextView[@text='"+country
				+ "']/preceding-sibling::android.widget.RadioButton"));	
		}

	public MobileElement getContinueButton() {
		return continueButton;
	}

    public FlightHotelMenuPage clickContinueButton(AppiumDriver<MobileElement> driver, TajawalBasePage tajawalBasePage) {
    	ElementUtil.clickElement(driver, tajawalBasePage.getContinueButton());
    	return new FlightHotelMenuPage(driver);
    }
	
	
	
}
