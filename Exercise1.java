package week1.day5;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Exercise1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*Launch Browser*/		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));    
		ChromeDriver driver = new ChromeDriver(options);

		/*Load URL and maximise window*/
		driver.get("https://acme-test.uipath.com/account/login");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		/*Login to the application*/
		driver.findElement(By.id("email")).sendKeys("kumar.testleaf@gmail.com", Keys.TAB);
		driver.findElement(By.id("password")).sendKeys("leaf@12");
		driver.findElement(By.id("buttonLogin")).click();
		
		/*Mouse Hover on the vendor and click vendor search*/
		WebElement ele = driver.findElementByXPath("//button[text()[normalize-space()='Vendors']]");
		Actions builder = new Actions(driver);
		builder.moveToElement(ele).perform();
		driver.findElement(By.linkText("Search for Vendor")).click();
		
		/*Enter vendor name and find vendor country*/
		driver.findElement(By.id("vendorName")).sendKeys("Blue Lagoon");
		driver.findElement(By.id("buttonSearch")).click();
		System.out.println("Country name is: "+driver.findElement(By.xpath("(//table[@class='table']//td)[5]")).getText());
		
		/*Logout and tear down*/
		driver.findElement(By.linkText("Log Out")).click();
		driver.close();


	}

}
