package com.tc911.commom.core;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Wait {

  /**
   * Checks if the element is present in browser DOM
   */

  private static final int DEFAULT_TIMEOUT = 15;

  private WebDriverWait wait;
  private WebDriver driver;

  public Wait(WebDriver webDriver) {
    this.driver = webDriver;
    this.wait = new WebDriverWait(webDriver, DEFAULT_TIMEOUT);
  }

  /**
   * Checks if the element is present in browser DOM
   */
  public WebElement forElementPresent(By by) {
    return forElementPresent(by, true);
  }

  /**
   * Checks if the element is present in browser DOM
   */
  public WebElement forElementPresent(By by, boolean failOnTimeout) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    } catch (TimeoutException e) {
      if (failOnTimeout) {
    	  e.printStackTrace();
      }

      throw e;
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Checks if the element is present in browser DOM
   */
  public WebElement forElementPresent(By by, int timeout) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return new WebDriverWait(driver, timeout).until(ExpectedConditions
                                                          .presenceOfElementLocated(by));
    } catch (TimeoutException e) {
      e.printStackTrace();
      throw e;
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Checks if the element is clickable in browser
   *
   * @param element The element to be checked
   */
  public WebElement forElementClickable(WebElement element) {
    changeImplicitWait(0, TimeUnit.MILLISECONDS);
    try {
      element.getTagName();
    } catch (WebDriverException e) {
      e.printStackTrace();
    }
    try {
      if (SelectorStack.isContextSet()) {
        SelectorStack.contextRead();
        return wait.until(ExpectedConditions.elementToBeClickable(element));
      } else {
        return forElementClickable(SelectorStack.read());
      }
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public WebElement forElementClickable(WebElement element, int timeout) {
    changeImplicitWait(0, TimeUnit.MILLISECONDS);
    try {
      element.getTagName();
    } catch (WebDriverException e) {
      e.printStackTrace();
    }
    try {
      if (SelectorStack.isContextSet()) {
        SelectorStack.contextRead();
      }
      return new WebDriverWait(driver, timeout).until(ExpectedConditions
                                                          .elementToBeClickable(element));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public WebElement forElementClickable(List<WebElement> elements, int index, int timeout) {
    changeImplicitWait(0, TimeUnit.MILLISECONDS);
    try {
      elements.get(index).getTagName();
    } catch (WebDriverException e) {
      e.printStackTrace();
    }
    try {
      SelectorStack.contextRead();
      return new WebDriverWait(driver, timeout).until(
          ExpectedConditions.elementToBeClickable(elements.get(index)));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Checks if the element is clickable on the browser
   */
  public WebElement forElementClickable(By by) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return wait.until(ExpectedConditions.elementToBeClickable(by));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Checks if the element is clickable on the browser
   */
  public WebElement forElementClickable(By by, int timeout) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return new WebDriverWait(driver, timeout).until(ExpectedConditions
                                                          .elementToBeClickable(by));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Checks if the element is visible in browser
   *
   * @param element The element to be checked
   */
  public WebElement forElementVisible(WebElement element) {
    changeImplicitWait(0, TimeUnit.MILLISECONDS);
    try {
      element.getTagName();
    } catch (WebDriverException e) {
      e.printStackTrace();
    }
    if (SelectorStack.isContextSet()) {
      SelectorStack.contextRead();
      return wait.until(ExpectedConditions.visibilityOf(element));
    } else {
      return forElementVisible(SelectorStack.read());
    }
  }

  public WebElement forElementVisible(WebElement element, int timeout, int polling) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return new WebDriverWait(driver, timeout, polling).until(ExpectedConditions
                                                                   .visibilityOf(element));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public WebElement forElementVisible(WebElement element, int timeout) {
    return forElementVisible(element, timeout, 500);
  }

  /**
   * Checks if the element is visible on the browser
   */
  public WebElement forElementVisible(By by) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public WebElement forElementVisible(By by, int timeout, int polling) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return new WebDriverWait(driver, timeout, polling).until(
          ExpectedConditions.visibilityOfElementLocated(by));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Wait for element to be either invisible or not present on the DOM.
   */
  public boolean forElementNotVisible(By by) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return new WebDriverWait(driver, DEFAULT_TIMEOUT).until(
          ExpectedConditions.invisibilityOfElementLocated(by));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Wait for element to be either invisible or not present on the DOM.
   */
  public boolean forElementNotVisible(WebElement element) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return new WebDriverWait(driver, DEFAULT_TIMEOUT).until(
          CommonExpectedConditions.invisibilityOfElementLocated(element));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Wait for element to be either invisible or not present on the DOM.
   */
  public boolean forElementNotVisible(By by, int timeout, int polling) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return new WebDriverWait(driver, timeout, polling).until(
          ExpectedConditions.invisibilityOfElementLocated(by));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Wait for element to be in viewport Either position top or left is bigger then -1
   */
  public boolean forElementInViewPort(WebElement element) {
    changeImplicitWait(250, TimeUnit.MILLISECONDS);
    try {
      return wait.until(CommonExpectedConditions.elementInViewPort(element));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public boolean forValueToBeNotPresentInElementsAttribute(
      WebElement element, String attribute, String value) {
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      return wait.until(CommonExpectedConditions.valueToBeNotPresentInElementsAttribute(
          element, attribute, value));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  /**
   * Wait for element to not be present in DOM
   */
  public boolean forElementNotPresent(By selector) {
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      return wait.until(CommonExpectedConditions.elementNotPresent(selector));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public boolean forTextNotInElement(WebElement element, String text) {
    try {
      element.getTagName();
    } catch (WebDriverException e) {
      e.printStackTrace();
    }
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      if (SelectorStack.isContextSet()) {
        SelectorStack.contextRead();
        return wait.until(CommonExpectedConditions.textToBeNotPresentInElement(element, text));
      } else {
        return forTextNotInElement(SelectorStack.read(), text);
      }
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public boolean forValueToBePresentInElementsAttribute (
      WebElement element, String attribute, String value) {
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      return wait.until(CommonExpectedConditions.valueToBePresentInElementsAttribute(
          element, attribute, value));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public boolean forTextNotInElement(By by, String text) {
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      return wait.until(CommonExpectedConditions.textToBeNotPresentInElement(by, text));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public boolean forTextInElement(By by, String text) {
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      return wait.until(CommonExpectedConditions.textToBePresentInElement(by, text));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public boolean forTextInElement(By by, int index, String text) {
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      return wait.until(CommonExpectedConditions.textToBePresentInElement(by, index, text));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public boolean forTextInElement(WebElement element, String text) {
    try {
      element.getTagName();
    } catch (WebDriverException e) {
      e.printStackTrace();
    }
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      if (SelectorStack.isContextSet()) {
        SelectorStack.contextRead();
        return wait.until(CommonExpectedConditions.textToBePresentInElement(element, text));
      } else {
        return forTextInElement(SelectorStack.read(), text);
      }
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public boolean forTextInElement(List<WebElement> elements, int index, String text) {
    try {
      elements.get(0).getTagName();
    } catch (WebDriverException e) {
      e.printStackTrace();
    }
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      if (SelectorStack.isContextSet()) {
        SelectorStack.contextRead();
        return wait.until(CommonExpectedConditions.textToBePresentInElement(elements, index, text));
      } else {
        return forTextInElement(SelectorStack.read(), index, text);
      }
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public boolean forAttributeToContain(WebElement element, String attribute, String expectedValue) {
    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      return wait.until(CommonExpectedConditions
                            .valueToBePresentInElementsAttribute(element, attribute,
                                                                 expectedValue));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public void forUrlContains(String text) {
    wait.until(ExpectedConditions.urlContains(text));
  }

  private void restoreDeaultImplicitWait() {
    changeImplicitWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
  }

  private void changeImplicitWait(int value, TimeUnit timeUnit) {
    driver.manage().timeouts().implicitlyWait(value, timeUnit);
  }

  /**
   * Wait for fixed time
   *
   * @param time - in milliseconds
   */
  public void forXMilliseconds(int time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
