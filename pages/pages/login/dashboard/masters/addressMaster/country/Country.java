package pages.login.dashboard.masters.addressMaster.country;

import java.util.Objects;

import org.openqa.selenium.By;

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

		// Create the object for the 'Country'
		if (Objects.isNull(country)) {
			country = createObject(DistricoConstant.COUNTRY);
		}

		boolean flag = country.addCountry(countryName, countryShortName);
		if (flag) {

			// Verify Notification.
			boolean notification = commonFunctions.verifyNotification("Country added successfully.");
			if (notification) {
				RESULT.PASS("New entry of country is added successfully.", true, ScreenshotType.browser);
			}
		} else {
			RESULT.FAIL("Failed to add the data to Mandatory fields for country.", true, ScreenshotType.browser);
		}

		// Refresh the page.
		refreshPage();
	}

	/*
	 * Method to edit data of the existing country
	 */
	public void editCountry(String shortNameToSearch, String countryName, String countryShortName) {

		// Create the object for the "Common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		commonFunctions.waitTillVisbilityOfElement(Country_OR.countryName);

		// Click on Edit button
		commonFunctions.clickOnEditButton(shortNameToSearch, Country_OR.shortNameColumn);

		// For verifying if the save button is enabled or not
		boolean flag = isElementExists(Country_OR.saveButton);
		if (flag) {
			setValue(Country_OR.editableCountry, countryName);
			setValue(Country_OR.editableShortName, countryShortName);

			// Click on save
			click(Country_OR.saveButton);

			// Verify Notification
			boolean notification = commonFunctions.verifyNotification("Country updated successfully.");
			if (notification) {
				RESULT.PASS("Existing entry of country is edited Successfully.", true, ScreenshotType.browser);
			}
		} else {
			RESULT.FAIL("Failed because row is not editable.", true, ScreenshotType.browser);
		}
		// Refresh the page.
		refreshPage();
	}

	/*
	 * Method to add data to mandatory fields of country form.
	 */
	public boolean addCountry(String countryName, String countryShortName) {

		boolean flag = false;

		// Create the object for the "Common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Wait till the 'Country' grid is visible on the page.
		commonFunctions.waitTillVisbilityOfElement(Country_OR.countryGrid);

		// Check if the 'Country Name' field is exist on the page or not.
		boolean countryNameField = isElementExists(Country_OR.countryName);
		if (countryNameField) {
			commonFunctions.setValue(Country_OR.countryName, countryName);

			commonFunctions.setValue(Country_OR.countryShortName, countryShortName);

			// Click on Add Button.
			commonFunctions.click(Shared_OR.addBtn);

			flag = true;
		} else {
			RESULT.FAIL("Failed because 'Contry Name' field is not exist on the page.", true, ScreenshotType.browser);
		}
		return flag;
	}

	/*
	 * Method to delete the existing record of the country.
	 */
	public void delteCountry(String countryToDelete) {
		// Create the object for the "Common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		By deleteButton = getLocator(Shared_OR.deleteButton, countryToDelete);

		boolean flag = isElementExists(deleteButton);
		if (flag) {

			// Click on delete button
			commonFunctions.delteItem(deleteButton, countryToDelete, "Country");

			// Verify notification
			boolean notification = commonFunctions.verifyNotification("Country removed successfully.");
			if (notification) {
				RESULT.PASS("The entry of country is deleted successfully.", true, ScreenshotType.browser);
			}
			// Refresh the page.
			refreshPage();
		} else {
			RESULT.FAIL("Failed because 'delete' button was not available for the record.", true,
					ScreenshotType.browser);
		}
	}

	/*
	 * Generic Method to verify duplicate country.
	 */
	public boolean verificationForduplicateData(String countryName, String countryShortName,
			String expectedNotification) {
		boolean flag = false;

		// Create the object for the "Common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Create object for 'Country' field.
		if (Objects.isNull(country)) {
			country = createObject(DistricoConstant.COUNTRY);
		}
		// Add data to mandatory fields of the country
		boolean addCountry = country.addCountry(countryName, countryShortName);
		if (addCountry) {
			boolean notification = commonFunctions.verifyNotification(expectedNotification);
			if (notification) {
				flag = true;
			}
		} else {
			RESULT.FAIL("Failed to add the data to Mandatory fields for country.", true, ScreenshotType.browser);
		}
		refreshPage();
		return flag;
	}

	/*
	 * Method to verify functionality of adding country with already existing
	 * countryName
	 */
	public void addCountryWithDuplicateCountryName(String countryName, String countryShortName,
			String expectedNotification) {

		// Create object for 'Country' field.
		if (Objects.isNull(country)) {
			country = createObject(DistricoConstant.COUNTRY);
		}

		boolean flag = country.verificationForduplicateData(countryName, countryShortName, expectedNotification);
		if (flag) {
			RESULT.PASS(
					"Successfully verified that system is not allowed to add country with already existing country Name.",
					true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because system is allowing to add country with already existing country Name", true,
					ScreenshotType.browser);
		}
	}

	/*
	 * Method to verify functionality of adding country with already existing
	 * country Short Name
	 */
	public void addCountryWithDuplicateCountryShortName(String countryName, String countryShortName,
			String expectedNotification) {

		// Create object for 'Country' field.
		if (Objects.isNull(country)) {
			country = createObject(DistricoConstant.COUNTRY);
		}

		boolean flag = country.verificationForduplicateData(countryName, countryShortName, expectedNotification);
		if (flag) {
			RESULT.PASS(
					"Successfully verified that system is not allowed to add country with already existing Country Short Name.",
					true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because system is allowing to add country with already existing Country Short Name",
					true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to verify functionality of adding country with already existing
	 * country Name and Country Short Name
	 */
	public void addCountryWithDuplicateCountryNameAndCountryShortName(String countryName, String countryShortName,
			String expectedNotification) {

		// Create object for 'Country' field.
		if (Objects.isNull(country)) {
			country = createObject(DistricoConstant.COUNTRY);
		}

		boolean flag = country.verificationForduplicateData(countryName, countryShortName, expectedNotification);
		if (flag) {
			RESULT.PASS(
					"Successfully verified that system is not allowed to add country with already existing Country Name and short Name.",
					true, ScreenshotType.browser);
		} else {
			RESULT.FAIL(
					"Failed because system is allowing to add country with already existing Country Name and Short Name. ",
					true, ScreenshotType.browser);
		}
	}
}
