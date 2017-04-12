-- CRUD checkrecordb -- 
-- select checkrecordb 
drop procedure if exists checkinfob_select;
create procedure checkinfob_select()
begin 
 select * from checkinfob where state='1';
end;

-- call checkrecordb_select();

-- insert checkrecordb 
drop procedure if exists checkinfob_insert;
create procedure checkinfob_insert(in FlowNumber varchar(100),in PropertyNumber varchar(100),in SerialNumber varchar(100),in ResponsibilityDepartment varchar(100),in MachineLocation varchar(100),in Model varchar(100),in SystemInfo varchar(100),in IPAdd varchar(100),in MachineUsage varchar(100),in MantainceStaff varchar(100),in `year` int,in MachineInfoID int,in Comments varchar(500),inout result varchar(100))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;
  insert into checkinfob(FlowNumber,PropertyNumber,SerialNumber,ResponsibilityDepartment,MachineLocation,Model,SystemInfo,IPAdd,MachineUsage,MantainceStaff,`year`,MachineInfoID,Comments,state)values(FlowNumber,PropertyNumber,SerialNumber,ResponsibilityDepartment,MachineLocation,Model,SystemInfo,IPAdd,MachineUsage,MantainceStaff,`year`,MachineInfoID,Comments,'1');
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
call checkrecordb_insert(1,1,'','','','','','','','',@result);
select @result;

select * from checkrecordb;

*/

-- delete checkrecordb
drop procedure if exists checkrecordb_delete; 
create procedure checkrecordb_delete(in id int,in FlowNumber varchar(100),in PropertyNumber varchar(100),inout result varchar(500))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;
  if EXISTS (select * from checkinfob where state='1' and (checkinfob.id=id or (checkinfob.id=id and checkinfob.FlowNumber=FlowNumber) or (checkinfob.id=id and checkinfob.PropertyNumber=PropertyNumber))) then
    update checkinfob set state='2' where state='1' and (checkinfob.id=id or (checkinfob.id=id and checkinfob.FlowNumber=FlowNumber) or (checkinfob.id=id and checkinfob.PropertyNumber=PropertyNumber));
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
call checkrecordb_delete(1,null,null,@result);
select @result;

select * from checkrecordb;
*/

-- update checkrecordb
drop procedure if exists checkinfob_update;
create procedure checkinfob_update(in `id` int,in FlowNumber varchar(100),in PropertyNumber varchar(100),in SerialNumber varchar(100),in ResponsibilityDepartment varchar(100),in MachineLocation varchar(100),in Model varchar(100),in SystemInfo varchar(100),in IPAdd varchar(100),in MachineUsage varchar(100),in MantainceStaff varchar(500),in `year` int,in MachineInfoID int,in Comments varchar(500),inout result varchar(100))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;

 start transaction;
    if exists (select * from checkinfob where checkinfob.id=id and state='1') then
     update checkinfob set FlowNumber=FlowNumber,PropertyNumber=PropertyNumber,SerialNumber=SerialNumber,ResponsibilityDepartment=ResponsibilityDepartment,MachineLocation=MachineLocation, Model=Model, SystemInfo=SystemInfo, IPAdd=IPAdd, MachineUsage=MachineUsage, MantainceStaff=MantainceStaff,`year`=`year`,MachineInfoID=MachineInfoID,Comments=Comments where checkinfob.id=`id` and state='1';
     set result='true';
   else 
      set result='false';
    end if;

    if tran_error<>0 then
       set result='false';
        rollback;
     ELSE 
      commit;
     end if;
end;

/*
set @result='';
call checkinfob_update(182,'FlowNumber','PropertyNumber','SerialNumber','ResponsibilityDepartment','MachineLocation','Model','SystemInfo','IPAdd','MachineUsage','MantainceStaff',2015,462,'',@result);
select @result;

select * from checkrecordb;
*/



