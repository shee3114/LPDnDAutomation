package suites.basesuite;

import java.lang.reflect.Method;
import java.sql.Connection;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import base.BaseSuite;
import framework.input.Configuration;
import framework.reporter.ScreenshotType;
import pages.CommonFunctions;
import pages.login.Login;

/**
 *
 * project specific base suite to be extended by every suite
 *
 */
public class LPDndBaseSuite extends BaseSuite {

	public static Connection connection = null;

	public static String loggedInUserName, loggedInPassword, url;

	public static String loggedPatientName;

	public static int waitLow, waitMedium, waitHigh;

	public String suiteName;

	// setup the test for every run
	@BeforeSuite
	public void Login() {

		//////////////////////////////////////////////////////////////////
		Method method = null;
		try {
			method = this.getClass().getMethod("Login");
		} catch (NoSuchMethodException | SecurityException e) {
			RESULT.ERROR("Error while starting the suite", e, false, ScreenshotType.fullScreen);
		}
		// setup for reporting
		BaseSuite.setUpTest(method);
		/////////////////////////////////////////////////////////////////
		// # LOGIN CODE GOES BELOW # COMES AT FIRST BEFORE EXECUTING ALL SUITS
		// logged in UserName & Password from Configuration File
		loggedInUserName = Configuration.getProperty("userName");
		loggedInPassword = Configuration.getProperty("password");
		url = Configuration.getProperty("URL");
		// Get default waits for pause from Configuration File
		waitLow = Integer.valueOf(Configuration.getProperty("waitLow"));
		waitMedium = Integer.valueOf(Configuration.getProperty("waitMedium"));
		waitHigh = Integer.valueOf(Configuration.getProperty("waitHigh"));
		Login login = createObject("Login");
		login.launch(url);
		login.login(loggedInUserName, loggedInPassword);
	}

	@AfterSuite
	public void Logout() {

		/////////////////////////////////////////////////////////////////
		Method method = null;
		try {
			method = this.getClass().getMethod("Logout");
		} catch (NoSuchMethodException | SecurityException e) {
			RESULT.ERROR("Error while terminating the suite", e, false, ScreenshotType.fullScreen);
		}
		// setup for reporting
		BaseSuite.setUpTest(method);
		/////////////////////////////////////////////////////////////////
		// # LOGOUT CODE GOES BELOW # COMES TO EFFECT AFTER EXECUTION OF ALL
		// SUITS
		// logout from the app
		CommonFunctions commonFunctions = createObject("CommonFunctions");
		commonFunctions.logOut();
	}
}
