-- CRUD checkinfoa -- 

-- select checkinfoa 
drop procedure if exists checkinfoa_select;
create procedure checkinfoa_select()
begin 
 select * from checkinfoa where state=1 order by id desc;
end; 

-- call checkinfoa_select();

-- insert checkinfoa 
drop procedure if exists checkinfoa_insert;
create procedure checkinfoa_insert(in FlowNumber varchar(100),in PropertyNumber varchar(100),in SerialNumber varchar(100),in ResponsibilityDepartment varchar(100),in MachineLocation varchar(100),in Model varchar(100),in SystemInfo varchar(100),in IPAdd varchar(100),in MachineUsage varchar(100),in MantainceStaff varchar(100),in BackupContent varchar(100),in BackupContentChange1 varchar(100),in BackupContentChange2 varchar(100),in FileDirectory varchar(100),in FileDirectoryChange1 varchar(100),in FileDirectoryChange2 varchar(100),in BackupPeriod varchar(100),in BackupPeriodChange1 varchar(100),in BackupPeriodChange2 varchar(100),in `year` int,in MachineInfoID int,inout result varchar(100))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;
insert into checkInfoA(FlowNumber,PropertyNumber,SerialNumber,ResponsibilityDepartment,MachineLocation,Model,SystemInfo,IPAdd,MachineUsage,MantainceStaff,BackupContent,BackupContentChange1,BackupContentChange2,FileDirectory,FileDirectoryChange1,FileDirectoryChange2,BackupPeriod,BackupPeriodChange1,BackupPeriodChange2,`year`,MachineInfoID,state)values(FlowNumber,PropertyNumber,SerialNumber,ResponsibilityDepartment,MachineLocation,Model,SystemInfo,IPAdd,MachineUsage,MantainceStaff,BackupContent,BackupContentChange1,BackupContentChange2,FileDirectory,FileDirectoryChange1,FileDirectoryChange2,BackupPeriod,BackupPeriodChange1,BackupPeriodChange2,`year`,MachineInfoID,'1');
 if tran_error<>0 then
  rollback;
  set result='false';
 ELSE 
  commit;
  set result='true';
 end if;
end; 

/*
set @result='';
call checkinfoa_insert(1,1,1,'信息系统部','','IBM3650','','159.226.100.101-125','园区IP','刘士函','acl','','','是','','','月','','','2015',8,@result);
select @result;
*/

-- delete checkinfoa
drop procedure if exists checkinfoa_delete; 
create procedure checkinfoa_delete(in id int,in FlowNumber varchar(100),in PropertyNumber varchar(100),inout result varchar(500))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;
  if EXISTS (select * from checkinfoa where state='1' and (checkinfoa.id=id or (checkinfoa.id=id and checkinfoa.FlowNumber=FlowNumber) or (checkinfoa.id=id and checkinfoa.PropertyNumber=PropertyNumber))) then
    update checkinfoa set state='2' where state='1' and (checkinfoa.id=id or (checkinfoa.id=id and checkinfoa.FlowNumber=FlowNumber) or (checkinfoa.id=id and checkinfoa.PropertyNumber=PropertyNumber));
    set result='true';
  ELSE 
     set result='true';
  end if;
  if tran_error<>0 THEN 
    rollback;
    set result='false';
  else 
    COMMIT;
    -- set result='删除成功';
   end if;
end;

/*
set @result='';
call checkinfoa_delete(1,null,null,@result);
select @result;


select * from checkinfoa;
*/

-- update checkinfoa
drop procedure if exists checkinfoa_update;
create procedure checkinfoa_update(in id int,in FlowNumber varchar(100),in PropertyNumber varchar(100),in SerialNumber varchar(100),in ResponsibilityDepartment varchar(100),in MachineLocation varchar(100),in Model varchar(100),in SystemInfo varchar(100),in IPAdd varchar(100),in MachineUsage varchar(100),in MantainceStaff varchar(100),in BackupContent varchar(100),in BackupContentChange1 varchar(100),in BackupContentChange2 varchar(100),in FileDirectory varchar(100),in FileDirectoryChange1 varchar(100),in FileDirectoryChange2 varchar(100),in BackupPeriod varchar(100),in BackupPeriodChange1 varchar(100),in BackupPeriodChange2 varchar(100),in `year` int,in MachineInfoID int,inout result varchar(100))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;

 start transaction;
    update checkinfoa set FlowNumber=FlowNumber,PropertyNumber=PropertyNumber,SerialNumber=SerialNumber,ResponsibilityDepartment=ResponsibilityDepartment,MachineLocation=MachineLocation, Model=Model, SystemInfo=SystemInfo, IPAdd=IPAdd, MachineUsage=MachineUsage, MantainceStaff=MantainceStaff,BackupContent=BackupContent,BackupContentChange1=BackupContentChange1,BackupContentChange2=BackupContentChange2,FileDirectory=FileDirectory,FileDirectoryChange1=FileDirectoryChange1,FileDirectoryChange2=FileDirectoryChange2,BackupPeriod=BackupPeriod,BackupPeriodChange1=BackupPeriodChange1,BackupPeriodChange2=BackupPeriodChange2,`year`=`year`,MachineInfoID=MachineInfoID where checkinfoa.id=id;
    if tran_error<>0 then
       set result='false';
        rollback;
     ELSE 
      commit;
      set result='true';
     end if;
end; 

/*
set @result='';
call checkinfoa_update(1,1,1,'信息系统部','','IBM3650','','159.226.100.101-125','园区IP','刘士函','acl','','','是','','','月','','',@result);
select @result;
*/



