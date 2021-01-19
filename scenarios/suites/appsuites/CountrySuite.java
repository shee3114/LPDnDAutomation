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

	/*
	 * Method to verify 'Add country' functionality with already existing Country
	 * Name.
	 */
	@Test(priority = 3, dataProvider = "multipleInput", enabled = true)
	public void addCntryWithExistingCntryName(String countryName, String countryShortName,
			String expectedNotification) {
		country = createObject(DistricoConstant.COUNTRY);
		country.addCountryWithDuplicateCountryName(countryName, countryShortName, expectedNotification);
	}

	/*
	 * Method to verify 'Add country' functionality with already existing Country
	 * Short Name.
	 */
	@Test(priority = 4, dataProvider = "multipleInput", enabled = true)
	public void addCntryWthExistingCntryShrtNam(String countryName, String countryShortName,
			String expectedNotification) {
		country = createObject(DistricoConstant.COUNTRY);
		country.addCountryWithDuplicateCountryShortName(countryName, countryShortName, expectedNotification);
	}

	/*
	 * Method to verify 'Add country' functionality with already existing Country
	 * Name and Country Short Name.
	 */
	@Test(priority = 5, dataProvider = "multipleInput", enabled = true)
	public void adCntryWthExstngCntryAndShrtNam(String countryName, String countryShortName,
			String expectedNotification) {
		country = createObject(DistricoConstant.COUNTRY);
		country.addCountryWithDuplicateCountryNameAndCountryShortName(countryName, countryShortName,
				expectedNotification);

	}

	/*
	 * Method to delete the existing record of country
	 */
	@Test(priority = 6, dataProvider = "multipleInput", enabled = true)
	public void deleteCountry(String countryToDelete) {
		country = createObject(DistricoConstant.COUNTRY);
		country.delteCountry(countryToDelete);
	}

	@AfterTest
	public void navigateToDashBoard() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.DASHBOARD);
	}
}
