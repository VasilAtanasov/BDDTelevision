package pages.frontend;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import browser.ChromeDriverInitializer;

public class TelevisionPage {

	@FindBy(css = "a[href*='/reviews/televisions/article/recommendations/which-best-buy-televisions']")
	public WebElement bestBuy;

	@FindBy(css = "a[href*='/reviews/televisions/article/guides']")
	public WebElement adviceGuidesPage;

	@FindBy(xpath = "//img[@class ='product-listing__thumb-image']")
	public WebElement firstProductInTheList;

	@FindBy(xpath = "//select[contains(@title,'Sort by')]")
	public WebElement sortByDrobdownMenu;

	@FindBy(xpath = "//ul[contains(@class,'products')]/li")
	public WebElement productList;

	@FindBy(xpath = "//div[@data-test='price-amount']")
	public WebElement productPrice;

	@FindBy(xpath = "//span[@class='_36Zts' and text()='Philips']")
	public WebElement filterBrandsPhilips;

	@FindBy(xpath = "//span[@class='product-listing__manufacturer']")
	public WebElement productBrand;

	public void goToFrontendTelevisionPage() {
		// open Television page
		ChromeDriverInitializer.instance
				.get("http://www.which.co.uk/reviews/televisions");
	}

	public void verifyFrontendTelevisionPageTitle() {
		// verify Television page title
		String expectedTitle = "Televisions - TV and home entertainment - Which? Tech";
		try {
			String actualTitle = ChromeDriverInitializer.instance.getTitle();
			Assert.assertEquals(actualTitle, expectedTitle);
		} catch (NoSuchElementException e) {
			Assert.fail("The title: " + expectedTitle + " was not found.");
		}
	}

	/**
	 * This method is used to get "Sort by" dropdown
	 * 
	 * @return SELECT element
	 */
	public Select getSelectSortBy() {
		// get Sort By dropdown menu
		Select selectSortBy = new Select(sortByDrobdownMenu);
		return selectSortBy;
	}

	/**
	 * This method is used to verify the size of "Sort by" dropdown
	 * 
	 * @param selectSortBy
	 *            SELECT element
	 */
	public void verifySortByDropdownSize(Select selectSortBy) {
		//
		int actualResultSelectSize = selectSortBy.getOptions().size();
		Assert.assertEquals(actualResultSelectSize, 6,
				"Check the size of Sort by dropdown");
	}

	/**
	 * This method is used to get all options from "Sort by" dropdown
	 * 
	 * @param selectSortBy
	 *            SELECT element
	 * @return A list of "Sort By" dropdown options
	 */
	public List<WebElement> getListOfDropdownOptions(Select selectSortBy) {
		List<WebElement> listOfDropdownOptions = selectSortBy.getOptions();

		return listOfDropdownOptions;
	}

	/**
	 * This method is used to verify the options in "Sort by" dropdown
	 * 
	 * @param listOfDropdownOptions
	 */
	public void verifySortByDropdownOptions(
			List<WebElement> listOfDropdownOptions) {
		System.out
				.println("Verifying the options in \"Sort By\" dropdown menu");
		Assert.assertEquals(listOfDropdownOptions.get(0).getText(),
				"Most-recently tested", "Check the first option in dropdown");
		Assert.assertEquals(listOfDropdownOptions.get(1).getText(),
				"Highest Which? score", "Check the second option in dropdown");
		Assert.assertEquals(listOfDropdownOptions.get(2).getText(),
				"Price (low to high)", "Check the third option in dropdown");
		Assert.assertEquals(listOfDropdownOptions.get(3).getText(),
				"Price (high to low)", "Check the fourth option in dropdown");
		Assert.assertEquals(listOfDropdownOptions.get(4).getText(),
				"Screen size (high to low)",
				"Check fifth second option in dropdown");
		Assert.assertEquals(listOfDropdownOptions.get(5).getText(),
				"Most-recently launched", "Check the sixth option in dropdown");
	}

	/**
	 * This method is used to verify that "Most-recently" option is selected by
	 * default in "Sort by" dropdown
	 * 
	 * @param listOfDropdownOptions
	 */
	public void verifySortByMostRecentlyOptiotIsSelectedByDefault(
			List<WebElement> listOfDropdownOptions) {
		String step = "Check if \"Most-recently\" option is selected by default";
		System.out.println(step);
		String actualSelected = listOfDropdownOptions.get(0).getAttribute(
				"selected");
		Assert.assertEquals(actualSelected, "true", step);
		String actualNonSelected = listOfDropdownOptions.get(2).getAttribute(
				"selected");
		Assert.assertEquals(actualNonSelected, null, step);
	}

	/**
	 * This method is used to verify that
	 * "Highest Which? score\" option is disabled by default in "Sort by"
	 * dropdown
	 * 
	 * @param listOfDropdownOptions
	 */
	public void verifySortByHighestWhichScoreOptionIsDisabledByDefault(
			List<WebElement> listOfDropdownOptions) {
		String step = "Check if the \"Highest Which? score\" option is disabled by default";
		System.out.println(step);
		String actualDisabled = listOfDropdownOptions.get(1).getAttribute(
				"disabled");
		Assert.assertEquals(actualDisabled, "true", "");
	}

	/**
	 * This method is used to verify that the products (TV) are ordered by
	 * Price(low to high) option
	 * 
	 * @param instance
	 *            instance of WebDriver
	 * @param listOfDropdownOptions
	 * @exception InterruptedException
	 */
	public void verifyProductsAreOrderedByPriceLowToHighOption(
			WebDriver instance, List<WebElement> listOfDropdownOptions)
			throws InterruptedException {
		// select "Price (low to high)"
		listOfDropdownOptions.get(2).click();
		Thread.sleep(3000);
		List<String> pricesAfter = new ArrayList<String>();
		List<WebElement> productListAfter = instance.findElements(By
				.xpath("//div[@data-test='price-amount']"));
		for (WebElement curWebElement : productListAfter) {
			pricesAfter.add(curWebElement.getText());
		}

		for (int i = 0; i < pricesAfter.size() - 1; i++) {
			String price = removeFirstChar(pricesAfter.get(i));
			String nextPrice = removeFirstChar(pricesAfter.get(i + 1));

			double priceValue = Double.parseDouble(price);
			double nextPriceValue = Double.parseDouble(nextPrice);
			Assert.assertTrue(priceValue <= nextPriceValue);
		}
	}

	/**
	 * This method is used to verify that the products (TV) are filtered by
	 * brand
	 * 
	 * @param brandName
	 *            brand which is used to filter the products(TV)
	 * @param instance
	 *            instance of WebDriver
	 */
	public void verifyTvFiltredByBrand(String brandName, WebDriver instance) {
		List<WebElement> productListAfter = instance.findElements(By
				.xpath("//span[@class='product-listing__manufacturer']"));

		for (int i = 0; i < productListAfter.size(); i++) {
			String actualBrand = productListAfter.get(i).getText();
			Assert.assertTrue(brandName.equals(actualBrand),
					"Brand does not match");
		}

	}

	public String removeFirstChar(String s) {
		return s.substring(1);
	}

}
