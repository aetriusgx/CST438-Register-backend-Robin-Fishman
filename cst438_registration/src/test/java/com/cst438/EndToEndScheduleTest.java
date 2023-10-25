package com.cst438;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;

/*
 * This example shows how to use selenium testing using the web driver 
 * with Chrome browser.
 * 
 *  - Buttons, input, and anchor elements are located using XPATH expression.
 *  - onClick( ) method is used with buttons and anchor tags.
 *  - Input fields are located and sendKeys( ) method is used to enter test data.
 *  - Spring Boot JPA is used to initialize, verify and reset the database before
 *      and after testing.
 *      
 *    Make sure that TEST_COURSE_ID is a valid course for TEST_SEMESTER.
 *    
 *    URL is the server on which Node.js is running.
 */

@SpringBootTest
public class EndToEndScheduleTest {

	public static final String CHROME_DRIVER_FILE_LOCATION = "C:/chromedriver_win32/chromedriver.exe";

	public static final String URL = "http://localhost:3000";

	public static final String TEST_USER_EMAIL = "test@csumb.edu";

	public static final int TEST_COURSE_ID = 40442; 

	public static final String TEST_SEMESTER = "2021 Fall";

	public static final int SLEEP_DURATION = 1000; // 1 second.
	
	@Test
	public void addStudent() throws Exception {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		try {
			driver.get(URL);
			Thread.sleep(SLEEP_DURATION);
			
			driver.findElement(By.linkText("Admin")).click();
			Thread.sleep(SLEEP_DURATION);
			
			driver.findElement(By.linkText("Add Student")).click();
			Thread.sleep(SLEEP_DURATION);
			
			WebElement emailInput = driver.findElement(By.name("email"));
			assertNotNull(emailInput);
			
			emailInput.sendKeys("rfishman@csumb.edu");
			Thread.sleep(SLEEP_DURATION);
			
			WebElement nameInput = driver.findElement(By.name("name"));
			assertNotNull(nameInput);
			
			nameInput.sendKeys("Robin");
			Thread.sleep(SLEEP_DURATION);
			
			WebElement statusInput = driver.findElement(By.name("status"));
			assertNotNull(statusInput);
			
			statusInput.sendKeys("good");
			Thread.sleep(SLEEP_DURATION);
			
			WebElement statusCodeInput = driver.findElement(By.name("status_code"));
			assertNotNull(statusCodeInput);
			
			statusCodeInput.sendKeys("0");
			Thread.sleep(SLEEP_DURATION);
			
			List<WebElement> buttons = driver.findElements(By.xpath("//button"));
			buttons.get(0).click(); // submit should be the only button
			Thread.sleep(SLEEP_DURATION);
			
			
			
		} catch (Exception ex) {
			throw ex;
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void editStudent() throws Exception {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		try {
			driver.get(URL);
			Thread.sleep(SLEEP_DURATION);
			
			driver.findElement(By.linkText("Admin")).click();
			Thread.sleep(SLEEP_DURATION);
			
			driver.findElement(By.linkText("Edit Student")).click();
			Thread.sleep(SLEEP_DURATION);
			
			WebElement emailInput = driver.findElement(By.name("email"));
			assertNotNull(emailInput);
			
			emailInput.sendKeys("test@csumb.edu");
			Thread.sleep(SLEEP_DURATION);
			
			WebElement nameInput = driver.findElement(By.name("name"));
			assertNotNull(nameInput);
			
			nameInput.sendKeys("Treston");
			Thread.sleep(SLEEP_DURATION);
			
			WebElement statusInput = driver.findElement(By.name("status"));
			assertNotNull(statusInput);
			
			statusInput.sendKeys("good");
			Thread.sleep(SLEEP_DURATION);
			
			WebElement statusCodeInput = driver.findElement(By.name("status_code"));
			assertNotNull(statusCodeInput);
			
			statusCodeInput.sendKeys("0");
			Thread.sleep(SLEEP_DURATION);
			
			List<WebElement> buttons = driver.findElements(By.xpath("//button"));
			buttons.get(0).click(); // submit should be the only button
			Thread.sleep(SLEEP_DURATION);
			
		} catch (Exception e) {
			
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void deleteStudent() throws Exception {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		try {
			driver.get(URL);
			Thread.sleep(SLEEP_DURATION);
			
			driver.findElement(By.linkText("Admin")).click();
			Thread.sleep(SLEEP_DURATION);
			
			driver.findElement(By.linkText("Delete Student")).click();
			Thread.sleep(SLEEP_DURATION);
			
			WebElement emailInput = driver.findElement(By.name("email"));
			assertNotNull(emailInput);
			
			emailInput.sendKeys("test@csumb.edu");
			Thread.sleep(SLEEP_DURATION);
			
			List<WebElement> buttons = driver.findElements(By.xpath("//button"));
			buttons.get(0).click(); // submit should be the only button
			Thread.sleep(SLEEP_DURATION);
			
		} catch (Exception e) {
			
		} finally {
			driver.quit();
		}
	}
}
