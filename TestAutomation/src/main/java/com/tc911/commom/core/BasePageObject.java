package com.tc911.commom.core;

import com.tc911.driverhelper.Constant;
import com.tc911.driverhelper.DriverHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class BasePageObject {

  private static final int TIMEOUT_PAGE_REGISTRATION = 3000;
  public final Wait wait;
  protected WebDriver driver =  DriverHelper.getDriverByName(Constant.Chrome);
  public WebDriverWait waitFor;
  public Actions builder;
  protected int timeOut = 15;
  protected JavascriptAction jsActions;

  public BasePageObject() {
    this.waitFor = new WebDriverWait(driver, timeOut);
    this.builder = new Actions(driver);
    this.wait = new Wait(driver);
    this.jsActions = new JavascriptAction(driver);
    PageFactory.initElements(driver, this);
  }

  public void openLogiTrak(){
	  String url = Constant.url;
	  String port = Constant.port;	  
	  driver.get("http://"+url+":"+port+"/JavaWeb/login.do");
  }

  public static String getTimeStamp() {
    Date time = new Date();
    long timeCurrent = time.getTime();
    return String.valueOf(timeCurrent);
  }

  /**
   * Simple method for checking if element is on page or not. Changing the implicitWait value allows
   * us no need for waiting 30 seconds
   */
  protected boolean isElementOnPage(By by) {
    changeImplicitWait(500, TimeUnit.MILLISECONDS);
    try {
      return driver.findElements(by).size() > 0;
    } finally {
      restoreDefaultImplicitWait();
    }
  }

  /**
   * Simple method for checking if element is on page or not. Changing the implicitWait value allows
   * us no need for waiting 30 seconds
   */
  protected boolean isElementOnPage(WebElement element) {
    changeImplicitWait(500, TimeUnit.MILLISECONDS);
    boolean isElementOnPage = true;
    try {
      // Get location on WebElement is rising exception when element is not present
      element.getLocation();
    } catch (WebDriverException ex) {
      isElementOnPage = false;
    } finally {
      restoreDefaultImplicitWait();
    }
    return isElementOnPage;
  }

  /**
   * Method to check if WebElement is displayed on the page
   *
   * @return true if element is displayed, otherwise return false
   */

  protected boolean isElementDisplayed(WebElement element) {
    try {
      return element.isDisplayed();
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  /**
   * Make sure element is ready to be clicked and click on it The separation of this method has
   * particular reason. It allows global modification of such click usages. This way it is very easy
   * to control what criteria have to be met in order to click on element
   *
   * @param element to be clicked on
   */
  protected void waitAndClick(WebElement element) {
    wait.forElementClickable(element).click();
  }

  /**
   * Simple method for getting number of element on page. Changing the implicitWait value allows us
   * no need for waiting 30 seconds
   */
  protected int getNumOfElementOnPage(By cssSelectorBy) {
    changeImplicitWait(500, TimeUnit.MILLISECONDS);
    int numElementOnPage;
    try {
      numElementOnPage = driver.findElements(cssSelectorBy).size();
    } catch (WebDriverException ex) {
      numElementOnPage = 0;
    } finally {
      restoreDefaultImplicitWait();
    }
    return numElementOnPage;
  }

  protected boolean isElementInContext(String cssSelector, WebElement element) {
    changeImplicitWait(500, TimeUnit.MILLISECONDS);
    boolean isElementInElement = true;
    try {
      if (element.findElements(By.cssSelector(cssSelector)).size() < 1) {
        isElementInElement = false;
      }
    } catch (WebDriverException ex) {
      isElementInElement = false;
    } finally {
      restoreDefaultImplicitWait();
    }
    return isElementInElement;
  }

  protected void scrollTo(WebElement element) {
    jsActions.scrollElementIntoViewPort(element);
    wait.forElementClickable(element, 5);
  }

  protected void scrollAndClick(WebElement element) {
    jsActions.scrollElementIntoViewPort(element);
    wait.forElementClickable(element, 5);
    element.click();
  }

  protected void scrollAndClick(List<WebElement> elements, int index) {
    jsActions.scrollElementIntoViewPort(elements.get(index));
    wait.forElementClickable(elements, index, 5);
    elements.get(index).click();
  }

  protected void scrollAndClick(WebElement element, int offset) {
    jsActions.scrollToElement(element, offset);
    element.click();
  }

  public boolean isStringInURL(String givenString) {
    String currentURL = driver.getCurrentUrl();
    if (currentURL.toLowerCase().contains(givenString.toLowerCase())) {
      return true;
    } else {
      return false;
    }
  }

  public void verifyUrlContains(final String givenString, int timeOut) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      new WebDriverWait(driver, timeOut).until(
          (ExpectedCondition<Boolean>) d -> d.getCurrentUrl().toLowerCase()
              .contains(givenString.toLowerCase()));
    } finally {
      restoreDefaultImplicitWait();
    }
  }

  public String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  public void refreshPage() {
    try {
      driver.navigate().refresh();
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
  }

  public void waitForWindow(String windowName, String comment) {
    Object[] windows = driver.getWindowHandles().toArray();
    int delay = 500;
    int sumDelay = 500;
    while (windows.length == 1) {
      try {
        Thread.sleep(delay);
        windows = driver.getWindowHandles().toArray();
        sumDelay += 500;
      } catch (InterruptedException e) {
    	  e.printStackTrace();
      }
      if (sumDelay > 5000) {
        break;
      }
    }
  }

  // You can get access to hidden elements by changing class
  public void unhideElementByClassChange(String elementName, String classWithoutHidden,
                                         int... optionalIndex) {
    int numElem = optionalIndex.length == 0 ? 0 : optionalIndex[0];
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("document.getElementsByName('" + elementName + "')[" + numElem
                      + "].setAttribute('class', '" + classWithoutHidden + "');");
  }

  public void waitForElementNotVisibleByElement(WebElement element) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      waitFor.until(CommonExpectedConditions.invisibilityOfElementLocated(element));
    } finally {
      restoreDefaultImplicitWait();
    }
  }

  public void waitForElementNotVisibleByElement(WebElement element, long timeout) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      new WebDriverWait(driver, timeout)
          .until(CommonExpectedConditions.invisibilityOfElementLocated(element));
    } finally {
      restoreDefaultImplicitWait();
    }
  }

  public void waitForValueToBePresentInElementsAttributeByCss(String selector, String attribute,
                                                              String value) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      waitFor.until(CommonExpectedConditions.valueToBePresentInElementsAttribute(
          By.cssSelector(selector), attribute, value));
    } finally {
      restoreDefaultImplicitWait();
    }
  }

  public void waitForValueToBePresentInElementsCssByCss(String selector, String cssProperty,
                                                        String expectedValue) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      waitFor.until(CommonExpectedConditions.cssValuePresentForElement(By.cssSelector(selector),
                                                                       cssProperty, expectedValue));
    } finally {
      restoreDefaultImplicitWait();
    }
  }

  public void waitForValueToBePresentInElementsAttributeByElement(WebElement element,
                                                                  String attribute, String value) {
    waitFor.until(CommonExpectedConditions.valueToBePresentInElementsAttribute(element, attribute,
                                                                               value));
  }

  public void waitForStringInURL(String givenString) {
    waitFor.until(CommonExpectedConditions.givenStringtoBePresentInURL(givenString));
  }

  public String getRandomDigits(int length) {
    String timeStamp = getTimeStamp();
    int timeStampLenght = timeStamp.length();
    int timeStampCut = timeStampLenght - length;
    return timeStamp.substring(timeStampCut);
  }

  /**
   * Wait for new window present
   */
  public void waitForNewWindow() {
    waitFor.until(CommonExpectedConditions.newWindowPresent());
  }

  protected void changeImplicitWait(int value, TimeUnit timeUnit) {
    driver.manage().timeouts().implicitlyWait(value, timeUnit);
  }

  protected void restoreDefaultImplicitWait() {
    changeImplicitWait(timeOut, TimeUnit.SECONDS);
  }

  public void verifyUrlInNewWindow(String url) {
    waitForWindow("", "");
    Object[] windows = driver.getWindowHandles().toArray();
    driver.switchTo().window(windows[1].toString());
    waitForStringInURL(url);
    driver.close();
    driver.switchTo().window(windows[0].toString());
  }

  public String switchToNewBrowserTab() {
    List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(tabs.size() - 1));

    return driver.getCurrentUrl();
  }

  private List<String> getTabUrls() {
    String currentTab = driver.getWindowHandle();
    List<String> result = new ArrayList<>();
    for (String windowHandler : driver.getWindowHandles()) {
      driver.switchTo().window(windowHandler);
      result.add(driver.getCurrentUrl());
    }

    driver.switchTo().window(currentTab);
    return result;
  }

  public boolean tabContainsUrl(String url) {
    return getTabUrls().contains(url);
  }

  public int getElementBottomPositionByCssSelector(String elementName) {
    WebElement element = driver.findElement(By.cssSelector(elementName));

    return element.getLocation().getY() + element.getSize().getHeight();
  }

  public int getElementTopPositionByCssSelector(String elementName) {
    WebElement element = driver.findElement(By.cssSelector(elementName));

    return element.getLocation().getY();
  }

}
