package week2.day5;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Project_WK2 {


	public static void main(String[] args) throws Exception {

		//Launch Browser

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);

		// Launch Application and Maximise window
		driver.get("https://www.myntra.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Thread.sleep(3000);
		Actions action = new Actions(driver);
		
		//Mouse Hover on Women menu item
		WebElement target = driver.findElement(By.xpath("//a[@data-index=1 and text()='Women']"));
		action.moveToElement(target).perform();
		driver.findElement(By.linkText("Jackets & Coats")).click();

		//Getting Total count on top
		String count = driver.findElement(By.className("title-count"))
				.getText().replaceAll("\\D", "");
		int totalCount = Integer.parseInt(count);

		//Getting Jackets count
		String jackets = driver.findElement(By.xpath("(//span[text()='Categories']/following::label/span[@class='categories-num'])[1]"))
				.getText().replaceAll("\\D", "");
		int jacketsCount = Integer.parseInt(jackets);

		//Getting Coats count
		String coats = driver.findElement(By.xpath("(//span[text()='Categories']/following::label/span[@class='categories-num'])[2]"))
				.getText().replaceAll("\\D", "");
		int coatsCount = Integer.parseInt(coats);

		//Total count and categories count verification
		int jacketsAndCoats = jacketsCount+coatsCount;

		if(totalCount == jacketsAndCoats) {
			System.out.println("Total counts matches with sum of category");
		}else {
			System.out.println("Count Mismatches");
		}


		// Selecting Coats checkbox and clicking on + more options
		driver.findElement(By.xpath("//span[text()='Categories']/following::label/input[@value='Coats']/following::div")).click();
		driver.findElement(By.xpath("//span[text()='Brand']/following::div[@class='brand-more']")).click();

		// Entering Brand name Mango and closing the filter popup
		driver.findElement(By.className("FilterDirectory-searchInput")).sendKeys("mango");
		driver.findElement(By.xpath("(//span[@class='FilterDirectory-count']/following::div)[1]")).click();
		driver.findElement(By.xpath("//span[contains(@class,'FilterDirectory-close')]")).click();

		Thread.sleep(3000);

		//Verification for Total count on top and total items displayed on current page
		List<WebElement> brandNames = driver.findElements(By.xpath("//h3[text()='MANGO']"));

		String filteredCount = driver.findElement(By.className("title-count"))
				.getText().replaceAll("\\D", "");
		int brandCount = Integer.parseInt(filteredCount);

		if(brandNames.size() == brandCount) {
			System.out.println("Total count and displayed items matches");
		}else {
			System.out.println("Total count and displayed items not matches");
		}


		//Verification for all items are displayed with brand name "MANGO"
		for (WebElement brandname : brandNames) {

			if(brandname.getText().equals("MANGO")) {

			}else {
				System.out.println("Some brand names are not equal to given name 'MANGO' -- "+brandname.getText());
				break;
			}
		}

		System.out.println("All "+brandNames.size()+ " brand names are displayed as Mango");


		// Sorting action for better discount
		driver.findElement(By.className("sort-sortBy")).click();
		driver.findElement(By.xpath("//label[text()='Better Discount']")).click();

		Thread.sleep(3000);

		//Getting first price of the item displayed
		String firstItem = driver.findElement(By.xpath("(//span[@class='product-discountedPrice'])[1]"))
				.getText().replaceAll("\\D", "");
		System.out.println("Price of the first discounted item: "+ Integer.parseInt(firstItem));

		Thread.sleep(3000);

		//Adding the first item to wishlist
		action.moveToElement(driver.findElement(By.xpath("(//h3[text()='MANGO'])[1]"))).perform();
		driver.findElement(By.xpath("(//span[text()='wishlist now'])[1]")).click();


		//tear down
		driver.close();

	}

}
