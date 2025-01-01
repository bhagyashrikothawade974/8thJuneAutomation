package coverFoxTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import coverFoxBase.Base;
import coverFoxPOM.CoverFoxAddressDetailsPage;
import coverFoxPOM.CoverFoxHealthPlanPage;
import coverFoxPOM.CoverFoxHomePage;
import coverFoxPOM.CoverFoxMemberDetailsPage;
import coverFoxPOM.CoverFoxResultPage;
import coverFoxUtility.Utility;

public class TC1234_CoverFox_ValidateBannerCount extends Base{
	
	String excelPath=System.getProperty("user.dir")+"\\\\DataSheets\\\\MyData.xlsx";
	String sheetName="Sheet3";
	
	CoverFoxHomePage homePage;
	CoverFoxHealthPlanPage healthPlanPage;
	CoverFoxMemberDetailsPage memberDetailsPage;
	CoverFoxAddressDetailsPage addressDetailsPage;
	CoverFoxResultPage resultPage;
	public static Logger logger;
	//open browser/open an application
	
	@BeforeClass
	public void openApplication() throws IOException
	{
		launchBrowser("chrome");
		logger=Logger.getLogger("Automation_Maven_CoverFox");
		PropertyConfigurator.configure("log4j.properties");
		logger.info("opening application");
	}
	
	@BeforeMethod
	public void enterDetails() throws InterruptedException, EncryptedDocumentException, IOException
	{
		homePage=new CoverFoxHomePage(driver);
		healthPlanPage=new CoverFoxHealthPlanPage(driver);
		memberDetailsPage=new CoverFoxMemberDetailsPage(driver);
		addressDetailsPage=new CoverFoxAddressDetailsPage(driver);
		resultPage=new CoverFoxResultPage(driver);
		homePage.clickOnGenderButton();
		logger.info("Clicking on gender button");
		Thread.sleep(1000);
		healthPlanPage.clickOnNextButton();
		logger.info("Clicking on next button");
		Thread.sleep(1000);
		memberDetailsPage.handleAgeDropDown(Utility.readDataFromExcel(excelPath, sheetName, 0, 0));
		logger.info("handling age drop down");
		memberDetailsPage.clickOnNextButton();
		logger.info("Clicking on next button");
		Thread.sleep(1000);
		logger.warn("Enter valid pin code");
		addressDetailsPage.enterPinCode(Utility.readDataFromExcel(excelPath, sheetName, 0, 1));
		logger.info("Entering pin code");
		logger.warn("Enter valid mobile number");
		addressDetailsPage.enterMobileNumber(Utility.readDataFromExcel(excelPath, sheetName, 0, 2));
		logger.info("Entering mobile number");
		addressDetailsPage.clickOnContinueButton();
		logger.info("Clicking on continue button");
		logger.error("Please check details again");
		Thread.sleep(3000);
	}
	
  @Test
  public void validatePolicyCount() throws IOException 
  {
	int textCount= resultPage.getCountFromText();
	int bannerCount=resultPage.getCountFromBanner();
	logger.info("validating results");
	Assert.assertEquals(textCount, bannerCount,"text count not matching with banner count,TC failed");
	//Assert.fail();
	Utility.takeScreenShot(driver, "TC12345");
	Reporter.log("taken screenshot..");
	Reporter.log("code changed by me",true);
  }
  
  //logout from application
  //close browser/close an application
  @AfterClass
  public void closeApplication()
  {
		logger.info("Closing browser");
	  closeBrowser();
  }
}
