-- 2015040303_machineinfo_crud.sql
-- CRUD machineinfo -- 

-- select machineinfo 
drop procedure if exists machineinfo_select;
create procedure machineinfo_select()
begin 
 declare currentmonth int;
 set currentmonth=MONTH(CURRENT_DATE());
 select machineinfo.*,
   IFNULL((select checkrecorda.checkstate from checkrecorda inner join checkinfoa on checkrecorda.CheckInfoID=checkinfoa.id inner join machineinfo mi on mi.id=checkinfoa.MachineInfoID where mi.id=machineinfo.id and checkrecorda.MonthNumber=currentmonth),'0') as checkstatea   
  ,IFNULL((select checkrecordb.checkstate from checkrecordb inner join checkinfob on checkrecordb.CheckInfoID=checkinfob.id inner join machineinfo mi on mi.id=checkinfob.MachineInfoID where mi.id=machineinfo.id and checkrecordb.MonthNumber=currentmonth),'0') as checkstateb   
 from machineinfo 

 where machineinfo.state='1' order by machineinfo.id DESC;
end; 

-- call machineinfo_select();

-- insert machineinfo 
drop procedure if exists machineinfo_insert;
create procedure machineinfo_insert(in PropertyNumber varchar(100),in MachineLocation varchar(100),in Model varchar(100),in IPAdd varchar(100),in MachineUsage varchar(100),in Department varchar(100),in Responsible varchar(100),in ResponsibleEmail varchar(200), in ResponsibleContactNumber varchar(100),in SystemInfo varchar(100),in PurchaseTime datetime,in Price varchar(100),in Project varchar(5000),in Comments varchar(5000),in MoveInTime datetime,in Purchaser varchar(100),in PurchaseMethod varchar(100),in Supplier varchar(100),in SupplierContact varchar(100),in SupplierContactNumber varchar(100),in MachineType varchar(100),in userid int,in responsibleid int,inout result varchar(100))
begin 
 declare newmachineinfoid int default 0;
 declare tran_error int default 0;
 declare tempusername varchar(100);
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;
  select username into tempusername from users where users.id=userid;
  insert into MachineInfo(PropertyNumber,MachineLocation ,Model ,IPAdd ,MachineUsage ,Department ,Responsible,ResponsibleEmail ,ResponsibleContactNumber,SystemInfo ,PurchaseTime,Price,Project,Comments,Registrant,MoveInTime,UpdateTime,Purchaser,PurchaseMethod,Supplier,SupplierContact,SupplierContactNumber,MachineType,state)values(PropertyNumber,MachineLocation ,Model ,IPAdd ,MachineUsage ,Department ,Responsible,ResponsibleEmail,ResponsibleContactNumber ,SystemInfo ,PurchaseTime,Price,Project,Comments,tempusername,MoveInTime,now(),Purchaser,PurchaseMethod,Supplier,SupplierContact,SupplierContactNumber,MachineType,'1');
  set  newmachineinfoid=LAST_INSERT_ID();
 insert into users_machineinfo_r (machineinfoid,userid,state) values (newmachineinfoid,userid,'1');  
 insert into users_machineinforesponsible_r (machineinfoid,userid,state) values (newmachineinfoid,responsibleid,'1');  
 insert into TransferRecord(MachineInfoID,PreOwner,PreOwnerEmail,CurOwner,CurOwnerEmail,Preid,HappenTime,state) values (newmachineinfoid,Responsible,ResponsibleEmail,Responsible,ResponsibleEmail,0,now(),'1');
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
call machineinfo_insert('02-0806050011','a04 6=6','IBM X3250 M2','124.16.154.6','网络监测评价','系统部','刘建华，张志雄','xx@xxx.com','win2k3',now(),'价格','项目',now(),@result);
select @result;

select * from machineinfo;
*/

-- delete machineinfo
drop procedure if exists machineinfo_delete; 
create procedure machineinfo_delete(in id int,in PropertyNumber varchar(100),inout result varchar(500))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;
  if EXISTS (select * from machineinfo where state='1' and (machineinfo.id=id or (machineinfo.id=id and machineinfo.PropertyNumber=PropertyNumber))) then
    update machineinfo set state='2',UpdateTime=now() where state='1' and (machineinfo.id=id or (machineinfo.id=id and machineinfo.PropertyNumber=PropertyNumber));
    update checkinfoa set state='2' where checkinfoa.MachineInfoID=id;
    update checkinfob set state='2' where checkinfob.MachineInfoID=id;
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
call machineinfo_delete(1,'02-080605001',@result);
select @result;

select * from machineinfo;
*/

-- update machineinfo
drop procedure if exists machineinfo_update;
create procedure machineinfo_update(in id int,in ProrertyNumber varchar(100),in MachineLocation varchar(100),in Model varchar(100),in IPAdd varchar(100),in MachineUsage varchar(100),in Department varchar(100),in Responsible varchar(100),in ResponsibleEmail varchar(200), in ResponsibleContactNumber varchar(100),in SystemInfo varchar(100),in PurchaseTime datetime,Price varchar(100),Project varchar(5000),in Comments varchar(5000),MoveInTime datetime,in Purchaser varchar(100),in PurchaseMethod varchar(100),in Supplier varchar(100),in SupplierContact varchar(100),in SupplierContactNumber varchar(100),in MachineType varchar(100),in userid int,inout result varchar(100))
begin 

 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;

 start transaction;
    update MachineInfo set PropertyNumber=ProrertyNumber,MachineLocation=MachineLocation,Model=Model,IPAdd=IPAdd, MachineUsage=MachineUsage, Department=Department, Responsible=Responsible,ResponsibleEmail=ResponsibleEmail,ResponsibleContactNumber=ResponsibleContactNumber, SystemInfo=SystemInfo, PurchaseTime=PurchaseTime,Price=Price,Project=Project,Comments=Comments,MoveInTime=MoveInTime,UpdateTime=now(),Purchaser=Purchaser,PurchaseMethod=PurchaseMethod,Supplier=Supplier,SupplierContact=SupplierContact,SupplierContactNumber=SupplierContactNumber,MachineType=MachineType where MachineInfo.id=id and state='1';
    update checkinfoa set checkinfoa.PropertyNumber=ProPertyNumber,checkinfoa.MachineLocation=MachineLocation,checkinfoa.Model=Model,checkinfoa.IPAdd=IPAdd,checkinfoa.MachineUsage=MachineUsage,checkinfoa.ResponsibilityDepartment=Department,checkinfoa.SystemInfo=SystemInfo where checkinfoa.MachineInfoID=id;
    update checkinfob set checkinfob.FlowNumber=FlowNumber,checkinfob.PropertyNumber=PropertyNumber,checkinfob.ResponsibilityDepartment=Department,checkinfob.MachineLocation=MachineLocation, checkinfob.Model=Model, checkinfob.SystemInfo=SystemInfo, checkinfob.IPAdd=IPAdd, checkinfob.MachineUsage=MachineUsage where checkinfob.MachineInfoID=`id` and state='1';
    update users_machineinforesponsible_r set users_machineinforesponsible_r.userid=userid where users_machineinforesponsible_r.machineinfoid=id;
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
call machineinfo_update(1,'02-08060123125001','a04 6=6','IBM X3250 M2','124.16.154.6','网络监测评价','系统部','刘建华，张志雄','win2k3',now(),'123','pro1',now(),@result);
select @result;
*/




-- 2015040304_checkinfoa_crud.sql
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




-- 2015040305_checkinfob_crud.sql
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
call checkrecordb_update(2,1,1,'','','','','','','','',@result);
select @result;

select * from checkrecordb;
*/




-- 2015040306_checkrecorda_crud.sql
-- CRUD checkrecorda -- 

-- select checkrecorda 
drop procedure if exists checkrecorda_select;
create procedure checkrecorda_select()
begin 
 select * from checkrecorda where state='1' order by id DESC;
