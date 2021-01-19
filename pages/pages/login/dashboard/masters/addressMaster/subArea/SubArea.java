package pages.login.dashboard.masters.addressMaster.subArea;

import java.util.Objects;

import org.openqa.selenium.By;

import base.BaseComponent;
import framework.reporter.ScreenshotType;
import pages.CommonFunctions;
import pages.Shared_OR;
import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.DistricoConstant;

public class SubArea extends BaseComponent {

	Dashboard dashboard;

	SubArea subArea;

	CommonFunctions commonFunctions;

	public void addSubArea(String country, String state, String city, String subAreaName) {

		// Create object of the 'Common Functions'
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Wait till the 'Sub Area' grid is visible on the page.
		commonFunctions.waitTillVisbilityOfElement(SubArea_OR.subAreaGrid);

		boolean flag = isElementExists(SubArea_OR.subAreaName);

		if (flag) {

			// Select Country from drop-down
			commonFunctions.selectValueFromDropdown(Shared_OR.countryDropDown, country);

			// Select State from drop-down
			commonFunctions.selectValueFromDropdown(Shared_OR.stateDropDown, state);

			// Select City from drop-down.
			commonFunctions.selectValueFromDropdown(Shared_OR.cityDropDown, city);

			commonFunctions.setValue(SubArea_OR.subAreaName, subAreaName);

			commonFunctions.click(Shared_OR.addBtn);
		} else {

			RESULT.FAIL("Failed because 'Sub Area' field is not exit on the page.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to check fields of city master which are by default enabled or not.
	 */

	public void verifySubAreaMasterFields() {

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

		// Verify if 'City' drop-down is by default enabled or not
		boolean cityField = isElementEnabled(Shared_OR.cityDropDown);
		if (!cityField) {
			RESULT.PASS("Successfully checked that 'City' field is by default disabled", true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because 'City' field is by default enabled.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to create new entry of Sub Area
	 */
	public void addNewSubArea(String country, String state, String city, String subAreaName) {

		// Create the object of the 'Common Functions'
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Create the object of the 'Sub Area'
		if (Objects.isNull(subArea)) {
			subArea = createObject(DistricoConstant.SUBAREA);
		}

		subArea.verifySubAreaMasterFields();

		subArea.addSubArea(country, state, city, subAreaName);

		// Verify Notification
		commonFunctions.verifyNotification("Sub Area details added successfully.");

		refreshPage();
	}

	/*
	 * Method to edit the details of Sub Area.
	 */
	public void editSubArea(String subAreaToSearch, String subAreaToEdit) {

		// Create object of the CommonFunctios
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Click on the edit Button of the particular row
		commonFunctions.clickOnEditButton(subAreaToSearch, SubArea_OR.subAreaColumn);

		// Verify if the 'save' button is showing or not
		boolean flag = isElementExists(Shared_OR.saveButton);
		if (flag) {

			commonFunctions.setValue(SubArea_OR.editableSubAreaName, subAreaToEdit);

			click(Shared_OR.saveButton);

			// Verify notification
			commonFunctions.verifyNotification("Sub Area updated successfully.");
		} else {
			RESULT.FAIL("Failed to edit the entry of Sub area because row is not editable.", true,
					ScreenshotType.browser);
		}
		// Refresh the page
		refreshPage();

	}

	/*
	 * Method to check if the system is allowed to duplicate entry of Sub area or
	 * not.
	 */
	public void verificationForDuplicateSubArea(String country, String state, String city, String subAreaName) {

		// Create the object of the 'Common Functions'
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Create the object of the 'Sub Area'
		if (Objects.isNull(subArea)) {
			subArea = createObject(DistricoConstant.SUBAREA);
		}
		subArea.addSubArea(country, state, city, subAreaName);

		// Verify Notification
		boolean flag = commonFunctions.verifyNotification("Sub Area has already been added!");
		if (flag) {
			RESULT.PASS("Successfully verified that system is not allowed to add duplicate Sub Area.", true,
					ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because system is allowing to add duplicate Sub Area.", true, ScreenshotType.browser);
		}

		// Refresh the page
		refreshPage();
	}

	/*
	 * Method to delete existing Sub Area
	 */
	public void deleteSubArea(String subAreaToDelete) {

		// Create the object for the "Common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		By deleteButton = getLocator(SubArea_OR.deleteButton, subAreaToDelete);

		boolean flag = isElementExists(deleteButton);
		if (flag) {

			// Click on delete button.
			commonFunctions.delteItem(deleteButton, subAreaToDelete, "Sub Area");

			// Verify notification.
			commonFunctions.verifyNotification("Sub Area removed successfully.");

			// Refresh the page.
			refreshPage();
		} else {
			RESULT.FAIL("Failed because delete button was not available for record. ", true, ScreenshotType.browser);
		}
	}

}
