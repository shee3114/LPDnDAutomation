package pages;

import org.openqa.selenium.By;

public class Shared_OR {

	public static By mastersMenu = By.xpath("//a[contains(text(),'Masters')]");

	public static By addressMenu = By.xpath("//a[contains(text(),'Address')]");

	public static By homeMenu = By.xpath("//a[contains(text(),'Home')]");

	public static By notification = By.xpath("//span[contains(@id,'LabelExceptionMessage')]");

	public static By deleteBtn = By.xpath("//a[contains(@id,'Delete')]");

	public static By addBtn = By.xpath("//span[text()='Add']");

	public static By editBtn = By.xpath("//a[contains(@id,'LinkEdit')]");

	public static By saveButton = By.xpath("//table//a[contains(@id,'LinkUpdate')]");

	public static By countryDropDown = By.xpath("//select[contains(@id,'ListCountry')]");

	public static By stateDropDown = By.xpath("//select[contains(@id,'ListState')]");

	public static By centreTypeDropDown = By.xpath("//select[contains(@id,'ListCentreType')]");

	public static By centreDropDown = By.xpath("(//select[contains(@id,'ListCentre')])[2]");

	// --------------------------------- Ignore below locator

	// Shared OR for "New Customer" button
	public static By newCustomerBtn = By.xpath("//button/span[contains(text(),'Add Customer')]");

	// Shared OR for moduleName
	public static By pageTitle = By.xpath("//span[@class='erp-title']");

	public static By dropDown = By.xpath("//mat-select[@%s='%s']");

	public static By saveBtn = By.xpath("//span[contains(text(),'Save')]//parent::button");

	public static By editButton = By.xpath("//button/span[contains(text(),'Edit')]");

	public static By yesDoBtn = By.xpath("//button/span[contains(text(),'Yes, Do it.')]");

	public static By yesDeleteBtn = By.xpath("//button/span[contains(text(),'Yes, Delete it.')]");

	public static By searchButton = By.xpath("//button/span[contains(text(),'Search')]");

	// shared OR for drop-down arrow for Filter
	public static By dropDownArrow = By.xpath("//mat-icon[contains(text(),'arrow_drop_down')]");

	// shared OR for pop-upTitle
	public static By popUpTitle = By.xpath("//div[@class='mat-dialog-title']/span");

	public static By confirmationDialog = By.xpath("//erp-confirm-dialog//erp-dialog");

	public static By confirmationDialogContent = By
			.xpath("//erp-confirm-dialog//span[@class='erp-dialog-message']/div");

	public static By deleteConfirmationContent = By.xpath("//erp-confirm-dialog//span[@class='erp-dialog-message']");

	public static By errorMessage = By.xpath("//mat-error");

	public static By tagListLocator = By.xpath("//span[@class='count']//parent::div/span[1]");

	// Shared OR For Notification
	public static By notificationTitle = By.xpath("//simple-notification//div[contains(@class,'sn-title')]");

	public static By notifacationMessage = By.xpath("//simple-notification//div[contains(@class,'sn-content')]");

	public static By dropDownOptionLocator = By.xpath("//mat-option//span");

	public static By crossButton = By.xpath("//button//mat-icon[contains(text(),'close')]");

	public static By cancelButton = By.xpath("//button//mat-icon[contains(text(),'cancel')]");

	public static By deleteButton = By.xpath("//button//span[contains(text(),'Delete')]");

	public static By feature = By.xpath("//input[@name='features']");

	// Locator for feature remove button
	public static By featureRemoveButton = By.xpath("//button/span/mat-icon[contains(text(),'close')]");

	// Locator for tag list
	public static By tagList = By.xpath("//mat-chip-list//mat-chip");

	// Locator for tags remove button
	public static By tagRemoveButton = By.xpath("//mat-chip-list//mat-chip//mat-icon[contains(text(),'cancel')]");

	// Locator for the Setting menu
	public static By settingMenu = By.xpath("//mat-icon[contains(text(),'setting')]");

	public static By organizationProfileMenu = By.xpath("//a[contains(text(),'Organization Profile')]");

	public static By userAndRoleMenu = By.xpath("//a[contains(text(),'Users & Roles')]");

	public static By auditLogsMenu = By.xpath("//a[contains(text(),'Audit Logs')]");

	public static By customFields = By.xpath("//button//span[contains(text(),'Custom Fields')]");
	// --------------------------------------------------------------------------------------
	// Shared OR For common page title
	public static By pageTitle1 = By.xpath("//head/title");