end; 

-- call checkrecorda_select();

-- insert checkrecorda 
drop procedure if exists checkrecorda_insert;
create procedure checkrecorda_insert(in CheckInfoID int,in os varchar(100),in MonthNumber int,in Application varchar(100),in DataBaseCheck varchar(100),in 360Check varchar(100),in Antivirus varchar(100),in AccountNormal varchar(100),in AccountAbnormal varchar(100),in EventLog varchar(100),in WebLog varchar(100),in DataBaseLog varchar(100),in HardDriverUsage varchar(100),in OSResponsibleSingnature varchar(100),in OSAdminsitratorSignature varchar(100),inout result varchar(100))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;
  insert into checkrecorda(CheckInfoID,os,MonthNumber,Application,DataBaseCheck,360Check,Antivirus,AccountNormal,AccountAbnormal,EventLog,WebLog,DataBaseLog,HardDriverUsage,OSResponsibleSingnature,OSAdminsitratorSignature,checkstate,state)values(CheckInfoID,os,MonthNumber,Application,DataBaseCheck,360Check,Antivirus,AccountNormal,AccountAbnormal,EventLog,WebLog,DataBaseLog,HardDriverUsage,OSResponsibleSingnature,OSAdminsitratorSignature,'0','1');
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
call checkrecorda_insert(1,'centos',1,'1','0','0','0','1','0','1','1','0','1','刘士函','',@result);
select @result;

select * from checkrecorda;

*/

-- delete checkrecorda
drop procedure if exists checkrecorda_delete; 
create procedure checkrecorda_delete(in id int,in CheckInfoID int,in MouthNumber int,inout result varchar(500))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;
  if EXISTS (select * from checkrecorda where state='1' and (checkrecorda.id=id or (checkrecorda.id=id and CheckInfoID=CheckInfoID) or (checkrecorda.id=id and MouthNumber=MouthNumber))) then
    update checkrecorda set state='2' where state='1' and (checkrecorda.id=id or (checkrecorda.id=id and CheckInfoID=CheckInfoID) or (checkrecorda.id=id and MouthNumber=MouthNumber));
    set result='true';
  ELSE 
     set result='false';
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
call checkrecorda_delete(2,null,null,@result);
select @result;
*/

-- update checkrecorda
drop procedure if exists checkrecorda_update;
create procedure checkrecorda_update(in id int,in CheckInfoID int,in os varchar(100),in MonthNumber int,in Application varchar(100),in DataBaseCheck varchar(100),in 360Check varchar(100),in Antivirus varchar(100),in AccountNormal varchar(100),in AccountAbnormal varchar(100),in EventLog varchar(100),in WebLog varchar(100),in DataBaseLog varchar(100),in HardDriverUsage varchar(100),in OSResponsibleSingnature varchar(100),in OSAdminsitratorSignature varchar(100),inout result varchar(100))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;

 start transaction;
    if exists (select * from checkrecorda where checkrecorda.id=id and state='1') then
     update checkrecorda set CheckInfoID=CheckInfoID,os=os,MonthNumber=MonthNumber,Application=Application, DataBaseCheck=DataBaseCheck, 360Check=360Check, Antivirus=Antivirus, AccountNormal=AccountNormal, AccountAbnormal=AccountAbnormal,EventLog=EventLog,WebLog=WebLog,DataBaseLog=DataBaseLog,HardDriverUsage=HardDriverUsage,OSResponsibleSingnature=OSResponsibleSingnature,OSAdminsitratorSignature=OSAdminsitratorSignature where checkrecorda.id=id and state='1';
     set @checkresult="";
     call checkrecorda_check_by_checkrecordaid(id,@checkresult);
     if @checkresult='' then 
       update checkrecorda set checkstate='1' where checkrecorda.id=id;
     ELSE
       update checkrecorda set checkstate='0' where checkrecorda.id=id;
     end if;
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
call checkrecorda_update(1,1,'centos',1,'1','0','0','0','1','0','1','1','0','1','刘士函','',@result);
select @result;

select * from checkrecorda;
*/




-- 2015040307_checkrecordb_crud.sql
-- CRUD checkrecordb -- 

-- select checkrecordb 
drop procedure if exists checkrecordb_select;
create procedure checkrecordb_select()
begin 
 select * from checkrecordb where state='1' order by id DESC;
end; 

-- call checkrecordb_select();

-- insert checkrecordb 
drop procedure if exists checkrecordb_insert;
create procedure checkrecordb_insert(in CheckInfoID int,in MonthNumber int,in NetworkBackup varchar(100),in HarddriverBackup varchar(100),in LogUploadAnalysis varchar(100),in FirewallCheck varchar(100),in MonthlyFloatAmount varchar(100),in ServerStoppedInfo varchar(100),in Signature varchar(100),inout result varchar(100))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;
  insert into checkrecordb(CheckInfoID ,MonthNumber ,NetworkBackup ,HarddriverBackup ,LogUploadAnalysis ,FirewallCheck ,MonthlyFloatAmount ,ServerStoppedInfo ,Signature,checkstate ,state)values(CheckInfoID ,MonthNumber ,NetworkBackup ,HarddriverBackup ,LogUploadAnalysis ,FirewallCheck ,MonthlyFloatAmount ,ServerStoppedInfo ,Signature,'0','1');
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
create procedure checkrecordb_delete(in id int,in CheckInfoID int,in MouthNumber int,inout result varchar(500))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;
  if EXISTS (select * from checkrecordb where state='1' and (checkrecordb.id=id or (checkrecordb.id=id and checkrecordb.CheckInfoID=CheckInfoID) or (checkrecordb.id=id and checkrecordb.MouthNumber=MouthNumber))) then
    update checkrecordb set state='2' where state='1' and (checkrecordb.id=id or (checkrecordb.id=id and checkrecordb.CheckInfoID=CheckInfoID) or (checkrecordb.id=id and checkrecordb.MouthNumber=MouthNumber));
    set result='true';
  ELSE 
     set result='false';
  end if;
  if tran_error<>0 THEN 
    rollback;
    set result='false';
  else 
    COMMIT;
    -- set result='操作成功';
   end if;
end;

/*
set @result='';
call checkrecordb_delete(1,null,null,@result);
select @result;

select * from checkrecordb;
*/

-- update checkrecordb
drop procedure if exists checkrecordb_update;
create procedure checkrecordb_update(in id int,in CheckInfoID int,in MonthNumber int,in NetworkBackup varchar(100),in HarddriverBackup varchar(100),in LogUploadAnalysis varchar(100),in FirewallCheck varchar(100),in MonthlyFloatAmount varchar(100),in ServerStoppedInfo varchar(100),in Signature varchar(100),inout result varchar(100))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;

 start transaction;
    if exists (select * from checkrecordb where checkrecordb.id=id and state='1') then
     update checkrecordb set CheckInfoID=CheckInfoID,MonthNumber=MonthNumber,NetworkBackup=NetworkBackup,HarddriverBackup=HarddriverBackup, LogUploadAnalysis=LogUploadAnalysis, FirewallCheck=FirewallCheck, MonthlyFloatAmount=MonthlyFloatAmount, ServerStoppedInfo=ServerStoppedInfo, Signature=Signature where checkrecordb.id=id and state='1';
      set @checkresult="";
     call checkrecordb_check_by_checkrecordbid(id,@checkresult);
     if @checkresult='' then 
       update checkrecordb set checkstate='1' where checkrecordb.id=id;
     ELSE
       update checkrecordb set checkstate='0' where checkrecordb.id=id;
     end if;
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
call checkrecordb_update(2,1,1,'','','','','','','','',@result);
select @result;

select * from checkrecordb;
*/




-- 2015040308_transferrecord_crud.sql
-- CRUD transferrecord -- 

