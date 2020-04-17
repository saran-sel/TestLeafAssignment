package week3.day5;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class Project_WK3 {


	public static void main(String[] args) throws Exception {

		/*Launch Browser*/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		
		/*Load URL and maximise window*/
		driver.get("https://dev92474.service-now.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		/*Login to the application*/
		driver.switchTo().frame(driver.findElement(By.id("gsft_main")));
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("India@123");
		driver.findElement(By.id("sysverb_login")).click();
		
		/*Enter Incidents and click new*/
		driver.findElement(By.id("filter")).sendKeys("Incident",Keys.ENTER);
		driver.findElement(By.xpath("\r\n" + 
				"//span[text()='Incident']/following::div[text()='Create New']")).click();
		
		/*Create Incident*/
		Thread.sleep(5000);
		driver.switchTo().frame(driver.findElement(By.id("gsft_main")));

		String incidentValue1 = driver.findElement(By.id("incident.number")).getAttribute("value");
		String parentHandle = driver.getWindowHandle();
		
		Thread.sleep(5000);
		driver.findElement(By.id("lookup.incident.caller_id")).click();

		/*Window Handling for Caller*/
		Set<String> allHandles = driver.getWindowHandles();
		String currentWindowHandle = allHandles.iterator().next();
		allHandles.remove(allHandles.iterator().next());
		
		String lastHandle = allHandles.iterator().next();
		driver.switchTo().window(lastHandle);
		driver.findElement(By.xpath("(//table[@id='sys_user_table']/tbody/tr/td[3]/a)[1]")).click();

		driver.switchTo().window(parentHandle);
		driver.switchTo().frame(driver.findElement(By.id("gsft_main")));

		/*Select Dropdown values*/
		Select dropdown = new Select(driver.findElement(By.name("incident.category")));
		dropdown.selectByValue("software");
		
		Select dropdown2 = new Select(driver.findElement(By.name("incident.subcategory")));
		dropdown2.selectByValue("email");
		
		Select dropdown3 = new Select(driver.findElement(By.name("incident.contact_type")));
		dropdown3.selectByValue("walk-in");
		
		Select dropdown4 = new Select(driver.findElement(By.name("incident.state")));
		dropdown4.selectByVisibleText("In Progress");
		
		Select dropdown5 = new Select(driver.findElement(By.name("incident.urgency")));
		dropdown5.selectByVisibleText("1 - High");
		
		
		driver.findElement(By.id("lookup.incident.assignment_group")).click();
		
		/*Window Handling for AG*/
		Set<String> allHandles1 = driver.getWindowHandles();
		String currentWindowHandle1 = allHandles1.iterator().next();
		allHandles1.remove(allHandles1.iterator().next());
		
		String lastHandle1 = allHandles1.iterator().next();
		driver.switchTo().window(lastHandle1);
		driver.findElement(By.xpath("(//table[@id='sys_user_group_table']/tbody/tr/td[3]/a)[last()]")).click();
		driver.switchTo().window(parentHandle);
		
		/*Entering Short Description and Submit*/
		driver.switchTo().frame(driver.findElement(By.id("gsft_main")));
		driver.findElement(By.id("incident.short_description")).sendKeys("Creating Incident For the Purpose of Management");
		driver.findElement(By.id("sysverb_insert")).click();
		
		/*Redirect to search page and enter recently created*/
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(incidentValue1,Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//table[@id='incident_table']/tbody/tr/td[3]/a")).click();
		
		/*Verifying the created value with updated value*/
		String incidentValue2 = driver.findElement(By.id("incident.number")).getAttribute("value");
		
		if(incidentValue1.equals(incidentValue2)) {
			System.out.println("Incident value displayed as expected");
		}
		
		/*Update Scenario*/
		Select dropdown6 = new Select(driver.findElement(By.name("incident.impact")));
		dropdown6.selectByVisibleText("1 - High");
		
		driver.findElement(By.id("incident.description")).sendKeys("Successfully Created an incident");
		driver.findElement(By.id("activity-stream-textarea")).sendKeys("Done Right");
		driver.findElement(By.id("sysverb_update")).click();
		
		/*Logout*/
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//span[@class='caret']/preceding-sibling::span[text()='System Administrator']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		
		/*tear down*/
		driver.close();
		

		

		
		

		
		
		
		
	
		
		
		
		
		
		
		
		



	}

}
