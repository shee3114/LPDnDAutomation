package pages.login.dashboard.masters.addressMaster.pinCode;

import java.util.Objects;

import org.openqa.selenium.By;

import base.BaseComponent;
import framework.reporter.ScreenshotType;
import pages.CommonFunctions;
import pages.Shared_OR;
import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.DistricoConstant;
import pages.login.dashboard.masters.addressMaster.country.Country_OR;
import pages.login.dashboard.masters.addressMaster.subArea.SubArea_OR;

public class PinCode extends BaseComponent {

	Dashboard dashboard;

	CommonFunctions commonFunctions;

	PinCode pinCode;

	/*
	 * Method to add data to 'Pin Code' popUp.
	 */
	public boolean addPinCode(String country, String state, String city, String pinCodeData) {

		boolean flag = false;

		// Create object of the 'Common Functions'
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Wait till the visibility of the 'Pin Code' grid
		commonFunctions.waitTillVisbilityOfElement(PinCode_OR.pinCodeGrid);

		// Wait till the visibility of the 'Pin Code' pop-up
		commonFunctions.waitTillVisbilityOfElement(PinCode_OR.pinCodePopUp);

		boolean pinCodePopUp = isElementExists(PinCode_OR.pinCodePopUp);
		if (pinCodePopUp) {

			// Enter the data to the fields showing in 'Pin Code' pop-up
			commonFunctions.selectValueFromDropdown(PinCode_OR.countryDropDown, country);

			commonFunctions.selectValueFromDropdown(PinCode_OR.stateDropDown, state);

			commonFunctions.selectValueFromDropdown(PinCode_OR.cityDropDown, city);

			setValue(PinCode_OR.pinCode, pinCodeData);

			// click on Add button
			click(Shared_OR.addBtn);

			flag = true;

		} else {
			RESULT.FAIL("Failed to add 'Pin Code' because 'Pin Code' pop-up was not opened", true,
					ScreenshotType.browser);
		}
		return flag;
	}

	public void openPinCodePopUp() {

		// Click on 'Add link'.
		click(Shared_OR.addLink);

		// Verify Fields of 'Pin Code' Pop-up
		pinCode.verifyPinCodeMasterFields();

	}

	/*
	 * Method to add entry of new pin code.
	 */
	public void addNewPinCode(String country, String state, String city, String pinCodeData) {

		// Create object for the commonFunctions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Create object for the 'Pin Code'
		if (Objects.isNull(pinCode)) {
			pinCode = createObject("PinCode");
		}

		pinCode.openPinCodePopUp();

		boolean flag = pinCode.addPinCode(country, state, city, pinCodeData);
		if (flag) {

			// Verify Notification.
			commonFunctions.verifyNotification("Pin Code added successfully.");

			// Click on Close link
			click(Shared_OR.closelink);

		} else {
			RESULT.FAIL("Failed to add new entry of 'Pin Code'.", true, ScreenshotType.browser);
		}

		// Refresh the page.
		refreshPage();
	}

	/*
	 * Method to check fields of 'Pin Code Master' which are by default enabled or
	 * not.
	 */

	public void verifyPinCodeMasterFields() {

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

	public void editPinCode(String pinCodeToSearch, String pinCodeToEdit) {

		// Create the object for the "Common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Wait till the visibility of the 'Pin Code' grid
		commonFunctions.waitTillVisbilityOfElement(PinCode_OR.pinCodeGrid);

		// Click on edit button
		commonFunctions.clickOnEditButton(pinCodeToSearch, PinCode_OR.pinCodeColumn);

		// For verifying if the save button is enabled or not
		boolean flag = isElementExists(Country_OR.saveButton);
		if (flag) {
			setValue(PinCode_OR.editablePinCode, pinCodeToEdit);

			// Click on save
			click(Country_OR.saveButton);

			commonFunctions.waitTillVisbilityOfElement(Shared_OR.notification);
			// Verify Notification
			commonFunctions.verifyNotification("Pin Code updated successfully.");

		} else {
			RESULT.FAIL("Failed because row is not editable.", true, ScreenshotType.browser);
		}
		// Refresh the page.
		refreshPage();

	}

	public void verificationForDuplicatePinCode(String country, String state, String city, String pinCodeData) {
		// Create object for the commonFunctions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Create object for the 'Pin Code'
		if (Objects.isNull(pinCode)) {
			pinCode = createObject("PinCode");
		}

		boolean flag = pinCode.addPinCode(country, state, city, pinCodeData);
		if (flag) {

			// Verify Notification.
			commonFunctions.verifyNotification("");

		} else {
			RESULT.FAIL("Failed to add new entry of 'Pin Code'.", true, ScreenshotType.browser);
		}

		// Refresh the page.
		refreshPage();
	}

	/*
	 * Method to delete the entry of 'Pin Code'
	 */
	public void deletePinCode(String pinCodeToDelete) {

		// Create the object for the "Common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		By deleteButton = getLocator(SubArea_OR.deleteButton, pinCodeToDelete);

		boolean flag = isElementExists(deleteButton);
		if (flag) {

			// Click on delete button.
			commonFunctions.delteItem(deleteButton, pinCodeToDelete, "Pin Code");

			// Verify notification.
			commonFunctions.verifyNotification("Pin Code removed successfully.");

			// Refresh the page.
			refreshPage();
		} else {
			RESULT.FAIL("Failed because delete button was not available for record. ", true, ScreenshotType.browser);
		}
	}
}
