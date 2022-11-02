package Shopping;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Resources.Base;

public class ContinueAsGuestTest extends Base
{
	@Test(dependsOnMethods={"Shopping.CartTest.verify_checkout_after_product_addition_and_continue_as_guest"})
    public void verify_and_assert_clicked_on_continue_as_guest()
	 {
		//Explicit wait		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		wait = new WebDriverWait(driver,30);
//		wait.until(ExpectedConditions.visibilityOfAllElements(conAsGuest.continueAsGuest));
				
		 //Click on Continue as Guest
		conAsGuest.continueAsGuest.click();
		 
		 //Assert "Find A Stylist"
		 String actualFindAStylistPopupText = conAsGuest.findAStylistText.getText();
		 Assert.assertEquals(expectedFindAStylistPopupText, actualFindAStylistPopupText);	
	 }
	
	@Test(dependsOnMethods={"Shopping.ContinueAsGuestTest.verify_and_assert_clicked_on_continue_as_guest"})
	@Parameters({"selectStylist"})
    public void find_stylist_by_zipcode_and_select_stylist_and_verify_shipping_address_page(String selectStylist)
	 {
		//Explicit wait		
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfAllElements(conAsGuest.zipCode));
		 
		//Enter a Zipcode
		conAsGuest.zipCode.sendKeys(zipcode);
		
		//Explicit wait		
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfAllElements(conAsGuest.zipcodeSearchButton));

		//Click on search button
		conAsGuest.zipcodeSearchButton.click();
		 
		//Select Stylist
		 String[] stylistStrArr = selectStylist.split(",");
		 int[] stylistIds = new int[stylistStrArr.length];

		 for(int i = 0; i < stylistStrArr.length; i++) {
		     stylistIds[i] = Integer.parseInt(stylistStrArr[i]);		     
		 }	
		 
		 int listOfStylists = conAsGuest.getListOfStylist.size();
		 for(int d=0; d<listOfStylists; d++)
		 {
			 int index = d+1;
			 for(int x : stylistIds)
			 {
				 if(x == index)
				 {					 
					conAsGuest.getListOfStylist.get(d).click();	
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			
					 break;
				 }
			 }
		 }
		 
		 //Assert "Shipping page" title
		String actualGetShippingPageTitle = driver.getTitle().toLowerCase();
		Assert.assertEquals(expectedGetShippingPageTitle, actualGetShippingPageTitle);
	 }
}
