package MuraliKrishnaSG.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MuraliKrishnaSG.Pagefactory.CartPage;
import MuraliKrishnaSG.Pagefactory.ConfirmPage;
import MuraliKrishnaSG.Pagefactory.OrderPage;
import MuraliKrishnaSG.Pagefactory.ProductCatlogue;
import MuraliKrishnaSG.testcompnents.BaseTest;

public class StandaloneTest extends BaseTest {
	//String Productname = "ADIDAS ORIGINAL";

	@Test(dataProvider="getdata",groups= {"Purchase"})
	public void submitorder(HashMap<String,String > input) throws IOException, InterruptedException {
		ProductCatlogue ProductCatlogueobj = LandingPageObj.LaunchingApp(input.get("email"),input.get("password") );

		List<WebElement> products = ProductCatlogueobj.getproductsList();
		ProductCatlogueobj.getproductByname(input.get("Productname"));
		ProductCatlogueobj.addproductToCart(input.get("Productname"));
		CartPage CartPageobj = ProductCatlogueobj.goToCartPage();
		Boolean match = CartPageobj.VerifyProductDisplay(input.get("Productname"));
		Assert.assertTrue(match);
		CartPageobj.goToCheckout();
		ConfirmPage ConfirmPageobj = new ConfirmPage(driver);
		String finalmessage = ConfirmPageobj.confirm();
		Assert.assertTrue(finalmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	
	@Test(dependsOnMethods= {"submitorder"})
	public void OrderHistory() {
		String Productname = "ADIDAS ORIGINAL";
		ProductCatlogue ProductCatlogueobj = LandingPageObj.LaunchingApp("krishna75@gmail.com", "Krishna57");
		OrderPage OrderPageobj = new OrderPage(driver);
		Boolean match = OrderPageobj.VerifyProductDisplay(Productname);
		Assert.assertTrue(match);

	}
	/* First type 
	@DataProvider
	public Object[][] getdata(){
		return new Object[][] {{"krishna75@gmail.com","Krishna57","ADIDAS ORIGINAL"}, {"shetty@gmail.com","Iamking@000","ZARA COAT 3"}};
		}
	@Test(dataProvider="getdata",groups= {"Purchase"})
	public void submitorder(String email,String password,String Productname) throws IOException, InterruptedException {
		ProductCatlogue ProductCatlogueobj = LandingPageObj.LaunchingApp(email, password);	
	*/
	@DataProvider
	public Object[][] getdata() throws IOException{
		/*HashMap<String,String > map =new HashMap<String,String >();
		map.put("email", "krishna75@gmail.com");
		map.put("password", "Krishna57");
		map.put("Productname", "ADIDAS ORIGINAL");
		HashMap<String,String > map1 =new HashMap<String,String >();
		map1.put("email", "shetty@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("Productname", "ZARA COAT 3");*/
		List<HashMap<String, String>> data=getJsonToDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\MuraliKrishnaSG\\Data\\purchaseorder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
	
}
