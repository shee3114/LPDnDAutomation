package suites.appsuites;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.DistricoConstant;
import pages.login.dashboard.masters.product.productBrand.ProductBrand;
import suites.basesuite.LPDndBaseSuite;

public class ProductBrandSuite extends LPDndBaseSuite {

	Dashboard dashboard;
	ProductBrand productBrand;

	@BeforeTest
	public void navigateToAreaMaster() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.PRODUCT_BRAND);
	}

	@Test(priority = 1, dataProvider = "multipleInput", enabled = true)
	public void addNewProductBrand(String brandName, String shortName) {
		productBrand = createObject(DistricoConstant.PRODUCT_BRAND);
		productBrand.addNewProductBrand(brandName, shortName);
	}

	@Test(priority = 2, dataProvider = "multipleInput", enabled = true)
	public void editProductBrand(String brandToSearch, String brandToEdit, String shortNameToEdit) {
		productBrand = createObject(DistricoConstant.PRODUCT_BRAND);
		productBrand.editProductBrand(brandToSearch, brandToEdit, shortNameToEdit);
	}

	@Test(priority = 3, dataProvider = "multipleInput", enabled = true)
	public void addBrandWithExistingBrandName(String brandName, String shortName) {
		productBrand = createObject(DistricoConstant.PRODUCT_BRAND);
		productBrand.verificationForDuplicateBrandName(brandName, shortName);
	}
	
	@Test(priority = 4, dataProvider = "multipleInput", enabled = true)
	public void addBrandWithExistingShortName(String brandName, String shortName) {
		productBrand = createObject(DistricoConstant.PRODUCT_BRAND);
		productBrand.verificationForDuplicateShortName(brandName, shortName);
	}
	
	@Test(priority = 5, dataProvider = "multipleInput", enabled = true)
	public void deleteProductBrand(String brandToDelete) {
		productBrand = createObject(DistricoConstant.PRODUCT_BRAND);
		productBrand.deleteProductStage(brandToDelete);
	}
	@AfterTest
	public void navigateToDashBoard() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.DASHBOARD);
	}
}
