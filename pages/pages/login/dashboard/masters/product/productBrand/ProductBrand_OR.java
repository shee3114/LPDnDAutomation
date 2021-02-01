package pages.login.dashboard.masters.product.productBrand;

import org.openqa.selenium.By;

public class ProductBrand_OR {

	public static final By productBrandMenu = By.xpath("//a[@class ='subMenuItem' and (text()='Brand')]");

	public static final By brandName = By.xpath("(//input[contains(@id,'TextBox')])[1]");

	public static final By shortName = By.xpath("//input[contains(@id,'TextBoxShortName')]");

	public static final By brandGrid = By.xpath("//table[contains(@id,'GridViewBrands')]");

	public static final By editableBrandName = By
			.xpath("(//table[contains(@id,'GridViewBrands')]//input[contains(@id,'TextBox')])[1]");

	public static final By editableBrandShortName = By
			.xpath("//table[contains(@id,'GridViewBrands')]//input[contains(@id,'TextBoxShortName')]");

	public static final By brandNameColumn = By.xpath("//table[contains(@id,'Brand')]//tr//td[1]");
}
