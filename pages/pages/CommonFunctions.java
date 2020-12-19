package pages;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseComponent;
import framework.reporter.ScreenshotType;
import pages.login.Login;
import pages.login.Login_OR;
import pages.login.dashboard.core.DistricoConstant;
import suites.basesuite.LPDndBaseSuite;

public class CommonFunctions extends BaseComponent {

	/**
	 * Logout from the application
	 * 
	 */
	public void logOut() {

		Login login = createObject("Login");
		// Check whether user is already logged out or not
		if (login.isAlreadyLoggedOut())
			RESULT.INFO("User is already logged Out", true, ScreenshotType.browser);
		else {

			click(Login_OR.logoutButton);

			// Verification for the login button present after logout

			pause(DistricoConstant.MODERATE);
			if (isElementExists(Login_OR.login) == true) {
				RESULT.PASS("Logout is performed successfully", false, ScreenshotType.browser);
				// click(Login_OR.login);
			} else {
				RESULT.FAIL("Logout is not performed successfully", true, ScreenshotType.browser);
			}
		}
	}

	/**
	 * @Objective: Method to verify the background color of the link to idenify
	 *             whether it is already clicked or not
	 * 
	 * @author vivekp
	 * 
	 * @param linklocator- locator to identify the particular module/submodule link
	 * 
	 * @param backcolorcode- HEX code of the background/font color property of the
	 *        link
	 * 
	 * @param module- Module or submodule name for which you want verify whether it
	 *        is already clicked or not
	 * 
	 * @param cssProperty- Type of the CSS Property for which you want to verify the
	 *        color attribute
	 */
	public void clickAndCheckLink(By linklocator, String backcolorcode, String module, String cssProperty) {

		String hexcolor = null;
		if (cssProperty.contentEquals("backgroundcolor")) {
			hexcolor = getColorHexCode(linklocator, "background-color");
		} else {
			hexcolor = getColorHexCode(linklocator, "color");
		}
		if (hexcolor.contentEquals(backcolorcode)) {
			RESULT.PASS(module + " is already selected", false, ScreenshotType.browser);
		} else {
			click(linklocator);
			RESULT.PASS("Clicked on " + module, false, ScreenshotType.browser);
		}
		waitForElement(Shared_OR.pageLoadIcon, 60, WaitType.invisibilityOfElementLocated);
		// pause(500);
	}

	/**
	 * @Objective: Method to Convert RGB value to Hex & get the Background color of
	 *             particular element
	 * 
	 * @author vivekp
	 * 
	 * @param locator- locator of the WebElement for which backgroup color is
	 *        required
	 * 
	 */
	public String getColorHexCode(By locator, String CssProperty) {

		String color1[];
		String backcolor = getCSSvalue(locator, CssProperty);
		color1 = backcolor.replace("rgba(", "").split(",");
		String hex = String.format("#%02x%02x%02x", Integer.parseInt(color1[0].trim()),
				Integer.parseInt(color1[1].trim()), Integer.parseInt(color1[2].trim()));
		return hex;
	}

