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

	/*
	 * Method to add new entry of 'State'
	 */
	@Test(priority = 1, dataProvider = "multipleInput", enabled = true)
	public void addState(String countryName, String stateName, String stateShortName, String gstCode) {
		state = createObject(DistricoConstant.STATE);
		state.addNewState(countryName, stateName, stateShortName, gstCode);
	}

	/*
	 * Method to edit existing State Record.
	 */
	@Test(priority = 2, dataProvider = "multipleInput", enabled = true)
	public void editState(String stateName, String stateShortName, String gstCode, String shortNameForSearch) {
		state = createObject(DistricoConstant.STATE);
		state.editState(stateName, stateShortName, gstCode, shortNameForSearch);
	}

	/*
	 * Method to verify 'Add State' functionality with already existing State Name.
	 */
	@Test(priority = 3, dataProvider = "multipleInput", enabled = true)
	public void addStateWthExistingStateName(String countryName, String stateName, String stateShortName,
			String gstCode, String expectedNotification) {
		state = createObject(DistricoConstant.STATE);
		state.addStateWithDuplicateStateName(countryName, stateName, stateShortName, gstCode, expectedNotification);
	}

	/*
	 * Method to verify 'Add State' functionality with already existing State Short
	 * Name.
	 */
	@Test(priority = 4, dataProvider = "multipleInput", enabled = true)
	public void addStateWthExstingStateShrtName(String countryName, String stateName, String stateShortName,
			String gstCode, String expectedNotification) {
		state = createObject(DistricoConstant.STATE);
		state.addStateWithDuplicateStateShortName(countryName, stateName, stateShortName, gstCode,
				expectedNotification);
	}

	/*
	 * Method to verify 'Add State' functionality with already existing GST Code.
	 */
	@Test(priority = 5, dataProvider = "multipleInput", enabled = true)
	public void addStateWthExistingGstCode(String countryName, String stateName, String stateShortName, String gstCode,
			String expectedNotification) {
		state = createObject(DistricoConstant.STATE);
		state.addStateWithDuplicateGstCode(countryName, stateName, stateShortName, gstCode, expectedNotification);
	}

	/*
	 * Method to verify 'Add State' functionality with already existing 'State
	 * Name', 'State Short Name' and 'GST Code'
	 */
	@Test(priority = 6, dataProvider = "multipleInput", enabled = true)
	public void addStatWthExistingStatNamAndGst(String countryName, String stateName, String stateShortName,
			String gstCode, String expectedNotification) {
		state = createObject(DistricoConstant.STATE);
		state.addStateWithDuplicateStateNameShortNameAndGST(countryName, stateName, stateShortName, gstCode,
				expectedNotification);
	}

	/*
	 * Method to delete the existing state
	 */
	@Test(priority = 7, dataProvider = "multipleInput", enabled = true)
	public void deleteState(String stateToDelete) {
		state = createObject(DistricoConstant.STATE);
		state.deleteState(stateToDelete);
	}

	@AfterTest
	public void navigateToDashBoard() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.DASHBOARD);
	}
}
