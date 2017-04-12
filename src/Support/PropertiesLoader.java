package Support;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
	
	private String filePath;
	private Properties properties;
	
	public PropertiesLoader(String FilePath)
	{
      this.filePath=FilePath;
      this.properties= new Properties(); 
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getValue(String key)
	{
	     InputStream in = Object.class.getResourceAsStream(this.filePath);   
	     try
	     {   
	    	 this.properties.load(in);    
	     } 
	     catch (Exception e) 
	     {   
	    	 e.printStackTrace();   
	     }  
	     
	     return properties.getProperty(key);
	}

}
