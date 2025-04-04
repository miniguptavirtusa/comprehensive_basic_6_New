import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support import expected_conditions as EC

# Path to the ChromeDriver (update the path accordingly)
chrome_driver_path = "C:\\Driver\\chromedriver_134.exe"


@pytest.fixture
def driver():
    # Initialize the Chrome WebDriver
    service = Service(executable_path=chrome_driver_path)
    driver = webdriver.Chrome(service=service)
    driver.maximize_window()
    yield driver  # Yield the driver to the test


def test_navigate_to_web_page(driver):
    # Step 1: Navigate to the Autify website
    driver.get("https://nocode.autify.com/")

    # Step 2: Locate and click the "For Web" link using XPath
    web_link = WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.XPATH, "//a//div[contains(text(),'For Web')]"))
    )
    web_link.click()

    # Step 3: Wait for the page to load and validate the URL
    WebDriverWait(driver, 10).until(EC.url_to_be("https://nocode.autify.com/"))

    # Step 4: Get the current URL after clicking the link
    current_url = driver.current_url
    print(f"Current URL: {current_url}")

    # Step 5: Assert that the URL is as expected
    assert current_url == "https://nocode.autify.com/", f"Test Failed! Expected URL 'https://nocode.autify.com/' but got {current_url}"

    # If the assertion passes, the test is successful
    print("Test Passed! Successfully navigated to the correct URL.")

    # Assert the 'enabled' attribute exists
    web_tab_enabled = driver.find_element(By.XPATH, "//a[@id='w-tabs-0-data-w-tab-0' and @aria-selected= 'true']")
    assert web_tab_enabled.is_enabled()
    print("Aria-attributes is Enabled for, For Web!")

    # 5. Close the browser
    driver.quit()