-- select transferrecord 
drop procedure if exists transferrecord_select;
create procedure transferrecord_select()
begin 
 select * from transferrecord where state='1' order by id desc;
end; 

-- call transferrecord_select();

-- insert transferrecord 
drop procedure if exists transferrecord_insert;
create procedure transferrecord_insert(in MachineInfoID int,in PreOwner varchar(100),in PreOwnerEmail varchar(100),in CurOwner varchar(100),in CurOwnerEmail varchar(200),in Preid int,in department varchar(200),in reason varchar(500),in HappenTime datetime,inout result varchar(100))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;
  insert into TransferRecord( MachineInfoID,PreOwner,PreOwnerEmail ,CurOwner,CurOwnerEmail ,Preid,department,reason,HappenTime,state) values (MachineInfoID,PreOwner,PreOwnerEmail,CurOwner,CurOwnerEmail,Preid,department,reason,HappenTime,'1');
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
call transferrecord_insert(1,'刘建华，张志雄','刘建华，张志雄',null,now(),@result);
select @result;
*/

-- delete transferrecord
drop procedure if exists transferrecord_delete; 
create procedure transferrecord_delete(in id int,inout result varchar(500))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;
  if EXISTS (select * from transferrecord where transferrecord.id=id and state='1') then
    update transferrecord set state='2' where transferrecord.id=id and state='1';
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
call transferrecord_delete(1,@result);
select @result;
*/

-- update transferrecord
drop procedure if exists transferrecord_update;
create procedure transferrecord_update(in id int,in MachineInfoID int,in PreOwner varchar(100),in PreOwnerEmail varchar(200),in CurOwner varchar(100),in CurOwnerEmail varchar(200),in Preid int,in department varchar(200),in reason varchar(500),inout result varchar(100))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;

 start transaction;
    update transferrecord set MachineInfoID=MachineInfoID,PreOwner=PreOwner,PreOwnerEmail=PreOwnerEmail,CurOwner=CurOwner,CurOwnerEmail=CurOwnerEmail,Preid=Preid,department=department,reason=reason, HappenTime=now() where transferrecord.id=id and state='1';
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
call transferrecord_update(1,1,'刘建华，张志雄','刘建华，张志雄',null,now(),@result);
select @result;
*/




-- 2015040309_users_crud.sql
-- CRUD users -- 

-- select users 
drop procedure if exists users_select;
create procedure users_select()
begin 
 select * from users where users.state='1';
end; 

-- call users_select();

-- insert users 
drop procedure if exists users_insert;
create procedure users_insert(in `name` varchar(100),in age varchar(100),in gender varchar(100),in username varchar(100),in `password` varchar(100),in email varchar(100),in islogin varchar(100),in Department varchar(100),in ContactNumber varchar(100),inout result varchar(100))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;
  insert into users(`name`,age,gender,username,`password`,email,state,Department,ContactNumber,islogin)values(`name`,age,gender,username,`password`,email,'1',Department,ContactNumber,islogin);
 if tran_error<>0 then
  rollback;
  set result='true';
 ELSE 
  commit;
  set result='false';
 end if;
end; 

/*
set @result='';
call users_insert('user1','30','男','user1','123456','user1@163.com','0',@result);
select @result;

select * from users;

*/

-- delete users
drop procedure if exists users_delete; 
create procedure users_delete(in id int,in `name` varchar(100),in email varchar(100),inout result varchar(500))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;
  if EXISTS (select * from users where users.id=id or (users.id=id and users.`name`=`name`) or (users.id=id and users.email=email)) then
    update users set users.state='2' where users.id=id or (users.id=id and users.`name`=`name`) or (users.id=id and users.email=email);
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
call users_delete(1,null,null,@result);
select @result;

select * from users;
*/

-- update users
drop procedure if exists users_update;
create procedure users_update(in id int,in `name` varchar(100),in age varchar(100),in gender varchar(100),in username varchar(100),in `password` varchar(100),in email varchar(100),in islogin varchar(100),in Department varchar(100),in ContactNumber varchar(100),in roleid int,inout result varchar(100))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;

 start transaction;
    if exists (select * from users where users.id=id) then
     update users set `name`=`name`,age=age,gender=gender,username=username, `password`=`password`, email=email, islogin=islogin,Department=Department,ContactNumber=ContactNumber where users.id=id;
     set result='true';
     update users_userrole_r set users_userrole_r.roleid=roleid where users_userrole_r.userid=id;
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
call users_update(2,'user1','30','男','user1','123456','user1@163.com','0',@result);
select @result;

select * from users;
*/




-- 2015040401_checkinfo_history.sql
-- search target checkinfo a 
delimiter //
Drop procedure if exists checkinfo_history;
create procedure checkinfo_history(in checkinfotype varchar(100),in machineid varchar(100),in checkyear varchar(100))
begin 
  declare str1 varchar(100);
  declare str2 varchar(100);
  set str1=CONCAT('%',machineid,'%');
  set str2=CONCAT('%',checkyear,'%');

  (select "A" as type,machineinfo.id as machindid,year as checkyear from checkinfoa inner join machineinfo on machineinfo.id=checkinfoa.MachineInfoID where checkinfoa.state="1" and CONCAT(machineinfo.id) like str1  and CONCAT(checkinfoa.year) like str2) UNION (select "B" as type,machineinfo.id as machindid,year as checkyear from checkinfob inner join machineinfo on machineinfo.id=checkinfob.MachineInfoID where checkinfob.state="1" and CONCAT(machineinfo.id) like str1 and CONCAT(checkinfob.year) like str2) order by machindid desc; 
   
end//
delimiter ;


/*

call checkinfo_history('','1',2015);

*/


-- 2015040402_checkinfoa_search_by_id.sql
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
      call checkinfoa_insert(0,PropertyNumber,'',ResponsibilityDepartment,MachineLocation,Model,SystemInfo,IPAdd,MachineUsage,'','','','','','','','','','',`year`,`id`,@result);
   end IF;
 end IF;
 select * from checkinfoa where MachineInfoID=`id` and checkinfoa.`year`=`year`;
end//

delimiter ;
/*

call checkinfoa_search_by_id(2,'2015');

*/
-- 2015040403_checkinfoa_update_by_id.sql
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
-- 2015040404_checkinfob_search_by_id.sql
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
      call checkinfob_insert('0',PropertyNumber,'',ResponsibilityDepartment,MachineLocation,Model,SystemInfo,IPAdd,MachineUsage,'',`year`,`id`,'',@result);
   end IF;
 end IF;
 select * from checkinfob where checkinfob.MachineInfoID=`id` and checkinfob.year=`year`;
end//

delimiter ;
/*

call checkinfob_search_by_id(1,'2015');

*/
-- 2015040405_checkinfob_update_by_id.sql
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
-- 2015040406_checkrecorda_search_by_id.sql
-- search target checkinfo a 
delimiter //
Drop procedure if exists checkrecorda_search_by_id;
create procedure checkrecorda_search_by_id(in `id` int,in `year` int,in monthnumber int)
begin 
   declare tempid int default 0;
   select checkrecorda.`id` into tempid from checkrecorda inner join checkinfoa on checkinfoa.`id`=checkrecorda.CheckInfoID inner join machineinfo on machineinfo.`id`=checkinfoa.MachineInfoID where machineinfo.`id`=`id` and checkinfoa.`year`=`year` and checkrecorda.MonthNumber=monthnumber;
  if tempid=0 THEN 
     select checkinfoa.`id` into tempid from checkinfoa where checkinfoa.MachineInfoID=`id` and checkinfoa.`year`=`year`;
     set @result='';
     call checkrecorda_insert(tempid,'',monthnumber,'0','0','0','0','','','0','0','0','0','','',@result);
  end IF;
 select checkrecorda.* from checkrecorda inner join checkinfoa on checkrecorda.CheckInfoID=checkinfoa.id where checkinfoa.MachineInfoID=`id` and checkinfoa.`year`=`year` and checkrecorda.MonthNumber=monthnumber;
