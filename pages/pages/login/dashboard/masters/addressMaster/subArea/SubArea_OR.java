package pages.login.dashboard.masters.addressMaster.subArea;

import org.openqa.selenium.By;

public class SubArea_OR {
	public static By subAreaName = By.xpath("//input[contains(@name,'TextBoxSub')]");

	public static By subAreaGrid = By.xpath("//table[contains(@id,'GridViewSubArea')]");

	public static By subAreaMenu = By.xpath("//a[contains(text(),'Sub Area')]");

	public static By subAreaColumn = By.xpath("//table[contains(@id,'GridViewSubArea')]//tr//td[4]");

	public static By editableSubAreaName = By
			.xpath("//table[contains(@id,'GridViewSubArea')]//input[contains(@id,'TextBoxSubArea')]");

	public static By deleteButton = By.xpath(
			"//tr[td//a[@title='Click here to delete'] and td/span[contains(text(),'%s')]]//a[@title='Click here to delete']");

}
