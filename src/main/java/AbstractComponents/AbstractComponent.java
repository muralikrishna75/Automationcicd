package AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MuraliKrishnaSG.Pagefactory.CartPage;
import MuraliKrishnaSG.Pagefactory.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement Cartdashboard;
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement orderHistory;

	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		
		
	}
	public void waitproductstoapper(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));//By.cssSelector(".mb-3")
	}
	
	
	public void waitmessagetodisapper(WebElement ele) throws InterruptedException {
		Thread.sleep(2000);
		/*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));*/
	}
		public   CartPage goToCartPage() {
			Cartdashboard.click();
			CartPage CartPageobj = new CartPage(driver);
			return CartPageobj;
			
		}
		public   OrderPage orderHistorydashboard() {
			orderHistory.click();
			OrderPage OrderPageobj = new OrderPage(driver);
			return OrderPageobj;
			
		}
	
	public void waitelementtoapper(WebElement  element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(element));//By.cssSelector(".mb-3")
	}
	
}
  