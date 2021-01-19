package suites.appsuites;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.DistricoConstant;
import pages.login.dashboard.masters.product.product.Product;
import suites.basesuite.LPDndBaseSuite;

public class ProductSuite extends LPDndBaseSuite {

	Dashboard dashboard;
	Product product;

	@BeforeTest
	public void navigateToProduct() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.PRODUCT);
	}

	@Test(priority = 1, dataProvider = "multipleInput", enabled = true)
	public void addNewProduct(String company, String productGroup, String productName, String aliasName) {
		product = createObject(DistricoConstant.PRODUCT);
		product.addNewProduct(company, productGroup, productName, aliasName);
	}

	@Test(priority = 2, dataProvider = "multipleInput", enabled = true)
	public void editProduct(String productNameForSearch, String productNameToEdit, String aliasName) {
		product = createObject(DistricoConstant.PRODUCT);
		product.editProduct(productNameForSearch, productNameToEdit, aliasName);
	}

	@Test(priority = 3, dataProvider = "multipleInput", enabled = true)
	public void verificationForDupliteProduct(String company, String productGroup, String productName, String aliasName) {
		product = createObject(DistricoConstant.PRODUCT);
		product.verificationForDupliteProduct(company, productGroup, productName, aliasName);
	}

	@Test(priority = 4, dataProvider = "multipleInput", enabled = true)
	public void deleteProduct(String productNameToDelete) {
		product = createObject(DistricoConstant.PRODUCT);
		product.deleteProduct(productNameToDelete);
	}

	@AfterTest
	public void navigateToDashBoard() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.DASHBOARD);
	}
}
