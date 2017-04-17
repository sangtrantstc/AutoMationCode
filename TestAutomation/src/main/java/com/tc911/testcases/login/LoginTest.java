package com.tc911.testcases.login;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.tc911.commom.core.BasePageObject;
import com.tc911.commom.element.Login;

public class LoginTest{
	@Test(groups = "Login_anonCanLoginOnAuthModalFromGlobalNavigation")
	public void anonCanLoginOnAuthModalFromGlobalNavigation() {
		Login login = new Login();
		//login.openLogiTrak();
		//login.typeUsername("ehoff1");
		//login.typePassword("Rmstc911");
		//login.submit();
		//signInLink.clickOnSignIn();
		//Assert.assertTrue(authModal.isSignInOpened());
	}
	@Test
	public void testLoginPassed() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","D:\\Software\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		try {
		driver.get("http://localhost:8080/JavaWeb/login.do");
		driver.findElement(By.name("userId")).sendKeys("off3");
		driver.findElement(By.name("passWord")).sendKeys("off3");
		driver.findElement(By.name("button.submit")).click();
		
        assertTrue(driver.findElement(By.id("expandCase")).isEnabled());
		} catch(Exception e) {
	        assertTrue(driver.findElement(By.id("expandCase")).isEnabled());
		}
        
		finally {
		Thread.sleep(2000);
        
		driver.get("http://localhost:8080/JavaWeb/logout.do");

        driver.close();
		driver.quit();
		System.out.println("Login - Passed Scenario Completed");
		}
	}
	
	@Test
	public void testLoginFailed() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","D:\\Software\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		try {
		driver.get("http://localhost:8080/JavaWeb/login.do");
		driver.findElement(By.name("userId")).sendKeys("off3");
		driver.findElement(By.name("passWord")).sendKeys("off");
		driver.findElement(By.name("button.submit")).click();
		
		String errorMessage = driver.findElement(By.xpath("//*[@id='lblError']/ul/li/span/b")).getAttribute("textContent");
        assertEquals("The Username and/or Password are invalid. Please try again.", errorMessage);
		} catch(Exception e) {
			String errorMessage = driver.findElement(By.xpath("//*[@id='lblError']/ul/li/span/b")).getAttribute("textContent");
	        assertEquals("The Username and/or Password are invalid. Please try again.", errorMessage);
		}
        
		finally {
		Thread.sleep(2000);
        
		driver.get("http://localhost:8080/JavaWeb/logout.do");

        driver.close();
		driver.quit();
		System.out.println("Login - Failed Scenario Completed");
		}
	}
}
