-- search target checkinfo a 
delimiter //
Drop procedure if exists checkinfoa_search_by_id;
create procedure checkinfoa_search_by_id(in `id` int,in `year` int)
begin 
   declare tempid int default 0;
   declare PropertyNumber varchar(100);
   declare MachineLocation varchar(100);
   declare ResponsibilityDepartment varchar(100);
   declare IPAdd varchar(100);
   declare Model varchar(100);
   declare MachineUsage varchar(100);
   declare SystemInfo varchar(100);
   select checkinfoa.`id` into tempid from checkinfoa inner join machineinfo on checkinfoa.MachineInfoID=machineinfo.`id` where machineinfo.`id`=`id` and checkinfoa.`year`=`year`;
 if tempid=0 THEN 
   select machineinfo.`id`, machineinfo.PropertyNumber,machineinfo.Department,machineinfo.MachineLocation,machineinfo.Model, machineinfo.SystemInfo,machineinfo.IPAdd,machineinfo.MachineUsage into tempid,PropertyNumber,ResponsibilityDepartment,MachineLocation,Model,SystemInfo ,IPAdd,MachineUsage from machineinfo where machineinfo.`id`=`id`;
   if tempid<>0 then
      set @result='';
      call checkinfoa_insert('',PropertyNumber,'',ResponsibilityDepartment,MachineLocation,Model,SystemInfo,IPAdd,MachineUsage,'','','','','','','','','','',`year`,`id`,@result);
   end IF;
 end IF;
 select * from checkinfoa where MachineInfoID=`id` and checkinfoa.`year`=`year`;
end//

delimiter ;
/*

call checkinfoa_search_by_id(2,'2015');

*/