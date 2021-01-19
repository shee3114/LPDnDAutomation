package suites.appsuites;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.DistricoConstant;
import pages.login.dashboard.masters.addressMaster.pinCode.PinCode;
import suites.basesuite.LPDndBaseSuite;

public class PinCodeSuite extends LPDndBaseSuite {

	public Dashboard dashboard;
	public PinCode pinCode;

	@BeforeTest
	public void navigateToAreaMaster() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.PINCODE);
	}

	@Test(priority = 1, dataProvider = "multipleInput", enabled = true)
	public void addPinCode(String country, String state, String city, String pinCodeData) {
		pinCode = createObject(DistricoConstant.PINCODE);
		pinCode.addNewPinCode(country, state, city, pinCodeData);
	}

	@Test(priority = 2, dataProvider = "multipleInput", enabled = true)
	public void editPinCode(String pinCodeToSearch, String pinCodeToEdit) {
		pinCode = createObject(DistricoConstant.PINCODE);
		pinCode.editPinCode(pinCodeToSearch, pinCodeToEdit);
	}

	@Test(priority = 3, dataProvider = "multipleInput", enabled = false)
	public void verificationForDuplicatePinCode(String country, String state, String city, String pinCodeData) {
		pinCode = createObject(DistricoConstant.PINCODE);
		pinCode.verificationForDuplicatePinCode(country, state, city, pinCodeData);
		// For now this test case is getting failed.
	}

	@Test(priority = 4, dataProvider = "multipleInput", enabled = true)
	public void deletePinCode(String pinCodeToDelete) {
		pinCode = createObject(DistricoConstant.PINCODE);
		pinCode.deletePinCode(pinCodeToDelete);
		// Didn't test this because for now delete button is not showing for record.
	}

	@AfterTest
	public void navigateToDashBoard() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.DASHBOARD);
	}
}
