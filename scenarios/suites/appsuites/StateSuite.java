package suites.appsuites;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.DistricoConstant;
import pages.login.dashboard.masters.addressMaster.state.State;
import suites.basesuite.LPDndBaseSuite;

public class StateSuite extends LPDndBaseSuite {
	public State state;
	public Dashboard dashboard;

	@BeforeTest
	public void navigateToStateMaster() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.STATE);
	}

	@Test(priority = 1, dataProvider = "multipleInput", enabled = true)
	public void addState(String countryName, String stateName, String stateShortName, String gstCode) {
		State state = createObject(DistricoConstant.STATE);
		state.addNewState(countryName, stateName, stateShortName, gstCode);
	}

	@Test(priority = 2, dataProvider = "multipleInput", enabled = true)
	public void editState(String stateName, String stateShortName, String gstCode, String shortNameForSearch) {
		State state = createObject(DistricoConstant.STATE);
		state.editState(stateName, stateShortName, gstCode, shortNameForSearch);
	}

	@Test(priority = 3, dataProvider = "multipleInput", enabled = true)
	public void verificationForDuplicateState(String countryName, String stateName, String stateShortName,
			String gstCode) {
		State state = createObject(DistricoConstant.STATE);
		state.verificationForDuplicateState(countryName, stateName, stateShortName, gstCode);
	}

	@AfterTest
	public void navigateToDashBoard() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.DASHBOARD);
	}
}
