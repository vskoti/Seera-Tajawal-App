package com.base.reporting;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.base.utils.ConfigConstants;
import com.base.utils.GenericDataUtil;
import com.relevantcodes.extentreports.ExtentReports;
/**
 * 
 * @author Sindhu Koti
 *
 */
public class ExtentManager {
    private static ExtentReports report;
   
    
    public static synchronized ExtentReports getInstance() {
    	String timeStamp = new SimpleDateFormat("MM-dd-yyyy_HH_mm_ss").format(new Date());
    	String path = null ;
    	try {
    			path = new File("./" + File.separator + GenericDataUtil.getConfigData(ConfigConstants.HTML_REPORT_FOLDER_NAME)) + "\\ExecutionReport-" + timeStamp + ".html";
    		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        if (report == null) {
          report = new ExtentReports(path, true);
        }

        return report;
    }
	
}
