package Assignments.assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.Set;

public class AutifyTest5 {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void setup() {

		try {
			System.out.println("Setting up WebDriver...");
			System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver_134.exe");

			// Initialize Chrome options
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized"); // Start with maximized window
			driver = new ChromeDriver(options);
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			System.out.println("WebDriver initialized successfully.");
		} catch (Exception e) {
			System.err.println("Error initializing WebDriver: " + e.getMessage());
			e.printStackTrace();
		}    

	}

	@Test(groups = {"smoke"})
	public void testStartFreeTrialButtonAndSwitchWindow() throws InterruptedException {
		// Open the Autify website
		if (driver == null) {
			throw new IllegalStateException("WebDriver is not initialized.");
		}
		driver.get("https://nocode.autify.com/");


		//Accept All cookies option

		WebElement acceptAll = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-cky-tag='accept-button']")));
		acceptAll.click();

		//wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement startFreeTrialButton = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Start Free Trial')]"))
				);
		startFreeTrialButton.click();

		// Get the handle of the all open window
		Set<String> allWindowHandles = driver.getWindowHandles();

		String childWindowHandle = allWindowHandles.iterator().next(); // Get the handle of the child window

		// Switch to the child window (new tab)
		driver.switchTo().window(childWindowHandle);

		// Click the "Sign Up" button
		WebElement signUpButton = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Sign up')]"))
				);
		signUpButton.click();

		// Verify the error messages
		WebElement emailError = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-group']//span[contains(text(), 'Email can')]"))
				);
		WebElement passwordError = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Password cannot be blank')]"))
				);

		// Assert that error messages are displayed
		Assert.assertTrue(emailError.isDisplayed(), "Email error message should be displayed.");
		Assert.assertTrue(passwordError.isDisplayed(), "Password error message should be displayed.");
	}

	@Test(groups = {"regression"})
	public void testVerifyErrorMessages() throws InterruptedException {
		// Check if the error messages are as expected
		if (driver == null) {
			throw new IllegalStateException("WebDriver is not initialized.");
		}
					
			WebElement emailError = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath
														("//div[@class='form-group']//span[contains(text(), 'Email can')]")));
			WebElement passwordError = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath
                                                        ("//span[contains(text(), 'Password cannot be blank')]")));

			Assert.assertEquals(emailError.getText(), "Email can't be blank", "Email error message does not match expected.");
			Assert.assertEquals(passwordError.getText(), "Password cannot be blank", "Password error message does not match expected.");
	}

	@AfterClass
	public void tearDown() {
		// Close the browser after test completion
		driver.quit();
	}
}
