package GenericLab;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import DataProviders.ReadExcel;


public class BrowserHandling {
	public static WebDriver driver;
	public static ExtentTest test;
	public static ExtentReports extent;
	
	protected BrowserHandling()
	{
		  Date date=new Date();			  
		  SimpleDateFormat dateFormat=new SimpleDateFormat("dd_MMM_yyyy_hh_mm_ssaa");
		  extent=new ExtentReports(System.getProperty("user.dir")+File.separator+"/FrameworkReports/"+"Report"+dateFormat.format(date)+".html",false);	
		 // extent.startTest("Open Browser");
	}
	
	
	public static WebDriver getInstance()
	{
		return driver;
	}
	
	
	@Parameters({"BrowserName","URL"})
	@BeforeClass
	public void OpenBrowser(String browserName,String URL) throws Exception
	{
    	test=extent.startTest("Open Browser");
		test.log(LogStatus.INFO, "Inside Open Browser method");
/*		ChromeOptions options = new ChromeOptions();
		 options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "D:\\Data Backup\\Desktop\\sfw\\chromedriver_win32\\chromedriver.exe");
		 driver = new ChromeDriver(options);
		      
		     // To launch website
		     driver.get("http://35.154.53.144");
		     // To maximize the browser
		     driver.manage().window().maximize();
				LoginTest.test.log(LogStatus.PASS, "Browser Maximised");
		     Action a=new Action();
		     a.waitForPageLoad();
*/		     
		if(browserName.equalsIgnoreCase("chrome")){
			ChromeOptions o = new ChromeOptions();
			o.addArguments("disable-extensions");
			o.addArguments("--start-maximized");
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
			driver=new ChromeDriver(o);
			driver.get(URL);
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
			DesiredCapabilities capabilities=DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			
			driver=new FirefoxDriver();
			driver.get(URL);
		}

		Thread.sleep(2000);
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Browser Maximised");
		
		
		//extent.endTest(test);
		//extent.flush();
	}
	
	@AfterClass
	public void CloseBrowser()
	{
		driver.quit();
	}

}
