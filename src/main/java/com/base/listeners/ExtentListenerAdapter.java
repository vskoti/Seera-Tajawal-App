/**
 * 
 */
package com.base.listeners;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.base.drivers.factory.AppiumService;
import com.base.drivers.factory.DriverFactory;
import com.base.reporting.ExtentManager;
import com.base.reporting.ExtentTestManager;
import com.base.utils.ConfigConstants;
import com.base.utils.CustomLogger;
import com.base.utils.GenericDataUtil;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author Sindhu Koti
 *
 */
public class ExtentListenerAdapter implements ITestListener {

	static File directory;

	public static DriverFactory driverFactory;

	public static ExtentTestManager ExtentManagerImpl;

	/**
	 * Any specific implementation when all test cases are run.
	 */
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("All Tests were Finished.........\n\n");

		ExtentManager.getInstance().flush();
		AppiumService.stopService();
		System.out.println("after flush");
	}

	/**
	 * Before suite checks
	 */
	@Override
	public void onStart(ITestContext arg0) {
        System.gc();;
		AppiumService.startService();
		boolean isfolderDeleted = false;
		try {
			if (!new File(
					"./" + File.separator + GenericDataUtil.getConfigData(ConfigConstants.FAILEDSCREENSHOT_FOLDER_NAME))
							.exists()) {
				new File(
						"./" + File.separator + GenericDataUtil.getConfigData(ConfigConstants.FAILEDSCREENSHOT_FOLDER_NAME))
								.mkdirs();
			} else if (new File(System.getProperty("user.dir") + File.separator
					+ GenericDataUtil.getConfigData(ConfigConstants.FAILEDSCREENSHOT_FOLDER_NAME)).exists()
					&& isfolderDeleted == false) {
				deleteDir(new File("./" + File.separator
						+ GenericDataUtil.getConfigData(ConfigConstants.FAILEDSCREENSHOT_FOLDER_NAME)));
				isfolderDeleted = true;
			}

			if (!new File("./" + File.separator + GenericDataUtil.getConfigData(ConfigConstants.HTML_REPORT_FOLDER_NAME))
					.exists()) {
				new File("./" + File.separator + GenericDataUtil.getConfigData(ConfigConstants.HTML_REPORT_FOLDER_NAME))
						.mkdirs();
			} else if (new File(System.getProperty("user.dir") + File.separator
					+ GenericDataUtil.getConfigData(ConfigConstants.HTML_REPORT_FOLDER_NAME)).exists()
					&& isfolderDeleted == false) {
				deleteDir(new File(
						"./" + File.separator + GenericDataUtil.getConfigData(ConfigConstants.HTML_REPORT_FOLDER_NAME)));
				isfolderDeleted = true;
			}
			
			File file = new File("./log4j2.xml");
		    
			LoggerContext context = (LoggerContext) LogManager.getContext(false);
			context.setConfigLocation(file.toURI());

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * On test fail, inside the
	 * current directory outside the target and save the failed page image with the
	 * test case name. This is very important for debugging the failures.
	 */
	public void onTestFailure(ITestResult testResult) {
		try {		
			    System.gc();
				CustomLogger.logResultData("Failed", testResult.getMethod().getQualifiedName(),testResult.getName() ,1);
				ExtentTestManager.endTest();
				ExtentManager.getInstance().flush();

			} catch (Exception e) {

			}
		      finally {
				// Close Browser
				DriverFactory.getInstance().closeDriver();
			}           
	}

	/**
	 * Form the URL, hard coded here. Initiate the driver on every test case start.
	 */
	public void onTestStart(ITestResult test) {

		try {
			System.gc();
			int port = Integer.parseInt(test.getTestContext().getCurrentXmlTest().getAllParameters().get("systemPort"));
			String plaformName = test.getTestContext().getCurrentXmlTest().getAllParameters().get("systemPort") ;
			String deviceName = test.getTestContext().getCurrentXmlTest().getAllParameters().get("DeviceName") ;
					
			
			System.out.println(" ***************************8 : "+port);
			//DriverFactory.getInstance();
			DriverFactory.setDriver(port,plaformName,deviceName);
		//	DriverFactory.getInstance();
			DriverFactory.getDriver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentTestManager.startTest(test.getName(), test.getName());
		for (int i = 0; i < test.getMethod().getGroups().length; i++) {
			String g = test.getMethod().getGroups()[i];
			ExtentManagerImpl.getTest().assignCategory(g);
		}
		System.out.println(" STRAING TEST CASE ************** :" + test.getMethod().getMethodName());
	}

	/**
	 * Update the result or implement anything specifically on test pass.
	 */
	public void onTestSuccess(ITestResult testResult) {
		try {
			   System.gc();
		       CustomLogger.logResultData("Passed",testResult.getName(),"successfully completed execution!",1);
				ExtentTestManager.endTest();
				ExtentManager.getInstance().flush();		   
			} catch (Exception e) {

			}
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
		DriverFactory.getInstance().closeDriver();

	}

	/**
	 * Deleting the Folder and Files inside the Folder
	 */
	public static boolean deleteDir(File dir) {
		try {
		if (dir.exists()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		} 
		}catch(Exception e) {
			
		}
		
		return dir.delete();
	
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentTestManager.getTest().log(LogStatus.SKIP, "skipped due to last test failure");
		DriverFactory.getInstance().closeDriver();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

}
