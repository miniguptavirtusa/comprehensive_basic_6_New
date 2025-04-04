package Assignments.assignment;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class VerifyChildWindowTitle1 {

	@Test
	public void verifyAutifyTitle() {

		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver_134.exe");

		// Initialize Chrome options (Optional, e.g., for headless mode or other configurations)
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized"); // Start with maximized window

		// Initialize WebDriver (Chrome browser)
		WebDriver driver = new ChromeDriver(options);

		try {
			// Open the Autify website
			driver.get("https://nocode.autify.com/");

			// Wait for the "Start Free Trial" button to be clickable and then click it
			//Accept All cookies option
			Thread.sleep(3000);
			WebElement acceptAll = driver.findElement(By.xpath("//button[@data-cky-tag='accept-button']"));
			acceptAll.click();
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement startFreeTrialButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Start Free Trial')]"))
					);
			startFreeTrialButton.click();
			
			Thread.sleep(3000);
			driver.get("https://app.autify.com/trial");

			// Get the list of the all window tabs
			Set<String> allWindowHandles = driver.getWindowHandles();
			String childWindowHandle = allWindowHandles.iterator().next(); // Get the handle of the child window

			// Switch to the child window (new tab)
			driver.switchTo().window(childWindowHandle);

			// Verify the title of the child window
			String childWindowTitle = driver.getTitle();
			System.out.println("Title of the child window: " + childWindowTitle);

			// Close the child window
			driver.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Quit the driver after completion
			driver.quit();
		}
	}
}
