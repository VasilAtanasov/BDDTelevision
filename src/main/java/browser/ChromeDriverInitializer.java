package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverInitializer {

	public static WebDriver instance;

	public static void init() {
		System.setProperty("webdriver.chrome.driver",
				"src//test//resources//SeleniumDrivers//chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized", "--disable-extensions");
		instance = new ChromeDriver(options);
		instance.manage().window().maximize();
	}

	public static void quit() {
		instance.quit();
	}
}