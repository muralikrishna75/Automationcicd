package MuraliKrishnaSG.Pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	WebDriver driver;
	
	public  OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productsordered;
	
	public Boolean  VerifyProductDisplay(String Productname) {
		orderHistorydashboard( );
		int count=0;
		for (WebElement product : productsordered) {
		    String prod=product.getText();
		    if(prod.equalsIgnoreCase(Productname)) {
		    	System.out.println(prod);
		    count++;
		    }
		}
		System.out.println(count);
		Boolean match = productsordered.stream()
				.anyMatch(productsordered -> productsordered.getText().equalsIgnoreCase(Productname));

		System.out.println(match);
		
	return match;
	}
	
	}
		
	


