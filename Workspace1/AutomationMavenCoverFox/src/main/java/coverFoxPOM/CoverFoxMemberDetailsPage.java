package coverFoxPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class CoverFoxMemberDetailsPage {

	@FindBy(id="Age-You")
	private WebElement ageDropDown;
	
	@FindBy(className="next-btn")
	private WebElement nextButton;
	
	public CoverFoxMemberDetailsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void handleAgeDropDown(String age)
	{
		Reporter.log("handeling age dropdown..",true);
		Select select=new Select(ageDropDown);
		select.selectByValue(age+"y");
	}
	
	public void clickOnNextButton()
	{
		Reporter.log("clicking on next button..",true);
		nextButton.click();
	}
}
