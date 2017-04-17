package maventest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;		
import org.testng.annotations.Test;

import com.tc911.driverhelper.Constant;
import com.tc911.driverhelper.DriverHelper;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterClass;	
public class Example {	
	private WebDriver driver;
	@AfterClass
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}
	@BeforeTest
	public void beforeTest() {	
		driver = DriverHelper.getDriverByName(Constant.Chrome);
	}
	@Test			
	public void testEasy() {	
		driver.get("http://google.com");  
		String title = driver.getTitle();				 
		Assert.assertTrue(title.contains("Google"));
	}
}	
