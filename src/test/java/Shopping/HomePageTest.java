package Shopping;

import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import Resources.Base;
import pageObjects.CartPage;
import pageObjects.CatelogPage;
import pageObjects.ContinueAsGuestPage;
import pageObjects.HomePage;


public class HomePageTest extends Base {
	
	@BeforeSuite
	public void initialize_driver() throws IOException
	{
		driver = initilizeDriver();
		driver.get(siteUrl);		
		homepage = PageFactory.initElements(driver, HomePage.class);
		catelog = PageFactory.initElements(driver, CatelogPage.class);
		cart = PageFactory.initElements(driver, CartPage.class);
		conAsGuest = PageFactory.initElements(driver, ContinueAsGuestPage.class);
	}
	
	 @Test
     public void select_and_verify_country() {
	        
		 // Select country 
		 WebElement country = homepage.country;
		 Select countryDropdown = new Select(country);
		 countryDropdown.selectByVisibleText(countryName);
		 
		 //Select language
		 WebElement languageEle = homepage.language;
		 Select langDropdown = new Select(languageEle);
		 langDropdown.selectByVisibleText(language);
		 
		 // Hit submit button
		 homepage.submitButton.click();
	
		 //Assert actual country and expected country
		 String actualCountry = homepage.getCountryName.getText();
		 Assert.assertEquals(expectedCountryName, actualCountry);		 
    }	
	 
	@AfterSuite
     public void terminate_browser(){
         driver.close();
     }
	
}
