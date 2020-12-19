package pages.login.dashboard.masters.addressMaster.area;

import java.util.Objects;

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

		// Wait for page loading till the 'Area Name' field gets visible
		commonFunctions.waitTillVisbilityOfElement(Area_OR.areaName);

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
		} else {
			RESULT.FAIL("Failed because 'Area Name' field is not exist on page.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to create new entry of the area
	 */

	public void addNewArea(String centreType, String centre, String areaName, String population, String areaShortName) {

		// Create Object for the commonFunctions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		if (Objects.isNull(area)) {
			area = createObject(DistricoConstant.AREA);
		}

		area.addArea(centreType, centre, areaName, population, areaShortName);

		click(Shared_OR.addBtn);

		// Verify Notification
		commonFunctions.verifyNotification("Area details- " + areaName + " added successfully.");
	}

	/*
	 * Method to check if the system is allowed to duplicate entry of area or not.
	 */
	public void verificationForDuplicateArea(String centreType, String centre, String areaName, String popuplation,
			String areaShortName) {
		// Create Object for the commonFunctions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		if (Objects.isNull(area)) {
			area = createObject(DistricoConstant.AREA);
		}

		area.addArea(centreType, centre, areaName, popuplation, areaShortName);

		click(Shared_OR.addBtn);

		// Verify Notification
		boolean flag = commonFunctions.verifyNotification("Area name and Short name already exists !");
		if (flag) {
			RESULT.PASS("Successfully verified that system is not allowed to add duplicate Area.", true,
					ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because system is allowing to add duplicate Area.", true, ScreenshotType.browser);
		}
	}
}
