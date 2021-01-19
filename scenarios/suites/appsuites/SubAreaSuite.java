package suites.appsuites;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.DistricoConstant;
import pages.login.dashboard.masters.addressMaster.subArea.SubArea;
import suites.basesuite.LPDndBaseSuite;

public class SubAreaSuite extends LPDndBaseSuite {

	public Dashboard dashboard;
	public SubArea subArea;

	@BeforeTest
	public void navigateToSubAreaMaster() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.SUBAREA);
	}

	@Test(priority = 1, dataProvider = "multipleInput", enabled = true)
	public void addSubArea(String country, String state, String city, String subAreaName) {
		subArea = createObject(DistricoConstant.SUBAREA);
		subArea.addNewSubArea(country, state, city, subAreaName);
	}

	@Test(priority = 2, dataProvider = "multipleInput", enabled = true)
	public void editSubArea(String subAreaToSearch, String subAreaToEdit) {
		subArea = createObject(DistricoConstant.SUBAREA);
		subArea.editSubArea(subAreaToSearch, subAreaToEdit);
	}

	@Test(priority = 3, dataProvider = "multipleInput", enabled = true)
	public void verificationForDuplicateSubArea(String country, String state, String city, String subAreaName) {
		subArea = createObject(DistricoConstant.SUBAREA);
		subArea.verificationForDuplicateSubArea(country, state, city, subAreaName);

	}

	@Test(priority = 4, dataProvider = "multipleInput", enabled = true)
	public void deleteSubArea(String subAreaToDelete) {
		subArea = createObject(DistricoConstant.SUBAREA);
		subArea.deleteSubArea(subAreaToDelete);
	}

	@AfterTest
	public void navigateToDashBoard() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.DASHBOARD);
	}
}