end//

delimiter ;
/*

call checkrecorda_search_by_id(1,'2015',2);

*/
-- 2015040407_checkrecorda_update_by_id.sql
-- checkrecorda_update_by_id
delimiter //
Drop procedure if exists checkrecorda_update_by_id;
create procedure checkrecorda_update_by_id(in `id` int,in CheckInfoID int,in os varchar(100),in MonthNumber int,in Application varchar(100),in DataBaseCheck varchar(100),in 360Check varchar(100),in Antivirus varchar(100),in AccountNormal varchar(100),in AccountAbnormal varchar(100),in EventLog varchar(100),in WebLog varchar(100),in DataBaseLog varchar(100),in HardDriverUsage varchar(100),in OSResponsibleSingnature varchar(100),in OSAdminsitratorSignature varchar(100),inout result varchar(100))
begin 
   declare tempid int default 0;
   select checkrecorda.`id` into tempid from checkrecorda inner join checkinfoa on checkinfoa.`id`=checkrecorda.CheckInfoID where checkinfoa.`id`=CheckInfoID and checkrecorda.`id`=`id` and checkrecorda.MonthNumber=MonthNumber;
if tempid<>0 THEN 
    call checkrecorda_update(`id`,CheckInfoID,os,MonthNumber,Application,DataBaseCheck,360Check,Antivirus,AccountNormal,AccountAbnormal,EventLog,WebLog,DataBaseLog,HardDriverUsage,OSResponsibleSingnature,OSAdminsitratorSignature,result);
 end IF;
end//

delimiter ;

/*

set @result='';
call checkrecorda_update_by_id(1,1,'os',1,'Application','DataBaseCheck','360Check','Antivirus','AccountNormal','AccountAbnormal','EventLog','WebLog','DataBaseLog','HardDriverUsage','OSResponsibleSingnature','OSAdminsitratorSignature',@result);
select @result;

*/
-- 2015040408_checkrecordb_search_by_id.sql
-- search target checkinfo a 
delimiter //
Drop procedure if exists checkrecordb_search_by_id;
create procedure checkrecordb_search_by_id(in `id` int,in `year` int,in monthnumber int)
begin 
   declare tempid int default 0;
if not exists (select * from checkrecordb inner join checkinfob on checkinfob.id=checkrecordb.CheckInfoID inner join machineinfo on machineinfo.id=checkinfob.MachineInfoID where machineinfo.id=`id` and checkinfob.year=`year` and checkrecordb.MonthNumber=monthnumber) THEN 
     select checkinfob.id into tempid from checkinfob where checkinfob.MachineInfoID=`id` and checkinfob.year=`year`;
     set @result='';
     call checkrecordb_insert(tempid,monthnumber,'','','','','','','',@result);
  end IF;
 select checkrecordb.* from checkrecordb inner join checkinfob on checkrecordb.CheckInfoID=checkinfob.id where checkinfob.MachineInfoID=`id` and checkinfob.year=`year` and checkrecordb.MonthNumber=monthnumber;
end//
delimiter ;


/*

call checkrecordb_search_by_id(1,'2015',1);

*/


-- 2015040409_checkrecordb_update_by_id.sql
-- checkrecordb_update_by_id
delimiter //
Drop procedure if exists checkrecordb_update_by_id;
create procedure checkrecordb_update_by_id(in id int,in CheckInfoID int,in MonthNumber int,in NetworkBackup varchar(100),in HarddriverBackup varchar(100),in LogUploadAnalysis varchar(100),in FirewallCheck varchar(100),in MonthlyFloatAmount varchar(100),in ServerStoppedInfo varchar(100),in Signature varchar(100),inout result varchar(100))
begin 
   declare tempid int default 0;
   select checkrecordb.`id` into tempid from checkrecordb inner join checkinfob on checkinfob.`id`=checkrecordb.CheckInfoID where checkinfob.`id`=CheckInfoID and checkrecordb.`id`=`id` and checkrecordb.MonthNumber=MonthNumber;
if tempid<>0 THEN 
    call checkrecordb_update(`id`,CheckInfoID,MonthNumber,NetworkBackup,HarddriverBackup,LogUploadAnalysis,FirewallCheck,MonthlyFloatAmount,ServerStoppedInfo,Signature,result);
 end IF;
end//

delimiter ;

/*

set @result='';
call checkrecordb_update_by_id(1,1,1,'NetworkBackup','HarddriverBackup','LogUploadAnalysis','FirewallCheck','MonthlyFloatAmount','ServerStoppedInfo','Signature',@result);
select @result;

*/
-- 2015040410_login.sql
-- login 
delimiter //
drop procedure if exists login;
create procedure login(in username varchar(100),in passwd varchar(100),inout result varchar(100))
BEGIN
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
  start transaction;
  set result='';
  select concat(users.id,',',users.username,',',users.`name`) as loginresult into result from users where users.username=username and users.`password`=passwd and state='1';
   if result='' then
    set result='false';
   end if;
  if tran_error<>0 then
  rollback;
  set result='false';
 ELSE 
  commit;
 end if;
end//

delimiter ;

/*
set @result="";
call login('chenzj','7C4A8D09CA3762AF61E59520943DC26494F8941B',@result);
select @result;
*/



-- 2015040411_machineinfo_search_by_id.sql
-- search target machineinfo by id 
delimiter //
Drop procedure if exists machineinfo_search_by_id;
create procedure machineinfo_search_by_id(in `id` int)
begin 
   declare responsible varchar(200);
   declare responsibletemp varchar(200);
   declare emailtemp varchar(200);
   declare emaillist varchar(500);
   declare seplocation int;
   declare sqlstr varchar(2000);
   declare checker Boolean; 

  declare currentmonth int;

  set currentmonth=MONTH(CURRENT_DATE());

   set checker=true;
   select machineinfo.Responsible into responsible from machineinfo where machineinfo.`id`=`id`;
   
   -- 替换掉中文逗号
   set responsible=REPLACE(responsible,'，',',');

   -- 替换掉空格
   set responsible=REPLACE(responsible,' ','');

   while checker do 
    if responsible<>'' then 
     if locate(',',responsible)>0 then 
       set responsibletemp='';
       set emailtemp='';
       set responsibletemp=SUBSTRING(responsible,1,(locate(',',responsible)-1));
       select users.email into emailtemp from users where users.`name`=responsibletemp;
       if emailtemp<>'' then 
         set emaillist=concat(emaillist,emailtemp,';');
       end if;
       set responsible=SUBSTRING(responsible,locate(',',responsible)+1,LENGTH(responsible));
     ELSE
      select users.email into emailtemp from users where users.`name`=responsible;
      set emaillist=concat(emaillist,emailtemp);
      set checker=false;
     end if;
    else 
     set checker=false;
    end if;
 end while;


 select machineinfo.*, 
  IFNULL((select checkrecorda.checkstate from checkrecorda inner join checkinfoa on checkrecorda.CheckInfoID=checkinfoa.id inner join machineinfo mi on mi.id=checkinfoa.MachineInfoID where mi.id=machineinfo.id and checkrecorda.MonthNumber=currentmonth),'0') as checkstatea   
  , IFNULL((select checkrecordb.checkstate from checkrecordb inner join checkinfob on checkrecordb.CheckInfoID=checkinfob.id inner join machineinfo mi on mi.id=checkinfob.MachineInfoID where mi.id=machineinfo.id and checkrecordb.MonthNumber=currentmonth),'0') as checkstateb 
  , users.name as r_name, users.Department as r_department, users.email as r_email,users.ContactNumber as r_contactnumber,users.id as r_id
  from machineinfo 
  inner join users_machineinforesponsible_r umr on umr.machineinfoid=machineinfo.id
  inner join users on users.id=umr.userid
  where machineinfo.id=`id`;

end//

