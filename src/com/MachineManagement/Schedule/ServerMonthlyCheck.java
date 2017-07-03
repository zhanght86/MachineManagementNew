package com.MachineManagement.Schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;  
import org.springframework.stereotype.Component;

import com.MachineManagement.Component.MachineMonthlyCheck;  

@Component
public class ServerMonthlyCheck {

	
	@Autowired(required = true)
	private MachineMonthlyCheck machineMonthlyCheck;
	
	
	//每月10日上午10:15触发
	@Scheduled(cron = "0 15 10 10 * ?")  
	public void doCheck()
	{
//		machineMonthlyCheck.checkByUserId();
	}
	
}
