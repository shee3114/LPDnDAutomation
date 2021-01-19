package pages.login.dashboard.masters.addressMaster.pinCode;

import org.openqa.selenium.By;

public class PinCode_OR {

	public static By pinCodeMenu = By.xpath("//a[contains(text(),'Pin Code')]");

	public static By pinCodeGrid = By.xpath("//div[contains(@id,'UpdatePanelPinCode')]");

	public static By pinCodePopUp = By.xpath("//div[contains(@id,'PanelCreate')]");

	public static By countryDropDown = By.xpath("(//td//select[contains(@id,'ListCountry')])[2]");

	public static By stateDropDown = By.xpath("(//td//select[contains(@id,'ListState')])[2]");

	public static By cityDropDown = By.xpath("(//td//select[contains(@id,'ListCity')])[2]");

	public static By pinCode = By.xpath("//input[contains(@id,'TextBoxPinCode')]");

	public static By editablePinCode = By
			.xpath("//table[contains(@id,'GridPin')]//input[contains(@id,'TextBoxPinCode')]");

	public static By pinCodeColumn = By.xpath("//table[contains(@id,'GridPinCode')]//tr//td[4]");

}
