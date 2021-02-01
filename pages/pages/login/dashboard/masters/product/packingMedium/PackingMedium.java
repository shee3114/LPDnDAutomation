package pages.login.dashboard.masters.product.packingMedium;

import java.util.Objects;

import org.openqa.selenium.By;

import base.BaseComponent;
import framework.reporter.ScreenshotType;
import pages.CommonFunctions;
import pages.Shared_OR;
import pages.login.dashboard.core.DistricoConstant;
import pages.login.dashboard.masters.product.productBrand.ProductBrand_OR;

public class PackingMedium extends BaseComponent {

	CommonFunctions commonFunctions;
	PackingMedium packingMedium;

	// Method to add data to mandatory fields for brand
	public boolean addPackingMedium(String packingMediumData, String shortName, String length, String breadth,
			String height) {

		boolean flag = false;

		// Create object of the 'Common Functions'.
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Wait till the 'Packing Medium' grid is visible on the page.
		commonFunctions.waitTillVisbilityOfElement(PackingMedium_OR.packingMediumGrid);

		// Check the visibility for the 'Packing Medium' field
		boolean packingMediumGrid = isElementDisplayed(PackingMedium_OR.packingMediumGrid);
		if (packingMediumGrid) {

			setValue(PackingMedium_OR.packingMediumField, packingMediumData);

			setValue(PackingMedium_OR.shortNameField, shortName);

			setValue(PackingMedium_OR.lengthField, length);

			setValue(PackingMedium_OR.breadthField, breadth);

			setValue(PackingMedium_OR.heightField, height);

			// Click on Add Button.
			commonFunctions.click(Shared_OR.addBtn);

			flag = true;
		} else {
			RESULT.FAIL("Failed because 'Packing Medium Grid' field is not available.", true, ScreenshotType.browser);
		}
		return flag;
	}

	/*
	 * Method to create new entry of packing medium.
	 */

	public void addNewPackingMedium(String packingMediumData, String shortName, String length, String breadth,
			String height) {
		// Create object for the common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		if (Objects.isNull(packingMedium)) {
			packingMedium = createObject(DistricoConstant.PACKING_MEDIUM);
		}

		boolean flag = packingMedium.addPackingMedium(packingMediumData, shortName, length, breadth, height);

		if (flag) {
			// Verify Notification.
			commonFunctions.verifyNotification("Packing Medium added successfully.");
		} else {
			RESULT.FAIL("Failed to add new entry of Product brand.", true, ScreenshotType.browser);
		}
		// Refresh page.
		refreshPage();
	}

	/*
	 * Method to edit the existing entry of the packing medium
	 */
	public void editPackingMedium(String packingMediumToSearch, String packingMediumToEdit, String shortName,
			String length, String breadth, String height) {
		// Create object of the 'Common Functions'.
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Click on the edit button for the selected Brand.
		commonFunctions.clickOnEditButton(packingMediumToSearch, PackingMedium_OR.packingMediumColum);

		// Verify if the 'Save' button is exist or not.
		boolean flag = isElementExists(Shared_OR.saveButton);
		if (flag) {

			// Edit the data of 'Packing Medium' field
			setValue(PackingMedium_OR.editablePackingMedium, packingMediumToEdit);

			// Edit the data of 'Short Name' field
			setValue(PackingMedium_OR.editableShortName, shortName);
			setValue(PackingMedium_OR.editableLength, length);
			setValue(PackingMedium_OR.editableBreadth, breadth);
			setValue(PackingMedium_OR.editableHeight, height);

			// Click on 'Save' button
			click(Shared_OR.saveButton);

			// Verify Notification
			commonFunctions.verifyNotification("Packing Medium updated successfully.");

		} else {
			RESULT.FAIL("Failed to edit the entry of 'Product Brand' because row was not editable.", true,
					ScreenshotType.browser);
		}
		// Refresh page.
		refreshPage();

	}

	/*
	 * Method to verify if the system is allowing to add packing medium with already
	 * existing packing medium or not.
	 */
	public void addPckingMediumWithExistingPackingMedium(String packingMediumData, String shortName, String length,
			String breadth, String height) {

		// Create object for the common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		if (Objects.isNull(packingMedium)) {
			packingMedium = createObject(DistricoConstant.PACKING_MEDIUM);
		}

		boolean flag = packingMedium.addPackingMedium(packingMediumData, shortName, length, breadth, height);

		if (flag) {
			// Verify Notification.
			boolean notification = commonFunctions.verifyNotification("Packing Medium has already been added!");
			if (notification) {
				RESULT.PASS(
						"Verified that system is not allowed to add new packing medium entry with already existing 'Packing Medium'.",
						true, ScreenshotType.browser);
			} else {
				RESULT.FAIL(
						"Failed because system is allowing to add new packing medium entry with already existing 'Packing Medium'.",
						true, ScreenshotType.browser);
			}
			// Refresh page.
			refreshPage();
		}

	}

	/*
	 * Method to verify if the system is allowing to add packing medium with already
	 * existing packing medium or not.
	 */
	public void addPckingMediumWithExistingShortName(String packingMediumData, String shortName, String length,
			String breadth, String height) {

		// Create object for the common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		if (Objects.isNull(packingMedium)) {
			packingMedium = createObject(DistricoConstant.PACKING_MEDIUM);
		}

		boolean flag = packingMedium.addPackingMedium(packingMediumData, shortName, length, breadth, height);

		if (flag) {
			// Verify Notification.
			boolean notification = commonFunctions.verifyNotification("Short Name has already been added!");
			if (notification) {
				RESULT.PASS(
						"Verified that system is not allowed to add new packing medium entry with already existing 'Short Name'.",
						true, ScreenshotType.browser);
			} else {
				RESULT.FAIL(
						"Failed because system is allowing to add new packing medium entry with already existing 'Short Name'.",
						true, ScreenshotType.browser);
			}
			// Refresh page.
			refreshPage();

		}

		/// Packing Medium removed successfully.
		// Short Name has already been added!
	}

	public void deletePackingMedium(String packingMediumToDelete) {
		// Create object for the 'Common Functions'
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		By deleteButton = getLocator(Shared_OR.deleteButton, packingMediumToDelete);

		boolean flag = isElementExists(deleteButton);
		if (flag) {

			// Click on Delete button
			commonFunctions.delteItem(deleteButton, packingMediumToDelete, "Packing Medium");

			// Verify notification.
			commonFunctions.verifyNotification("Packing Medium removed successfully.");

			// Refresh the page.
			refreshPage();
		} else {
			RESULT.FAIL("Failed because delete button was not available for record. ", true, ScreenshotType.browser);
		}
	}
}
