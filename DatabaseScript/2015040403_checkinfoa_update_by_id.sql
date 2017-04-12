-- search target checkinfo a 
delimiter //
Drop procedure if exists checkinfoa_update_by_id;
create procedure checkinfoa_update_by_id(in `id` int,in FlowNumber varchar(100),in PropertyNumber varchar(100),in SerialNumber varchar(100),in ResponsibilityDepartment varchar(100),in MachineLocation varchar(100),in Model varchar(100),in SystemInfo varchar(100),in IPAdd varchar(100),in MachineUsage varchar(100),in MantainceStaff varchar(100),in BackupContent varchar(100),in BackupContentChange1 varchar(100),in BackupContentChange2 varchar(100),in FileDirectory varchar(100),in FileDirectoryChange1 varchar(100),in FileDirectoryChange2 varchar(100),in BackupPeriod varchar(100),in BackupPeriodChange1 varchar(100),in BackupPeriodChange2 varchar(100),in `year` int,in MachineInfoID int,inout result varchar(100))
begin 
   declare tempid int default 0;
   select checkinfoa.`id` into tempid from checkinfoa inner join machineinfo on checkinfoa.MachineInfoID=machineinfo.`id` where machineinfo.`id`=MachineInfoID and checkinfoa.`id`=`id` and checkinfoa.`year`=`year`;
if tempid<>0 THEN 
    call checkinfoa_update(`id`,FlowNumber,PropertyNumber,SerialNumber,ResponsibilityDepartment,MachineLocation,Model,SystemInfo,IPAdd,MachineUsage,MantainceStaff,BackupContent,BackupContentChange1,BackupContentChange2,FileDirectory,FileDirectoryChange1,FileDirectoryChange2,BackupPeriod,BackupPeriodChange1,BackupPeriodChange2,`year`,MachineInfoID,result);
 end IF;
 -- select * from checkinfoa where MachineInfoID=`id` and checkinfoa.`year`=`year`;
end//

delimiter ;

/*

set @result='';
call checkinfoa_update_by_id(1,'FlowNumber','PropertyNumber','SerialNumber','ResponsibilityDepartment','MachineLocation','Model','SystemInfo','IPAdd','MachineUsage','MantainceStaff','BackupContent','BackupContentChange1','BackupContentChange2','FileDirectory','FileDirectoryChange1','FileDirectoryChange2','BackupPeriod','BackupPeriodChange1','BackupPeriodChange2',2015,1,@result);
select @result;

*/