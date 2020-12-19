package pages.login;

import base.BaseComponent;
import framework.reporter.ScreenshotType;
import pages.login.dashboard.Dashboard;
import suites.basesuite.LPDndBaseSuite;

public class Login extends BaseComponent {

	/**
	 * @Objective: Method to verify the Alert Message
	 * 
	 * @param alertText- Alert Text to verify after entering invalid credentials
	 */
	public void alertVerification(String alertText) {

		if (isAlertPresent(2)) {
			RESULT.PASS("Alert Message exist after entering invalid credentials", false, ScreenshotType.browser);
			// Verify the Alert text
			verifyAlertMsg_AlertAction_PartialText(AlertAction.Accept, alertText);
			pause(LPDndBaseSuite.waitLow);
		} else {
			RESULT.FAIL("Failed to check the text on the alert and invalid text", true, ScreenshotType.browser);
		}
	}

	/**
	 * Objective- Method to check whether user is already logged out or not
	 * 
	 * @return- Yes - if user Logged out No - if user is not logged out
	 * 
	 */
	public boolean isAlreadyLoggedOut() {

		if (isElementExists(Login_OR.login))
			return true;
		else
			return false;
	}

	/**
	 * @Objective: Method to launch the URL in browser
	 * 
	 * @param URL- Specify the URL which you want to launch
	 */
	public void launch(String URL) {

		launchApplication(URL);
	}

	/**
	 * @Objective- Method to login into application
	 * 
	 * @param userName - UserName
	 * 
	 * @param password - Password
	 * @return Dashboard object if successfully login to Application
	 */
	public Dashboard login(String userName, String password) {

		// wait for the email field to be displayed
		waitForElement(Login_OR.userName, 30, WaitType.visibilityOfElementLocated);
		// Enter Username & Password
		setValue(Login_OR.userName, userName);
		setValue(Login_OR.password, password);
		click(Login_OR.login);
		// wait for page to be loaded
		pause(LPDndBaseSuite.waitMedium);
		// Check if user logged in successfully
		// if(loggedIn)
		// return createObject("Dashboard");
		// else
		// return null;
		return null; // remove this line
	}
}
