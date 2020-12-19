package suites.appsuites;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.DistricoConstant;
import pages.login.dashboard.masters.addressMaster.area.Area;
import suites.basesuite.LPDndBaseSuite;

public class AreaSuite extends LPDndBaseSuite {
	Dashboard dashboard;
	Area area;

	@BeforeTest
	public void navigateToStateMaster() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.AREA);
	}

	@Test(priority = 1, dataProvider = "multipleInput", enabled = true)
	public void addArea(String centreType, String centre, String areaName, String population, String areaShortName) {
		area = createObject(DistricoConstant.AREA);
		area.addNewArea(centreType, centre, areaName, population, areaShortName);
	}

	@Test(priority = 2, dataProvider = "multipleInput", enabled = false)
	public void editArea() {
		area = createObject(DistricoConstant.AREA);
	}

	@Test(priority = 3, dataProvider = "multipleInput", enabled = true)
	public void verificationForDuplicateArea(String centreType, String centre, String areaName, String population,
			String areaShortName) {
		area = createObject(DistricoConstant.AREA);
		area.verificationForDuplicateArea(centreType, centre, areaName, population, areaShortName);
	}

	@AfterTest
	public void navigateToDashBoard() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.DASHBOARD);
	}
}
