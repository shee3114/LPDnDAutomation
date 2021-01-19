package pages.login.dashboard.masters.product.productGroup;

import org.openqa.selenium.By;

public class ProductGroup_OR {

	public static By productGroupMenu = By.xpath("//a[@class ='subMenuItem' and (text()='Product Group')]");

	public static By productGroupGrid = By.xpath("//table[contains(@id,'GridViewProductGroup')]");

	public static By typeDropDown = By.xpath("//select[contains(@id,'ListType')]");

	public static By productGroupField = By.xpath("(//input[contains(@class,'textbox')])[1]");

	public static By productGroupColumn = By.xpath("//table[contains(@id,'ProductGroup')]//tr//td[2]");

	public static By editableProductGroupField = By
			.xpath("(//table[contains(@id,'GridViewProduct')]//input[contains(@name,'TextBoxName')])[1]");

}
