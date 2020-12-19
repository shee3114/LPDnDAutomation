package suites.appsuites;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.DistricoConstant;
import pages.login.dashboard.masters.addressMaster.city.City;
import suites.basesuite.LPDndBaseSuite;

public class CitySuite extends LPDndBaseSuite {

	Dashboard dashboard;
	City city;

	@BeforeTest
	public void navigateToStateMaster() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.CITY);
	}

	@Test(priority = 1, dataProvider = "multipleInput", enabled = true)
	public void addCity(String countryName, String stateName, String cityName) {
		city = createObject(DistricoConstant.CITY);
		city.addNewCity(countryName, stateName, cityName);

	}

	@Test(priority = 2, dataProvider = "multipleInput", enabled = true)
	public void editCity(String stateName, String cityNameToSearch, String cityNameToEdit) {
		city = createObject(DistricoConstant.CITY);
		city.editCity(cityNameToSearch, cityNameToEdit);
	}

	// This method to verify if the system is allowed to add duplicate city or not.
	@Test(priority = 3, dataProvider = "multipleInput", enabled = true)
	public void verificationForDuplicateCity(String countryName, String stateName, String cityName) {
		city = createObject(DistricoConstant.CITY);
		city.verificationForDuplicateCity(countryName, stateName, cityName);
	}

	@AfterTest
	public void navigateToDashBoard() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.DASHBOARD);
	}
}
