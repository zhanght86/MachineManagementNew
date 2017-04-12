-- search target checkinfo a 
delimiter //
Drop procedure if exists checkinfob_search_by_id;
create procedure checkinfob_search_by_id(in `id` int,in `year` int)
begin 
   declare tempid int default 0;
   declare FlowNumber varchar(100);
   declare PropertyNumber varchar(100);
   declare ResponsibilityDepartment varchar(100);
   declare MachineLocation varchar(100);
   declare Model varchar(100);
   declare SystemInfo varchar(100);
   declare IPAdd varchar(100);
   declare MachineUsage varchar(100);

   select checkinfob.id into tempid from checkinfob where checkinfob.MachineInfoID=`id` and checkinfob.year=`year`;

 if tempid=0 THEN 
   select machineinfo.id, machineinfo.PropertyNumber,machineinfo.Department,machineinfo.MachineLocation,machineinfo.Model, machineinfo.SystemInfo,machineinfo.IPAdd,machineinfo.MachineUsage into tempid,PropertyNumber,ResponsibilityDepartment,MachineLocation,Model,SystemInfo,IPAdd,MachineUsage from machineinfo where machineinfo.id=`id`;
   if tempid<>0 then
      set @result='';
      call checkinfob_insert('',PropertyNumber,'',ResponsibilityDepartment,MachineLocation,Model,SystemInfo,IPAdd,MachineUsage,'',`year`,`id`,'',@result);
   end IF;
 end IF;
 select * from checkinfob where checkinfob.MachineInfoID=`id` and checkinfob.year=`year`;
end//

delimiter ;
/*

call checkinfob_search_by_id(1,'2015');

*/