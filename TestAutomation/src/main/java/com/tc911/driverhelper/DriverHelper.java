package com.tc911.driverhelper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;

public class DriverHelper {
	public static WebDriver getInternetExploreDriver(){
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/src/main/resources/IE/IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		return driver;
	}
	public static WebDriver getChromeDriver(){
		ChromeDriverManager.getInstance().setup();
		DesiredCapabilities cap = new DesiredCapabilities();
		
		//disable save password
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("start-maximized");
	    chromeOptions.addArguments("disable-notifications");
	    chromeOptions.addArguments("process-per-site");
	    chromeOptions.addArguments("dns-prefetch-disable");
	    cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
	    
		WebDriver driver = new ChromeDriver(chromeOptions);
		return driver;
	}
	public static WebDriver getFirefoxDriver(){
		FirefoxDriverManager.getInstance().setup();
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
	public static WebDriver getEdgeDriver(){
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		EdgeDriverManager.getInstance().setup();
		WebDriver driver = new EdgeDriver();
		return driver;
	}
	/**
	 * return WebDriver by name, default ChromeDriver
	 */
	public static WebDriver getDriverByName(String name){
		if(name.equals(Constant.InternetExplorer)){
			return DriverHelper.getInternetExploreDriver();
		} else if(name.equals(Constant.Chrome)){
			return DriverHelper.getChromeDriver();
		} else if(name.equals(Constant.Firefox)){
			return DriverHelper.getFirefoxDriver();
		} else if(name.equals(Constant.Edge)){
			return DriverHelper.getEdgeDriver();
		} else{
			return DriverHelper.getChromeDriver();
		}
	}
}
