package pages.login.dashboard.masters.product.productGroup;

import java.util.Objects;

import org.openqa.selenium.By;
import base.BaseComponent;
import framework.reporter.ScreenshotType;
import pages.CommonFunctions;
import pages.Shared_OR;
import pages.login.dashboard.core.DistricoConstant;

public class ProductGroup extends BaseComponent {

	CommonFunctions commonFunctions;

	ProductGroup productGroup;

	/*
	 * Method to add data to mandatory fields which are required to add Product
	 * Group.
	 */
	public boolean addProductGroup(String type, String productGroup) {

		boolean flag = false;

		// Create object of the 'Common Functions'.
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Wait till the 'Product Group' grid is visible on the page.
		commonFunctions.waitTillVisbilityOfElement(ProductGroup_OR.productGroupGrid);

		// Check the visibility for the 'Product Group' grid field
		boolean productGroupField = isElementDisplayed(ProductGroup_OR.productGroupField);
		if (productGroupField) {

			// Enter data in remaining field.
			commonFunctions.selectValueFromDropdown(ProductGroup_OR.typeDropDown, type);

			commonFunctions.setValue(ProductGroup_OR.productGroupField, productGroup);

			// Click on Add Button.
			commonFunctions.click(Shared_OR.addBtn);

			flag = true;
		} else {
			RESULT.FAIL("Failed because 'Product Group' field is not available.", true, ScreenshotType.browser);
		}
		return flag;
	}

	/*
	 * Method to add new entry of the product group.
	 */
	public void addNewProductGroup(String type, String productGroupName) {

		// Create object of the 'Common Functions'.
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		if (Objects.isNull(productGroup)) {
			productGroup = createObject(DistricoConstant.PRODUCT_GROUP);
		}

		boolean flag = productGroup.addProductGroup(type, productGroupName);

		if (flag) {
			commonFunctions.verifyNotification("The Product Group - " + productGroupName + " added successfully.");
		} else {
			RESULT.FAIL("Failed to add New entry of 'Product Group", true, ScreenshotType.browser);
		}

		// Refresh the page.
		refreshPage();
	}

	public void editProductGroup(String productGroupForSearch, String productGroupToEdit) {

		// Create object of the 'Common Functions'.
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Click on the edit button for the selected Product Group
		commonFunctions.clickOnEditButton(productGroupForSearch, ProductGroup_OR.productGroupColumn);

		boolean flag = isElementExists(Shared_OR.saveButton);
		if (flag) {

			// Edit the data of 'Product Group' name field
			commonFunctions.setValue(ProductGroup_OR.editableProductGroupField, productGroupToEdit);

			// Click on 'Save' button
			click(Shared_OR.saveButton);

			// Verify Notification
			commonFunctions.verifyNotification("Product Group updated successfully.");

			// Refresh page.
			refreshPage();
		} else {
			RESULT.FAIL("Failed to edit the entry of 'Product Group' because row is not editable.", true,
					ScreenshotType.browser);
		}
	}

	public void deleteProductGroup(String productGroupToDelete) {

		// Create the object for the "Common Functions"
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		By deleteButton = getLocator(Shared_OR.deleteButton, productGroupToDelete);

		boolean flag = isElementExists(deleteButton);
		if (flag) {

			// Click on delete button.
			commonFunctions.delteItem(deleteButton, productGroupToDelete, "Product Group");

			// Verify notification.
			commonFunctions.verifyNotification("Product Group removed successfully.");

			// Refresh the page.
			refreshPage();
		} else {
			RESULT.FAIL("Failed to delete the entry because delete button is not available for the entry", true,
					ScreenshotType.browser);
		}
	}

	public void verificationForDuplicateProductGroup(String type, String productGroupName) {
		// Create object of the 'Common Functions'.
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		if (Objects.isNull(productGroup)) {
			productGroup = createObject(DistricoConstant.PRODUCT_GROUP);
		}

		boolean flag = productGroup.addProductGroup(type, productGroupName);

		if (flag) {
			commonFunctions.verifyNotification("This Product Group already exists !");
		} else {
			RESULT.FAIL(
					"Failed because system is allowed to add 'Product Group' which is already exists in the system. ",
					true, ScreenshotType.browser);
		}

		// Refresh the page.
		refreshPage();

	}
}
