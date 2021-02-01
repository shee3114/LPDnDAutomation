package suites.appsuites;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.DistricoConstant;
import pages.login.dashboard.masters.product.packingMedium.PackingMedium;
import suites.basesuite.LPDndBaseSuite;

public class PackingMediumSuite extends LPDndBaseSuite {

	Dashboard dashboard;
	PackingMedium packingMedium;

	@BeforeTest
	public void navigateToAreaMaster() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.PACKING_MEDIUM);
	}

	@Test(priority = 1, dataProvider = "multipleInput", enabled = true)
	public void addNewPackingMedium(String packingMediumData, String shortName, String length, String breadth,
			String height) {
		packingMedium = createObject(DistricoConstant.PACKING_MEDIUM);
		packingMedium.addNewPackingMedium(packingMediumData, shortName, length, breadth, height);
	}

	@Test(priority = 2, dataProvider = "multipleInput", enabled = true)
	public void editPackingMedium(String packingMediumToSearch, String packingMediumToEdit, String shortName,
			String length, String breadth, String height) {
		packingMedium = createObject(DistricoConstant.PACKING_MEDIUM);
		packingMedium.editPackingMedium(packingMediumToSearch, packingMediumToEdit, shortName, length, breadth, height);
	}

	@Test(priority = 3, dataProvider = "multipleInput", enabled = true)
	public void addPckingMediumWithExistingData(String packingMediumData, String shortName, String length,
			String breadth, String height) {
		packingMedium = createObject(DistricoConstant.PACKING_MEDIUM);
		packingMedium.addPckingMediumWithExistingPackingMedium(packingMediumData, shortName, length, breadth, height);
	}

	@Test(priority = 4, dataProvider = "multipleInput", enabled = true)
	public void addPckingMdiumWthExistngshrtNme(String packingMediumData, String shortName, String length,
			String breadth, String height) {
		packingMedium = createObject(DistricoConstant.PACKING_MEDIUM);
		packingMedium.addPckingMediumWithExistingShortName(packingMediumData, shortName, length, breadth, height);
	}

	@Test(priority = 5, dataProvider = "multipleInput", enabled = true)
	public void deletePackingMedium(String packingMediumToDelete) {
		packingMedium = createObject(DistricoConstant.PACKING_MEDIUM);
		packingMedium.deletePackingMedium(packingMediumToDelete);
	}

	@AfterTest
	public void navigateToDashBoard() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.DASHBOARD);
	}
}
