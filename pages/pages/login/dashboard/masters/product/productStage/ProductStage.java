package pages.login.dashboard.masters.product.productStage;

import java.util.Objects;
import java.util.concurrent.Executor;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import base.BaseComponent;
import framework.reporter.ScreenshotType;
import pages.CommonFunctions;
import pages.Shared_OR;
import pages.login.dashboard.core.DistricoConstant;

public class ProductStage extends BaseComponent {

	CommonFunctions commonFunctions;

	ProductStage productStage;

	public boolean addProductStage(String productGroup, String productStageData) {
		boolean flag = false;

		// Create object for the 'Common Functions'
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Wait till the 'Product Stage' Grid getting visible on the page.
		commonFunctions.waitTillVisbilityOfElement(ProductStage_OR.productStageGrid);

		boolean productStageField = isElementExists(ProductStage_OR.productStageField);

		if (productStageField) {

			// Select the product group from the drop-down.
			commonFunctions.selectValueFromDropdown(Shared_OR.productGroupDropDown, productGroup);

			// Enter the data to the Product Stage field.
			setValue(ProductStage_OR.productStageField, productStageData);

			// Click on 'Add' button.
			click(Shared_OR.addBtn);

			flag = true;
		} else {
			RESULT.FAIL("Failed because 'Product Stage' field is not available.", true, ScreenshotType.browser);
		}
		return flag;
	}

	// Method to add new entry of the 'Product Stage'.
	public void addNewProductStage(String productGroup, String productStageData) {

		// Create object for the common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		if (Objects.isNull(productStage)) {
			productStage = createObject(DistricoConstant.PRODUCT_STAGE);
		}

		boolean flag = productStage.addProductStage(productGroup, productStageData);

		if (flag) {
			// Verify Notification.
			commonFunctions.verifyNotification("Product Stage details added successfully.");
		} else {
			RESULT.FAIL("Failed to add new entry of Product Stage.", true, ScreenshotType.browser);
		}
		// Refresh page.
		refreshPage();
	}

	// Method to edit 'Product Stage' Entry.
	public void editProductStage(String productStageToSearch, String productStageToEdit) {

		// Create object of the 'Common Functions'.
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Click on the edit button for the selected Product Stage.
		commonFunctions.clickOnEditButton(productStageToSearch, ProductStage_OR.productStageColum);

		// Verify if the 'Save' button is exist or not.
		boolean flag = isElementExists(Shared_OR.saveButton);
		if (flag) {

			// Edit the data of 'Product Stage' field
			commonFunctions.setValue(ProductStage_OR.editableProductStageField, productStageToEdit);

			// Click on 'Save' button
			click(Shared_OR.saveButton);

			// Verify Notification
			commonFunctions.verifyNotification("Product Stage updated successfully.");

		} else {
			RESULT.FAIL("Failed to edit the entry of 'Product Stage' because row was not editable.", true,
					ScreenshotType.browser);
		}
		// Refresh page.
		refreshPage();
	}

	// Method to delete the entry of 'Product Stage'
	public void deleteProductStage(String productStageToDelete) {

		// Create object for the 'Common Functions'
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		By deleteButton = getLocator(Shared_OR.deleteButton, productStageToDelete);

		boolean flag = isElementExists(deleteButton);
		if (flag) {

			// Click on Delete button
			commonFunctions.delteItem(deleteButton, productStageToDelete, "Product Stage");

			// Verify notification.
			commonFunctions.verifyNotification("Product removed successfully.");

			// Refresh the page.
			refreshPage();
		} else {
			RESULT.FAIL("Failed because delete button was not available for record. ", true, ScreenshotType.browser);
		}
	}

	public void verificationForDuplicateProductStage(String productGroup, String productStageData) {
		// Create object for the common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		if (Objects.isNull(productStage)) {
			productStage = createObject(DistricoConstant.PRODUCT_STAGE);
		}

		boolean flag = productStage.addProductStage(productGroup, productStageData);

		if (flag) {
			// Verify Notification.
			boolean notification = commonFunctions.verifyNotification("Product Stage has already been added!");

			if (notification) {
				RESULT.PASS("Verified that system is not allowed to add duplicate entry product Stage'.", true,
						ScreenshotType.browser);
			} else {
				RESULT.FAIL("Failed because system is allowing to add duplicate entry product Stage'.", true,
						ScreenshotType.browser);
			}
		}
		// Refresh page.
		refreshPage();
	}

}
