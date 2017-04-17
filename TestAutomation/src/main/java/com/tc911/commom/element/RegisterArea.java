package com.tc911.commom.element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tc911.commom.core.BasePageObject;

public class RegisterArea extends BasePageObject {

	  @FindBy(css = ".auth.desktop.signin-page")
	  private WebElement authModal;
	  @FindBy(css = "#signupEmail")
	  private WebElement signupEmail;
	  @FindBy(css = "#signupUsername")
	  private WebElement signupUsername;
	  @FindBy(css = "#signupPassword")
	  private WebElement signupPassword;
	  @FindBy(css = "#signupBirthDate")
	  private WebElement signupBirthdate;
	  @FindBy(css = ".birth-month")
	  private WebElement signupBirthMonth;
	  @FindBy(css = ".birth-day")
	  private WebElement signupBirthDay;
	  @FindBy(css = ".birth-year")
	  private WebElement signupBirthYear;
	  @FindBy(css = "#signupSubmit")
	  private WebElement signupSubmitButton;
	  @FindBy(css = ".wikia-nav__avatar")
	  private WebElement avatar;
	  @FindBy(css = "#signupForm div:nth-child(2) small")
	  private WebElement usernameError;
	  @FindBy(xpath = "//*[@id=\"signupForm\"]/div[3]/small")
	  private WebElement passwordError;
	  @FindBy(css = "#signupForm > small.error")
	  private WebElement genericError;
	  @FindBy(css = " header.auth-header")
	  private WebElement registerHeader;

	  private final String mainWindowHandle;

	  public RegisterArea(boolean waitForNewWindow) {
	    super();
	    if (waitForNewWindow) {
	      waitForNewWindow();
	      this.mainWindowHandle = driver.getWindowHandle();
	    } else {
	      this.mainWindowHandle = null;
	    }
	  }

	  public void switchToAuthModalHandle() {
	    for (String winHandle : driver.getWindowHandles()) {
	      driver.switchTo().window(winHandle);
	    }
	  }

	  public void switchToMainWindowHandle() {
	    driver.switchTo().window(this.mainWindowHandle);
	  }

	  public boolean isOpened() {
	    switchToAuthModalHandle();
	    boolean isOpenedResult = authModal.isDisplayed();
	    switchToMainWindowHandle();
	    return isOpenedResult;
	  }

	  public RegisterArea typeEmailAddress(String email) {
	    wait.forElementVisible(signupEmail);
	    signupEmail.sendKeys(email);
	    return this;
	  }

	  public RegisterArea typeUsername(String username) {
	    wait.forElementVisible(signupUsername);
	    signupUsername.sendKeys(username);
	    return this;
	  }

	  public RegisterArea typePassword(String password) {
	    wait.forElementVisible(signupPassword);
	    signupPassword.sendKeys(password);
	    return this;
	  }

	  public RegisterArea typeBirthdate(String month, String day, String year) {
	    wait.forElementVisible(signupBirthdate);
	    signupBirthdate.click();

	    wait.forElementVisible(signupBirthMonth);
	    signupBirthMonth.click();
	    signupBirthMonth.sendKeys(month);

	    wait.forElementVisible(signupBirthDay);
	    signupBirthDay.click();
	    signupBirthDay.sendKeys(day);

	    wait.forElementVisible(signupBirthYear);
	    signupBirthYear.click();
	    signupBirthYear.sendKeys(year);

	    return this;
	  }
	  public void clickSignUpSubmitButton() {
	    wait.forElementVisible(signupSubmitButton);
	    signupSubmitButton.click();
	  }

	}
