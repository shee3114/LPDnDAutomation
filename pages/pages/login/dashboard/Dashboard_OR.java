package pages.login.dashboard;

import org.openqa.selenium.By;

public class Dashboard_OR {
	// All modules link locators

	// Locators for the Customer Menu
	public static By customerMenu = By.xpath(" //a[@href='/customers']");

	// Locators for the Products Menu
	public static By productMenu = By.xpath("//a[@href='/products']");

	// Locators for the Subscription Menu
	public static By subscriptionMenu = By.xpath("//a[@href='/subscriptions']");

	// Locators for the Transactions Menu
	public static By transactionMenu = By.xpath("//a[@href='/transactions']");

	// Locators for the Invoice Menu
	public static By invoiceMenu = By.xpath("//a[@href='/invoices']");

}
