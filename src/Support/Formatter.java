package Support;

import java.text.ParseException;

public class Formatter {
	
	public static java.util.Date DateTimeFormatter(String indateTime) throws ParseException
	{

		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 

		return sdf.parse(indateTime);
	}
	
	public static String dbNull(Object object)
	{
		String result="";
		
		if(object==null||"null".equalsIgnoreCase(object.toString()))
		{
			result="";
		}
		else
		{
			result=object.toString();
		}
		
		return result;
	}
	
	

}
