/**
 * 
 */
package com.base.pagefactory;

/**
 * @author Sindhu Koti
 * 
 * description : Object repo
 *
 */
public class ObjectRepo {
	
	 public static final String usernameInput = "//*[@content-desc='test-Username']";
	 
	 public static final String passwordInput = "//*[@content-desc='test-Password']";
	 
	 public static final String loginButton = "//*[@text='LOGIN']";
	 
	 public static final String addTocartButton = "//*[@text='ADD TO CART']";
	 
	 public static final String cartInHeader = "//*[@content-desc='test-Cart']/android.view.ViewGroup";
	
	 public static final String checkoutButton = "//*[@content-desc='test-CHECKOUT']";
	 
	 public static final String removeButton = "//*[@content-desc='test-REMOVE']";
	 
	 public static final String continueShoppingButton = "//*[@content-desc='test-CONTINUE SHOPPING']";
	 
	 
	 public static final String checkoutDetailFirstName = "//*[@content-desc='test-First Name']";
	 
	 public static final String checkoutDetailLastName = "//*[@content-desc='test-Last Name']";
	
	 public static final String checkoutDetailPostalCode = "//*[@content-desc='test-Zip/Postal Code']";
	 
	 public static final String checkoutCancelButton = "//*[@content-desc='test-CANCEL']";
	   
	 public static final String checkoutContinueButton = "//*[@content-desc='test-CONTINUE']";
	 
	 public static final String checkoutOverviewText = "//*[@text='CHECKOUT: OVERVIEW']";
	 
	 public static final String checkoutFinishButton = "//*[@text='FINISH']" ;        // "//*[@text='test-FINISH']";
			
	 public static final String checkoutCompleteText = "//*[@text='CHECKOUT: COMPLETE!']" ;   
	 
	 public static final String checkoutCcancelProductsListText = "//*[@text='PRODUCTS']" ;   
	 
	 public static final String loginErrorText = "//*[@content-desc='test-Error message']/android.widget.TextView" ;   
	 
	 public static final String cartItems = "//*[@content-desc='test-Item']" ;  
	
	 public static final String removeItem = "//*[@text='REMOVE']" ; 
	 
	 public static final String usernameErrorClose = "//*[@content-desc='test-Username']//following-sibling::android.view.ViewGroup/android.widget.TextView" ; 
	 
	 public static final String passwordErrorClose = "//*[@content-desc='test-Password']//following-sibling::android.view.ViewGroup/android.widget.TextView" ; 
	 
	 public static final String commonLoginerrorMessage = "//*[@content-desc='test-Error message']/android.widget.TextView" ;  
	 
	 public static final String checkoutFirstnameErrorClose =  "//*[@content-desc='test-First Name']//following-sibling::android.view.ViewGroup/android.widget.TextView" ;
	 
	 public static final String checkoutLastnameErrorClose =  "//*[@content-desc='test-Last Name']//following-sibling::android.view.ViewGroup/android.widget.TextView" ;
 	 
	 public static final String checkoutZipcodeErrorClose =  "//*[@content-desc='test-Zip/Postal Code']//following-sibling::android.view.ViewGroup/android.widget.TextView" ;
	 
	 public static final String commonCheckoutErrorMessage = "//*[@content-desc='test-Error message']/android.widget.TextView" ;  
		 
	}