	/**
	 * To check if page is loaded by verifying the header. then returning true if
	 * page is matched else false
	 * 
	 * @author rajatg
	 * 
	 * @param expectedPageHeader
	 */
	public Boolean checkPageHeader(String expectedPageHeader) {

		String LocatorText = getTextWebelement(Shared_OR.pageHeaderText);
		if (LocatorText.contains(expectedPageHeader)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * To verify Page Label/Header after navigation
	 * 
	 * @author Ekansh
	 * 
	 * @param expectedPageHeader- Page Header which need to verified
	 */
	public void verifyPageLabel(String expectedPageHeader) {

		// Wait for page to load
		waitForElement(Shared_OR.pageLoadIcon, 180, WaitType.invisibilityOfElementLocated);
		// Get the page header to verify whether page loaded or not
		String LocatorText = getTextWebelement(Shared_OR.pageHeaderText);
		if (LocatorText.contains(expectedPageHeader)) {
			RESULT.PASS("Page: " + LocatorText + " loaded successfully.", false, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Page: " + LocatorText + " not loaded successfully.", true, ScreenshotType.browser);
		}
	}

	/**
	 * Click on Notifications
	 * 
	 * @author Ekansh
	 */
	public void Notifications() {

		if (isElementExists(Shared_OR.Notifications) == true) {
			click(Shared_OR.Notifications);
		} else {
			System.out.println("Notifications are already opened");
		}
	}
	/**
	 * @Objective- Method to Open Specific Option from Patient Drop Down
	 * 
	 * @param patientMenuOption - Use PatientMenuOption Interface to select specific
	 *                          Option
	 * 
	 * @param pageHeader        - Specify Page Header for verification
	 * 
	 * @return Object of particular Page
	 */
	// public <Type> Type openPatientMenuItem(String patientMenuOption, String
	// pageHeader) {
	//
	// // Get Locator of Patient Menu dropdown button
	// By patientMenu = getLocator(Shared_OR.patientOptions,
	// ShowItBigBaseSuite.loggedPatientName);
	//
	// // Get String Locator of Patient Drop Down list items
	// String patientMenuList = patientMenu.toString();
	// String patientList =
	// patientMenuList.substring(patientMenuList.indexOf("//"))
	// + "/following-sibling::ul/li";
	//
	// // Get Locator of Patient Drop Down List
	// By patMenuList = By.xpath(patientList);
	//
	// // Get Locator of Patient Forms Option
	// By forms = getLocator(Shared_OR.patientOptions, "Forms");
	//
	// // Check whether Forms Option available or not
	// if (isElementExists(forms) == false &&
	// patientMenuOption.equalsIgnoreCase(PatientMenuOption.formsToBeFilled)) {
	// // Log the result if patient form option is not available
	// RESULT.WARNING(
	// "Patient: " + ShowItBigBaseSuite.loggedPatientName + " doesn't contain
	// any
	// forms to be signed.",
	// true, ScreenshotType.browser);
	// return null;
	// }

	// // Check whether Patient Drop Down list is already opened or not
	// if (isElementDisplayed(patMenuList) == false) {
	//
	// // Open Patient List menu
	// click(patientMenu);
	//
	// waitForPageLoad();
	//
	// // Open Specific Option and return the Object
	// return openPatientSpecificOption(patientMenuOption, pageHeader);
	// } else {
	// RESULT.INFO("Patient drop down is already opened", true,
	// ScreenshotType.browser);
	//
	// // Open Specific Option and return the Object
	// return openPatientSpecificOption(patientMenuOption, pageHeader);
	// }
	// }
	/**
	 * @Objective- Method to Navigate to Specific Option under Patient Menu List
	 * 
	 * @param menuOption - Specify the Menu Option which need to be open
	 * 
	 * @param pageHeader - Expected Page Header After loading a page
	 * 
	 * @return Object of Specified Page
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private <Type> Type openPatientSpecificOption(String patientMenuOption, String pageHeader) {

		// Get Locator of Specific Patient Menu List Option
		By patListOption = getLocator(Shared_OR.pateientMenuListOpt, LPDndBaseSuite.loggedPatientName,
				patientMenuOption);
		// Click on Particular Link
		click(patListOption);
		waitForPageLoad();
		// Verify the Page Header and return the Object
		if (checkPageHeader(pageHeader)) {
			switch (patientMenuOption) {
			case "Forms To Be Filled":
				return (Type) createObject("PatientForms");
			case "About clinic":
				return (Type) createObject("AboutClinic");
			case "Contact clinic":
				return (Type) createObject("ContactClinic");
			case "Email /Text Subscription":
				return (Type) createObject("EmailSubScription");
			case "Help":
				return (Type) createObject("Help");
			case "Activity Log(s)":
				return (Type) createObject("ActivityLogs");
			default:
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * @Objective: Method to Click on Search
	 * 
	 * @author swatic
	 * 
	 * @param locator- locator of the Search button
	 * 
	 * 
	 * 
	 */
	public void clickSearch() {

		click(Shared_OR.Search);
	}

	/**
	 * @Objective: Method to pass the date as string format and set the calendar
	 *             date using calendar picker
	 * 
	 * @author AbhijitG
	 * 
	 * @param i_calendar_index = This will pass which calendar user want to click on
	 *                         the page (index starts from 0) s_day = To pass the
	 *                         date as a string
	 * 
	 */
	public void calendarSetDate(int i_calendar_index, String s_day) {

		int i_date; // To store the Date part
		int i_month; // To store the Month part
		int i_year; // To store the Year part
		String[] as_date = s_day.split("-"); // Array to store date, month and
		// year separately
		i_date = Integer.parseInt(as_date[1]);
		i_month = Integer.parseInt(as_date[0]);
		i_year = Integer.parseInt(as_date[2]);
		int i_first_year; // To store the first year of the dropdown
		int i_get_year_index; // To store the index to select the year
		int i_get_month_index; // To store the index to select the month
		int i_get_date_index; // To store the index to select the date
		// Declare List to store all the Calendar in the page
		List<WebElement> li_we_calendar_icon = getList(Shared_OR.calendarIcon);
		// Verification for the Calendar icon is displayed on the page
		if (li_we_calendar_icon.get(i_calendar_index).isDisplayed()) {
			// Verification for the Calendar icon is Enabled
			if (li_we_calendar_icon.get(i_calendar_index).isEnabled()) {
				// Click on the Calendar icon
				if (!isElementDisplayed(Shared_OR.by_calendar_year))
					li_we_calendar_icon.get(i_calendar_index).click();
				// Click on the year dropdown of the selected calendar
				click(Shared_OR.by_calendar_year);
				// Declare List to store the all the Years from the dropdown
				List<WebElement> li_we_calendar_year = getList(Shared_OR.by_calendar_all_year);
				// To get the first year of the dropdown as string and convert
				// it to integer
				i_first_year = Integer.parseInt(li_we_calendar_year.get(0).getText());
				// To get the difference from the first year and year passed as
				// argument to select the index
				i_get_year_index = i_year - i_first_year;
				// Click on the year from the dropdown
				li_we_calendar_year.get(i_get_year_index).click();
				// Declare List to store the all the Months from the dropdown
				List<WebElement> li_we_calendar_month = getList(Shared_OR.by_calendar_all_month);
				// To get the index of the Year to select
				i_get_month_index = i_month - 1;
				// Click on the year from the dropdown
				li_we_calendar_month.get(i_get_month_index).click();
				// Declare List to store the all the Date
				List<WebElement> li_we_calendar_date = getList(Shared_OR.by_calendar_all_date);
				// To get the index of the Date to select
				i_get_date_index = i_date - 1;
				// Click on the year from the dropdown
				li_we_calendar_date.get(i_get_date_index).click();
			} else {
				RESULT.PASS("Calendar disabled.", false, ScreenshotType.browser);
			}
		} else {
			RESULT.PASS("Calendar not displayed.", true, ScreenshotType.browser);
		}
	}
	// End Jun 11, 2017 (AbhijitG)

	// Start Jun 11, 2017 (AbhijitG)
	/**
	 * @Objective: Method to Get the date by passing the parameter
	 * 
	 * @author vivekp
	 * 
	 * @param noDays = Pass the Integer value to set the date range from current
	 *               date
	 */
	public String getDate(int noDays) {

		String date;
		// DateFromate Object to get date in specific format
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		// Add Integer value to get required date using Calendar instance
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.add(Calendar.DATE, noDays);
		Date expDate = calendarDate.getTime();
		// Format required date in specified format
		date = dateFormat.format(expDate);
		return date;
	}

	/**
	 * @Objective: Method to convert Date Format from yyyy-MM-dd to MMM dd,YYYY
	 * 
	 * @param date- Specify the date in yyyy-MM-dd format
	 * 
	 * @return- Date in MMM dd,yyyy format
	 */
	public String getDateMMMddYYYY(String date) {

		Date myDate = null;
		try {
			myDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			String finalDate = new SimpleDateFormat("MMM dd, yyyy").format(myDate);
			return finalDate;
		} catch (ParseException e) {
			RESULT.FAIL("Given Date String is not in YYYY-MM-dd format", false, ScreenshotType.browser);
			return null;
		}
	}

	/**
	 * @Objective: Method to traverse page by page and store data of each cell of a
	 *             particular column in an Array
	 * 
	 * @author vivekp
	 * 
	 * @param tableXpathLoactor - Xpath of Particular table from which you want to
	 *                          get Data
	 * 
	 * @param columnName        - Column Name visible on front end
	 * 
	 * @throws InterruptedException
	 * 
	 * @return All the data in an ArrayList
	 */
	public ArrayList<String> getCellData(By tableXpathLoactor, String columnName) throws InterruptedException {

		int cellCount = 0;
		String tablepath = tableXpathLoactor.toString();
		String tableXpath = tablepath.substring(tablepath.lastIndexOf("//"));
		// Generate Xpath for Last row of the Table
		String pagingRowXpath = tableXpath + "/tbody/tr[11]/td/font//descendant::a";
		By paging = By.xpath(pagingRowXpath);
		// Initialize array list to store the result set
		ArrayList<String> resultCellData = new ArrayList<String>(0);
		// Create Xpath for Header for specified table
		String tableHeaderXpath = tableXpath + "/thead/tr/th";
		By tableLocator = By.xpath(tableXpath);
		// Generate Cell Xpath for specified Table
		String cellXpath = tableXpath + "/tbody/tr/td";
		By cell = By.xpath(cellXpath);
		// Get no. of cells available in the table
		List<WebElement> firstcell = getList(cell);
		cellCount = firstcell.size();
		// Get List of WebElement for Table Header
		List<WebElement> tableHeaderElement = getList(By.xpath(tableHeaderXpath));
		// Get specified column index
		int columnIndex = -1;
		for (int i = 0; i < tableHeaderElement.size(); i++) {
			if (tableHeaderElement.get(i).getText().equals(columnName)) {
				columnIndex = i + 1;
				break;
			}
		}
		// Check whether required Column Exists in Table or not
		if (columnIndex != -1) {
			// Check for Cell Count
			if (cellCount > 1) {
				// Check for paging Element present or not
				if (isElementExists(paging)) {
					// Creating Xpath for No. Of Page Count for navigating to
					// each page
					String totalPageXpath = tableXpath
							+ "/tbody/tr[11]/td/font//descendant::font[contains(@class,'header_text')]";
					By totalPageLocator = By.xpath(totalPageXpath);
					String totalPages = getTextWebelement(totalPageLocator);
					// Getting total no of page
					int noPageToClick = Integer.valueOf(totalPages.substring(0, totalPages.indexOf(" ")));
					// Get specified Column Data from First Page
					for (int i = 1; i < getRowCount(tableLocator) - 1; i++)
						if (getCellData(tableLocator, i + 1, columnName).length() > 30)
							resultCellData.add(getCellData(tableLocator, i + 1, columnName).substring(0, 30));
						else
							resultCellData.add(getCellData(tableLocator, i + 1, columnName));
					// no of paging elements available under particular table
					for (int i = 2; i <= noPageToClick; i++) {
						// Navigate to each page and get the data
						List<WebElement> pagingList = getList(paging);
						int pageToClick = 0;
						for (int k = 0; k < pagingList.size(); k++) {
							// check for next pagging icon to move to next page
							if (i % 5 == 1) {
								if (pagingList.get(k).getText().contains("ï¿½")) {
									pageToClick = k;
									break;
								}
							} else {
								if (pagingList.get(k).getText().equalsIgnoreCase(Integer.toString(i))) {
									pageToClick = k;
									break;
								}
							}
						}
						click(pagingList.get(pageToClick));
						pause(LPDndBaseSuite.waitMedium);
						// Check paging exist on next page or not
						for (int j = 1; j < getRowCount(tableLocator) - 1; j++) {
							if (getCellData(tableLocator, j + 1, columnName).length() > 30)
								resultCellData.add(getCellData(tableLocator, j + 1, columnName).substring(0, 30));
							else
								resultCellData.add(getCellData(tableLocator, j + 1, columnName));
						}
					}
				} else {
					// Get specified Column Data from First Page
					for (int i = 1; i < getRowCount(tableLocator); i++)
						if (getCellData(tableLocator, i + 1, columnName).length() > 30)
							resultCellData.add(getCellData(tableLocator, i + 1, columnName).substring(0, 30));
						else
							resultCellData.add(getCellData(tableLocator, i + 1, columnName));
				}
			} else {
				// Return null data if no data found in row
				RESULT.PASS("NO DATA AVAILABLE", true, ScreenshotType.browser);
				resultCellData.clear();
			}
		} else {
			// Log the result if no Column Found with specified name
			RESULT.FAIL("The Column name is not present in the table.", true, ScreenshotType.browser);
		}
		return resultCellData;
	}

	/**
	 * @Objective Check Date Validations
	 * 
	 * @author vivekp
	 * 
	 * @throws Exception
	 */
	public void dateValidation() throws Exception {

		// Get total no of dateFields in particular page
		List<WebElement> dateField = getList(Shared_OR.calendarTextBox);
		int fromDateIndex = 0;
		int toDateIndex = 0;
		// Get index of From Date Field
		for (int i = 0; i < dateField.size(); i++) {
			if (dateField.get(i).getText().contains("From Date")) {
				fromDateIndex = i + 1;
				break;
			}
		}
		// Get index of To Date Field
		for (int j = 0; j < dateField.size(); j++) {
			if (dateField.get(j).getText().contains("To Date")) {
				toDateIndex = j + 1;
				break;
			}
		}
		// Convert Date Field Locator to String to modified the Locaor
		String dateFieldLocator = Shared_OR.calendarTextBox.toString();
		String datFieldLocator1 = dateFieldLocator.substring(dateFieldLocator.lastIndexOf("//"));
		// Get the Xpath of From Date and To Date Field
		String fromDateLocator = datFieldLocator1 + "[" + fromDateIndex + "]/input[contains(@id,'Sr_Dttxt')]";
		String toDateLocator = datFieldLocator1 + "[" + toDateIndex + "]/input[contains(@id,'Sr_Dttxt')]";
		By fromDateXpath = By.xpath(fromDateLocator);
		By toDateXpath = By.xpath(toDateLocator);
		// Check Validation Alert when From Date > To Date
		String fromDate = getDate(10);
		String toDate = getDate(0);
		setValue(fromDateXpath, fromDate);
		setValue(toDateXpath, toDate);
		click(Shared_OR.Search);
		verifyAlertMsg_AlertAction_PartialText(AlertAction.Accept, "To Date can not be less than From Date.");
		click(toDateXpath);
		// Check Validation when Invalid Month is added
		setValue(fromDateXpath, "13-05-2020");
		setValue(toDateXpath, toDate);
		click(Shared_OR.Search);
		verifyAlertMsg_AlertAction_PartialText(AlertAction.Accept, "Please enter a valid month");
		click(fromDateXpath);
		// Check Validation when Invalid Year is added
		setValue(fromDateXpath, toDate);
		setValue(toDateXpath, "05-05-0200");
		click(Shared_OR.Search);
		verifyAlertMsg_AlertAction_PartialText(AlertAction.Accept,
				"Please enter a valid 4 digit year between 1900 and 2100");
		click(toDateXpath);
		// Check Validation when Invalid Date is entered
		setValue(fromDateXpath, "02-29-2015");
		setValue(toDateXpath, toDate);
		click(Shared_OR.Search);
		verifyAlertMsg_AlertAction_PartialText(AlertAction.Accept, "Please enter a valid day");
		click(toDateXpath);
	}

	/**
	 * Select an option from drop-down**
	 * 
	 * @param dropDownLocator Locator of drop- down element**
	 * @param optionValue     Value attribute of option or visible text of option as
	 *                        String
	 * 
	 * @author kandarpp
	 * 
	 */
	@Override
	public void selectDropDownOption(By dropDownLocator, String optionValue) {

		// variable to hold drop down element
		String elementName = null;
		Select dropDownSelect;
		// in-case of invalid option, store in variable for reporting
		String invalidOption = null;
		// List to store all options of drop-down
		ArrayList<String> allOptionList = new ArrayList<String>();
		try {
			// set drop-down element name
			elementName = dropDownLocator.toString();
			try {
				// wait and locate drop-down element
				// waitForElement(dropDownLocator, 40,
				// WaitType.visibilityOfElementLocated);
				WebElement element = driver.findElement(dropDownLocator);
				dropDownSelect = new Select(element);
				// Retrieve all options web elements as List
				List<WebElement> dropDownOptions = dropDownSelect.getOptions();
				// checking if list contains drop-down options web elements
				if (dropDownOptions != null) {
					for (int i = 0; i < dropDownOptions.size(); i++) {
						allOptionList.add(dropDownOptions.get(i).getText());
					}
					if (allOptionList.contains(optionValue)) {
						invalidOption = optionValue;
						// select desired option from drop down
						dropDownSelect.selectByVisibleText(optionValue);
						RESULT.PASS("Drop down option is selected with specified visible text -'" + optionValue + "'",
								false, ScreenshotType.browser);
					} else {
						invalidOption = optionValue;
						// select desired option based on value attribute
						dropDownSelect.selectByValue(optionValue);
						RESULT.PASS("Drop down option is selected with specified value -'" + optionValue + "'", false,
								ScreenshotType.browser);
					}
				} else {
					RESULT.FAIL("No option is available in drop-down - '" + elementName + "'", false,
							ScreenshotType.browser);
				}
			} catch (NullPointerException e) {
				RESULT.FAIL("Specified option -'" + invalidOption + "' is not available in drop down - '" + elementName
						+ "'", false, ScreenshotType.browser);
			} catch (NoSuchElementException e) {
				RESULT.FAIL("elemenent not found - '" + elementName + "'", false, ScreenshotType.browser);
			}
		} catch (WebDriverException e) {
			RESULT.ERROR(("Exception occurred in selecting option value of drop down '" + elementName + "'"), e, false,
					ScreenshotType.browser);
		}
		dropDownSelect = null;
	}

	/**
	 * @Objective- Method to generate Random String
	 * 
	 * @param length    -Specify length of the String
	 * 
	 * @param stringSet - Specify the Sting set out of which you want to generate
	 *                  random string
	 * 
	 * @return Random String value
	 */
	public String getRandomString(int length, String stringSet) {

		// Convert given String into Character of Array
		char[] chars = stringSet.toCharArray();
		StringBuilder stingBuilder = new StringBuilder();
		Random random = new SecureRandom();
		for (int i = 0; i < length; i++) {
			char c = chars[random.nextInt(chars.length)];
			stingBuilder.append(c);
		}
		String output = stingBuilder.toString();
		return output;
	}

	/**
	 * @Objective: Method to verify Message Content retrieved from Front End and
	 *             Back End
	 * 
	 * @author vivekp
	 * 
	 * @param actualMsgContent- ArrayList of the Front End Message Content
	 * 
	 * @param expectedMsgContent- String Array of Back End Message Content
	 */
	public void verifyMessageContent(ArrayList<String> actualMsgContent, String[] expectedMsgContent) {

		// Verify the Front Message Content with Message Content got from
		// Back end
		if (expectedMsgContent.length == actualMsgContent.size()) {
			for (int i = 0; i < actualMsgContent.size(); i++) {
				if (actualMsgContent.get(i).contains(expectedMsgContent[i]))
					RESULT.PASS("Row:" + (i + 1) + " data mtaches with the Database content.", false,
							ScreenshotType.browser);
				else
					RESULT.FAIL("Row:" + (i + 1) + " data doesn't match with the Database content.", false,
							ScreenshotType.browser);
			}
		} else {
			RESULT.FAIL("Table Data doesn't match with the Database Data", false, ScreenshotType.browser);
		}
	}

	/**
	 * To click the next page number link.
	 * 
	 * @author himanshuk
	 * @return true - If the page is switched. false - If the page is not switched.
	 */
	public boolean nextPage() {

		// Check if the paging is present
		if (!isElementExists(By.xpath(Shared_OR.pagerMsg))) {
			RESULT.INFO("Paging is not present", true, ScreenshotType.browser);
			return false;
		}
		// init page number
		int pageNumber = 1;
		// Get current page number
		if (!isElementExists(Shared_OR.currentPage)) {
			RESULT.FAIL("Failed to find the current page number.", true, ScreenshotType.fullScreen);
			return false;
		} else {
			pageNumber = Integer.parseInt(driver.findElement(Shared_OR.currentPage).getText().trim());
		}
		// Generate next page locator
		By nextPage = By.xpath("//tr[@class='pgr']//a[text()='" + (pageNumber + 1) + "']");
		// Click on next page number
		if (isElementExists(nextPage)) {
			click(nextPage);
			return true;
		} else {
			RESULT.INFO("Failed to find next page link.", false, ScreenshotType.browser);
			return false;
		}
	}

	/**
	 * To click the previous page number link.
	 * 
	 * @author himanshuk
	 * @return true - If the page is switched. false - If the page is not switched.
	 */
	public boolean previousPage() {

		// Check if the paging is present
		if (!isElementExists(By.xpath(Shared_OR.pagerMsg))) {
			RESULT.INFO("Paging is not present", true, ScreenshotType.browser);
			return false;
		}
		// init page number
		int pageNumber = 1;
		// Get current page number
		if (!isElementExists(Shared_OR.currentPage)) {
			RESULT.FAIL("Failed to find the current page number.", true, ScreenshotType.fullScreen);
			return false;
		} else {
			pageNumber = Integer.parseInt(driver.findElement(Shared_OR.currentPage).getText().trim());
		}
		// Generate previous page locator
		By previousPage = By.xpath("//tr[@class='pgr']//a[text()='" + (pageNumber - 1) + "']");
		// Click on previous page number
		if (isElementExists(previousPage)) {
			click(previousPage);
			return true;
		} else {
			RESULT.INFO("Failed to find previous page link.", false, ScreenshotType.browser);
			return false;
		}
	}

	/**
	 * <strong>Objective:</strong> Get all the data from table and return 2D array.
	 * 
	 * @author himanshuk
	 * @param <strong>tableLocator</strong>
	 * @return <strong>data</strong> - 2D array of data from the table
	 */
	public String[][] getTableData(By tableLocator) {

		// Initialize required variables
		String tableXpath = tableLocator.toString().replace("By.xpath: ", "");
		List<String> columnDataList = new ArrayList<String>();
		List<String[]> rowDataList = new ArrayList<String[]>();
		if (isElementExists(tableLocator)) {
			OUTER: do {
				// Generate row xpath
				String rowXpath = tableXpath + "//tr[child::td and contains(@class,'data')]";
				// Get the rowlist
				List<WebElement> rowList = driver.findElements(By.xpath(rowXpath));
				// For all the rows
				for (WebElement rowItem : rowList) {
					// Generate column xpath
					String columnXpath = "./td";
					List<WebElement> columnList = rowItem.findElements(By.xpath(columnXpath));
					int columnCount = columnList.size();
					columnDataList.clear();
					for (WebElement columnItem : columnList) {
						// Add column to row data
						columnDataList.add(columnItem.getText());
					}
					// Add row to table data
					rowDataList.add(columnDataList.toArray(new String[columnCount]));
					RESULT.INFO("Added row " + rowDataList.size(), false, ScreenshotType.browser);
				}
				// Go to next page/ if next page doesn't exist break
				if (!nextPage())
					break OUTER;
			} while (true);
		}
		// go to first page
		// Return the output to desired format
		return rowDataList.toArray(new String[rowDataList.size()][columnDataList.size()]);
	}

	/**
	 * Compare two tables as 2D arrays.
	 * 
	 * @author himanshuk
	 * @param expectedTable - String[][]
	 * @param actualTable   - String[][]
	 * @return <strong>true</strong> - If tables are same.</br>
	 *         <strong>false</strong> - If tables are not same.
	 */
	public boolean compareTables(String[][] expectedTable, String[][] actualTable) {

		boolean isEqual = false;
		if (expectedTable.length != actualTable.length) {
			return isEqual;
		}
		int size = expectedTable.length;
		isEqual = true;
		for (int i = 0; i < size; i++) {
			if (!Arrays.equals(expectedTable[i], actualTable[i])) {
				isEqual = false;
				break;
			}
		}
		return isEqual;
	}

	/**
	 * search an element in table
	 * 
	 * @param tableLocator
	 * @param element
	 * @return boolean returns true if element is found in table returns false if
	 *         element is not found in table
	 * @author ankitas
	 */
	public Boolean searchElementInTable(By tableLocator, By element) {

		try {
			// to get web element that contains total no of pages
			WebElement pages = getList(tableLocator, By.xpath(".//font[@class='header_text']")).get(0);
			By nextPage = By.xpath("//a[text()='›']");
			// to get count of total pages
			int count = Integer.parseInt(pages.getText().replace(" Page(s): ", ""));
			int click;
			// calculates total no. of pages
			if (count % 10 == 0) {
				click = count / 10;
			} else {
				click = (count / 10) + 1;
			}
			// go through all the pages of table and find element if it exists
			for (int i = 0; i < click; i++) {
				for (int j = 1; j <= 10; j++) {
					// wait for data to load
					waitForAJAXLoad(1);
					// navigate to the particular page
					click(getList(tableLocator,
							By.xpath(".//font[@class='header_text']//following-sibling::*[contains(text(),'"
									+ ((i * 10) + j) + "')]")).get(0));
					// if element exists on that page then return true
					if (isElementExists(element)) {
						return true;
					} else {
						// if we are on last page and element doesn't exist then
						// return false
						if (j + (i * 10) == count) {
							return false;
						}
					}
				}
				// click on next arrow
				click(nextPage);
			}
			return false;
		} catch (Exception e) {
			RESULT.FAIL("Exception occured while finding an element in the table " + e.getMessage(), true,
					ScreenshotType.browser);
		}
		return null;
	}

	/**
	 * convert time of 12 hour format to 24 hour format
	 * 
	 * @param time - pass the time in 12 hour format(i.e. 02:00 PM)
	 * 
	 * @return String - returns the time in 24 hour format (i.e. 14:00)
	 */
	public String convertTimeTo24HourFormat(String time) {

		String resultTime[], hour[];
		// split the time as the time format is in 12 hour format
		resultTime = time.split(" ");
		// converting 12 hour format to 24 hour format
		// if time is in PM then add 12 except if it is 12 PM
		if (time.contains("PM") && !(time.contains("12"))) {
			// split by : and add 12
			hour = resultTime[0].split(":");
			hour[0] = String.valueOf(Integer.parseInt(hour[0]) + 12);
			// again append minutes with hours
			resultTime[0] = hour[0] + ":" + hour[1];
		}
		// if time is 12 AM then convert it into 00
		if (time.contains("AM") && (time.contains("12"))) {
			// split by : and replace 12 with 00
			hour = resultTime[0].split(":");
			hour[0] = hour[0].replaceAll("12", "00");
			// again append minutes with hours
			resultTime[0] = hour[0] + ":" + hour[1];
		}
		// return the time of 24 hour format
		return resultTime[0];
	}

	/**
	 * To check if the table is empty.
	 * 
	 * @author himanshuk
	 * @param tableLocator
	 * @return
	 */
	public boolean isTableEmpty(By tableLocator) {

		// Get the row count
		int rowCount = getRowCount(tableLocator);
		// Return if rowcount is not 1
		if (rowCount != 1)
			return false;
		// Match first row text
		List<String> firstRowData = getRowData(tableLocator, 1);
		if (firstRowData.get(0).matches("No .* Available."))
			return true;
		else
			return false;
	}

	/**
	 * To get the total entries count from the table (paging included.)
	 * 
	 * @author himanshuk
	 * @param tableLocator
	 * @return totalRowCount
	 */
	public int getTotalRowCount(By tableLocator) {

		// init rowcount variable
		int rowCount = 0;
		// Check if the table exists
		if (!isElementExists(tableLocator))
			RESULT.FAIL("The table doesn't exist.", true, ScreenshotType.browser);
		// Generate pager xpath
		By pagerLocator = By.xpath(tableLocator.toString().replace("By xpath: ", "") + Shared_OR.pagerMsg);
		// Get the total row count
		if (isElementExists(pagerLocator)) {
			String pagerMsg = getWebElement(pagerLocator).getText();
			// Replace everything other than the entries count
			rowCount = Integer.parseInt(pagerMsg.replaceAll(".*of", "").replaceAll("].*", "").trim());
		} else {
			rowCount = getRowCount(tableLocator) - 1;
		}
		return rowCount;
	}

	/**
	 * To verify the title of the sub window
	 * 
	 * @author sheetald
	 * 
	 * @param subwindowname locator
	 *
	 */
	public void verifySubWindowTitle(String windowName) {

		String subWindowTitle = getTextWebelement(Shared_OR.subWindowTitle);
		if (subWindowTitle.equals(windowName)) {
			RESULT.PASS(windowName + " window is opened successfully.", true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed to open the " + windowName + " window.", true, ScreenshotType.browser);
		}
	}

	/**
	 * To verify the title of the sub window
	 * 
	 * @author Pankajb
	 * 
	 * @param subwindowname locator
	 *
	 */
	public void verifySubWindowTitle(String windowName, By locator) {

		String subWindowTitle = getTextWebelement(locator);
		if (subWindowTitle.equals(windowName)) {
			RESULT.PASS(windowName + " window is opened successfully.", true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed to open the " + windowName + " window.", true, ScreenshotType.browser);
		}
	}

	/**
	 * To get random data of Alphabetical or numeric or alphanumeric
	 * 
	 * @author sheetald
	 * 
	 * @param locator, length, and type of random data
	 *
	 */
	public void setRandomValue(By locator, String type, int length) {

		String value = new String();
		switch (type) {
		case "AN":
			value = RandomStringUtils.randomAlphanumeric(length);
			break;
		case "N":
			value = RandomStringUtils.randomNumeric(length);
			break;
		case "A":
			value = RandomStringUtils.randomAlphabetic(length);
			break;
		}
		WebElement element = driver.findElement(locator);
		element.sendKeys(value);
	}

	/**
	 * To erase data into field
	 * 
	 * @author sheetald
	 * 
	 * @param locator
	 * 
	 * @return
	 */
	public void clearData(By locator) {

		WebElement element = driver.findElement(locator);
		element.clear();
	}

	/**
	 * To close sub window
	 * 
	 * @author sheetald
	 * 
	 * 
	 */
	public void closeSubWindow() {

		click(Shared_OR.cancelButton1);
		pause(2000);
	}

	/**
	 * To get total records available in the page
	 * 
	 * @author sheetald
	 * 
	 * @param locator
	 * 
	 * @return totalRowCount
	 */
	public int getTotalRecords(By tableLocator) {

		// initialise rowcount variable
		int totalRecords = 0;
		// Check if the table exists
		if (!isElementExists(tableLocator))
			RESULT.FAIL("The table doesn't exist.", true, ScreenshotType.browser);
		// click on the last forward step arrow
		click(Shared_OR.stepForwardArrow);
		pause(2000);
		List<WebElement> list = driver.findElements(Shared_OR.PageNumber);
		int listSize = list.size() - 1;
		String lastPageNumber = list.get(listSize).getText();
		int totalPages = Integer.parseInt(lastPageNumber);
		// Click on first page
		click(Shared_OR.stepBackwardArrow);
		pause(4000);
		for (int i = 0; i <= totalPages - 1; i++) {
			int count = getRowCount(tableLocator);
			totalRecords = totalRecords + count;
			// list.get(i).click();
			boolean value = isElementEditable(Shared_OR.forwardArrow);
			if (value == true) {
				click(Shared_OR.forwardArrow);
				pause(3000);
			}
		}
		// Click on first page
		click(Shared_OR.stepBackwardArrow);
		pause(4000);
		return totalRecords;
	}

	/**
	 * To get list of column's head
	 * 
	 * @author sheetald
	 * 
	 * @param tablelocator
	 * 
	 * @return Arraylist
	 */
	public ArrayList<String> getColumnHead(By locator) {

		// Check if the table exists
		if (!isElementExists(locator))
			RESULT.FAIL("The table doesn't exist.", true, ScreenshotType.browser);
		List<WebElement> list = driver.findElements(locator);
		ArrayList<String> columnHeadList = new ArrayList<String>();
		// storing each column name in the list
		for (int i = 0; i <= list.size() - 1; i++) {
			columnHeadList.add(list.get(i).getText());
			if (list.get(i).getText() == "") {
				i++;
			}
		}
		return columnHeadList;
	}

	/**
	 * To get list of column's head
	 * 
	 * @author sheetald
	 * 
	 * @param locator
	 * 
	 * @return Arraylist
	 */
	public ArrayList<String> getColumnTitle(By locator) {

		List<WebElement> list = driver.findElements(locator);
		ArrayList<String> columnTitleList = new ArrayList<String>();
		int j = 1;
		for (int i = 0; i <= list.size() - 1; i++) {
			// String columnHead =
			// (Shared_OR.AllcolumnAHead).toString().replaceAll("By.xpath: ",
			// "");
			// By columnHeadLocator = By.xpath(columnHead + "[" + j + "]");
			// boolean flag = isElementExists(columnHeadLocator);
			// boolean isDropDown = false;
			// if(flag && !isDropDown) {
			String columnName = (Shared_OR.AllcolumnAHead).toString().replace("By.xpath: ", "");
			By columnNameLocator = By.xpath(columnName + "[" + j + "]");
			String data = getTextWebelement(columnNameLocator);
			if (!data.equals("")) {
				columnTitleList.add(data);
			}
			j++;
		}
		// columnTitleList.add(list.get(i).getText());
		// if (list.get(i).getText() == "") {
		// i++;
		return columnTitleList;
	}

	/**
	 * To verify the titla and the contenct of the notification
	 * 
	 * @author sheetald
	 * 
	 * @param expectedTitle   of the notification
	 * 
	 * @param expectedMessage content of the notification
	 * 
	 */
	public boolean verifyNotification123(String expectedTitle, String expectedMessage) {

		// Get the title of the Notification title
		String notificationTitle = getTextOfWebElement(Shared_OR.notificationTitle);
		if (notificationTitle.equals(expectedTitle)) {
			RESULT.PASS("Title of the notification is matched successfully.", true, ScreenshotType.browser);
			// Get the content of notification message
			String notificationMessage = getTextOfWebElement(Shared_OR.notifacationMessage);
			if (notificationMessage.equals(expectedMessage)) {
				RESULT.PASS("Message contenct of the notification is matched successfully.", true,
						ScreenshotType.browser);
			} else {
				RESULT.FAIL("Failed to matched the message content of the notification.", true, ScreenshotType.browser);
			}
			return true;
		} else {
			RESULT.FAIL("failed to matched the title of the Notification.", true, ScreenshotType.browser);
			return false;
		}
	}

	/**
	 * To enter the data to Search fields
	 * 
	 * @author sheetald
	 * 
	 * @param array list of searchData
	 * 
	 */
	public void setDataToSearchFields(ArrayList<String> searchData) {

		// Get the list of the search fields
		List<WebElement> columnlist = getList(Shared_OR.AllcolumnAHead);
		// Enter the data into the searchfields
		for (int i = 0; i <= columnlist.size() - 1; i++) {
			String searchLocator = (Shared_OR.AllcolumnAHead).toString().replace("By.xpath: ", "");
			i = i + 1;
			By searchField = By.xpath(searchLocator + "[" + i + "]//input");
			boolean flag = isElementExists(searchField);
			if (flag == true) {
				click(searchField);
				pause(2000);
				i = i - 1;
				String value = searchData.get(i);
				setValue(searchField, value);
			} else {
				i = i - 1;
			}
		}
	}

	/**
	 * To enter the data to search field
	 * 
	 * @author sheetald
	 * 
	 * @param Arraylist of Search data
	 * 
	 * @param list      of the column names which need to ignore
	 * 
	 * @param column    head locator
	 * 
	 */
	public void setDataToSearchFields(ArrayList<String> searchData, List<String> ignoreColumns, By columnHeadLocator) {

		// Get the list of the search fields
		List<String> columnNames = getColumnHead(columnHeadLocator);
		int i = 1;
		int j = 0;
		for (String name : columnNames) {
			// if(ignoreColumns.contains(name))
			String searchLocator = columnHeadLocator.toString().replace("By.xpath: ", "");
			By searchField = By.xpath(searchLocator + "[" + i + "]//input");
			boolean flag = isElementExists(searchField);
			boolean isDropDown = false;
			for (String field : ignoreColumns) {
				if (name.contains(field)) {
					isDropDown = true;
					break;
				}
			}
			if (flag && !isDropDown && !StringUtils.isEmpty(name)) {
				click(searchField);
				pause(DistricoConstant.VERY_LOW);
				String value = searchData.get(j);
				setValue(searchField, value);
				j++;
			} else {
				// Do nothing
			}
			i++;
		}
	}

	/**
	 * To set the option from drop-down
	 * 
	 * @author sheetald
	 * 
	 * @param        drop-down loactor
	 * 
	 * @param the    option need to select the from the drop-down
	 * 
	 * @param column head locator
	 * 
	 */
	public void setDropDownOptions(By dropDownLocator, String optionValue) {

		ArrayList<String> dropDownOptions = getDropDownOptions(dropDownLocator);
		for (int i = 1; i <= dropDownOptions.size(); i++) {
			String dropDownOptionLocator = dropDownLocator.toString().replace("By.xpath: ", "");
			WebElement dropDownOption = driver.findElement(By.xpath(dropDownOptionLocator + "//li[" + i + "]//span"));
			if (dropDownOption.getText().equals(optionValue)) {
				dropDownOption.click();
				break;
			}
		}
	}

	/**
	 * To get options list of single selection type drop-down
	 * 
	 * @author sheetald
	 * 
	 * @param drop-down locator
	 * 
	 * @return Arraylist
	 */
	@Override
	public ArrayList<String> getDropDownOptions(By dropDownLocator) {

		// Check if the table exists
		if (!isElementExists(dropDownLocator))
			RESULT.FAIL("The table doesn't exist .", true, ScreenshotType.browser);
		// List to store all options of drop-down
		ArrayList<String> allOptionList = new ArrayList<String>();
		// Click on the dropdown locator
		click(dropDownLocator);
		pause(DistricoConstant.LOW);
		String dropDownOptionLocator = dropDownLocator.toString().replace("By.xpath: ", "");
		// Get the list of the
		List<WebElement> dropdownOptions = driver.findElements(By.xpath(dropDownOptionLocator + "//li/span" + " | "
				+ dropDownOptionLocator + "//li/label" + "|" + dropDownOptionLocator + "//li//div"));
		// storing each column name in the list
		for (int i = 0; i <= dropdownOptions.size() - 1; i++) {
			allOptionList.add(dropdownOptions.get(i).getText());
		}
		return allOptionList;
	}

	/**
	 * To get the options list of multi-selection type drop-down
	 * 
	 * @author sheetald
	 * 
	 * @param drop-down locator
	 * 
	 * @return Arraylist
	 */
	// This function is used to get the options of multi-selection type
	// drop-down
	public ArrayList<String> getOptionsOfMultiSelectionDropDown(By dropDownLocator) {

		// Check if the table exists
		if (!isElementExists(dropDownLocator))
			RESULT.FAIL("The table doesn't exist .", true, ScreenshotType.browser);
		// List to store all options of drop-down
		ArrayList<String> allOptionList = new ArrayList<String>();
		String dropDownOptionLocator = dropDownLocator.toString().replace("By.xpath: ", "");
		// Get the list of the drop-down options
		List<WebElement> dropdownOptions = driver.findElements(By.xpath(dropDownOptionLocator + "//li/span" + " | "
				+ dropDownOptionLocator + "//li/label" + "|" + dropDownOptionLocator + "/option"));
		// storing each column name in the list
		for (int i = 0; i <= dropdownOptions.size() - 1; i++) {
			allOptionList.add(dropdownOptions.get(i).getText());
		}
		return allOptionList;
	}

	/**
	 * To check weather the given option is available in the drop-down list.
	 * 
	 * @author sheetald
	 * 
	 * @param       drop-down locator
	 * 
	 * @param value of option
	 * 
	 * @return true - If the given option is available in the drop-down. false - If
	 *         given option is not available in the drop-down
	 */
	public boolean isDropDownOptionsExists1(By dropDownLocator, String optionValue) {

		// for optionValue.size by himanshu
		// Get the list of options in the multiselection pop-up
		ArrayList<String> dropDownOptions = getOptionsOfMultiSelectionDropDown(dropDownLocator);
		// Append separator at the back of each option.
		String value_new = "##";
		for (int i = 0; i <= dropDownOptions.size() - 1; i++) {
			value_new = value_new + dropDownOptions.get(i) + "##";
		}
		return value_new.contains("##" + optionValue + "##");
	}

	// This is the start of the function
	public void selectOptionFromDropDown1(By dropDownLocator, String optionValue) {

		if (isDropDownOptionsExists(dropDownLocator, optionValue)) {
			String dropDownOptionLocator = dropDownLocator.toString().replace("By.xpath: ", "");
			// Get the list available in dropDown
			List<WebElement> dropDownOptions = driver.findElements(By.xpath(dropDownOptionLocator + "//li/span" + " | "
					+ dropDownOptionLocator + "//li/label" + "|" + dropDownOptionLocator + "/option"));
			// click on given option from dropdown
			for (int i = 0; i <= dropDownOptions.size() - 1; i++) {
				String optionLabel = "##" + dropDownOptions.get(i).getText() + "##";
				if (optionLabel.equals("##" + optionValue + "##")) {
					dropDownOptions.get(i).click();
					RESULT.PASS("Successfully to select the " + optionValue + " option from the dropdown.", true,
							ScreenshotType.browser);
					break;
				}
			}
		} else {
			RESULT.FAIL("Failed because " + optionValue + " is not available in the list", true,
					ScreenshotType.browser);
		}
	}

	public void selectOptionFromMultiSelectionPopup(By dropDownLocator, String optionValue) {

		if (isDropDownOptionsExists(dropDownLocator, optionValue)) {
			String dropDownOptionLocator = Shared_OR.multipleSelectionPopUp.toString().replace("By.xpath: ", "");
			// Get the list available in multiselection pop-up
			List<WebElement> multiSelectionOptions = driver.findElements(By.xpath(dropDownOptionLocator + "//li/span"
					+ " | " + dropDownOptionLocator + "//li/label" + " | " + dropDownOptionLocator + "/option"));
			// storing each column name in the list
			for (int i = 0; i <= multiSelectionOptions.size() - 1; i++) {
				String optionValue_01 = multiSelectionOptions.get(i).getText();
				if ((optionValue_01).equals(optionValue)) {
					// if
					// (multiSelectionOptions.get(i).getText().equals(optionValue))
					// {
					multiSelectionOptions.get(i).click();
					pause(DistricoConstant.LOW);
					RESULT.PASS("Successfully select " + optionValue + "from the multiselection Pop-up ", true,
							ScreenshotType.browser);
					break;
				}
			}
			RESULT.PASS("Successfully checked that " + optionValue + " is available in the list", true,
					ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because " + optionValue + " is not available in the list", true,
					ScreenshotType.browser);
		}
	}

	// function added on 28th august
	public void setMultipleDropDownOptions(By dropDownLocator, String optionValue) {

		// click(dropDownLocator);
		// click(Shared_OR.multiSelectionSearchBox);
		// pause(DistricoConstant.VERY_LOW);
		// setValue(Shared_OR.multiSelectionSearchBox, optionValue);
		// pause(DistricoConstant.LOW);
		ArrayList<String> dropDownOptions = getMultipleDropDownOptions(dropDownLocator);
		for (int i = 0; i < dropDownOptions.size(); i++) {
			WebElement mutltiSelectionPopUpLocator = driver.findElement(Shared_OR.multipleSelectionPopUp);
			String dropDownOptionLocator = mutltiSelectionPopUpLocator.toString().replace("By.xpath: ", "");
			WebElement multiSelectionOptions = driver
					.findElement(By.xpath(dropDownOptionLocator + "[" + (i + 1) + "]//label"));
			if (multiSelectionOptions.getText().equals(optionValue)) {
				multiSelectionOptions.click();
				click(dropDownLocator);
				break;
			}
		}
	}

	// function added on 28th august
	public ArrayList<String> getMultipleDropDownOptions(By dropDownLocator) {

		// Check if the table exists
		if (!isElementExists(dropDownLocator))
			RESULT.FAIL("The table doesn't exist .", true, ScreenshotType.browser);
		// List to store all options of drop-down
		ArrayList<String> allOptionList = new ArrayList<String>();
		// Click on the dropdown locator
		click(dropDownLocator);
		pause(DistricoConstant.LOW);
		// Get the list available in multiselection pop-up
		List<WebElement> list = driver.findElements(Shared_OR.multipleSelectionPopUp);
		// WebElement mutltiSelectionPopUpLocator =
		// driver.findElement(Shared_OR.multipleSelectionPopUp);
		System.out.println("hi");
		String dropDownOptionLocator = Shared_OR.multipleSelectionPopUp.toString().replace("By.xpath: ", "");
		List<WebElement> multiSelectionOptions = driver.findElements(By.xpath(dropDownOptionLocator + "//label"));
		// storing each column name in the list
		for (int i = 0; i <= list.size() - 1; i++) {
			allOptionList.add(multiSelectionOptions.get(i).getText());
		}
		return allOptionList;
	}

	@Override
	public By getLocator(By elementLocator, String... strReplace) {

		// variable to hold count of wild card %s in locator string
		int wildCardCount;
		// get count of string arguments
		int countOfStringArguments = strReplace.length;
		try {
			// get string value of element locator containing two wild cards %s
			String strElementLocator = String.valueOf(elementLocator);
			// get no of wild card '%s' in locator string
			wildCardCount = (strElementLocator.length() - strElementLocator.replace("%s", "").length()) / 2;
			// check for two wild cards
			if (wildCardCount == countOfStringArguments) {
				// replace wild cards '%s' with string[]
				strElementLocator = String.format(strElementLocator, strReplace);
				// convert locator string to By type
				elementLocator = locatorParser(strElementLocator);
			} else {
				RESULT.FAIL("No of wild cards and No of strings passed in argument is not matched", false,
						ScreenshotType.browser);
				elementLocator = null;
			}
		} catch (WebDriverException e) {
			RESULT.ERROR(("Exception occurred while getting dynamic locator"), e, false, ScreenshotType.browser);
		}
		// return dynamic element locator
		return elementLocator;
	}

	@Override
	public By locatorParser(String strLocator) {

		// variable to hold element locator
		By elementLocator = null;
		// get locator string excluding locator type(i.e. xpath, id etc)
		try {
			String locatorString = strLocator.substring(strLocator.indexOf(":") + 1, strLocator.length()).trim();
			// get locator type string (i.e. xpath, id etc)
			String locatorType = strLocator.substring(strLocator.indexOf(".") + 1, strLocator.indexOf(":")).trim();
			// based on locator type string it returns element locator object
			switch (locatorType) {
			// returns By id locator
			case "id":
				elementLocator = By.id(locatorString);
				break;
			// returns By name locator
			case "name":
				elementLocator = By.name(locatorString);
				break;
			// returns By partialLinkText locator
			case "partialLinkText":
				elementLocator = By.partialLinkText(locatorString);
				break;
			// returns By className locator
			case "className":
				elementLocator = By.className(locatorString);
				break;
			// returns By cssSelector locator
			case "cssSelector":
				elementLocator = By.cssSelector(locatorString);
				break;
			// returns By cssSelector locator
			case "tagName":
				elementLocator = By.tagName(locatorString);
				break;
			// returns By linkText locator
			case "linkText":
				elementLocator = By.linkText(locatorString);
				break;
			// returns By xpath locator
			case "xpath":
				elementLocator = By.xpath(locatorString);
				break;
			// invalid locator type
			default:
				RESULT.FAIL("Invalid locator type", false, ScreenshotType.browser);
			}
		} catch (WebDriverException e) {
			RESULT.ERROR(("Exception occurred while parsing locator"), e, false, ScreenshotType.browser);
		}
		return elementLocator;
	}

	/**
	 * Java enter value on WebElement
	 * 
	 * @param locator locator of WebElement
	 * 
	 * @param value   value to be entered in the locater
	 * @author rajatg
	 * 
	 */
	@Override
	public void javaScriptSetValue(By locator, String value) {

		JavascriptExecutor executor;
		// set locator name
		String elementName = null;
		try {
			elementName = locator.toString();
			// check element is editable or not
			boolean isEditable = isElementEditable(locator);
			if (isEditable) {
				try {
					// get WebElement of locator
					WebElement element = driver.findElement(locator);
					// Create an object of JavaScriptExector Class
					executor = (JavascriptExecutor) driver;
					// clear value
					executor.executeScript("arguments[0].setAttribute('value', '')", element);
					// set value
					executor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
					// get the entered value
					String getText = getElementAttribute(locator, "value");
					if (getText.equalsIgnoreCase(value)) {
						RESULT.PASS(value + " is entered in '" + elementName + "' Text Field", false,
								ScreenshotType.browser);
					} else {
						RESULT.FAIL(getText + " is entered in '" + elementName + "' Text Field instead of " + value,
								false, ScreenshotType.browser);
					}
				} catch (WebDriverException e) {
					RESULT.ERROR("Exception occurred in Java setting value : '" + value + "'  in element : '"
							+ elementName + "'", e, false, ScreenshotType.browser);
				}
			}
		} catch (NoSuchElementException e) {
			RESULT.FAIL("Element not found - " + elementName, false, ScreenshotType.browser);
		}
		executor = null;
	}// end of javaScriptSetValue

	public void verifyRecord(ArrayList<String> recordList, List<String> ignoreColumns, By columnHeadLocator) {

		// Get the record of first row from list
		ArrayList<String> recordDataList = getFirstRowData(ignoreColumns, columnHeadLocator);
		pause(2000);

		// Get the list of the column names in page.
		ArrayList<String> columnName = getColumnHead(ignoreColumns, columnHeadLocator);
		for (int i = 0; i <= columnName.size() - 1; i++) {
			if (recordDataList.get(i).replace(String.valueOf((char) 160), "").equalsIgnoreCase(recordList.get(i))) {
				RESULT.PASS(columnName.get(i) + " column is matched successfully.", true, ScreenshotType.browser);
			} else {
				RESULT.FAIL("Failed to match " + columnName.get(i) + " column.", true, ScreenshotType.browser);
			}
		}
	}

	public void verifyRecord(ArrayList<String> recordList) {

		// Get the record of first row from list
		ArrayList<String> recordDataList = getRowData(Shared_OR.listOfRecords, 1);
		// Get the list of the column names in page.
		ArrayList<String> columnName = getColumnTitle(Shared_OR.AllcolumnAHead);
		for (int i = 0; i <= columnName.size() - 1; i++) {
			if (recordDataList.get(i).equals(recordList.get(i))) {
				RESULT.PASS(columnName.get(i) + " column is matched successfully.", true, ScreenshotType.browser);
			} else {
				RESULT.FAIL("Failed to mathched " + columnName.get(i) + " column.", true, ScreenshotType.browser);
			}
		}
	}

	/**
	 * To get list of column's head
	 * 
	 * @author sheetald
	 * 
	 * @param tablelocator, the name of the columns which should be ignore and
	 *        columnHeadLocator
	 * 
	 * @return Arraylist
	 */
	// This functions give the list of columns
	public ArrayList<String> getColumnHead(List<String> ignoreColumns, By columnHeadLocator) {

		// Storing the data of first row into the array list
		ArrayList<String> recordColumnList = new ArrayList<String>();
		// Get the count of columns in the list
		List<String> columnNames = getColumnHead(columnHeadLocator);
		int i = 1;
		for (String name : columnNames) {
			String columnHead = columnHeadLocator.toString().replaceAll("By.xpath: ", "");
			By columnHeadLabelLocator = By.xpath(columnHead + "[" + i + "]");
			boolean flag = isElementExists(columnHeadLabelLocator);
			boolean isDropDown = false;
			for (String field : ignoreColumns) {
				if (name.contains(field)) {
					isDropDown = true;
					break;
				}
			}
			if (flag && !isDropDown && !StringUtils.isEmpty(name)) {
				String columnName = columnHeadLocator.toString().replace("By.xpath: ", "");
				By columnNameLocator = By.xpath(columnName + "[" + i + "]");
				String data = getTextWebelement(columnNameLocator);
				recordColumnList.add(data);
			} else {
				// Do nothing
			}
			i++;
		}
		return recordColumnList;
	}

	public ArrayList<String> getFirstRowData(List<String> ignoreColumns, By columnHeadLocator) {

		// Storing the data of first row into the array list
		ArrayList<String> recordDataList = new ArrayList<String>();
		// Get the count of columns in the list
		List<String> columnNames = getColumnHead(columnHeadLocator);
		int i = 1;
		for (String name : columnNames) {
			String columnHead = columnHeadLocator.toString().replaceAll("By.xpath: ", "");
			By columnHeadLabelLocator = By.xpath(columnHead + "[" + i + "]");
			boolean flag = isElementExists(columnHeadLabelLocator);
			boolean isDropDown = false;
			for (String field : ignoreColumns) {
				if (name.contains(field)) {
					isDropDown = true;
					// i++;
					break;
				}
			}
			if (flag && !isDropDown && !StringUtils.isEmpty(name)) {
				String firstRowLocator = (Shared_OR.firstRowDataLocator).toString().replace("By.xpath: ", "");
				By dataLocator = By.xpath(firstRowLocator + "[" + i + "]/div");

				WebElement element = driver.findElement(dataLocator);

				// Create an object of JavaScriptExector Class
				JavascriptExecutor executor = (JavascriptExecutor) driver;

				// Get the data
				String data = String.valueOf(executor.executeScript("return arguments[0].textContent", element));

				// Remove '&nbsp' from data
				String data1 = data.replaceAll(String.valueOf((char) 160), " ");
				recordDataList.add(data1.trim());
			} else {
				// Do nothing
			}
			i++;
		}
		return recordDataList;
	}

	public void clearSearchCriteria() {

		List<WebElement> searchList = getList(Shared_OR.searchFields);
		for (int i = 0; i <= searchList.size() - 1; i++) {
			searchList.get(i).click();
			String data = searchList.get(i).getAttribute("value");
			int TotalChar = data.length();
			for (int j = 0; j <= TotalChar - 1; j++) {
				searchList.get(i).sendKeys(Keys.END);
				searchList.get(i).sendKeys(Keys.BACK_SPACE);
			}
			pause(3000);
			searchList.get(i).sendKeys(Keys.TAB);
		}
	}

	public void verifyPageSelector() {

		// check that by default value of page selector is set to 20
		String defaultPageSelectorValue = getTextWebelement(Shared_OR.pageSelectorDefaultValue);
		if (defaultPageSelectorValue.equals("20")) {
			// Get the count of total pages
			List<WebElement> listIf20 = driver.findElements(Shared_OR.PageNumber);
			int totalPagesIfPageSel20 = listIf20.size();
			if (totalPagesIfPageSel20 >= 1) {
				// If there are more than one pages then get the count of rows
				// in first page
				int recordCountinPageIf20 = getRowCount(Shared_OR.listOfRecords);
				if (recordCountinPageIf20 == 20) {
					RESULT.PASS(
							"Successfully check that in first page 20 records are available if the pages count is more than one.",
							true, ScreenshotType.browser);
				} else {
					RESULT.FAIL(
							"Failed because in first page 20 records are not available if the pages count is more than one.",
							true, ScreenshotType.browser);
				}
			} else {
				int recordCountinPageIf20 = getRowCount(Shared_OR.listOfRecords);
				if (recordCountinPageIf20 >= 20) {
					RESULT.PASS(
							"Successfully check that in first page there are either 20 or less than 20 records are avilable when page count is one.",
							true, ScreenshotType.browser);
				} else {
					RESULT.FAIL(
							"Failed because the record count in first page is greater than 20 when page count is one",
							true, ScreenshotType.browser);
				}
			}
			RESULT.PASS("Successfully check that the default value of page selector is set to 20", true,
					ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because default value of page selector is not set to 20", true, ScreenshotType.browser);
		}
		// Get the Total records available in all pages when page selector value
		// is set
		// to 20
		int totalRecordsIfPageSelectorIs20 = getTotalRecords(Shared_OR.listOfRecords);
		// Set the page selector value to 10
		setDropDownOptions(Shared_OR.pageNavigatorDropDown, "10");
		pause(3000);
		// Get the total records in first page when page selector value is set
		// to 10
		// int totalPagesIfNavIs10 = getRowCount(Shared_OR.listOfRecords);
		List<WebElement> listIf10 = driver.findElements(Shared_OR.PageNumber);
		int totalPagesIfPageSell0 = listIf10.size();
		if (totalPagesIfPageSell0 >= 1) {
			int recordCountinPageIf10 = getRowCount(Shared_OR.listOfRecords);
			if (recordCountinPageIf10 == 10) {
				RESULT.PASS(
						"Successfully check that 10 records are available in first page if page selector value is set to 10.",
						true, ScreenshotType.browser);
			} else {
				RESULT.FAIL(
						"Failed because 10 records are not available in first page if page selector value is set to 10.",
						true, ScreenshotType.browser);
			}
		}
		// Get the Total records available in all pages when page selector value
		// is set
		// to 10
		int totalRecordsIfPageSelectorIs10 = getTotalRecords(Shared_OR.listOfRecords);
		if (totalRecordsIfPageSelectorIs10 == totalRecordsIfPageSelectorIs20) {
			RESULT.PASS("The count of records is same when page selector value is set to 10 ", true,
					ScreenshotType.browser);
		} else {
			RESULT.FAIL("Count is not matched when page selector is set to 10", true, ScreenshotType.browser);
		}
	}

	/**
	 * This functions enter the data to drop-down type field verify whether the
	 * option is available in drop-down and select the option
	 **/
	public void setDataToSuggestionTypeDropDown(By locator, String value) {

		// Get the list of option showing in the suggestion
		By dropDownLocator = (Shared_OR.suggestion);
		ArrayList<String> dropDownOptions = getSuggstionOptinList(dropDownLocator, locator, value);
		// Verify weather the given value is exist in the list and if exist then
		// click
		// on it.
		for (int i = 1; i <= dropDownOptions.size(); i++) {
			String suggestion = dropDownLocator.toString().replace("By.xpath: ", "");
			WebElement suggestionOptions = driver.findElement(
					By.xpath(suggestion + "//li[" + i + "]//div" + " | " + suggestion + "//li[" + i + "]//span"));
			String optionName = suggestionOptions.getText();
			if (suggestionOptions.getText().contains(value)) {
				suggestionOptions.click();
				RESULT.PASS("Successfully" + value + " option is selected from Suggestion.", true,
						ScreenshotType.browser);
				break;
			}
		}
	}

	public void setDataToSuggestionTypeDropDown(By locator, String value, By dropDownLocator) {

		// Get the list of option showing in the suggestion
		ArrayList<String> dropDownOptions = getSuggstionOptinList(dropDownLocator, locator, value);
		// Verify weather the given value is exist in the list and if exist then
		// click
		// on it.
		for (int i = 1; i <= dropDownOptions.size(); i++) {
			String suggestion = dropDownLocator.toString().replace("By.xpath: ", "");
			WebElement suggestionOptions = driver.findElement(By.xpath(suggestion + "//li[" + i + "]//div"));
			String optionName = suggestionOptions.getText();
			if (suggestionOptions.getText().contains(value)) {
				suggestionOptions.click();
				RESULT.PASS("Successfully" + value + " option is selected from Suggestion.", true,
						ScreenshotType.browser);
				break;
			}
		}
	}

	/**
	 * This functions return the list of suggestion option
	 **/
	public ArrayList<String> getSuggstionOptinList(By dropDownLocator, By locator, String value) {

		click(locator);
		pause(DistricoConstant.VERY_LOW);
		setValue(locator, value);
		pause(DistricoConstant.MODERATE);
		// Check if the suggestion is showing or not.
		if (!isElementExists(locator)) {
			RESULT.FAIL("Suggestion list is not sohwing", true, ScreenshotType.browser);
		}
		// List to store all options of drop-down
		ArrayList<String> allOptionList = new ArrayList<String>();
		String dropDownOptionLocator = dropDownLocator.toString().replace("By.xpath: ", "");
		// Get the list of the
		List<WebElement> dropdownOptions = driver.findElements(By.xpath(dropDownOptionLocator + "//li/span" + " | "
				+ dropDownOptionLocator + "//li/label" + "|" + dropDownOptionLocator + "//li//div"));
		// storing each column name in the list
		for (int i = 0; i <= dropdownOptions.size() - 1; i++) {
			allOptionList.add(dropdownOptions.get(i).getText());
		}
		return allOptionList;
	}

	/**
	 * This function Checks weather button or icon is enable or not and if the
	 * button is enabled then click on it.
	 **/
	public void verifyAndClickOnButtonIfEnable(By locator) {

		boolean value = isElementEnabled(locator);
		String buttonText = getTextWebelement(locator);
		if (value) {
			RESULT.PASS("Successfully checked that " + buttonText + " button is enbale after addind mandatory fields",
					true, ScreenshotType.browser);
			javaScriptClick(locator);
		} else {
			RESULT.FAIL("Failed because " + buttonText + "button is not enable after adding mandatory fields", true,
					ScreenshotType.browser);
		}
	}

	/** This function checks weather button or icon is enable or not **/
	public void verifyIfButtonisDisable(By locator) {

		boolean flag = isElementEnabled(locator);
		if (!flag) {
			RESULT.PASS("Successfully checked that" + locator + " button is not enbale without addind mandatory fields",
					true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because" + locator + " button is enable without addind mandatory fields", true,
					ScreenshotType.browser);
		}
	}

	/** This function return the date in proper format i.e. 01Jan18 **/
	public String getFormatedDate(int noDays) {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, noDays);
		Date date = cal.getTime();
		cal.setTime(date);
		String expDate = dateFormat.format(date);
		Date myDate;
		try {
			myDate = dateFormat.parse(expDate);
			String finalDate = new SimpleDateFormat("ddMMMyy").format(myDate);
			System.out.println("Date is " + finalDate);
			return finalDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void calendarSetDate(By calendearIconLocator, int s_day, By dateField) {

		boolean flag = isElementDisplayed(calendearIconLocator);
		String dateToSet = getFormatedDate(s_day);
		if (flag) {
			setValue(dateField, dateToSet);
			pause(DistricoConstant.VERY_LOW);
			RESULT.PASS("Calendar is enabled.", false, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Calendar is disabled.", false, ScreenshotType.browser);
		}
	}

	/**
	 * To scroll to given element inside browser default window using By locator
	 * 
	 * @param locator: By
	 */
	@Override
	public void jsScrollToElement(By locator) {

		JavascriptExecutor executor;
		try {
			// Create instance of Javascript executor
			executor = (JavascriptExecutor) driver;
			try {
				// Identify the WebElement which will appear after scrolling
				// down
				WebElement element = driver.findElement(locator);
				// now execute query which actually will scroll until that
				// element is not
				// appeared on page.
				executor.executeScript(
						"arguments[0].scrollIntoView({behavior: \"instant\", block: \"center\", inline: \"center\"});",
						element);
				pause(DistricoConstant.MODERATE);
				RESULT.PASS("Scrolled to given locator - " + locator, false, ScreenshotType.browser);
			} catch (NoSuchElementException e1) {
				RESULT.FAIL("Given element is not available to scroll to", true, ScreenshotType.browser);
			}
		} catch (WebDriverException e) {
			RESULT.FAIL("Failed to scroll to " + locator, false, ScreenshotType.browser);
		}
		executor = null;
	}// end of jsScrollToElement

	// This function add by sheetal on 10th september 2018
	public void calendarSetDate_01(By calendearIconLocator, String s_day, By dateField) throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		Date date = dateFormat.parse(s_day);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String month = new SimpleDateFormat("MM").format(date);
		// System.out.println(new
		// SimpleDateFormat("MMM").format(cal.getTime()));
		String year = new SimpleDateFormat("yy").format(date);
		String day = new SimpleDateFormat("dd").format(date);
		String dateToSet = day + month + year;
		// System.out.println("Date to set in Start Date field is " +
		// dateToSet);
		boolean flag = isElementDisplayed(calendearIconLocator);
		if (flag) {
			setValue(dateField, dateToSet);
			pause(DistricoConstant.VERY_LOW);
			RESULT.PASS("Calendar is enabled.", false, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Calendar is disabled.", false, ScreenshotType.browser);
		}
	}

	// This function is use to select the date from the calender
	public void selectDateFromCalender(By calenderIconLocator, int s_day) {

		click(calenderIconLocator);
		pause(DistricoConstant.VERY_LOW);
		// Check if the calendar is opened or not.
		boolean flag = isElementDisplayed(Shared_OR.calendar);
		if (flag) {
			pause(DistricoConstant.VERY_LOW);
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, s_day);
			Date date = cal.getTime();
			cal.setTime(date);
			String expDate = dateFormat.format(date);
			Date myDate;
			try {
				myDate = dateFormat.parse(expDate);
				String year = new SimpleDateFormat("yyyy").format(myDate);
				String month = new SimpleDateFormat("MMMMMMMM").format(myDate);
				String dateToSelect = new SimpleDateFormat("dd").format(myDate);
				String[] as_date = dateToSelect.split("");
				if (as_date[0].equals("0")) {
					dateToSelect = new SimpleDateFormat("d").format(myDate);
				}
				// Select the year from year drop-down
				By yearLocator = Shared_OR.yearLocator;
				click(yearLocator);
				pause(DistricoConstant.LOW);
				selectOptionFromDropDown(yearLocator, year);
				// Select the month from the drop-down
				By monthLocator = Shared_OR.monthLocator;
				click(monthLocator);
				pause(DistricoConstant.LOW);
				selectOptionFromDropDown(monthLocator, month);
				// int dateToSelect01 = Integer.parseInt(dateToSelect);
				// select the date from the calendar
				By calendarDate = getLocator(Shared_OR.dateLocator, dateToSelect);
				click(calendarDate);
				RESULT.PASS("Date is selected successfully from the calendar.", true, ScreenshotType.browser);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			RESULT.FAIL("Failed because calender is not opened.", true, ScreenshotType.browser);
		}
	}

	// This function verify the title of the full page pop-up
	public void verifyFullPagePopUpTitle(By locator, String popUptitle) {

		String title = getTextWebelement(locator);
		if (title.equals(popUptitle)) {
			RESULT.PASS(popUptitle + " pop-up is opened successfully.", true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed to open the " + popUptitle + " page.", true, ScreenshotType.browser);
		}
	}

	// This function verify the title of the page.
	public void verifyTitleOfPage(String pageTitle) {

		String title = driver.getTitle();
		if (title.toLowerCase().contains(pageTitle.toLowerCase())) {
			RESULT.PASS(pageTitle + " pop-up is opened successfully.", true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed to open the " + pageTitle + " page.", true, ScreenshotType.browser);
		}
	}

	/*
	 * public boolean isAttributeExist(By locator, String attribute) {
	 * 
	 * WebElement element;
	 * 
	 * //Set the Element Name try { //Get the webElement locator element =
	 * driver.findElement(locator);
	 * 
	 * //Get the value of attribute value = element. } return false;
	 * 
	 * }
	 */
	public void selectAllChkBoxForShowColumns() {

		// click on show columns
		click(Shared_OR.showColumnIcon);
		pause(DistricoConstant.LOW);
		// Click on SelectAll Check box
	}

	/**
	 * To get days count between the start date and end date
	 * 
	 * @author sheetald
	 * 
	 * @param start Date
	 * 
	 * @param End   Date
	 */
	public short calculatePeriod(String dateStart, String dateStop) {

		SimpleDateFormat format = new SimpleDateFormat("ddMMMyy");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long diff = d2.getTime() - d1.getTime();
		short diffInDays = (short) TimeUnit.MILLISECONDS.toDays(diff);
		short actualDateDiff = (short) (diffInDays + 1);
		System.out.println("Difference = " + (diffInDays + 1));
		return actualDateDiff;
	}
	// end of calculatePeriod method

	/**
	 * To get days count between the start date and end date
	 * 
	 * @author sheetald
	 * 
	 * @param table            locator
	 * 
	 * @param column           Name
	 * 
	 * @param dateFieldlocator
	 * 
	 * 
	 * @return date showing in column
	 * 
	 */
	public String getDateFromcolumn(By tablelocator, String columnName, By dateFieldLocator) {

		List<String> tableHeaderElement = getColumnHead(tablelocator);
		// Get specified column index
		int columnIndex = -1;
		for (int i = 0; i < tableHeaderElement.size(); i++) {
			if (tableHeaderElement.get(i).equals(columnName)) {
				columnIndex = i + 1;
				break;
			}
		}
		String columnIdx = Integer.toString(columnIndex);
		// Get the date from column
		By dateLocator = getLocator(dateFieldLocator, columnIdx);
		String date = getElementAttribute(dateLocator, "value");
		return date;
	}

	/**
	 * To get module name from the url of the page (16 jan 19)
	 * 
	 * @author sheetald
	 * 
	 * @return module name
	 * 
	 */
	public String getModuleName() {

		String urlData = getCurrentURL();
		String[] values = urlData.split("/");
		String moduleName = values[2];
		// String moduleName = values[values.length - 1];
		return moduleName;
	}

	/**
	 * This method is used to verify if button is disabled or not using its locator
	 * 
	 * @author sheetald
	 * 
	 * @param locator This is the locator of button of which one wants to find the
	 *                disability.
	 */
	public void verifyIfButtonisDisabled(By locator) {

		boolean flag = isElementEnabled(locator);
		String button = getTextWebelement(locator);
		if (!flag) {
			RESULT.PASS("Successfully Checked that by default " + button + " button is disabled", true,
					ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed because by default " + button + " button is not disabled", true,
					ScreenshotType.browser);
		}
	}

	/**
	 * This method is used to clear entered input in input field
	 * 
	 * @author sheetald
	 * 
	 * @param locator This is the locator of input field
	 */
	public void clearInput(By locator) {

		// click on locator
		click(locator);

		// select all and then backspace
		driver.findElement(locator).sendKeys(Keys.CONTROL, "a");
		pause(DistricoConstant.VERY_LOW);
		driver.findElement(locator).sendKeys(Keys.BACK_SPACE);
		pause(DistricoConstant.VERY_LOW);
		RESULT.PASS("Cleared input!", true, ScreenshotType.browser);

	}

	/**
	 * This method to hit on the enter button
	 * 
	 * @author sheetald
	 * 
	 * @param locator This is the locator of input field
	 */
	public void pressEnterButton(By locator) {
		driver.findElement(locator).sendKeys(Keys.ENTER);
		pause(DistricoConstant.VERY_LOW);
	}

	/**
	 * This method canbe used for scrolling horizontally for element to display
	 * 
	 * @author dhruvilb
	 * 
	 * @param locator      This is the locator of element which is not displayed in
	 *                     the grid
	 * @param tableLocator This is the locator of table grid(enter locator for Table
	 *                     data area except Table head area)
	 */
	public void scrollHorizontalForElementToDisplay(By locator, By tableLocator) {

		boolean isElementDisplayed;

		int i = 0;

		click(tableLocator);

		do {

			keyPress(Keys.ARROW_RIGHT);
			isElementDisplayed = isElementDisplayed(locator);
			i++;
			if (i == 100) {
				System.out.println("Element not found on left side!");
				do {

					keyPress(Keys.ARROW_LEFT);
					isElementDisplayed = isElementDisplayed(locator);
					i++;
					if (i == 200) {
						System.out.println("Element not found on right side!");
						break;
					}
				} while (!isElementDisplayed);

				break;
			}
		} while (!isElementDisplayed);

		pause(1000);
	}

	/**
	 * This method can be used to show all columns in grid
	 * 
	 * @author dhruvilb
	 */
	public void clickOnShowAllColumns() {

		pause(DistricoConstant.MODERATE);

		// click on show column icon
		click(By.xpath("//div[@title='Show Columns']//following-sibling::div//span"));

		int columnCount = getList(By.xpath("//div[@class='ui-multiselect-items-wrapper']//li")).size();
		int selectedColumns;
		try {
			selectedColumns = getList(By.xpath("//div[contains(@class,'ui-state-active')]")).size();
		} catch (NullPointerException e) {
			selectedColumns = 0;
		}

		if (selectedColumns != (columnCount + 1)) {
			// select all checkbox
			click(By.xpath(
					"//div[contains(@class,'ui-multiselect-header')]/div[@class='ui-chkbox ui-widget ng-star-inserted']"));
			pause(2000);
		}

		// close the show column icon
		click(By.xpath("//div[@title='Show Columns']//following-sibling::div//span"));
	}

	/**
	 * This method is used to apply implicit wait
	 * 
	 * @author dhruvilb
	 * 
	 * @param timeOut This is the wait for timeout in miliseconds
	 */
	public void waitImplicitly(int timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.MILLISECONDS);
	}

	/**
	 * This method is used to apply explicit wait using condition and locator for
	 * particular element
	 * 
	 * @author dhruvilb
	 * 
	 * @param timeOut   This is the timeout in miliseconds
	 * @param condition This is the condition for element
	 * @param locator   This is the locator of element
	 */
	public void waitExplicitly(int timeOut, String condition, By locator) {

		WebDriverWait wait = new WebDriverWait(driver, timeOut);

		switch (condition) {

		// wait until visibility of element located
		case "isElementDisplayed":
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return;

		case "isElementEnabled":
			WebElement element = getWebElement(locator);
			wait.until((ExpectedCondition<Boolean>) driver -> element.isEnabled());

		default:
			RESULT.FAIL("Entered Wait condition'" + condition + "'is invalid!", false, ScreenshotType.browser);

		}

	}

	public void selectItemsFromMultiselectDropdown(String selectInput) {

		// verify if multiselect dialog is open or not
		boolean visibilityOfDialog = isElementDisplayed(
				By.xpath("//p-multiselect//div[contains(@style,'display: block')]"));

		if (visibilityOfDialog == true) {
			RESULT.PASS("Multiselect Dialogue is Visible!", true, ScreenshotType.browser);

			// selecting the items one by one from the list of items
			String[] nameOfItems = selectInput.split(",");
			for (String item : nameOfItems) {

				// enter the item name in search input
				WebElement searchItem = getWebElement(
						By.xpath("//p-multiselect//div[contains(@style,'display: block')]/div/div[2]/input"));
				searchItem.sendKeys(item);

				// check if result shown for searched item
				boolean itemAvailable = isElementDisplayed(By.xpath(
						"//p-multiselect//div[contains(@style,'display: block')]/div[2]/ul/li/label[text()='Gujarat']"));
				if (itemAvailable == true) {
					// select item
					click(By.xpath(
							"//p-multiselect//div[contains(@style,'display: block')]/div[2]/ul/li[contains(@style,'display: block')]/div/div[2]"));
				} else {
					RESULT.FAIL("Input " + item + " is not available in list!", true, ScreenshotType.browser);
				}

				// clear the input
				searchItem.sendKeys("");
			}

		} else {
			RESULT.FAIL("Multiselect Dialogue is not shown!", true, ScreenshotType.browser);
		}
	}

	public void selectOptionFromDropDown(By dropDownLocator, String optionValue) {

		if (isDropDownOptionsExists(dropDownLocator, optionValue)) {
			// Get the list available in dropDown
			List<WebElement> dropDownOptions = driver.findElements(dropDownLocator);

			// click on given option from drop-down
			for (int i = 0; i <= dropDownOptions.size() - 1; i++) {
				String optionLabel = "##" + dropDownOptions.get(i).getText() + "##";
				if (optionLabel.equals("##" + optionValue + "##")) {
					dropDownOptions.get(i).click();
					pause(DistricoConstant.LOW);
					RESULT.PASS("Successfully to select the " + optionValue + " option from the dropdown.", true,
							ScreenshotType.browser);
					break;
				}
			}
		} else {
			RESULT.FAIL("Failed because " + optionValue + " is not available in the list", true,
					ScreenshotType.browser);
		}
	}

	/**
	 * To check weather the given option is available in the drop-down list.
	 * 
	 * @author sheetald
	 * 
	 * @param       drop-down locator
	 * 
	 * @param value of option
	 * 
	 * @return true - If the given option is available in the drop-down. false - If
	 *         given option is not available in the drop-down
	 */

	public boolean isDropDownOptionsExists(By dropDownLocator, String optionValue) {

		ArrayList<String> dropDownOptions = getOptionsOfDropDown(dropDownLocator);

		// Append separator at the back of each option.
		String value_new = "##";
		for (int i = 0; i <= dropDownOptions.size() - 1; i++) {
			value_new = value_new + dropDownOptions.get(i) + "##";
		}
		if (value_new.contains("##" + optionValue + "##")) {
			return true;
		} else
			return false;
	}

	// This function is used to get the options of multi-selection type
	// drop-down
	public ArrayList<String> getOptionsOfDropDown(By dropDownLocator) {

		// Check if the drop-down option is exists
		if (!isElementExists(dropDownLocator))
			RESULT.FAIL("The drop-down doesn't exist .", true, ScreenshotType.browser);

		// List to store all options of drop-down
		ArrayList<String> allOptionList = new ArrayList<String>();

		// Get the list of the drop-down options
		List<WebElement> dropdownOptions = driver.findElements(dropDownLocator);

		// storing each column name in the list
		for (int i = 0; i <= dropdownOptions.size() - 1; i++) {
			allOptionList.add(dropdownOptions.get(i).getText());
		}
		return allOptionList;
	}

	public boolean verifyPopUpTitle(String expectedTitle) {
		String popUpTitle = getTextWebelement(Shared_OR.popUpTitle);
		if (popUpTitle.toLowerCase().contains(expectedTitle.toLowerCase())) {
			RESULT.PASS("'" + expectedTitle + "'" + " Pop-up is opened successfully.", true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed to open the " + "'" + expectedTitle + "'" + " Pop-up.", true, ScreenshotType.browser);
		}
		return false;
	}

	// Method to get the text of the input field by using java script executor

	public String getTextOfWebElement(By locator) {
		WebElement element = driver.findElement(locator);

		// Create an object of JavaScriptExector Class
		JavascriptExecutor executor = (JavascriptExecutor) driver;

		String fieldValue = String.valueOf(executor.executeScript("return arguments[0].textContent", element));
		if (!fieldValue.isEmpty()) {
			return fieldValue;
		} else {
			fieldValue = String.valueOf(executor.executeScript("return arguments[0].value", element));
		}
		return fieldValue;

	}

	/*
	 * Method to select the tag from the 'Select Tag to Filter'section.
	 */
	public void selectTagFromTagsSelectorSection(By tagListLocator, String tagValue) {

		if (isTagOptionsExists(tagValue)) {
			// Get the list available in dropDown
			List<WebElement> tagOptions = driver.findElements(tagListLocator);

			// click on given option from drop-down
			for (int i = 0; i <= tagOptions.size() - 1; i++) {
				String tagLabel = "##" + tagOptions.get(i).getText() + "##";
				if (tagLabel.equals("##" + tagValue + "##")) {
					tagOptions.get(i).click();
					pause(DistricoConstant.LOW);
					RESULT.PASS(
							"Successfully to select the " + tagValue
									+ " option from the 'Select Tags to Filter Section'.",
							true, ScreenshotType.browser);
					break;
				}
			}
		} else {
			RESULT.FAIL(
					"Failed because " + tagValue + " option is not available in the 'Select Tags to Filter Section'.",
					true, ScreenshotType.browser);
		}
	}

	/**
	 * To check weather the given option is available in the tag list.
	 * 
	 * @author sheetald
	 * 
	 * @param value of tagOption
	 * 
	 * @return true - If the given option is available in the tag list. false - If
	 *         given option is not available in the tag list.
	 */

	public boolean isTagOptionsExists(String tag) {

		ArrayList<String> tagOptions = getOptionsOfTagSelectorSection(Shared_OR.tagListLocator);

		// Append separator at the back of each option.
		String value_new = "##";
		for (int i = 0; i <= tagOptions.size() - 1; i++) {
			value_new = value_new + tagOptions.get(i) + "##";
		}
		if (value_new.contains("##" + tag + "##")) {
			return true;
		} else
			return false;
	}

	/**
	 * To get the list tag options from the 'Select Tags to Filter' section.
	 * 
	 * @author sheetald
	 * 
	 * @param tagListLocator
	 * 
	 * @return Arraylist
	 */

	public ArrayList<String> getOptionsOfTagSelectorSection(By tagListlocator) {

		// Check if the drop-down option is exists
		if (!isElementExists(tagListlocator))
			RESULT.FAIL("The 'Tag Section section.' doesn't exist .", true, ScreenshotType.browser);

		// List to store all options of drop-down
		ArrayList<String> allTagList = new ArrayList<String>();

		// Get the list of the drop-down options
		List<WebElement> dropdownOptions = driver.findElements(tagListlocator);

		// storing each column name in the list
		for (int i = 0; i <= dropdownOptions.size() - 1; i++) {
			allTagList.add(dropdownOptions.get(i).getText());
		}
		return allTagList;
	}

	/**
	 * To check weather the given drop-down field is enabled
	 * 
	 * @author sheetald
	 * 
	 * @param       drop-down locator
	 * 
	 * @param value of option
	 * 
	 * @return true - If the drop-down field is enabled. false - If the drop-down
	 *         field is disabled.
	 */

	public boolean isDropDownEnabled(By dropDownLocator) {
		// flag to hold status of element
		boolean flag = false;

		// variable to hold element name
		String elementName = null;

		try {
			// set web element name
			elementName = dropDownLocator.toString();

			WebElement element = driver.findElement(dropDownLocator);

			String AttributeValue = getElementAttribute(dropDownLocator, "aria-disabled");
			if (AttributeValue.equals("false")) {
				RESULT.PASS("DropDown is enabled - " + elementName, true, ScreenshotType.browser);
				flag = true;
			} else {
				flag = false;
			}
		} catch (NoSuchElementException e) {

			RESULT.FAIL("Element not found - " + elementName, false, ScreenshotType.browser);
		} catch (WebDriverException e) {

			RESULT.ERROR(("Error: Caused while checking element -'" + elementName + "'"), e, false,
					ScreenshotType.browser);
		}
		return flag;
	}
	// end of isElementEnabled

	public boolean isElementSelected(By elementLocator) {
		// flag to hold status of element
		boolean flag = false;

		// variable to hold element name
		String elementName = null;

		try {
			// set web element name
			elementName = elementLocator.toString();

			WebElement element = driver.findElement(elementLocator);

			String AttributeValue = getElementAttribute(elementLocator, "aria-checked");
			if (AttributeValue.equals("true")) {
				RESULT.PASS("Checkbox is selected - " + elementName, true, ScreenshotType.browser);
				flag = true;
			} else {
				flag = false;
			}

		} catch (NoSuchElementException e) {

			RESULT.FAIL("Element not found - " + elementName, false, ScreenshotType.browser);
		} catch (WebDriverException e) {

			RESULT.ERROR(("Error: Caused while checking element -'" + elementName + "'"), e, false,
					ScreenshotType.browser);
		}
		return flag;
	}// end of isElementSelected

	/*
	 * This method to add feature
	 */
	public void addFeature(String featureValue) {

		setValue(Shared_OR.feature, featureValue);
		pressEnterButton(Shared_OR.feature);
	}

	/*
	 * Method to remove the already existing tags from the 'Tag' field
	 */
	public void removeTags() {
		while (isElementExists(Shared_OR.tagList)) {
			// Remove the tags
			click(Shared_OR.tagRemoveButton);
		}
		boolean flag = isElementExists(Shared_OR.tagList);
		if (!flag) {
			RESULT.PASS("All the tags are removed successfully from the Tag field.", true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed to remove the all the tags from 'Tag' field.", true, ScreenshotType.browser);
		}
	}

	/*
	 * Method to remove the already existing features
	 */
	public void removeFeatures(By locator) {
		while (isElementExists(locator)) {
			// Remove the features
			click(Shared_OR.featureRemoveButton);
		}
		boolean flag = isElementExists(locator);
		if (!flag) {
			RESULT.PASS("All the existing features are removed successfully.", true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed to remove the all the features.", true, ScreenshotType.browser);
		}
	}

//---------------------- Desai Sheetal---------------
	/**
	 * Get the WebElement
	 * 
	 * @param locator Locator of webElement
	 * 
	 */
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			// get the webElement of locator
			element = driver.findElement(locator);

		} catch (NoSuchElementException e) {
			System.out.println(("Element not found - '" + locator.toString() + "'"));
		}
		return element;
	}

	/**
	 * Move to Element by using actions class
	 * 
	 * @param locator Locator of webElement
	 * 
	 */
	public void moveToElement(By locator) {
		WebElement element = getElement(locator);
		Actions actions = new Actions(driver);

		try {
			actions.moveToElement(element).perform();
		} catch (NoSuchElementException e) {
			System.out.println(("Element not found - '" + locator.toString() + "'"));
		}
	}

	public void actionsClick(By locator) {
		WebElement element = getElement(locator);
		Actions actions = new Actions(driver);

		try {
			actions.click(element).perform();
		} catch (NoSuchElementException e) {
			System.out.println(("Element not found - '" + locator.toString() + "'"));
		}
	}

	/*
	 * Navigate to page
	 */
	public void navigateToPage(By moduleName, By menu, By subMenu) {
		Actions actions = new Actions(driver);

		moveToElement(moduleName);
		moveToElement(menu);
		actionsClick(subMenu);
		actions.build().perform();
	}

	/*
	 * Method to wait untill visibility of element
	 */
	public void waitTillVisbilityOfElement(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (NoSuchElementException e) {
			System.out.println((" " + locator.toString() + " Element is not visible."));
		}
	}

	/*
	 * Method to select the value from drop-down
	 */
	public void selectValueFromDropdown(By dropDownLocator, String value) {

		WebElement element = getElement(dropDownLocator);

		// Initialization for the Select Class
		Select select = new Select(element);

		try {
			select.selectByVisibleText(value);
		} catch (NoSuchElementException e) {
			System.out.println(("Failed to select " + value + " from dropdown."));
		}
	}

	/*
	 * Method to verify Notification.
	 */
	public boolean verifyNotification(String expectedNotification) {
		boolean flag = false;
		String actualNotification = getTextOfWebElement(Shared_OR.notification);
		if (actualNotification.equals(expectedNotification)) {
			flag = true;
			RESULT.PASS("Message contenct of the notification is matched successfully.", true, ScreenshotType.browser);
		} else {
			RESULT.FAIL("Failed to matched the message content of the notification.", true, ScreenshotType.browser);
		}
		return flag;
	}
}
