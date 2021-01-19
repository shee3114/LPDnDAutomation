package pages.login.dashboard.masters.product.product;

import org.openqa.selenium.By;

public class Product_OR {

	public static By productMenu = By.xpath("(//a[@class ='subMenuItem' and (text()='Product')])[2]");

	public static By productGrid = By.xpath("//table[contains(@id,'GridViewProduct')]");

	public static By companyField = By.xpath("//td[@class='topalign']//select[contains(@name,'ListCompany')]");

	public static By productGroupField = By
			.xpath("//td[@class='topalign']//select[contains(@name,'ListProductGroup')]");

	public static By productNameField = By
			.xpath("(//td[@class='topalign']//input[contains(@name,'TextBoxProduct')])[1]");

	public static By aliasNameField = By.xpath("(//td[@class='topalign']//input[contains(@name,'AliasName')])[1]");

	public static By editableProductNameField = By
			.xpath("(//table[contains(@id,'GridViewProduct')]//input[contains(@id,'TextBoxProductName')])[1]");

	public static By editableAliasNameField = By
			.xpath("(//table[contains(@id,'GridViewProduct')]//input[contains(@id,'TextBoxAliasName')])[1]");

	public static By productNameColumn = By.xpath("//table[contains(@id,'Product')]//tr//td[3]");

}
