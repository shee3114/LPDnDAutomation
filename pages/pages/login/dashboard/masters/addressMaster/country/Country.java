package pages.login.dashboard.masters.addressMaster.country;

import java.util.List;
import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BaseComponent;
import framework.reporter.ScreenshotType;
import pages.CommonFunctions;
import pages.Shared_OR;
import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.DistricoConstant;

public class Country extends BaseComponent {
	CommonFunctions commonFunctions;

	Dashboard dashboard;

	Country country;

	/*
	 * Method to add new entry of country
	 */
	public void addNewCountry(String countryName, String countryShortName) {

		// Create the object for the "Common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		if (Objects.isNull(country)) {
			country = createObject("Country");
		}

		country.addCountry(countryName, countryShortName);

		// Verify Notification.
		commonFunctions.verifyNotification("Country added successfully.");
	}

	/*
	 * Method to edit data of the existing country
	 */

	public void editCountry(String shortNameToSearch, String countryName, String countryShortName) {

		// Create the object for the "Common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		if (Objects.isNull(country)) {
			country = createObject("Country");
		}

		// Click on Edit button
		country.clickOnEditButton(shortNameToSearch);

		// For verifying if the save button is enabled or not
		boolean flag = isElementExists(Country_OR.saveButton);
		if (flag) {
			setValue(Country_OR.editableCountry, countryName);
			setValue(Country_OR.editableShortName, countryShortName);

			// Click on save
			click(Country_OR.saveButton);

			// Verify Notification
			commonFunctions.verifyNotification("Country updated successfully.");
		} else {
			RESULT.FAIL("Failed because row is not editable.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to click on edit button of selected row
	 */
	public void clickOnEditButton(String shortName) {

		// Get the list of the of 'Short Name' column list
		List<WebElement> shortNamecolumnData = getList(Country_OR.shortNameColumn);
		for (int i = 0; i < shortNamecolumnData.size() - 1; i++) {
			String data = shortNamecolumnData.get(i).getText();
			String dataWithoutSpace = data.trim();
			if (dataWithoutSpace.equals(shortName)) {
				i = i + 1;
				String number = String.valueOf(i);
				By countryEditBtn = getLocator(Country_OR.editButton, number);
				click(countryEditBtn);
				break;
			}
		}
	}

	public void clickOnDeleteButton(String shortName) {
		// Get the list of the 'Total Delete Buttons'.
		List<WebElement> totalDeleteButtons = getList(Country_OR.totalDeleteButtons);

		// Get the list of the of 'Short Name' column list
		List<WebElement> shortNamecolumnData = getList(Country_OR.shortNameColumn);

		for (int i = 0; i < totalDeleteButtons.size() - 1; i++) {
			String data = shortNamecolumnData.get(i).getText();
			String dataWithoutSpace = data.trim();
			if (dataWithoutSpace.equals(shortName)) {
				i = i + 1;
				String number = String.valueOf(i);
				By countryDeleteBtn = getLocator(Country_OR.deleteBtn, number);
				click(countryDeleteBtn);
				break;
			}
		}
	}

	/*
	 * Method to add data to mandatory fields of country form.
	 */
	public void addCountry(String countryName, String countryShortName) {

		// Create the object for the "Common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Add Data to "Country Name" field
		commonFunctions.waitTillVisbilityOfElement(Country_OR.countryName);

		boolean flag = isElementExists(Country_OR.countryName);
		if (flag) {
			commonFunctions.setValue(Country_OR.countryName, countryName);

			commonFunctions.setValue(Country_OR.countryShortName, countryShortName);

			// Click on Add Button.
			commonFunctions.click(Shared_OR.addBtn);
		} else {
			RESULT.FAIL("Failed because 'Contry Name' field is not exist on the page.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to verify if the system is allowed to add the duplicate entry of the
	 * country or not
	 */

	public void verificationForDuplicateCountry(String countryName, String countryShortName) {

		// Create the object for the "Common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		if (Objects.isNull(country)) {
			country = createObject("Country");
		}

		// Add data to "Country Name" field.
		country.addCountry(countryName, countryShortName);

		// Verify Notification
		boolean flag = commonFunctions.verifyNotification("Country name and Short name already exists !");

		if (flag) {
			RESULT.PASS("Successfully verified that system is not allowed to add duplicate country.", true,
					ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because system is allowing to add duplicate country.", true, ScreenshotType.browser);
		}
	}
}
