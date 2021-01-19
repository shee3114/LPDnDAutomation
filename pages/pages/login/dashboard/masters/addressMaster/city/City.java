package pages.login.dashboard.masters.addressMaster.city;

import java.util.Objects;

import org.openqa.selenium.By;

import base.BaseComponent;
import framework.reporter.ScreenshotType;
import pages.CommonFunctions;
import pages.Shared_OR;
import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.DistricoConstant;

public class City extends BaseComponent {
	CommonFunctions commonFunctions;

	Dashboard dashboard;

	City city;

	/*
	 * Method to add new entry of the city
	 */
	public void addNewCity(String countryName, String stateName, String cityName) {

		// Create the object for "Common Functions"
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Create the object for "City".
		if (Objects.isNull(city)) {
			city = createObject(DistricoConstant.CITY);
		}

		// Verify the fields of city master.
		city.verifyCityMasterFields();

		// Add data to mandatory fields for creating city
		city.addCity(countryName, stateName, cityName);

		// Verify Notification
		commonFunctions.verifyNotification("City added successfully.");

		// Refresh the page
		refreshPage();
	}

	/*
	 * Method to check fields of city master which are by default enabled or not.
	 */

	public void verifyCityMasterFields() {

		// Verify if 'Country' drop-down is by default enabled or not.
		boolean countryField = isElementEditable(Shared_OR.countryDropDown);
		if (countryField) {
			RESULT.PASS("Successfully verified that 'Country' drop-down is by default enabled.", true,
					ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because 'Country' field is not by default enabled.", true, ScreenshotType.browser);
		}
		// Verify if 'state' drop-down is by default enabled or not.
		boolean stateField = isElementEnabled(Shared_OR.stateDropDown);
		if (!stateField) {
			RESULT.PASS("Successfully checked that 'State' field is by default disabled", true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because 'State' field is by default enabled.", true, ScreenshotType.browser);
		}

	}

	/*
	 * Method to edit the data of the existing city
	 */
	public void editCity(String cityNameToSearch, String cityNameToEdit) {

		// Create the object of "Common Functions"
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Wait till the loading the "City" master
		commonFunctions.waitTillVisbilityOfElement(City_OR.city);

		// Click on Edit button
		commonFunctions.clickOnEditButton(cityNameToSearch, City_OR.cityColumn);

		// Verify if the 'Save' button is available or not.
		boolean flag = isElementExists(Shared_OR.saveButton);
		if (flag) {
			RESULT.PASS("Successfully checked that row is editable.", true, ScreenshotType.browser);

			// Edit the data of the "City" field
			setValue(City_OR.editableCity, cityNameToEdit);

			// Click on save
			click(Shared_OR.saveButton);

			// Verify Notification
			commonFunctions.verifyNotification("City updated successfully.");
		} else {
			RESULT.FAIL("Failed to edit the city because row is not editable.", true, ScreenshotType.browser);
		}
		// Refresh the page
		refreshPage();
	}

	/*
	 * Method to verify if the system is allowed to add the duplicate entry of the
	 * city or not
	 */
	public void verificationForDuplicateCity(String countryName, String stateName, String cityName) {

		// Create the object for "Common Functions"
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Create the object for "City"
		if (Objects.isNull(city)) {
			city = createObject(DistricoConstant.CITY);
		}

		// Add data to mandatory fields for creating city
		city.addCity(countryName, stateName, cityName);

		// Verify Notification
		boolean flag = commonFunctions.verifyNotification("This City already exists !");

		if (flag) {
			RESULT.PASS("Successfully verified that system is not allowed to add duplicate city", true,
					ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because system is allowing to add duplicate city.", true, ScreenshotType.browser);
		}
		// Refresh the page
		refreshPage();
	}

	/*
	 * Method to add the data to the mandatory fields of City form.
	 */
	public void addCity(String countryName, String stateName, String cityName) {
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Wait till the 'City' grid is visible on the page.
		commonFunctions.waitTillVisbilityOfElement(City_OR.cityGrid);

		// Verify if the 'City field' is available or not.
		boolean flag = isElementExists(City_OR.city);
		if (flag) {

			// Select the Country value from drop-down.
			commonFunctions.selectValueFromDropdown(Shared_OR.countryDropDown, countryName);

			// Select the City value from drop-down.
			commonFunctions.selectValueFromDropdown(Shared_OR.stateDropDown, stateName);

			// Enter the data into the city field.
			setValue(City_OR.city, cityName);

			// click on add
			click(Shared_OR.addBtn);

		} else {
			RESULT.FAIL("Failed because 'City' field is not available.", true, ScreenshotType.browser);
		}
	}

	public void deleteCity(String cityToDelete) {
		// Create the object for the "Common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		By deleteButton = getLocator(City_OR.deleteButton, cityToDelete);

		boolean flag = isElementExists(deleteButton);
		if (flag) {

			// Click on delete button.
			commonFunctions.delteItem(deleteButton, cityToDelete, "City");

			// Verify notification.
			commonFunctions.verifyNotification("City removed successfully.");

			// Refresh the page.
			refreshPage();
		} else {
			RESULT.FAIL("Failed because delete button was not available for record. ", true, ScreenshotType.browser);
		}
	}
}
