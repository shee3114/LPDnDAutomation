package pages.login.dashboard.masters.product.product;

import java.util.Objects;

import org.openqa.selenium.By;

import base.BaseComponent;
import framework.reporter.ScreenshotType;
import pages.CommonFunctions;
import pages.Shared_OR;
import pages.login.dashboard.core.DistricoConstant;

public class Product extends BaseComponent {

	CommonFunctions commonFunctions;
	Product product;

	/*
	 * Method to add data to mandatory fields which are required to add Product.
	 */
	public boolean addProduct(String company, String productGroup, String productName, String aliasName) {

		boolean flag = false;

		// Create object of the 'Common Functions'.
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Wait till the 'Product' grid is visible on the page.
		commonFunctions.waitTillVisbilityOfElement(Product_OR.productGrid);

		// Check the visibility for the 'Company' field
		boolean companyField = isElementDisplayed(Product_OR.companyField);

		if (companyField) {

			// Enter data in remaining field.
			commonFunctions.selectValueFromDropdown(Product_OR.companyField, company);

			commonFunctions.selectValueFromDropdown(Product_OR.productGroupField, productGroup);

			commonFunctions.setValue(Product_OR.productNameField, productName);

			commonFunctions.setValue(Product_OR.aliasNameField, aliasName);

			// Click on Add Button.
			commonFunctions.click(Shared_OR.addBtn);

			flag = true;
		} else {
			RESULT.FAIL("Failed because 'Company' field is not available.", true, ScreenshotType.browser);
		}
		return flag;
	}

	public void addNewProduct(String company, String productGroup, String productName, String aliasName) {
		// Create object of the 'Common Functions'.
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		if (Objects.isNull(product)) {
			product = createObject(DistricoConstant.PRODUCT);
		}

		boolean flag = product.addProduct(company, productGroup, productName, aliasName);

		if (flag) {
			commonFunctions.verifyNotification("Product details added successfully.");
		} else {
			RESULT.FAIL("Failed to add New entry of 'Product", true, ScreenshotType.browser);
		}

		// Refresh the page.
		refreshPage();
	}

	public void editProduct(String productNameForSearch, String productNameToEdit, String aliasName) {

		// Create object of the 'Common Functions'.
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Click on the edit button for the selected Product Group
		commonFunctions.clickOnEditButton(productNameForSearch, Product_OR.productNameColumn);

		boolean flag = isElementExists(Shared_OR.saveButton);
		if (flag) {

			// Edit the data of 'Product Name' name field
			commonFunctions.setValue(Product_OR.editableProductNameField, productNameToEdit);

			// Edit the data of 'Alias Name' field
			commonFunctions.setValue(Product_OR.editableAliasNameField, aliasName);

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

	// Method to delete the product
	public void deleteProduct(String productNameToDelete) {

		// Create object for the 'Common Functions'
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		By deleteButton = getLocator(Shared_OR.deleteButton, productNameToDelete);

		boolean flag = isElementExists(deleteButton);
		if (flag) {

			// Click on Delete button
			commonFunctions.delteItem(deleteButton, productNameToDelete, "Product");

			// Verify notification.
			commonFunctions.verifyNotification("Product removed successfully.");

			// Refresh the page.
			refreshPage();
		} else {
			RESULT.FAIL("Failed because delete button was not available for record. ", true, ScreenshotType.browser);
		}

	}

	// Method to verify duplicate product

	public void verificationForDupliteProduct(String company, String productGroup, String productName,
			String aliasName) {

		// Create object of the 'Common Functions'.
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		if (Objects.isNull(product)) {
			product = createObject(DistricoConstant.PRODUCT);
		}

		boolean flag = product.addProduct(company, productGroup, productName, aliasName);

		if (flag) {
			boolean notification = commonFunctions.verifyNotification("Product has already been added!");

			if (notification) {
				RESULT.PASS(
						"Verified that system is not allowed to add 'Product' which is already exists in the system.",
						true, ScreenshotType.browser);
			} else {
				RESULT.FAIL("Failed because system is allowed to add 'Product' which is already exists in the system. ",
						true, ScreenshotType.browser);
			}
		}

		// Refresh the page.
		refreshPage();
	}
}
