package MuraliKrishnaSG.Tests;

import java.time.Duration;
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

public class Standalone {
	public static void main(String[] args) throws InterruptedException {
		String username = "krishna75@gmail.com";
		String Password = "Krishna57";
		String Productname="IPHONE 13 PRO";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get("https://rahulshettyacademy.com/client");comment the lines to check
		//driver.findElement(By.id("userEmail")).sendKeys(username);
		driver.findElement(By.id("userPassword")).sendKeys(Password);
		driver.findElement(By.id("login")).click();
		/*
		 * List<WebElement> element=driver.findElements(By.cssSelector(".mb-3"));
		 * Iterator<WebElement> h= element.iterator(); while(h.hasNext()) {
		 * System.out.println(h.next().findElement(By.tagName("b")).getText());
		 */
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(Productname)).findFirst()
				.orElse(null);
		System.out.println(prod.getText());
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));btn
		// btn-custom
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		List<WebElement> cartproduct=driver.findElements(By.cssSelector(".cart h3"));
		Boolean match=cartproduct.stream().anyMatch(cartproducts->cartproducts.getText().equalsIgnoreCase(Productname));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click(); 
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)","");
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("a.btnn")))).click();//.findElement(By.cssSelector("a.btnn"))
		String confirmmessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(confirmmessage);
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		Thread.sleep(4000);
		driver.close();
	}  

}