delimiter ;
/*

call machineinfo_search_by_id(1);

*/
-- 2015040412_machineinfo_search.sql
-- machineinfo_search
delimiter //
drop procedure if exists machineinfo_search;
create procedure machineinfo_search(in keyword varchar(100),in pageamount int,in pagecounter int,in showall varchar(10),in searchcondition VARCHAR(2000),in orderstring varchar(100),in userright varchar(5000),inout result varchar(2000))
begin 
 declare str varchar(100);
 declare starter int default 0;
 declare ender int default 1;
 declare totalamount int default 0;
 declare totalpages int default 0;
 declare currentmonth int;

 set currentmonth=MONTH(CURRENT_DATE());
 set str=CONCAT('%',keyword,'%');

 if showall='0'||showall='' then
 set @totalamount=0;
 set @sqlstr=concat("select count(machineinfo.id) into @totalamount ",
                     " from machineinfo ",
                      " inner join users_machineinforesponsible_r umir on umir.machineinfoid=machineinfo.id inner join users u1 on u1.id=umir.userid ",
                     userright,
                     " where machineinfo.state='1' ",
                     " and ( machineinfo.PropertyNumber like '",str,"' or machineinfo.MachineLocation like '",str,"' or machineinfo.Model like '",str,"' or machineinfo.IPAdd like '",str,"' or machineinfo.MachineUsage like '",str,"' or machineinfo.Department like '",str,"' or machineinfo.Responsible like '",str,"' or machineinfo.ResponsibleEmail like '",str,"' or machineinfo.SystemInfo like '",str,"' or machineinfo.PurchaseTime like binary '",str,"' or machineinfo.Price like '",str,"' or machineinfo.Project like '",str,"' or machineinfo.MoveInTime like binary '",str,"' or machineinfo.Comments like '",str,"' or machineinfo.Purchaser like '",str,"' or machineinfo.PurchaseMethod like '",str,"' or machineinfo.Supplier like '",str,"' or machineinfo.SupplierContact like '",str,"' or machineinfo.SupplierContactNumber like '",str,"')",
                     searchcondition,
                     orderstring,
                     " ;");
PREPARE finalsql from @sqlstr;
execute finalsql;
set totalamount=@totalamount;


  if totalamount<>0 then 
      if MOD(totalamount,pageamount)<>0 then 
        set totalpages=(totalamount DIV pageamount)+1;
      else 
       set totalpages=(totalamount DIV pageamount);
      end if;

  ELSE
    set totalpages=0;
  end if;
if pagecounter>totalpages THEN 
 if totalpages<>0 then
  set pagecounter=totalpages; 
 ELSE
   set pagecounter=1;
 end if;
end if;
  set result=concat('{"pagecounter":',pagecounter,',"pageamount":',pageamount,',"totalamount":',totalamount,',"totalpages":',totalpages,'}');


end if;


 if showall='0'||showall='' then
 set @sqlstr =concat("select machineinfo.*,",
                     " IFNULL((select checkrecorda.checkstate from checkrecorda inner join checkinfoa on checkrecorda.CheckInfoID=checkinfoa.id inner join machineinfo mi on mi.id=checkinfoa.MachineInfoID where mi.id=machineinfo.id and checkrecorda.MonthNumber=",currentmonth,"),'0') as checkstatea,",
                     " IFNULL((select checkrecordb.checkstate from checkrecordb inner join checkinfob on checkrecordb.CheckInfoID=checkinfob.id inner join machineinfo mi on mi.id=checkinfob.MachineInfoID where mi.id=machineinfo.id and checkrecordb.MonthNumber=",currentmonth,"),'0') as checkstateb, ", 
                     " u1.name as r_name, u1.Department as r_department, u1.email as r_email,u1.ContactNumber as r_contactnumber,u1.id as r_id ",
                     " from machineinfo ",
                     " inner join users_machineinforesponsible_r umir on umir.machineinfoid=machineinfo.id inner join users u1 on u1.id=umir.userid ",
                     userright,
                     " where machineinfo.state='1' ",
                     " and ( machineinfo.PropertyNumber like '",str,"' or machineinfo.MachineLocation like '",str,"' or machineinfo.Model like '",str,"' or machineinfo.IPAdd like '",str,"' or machineinfo.MachineUsage like '",str,"' or machineinfo.Department like '",str,"' or machineinfo.Responsible like '",str,"' or machineinfo.ResponsibleEmail like '",str,"' or machineinfo.SystemInfo like '",str,"' or machineinfo.PurchaseTime like binary '",str,"' or machineinfo.Price like '",str,"' or machineinfo.Project like '",str,"' or machineinfo.MoveInTime like binary '",str,"' or machineinfo.Comments like '",str,"' or machineinfo.Purchaser like '",str,"' or machineinfo.PurchaseMethod like '",str,"' or machineinfo.Supplier like '",str,"' or machineinfo.SupplierContact like '",str,"' or machineinfo.SupplierContactNumber like '",str,"')",
                     searchcondition,
                     orderstring,
                     "limit ",
                     (pagecounter-1)*pageamount,
                     ",",
                     pageamount,
                     ";");
elseif showall='1' then
 set @sqlstr =concat("select machineinfo.*,",
                     " IFNULL((select checkrecorda.checkstate from checkrecorda inner join checkinfoa on checkrecorda.CheckInfoID=checkinfoa.id inner join machineinfo mi on mi.id=checkinfoa.MachineInfoID where mi.id=machineinfo.id and checkrecorda.MonthNumber=",currentmonth,"),'0') as checkstatea,",
                     " IFNULL((select checkrecordb.checkstate from checkrecordb inner join checkinfob on checkrecordb.CheckInfoID=checkinfob.id inner join machineinfo mi on mi.id=checkinfob.MachineInfoID where mi.id=machineinfo.id and checkrecordb.MonthNumber=",currentmonth,"),'0') as checkstateb, ", 
                     " u1.name as r_name, u1.Department as r_department, u1.email as r_email,u1.ContactNumber as r_contactnumber,u1.id as r_id ",
                     " from machineinfo ",
                     "  inner join users_machineinforesponsible_r umir on umir.machineinfoid=machineinfo.id inner join users u1 on u1.id=umir.userid ",
                     userright,
                     " where machineinfo.state='1' ",
                     " and ( machineinfo.PropertyNumber like '",str,"' or machineinfo.MachineLocation like '",str,"' or machineinfo.Model like '",str,"' or machineinfo.IPAdd like '",str,"' or machineinfo.MachineUsage like '",str,"' or machineinfo.Department like '",str,"' or machineinfo.Responsible like '",str,"' or machineinfo.ResponsibleEmail like '",str,"' or machineinfo.SystemInfo like '",str,"' or machineinfo.PurchaseTime like binary '",str,"' or machineinfo.Price like '",str,"' or machineinfo.Project like '",str,"' or machineinfo.MoveInTime like binary '",str,"' or machineinfo.Comments like '",str,"' or machineinfo.Purchaser like '",str,"' or machineinfo.PurchaseMethod like '",str,"' or machineinfo.Supplier like '",str,"' or machineinfo.SupplierContact like '",str,"' or machineinfo.SupplierContactNumber like '",str,"')",
                     searchcondition,
                     orderstring,
                     ";");
end if;

PREPARE finalsql from @sqlstr;
execute finalsql;
end //


delimiter;
/*

set @result="";
call machineinfo_search('',10,1,'1','',' order by machineinfo.moveintime desc ','',@result);
select @result;

*/

