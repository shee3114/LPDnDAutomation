package pages.login.dashboard.masters.product.packingMedium;

import org.openqa.selenium.By;

public class PackingMedium_OR {

	public static final By packingMediumMenu = By.xpath("//a[@class ='subMenuItem' and (text()='Packing Medium')]");

	public static final By packingMediumGrid = By.xpath("//table[contains(@id,'GridViewPackingMedium')]");

	public static final By packingMediumColum = By.xpath("//table[contains(@id,'PackingMedium')]//tr//td[1]");

	public static final By packingMediumField = By.xpath("(//input[contains(@name,'PackingMedium')])[1]");

	public static final By shortNameField = By.xpath("(//input[contains(@name,'ShortName')])[1]");

	public static final By lengthField = By.xpath("//input[contains(@name,'Length')]");

	public static final By breadthField = By.xpath("//input[contains(@name,'Breadth')]");

	public static final By heightField = By.xpath("//input[contains(@name,'Height')]");

	public static final By editablePackingMedium = By
			.xpath("(//table[contains(@id,'GridViewPacking')]//input[contains(@id,'TextBoxPackingMedium')])[1]");

	public static final By editableShortName = By
			.xpath("(//table[contains(@id,'GridViewPacking')]//input[contains(@id,'TextBoxShortName')])[1]");

	public static final By editableLength = By
			.xpath("//table[contains(@id,'GridViewPacking')]//input[contains(@id,'TextBoxLength')]");

	public static final By editableBreadth = By
			.xpath("//table[contains(@id,'GridViewPacking')]//input[contains(@id,'TextBoxBreadth')]");

	public static final By editableHeight = By
			.xpath("//table[contains(@id,'GridViewPacking')]//input[contains(@id,'TextBoxHeight')]");

}
