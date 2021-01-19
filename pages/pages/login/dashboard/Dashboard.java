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
import pages.login.dashboard.masters.addressMaster.pinCode.PinCode_OR;
import pages.login.dashboard.masters.addressMaster.state.State_OR;
import pages.login.dashboard.masters.addressMaster.subArea.SubArea_OR;
import pages.login.dashboard.masters.product.product.Product_OR;
import pages.login.dashboard.masters.product.productBrand.ProductBrand_OR;
import pages.login.dashboard.masters.product.productGroup.ProductGroup_OR;
import pages.login.dashboard.masters.product.productStage.ProductStage_OR;

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

		case DistricoConstant.SUBAREA:
			navigateToPage(Shared_OR.mastersMenu, Shared_OR.addressMenu, SubArea_OR.subAreaMenu);
			return createObject(DistricoConstant.SUBAREA);

		case DistricoConstant.PINCODE:
			navigateToPage(Shared_OR.mastersMenu, Shared_OR.addressMenu, PinCode_OR.pinCodeMenu);
			return createObject(DistricoConstant.PINCODE);

		case DistricoConstant.PRODUCT_GROUP:
			navigateToPage(Shared_OR.mastersMenu, Shared_OR.productMenu, ProductGroup_OR.productGroupMenu);
			return createObject(DistricoConstant.PRODUCT_GROUP);

		case DistricoConstant.PRODUCT:
			navigateToPage(Shared_OR.mastersMenu, Shared_OR.productMenu, Product_OR.productMenu);
			return createObject(DistricoConstant.PRODUCT);

		case DistricoConstant.PRODUCT_STAGE:
			navigateToPage(Shared_OR.mastersMenu, Shared_OR.productMenu, ProductStage_OR.productStageMenu);
			return createObject(DistricoConstant.PRODUCT_STAGE);

		case DistricoConstant.PRODUCT_BRAND:
			navigateToPage(Shared_OR.mastersMenu, Shared_OR.productMenu, ProductBrand_OR.productBrandMenu);
			return createObject(DistricoConstant.PRODUCT_BRAND);

		case DistricoConstant.DASHBOARD:
			click(Shared_OR.homeMenu);

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