-- 2015040413_transferrecord_search_by_machineid.sql
-- transferrecord_search_by_machineid
delimiter //
drop procedure if exists transferrecord_search_by_machineid;
create procedure transferrecord_search_by_machineid(in machineid int)
begin 
  declare machineowner varchar(200);
  declare responsibleEmail varchar(200);
  declare department varchar(100);
  set machineowner='';

  select machineinfo.Responsible,machineinfo.ResponsibleEmail,machineinfo.Department into machineowner,responsibleEmail,department from machineinfo where machineinfo.`id`=machineid;


    if not exists (select * from transferrecord where MachineInfoID=machineid) then 
      set @result='';
      call transferrecord_insert(machineid,machineowner,responsibleEmail,machineowner,responsibleEmail,0,department,'',now(),@result);
    end if;
   select * from transferrecord where MachineInfoID=machineid and state='1' order by `id` asc; 

end//

delimiter ;


/*

call transferrecord_search_by_machineid(2);

*/

-- 2015040414_transferrecord_add_by_machineid.sql
-- transferrecord_add_by_id
delimiter //
drop procedure if exists transferrecord_add_by_machineid;
create procedure transferrecord_add_by_machineid(in MachineId int,in CurOwner varchar(100),in CurOwnerEmail varchar(200),in department varchar(100),in reason varchar(500),inout result varchar(100))
BEGIN
 declare lastTransferRecordID int;
 declare lastTransferRecordOwner varchar(100);
 declare PreOwnerEmail varchar(200);
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;

 select max(transferrecord.`id`) into lastTransferRecordID from transferrecord where transferrecord.MachineInfoID=MachineId and state=1;
 select transferrecord.CurOwner,transferrecord.CurOwnerEmail into lastTransferRecordOwner,PreOwnerEmail from transferrecord where transferrecord.`id`=lastTransferRecordID;
 set @result='';
 call transferrecord_insert(MachineId,lastTransferRecordOwner,PreOwnerEmail,CurOwner,CurOwnerEmail,lastTransferRecordID,department,reason,now(),@result);

 if tran_error<>0 then
  rollback;
  set result='false';
 ELSE 
  commit;
  set result=@result;
 end if;

end//

delimiter ;

/*
set @result="";
call transferrecord_add_by_machineid(1,'张三1','zhangsan@xx.com',@result);
select @result;
*/
-- 2015040701_emailconfiguration_search_by_userid.sql
-- emailconfiguration_search_by_userid
delimiter //
drop procedure if exists emailconfiguration_search_by_userid;
create procedure emailconfiguration_search_by_userid(in userid int)
BEGIN
 if exists (select * from users where users.id=userid) then 
  select emailconfiguration.*,users.id as userid,emailconfigurationID,users.name as loginname from users inner join emailconfiguration on users.EmailConfigurationID=emailconfiguration.id where users.id=userid;
 ELSE
  select emailconfiguration.*,users.id as userid,emailconfigurationID,users.name as loginname from users inner join emailconfiguration on users.EmailConfigurationID=emailconfiguration.id where users.id=0000;
 end if;
end//

delimiter ;

/*

call emailconfiguration_search_by_userid(1);

*/
-- 2015040702_checkrecorda_check.sql
-- checkrecorda_check
delimiter //

drop procedure if exists checkrecorda_check;
create procedure checkrecorda_check(in machineid int,in `year` int,in `month` int,inout checkresult varchar(5000))
begin 

 declare check_os varchar(100);
 declare check_Application varchar(100);
 declare check_DataBaseCheck varchar(100);
 declare check_360Check varchar(100);
 declare check_Antivirus varchar(100);
 declare check_AccountNormal varchar(100);
 declare check_AccountAbnormal varchar(100);
 declare check_EventLog varchar(100);
 declare check_WebLog varchar(100);
 declare check_DataBaseLog varchar(100);
 declare check_HardDriverUsage varchar(100);
 declare check_OSResponsibleSingnature varchar(100);
 declare check_OSAdminsitratorSignature varchar(100);

 set checkresult='';
 select checkrecorda.os,checkrecorda.Application,checkrecorda.DataBaseCheck,checkrecorda.360Check,checkrecorda.Antivirus,checkrecorda.AccountNormal,checkrecorda.AccountAbnormal,checkrecorda.EventLog,checkrecorda.WebLog,checkrecorda.DataBaseLog,checkrecorda.HardDriverUsage,checkrecorda.OSResponsibleSingnature,checkrecorda.OSAdminsitratorSignature into check_os,check_Application,check_DataBaseCheck,check_360Check,check_Antivirus,check_AccountNormal,check_AccountAbnormal,check_EventLog,check_WebLog,check_DataBaseLog,check_HardDriverUsage,check_OSResponsibleSingnature,check_OSAdminsitratorSignature from checkrecorda inner join checkinfoa on checkrecorda.CheckInfoID=checkinfoa.id where checkinfoa.MachineInfoID=machineid and checkrecorda.MonthNumber=`month` and checkinfoa.year=`year`;

  if check_os='' then set checkresult=concat(checkresult,'操作系统',';'); end if;
  if check_Application='0' then set checkresult=concat(checkresult,'应用程序',';'); end if;
  if check_DataBaseCheck='0' then set checkresult=concat(checkresult,'数据库',';'); end if;
  if check_360Check='0' then set checkresult=concat(checkresult,'360安全卫士',';'); end if;
  if check_Antivirus='0' then set checkresult=concat(checkresult,'杀毒软件',';'); end if;
  if check_AccountNormal='' then set checkresult=concat(checkresult,'正常使用账号',';'); end if;
  if check_AccountAbnormal='' then set checkresult=concat(checkresult,'非正常使用账号',';'); end if;
  if check_EventLog='0' then set checkresult=concat(checkresult,'事件日志',';'); end if;
  if check_WebLog='0' then set checkresult=concat(checkresult,'web日志',';'); end if;
  if check_OSResponsibleSingnature='' then set checkresult=concat(checkresult,'系统责任人签字',';'); end if;
  if check_OSAdminsitratorSignature='' then set checkresult=concat(checkresult,'系统管理员签字',';'); end if;
   

  if checkresult<>'' && RIGHT(checkresult,1)=';' then 
   set checkresult=left(checkresult,LENGTH(checkresult)-1);
  end if;
  
end//

delimiter;


/*
set @result="";
call checkrecorda_check(4,2015,4,@result);
select @result;
*/
-- 2015040801_checkrecordb_check.sql
-- checkrecordb_check
delimiter //

drop procedure if exists checkrecordb_check;
create procedure checkrecordb_check(in machineid int,in `year` int,in `month` int,inout checkresult varchar(5000))
begin 
 declare check_NetworkBackup varchar(100);
 declare check_HarddriverBackup varchar(100);
 declare check_LogUploadAnalysis varchar(100);
 declare check_FirewallCheck varchar(100);
 declare check_MonthlyFloatAmount varchar(100);
 declare check_ServerStoppedInfo varchar(100);
 declare check_Signature varchar(100);

 set checkresult='';
 select checkrecordb.NetworkBackup,checkrecordb.HarddriverBackup,checkrecordb.LogUploadAnalysis,checkrecordb.FirewallCheck,checkrecordb.MonthlyFloatAmount,checkrecordb.ServerStoppedInfo,checkrecordb.Signature
 into check_NetworkBackup,check_HarddriverBackup,check_LogUploadAnalysis,check_FirewallCheck,check_MonthlyFloatAmount,check_ServerStoppedInfo,check_Signature from checkrecordb inner join checkinfob on checkrecordb.CheckInfoID=checkinfob.id where checkinfob.MachineInfoID=machineid and checkrecordb.MonthNumber=`month` and checkinfob.`year`=`year`;

  if check_NetworkBackup='' then set checkresult=concat(checkresult,'网络备份（系统数据）',';'); end if;
  if check_HarddriverBackup='' then set checkresult=concat(checkresult,'光盘备份（系统数据）',';'); end if;
  if check_LogUploadAnalysis='' then set checkresult=concat(checkresult,'日志上在分析',';'); end if;
  if check_FirewallCheck='' then set checkresult=concat(checkresult,'防火墙检查',';'); end if;
  if check_MonthlyFloatAmount='' then set checkresult=concat(checkresult,'本月流量',';'); end if;
  if check_ServerStoppedInfo='' then set checkresult=concat(checkresult,'停止服务情况',';'); end if;
  if check_Signature='' then set checkresult=concat(checkresult,'签字',';'); end if;
  
  if checkresult<>'' && RIGHT(checkresult,1)=';' then 
   set checkresult=left(checkresult,LENGTH(checkresult)-1);
  end if;
  
