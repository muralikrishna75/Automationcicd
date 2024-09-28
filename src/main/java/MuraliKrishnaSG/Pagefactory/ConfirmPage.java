package MuraliKrishnaSG.Pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


import AbstractComponents.AbstractComponent;

public class ConfirmPage<confirmmessage> extends AbstractComponent {
	
	WebDriver driver;
	
	public  ConfirmPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
		
		
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement resultavilable;
	
	@FindBy(css="a.btnn")
	WebElement placeorderbutton;
	
	@FindBy(css=".hero-primary")
	WebElement Message;
	
	
	By result=By.cssSelector(".ta-results");
	
public  String confirm( ) throws InterruptedException {
	
	Actions a = new Actions(driver);
	a.sendKeys(country, "india").build().perform();
	waitproductstoapper(result);
	resultavilable.click();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,500)", "");
	Thread.sleep(4000);
	waitelementtoapper(placeorderbutton);
	placeorderbutton.click();
	
	 String  confirmmessage=Message.getText();
	return confirmmessage;
	
}
}




