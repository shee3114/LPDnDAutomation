package suites.appsuites;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.CommonFunctions;
import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.DistricoConstant;
import pages.login.dashboard.masters.product.productStage.ProductStage;
import suites.basesuite.LPDndBaseSuite;

public class ProductStageSuite extends LPDndBaseSuite {

	CommonFunctions commonFunctions;

	ProductStage productStage;

	Dashboard dashboard;

	@BeforeTest
	public void navigateToCountryMaster() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.PRODUCT_STAGE);
	}

	@Test(priority = 1, dataProvider = "multipleInput", enabled = true)
	public void addProductStage(String productGroup, String productStageData) {
		productStage = createObject(DistricoConstant.PRODUCT_STAGE);
		productStage.addNewProductStage(productGroup, productStageData);
	}

	@Test(priority = 2, dataProvider = "multipleInput", enabled = true)
	public void editProductStage(String productStageToSearch, String productStageToEdit) {
		productStage = createObject(DistricoConstant.PRODUCT_STAGE);
		productStage.editProductStage(productStageToSearch, productStageToEdit);
	}

	@Test(priority = 3, dataProvider = "multipleInput", enabled = true)
	public void addProductGroupWithExistingData(String productGroup, String productStageData) {
		productStage = createObject(DistricoConstant.PRODUCT_STAGE);
		productStage.verificationForDuplicateProductStage(productGroup, productStageData);
	}

	@Test(priority = 4, dataProvider = "multipleInput", enabled = true)
	public void deleteProductStage(String productStageToDelete) {
		productStage = createObject(DistricoConstant.PRODUCT_STAGE);
		productStage.deleteProductStage(productStageToDelete);
	}

	@AfterTest
	public void navigateToDashBoard() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.DASHBOARD);
	}
}
