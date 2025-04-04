from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.action_chains import ActionChains

import time

# Path to the ChromeDriver (update the path to where you have downloaded ChromeDriver)
chrome_driver_path = "C:\Driver\chromedriver_134.exe"

# Create a WebDriver instance for Chrome
#driver = webdriver.Chrome(executable_path=chrome_driver_path)
service = Service(executable_path=chrome_driver_path)
driver = webdriver.Chrome(service=service)
wait = WebDriverWait(driver, 10)

try:
    # 1. Navigate to the URL
    driver.get("https://nocode.autify.com/")

    # 2. Maximize the window (optional, but helps in some cases)
    driver.maximize_window()

    # 3. Wait for the page to load completely (optional but helps if needed)
    time.sleep(10)  # This is just an example, better to use WebDriverWait in real scenarios

    # 4. Locate the "For Mobile" link using XPath and click it
    mobile_link = driver.find_element(By.XPATH, "//a//div[contains(text(),'For Mobile')]")
    mobile_link.click()

    # 5. Wait for the page to load (optional)
    time.sleep(3)

    # 6. Verify the page URL after clicking the link
    current_url = driver.current_url
    print(f"Current URL: {current_url}")

    # 7. Assertion to check if the URL matches the expected one
    assert current_url == "https://nocode.autify.com/", f"Test Failed! Current URL: {current_url}"
    print("Test Passed! Successfully navigated to the correct URL.")
    # Assert the 'enabled' attribute exists
    mobile_tab_enabled = driver.find_element(By.XPATH, "//a[@id='w-tabs-0-data-w-tab-1' and @aria-selected= 'true']")
    assert mobile_tab_enabled.is_enabled()
    print("Aria-attributes is Enabled for, For Mobile!")

finally:
    # 8. Close the browser
    driver.quit()
