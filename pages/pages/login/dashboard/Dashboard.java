package pages.login.dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import base.BaseComponent;
import framework.reporter.ScreenshotType;
import pages.CommonFunctions;
import pages.Shared_OR;
import pages.login.dashboard.core.DistricoConstant;
import pages.login.dashboard.masters.addressMaster.area.Area_OR;
import pages.login.dashboard.masters.addressMaster.city.City_OR;
import pages.login.dashboard.masters.addressMaster.country.Country_OR;
import pages.login.dashboard.masters.addressMaster.state.State_OR;

public class Dashboard extends BaseComponent {

	CommonFunctions commonFunctions = new CommonFunctions();

	/**
	 * @Objective: Method to navigate page
	 * 
	 * @author sheetald
	 * 
	 * @param moduleName- moduleName to verify the title of the page
	 */
	public <Type> Type navigateToModule(String moduleName) {

		// Wait for page to load
		commonFunctions.pause(8000);
		switch (moduleName) {
		case DistricoConstant.COUNTRY:
			commonFunctions.navigateToPage(Shared_OR.mastersMenu, Shared_OR.addressMenu, Country_OR.countryMenu);
			return createObject(DistricoConstant.COUNTRY);

		case DistricoConstant.STATE:
			navigateToPage(Shared_OR.mastersMenu, Shared_OR.addressMenu, State_OR.stateMenu);
			return createObject(DistricoConstant.STATE);

		case DistricoConstant.CITY:
			navigateToPage(Shared_OR.mastersMenu, Shared_OR.addressMenu, City_OR.cityMenu);
			return createObject(DistricoConstant.CITY);

		case DistricoConstant.AREA:
			navigateToPage(Shared_OR.mastersMenu, Shared_OR.addressMenu, Area_OR.areaMenu);
			return createObject(DistricoConstant.AREA);

		case DistricoConstant.DASHBOARD:
			click(Shared_OR.homeMenu);
			// verifyModule(SibConstant.DASHBOARD);
			

			verifyPageTitle("ShowItBig");
			return createObject(DistricoConstant.DASHBOARD);
		}
		return null;
	}

	public void navigateToPage(By moduleName, By menu, By subMenu) {
		Actions actions = new Actions(driver);
		commonFunctions = new CommonFunctions();
		commonFunctions.moveToElement(moduleName);
		commonFunctions.moveToElement(menu);
		commonFunctions.actionsClick(subMenu);
		actions.build().perform();
	}

	/**
	 * @Objective: Method to verify page
	 * 
	 * @author sheetald
	 * @param moduleName- moduleName to verify the title of the page
	 */
	public boolean verifyModule(String moduleName) {

		// String pageTitle = driver.getTitle();
		// String pageTitle = getTex(Shared_OR.pageTitle);
		// String pageTitle = driver.getTitle();
		// String module = commonFunctions.getModuleName();

		String pageTitle = getTextWebelement(Shared_OR.pageTitle);
		if (pageTitle.toLowerCase().contains(moduleName.toLowerCase())) {
			RESULT.PASS("'" + moduleName + "'" + " Module is opened successfully.", true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed to open the " + "'" + moduleName + "'" + " Module.", true, ScreenshotType.browser);
		}
		return false;
	}
}
