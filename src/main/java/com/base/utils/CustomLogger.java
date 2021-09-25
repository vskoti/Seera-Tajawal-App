/**
 * 
 */
package com.base.utils;

import java.util.Calendar;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.base.drivers.factory.DriverFactory;
import com.base.reporting.ExtentManager;
import com.base.reporting.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

/** 
 * @author Sindhu Koti
 * @apiNote Custom Logger class for logging using Extent Report.
 *
 */
public class CustomLogger {

	public static String fGetRandomNumUsingTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(calendar.getTime());
		int month = Calendar.MONTH;
		int day = Calendar.DAY_OF_MONTH;
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		int counterScreenshot = 1;
		String Rand = counterScreenshot + Integer.toString(month) + Integer.toString(day) + Integer.toString(hours)
				+ Integer.toString(minutes) + Integer.toString(seconds);
		return Rand;
	}

	public static void logResultData(String Status, String Step, String Decripion, int ScreenShot) throws Exception {
		//ScreenShot = 0 ;
		switch (ScreenShot) {
		case 1:

			StringBuilder screenshotName = new StringBuilder("img" + fGetRandomNumUsingTime() + ".jpg");

			try {

				StringBuilder screenshotBase64 = new StringBuilder(((TakesScreenshot) DriverFactory.getInstance().getDriver())
						.getScreenshotAs(OutputType.BASE64));
				StringBuilder justString = new StringBuilder(screenshotBase64);
				StringBuilder base = new StringBuilder("data:image/png;base64,");				
				screenshotBase64 =   base.append(screenshotBase64.toString());  
				if (Status.equals("Passed")) {
					//ExtentTestManager.getTest().log(LogStatus.PASS, Step + "-- The Actual Is : " + '\n' + Decripion, getSource(screenshotBase64, justString).toString());
					ExtentTestManager.getTest().log(LogStatus.PASS, Step + "-- The Actual Is : " + '\n' + Decripion,getSource(screenshotBase64, justString).toString());
					
				} else {
					ExtentTestManager.getTest().log(LogStatus.FAIL, Step + "-- The Actual Is : " + '\n' + Decripion,
							getSource(screenshotBase64, justString).toString());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
			
		case 0:

			try {

				//System.out.println(screenshotName);
				
				if (Status.equals("Passed")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, Step + "-- The Actual Is : " + '\n' + Decripion);

				} else {
					StringBuilder screenshotBase64 = new StringBuilder(((TakesScreenshot) DriverFactory.getInstance().getDriver())
							.getScreenshotAs(OutputType.BASE64));
					StringBuilder justString = new StringBuilder(screenshotBase64);
					StringBuilder base = new StringBuilder("data:image/png;base64,");				
					screenshotBase64 =   base.append(screenshotBase64.toString());  
									
					ExtentTestManager.getTest().log(LogStatus.FAIL, Step + "-- The Actual Is : " + '\n' + Decripion,
							getSource(screenshotBase64, justString).toString());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
			
		}

		// report.flush();
		ExtentManager.getInstance().flush();
	}

	public static String getSource(StringBuilder imgPath, StringBuilder string) {
		
		StringBuilder base = new StringBuilder("<a class='report-img' href='" + imgPath + "' onclick=openBase64InNewTab('" + string
				+ "','image/png') > <img  class='report-img' src='" + imgPath + "' /> </a>" + getUrlJs()
				);
		
		return base.toString() ;
		/*
		return "<a class='report-img' href='" + imgPath + "' onclick=openBase64InNewTab('" + string
				+ "','image/png') > <img  class='report-img' src='" + imgPath + "' /> </a>" + getUrlJs();
				*/

	}
	/*
	 * private static String encodeFileToBase64Binary(File file) { String
	 * encodedfile = null; try {
	 * 
	 * // FileInputStream fileInputStreamReader = new FileInputStream(file); byte[]
	 * bytes = new byte[(int) file.length()]; // fileInputStreamReader.read(bytes);
	 * encodedfile = new String(Base64.encodeBase64URLSafeString(bytes)); } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * return encodedfile; }
	 */

	public static String getUrlJs() {

		return "<script>  " + " function openBase64InNewTab(data, mimeType) {\r\n"
				+ "    var byteCharacters = atob(data);\r\n"
				+ "    var byteNumbers = new Array(byteCharacters.length);\r\n"
				+ "    for (var i = 0; i < byteCharacters.length; i++) {\r\n"
				+ "        byteNumbers[i] = byteCharacters.charCodeAt(i);\r\n" + "    }\r\n"
				+ "    var byteArray = new Uint8Array(byteNumbers);\r\n"
				+ "    var file = new Blob([byteArray], { type: mimeType + ';base64' });\r\n"
				+ "    var fileURL = URL.createObjectURL(file);\r\n" + "    window.open(fileURL);\r\n" + "}  </script>";
	}

}
