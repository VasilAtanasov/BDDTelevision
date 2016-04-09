package pages.frontend;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import browser.ChromeDriverInitializer;

public class BestBuysPage {

	@FindBy(css = "a[href*='/reviews/televisions/article/guides']")
	private WebElement BestBuysPage;

	public static void verifyBestBuysPageTitle() {
		// verify title of Best Buys Page
		String expectedTitle = "Which? Best Buy televisions - Televisions reviews - Which?";
		try {
			String actualTitle = ChromeDriverInitializer.instance.getTitle();
			Assert.assertEquals(actualTitle, expectedTitle);
		} catch (NoSuchElementException e) {
			Assert.fail("The title: " + expectedTitle + " was not found.");
		}
	}

}
