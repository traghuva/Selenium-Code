package listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.util.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import GenericLab.BrowserHandling;

public class ScreenshotOnFailure {
	 public static WebDriver driver; 
	 //private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
	 
/*	  @Override
	  public void onTestFailure(ITestResult tr) {
		  try {
			screenshot(tr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	 
	  @Override
	  public void onTestSkipped(ITestResult tr) {
	  }
	 
	  @Override
	  public void onTestSuccess(ITestResult tr) {
	  }
*/	  
	  
	  public static String screenshot() throws IOException{
		 
		//  System.setProperty(ESCAPE_PROPERTY, "false");
/*		  OpenAndCloseBrowser opencloseBrowser=new OpenAndCloseBrowser();
	        driver = opencloseBrowser.getInstance();
*/	      String encodedBase64 = null ; 
		  
			  System.out.println("Taking screenshot");
		        
			//  File location=new File(System.getProperty("user.dir"));
			  
			  Date date=new Date();			  
			  SimpleDateFormat dateFormat=new SimpleDateFormat("dd_MMM_yyyy_hh_mm_ssaa");
			  
			  String screenshotName=System.getProperty("user.dir")+File.separator+"/Screenshots/"+dateFormat.format(date)+".png";
			  
			  
			  System.out.println("screenshot loction: "+screenshotName);
			  driver=BrowserHandling.getInstance();
		        System.out.println(driver.getTitle());
	
			  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			  FileUtils.copyFile(src, new File(screenshotName));
			  
/*			    FileInputStream fileInputStreamReader = null;
			    try {
			        fileInputStreamReader = new FileInputStream(src);
			        byte[] bytes = new byte[(int)src.length()];
			        fileInputStreamReader.read(bytes);
			        encodedBase64 = new String(Base64.encodeBase64(bytes));
			    } catch (FileNotFoundException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			    return "data:image/png;base64,"+encodedBase64;
		  Reporter.log("<H4>Repro Steps</H4><br>"+tr.getMethod().getMethodName());
		  Reporter.log("<br><a href="+screenshotName+">Screenshot</a>");

*/			  
			 return screenshotName;
		}

}
