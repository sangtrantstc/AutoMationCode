package com.tc911.commom.element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tc911.commom.core.BasePageObject;

public class Login extends BasePageObject{
	@FindBy(css = ".auth.desktop.signin-page")
	private WebElement authModal;
	@FindBy(css = "#userId")
	private WebElement userId;
	@FindBy(css = "#passWord")
	private WebElement passWord;
	@FindBy(css = "#action")
	private WebElement action ;
	@FindBy(css = "#treeView")
	private WebElement treeView;
	@FindBy(css = "#browser")
	private WebElement browser;
	@FindBy(css = "#operatingSystem")
	private WebElement operatingSystem ;
	@FindBy(css = "#loginType")
	private WebElement loginType;
	
	public Login typeUsername(String username) {
		wait.forElementVisible(userId);
		userId.sendKeys(username);
		return this;
	}
	public Login typePassword(String password) {
		wait.forElementVisible(passWord);
		passWord.sendKeys(password);
		return this;
	}
}
