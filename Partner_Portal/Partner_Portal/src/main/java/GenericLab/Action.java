package GenericLab;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.relevantcodes.extentreports.LogStatus;



public class Action extends BrowserHandling{
	
	WebDriverWait wait;
	
	public void waitForElement(By loc,String a){
		
		wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(loc));
		wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
	}
	
	public void click(By loc, String a){
		waitForElement(loc,"");
		WebElement element=driver.findElement(loc);

		WebDriverWait wait=new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.elementToBeClickable(loc));

		element.click();
		wait=new WebDriverWait(driver,30);
	}

	
	public void selectFromDropDownUsingVisibleText(By loc,String value)
	{
		waitForElement(loc,"");
		WebElement element=driver.findElement(loc);
		
		Select s=new Select(element);
		s.selectByVisibleText(value);	
	}
	
	public void selectFromDropDownUsingIndex(By loc,String value)
	{
		waitForElement(loc,"");
		int index=Integer.parseInt(value);
		WebElement element=driver.findElement(loc);
		
		Select s=new Select(element);
		s.selectByIndex(index);
	}
	
	public void selectFromDropDownUsingValue(By loc,String value)
	{
		waitForElement(loc,"");
		WebElement element=driver.findElement(loc);
		
		Select s=new Select(element);
		s.selectByValue(value);
	}
	
	
/*	@SuppressWarnings("deprecation")
	public void waitFor60Seconds(By loc,String a){
		
		//wait=new WebDriverWait(driver,60);
		wait.withTimeout(1, TimeUnit.MINUTES);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
		wait.until(ExpectedConditions.presenceOfElementLocated(loc));
		
	}
*/	
	public void waitForPageLoad()
	{
		wait=new WebDriverWait(driver, 40);
		 ExpectedCondition<Boolean> pageLoadCondition = new
	                ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver driver) {
	                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	                    	}
		 			};
		
		wait.until(pageLoadCondition);		
				
	}

	public void type(By loc,String value) throws InterruptedException{
		waitForElement(loc,"");
		WebElement element=driver.findElement(loc);

		Thread.sleep(3000);
		element.clear();	
		element.sendKeys(value);

	}
	
	
	public void mouseHover(By loc,String a)
	{
		waitForElement(loc,"");
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(loc);	
		action.moveToElement(we).moveToElement(driver.findElement(loc)).build().perform();
		//this.sleep(20);
	}
	
	public void sleep(int seconds,String a) 
	{
	    try {
	        Thread.sleep(seconds * 1000);
	    } catch (InterruptedException e) {

	    }
	}
	
	public void ImageUpload(By loc, String value){
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		//js.executeScript("arguments[0].setAttribute('style', arguments[1])",loc, "0");
		js.executeScript("arguments[0].setAttribute('class', arguments[1])",driver.findElement(loc), "visible");
				
		//waitForElement(loc);
		WebElement element=driver.findElement(loc);

		//WebDriverWait wait=new WebDriverWait(driver,30);
		//wait.until(ExpectedConditions.elementToBeClickable(loc));
				
		element.sendKeys(value);

	}

	
	@SuppressWarnings("resource")
	public void enterOTP(By loc,String value)
	{
		String otp="";
		
			waitForElement(loc, "");
			WebElement element=driver.findElement(loc);
			
			MongoClient mongoClient = new MongoClient("35.154.53.144" , 27017 );
		    // Now connect to your databases
		    @SuppressWarnings("deprecation")
			DB db = mongoClient.getDB("partnerPortal");

		    DBCollection coll = db.getCollection("otps");
		    
		    // Get documents by query
		    BasicDBObject query = new BasicDBObject();
		    BasicDBObject field = new BasicDBObject();
		    query.put("mobile", "7337521533");
		    field.put("otp", 1);
		    DBCursor cursor = coll.find(query,field).limit(1);
		    	otp = cursor.next().toString().substring(60,64);    	
		        System.out.println(otp);		    

			element.sendKeys(otp);
			
	}
	
	
//=============================Keywords for Expected Result Verification ============================================
	
	public void verifyText(By loc,String value)
	{
		waitForElement(loc, "");
		WebElement element=driver.findElement(loc);

		System.out.println("tag name: "+element.getTagName());
		
		//String txt=element.getAttribute("value");
		String txt=element.getText();
		
		System.out.println("actual result: "+txt);
		System.out.println("expected result: "+value);
		Assert.assertEquals(txt, value);
		BrowserHandling.test.log(LogStatus.INFO, "Verified element text");
	}
	
	public void verifyValue(By loc,String value)
	{
		waitForElement(loc, "");
		WebElement element=driver.findElement(loc);
		
		String txt=element.getAttribute("value");
		
		System.out.println("actual result: "+txt);
		System.out.println("expected result: "+value);
		Assert.assertEquals(txt, value);
		BrowserHandling.test.log(LogStatus.INFO, "Verified element value");
	}
	
	public void verifyHighlightInRedColor(By loc,String value) throws Exception
	{
		waitForElement(loc, "");
		WebElement element=driver.findElement(loc);
		String color=element.getCssValue("color");
		System.out.println("color: "+color);
		if(color.equalsIgnoreCase("rgba(111, 111, 111, 1)"))
			BrowserHandling.test.log(LogStatus.INFO, "Field is highlighted with red color");
		else
			throw new Exception("Field not highlighted");
	}
	
	public void verifyAlertText(By loc,String value) throws Exception
	{
		waitForElement(loc, "");
		Alert alert=driver.switchTo().alert();
		
		if(alert.getText().equalsIgnoreCase(value))
			BrowserHandling.test.log(LogStatus.INFO, "Alert verified with its text");
		else
			throw new Exception("Incorrect text present in the Alert");
	}
	
	public void verifyPageUrl(By loc,String value) throws Exception
	{
		waitForPageLoad();
		if(driver.getCurrentUrl().equalsIgnoreCase(value))
			BrowserHandling.test.log(LogStatus.INFO, "URL is correct");
		else
			throw new Exception("Incorrcect URL");
	}
	
	public void verifyPartialText(By loc,String value) throws Exception
	{
		waitForElement(loc, "");
		WebElement element=driver.findElement(loc);
		
		if(element.getText().contains(value))
			BrowserHandling.test.log(LogStatus.INFO, "Partial text is correctly displayed");
		else
			throw new Exception("Incorrect Partial Text");
	}

	public void isDisplayed(By loc,String a) throws Exception
	{
		waitForElement(loc,"");
		WebElement element=driver.findElement(loc);
		if(element.isDisplayed())
			BrowserHandling.test.log(LogStatus.INFO, "Element is displayed corrcetly on the page");
		else
			throw new Exception("Element is not displayed on the page");
	}
	
	
	public boolean verifyElementPresence(By loc,String a) {
        try {
          driver.findElement(loc);
          return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
          return false;
        }
      }

}
