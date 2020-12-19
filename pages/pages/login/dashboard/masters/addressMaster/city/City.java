package pages.login.dashboard.masters.addressMaster.city;

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

		// Create the object for "City"
		if (Objects.isNull(city)) {
			city = createObject(DistricoConstant.CITY);
		}

		// Add data to mandatory fields for creating city
		city.addCity(countryName, stateName, cityName);

		// Verify Notification
		commonFunctions.verifyNotification("City added successfully.");

	}

	/*
	 * Method to edit the data of the existing city
	 */
	public void editCity(String cityNameToSearch, String cityNameToEdit) {

		// Create the object for "City"
		if (Objects.isNull(city)) {
			city = createObject(DistricoConstant.CITY);
		}

		// Create the object of "Common Functions"
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Wait till the loading the "City" master
		commonFunctions.waitTillVisbilityOfElement(City_OR.city);

		// Click on Edit button
		city.clickOnEditButton(cityNameToSearch);

		// Verify if the 'Save' button is available or not.
		boolean flag = isElementExists(Shared_OR.saveButton);
		if (flag) {
			RESULT.PASS("Successfully checked that row is editable.", true, ScreenshotType.browser);

			// Edit the data of the "City" field
			setValue(City_OR.city, cityNameToEdit);

			// Click on save
			click(Shared_OR.saveButton);

			// Verify Notification
			commonFunctions.verifyNotification("City updated successfully.");
		} else {
			RESULT.FAIL("Failed to edit the city because row is not editable.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to click on the edit button of the selected row.
	 */
	public void clickOnEditButton(String cityNameToSearch) {
		// Get the list of the of 'Short Name' column list
		List<WebElement> cityNameColumnData = getList(City_OR.cityColumn);
		for (int i = 0; i < cityNameColumnData.size() - 1; i++) {
			String data = cityNameColumnData.get(i).getText();
			String dataWithoutSpace = data.trim();
			if (dataWithoutSpace.equals(cityNameToSearch)) {
				i = i + 1;
				String number = String.valueOf(i);
				By cityEditBtn = getLocator(City_OR.cityEditBtn, number);
				click(cityEditBtn);
				break;
			}
		}
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

	}

	/*
	 * Method to add the data to the mandatory fields of City form.
	 */
	public void addCity(String countryName, String stateName, String cityName) {
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Wait till the loading the "City" master
		commonFunctions.waitTillVisbilityOfElement(City_OR.city);

		// Verify if the 'City field' is available or not.
		boolean flag = isElementExists(City_OR.city);
		if (flag) {

			// Select Country ad State from drop-down.
			commonFunctions.selectValueFromDropdown(Shared_OR.countryDropDown, countryName);
			commonFunctions.selectValueFromDropdown(Shared_OR.stateDropDown, stateName);

			// Enter the data into the city field.
			setValue(City_OR.city, cityName);

			// click on add
			click(Shared_OR.addBtn);

		} else {
			RESULT.FAIL("Failed because 'City' field is not available.", true, ScreenshotType.browser);
		}

	}
}
