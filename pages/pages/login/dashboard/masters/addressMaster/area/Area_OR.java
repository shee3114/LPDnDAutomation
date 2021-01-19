package pages.login.dashboard.masters.addressMaster.area;

import org.openqa.selenium.By;

public class Area_OR {

	public static By areaMenu = By.xpath("(//a[contains(text(),'Area')])[1]");

	public static By areaGrid = By.xpath("//table[contains(@id,'GridViewArea')]");

	public static By areaName = By.xpath("(//input[contains(@id,'TextBoxName')])[1]");

	public static By population = By.xpath("//input[contains(@id,'TextBoxPopulation')]");

	public static By areaShortName = By.xpath("(//input[contains(@id,'TextBoxShortName')])[1]");

	public static By areaColumn = By.xpath("//table[contains(@id,'GridViewAreas')]//tr//td[3]");

	public static By editableAreaName = By
			.xpath("(//table[contains(@id,'GridViewAreas')]//input[contains(@id,'TextBoxName')])[1]");

	public static By editablePopulation = By
			.xpath("//table[contains(@id,'GridViewAreas')]//input[contains(@id,'TextBoxPopulation')]");

	public static By editableAreaShortName = By
			.xpath("(//table[contains(@id,'GridViewAreas')]//input[contains(@id,'TextBoxShortName')])[1]");

}
