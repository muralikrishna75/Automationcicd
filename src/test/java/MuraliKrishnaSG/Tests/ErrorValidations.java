package MuraliKrishnaSG.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import MuraliKrishnaSG.testcompnents.Retry;

import MuraliKrishnaSG.Pagefactory.CartPage;
import MuraliKrishnaSG.Pagefactory.ConfirmPage;
import MuraliKrishnaSG.Pagefactory.LandingPage;
import MuraliKrishnaSG.Pagefactory.ProductCatlogue;
import MuraliKrishnaSG.testcompnents.BaseTest;

public class ErrorValidations extends BaseTest {

	@Test(groups= {"ErrorValidation"},retryAnalyzer=Retry.class)
	public void LoginError() {
		LandingPageObj.LaunchingApp("krishna75@gmail.com", "XYZABC");//Krishn1a57
		String error = LandingPageObj.geterrormessages();
		//System.out.println(error);
		Assert.assertEquals(error, "Incorrect email or password.");
	}
	@Test
	public void ProductValidatione() throws InterruptedException {
	ProductCatlogue ProductCatlogueobj = LandingPageObj.LaunchingApp("krishna75@gmail.com", "Krishn1a57");
	String Productname = "IPHONE 13 PRO";
	List<WebElement> products = ProductCatlogueobj.getproductsList();
	ProductCatlogueobj.getproductByname(Productname);
	ProductCatlogueobj.addproductToCart(Productname);
	CartPage CartPageobj = ProductCatlogueobj.goToCartPage();
	Boolean match = CartPageobj.VerifyProductDisplay("IPHONE 13 PRO");
	Assert.assertTrue(match);
	}
}
