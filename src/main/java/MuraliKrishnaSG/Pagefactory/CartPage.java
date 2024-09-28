package MuraliKrishnaSG.Pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
	
	public  CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	@FindBy(css=".totalRow button")
	WebElement checkbttn;
	@FindBy(css=".cart h3")
	List<WebElement> cartproduct;
	public Boolean  VerifyProductDisplay(String Productname) {
		
		Boolean match = cartproduct.stream()
				.anyMatch(cartproducts -> cartproducts.getText().equalsIgnoreCase(Productname));
		System.out.println(match);
		
	return match;
	}
	public  ConfirmPage goToCheckout() {
		checkbttn.click();
		ConfirmPage CheckoutPageobj=new ConfirmPage(driver);
		return CheckoutPageobj;
	}
	}
		
	


