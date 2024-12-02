package coverFoxBase;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.Parameters;

import coverFoxUtility.Utility;

public class Base {

	//driver
	//url
	//launch browser
	//close browser
	
	protected static WebDriver driver;
	@Parameters("browserName")
	public void launchBrowser(String browser) throws IOException {
		
		ChromeOptions co=new ChromeOptions();
		co.addArguments("disable-notifications");
		
		if(browser.equals("chrome")) {
		driver=new ChromeDriver(co);
		}else if(browser.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}else if(browser.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.get(Utility.readDataFromPropertyFile("url"));
		Reporter.log("launching browser",true);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Reporter.log("waiting..",true);
	}
	
	public void closeBrowser()
	{
		Reporter.log("closing browser",true);
		driver.quit();
	}
}
