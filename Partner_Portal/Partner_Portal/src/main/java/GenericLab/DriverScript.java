package GenericLab;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;

import com.relevantcodes.extentreports.LogStatus;

import DataProviders.ReadExcel;
import GenericLab.Action;
import ObjectRepository.PartnerPortalObjects;
import listeners.ScreenshotOnFailure;
import tests.PartnerPortalTest;



	
public class DriverScript {

		public static Method methods[];
		public static Field fields[];
		public static Object object[];
		public static String sActionKeyword;
		public static String sValue;
		public static String sField;
		public static String sExpectedAction;
		public static String sExpectedField;
		public static String sExpectedValue;
		public static String[] sExpectedActions;
		public static String[] sExpectedFields;
		public static String[] sExpectedValues;
		public static Action action;
		public static Object ob;
		

		public DriverScript() throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, Exception{
			//This will load all the methods of the class 'ActionKeywords' in it.
	                //It will be like array of method, use the break point here and do the watch
			PartnerPortalObjects ppo = new PartnerPortalObjects();
			fields=ppo.getClass().getDeclaredFields();

			action = new Action();
			methods = action.getClass().getMethods();
		}
		
			
		public void executeAllTestCases(String filePath,String sheetName) throws Exception
		{
			int steps=0,m=0,rowCount=0;

			ReadExcel.setExcelFile(filePath, sheetName);
			rowCount=ReadExcel.getRowCount();	
			
			for(m=2;m<=rowCount;m++)
			{
				
			}
			
			BrowserHandling.test.log(LogStatus.INFO, "No. of rows in \""+sheetName+"\" sheet : "+rowCount);
			
			//BrowserHandling.extent.endTest(BrowserHandling.test);
			
			for(m=2;m<=rowCount;m++)
			{
				if(ReadExcel.getCellData(m, 1).contains("TC") && ReadExcel.getCellData(m, 10).equalsIgnoreCase("Yes"))
				{
					int k=0,flag=0;
					//BrowserHandling.test=BrowserHandling.extent.startTest("Test Case "+ReadExcel.getCellData(m, 1));
					BrowserHandling.test=BrowserHandling.extent.startTest(ReadExcel.getCellData(m, 1)+" : "+ReadExcel.getCellData(m, 2));
					
					steps=ReadExcel.getTestStepCount(m);
					BrowserHandling.test.log(LogStatus.INFO, "This test case has "+steps+" steps");

					for(k=m+1;k<=m+steps;k++)
					{
						sField=ReadExcel.getCellData(k, 3);
						sValue=ReadExcel.getCellData(k, 4);
						sActionKeyword=ReadExcel.getCellData(k, 5);

						//executing step
						executeAction(ob,sField,sActionKeyword,sValue);
						PartnerPortalTest.test.log(LogStatus.INFO, "Performed action \""+sActionKeyword+ "\" in \""+sValue+"\" for \""+sField+"\"");
						
						sExpectedAction=ReadExcel.getCellData(k, 6);
						sExpectedField=ReadExcel.getCellData(k, 7);
						sExpectedValue=ReadExcel.getCellData(k, 8);					
						//verifying expected result
						BrowserHandling.test.log(LogStatus.INFO, "Verifying expected result: " + sExpectedAction + " for this step");						
						sExpectedActions=sExpectedAction.split("\\n");
						sExpectedFields=sExpectedField.split("\\n");
						sExpectedValues=sExpectedValue.split("\\n");
						
						System.out.println("Length: " +sExpectedActions.length);
						
						for(int j=0;j<sExpectedActions.length;j++)
						{
							if(sExpectedActions[j]!=null)
							{
								try{
									System.out.println("Expected action "+sExpectedActions[j]);

									executeAction(ob,sExpectedFields[j],sExpectedActions[j],sExpectedValues[j]);
								BrowserHandling.test.log(LogStatus.INFO, "Step Passed");
								}catch(Exception e)
								{
									System.out.println("Exception mesage: "+e.getMessage());
									e.printStackTrace();
									BrowserHandling.test.log(LogStatus.ERROR, "Step Failed" + BrowserHandling.test.addScreenCapture(ScreenshotOnFailure.screenshot()));
									flag=1;
								}
							}
						}
						
					}				
				
					if(flag==0)
						BrowserHandling.test.log(LogStatus.PASS, "Completed Test Case " +ReadExcel.getCellData(m, 1));
					else
						BrowserHandling.test.log(LogStatus.FAIL, "Failed Test Case " + BrowserHandling.test.addScreenCapture(ScreenshotOnFailure.screenshot()));
					
					BrowserHandling.extent.endTest(BrowserHandling.test);
					BrowserHandling.extent.flush();						
				}
			}
		}
		
		
		public void executeAction(Object obj,String field,String actionKey,String value) throws Exception
		{
			int fieldFlag=0, methodFlag=0;
			By a=By.xpath("");			
						
			
			for(int p=0;p<fields.length;p++)
			{
				if(field.equalsIgnoreCase("NA"))
					fieldFlag=1;
				if(fields[p].getName().equalsIgnoreCase(field))
				{
					a =(By) (fields[p].get(obj));
					PartnerPortalTest.test.log(LogStatus.INFO, "Field : "+sField+"\n"+" Locator: "+a.toString());
					fieldFlag=1;
					break;
				}
				
			}
			
			for(int l=0;l<methods.length;l++)
			{
				if(actionKey.equalsIgnoreCase("NA"))
					methodFlag=1;
				if(methods[l].getName().equalsIgnoreCase(actionKey))
				{
					methods[l].invoke(action,a,value);					
					methodFlag=1;
					break;
				}
			}	
			
			if(fieldFlag==0 || methodFlag==0 )
			{
					BrowserHandling.test.log(LogStatus.ERROR, "Either field or Action Keyword is not found");
					throw new Exception();
			}
			
			Thread.sleep(1000);
			
			
		}// end of executeAction method
		
		
		

}
