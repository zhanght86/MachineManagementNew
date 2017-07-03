package com.Support;


public class Constant {

	private static PropertiesLoader properties;

	public static String getPropertyValue(String propertiesPath,String propertiesKey)
	{
		String value="";
		try
		{
			properties=new PropertiesLoader(propertiesPath);
			value=properties.getValue(propertiesKey);
		}
		catch(Exception ex)
		{
		  ex.printStackTrace();
		  value="";
		}
		
		return value;
	}
	
}
