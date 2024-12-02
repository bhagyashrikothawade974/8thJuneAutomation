package coverFoxPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverFoxAddressDetailsPage {

	@FindBy(xpath="(//input[@type='number'])[1]")
	private WebElement pinCode;
	
	@FindBy(id="want-expert")
	private WebElement mobNum;
	
	@FindBy(className="next-btn")
	private WebElement continueButton;
	
	@FindBy(xpath="//div[text()=' Please enter a valid pincode ']")
	private WebElement pinCodeErrMsg;
	
	@FindBy(xpath="//div[text()=' Please enter a valid mobile no. ']")
	private WebElement mobErrMsg;
	
	public CoverFoxAddressDetailsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void enterPinCode(String pin)
	{
		Reporter.log("entering pin code",true);
		pinCode.sendKeys(pin);
	}
	
	public void enterMobileNumber(String mobNumber)
	{
		Reporter.log("entering mobile number",true);
		mobNum.sendKeys(mobNumber);
	}
	
	public void clickOnContinueButton()
	{
		Reporter.log("clicking on continue button",true);
		continueButton.click();
	}
	
	public String getPinCodeErrMsg()
	{
		String actualText=pinCodeErrMsg.getText();
		return actualText;
	}
	
	public String getMobNumErrMsg()
	{
		String actualText=mobErrMsg.getText();
		return actualText;
	}
}
