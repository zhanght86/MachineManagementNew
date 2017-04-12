package MachineManagement.DataBaseHelper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private Connection con;
	private CallableStatement cs;
	private Statement statement;
	
 	public Database() {
		try {
			
			String server="";
			String port="";
			String instancename="";
			String username="";
			String password="";
			try
			{
//				Properties prop = new Properties();
//				InputStream in = getClass().getResourceAsStream("config.properties");
//				prop.load(in);
//				in.close();
//	           System.out.println( prop.getProperty("DB.ip"));
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				System.out.println(ex.toString());
			}

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://159.226.100.85:3306/machinemanagement?autoReconnect=true","root", "123456");//Connects to Database
			this.statement=this.con.createStatement(); //声明statement
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		return this.con;
	}
 
	public CallableStatement getCallableStatement()
	{
		return this.cs;
	}
	
	public Statement getStatement()
	{
	   return this.statement;
	}
	
	public ResultSet query(String sql) {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public int insert(String sql) {
		try {
			Statement st = con.createStatement();
			int rs = st.executeUpdate(sql);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	

	public int update(String sql) {
		try {
			Statement st = con.createStatement();
			int rs = st.executeUpdate(sql);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//call a sp for a resultset 
	public ResultSet query_sp(String sp_name)
	{
		ResultSet result=null;
		String ExecuteResult="";
		if(sp_name==""||sp_name==null)
		{
			return null;
		}
	
	    try
	    {
	         CallableStatement cs=this.con.prepareCall(sp_name);
	         result=cs.executeQuery();
	    }
		catch(Exception ex)
	    {
			ExecuteResult=ex.toString();
		    System.out.println(ex.toString());
	    }
		finally
		{
			if(ExecuteResult!="")
			{
			   result=null;
			}
			else{}
		}
		return result;
	}
	
	//call a sp for a nonquery
	public String nonquery_sp(String sp_name,String[] Paras,Boolean hasReturnValue )
	{
		String returnValue="";
		String ExecuteResult="";
		if(sp_name==""||sp_name==null)
		{
			return null;
		}
		try
		{
	         CallableStatement cs=this.con.prepareCall(sp_name);
	         if(cs.execute())
	         {
	        	 returnValue=cs.getString(0);
	         }
	         else
	         {
	        	 returnValue="";
	         }
		}
		catch(Exception ex)
		{
			ExecuteResult=ex.toString();
		    System.out.println(ex.toString());
		}
		finally
		{
			if(ExecuteResult!="")
			{
				returnValue=null;
			}
			else{}
		}

		return returnValue;
	}
	
	public CallableStatement ParameterBuilder(CallableStatement cs,String[] paras,Boolean hasReturnValue)
	{
		 CallableStatement finalCS=cs;
		 if(paras.length==0)
		 {
		   return null;
		 }
		 try
		 {
			 if(hasReturnValue)
			 {
				 for (int i=0;i<paras.length;i++)
				 {
					 
				 }
			 }
			 else
			 {
				 
			 }


		 }catch(Exception ex)
		 {
			 
		 }
		 
		 
		 return finalCS;
	
	}
	
	public void conClose() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}	
