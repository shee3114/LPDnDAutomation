package pages.login.dashboard.masters.addressMaster.country;

import org.openqa.selenium.By;

public class Country_OR {
	public static By countryMenu = By.xpath("//a[contains(text(),'Country')]");

	public static By countryName = By.xpath("//input[contains(@id,'TextBoxCountry')]");

	public static By countryShortName = By.xpath("//input[contains(@id,'TextBoxShortName')]");

	public static By countryShortNameColumnData = By.xpath("//table[contains(@id,'GridViewCountry')]//tr/td[2]");

	public static By countryTable = By.xpath("//table[contains(@id,'ViewCountry')]");

	public static By countryTableRow = By.xpath("//table[contains(@id,'ViewCountry')]//tr");

	public static By shortNameColumn = By.xpath("//table[contains(@id,'ViewCountry')]//tr//td[2]");

	public static By shortNameLocator = By.xpath("//table[contains(@id,'ViewCountry')]//tr//td[contains(text(),'%s')]");

	public static By editButton = By.xpath("(//a[contains(@id,'LinkEdit')])[%s]");

	public static By saveButton = By.xpath("//table//a[contains(@id,'LinkUpdate')]");

	public static By editableCountry = By
			.xpath("//table[contains(@id,'ViewCountry')]//input[contains(@id,'BoxCountry')]");

	public static By editableShortName = By
			.xpath("//table[contains(@id,'ViewCountry')]//input[contains(@id,'ShortName')]");

	public static By totalDeleteButtons = By.xpath("//a[contains(@id,'LinkDelete')]");

	public static By deleteBtn = By.xpath("(//a[contains(@id,'LinkDelete')])[%s]");

}
