package Shopping;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import Resources.Base;

public class CartTest extends Base{
	
	 @Test(dependsOnMethods={"Shopping.CatelogTest.add_and_verify_cart"})
     public void verify_added_product_in_cart_page()
     {
		 //Explicit wait
		 wait = new WebDriverWait(driver,10);
		 wait.until(ExpectedConditions.elementToBeClickable(cart.shoppingBag));
		 
		 //Click on Shopping Bag
		 cart.shoppingBag.click();
		 
		 //Assert Product count in Cart		 
		 int addedProudctCountInCart = cart.addedProudctCountInCart.size();
		 Assert.assertEquals(Integer.parseInt(expectedAddedProudctCountInCart), addedProudctCountInCart);
    }
	 
	 	 
	 @Test(dependsOnMethods={"Shopping.CartTest.verify_added_product_in_cart_page"})
     public void verify_checkout()
	 {
		 cart.checkoutButton.click();

		 //Pop up is displayed 
		 if(cart.returnToCatalogPopUp.isDisplayed())
		 {
			 cart.returnToCatalogButton.click();
		 }
	 }
	 
	 @Test(dependsOnMethods={"Shopping.CatelogTest.select_product_after_returning_from_cart"})
     public void  verify_added_product_in_cart_page_after_addtition()
	 {	 
		//Explicit wait
		 wait = new WebDriverWait(driver,10);
		 wait.until(ExpectedConditions.elementToBeClickable(cart.shoppingBag));
		 
		 //Click on Shopping Bag
		 cart.shoppingBag.click();
		 
		//Explicit wait
		 wait = new WebDriverWait(driver,10);
		 wait.until(ExpectedConditions.visibilityOfAllElements(cart.addedProudctCountInCart));
		 
		 //Verify added product count in cart after addition  
		 int addedProudctCountInCartAfterAddition = cart.addedProudctCountInCart.size();
		 Assert.assertEquals(Integer.parseInt(expectedAddedProudctCountInCartAfterAddition), addedProudctCountInCartAfterAddition);
		 
    }
	 
	 @Test(dependsOnMethods={"Shopping.CartTest.verify_added_product_in_cart_page_after_addtition"})
     public void verify_checkout_after_product_addition_and_continue_as_guest()
	 {
		//click on checkout button
		 cart.checkoutButton.click();
		 
		 //Explicit wait
		 wait = new WebDriverWait(driver,20);
		 wait.until(ExpectedConditions.visibilityOfAllElements(cart.addedProudctCountInCart));
		 
		//Assert "Sign in" page title
		 String actualGetSignInPageTitle = driver.getTitle().toLowerCase();
		 Assert.assertEquals(expectedGetSignInPageTitle, actualGetSignInPageTitle);
		 
	 }	 
}
