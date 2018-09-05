package DataProviders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import GenericLab.Action;

public class ReadExcel extends Action {
	
	public static XSSFWorkbook workBook;
	public static XSSFSheet sheet;
	public static int i=0,j=0;
	
	
	public static void setExcelFile(String filePath,String sheetName) throws IOException
	{
		FileInputStream f=new FileInputStream(new File(filePath));
		workBook=new XSSFWorkbook(f);
		sheet=workBook.getSheet(sheetName);
	}

	public static String getCellData(int rowNum,int colNum)
	{
		Cell cell=sheet.getRow(rowNum).getCell(colNum);
		
		if(cell==null)
			return "blank";
		else
			return cell.getStringCellValue();

	}
	
	public static int getRowCount()
	{
		return sheet.getLastRowNum();
	}
	
	
	public static int getTestStepCount(int rowNum) throws IOException
	{		
		int stepCount=0,tcCount=0;
		
		int rowCount=getRowCount();
		stepCount=0;
		for(j=rowNum;j<=rowCount;j++)
		 {			  
			 if(getCellData(j, 1).equalsIgnoreCase("blank"))
				 	stepCount++;
			 else
				 tcCount++;
		
			 if(tcCount==2)
				 break;
		 }		 		
		return stepCount;		
	}
		
}
