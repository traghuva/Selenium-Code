package verify;

import java.net.HttpURLConnection;
import java.net.URL;

public class VerifyLink {
	
	
	public void verifyLinkActive(String linkUrl)
	
	{
        try 
        {           
           URL url = new URL(linkUrl);
           
           HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
           
           httpURLConnect.setConnectTimeout(3000);
           
           httpURLConnect.connect();
           
           if(httpURLConnect.getResponseCode()==200)
           {
               System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
           }
          if(httpURLConnect.getResponseCode()!=200)  
           {
               System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());// + " - "+ HttpURLConnection.HTTP_NO_CONTENT);
            }
        } catch (Exception e) {
        	
        }
    } 

}
