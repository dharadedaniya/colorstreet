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
		 //Click on Continue as Guest
		conAsGuest.continueAsGuest.click();
		 
		 //Assert "Find A Stylist"
		 String actualFindAStylistPopupText = conAsGuest.findAStylistText.getText();
		 Assert.assertEquals(expectedFindAStylistPopupText, actualFindAStylistPopupText);	
		 
		 System.out.println("9999999999999999999999999999");
	 }
	
	@Test(dependsOnMethods={"Shopping.ContinueAsGuestTest.verify_and_assert_clicked_on_continue_as_guest"})
	@Parameters({"selectStylist"})
    public void find_stylist_by_zipcode_and_select_stylist_and_verify_shipping_address_page(String selectStylist)
	 {
		try {
			//Enter a Zipcode
		
		 System.out.println("10");

		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conAsGuest.zipCode.sendKeys(zipcode);

		
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
					wait = new WebDriverWait(driver,10);
					wait.until(ExpectedConditions.visibilityOfAllElements(conAsGuest.getListOfStylist));				
					 break;
				 }
			 }
		 }
		 
		 //Assert "Shipping page" title
		/* String actualGetShippingPageTitle = driver.getTitle().toLowerCase();
		 Assert.assertEquals(expectedGetShippingPageTitle, actualGetShippingPageTitle); */
		}catch(Exception e) {
			driver.close();
		}
	 }

}