end//

delimiter;


/*
set @result="";
call checkrecordb_check(4,2015,4,@result);
select @result;
*/
-- 2015040802_checkrecorda_check_by_checkrecordaid.sql
-- checkrecorda_check
delimiter //

drop procedure if exists checkrecorda_check_by_checkrecordaid;
create procedure checkrecorda_check_by_checkrecordaid(in checkrecordid int,inout checkresult varchar(5000))
begin 

 declare check_os varchar(100);
 declare check_MonthNumber int;
 declare check_Application varchar(100);
 declare check_DataBaseCheck varchar(100);
 declare check_360Check varchar(100);
 declare check_Antivirus varchar(100);
 declare check_AccountNormal varchar(100);
 declare check_AccountAbnormal varchar(100);
 declare check_EventLog varchar(100);
 declare check_WebLog varchar(100);
 declare check_DataBaseLog varchar(100);
 declare check_HardDriverUsage varchar(100);
 declare check_OSResponsibleSingnature varchar(100);
 declare check_OSAdminsitratorSignature varchar(100);

 set checkresult='';
 select checkrecorda.os,checkrecorda.MonthNumber,checkrecorda.Application,checkrecorda.DataBaseCheck,checkrecorda.360Check,checkrecorda.Antivirus,checkrecorda.AccountNormal,checkrecorda.AccountAbnormal,checkrecorda.EventLog,checkrecorda.WebLog,checkrecorda.DataBaseLog,checkrecorda.HardDriverUsage,checkrecorda.OSResponsibleSingnature,checkrecorda.OSAdminsitratorSignature into check_os,check_MonthNumber,check_Application,check_DataBaseCheck,check_360Check,check_Antivirus,check_AccountNormal,check_AccountAbnormal,check_EventLog,check_WebLog,check_DataBaseLog,check_HardDriverUsage,check_OSResponsibleSingnature,check_OSAdminsitratorSignature from checkrecorda where checkrecorda.id=checkrecordid;

  if check_os='' then set checkresult=concat(checkresult,'操作系统',';'); end if;
  if check_MonthNumber='0' then set checkresult=concat(checkresult,'应用程序',';'); end if;
  if check_Application='0' then set checkresult=concat(checkresult,'数据库',';'); end if;
  if check_DataBaseCheck='0' then set checkresult=concat(checkresult,'360安全卫士',';'); end if;
  if check_360Check='0' then set checkresult=concat(checkresult,'正常使用账号',';'); end if;
  if check_Antivirus='0' then set checkresult=concat(checkresult,'非正常使用账号',';'); end if;
  if check_AccountNormal='' then set checkresult=concat(checkresult,'事件日志',';'); end if;
  if check_AccountAbnormal='' then set checkresult=concat(checkresult,'web日志',';'); end if;
  if check_EventLog='0' then set checkresult=concat(checkresult,'数据库日志',';'); end if;
  if check_WebLog='0' then set checkresult=concat(checkresult,'磁盘空间情况',';'); end if;
  if check_OSResponsibleSingnature='' then set checkresult=concat(checkresult,'系统责任人签字',';'); end if;
  if check_OSAdminsitratorSignature='' then set checkresult=concat(checkresult,'系统管理员签字',';'); end if;
   

  if checkresult<>'' && RIGHT(checkresult,1)=';' then 
   set checkresult=left(checkresult,LENGTH(checkresult)-1);
  end if;
  
end//

delimiter;


/*
set @result="";
call checkrecorda_check_by_checkrecordaid(1,@result);
select @result;
*/
-- 2015040803_checkrecordb_check_by_checkrecordbid.sql
-- checkrecordb_check_by_checkrecordbid
delimiter //

drop procedure if exists checkrecordb_check_by_checkrecordbid;
create procedure checkrecordb_check_by_checkrecordbid(in checkrecordid int,inout checkresult varchar(5000))
begin 
 declare check_NetworkBackup varchar(100);
 declare check_HarddriverBackup varchar(100);
 declare check_LogUploadAnalysis varchar(100);
 declare check_FirewallCheck varchar(100);
 declare check_MonthlyFloatAmount varchar(100);
 declare check_ServerStoppedInfo varchar(100);
 declare check_Signature varchar(100);

 set checkresult='';
 select checkrecordb.NetworkBackup,checkrecordb.HarddriverBackup,checkrecordb.LogUploadAnalysis,checkrecordb.FirewallCheck,checkrecordb.MonthlyFloatAmount,checkrecordb.ServerStoppedInfo,checkrecordb.Signature
 into check_NetworkBackup,check_HarddriverBackup,check_LogUploadAnalysis,check_FirewallCheck,check_MonthlyFloatAmount,check_ServerStoppedInfo,check_Signature 
 from checkrecordb where checkrecordb.id=checkrecordid;

  if check_NetworkBackup='' then set checkresult=concat(checkresult,'网络备份（系统数据）',';'); end if;
  if check_HarddriverBackup='' then set checkresult=concat(checkresult,'光盘备份（系统数据）',';'); end if;
  if check_LogUploadAnalysis='' then set checkresult=concat(checkresult,'日志上在分析',';'); end if;
  if check_FirewallCheck='' then set checkresult=concat(checkresult,'防火墙检查',';'); end if;
  if check_MonthlyFloatAmount='' then set checkresult=concat(checkresult,'本月流量',';'); end if;
  if check_ServerStoppedInfo='' then set checkresult=concat(checkresult,'停止服务情况',';'); end if;
  if check_Signature='' then set checkresult=concat(checkresult,'签字',';'); end if;
  
  if checkresult<>'' && RIGHT(checkresult,1)=';' then 
   set checkresult=left(checkresult,LENGTH(checkresult)-1);
  end if;
  
end//

delimiter;


/*
set @result="";
call checkrecordb_check_by_checkrecordbid(1,@result);
select @result;
*/
-- 2015040901_checkrecorda_search_by_checkinfoaid_allyear.sql
-- checkrecorda_search_by_checkinfoaid_allyear
delimiter //
Drop procedure if exists checkrecorda_search_by_checkinfoaid_allyear;
create procedure checkrecorda_search_by_checkinfoaid_allyear(in `id` int,in `year` int)
begin 
--    declare tempid int default 0;
--    declare monthnumber int default 1;
-- 
--    select checkrecorda.`id` into tempid from checkrecorda inner join checkinfoa on checkinfoa.`id`=checkrecorda.CheckInfoID inner join machineinfo on machineinfo.`id`=checkinfoa.MachineInfoID where machineinfo.`id`=`id` and checkinfoa.`year`=`year`;
--   if tempid=0 THEN 
--      select checkinfoa.`id` into tempid from checkinfoa where checkinfoa.MachineInfoID=`id` and checkinfoa.`year`=`year`;
--      
--      while monthnumber<13 do
--       set @result='';
--       if not exists (select * from checkrecorda where CheckInfoID=tempid and checkrecorda.MonthNumber=monthnumber) then 
--       call checkrecorda_insert(tempid,'',monthnumber,'0','0','0','0','','','0','0','0','0','','',@result);
--       end if;
--       set monthnumber=monthnumber+1;
--      end while;
--   end IF;
 select checkrecorda.* from checkrecorda inner join checkinfoa on checkrecorda.CheckInfoID=checkinfoa.id where checkinfoa.MachineInfoID=`id` and checkinfoa.`year`=`year` order by checkrecorda.MonthNumber asc;
