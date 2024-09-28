package MuraliKrishnaSG.Pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.AbstractComponent;

public class ProductCatlogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatlogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastmessage = By.cssSelector("#toast-container");

	public List<WebElement> getproductsList() {
		waitproductstoapper(productsBy);
		return products;

	}

	public WebElement getproductByname(String productname) {
		WebElement prod = getproductsList().stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(productname)).findFirst()
				.orElse(null);
		System.out.println(prod.getText());
		return prod;

	}

	public void addproductToCart(String Productname) throws InterruptedException {
		WebElement prod = getproductByname(Productname);
		prod.findElement(addToCart).click();
		waitproductstoapper(toastmessage);
		waitmessagetodisapper(spinner);

	}

}