	// Shared OR For NEW button
	public static By newButton = By.xpath("//button/span[contains(text(),'NEW')]");

	// Shared OR For CREATE button
	public static By createButton = By.xpath("//button/span[contains(text(),'CREATE')]");

	// Shared OR For CANCEL button
	public static By cancelButton1 = By.xpath("//button/span[contains(text(),'CANCEL')]");

	// Shared OR For RESET button
	public static By resetButton = By.xpath("//button/span[contains(text(),'RESET')]");

	// Shared OR For SAVE button
	// public static By saveButton =
	// By.xpath("//button/span[contains(text(),'SAVE')]");

	// Share OR For YES button
	public static By yesButton = By.xpath("//button/span[contains(text(),'YES')]");

	// Share OR For UPDATE button
	public static By updateButton = By.xpath("//Button//span[contains(text(),'UPDATE')]");

	// Shared OR For ShowItBig Icon
	public static By showItBigIcon = By.xpath("//div[@class='topbar-left']");

	// Shared OR For sub Window
	public static By subWindow = By.cssSelector("div[role='dialog']");

	// Shared OR For sub Window title -- Updated by PB on 02Jan19
	public static By subWindowTitle = By.xpath("//p-dialog//div//div[contains(@class,'ui-dialog-title')]/span");

	// Shared OR For edit icon
	public static By editIcon = By.xpath("//table/tbody/tr[1]//button");

	// Shared OR For back icon or button
	public static By backButton = By.xpath("//button[@icon='fa fa-arrow-left']/span[2]//parent::button");

	// Shared OR For Search fields
	public static By searchFields = By.xpath("//input[@placeholder='Search']"); // By.className("input[placeholder='Search']");

	// Shared OR For PageElement
	public static By PageNumber = By.xpath("//span[@class='ui-paginator-pages']/a");

	// Shard OR For tableHead of all the columns
	public static By AllcolumnAHead = By.xpath("//table//th");

	// Shared OR For column name which are input type
	public static By columnHead = By.xpath("//table//th//input");

	// Shard OR For close button of the notification
	public static By closeNotification = By.xpath("//div[contains(@class,'fa-close')]");

	// Shard OR For value of page selector
	public static By pageSelectorDefaultValue = By.xpath("//p-paginator//label");

	// Shard OR For value of list of records
	public static By listOfRecords = By.xpath("//table/tbody[@class='ui-datatable-data ui-widget-content']");

	// Shared OR For pageselector value
	public static By pageNavigatorDropDown = By.xpath("//p-paginator//p-dropdown/div");

	// Shared OR For the dropdown of p-dropdowntag
	public static By pdropDown = By.xpath("//p-dropdown[@%s='%s']");

	// Static OR For the dropdown of p-autocomplete
	public static By pAutoComplete = By.xpath("//p-autocomplete[@%s='%s']");

	// Shared OR For the dropdown of multiselect option
	public static By pmultiselect = By.xpath("//p-multiselect[@%s='%s']");

	// Shared OR For the forward arrow in page navigation
	public static By forwardArrow = By.xpath("//a[contains(@class,'ui-paginator-next')]");

	// Shared OR For the step backward arrow
	public static By stepBackwardArrow = By.xpath("//a[contains(@class,'ui-paginator-first')]");

	// Shared OR For the step forward Arrow
	public static By stepForwardArrow = By.xpath("//a[contains(@class,'ui-paginator-last')]");

	// Shared OR For the last page number
	public static By lastPageNumber = By
			.xpath("//span[@class='ui-paginator-pages']/a[contains(@class,'ui-state-active')]");

	// Shared OR For the multiple option selection from the pop-up
	public static By multipleSelectionPopUp = By.xpath("//div[@class='ui-multiselect-items-wrapper']/ul");

	public static By multiSelectionSearchBox = By.xpath("//input[@role='textbox']");

	// Shared OR For the full page pop-up title
	public static By popUpTitle1 = By.xpath("//p-dialog//span[contains(text(),'Edit Contract')]");

	// Shard OR For the suggestion in dropdown type field
	public static By suggestion = By.xpath("//div[contains(@class,'ui-autocomplete')]");

	public static By suggestionLabel = By.xpath("//div[contains(@class,'ui-autocomplete-panel')]//li//div");

