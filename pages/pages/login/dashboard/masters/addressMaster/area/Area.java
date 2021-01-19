package pages.login.dashboard.masters.addressMaster.area;

import java.util.Objects;

import org.openqa.selenium.By;

import base.BaseComponent;
import framework.reporter.ScreenshotType;
import pages.CommonFunctions;
import pages.Shared_OR;
import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.DistricoConstant;

public class Area extends BaseComponent {

	CommonFunctions commonFunctions;

	Dashboard dashboard;

	Area area;

	/*
	 * Method to add data to the fields which are required to add area.
	 */
	public void addArea(String centreType, String centre, String areaName, String population, String areaShortName) {

		// Create Object for the commonFunctions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Wait till the 'Area' grid is visible on the page.
		commonFunctions.waitTillVisbilityOfElement(Area_OR.areaGrid);

		boolean flag = isElementExists(Area_OR.areaName);
		if (flag) {

			commonFunctions.selectValueFromDropdown(Shared_OR.centreTypeDropDown, centreType);

			commonFunctions.selectValueFromDropdown(Shared_OR.centreDropDown, centre);

			commonFunctions.moveToElement(Area_OR.areaName);
			commonFunctions.setValue(Area_OR.areaName, areaName);

			commonFunctions.moveToElement(Area_OR.population);
			commonFunctions.setValue(Area_OR.population, population);

			commonFunctions.moveToElement(Area_OR.areaShortName);
			commonFunctions.setValue(Area_OR.areaShortName, areaShortName);

			click(Shared_OR.addBtn);

		} else {
			RESULT.FAIL("Failed because 'Area Name' field is not exist on page.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to check fields of Area master which are by default enabled or not.
	 */
	public void verifyAreaMasterFields() {

		// Verify if 'Country' drop-down is by default enabled or not.
		boolean centreType = isElementEditable(Shared_OR.centreTypeDropDown);
		if (centreType) {
			RESULT.PASS("Successfully verified that 'Centre Type' drop-down is by default enabled.", true,
					ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because 'Centre Type' field is not by default enabled.", true, ScreenshotType.browser);
		}

		// Verify if 'state' drop-down is by default enabled or not.
		boolean centre = isElementEnabled(Shared_OR.centreDropDown);
		if (!centre) {
			RESULT.PASS("Successfully checked that 'Centre' field is by default disabled", true,
					ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because 'Centre' field is by default enabled.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to create new entry of the area
	 */

	public void addNewArea(String centreType, String centre, String areaName, String population, String areaShortName) {

		// Create Object for the commonFunctions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Create object for the 'Area' page.
		if (Objects.isNull(area)) {
			area = createObject(DistricoConstant.AREA);
		}

		area.verifyAreaMasterFields();
		area.addArea(centreType, centre, areaName, population, areaShortName);

		// Verify Notification
		commonFunctions.verifyNotification("Area details- " + areaName + " added successfully.");

		// Refresh the page
		refreshPage();
	}

	/*
	 * Method to check if the system is allowed to duplicate entry of area or not.
	 */
	public void verificationForDuplicateArea(String centreType, String centre, String areaName, String popuplation,
			String areaShortName) {

		// Create Object for the 'Common Functions'
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Create object for 'area'
		if (Objects.isNull(area)) {
			area = createObject(DistricoConstant.AREA);
		}

		area.addArea(centreType, centre, areaName, popuplation, areaShortName);

		// Verify Notification
		boolean flag = commonFunctions.verifyNotification("Area name and Short name already exists !");
		if (flag) {
			RESULT.PASS("Successfully verified that system is not allowed to add duplicate Area.", true,
					ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because system is allowing to add duplicate Area.", true, ScreenshotType.browser);
		}

		// Refresh the page
		refreshPage();

	}

	/*
	 * Method to edit the existing data of the area.
	 */
	public void editArea(String areaToSearch, String areaToEdit, String population, String areaShortName) {

		// Create object of the CommonFunctios
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Click on the edit Button of the particular row
		commonFunctions.clickOnEditButton(areaToSearch, Area_OR.areaColumn);

		// Verify if the 'save' button is showing or not
		boolean flag = isElementExists(Shared_OR.saveButton);
		if (flag) {

			commonFunctions.setValue(Area_OR.editableAreaName, areaToEdit);

			commonFunctions.setValue(Area_OR.editablePopulation, population);

			commonFunctions.setValue(Area_OR.editableAreaShortName, areaShortName);

			click(Shared_OR.saveButton);

			// Verify notification
			commonFunctions.verifyNotification("Area updated successfully.");
		} else {
			RESULT.FAIL("Failed to edit the entry of area because row is not editable.", true, ScreenshotType.browser);
		}
		// Refresh the page
		refreshPage();

	}

	/*
	 * Method to delete area.
	 */
	public void deleteArea(String areaToDelete) {

		// Create the object for the "Common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		By deleteButton = getLocator(Shared_OR.deleteButton, areaToDelete);

		boolean flag = isElementExists(deleteButton);
		if (flag) {

			// Click on delete button.
			commonFunctions.delteItem(deleteButton, areaToDelete, "Area");

			// Verify notification.
			commonFunctions.verifyNotification("Area removed successfully.");

			// Refresh the page.
			refreshPage();
		} else {
			RESULT.FAIL("Failed because delete button was not available for record. ", true, ScreenshotType.browser);
		}
	}
}
