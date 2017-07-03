package com.las.MachineManagement.BeanOther;

public class CheckInfo {
	
	private  String type;
	private int id;
	private int year;
	
	public CheckInfo(){}
	
	public CheckInfo(String Type,int Id,int Year)
	{
		this.type=Type;
		this.id=Id;
		this.year=Year;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	 

}
