package Assignments.assignment;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.Set;

public class AutifyTest4 {

	WebDriverWait wait;

	@Test
	public void verifyErrorMessage() {

		// Setup WebDriver
		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver_134.exe");

		// Initialize Chrome options
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized"); // Start with maximized window

		// Initialize WebDriver (Chrome browser)
		WebDriver driver = new ChromeDriver(options);

		try {
			// Open the Autify website
			driver.get("https://nocode.autify.com/");

			//Accept All cookies option
			Thread.sleep(3000);
			WebElement acceptAll = driver.findElement(By.xpath("//button[@data-cky-tag='accept-button']"));
			acceptAll.click();

			wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			WebElement startFreeTrialButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Start Free Trial')]"))
					);
			startFreeTrialButton.click();
			Thread.sleep(5000);

			// Get the handle of the all open window
			Set<String> allWindowHandles = driver.getWindowHandles();

			String childWindowHandle = allWindowHandles.iterator().next(); // Get the handle of the child window

			// Switch to the child window (new tab)
			driver.switchTo().window(childWindowHandle);

			//driver.get("https://app.autify.com/trial");
			Thread.sleep(3000);

			// Wait for the "Sign Up" button to be clickable and click it
			WebElement signUpButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Sign up')]"))
					);
			signUpButton.click();

			// Verify email field's error messages
			WebElement emailError = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']//span[contains(text(), 'Email can')]"))
					);
			// Verify password field's error messages
			WebElement passwordError = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Password cannot be blank')]"))
					);

			Thread.sleep(3000);

			// Print the error messages
			System.out.println("Email Error Message: " + emailError.getText());
			System.out.println("Password Error Message: " + passwordError.getText());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Quit the driver after completion
			driver.quit();
		}
	}
}
