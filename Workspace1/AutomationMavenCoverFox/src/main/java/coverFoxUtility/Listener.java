package coverFoxUtility;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import coverFoxBase.Base;

public class Listener extends Base implements ITestListener {

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log("TC"+result.getName()+"passed",true);
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.log("Taking screenshot",true);
		try {
			Utility.takeScreenShot(driver, result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
