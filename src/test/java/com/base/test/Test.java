/**
 * 
 */
package com.base.test;

import java.io.IOException;

import com.base.utils.ConfigConstants;
import com.base.utils.GenericUtil;

/**
 * @author sindhu.koti
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ports = "4723,22323" ;
		try {
			
			String[] portArr = ports.split(",");
			System.out.println("arr :"+portArr[0]);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		

	}

}
