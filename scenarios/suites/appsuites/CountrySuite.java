package suites.appsuites;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.DistricoConstant;
import pages.login.dashboard.masters.addressMaster.country.Country;
import suites.basesuite.LPDndBaseSuite;

public class CountrySuite extends LPDndBaseSuite {
	public Country country;
	public Dashboard dashboard;

	@BeforeTest
	public void navigateToCountryMaster() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.COUNTRY);
	}

	@Test(priority = 1, dataProvider = "multipleInput", enabled = true)
	public void addCountry(String countryName, String countryShortName) {
		country = createObject(DistricoConstant.COUNTRY);
		country.addNewCountry(countryName, countryShortName);
	}

	@Test(priority = 2, dataProvider = "multipleInput", enabled = true)
	public void editCountry(String shortNameToSearch, String countryName, String countryShortName) {
		country = createObject(DistricoConstant.COUNTRY);
		country.editCountry(shortNameToSearch, countryName, countryShortName);
	}

	@Test(priority = 3, dataProvider = "multipleInput", enabled = true)
	public void verificationForDuplicateCountry(String countryName, String countryShortName) {
		country = createObject(DistricoConstant.COUNTRY);
		country.verificationForDuplicateCountry(countryName, countryShortName);
	}

	@AfterTest
	public void navigateToDashBoard() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.DASHBOARD);
	}
}
