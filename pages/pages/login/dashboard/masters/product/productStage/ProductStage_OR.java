package pages.login.dashboard.masters.product.productStage;

import org.openqa.selenium.By;

public class ProductStage_OR {

	public static By productStageMenu = By.xpath("//a[@class ='subMenuItem' and (text()='Product Stage')]");

	public static By productStageGrid = By.xpath("//table[contains(@id,'GridViewProductStage')]");

	public static By productStageField = By.xpath("//input[contains(@name,'TextBoxProductStage')]");

	public static By editableProductStageField = By
			.xpath("//table[contains(@id,'GridViewProduct')]//input[contains(@id,'TextBoxProductStage')]");

	public static By productStageColum = By.xpath("//table[contains(@id,'Product')]//tr//td[2]");

}
