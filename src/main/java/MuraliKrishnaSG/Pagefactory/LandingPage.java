package MuraliKrishnaSG.Pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	// String username = "krishna75@gmail.com";
	// String Password = "Krishna57";
	String Url = "https://rahulshettyacademy.com/client";
	@FindBy(id = "userEmail")
	WebElement usernameField;
	@FindBy(id = "userPassword")
	WebElement userPasswordField;
	@FindBy(id = "login")
	WebElement loginField;
	@FindBy(css = "[class*='flyInOut']")
	WebElement errormessage;

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public ProductCatlogue LaunchingApp(String username, String Password) {

		usernameField.sendKeys(username);
		userPasswordField.sendKeys(Password);
		loginField.click();
		ProductCatlogue ProductCatlogueobj = new ProductCatlogue(driver);
		return ProductCatlogueobj;
	}

	public void AppUrl() {
		driver.get(Url);
	}

	public String geterrormessages() {
		return errormessage.getText();
	}

}