end//

delimiter ;
/*

call checkrecorda_search_by_checkinfoaid_allyear(1,'2015');

*/
-- 2015040902_checkrecordb_search_by_checkinfobid_allyear.sql
-- checkrecordb_search_by_checkinfobid_allyear
delimiter //
Drop procedure if exists checkrecordb_search_by_checkinfobid_allyear;
create procedure checkrecordb_search_by_checkinfobid_allyear(in `id` int,in `year` int)
begin 
--    declare tempid int default 0;
--    declare monthnumber int default 1;
-- 
--    select checkrecorda.`id` into tempid from checkrecorda inner join checkinfoa on checkinfoa.`id`=checkrecorda.CheckInfoID inner join machineinfo on machineinfo.`id`=checkinfoa.MachineInfoID where machineinfo.`id`=`id` and checkinfoa.`year`=`year`;
--   if tempid=0 THEN 
--      select checkinfoa.`id` into tempid from checkinfoa where checkinfoa.MachineInfoID=`id` and checkinfoa.`year`=`year`;
--      
--      while monthnumber<13 do
--       set @result='';
--       if not exists (select * from checkrecorda where CheckInfoID=tempid and checkrecorda.MonthNumber=monthnumber) then 
--       call checkrecorda_insert(tempid,'',monthnumber,'0','0','0','0','','','0','0','0','0','','',@result);
--       end if;
--       set monthnumber=monthnumber+1;
--      end while;
--   end IF;
 select checkrecordb.* from checkrecordb inner join checkinfob on checkrecordb.CheckInfoID=checkinfob.id where checkinfob.MachineInfoID=`id` and checkinfob.`year`=`year` order by checkrecordb.MonthNumber asc;
end//

delimiter ;
/*

call checkrecordb_search_by_checkinfobid_allyear(1,'2015');

*/
-- 2015041404_get_functions_by_userid.sql
-- 通过人员的ID给出人员被授权的功能

delimiter //

drop procedure if exists get_functions_by_userid;
create procedure get_functions_by_userid(in inuserid int)
begin 
 select f.* from functions f 
 inner join userrole_functions_r ufr on ufr.functionid=f.id
 inner join userrole ur on ur.id=ufr.userroleid
 inner join users_userrole_r uur on uur.roleid=ur.id
 inner join users on users.id=uur.userid
 where users.id=inuserid 
 order by f.displayorder asc;

end //

delimiter ;

/*

call get_functions_by_userid(1);

*/
-- 2015041408_laod_users.sql
-- laod_users
delimiter // 
drop procedure if exists laod_users;
create procedure laod_users()
begin 
 
select * from users where state='1';

end //

delimiter ;

/*

call laod_users();

*/
-- 2015041701_auth_user.sql
-- auth_user

delimiter //
drop procedure if exists auth_user;
create procedure auth_user(in userid int,in roleid int)
begin 
   declare tempid int default 0;
   select id into tempid from users_userrole_r where users_userrole_r.userid=userid and users_userrole_r.roleid=roleid;

   if tempid=0 then
    insert into users_userrole_r (userid, roleid, description,updatetime,state) values (userid,roleid,'',now(),'1');
    select id into tempid from users_userrole_r where users_userrole_r.userid=userid and users_userrole_r.roleid=roleid; 
  else 
    update users_userrole_r set users_userrole_r.userid=userid,users_userrole_r.roleid=roleid,description='',updatetime=now(),state='1' where id=tempid;
   end if;
end//

delimiter ;

/*

call auth_user(3,2);

*/
-- 2015041702_add_user.sql
-- search target checkinfo a 
delimiter //
Drop procedure if exists add_user;
create procedure add_user(in name varchar(100),in newusername varchar(100),in newpassword VARCHAR(100),in newuserroleid int,in email varchar(100),in department varchar(100),in ContactNumber varchar(100),inout executeresult varchar(100))
begin 

   declare tempuserid int default 0;
   set executeresult='';
   if not exists (select * from users where users.username=newusername) then 
    insert into users (users.username, users.password,users.EmailConfigurationID,users.name,users.email,users.Department,ContactNumber,state) values (newusername,newpassword,1,name,email,department,ContactNumber,'1');
    set tempuserid=LAST_INSERT_ID();
       set executeresult='';
   else 
    set executeresult=concat(executeresult,"用户名已经存在,请更换！");
   end if;

   if not exists (select * from users_userrole_r where userid=tempuserid) then 
     insert into users_userrole_r (userid,roleid,updatetime) values (tempuserid,newuserroleid,now());
   end if;
    
   if executeresult='' then 
    set executeresult='true';
   end if;
   
end//

delimiter ;
/*
set @result="";
call add_user('lili','123',1,@result);
select @result;

*/

-- 2015041703_isadmin.sql
-- login 
delimiter //
drop procedure if exists isadmin;
create procedure isadmin(in userid int,inout result varchar(100))
BEGIN
    set result='';
   if exists (select u.* from users u inner join users_userrole_r uur on u.id=uur.userid inner join userrole ur on ur.id=uur.roleid where ur.id=1 and u.id=userid) then 
     set result='true';
   else 
    set result ='false';
   end if;
  
end//

delimiter ;

/*
set @result="";
call isadmin(0,@result);
select @result;
*/
-- 2015042001_users_search_by_id.sql
-- users_search_by_id
delimiter //
Drop procedure if exists users_search_by_id;
create procedure users_search_by_id(in userid int)
begin 
 select users.*,uur.roleid as roleid from users 
 inner join users_userrole_r uur on users.id=uur.userid
 where users.id=userid and users.state='1';
end//

delimiter ;
/*

call users_search_by_id(1);

*/
-- 2015042101_machineinfo_add_columns.sql
-- machineinfo_add_columns_20150421
delimiter //
Drop procedure if exists machineinfo_add_columns_20150421;
create procedure machineinfo_add_columns_20150421()
begin 

-- 购买人
if not exists (select * from INFORMATION_SCHEMA.COLUMNS where table_name='machineinfo' and TABLE_SCHEMA='machinemanagement' and COLUMN_NAME='Purchaser') then 
 alter table machineinfo add Purchaser varchar(100) after updatetime;
end if;
-- 购买方式
if not exists (select * from INFORMATION_SCHEMA.COLUMNS where table_name='machineinfo' and TABLE_SCHEMA='machinemanagement' and COLUMN_NAME='PurchaseMethod') then 
  alter table machineinfo add PurchaseMethod varchar(100) after Purchaser;
end if;
-- 供应商
if not exists (select * from INFORMATION_SCHEMA.COLUMNS where table_name='machineinfo' and TABLE_SCHEMA='machinemanagement' and COLUMN_NAME='Supplier') then 
alter table machineinfo add Supplier varchar(100) after PurchaseMethod;
end if;
-- 供应商联系人
if not exists (select * from INFORMATION_SCHEMA.COLUMNS where table_name='machineinfo' and TABLE_SCHEMA='machinemanagement' and COLUMN_NAME='SupplierContact') then 
alter table machineinfo add SupplierContact varchar(100) after Supplier;
end if;
-- 供应商联系人电话
if not exists (select * from INFORMATION_SCHEMA.COLUMNS where table_name='machineinfo' and TABLE_SCHEMA='machinemanagement' and COLUMN_NAME='SupplierContactNumber') then 
alter table machineinfo add SupplierContactNumber varchar(100) after SupplierContact;
end if;

-- 设备类型 pc 1 server 2 laptop 3
if not exists (select * from INFORMATION_SCHEMA.COLUMNS where table_name='machineinfo' and TABLE_SCHEMA='machinemanagement' and COLUMN_NAME='MachineType') then 
alter table machineinfo add MachineType varchar(100) after SupplierContactNumber;
end if;


end//

delimiter ;

call machineinfo_add_columns_20150421();

/*

call machineinfo_add_columns_20150421();

*/
