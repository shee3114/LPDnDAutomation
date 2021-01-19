package suites.appsuites;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.DistricoConstant;
import pages.login.dashboard.masters.product.productGroup.ProductGroup;
import suites.basesuite.LPDndBaseSuite;

public class ProductGroupSuite extends LPDndBaseSuite {

	public Dashboard dashboard;
	public ProductGroup productGroup;

	@BeforeTest
	public void navigateToStateMaster() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.PRODUCT_GROUP);
	}

	@Test(priority = 1, dataProvider = "multipleInput", enabled = true)
	public void addNewProductGroup(String type, String productGroupName) {
		productGroup = createObject(DistricoConstant.PRODUCT_GROUP);
		productGroup.addNewProductGroup(type, productGroupName);
	}

	@Test(priority = 2, dataProvider = "multipleInput", enabled = true)
	public void editProductGroup(String productGroupToSearch, String productGroupToEdit) {
		productGroup = createObject(DistricoConstant.PRODUCT_GROUP);
		productGroup.editProductGroup(productGroupToSearch, productGroupToEdit);
	}

	@Test(priority = 3, dataProvider = "multipleInput", enabled = true)
	public void deleteProductGroup(String productGroupToDelete) {
		productGroup = createObject(DistricoConstant.PRODUCT_GROUP);
		productGroup.deleteProductGroup(productGroupToDelete);
	}

	@Test(priority = 4, dataProvider = "multipleInput", enabled = true)
	public void addProductGroupWithExistingData(String type, String productGroupName) {
		productGroup = createObject(DistricoConstant.PRODUCT_GROUP);
		productGroup.verificationForDuplicateProductGroup(type, productGroupName);
	}

	@AfterTest
	public void navigateToDashBoard() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.DASHBOARD);
	}
}
