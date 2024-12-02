package coverFoxPOM;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverFoxResultPage {

	@FindBy(xpath="//div[contains(text(),'matching Health')]")
	private WebElement resultText;
	
	@FindBy(className="plan-card-container")
	private List<WebElement>planList;
	
	public CoverFoxResultPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public int getCountFromText()
	{
		Reporter.log("gettig policy count from text displayed..",true);
		String numberInString=resultText.getText().substring(0,2);
		int countFromText=Integer.parseInt(numberInString);
		return countFromText;
	}
	
	public int getCountFromBanner()
	{
		Reporter.log("gettig policy count from banners displayed..",true);
		int countFromBanner=planList.size();
		return countFromBanner;
	}
	
//	public void validateResult()
//	{
//		String numberInString=resultText.getText().substring(0,2);
//		int resultNumber=Integer.parseInt(numberInString);
//		int planListNumber=planList.size();
//		System.out.println("Result number is "+resultNumber);
//		System.out.println("Plan list number is "+planListNumber);
//		if(resultNumber==planListNumber)
//		{
//			System.out.println("results are matching TC is passed");
//		}
//		else
//		{
//			System.out.println("results are not matching TC is failed");
//		}
//	}
}
