package week1.day5;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Exercise2 {
	
	public static void main(String[] args) throws Exception {
		
		/*Launch Browser*/		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);

		/*Load URL and maximise window*/
		driver.get("https://redbus.in");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		/*Enter source and destination*/
		driver.findElement(By.id("src")).sendKeys("chennai");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//li[text()='Chennai (All Locations)']")).click();
		driver.findElement(By.id("dest")).sendKeys("bangalore", Keys.TAB);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//li[text()='Bangalore (All Locations)']")).click();
		
		/*Select date and click search*/
		driver.findElement(By.xpath("(//span[contains(@class,'fl icon-calendar_icon-new')])[1]")).click();
		driver.findElement(By.xpath("(//td[text()='30'])[2]")).click();
		driver.findElement(By.id("search_btn")).click();
		
		/*Apply filters and sort search*/
		driver.findElement(By.xpath("(//label[@for='dtAfter 6 pm'])[2]")).click();
		driver.findElement(By.xpath("(//label[@for='bt_SLEEPER'])[1]")).click();
		driver.findElement(By.xpath("//*[text()='Seats Available']")).click();
		
		/*tear down*/
		driver.close();
		
		
		
		
	}

}
