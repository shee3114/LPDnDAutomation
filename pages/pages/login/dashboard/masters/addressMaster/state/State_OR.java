package pages.login.dashboard.masters.addressMaster.state;

import org.openqa.selenium.By;

public class State_OR {
	public static By stateMenu = By.xpath("//a[contains(text(),'State')]");

	public static By stateName = By.xpath("//input[contains(@name,'BoxState')]");

	public static By stateShortName = By.xpath("//input[contains(@name,'BoxShortName')]");

	public static By gstCode = By.xpath("//input[contains(@name,'BoxGSTCode')]");

	public static By shortNameColumn = By.xpath("//table[contains(@id,'ViewState')]//tr//td[3]");

	public static By editButton = By.xpath("(//a[contains(@id,'LinkEdit')])[%s]");

	// public static By saveButton =
	// By.xpath("//table//a[contains(@id,'LinkUpdate')]");

	public static By editableStateName = By
			.xpath("//table[contains(@id,'ViewState')]//input[contains(@id,'TextBoxState')]");

	public static By editableShortName = By
			.xpath("//table[contains(@id,'ViewState')]//input[contains(@id,'ShortName')]");

	public static By editableGstCode = By.xpath("//table[contains(@id,'ViewState')]//input[contains(@id,'GstCode')]");
}
