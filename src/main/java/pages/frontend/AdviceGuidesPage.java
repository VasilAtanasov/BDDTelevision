package pages.frontend;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import browser.ChromeDriverInitializer;

public class AdviceGuidesPage {

	@FindBy(css = "a[href*='/reviews/televisions/article/recommendations/which-best-buy-televisions']")
	public WebElement AdviceGuidesPage;

	public static void verifyAdviceGuidesPageTitle() {
		// verify title of Advice Guides Page
		String expectedTitle = "Guides - Televisions reviews - TV and home entertainment - Which? Tech";
		try {
			String actualTitle = ChromeDriverInitializer.instance.getTitle();
			Assert.assertEquals(actualTitle, expectedTitle);
		} catch (NoSuchElementException e) {
			Assert.fail("The title: " + expectedTitle + " was not found.");
		}
	}

}
