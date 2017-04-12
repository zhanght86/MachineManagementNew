-- search target checkinfo a 
delimiter //
Drop procedure if exists checkinfob_update_by_id;
create procedure checkinfob_update_by_id(in `id` int,in FlowNumber varchar(100),in PropertyNumber varchar(100),in SerialNumber varchar(100),in ResponsibilityDepartment varchar(100),in MachineLocation varchar(100),in Model varchar(100),in SystemInfo varchar(100),in IPAdd varchar(100),in MachineUsage varchar(100),in MantainceStaff varchar(100),in `year` int,in MachineInfoID int,in Comments varchar(500),inout result varchar(100))
begin 
   declare tempid int default 0;
   select checkinfob.`id` into tempid from checkinfob inner join machineinfo on checkinfob.MachineInfoID=machineinfo.`id` where machineinfo.`id`=MachineInfoID and checkinfob.`id`=`id` and checkinfob.`year`=`year`;
if tempid<>0 THEN 
    call checkinfob_update(`id`,FlowNumber,PropertyNumber,SerialNumber,ResponsibilityDepartment,MachineLocation,Model,SystemInfo,IPAdd,MachineUsage,MantainceStaff,`year`,MachineInfoID,Comments,result);
 end IF;
end//

delimiter ;

/*

set @result='';
call checkinfob_update_by_id(1,'FlowNumber','PropertyNumber','SerialNumber','ResponsibilityDepartment','MachineLocation','Model','SystemInfo','IPAdd','MachineUsage','MantainceStaff','BackupContent',2015,1,'',@result);
select @result;

*/