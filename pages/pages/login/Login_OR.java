package pages.login;

import org.openqa.selenium.By;

public class Login_OR {

	// public static By login = By.xpath("//button[@type='submit']");

	// public static By passWord = By.xpath("//input[@name='password']");

	// username, password and login button

	public static By userName = By.xpath("//input[@id='LoginUser_UserName']");

	public static By password = By.xpath("//input[@id='LoginUser_Password']");

	public static By login = By.xpath("//a[@id='LoginUser_LoginButton']");

	public static By logoutButton = By.xpath("//a[contains(@id,'LinkLogOut')]");

}
