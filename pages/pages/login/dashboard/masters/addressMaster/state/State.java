package pages.login.dashboard.masters.addressMaster.state;

import java.util.Objects;

import org.openqa.selenium.By;

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

	public boolean addState(String countryName, String stateName, String stateShortName, String gstCode) {

		boolean flag = false;
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Wait till the 'State' grid is visible on the page.
		commonFunctions.waitTillVisbilityOfElement(State_OR.stateGrid);

		// Check the visibility for the 'State Name' field.
		boolean stateNameField = isElementDisplayed(State_OR.stateName);
		if (stateNameField) {
			// Select the value from Country drop-down.
			commonFunctions.selectValueFromDropdown(Shared_OR.countryDropDown, countryName);

			// Enter data in remaining field.
			commonFunctions.setValue(State_OR.stateName, stateName);

			commonFunctions.setValue(State_OR.stateShortName, stateShortName);

			commonFunctions.setValue(State_OR.gstCode, gstCode);

			// Click on Add Button.
			commonFunctions.click(Shared_OR.addBtn);
			flag = true;
		} else {
			RESULT.FAIL("Failed because 'State' field is not available.", true, ScreenshotType.browser);
		}
		return flag;
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
		boolean flag = state.addState(countryName, stateName, stateShortName, gstCode);
		if (flag) {
			// Verify Notification
			commonFunctions.verifyNotification("State added successfully.");
		} else {
			RESULT.FAIL("Failed to add new entry of State", true, ScreenshotType.browser);
		}
		// Refresh page
		refreshPage();
	}

	/*
	 * Method to edit the existing data for the state
	 */
	public void editState(String stateName, String stateShortName, String gstCode, String shortNameForSearch) {

		// Create the object for the "Common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		commonFunctions.clickOnEditButton(shortNameForSearch, State_OR.shortNameColumn);

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
		// Refresh page
		refreshPage();
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
		// Refresh page
		refreshPage();
	}

	public void deleteState(String stateToDelete) {
		// Create the object for the "Common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		By deleteButton = getLocator(Shared_OR.deleteButton, stateToDelete);

		boolean flag = isElementExists(deleteButton);
		if (flag) {

			// Click on delete button
			commonFunctions.delteItem(deleteButton, stateToDelete, "state");

			// Verify notification
			commonFunctions.verifyNotification("State removed successfully.");
		} else {
			RESULT.FAIL("Failed because delete button was not available for record. ", true, ScreenshotType.browser);
		}
	}

	/*
	 * Generic Method to verify duplicate State. &&&&&&&&&&&&&&&&&&&&&&
	 */
	public boolean verificationForduplicateData(String countryName, String stateName, String stateShortName,
			String gstCode, String expectedNotification) {
		boolean flag = false;

		// Create the object for the "Common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Create object for 'State' field.
		if (Objects.isNull(state)) {
			state = createObject(DistricoConstant.STATE);
		}
		// Add data to mandatory fields of the State
		boolean addState = state.addState(countryName, stateName, stateShortName, gstCode);
		if (addState) {
			boolean notification = commonFunctions.verifyNotification(expectedNotification);
			if (notification) {
				flag = true;
			}
		} else {
			RESULT.FAIL("Failed to add the data to Mandatory fields for State.", true, ScreenshotType.browser);
		}
		refreshPage();
		return flag;
	}

	/*
	 * Method to verify functionality of adding State with already existing State
	 * Name Name
	 */
	public void addStateWithDuplicateStateName(String countryName, String stateName, String stateShortName,
			String gstCode, String expectedNotification) {

		// Create object for 'State' field.
		if (Objects.isNull(state)) {
			state = createObject(DistricoConstant.STATE);
		}

		boolean flag = state.verificationForduplicateData(countryName, stateName, stateShortName, gstCode,
				expectedNotification);
		if (flag) {
			RESULT.PASS(
					"Successfully verified that system is not allowed to add state with already existing state Name.",
					true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because system is allowing to add state with already existing state Name", true,
					ScreenshotType.browser);
		}
	}

	/*
	 * Method to verify functionality of adding State with already existing State
	 * Short Name
	 */
	public void addStateWithDuplicateStateShortName(String countryName, String stateName, String stateShortName,
			String gstCode, String expectedNotification) {

		// Create object for 'State' field.
		if (Objects.isNull(state)) {
			state = createObject(DistricoConstant.STATE);
		}

		boolean flag = state.verificationForduplicateData(countryName, stateName, stateShortName, gstCode,
				expectedNotification);
		if (flag) {
			RESULT.PASS(
					"Successfully verified that system is not allowed to add State with already existing state Short Name.",
					true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because system is allowing to add state with already existing state Short Name", true,
					ScreenshotType.browser);
		}
	}

	/*
	 * Method to verify functionality of adding State with already existing State
	 * GST Code.
	 */
	public void addStateWithDuplicateGstCode(String countryName, String stateName, String stateShortName,
			String gstCode, String expectedNotification) {

		// Create object for 'State' field.
		if (Objects.isNull(state)) {
			state = createObject(DistricoConstant.STATE);
		}

		boolean flag = state.verificationForduplicateData(countryName, stateName, stateShortName, gstCode,
				expectedNotification);
		if (flag) {
			RESULT.PASS("Successfully verified that system is not allowed to add State with already existing GST Code.",
					true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because system is allowing to add state with already existing GST Code.", true,
					ScreenshotType.browser);
		}
	}

	/*
	 * Method to verify functionality of adding State with already existing State
	 * Name, State Short Name and GST Code.
	 */
	public void addStateWithDuplicateStateNameShortNameAndGST(String countryName, String stateName,
			String stateShortName, String gstCode, String expectedNotification) {

		// Create object for 'State' field.
		if (Objects.isNull(state)) {
			state = createObject(DistricoConstant.STATE);
		}

		boolean flag = state.verificationForduplicateData(countryName, stateName, stateShortName, gstCode,
				expectedNotification);
		if (flag) {
			RESULT.PASS(
					"Successfully verified that system is not allowed to add State with already existing 'State Name, 'State Short Name' and  GST Code.",
					true, ScreenshotType.browser);
		} else {
			RESULT.FAIL(
					"Failed because system is allowing to add state with already existing 'State Name, 'State Short Name' and  GST Code.",
					true, ScreenshotType.browser);
		}
	}
}
