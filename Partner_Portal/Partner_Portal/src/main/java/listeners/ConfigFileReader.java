package listeners;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	private Properties properties;
	private final String propertyFilePath= "C:\\Users\\so.maggo\\Downloads\\new\\Partner_Portal_Framework (1)"
			+ "\\Partner_Portal_Framework\\configs\\Configuration.properties";

	
	public ConfigFileReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}
	
	public String getDriverPath(){
		String driverPath = properties.getProperty("driverPath");
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");		
	}
	
	public long getImplicitlyWait() {		
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
		else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");		
	}
	
	public String getApplicationUrl() {
		String url = properties.getProperty("URL");
		if(url != null) return url;
		else throw new RuntimeException("url not specified in the Configuration.properties file.");
	}

	public String getBrowser() {
		// TODO Auto-generated method stub
		String Browser = properties.getProperty("BrowserName");
		if(Browser != null) return Browser;
		else throw new RuntimeException("Browser not specified in the Configuration.properties file.");
	}
	
	public String getExcel() {
		// TODO Auto-generated method stub
		String Excel = properties.getProperty("Path");
		System.out.println(Excel);
		if(Excel != null) return Excel;
		else throw new RuntimeException("Excel not specified in the Configuration.properties file.");
	}
	
	public String getExtentReportPath() {
		// TODO Auto-generated method stub
		String ReportPath = properties.getProperty("ExtentReportPath");
		if(ReportPath != null) return ReportPath;
		else throw new RuntimeException("ReportPath not specified in the Configuration.properties file.");
	}
	
	public String getExtentReportFormat() {
		// TODO Auto-generated method stub
		String Reportformat = properties.getProperty("ExtentReportFormat");
		if(Reportformat != null) return Reportformat;
		else throw new RuntimeException("ReportFormat not specified in the Configuration.properties file.");
	}
	
	
}
