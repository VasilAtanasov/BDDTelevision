package pages.frontend;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import browser.ChromeDriverInitializer;

public class WichTelevisionTest {

	ChromeDriverInitializer driver;
	TelevisionPage onHomePage;
	WebDriverWait wait;

	@BeforeMethod
	public void setUp() {
		driver.init();
		wait = new WebDriverWait(driver.instance, 10);
		onHomePage = PageFactory.initElements(driver.instance,
				TelevisionPage.class);
		onHomePage.goToFrontendTelevisionPage();
		onHomePage.verifyFrontendTelevisionPageTitle();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void goToBestBuyPage() {
		// click on Best Buy navigation
		onHomePage.bestBuy.click();
		// wait until the page is loaded
		wait.until(ExpectedConditions.titleContains("Best Buy"));
		// verify the page title is:
		// "Which? Best Buy televisions - Televisions reviews - Which?"
		BestBuysPage.verifyBestBuysPageTitle();
	}

	@Test
	public void goToAdviceGuidesPage() {
		// click on Advice Guides navigation
		onHomePage.adviceGuidesPage.click();
		// wait until the page is loaded
		wait.until(ExpectedConditions.titleContains("Guides"));
		// verify the page title
		AdviceGuidesPage.verifyAdviceGuidesPageTitle();
	}

	// add contains to title method
	@Test
	public void openTvProductSummaryPage() {
		// click on first product from the list
		onHomePage.firstProductInTheList.click();
		// wait until the product page is loaded
		wait.until(ExpectedConditions.titleContains("review - Televisions"));
		// verify the page title
		Assert.assertEquals(
				"LG 55EF950V review - Televisions reviews - Which?",
				ChromeDriverInitializer.instance.getTitle());
	}

	@Test
	public void checkSortByDropdown() {
		// get all options from the "Sort by" dropdown menu
		Select selectSortBy = new Select(onHomePage.sortByDrobdownMenu);
		List<WebElement> listOfDropdownOptions = onHomePage
				.getListOfDropdownOptions(selectSortBy);

		// check the size of dropdown menu
		onHomePage.verifySortByDropdownSize(selectSortBy);

		/*
		 * Verify that the options in "Sort by" dropdown menu are:
		 * "Most-recently tested" "Highest Which? score" "Price (low to high)"
		 * "Price (high to low)" "Screen size (high to low)"
		 * "Most-recently launched"
		 */
		onHomePage.verifySortByDropdownOptions(listOfDropdownOptions);

		// verify the that the "Most-recently" option is selected by default in
		// Sort By dropdown menu
		onHomePage
				.verifySortByMostRecentlyOptiotIsSelectedByDefault(listOfDropdownOptions);

		// verify that the "Highest Which? score" option is selected by default
		// in Sort By dropdown menu
		onHomePage
				.verifySortByHighestWhichScoreOptionIsDisabledByDefault(listOfDropdownOptions);
	}

	@Test
	public void sortByPriceLowToHigh() throws InterruptedException {
		// get all options from the "Sort by" dropdown menu
		Select selectSortBy = new Select(onHomePage.sortByDrobdownMenu);
		List<WebElement> listOfDropdownOptions = onHomePage
				.getListOfDropdownOptions(selectSortBy);

		// verify that the products are ordered by option "Price (low to high)"
		onHomePage.verifyProductsAreOrderedByPriceLowToHighOption(
				driver.instance, listOfDropdownOptions);

	}

	@Test
	public void filterByBrandPhilips() throws InterruptedException {
		// select brand Philips from filters
		onHomePage.filterBrandsPhilips.click();
		// wait 3 sec. to load the filter
		Thread.sleep(3000);
		// verify that the products are filtered by Philips brand
		onHomePage.verifyTvFiltredByBrand("Philips", driver.instance);

	}

}
