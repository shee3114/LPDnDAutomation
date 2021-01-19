package pages.login.dashboard.masters.product.productBrand;

import java.util.Objects;

import org.openqa.selenium.By;

import base.BaseComponent;
import framework.reporter.ScreenshotType;
import pages.CommonFunctions;
import pages.Shared_OR;
import pages.login.dashboard.core.DistricoConstant;

public class ProductBrand extends BaseComponent {

	CommonFunctions commonFunctions;
	ProductBrand productBrand;

	// Method to add data to mandatory fields for brand
	public boolean addProductBrand(String brandName, String shortName) {
		boolean flag = false;

		// Create object of the 'Common Functions'.
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Wait till the 'Brand' grid is visible on the page.
		commonFunctions.waitTillVisbilityOfElement(ProductBrand_OR.brandGrid);

		// Check the visibility for the 'Title' field
		boolean productBrandGrid = isElementDisplayed(ProductBrand_OR.brandName);
		if (productBrandGrid) {

			setValue(ProductBrand_OR.brandName, brandName);

			setValue(ProductBrand_OR.shortName, shortName);

			// Click on Add Button.
			commonFunctions.click(Shared_OR.addBtn);

			flag = true;
		} else {
			RESULT.FAIL("Failed because 'Product Brand Grid' field is not available.", true, ScreenshotType.browser);
		}
		return flag;
	}

	/*
	 * Method to create new entry of brand.
	 */

	public void addNewProductBrand(String brandName, String shortName) {
		// Create object for the common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		if (Objects.isNull(productBrand)) {
			productBrand = createObject(DistricoConstant.PRODUCT_BRAND);
		}

		boolean flag = productBrand.addProductBrand(brandName, shortName);

		if (flag) {
			// Verify Notification.
			commonFunctions.verifyNotification("The Brand - " + brandName + " added successfully.");
		} else {
			RESULT.FAIL("Failed to add new entry of Product brand.", true, ScreenshotType.browser);
		}
		// Refresh page.
		refreshPage();
	}

	/*
	 * Method to edit the existing entry of brand.
	 */
	public void editProductBrand(String brandToSearch, String brandToEdit, String shortNameToEdit) {

		// Create object of the 'Common Functions'.
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		// Click on the edit button for the selected Brand.
		commonFunctions.clickOnEditButton(brandToSearch, ProductBrand_OR.brandNameColumn);

		// Verify if the 'Save' button is exist or not.
		boolean flag = isElementExists(Shared_OR.saveButton);
		if (flag) {

			// Edit the data of 'Title' field
			setValue(ProductBrand_OR.editableBrandName, brandToEdit);

			// Edit the data of 'Short Name' field
			setValue(ProductBrand_OR.editableBrandShortName, shortNameToEdit);

			// Click on 'Save' button
			click(Shared_OR.saveButton);

			// Verify Notification
			commonFunctions.verifyNotification("Brand updated successfully.");

		} else {
			RESULT.FAIL("Failed to edit the entry of 'Product Brand' because row was not editable.", true,
					ScreenshotType.browser);
		}
		// Refresh page.
		refreshPage();
	}

	/*
	 * Method to verify 'Add Brand' functionality with existing 'Brand Name'
	 */
	public void verificationForDuplicateBrandName(String brandName, String shortName) {

		// Create object for the common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		if (Objects.isNull(productBrand)) {
			productBrand = createObject(DistricoConstant.PRODUCT_BRAND);
		}

		// Add data to 'Title' and 'Short Name' field.
		boolean flag = productBrand.addProductBrand(brandName, shortName);

		if (flag) {
			// Verify Notification.
			boolean notification = commonFunctions.verifyNotification("Brand name has already been added !");
			if (notification) {
				RESULT.PASS(
						"Verified that system is not allowed to add new product brand entry with already existing brand name.",
						true, ScreenshotType.browser);
			} else {
				RESULT.FAIL(
						"Failed because system is allowing to add new product brand entry with already existing 'Brand Name'.",
						true, ScreenshotType.browser);
			}
		}

		// Refresh page.
		refreshPage();
	}

	/*
	 * Method to verify 'Add Brand' functionality with existing 'Brand Short Name'
	 */
	public void verificationForDuplicateShortName(String brandName, String shortName) {

		// Create object for the common Functions
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		if (Objects.isNull(productBrand)) {
			productBrand = createObject(DistricoConstant.PRODUCT_BRAND);
		}

		// Add data to 'Title' and 'Short Name' field.
		boolean flag = productBrand.addProductBrand(brandName, shortName);

		if (flag) {
			// Verify Notification.
			boolean notification = commonFunctions.verifyNotification("Short Name has already been added !");
			if (notification) {
				RESULT.PASS(
						"Verified that system is not allowed to add new product brand entry with already existing 'Short Name'.",
						true, ScreenshotType.browser);
			} else {
				RESULT.FAIL(
						"Failed because system is allowing to add new product brand entry with already existing 'Short Name'.",
						true, ScreenshotType.browser);
			}
		}

		// Refresh page.
		refreshPage();
	}

	/*
	 * Method to delete product Brand
	 */
	public void deleteProductStage(String brandToDelete) {

		// Create object for the 'Common Functions'
		commonFunctions = createObject(DistricoConstant.COMMON_FUNCTIONS);

		By deleteButton = getLocator(Shared_OR.deleteButton, brandToDelete);

		boolean flag = isElementExists(deleteButton);
		if (flag) {

			// Click on Delete button
			commonFunctions.delteItem(deleteButton, brandToDelete, "Brand");

			// Verify notification.
			commonFunctions.verifyNotification("Brand removed successfully.");

			// Refresh the page.
			refreshPage();
		} else {
			RESULT.FAIL("Failed because delete button was not available for record. ", true, ScreenshotType.browser);
		}
	}

}
