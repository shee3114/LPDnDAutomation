package suites.appsuites;

import org.testng.annotations.BeforeTest;

import pages.login.dashboard.Dashboard;
import pages.login.dashboard.core.DistricoConstant;
import suites.basesuite.LPDndBaseSuite;

public class PackingTypeSuite  extends LPDndBaseSuite {
Dashboard dashboard;

	@BeforeTest
	public void navigateToAreaMaster() {
		dashboard = createObject(DistricoConstant.DASHBOARD);
		dashboard.navigateToModule(DistricoConstant.PRODUCT_BRAND);
	}
}
