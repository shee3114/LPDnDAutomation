package pages.login.dashboard.masters.addressMaster.city;

import org.openqa.selenium.By;

public class City_OR {
	public static By cityMenu = By.xpath("//a[contains(text(),'City')]");

	public static By cityGrid = By.xpath("//table[contains(@id,'GridCity')]");

	public static By city = By.xpath("//input[contains(@name,'BoxCity')]");

	public static By cityColumn = By.xpath("//table[contains(@id,'GridCity')]//tr//td[3]");

	public static By cityEditBtn = By.xpath("(//a[contains(@id,'LinkEdit')])[%s]");

	public static By editableCity = By.xpath("//table[contains(@id,'GridCity')]//input[contains(@name,'BoxCity')]");

	public static By deleteButton = By.xpath(
			"//tr[td//a[@title='Click here to delete'] and td/span[contains(text(),'%s')]]//a[@title='Click here to delete']");
}
