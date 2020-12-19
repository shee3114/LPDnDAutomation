package pages.login.dashboard.masters.addressMaster.state;

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

public class State extends BaseComponent {

	CommonFunctions commonFunctions;

	Dashboard dashboard;

	State state;

	public void addState(String countryName, String stateName, String stateShortName, String gstCode) {
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		commonFunctions.waitTillVisbilityOfElement(State_OR.stateName);

		boolean flag = isElementDisplayed(State_OR.stateName);
		if (flag) {
			// Select the value from Country drop-down.
			commonFunctions.selectValueFromDropdown(Shared_OR.countryDropDown, countryName);

			// Enter data in remaining field.
			commonFunctions.setValue(State_OR.stateName, stateName);

			commonFunctions.setValue(State_OR.stateShortName, stateShortName);

			commonFunctions.setValue(State_OR.gstCode, gstCode);

			// Click on Add Button.
			commonFunctions.click(Shared_OR.addBtn);
		} else {
			RESULT.FAIL("Failed because 'State' field is not available.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to add new entry for State
	 */
	public void addNewState(String countryName, String stateName, String stateShortName, String gstCode) {

		// Create the object for the 'Common Functions'
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Create object of the 'State' field
		if (Objects.isNull(state)) {
			state = createObject(DistricoConstant.STATE);
		}
		state.addState(countryName, stateName, stateShortName, gstCode);

		// Verify Notification
		commonFunctions.verifyNotification("State added successfully.");
	}

	/*
	 * Method to edit the existing data for the state
	 */
	public void editState(String stateName, String stateShortName, String gstCode, String shortNameForSearch) {

		// Create the object for the "Common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		if (Objects.isNull(state)) {
			state = createObject(DistricoConstant.STATE);
		}

		state.clickOnEditButton(shortNameForSearch);

		// For verifying if the save button is enabled or not
		boolean flag = isElementExists(Shared_OR.saveButton);
		if (flag) {
			RESULT.PASS("Successfully checked that row is editable.", true, ScreenshotType.browser);

			setValue(State_OR.editableStateName, stateName);
			setValue(State_OR.editableShortName, stateShortName);
			setValue(State_OR.editableGstCode, gstCode);
			// Click on save
			click(Shared_OR.saveButton);

			// Verify Notification
			commonFunctions.verifyNotification("State updated successfully.");
		} else {
			RESULT.FAIL("Failed because row is not editable.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to click on 'Edit' button of the selected row
	 */
	public void clickOnEditButton(String shortName) {
		// Get the list of the of 'Short Name' column list
		List<WebElement> shortNamecolumnData = getList(State_OR.shortNameColumn);
		for (int i = 0; i < shortNamecolumnData.size() - 1; i++) {
			String data = shortNamecolumnData.get(i).getText();
			String dataWithoutSpace = data.trim();
			if (dataWithoutSpace.equals(shortName)) {
				i = i + 1;
				String number = String.valueOf(i);
				By stateEditBtn = getLocator(State_OR.editButton, number);
				click(stateEditBtn);
				break;
			}
		}
	}

	/*
	 * Method to verify if the system is allowed to add the duplicate entry of the
	 * state or not
	 */
	public void verificationForDuplicateState(String countryName, String stateName, String stateShortName,
			String gstCode) {

		// Create object for the 'Common Functions' class
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Create the object of the State
		if (Objects.isNull(state)) {
			state = createObject(DistricoConstant.STATE);
		}

		state.addState(countryName, stateName, stateShortName, gstCode);

		// Verify Notification
		boolean flag = commonFunctions.verifyNotification("State Name and Short Name already exists !");

		if (flag) {
			RESULT.PASS("Successfully verified that system is not allowed to add duplicate Sate.", true,
					ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because system is allowing to add duplicate State.", true, ScreenshotType.browser);
		}

	}

}

// Deleted notification - State removed successfully.
