package MuraliKrishnaSG.testcompnents;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import MuraliKrishnaSG.Pagefactory.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage LandingPageObj;

	public WebDriver initilaizer() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\MuraliKrishnaSG\\resources\\GlobalData.properties");

		prop.load(fis);
		String browserName=System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		if (browserName.toLowerCase().contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options= new ChromeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(140,900));
			System.out.println("Launching chrome");

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("Launching firefox");

		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("Launching Edge");

		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
		public List<HashMap<String,String>> getJsonToDataToMap(String filepath) throws IOException {
			//Json to string
			String JsonContent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);//String Hashmap
			ObjectMapper mapper=new ObjectMapper();
			List<HashMap<String, String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String, String>>>(){});
			return data;
			
			
		}
		public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
			TakesScreenshot ts=	(TakesScreenshot)driver;
			File Source=ts.getScreenshotAs(OutputType.FILE);
			File file=new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png");
			FileUtils.copyFile(Source, file);
			return System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
			
			 //String path=System.getProperty("user.dir")+"\\reports\\index.html";
		}

	@BeforeMethod(alwaysRun=true)
	public LandingPage launchapplication() throws IOException {
		driver = initilaizer();
		LandingPageObj = new LandingPage(driver);
		LandingPageObj.AppUrl();
		return LandingPageObj;

	}

	@AfterMethod(alwaysRun=true)
	public void teardown() {
		driver.close();
	}
	

}
