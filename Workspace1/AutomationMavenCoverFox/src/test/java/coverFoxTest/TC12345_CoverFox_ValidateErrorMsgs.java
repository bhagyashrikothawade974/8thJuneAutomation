package coverFoxTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
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

public class TC12345_CoverFox_ValidateErrorMsgs extends Base{
	
	String excelPath=System.getProperty("user.dir")+"\\\\DataSheets\\\\MyData.xlsx";
	String sheetName="Sheet3";
	
	CoverFoxHomePage homePage;
	CoverFoxHealthPlanPage healthPlanPage;
	CoverFoxMemberDetailsPage memberDetailsPage;
	CoverFoxAddressDetailsPage addressDetailsPage;
	CoverFoxResultPage resultPage;
	public static Logger logger;
	//open browser/open an application
	
	
	@BeforeMethod
	public void enterDetails() throws InterruptedException, EncryptedDocumentException, IOException
	{
		launchBrowser("chrome");
		logger=Logger.getLogger("Automation_Maven_CoverFox");
		PropertyConfigurator.configure("log4j.properties");
		logger.info("opening application");
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
	}
	
  @Test(priority=-1)
  public void validatePinCodeErrorMessage() throws IOException 
  {
	    logger.warn("Enter valid mobile number");
		addressDetailsPage.enterMobileNumber(Utility.readDataFromExcel(excelPath, sheetName, 0, 2));
		logger.info("Entering mobile number");
		addressDetailsPage.clickOnContinueButton();
		logger.info("Clicking on continue button");
		logger.error("Please check details again");
		String actualPinErrorMsg=addressDetailsPage.getPinCodeErrMsg();
		String expectedPinErrorMsg=Utility.readDataFromExcel(excelPath, sheetName, 0, 3);
		Assert.assertEquals(actualPinErrorMsg,expectedPinErrorMsg,"Error msg not matching,TC failed" );
  }
  
  @Test
  public void validateMobileNumErrorMessage() throws EncryptedDocumentException, IOException
  {
	    logger.warn("Enter valid pin code");
		addressDetailsPage.enterPinCode(Utility.readDataFromExcel(excelPath, sheetName, 0, 1));
		logger.info("Entering pin code");
		addressDetailsPage.clickOnContinueButton();
		logger.info("Clicking on continue button");
		logger.error("Please check details again");
		String actualMobNumErrMsg=addressDetailsPage.getMobNumErrMsg();
		String expectedMobNumErrMsg=Utility.readDataFromExcel(excelPath, sheetName, 0, 4);
		Assert.assertEquals(actualMobNumErrMsg, expectedMobNumErrMsg,"Error msg not matching,TC failed");
  }
  
  //logout from application
  //close browser/close an application
  @AfterMethod
  public void closeApplication()
  {
		logger.info("Closing browser");
	  closeBrowser();
  }
}