	// Shared Or for the data of firstRow in the list
	public static By firstRowDataLocator = By.xpath("//table/tbody/tr[1]/td");

	// Shared OR For the close button
	public static By closeButton = By.xpath("//p-dialog/div/div[1]/a/span");

	public static By profileButton = By.xpath("//erp-user-profile//img"); /// Done
																			/// By
																			/// Sheetal
																			/// For
																			/// ERP

	public static By LogoutButton = By.xpath("//button/span[contains(text(),'Logout')]"); // Done
																							// by
																							// Sheetal
																							// For
																							// ERP

	// to click on particular button by passing the label of the button
	public static By button = By.xpath("//button[@label='%s']");

	// Shared OR for calender locators
	public static By yearLocator = By.xpath("//select[contains(@class,'ui-datepicker-year')]");

	public static By monthLocator = By.xpath("//select[contains(@class,'ui-datepicker-month')]");

	public static By dateLocator = By
			.xpath("//table[contains(@class, 'ui-datepicker-calendar')]//a[text()='1']//following::a[text()=%s]");

	public static By calendar = By.xpath("//body//table[contains(@class,'ui-datepicker-calendar')]");

	public static By showColumnIcon = By.xpath("//p-multiselect[@defaultlabel='Show Columns']//span");
	// ---------------------------------------------------------------------------------

	// Shared OR For Common Options under Top Bar of Patient Portal
	public static By logOut = By.xpath("//a[@title='Logout']");

	public static By PtBalance = By.xpath("//div[@id='balance']/a");

	public static By Notifications = By.xpath("//div[@id='mainmenu']/ul/li[1]/a/i");

	public static By NotificationText = By.xpath(".//ul[@id='notificationMenu']/li[1]/span/h5/b");

	public static By patientOptions = By.xpath("//div[@id='mainmenu']/ul/li/a[contains(text(),'%s')]");

	public static By pageTitleText = By.xpath("//div[@class='box-header']/div/span");

	public static By pageHeaderText = By.xpath("//div[@class='box-name']/span");

	public static By pateientMenuListOpt = By.xpath(
			"//div[@id='mainmenu']/ul/li/a[contains(text(),'%s')]/following-sibling::ul/li/a/span[contains(text(),'%s')]");

	// Shared OR for User Name and Password field
	public static By by_username = By.xpath("//input[@id='TaRtxt_username']");

	public static By by_password = By.xpath("//input[@id='TaRpas_password']");

	public static By by_login = By.xpath("//button[@type='submit']");

	public static By by_health_record = By.xpath("//li[@class='dropdown'][3]/a");

	public static By by_documents = By.xpath("//li[@class='dropdown'][3]/ul/li[1]/a");

	// These are Shared_OR for Calendar control
	// public static By by_calendar_icon = By.xpath("//img[@alt='Calendar']");
	public static By calendarIcon = By.xpath("//p-calendar//input[@%s='%s']//following-sibling::button");

	public static By dateField = By.xpath("//input[@%s='%s']");

	public static By by_calendar_year = By
			.xpath("//div[@class='ui-datepicker-title']/select[@class='ui-datepicker-year']");

	public static By by_calendar_month = By
			.xpath("//div[@class='ui-datepicker-title']/select[@class='ui-datepicker-month']");

	public static By by_calendar_all_year = By
			.xpath("//div[@class='ui-datepicker-title']/select[@class='ui-datepicker-year']/option");

	public static By by_calendar_all_month = By
			.xpath("//div[@class='ui-datepicker-title']/select[@class='ui-datepicker-month']/option");

	public static By by_calendar_all_date = By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr/td/a");

	// Shared OR for Search Button and Page Header of each page
	public static By Search = By.xpath("//button[@title='Search']");

	public static By pageHeader = By.xpath("//div[@class='box-name']/span");

	// Locator for Page loading Icon
	public static By pageLoadIcon = By.xpath("div/div[3]/button");

	public static By calendarTextBox = By.xpath("//ul[@class='list-inline clearfix']/li");

	// Pager elements
	public static String pagerMsg = "//tr[@class='pgr']//span[@class='msg']";

	public static By currentPage = By.xpath("//tr[@class='pgr']//font[@class='header_text']/following-sibling::font");
	// public static String lastPageSuffix =
	// "//tr[@class='pgr']//a[text()='»']";
	// public static String firstPageSuffix =
	// "//tr[@class='pgr']//a[text()='«']";

}
