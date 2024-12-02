package coverFoxPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverFoxHomePage {

	//variable declaration-->webElements
	//WebElement element=driver.findElement(By.xpath(""))
	@FindBy(xpath="//div[text()='Male']")
	private WebElement gender;
	
	//constructor-->variable initialization
	public CoverFoxHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//methods
	public void clickOnGenderButton()
	{
		Reporter.log("clicking on gender button",true);
		gender.click();
	}
}
