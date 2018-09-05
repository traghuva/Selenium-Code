package tests;

import GenericLab.BrowserHandling;
import GenericLab.DriverScript;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;

 
public class PartnerPortalTest extends BrowserHandling {
	static BrowserHandling bh;

	
	PartnerPortalTest()
	{
		super();
	}	
	
 @Test(priority=1)
 @Parameters({"ExcelFile"})
 public void login(String filePath) throws Exception
 {
	BrowserHandling.test.log(LogStatus.INFO, "Login tests started");
	DriverScript ds=new DriverScript();
	ds.executeAllTestCases(filePath, "Login");
  	extent.endTest(test);
	extent.flush();
 } 
  
 
 

 
}