package pages.login.dashboard.masters.addressMaster.area;

import org.openqa.selenium.By;

public class Area_OR {

	public static By areaMenu = By.xpath("(//a[contains(text(),'Area')])[1]");

	public static By areaName = By.xpath("(//input[contains(@id,'TextBoxName')])[1]");

	public static By population = By.xpath("//input[contains(@id,'TextBoxPopulation')]");

	public static By areaShortName = By.xpath("(//input[contains(@id,'TextBoxShortName')])[1]");

}
