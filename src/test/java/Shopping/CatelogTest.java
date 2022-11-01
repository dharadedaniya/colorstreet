package Shopping;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Resources.Base;

public class CatelogTest extends Base {
	
	 @Test(dependsOnMethods={"Shopping.HomePageTest.select_and_verify_country"})
     public void select_and_verify_solid_colors_from_menu() throws IOException
	 {
		 // Select "Shop" menu
		 action = new Actions(driver); 
		 int listOfMenuElements = catelog.getMenuElements.size();
		 for(int a=0; a<listOfMenuElements; a++)
		 {
			 String menuItem = catelog.getMenuElements.get(a).getText();
			 if(menuItem.toLowerCase().equalsIgnoreCase(menuElementName))
			 {
				 WebElement mouseHoverOnShop = catelog.getMenuElements.get(a);
				 action.moveToElement(mouseHoverOnShop).build().perform();
				 break;
			 }
		 }
		 
		 //Select "Solid colors" in "Nails" from "Shop" menu	 
		 int listOfSubmenuElements = catelog.getSubmenuElements.size();
		 for(int b=0; b<listOfSubmenuElements; b++)
		 {
			 String nailsMenu = catelog.getSubmenuElements.get(b).getText();
			 if(nailsMenu.toLowerCase().equalsIgnoreCase(nailsColor))
			 {
				 WebElement mousehoverOnSolidColor = catelog.getSubmenuElements.get(b);
				 action.moveToElement(mousehoverOnSolidColor).perform();
				 mousehoverOnSolidColor.click();

				 //Explicit wait
				wait = new WebDriverWait(driver,20);
				wait.until(ExpectedConditions.visibilityOfAllElements(catelog.getListItems));
				break;
			 }
		 }
		 
		 //Assert solid color page title
		 String actualGetSolidColorPageTitle = driver.getTitle().toLowerCase();
		 Assert.assertEquals(expectedSolidColorPageTitle, actualGetSolidColorPageTitle);
		 
		 System.out.println("222222222222222222222222222");
    }
	
	
	@Test(dependsOnMethods={"Shopping.CatelogTest.select_and_verify_solid_colors_from_menu"})
	 @Parameters({"selectedProducts"})
     public void add_and_verify_cart(String selectedProducts) throws IOException
	{	 
		String[] productStrArr = selectedProducts.split(",");
		int[] productIds = new int[productStrArr.length];
		for(int i = 0; i < productStrArr.length; i++)
		{
		    productIds[i] = Integer.parseInt(productStrArr[i]);		     
		}
		int listOfItems = catelog.getListItems.size();
		for(int c=0; c<listOfItems; c++)
		{
			int index = c+1;
			for(int x : productIds)
			{
				if(x == index)
				{
					catelog.addToCartButton.get(c).click();
					//Explicit wait
					wait = new WebDriverWait(driver,30);
					wait.until(ExpectedConditions.elementToBeClickable(catelog.addedProducts));
					break;
				}
			}
		}
		
		/* try{
			 driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			 String addedCountStr = catelog.addedProducts.getText();
				// int addedProductCount = Integer.parseInt(addedCountStr);
				 Assert.assertEquals(expectedAddedProductCount, addedCountStr);
			 }

			 catch (Exception e) {
			         System.out.println("Error. Closing");
			         driver.quit();
			         Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");} */
		
		
		//Assert 3 products added
		 
		 
		 System.out.println("33333333333333333333333333333333333");

    }
	 
	 @Test(dependsOnMethods={"Shopping.CartTest.verify_checkout"})
	 @Parameters({"laterSelectedProduct"})
	 public void select_product_after_returning_from_cart(String laterSelectedProduct)
	 {
		 String[] productStrArr = laterSelectedProduct.split(",");
		 int[] productIds = new int[productStrArr.length];
		 for(int i = 0; i < productStrArr.length; i++)
		 {
		     productIds[i] = Integer.parseInt(productStrArr[i]);		     
		 }	
		 int listOfItems = catelog.getListItems.size();
		 for(int c=0; c<listOfItems; c++)
		 {
			 int index = c+1;
			 for(int x : productIds)
			 {
				 if(x == index)
				 {
					 catelog.addToCartButton.get(c).click();
					//Explicit wait 
					wait = new WebDriverWait(driver,15);
					wait.until(ExpectedConditions.elementToBeClickable(cart.shoppingBag));				 
					break;
				 }
			 }
		 }
		 
		 System.out.println("66666666666666666666");

    }
}
