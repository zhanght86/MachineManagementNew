-- CRUD machineinfo -- 

-- select machineinfo 
drop procedure if exists machineinfo_select;
create procedure machineinfo_select()
begin 
 declare currentmonth int;
 declare currentyear int;
 set currentmonth=MONTH(CURRENT_DATE());
  set currentyear=YEAR(CURRENT_DATE());
 select machineinfo.*,
   IFNULL((select checkrecorda.checkstate from checkrecorda inner join checkinfoa on checkrecorda.CheckInfoID=checkinfoa.id inner join machineinfo mi on mi.id=checkinfoa.MachineInfoID where checkinfoa.`year`=currentyear and mi.id=machineinfo.id and checkrecorda.MonthNumber=currentmonth),'0') as checkstatea   
  ,IFNULL((select checkrecordb.checkstate from checkrecordb inner join checkinfob on checkrecordb.CheckInfoID=checkinfob.id inner join machineinfo mi on mi.id=checkinfob.MachineInfoID where checkinfob.`year`=currentyear and mi.id=machineinfo.id and checkrecordb.MonthNumber=currentmonth),'0') as checkstateb   
 from machineinfo 

 where machineinfo.state='1' order by machineinfo.id DESC;
end; 

-- call machineinfo_select();

-- insert machineinfo 
drop procedure if exists machineinfo_insert;
create procedure machineinfo_insert(in PropertyNumber varchar(100),in MachineLocation varchar(100),in Model varchar(100),in IPAdd varchar(100),in MachineUsage varchar(100),in Department varchar(100),in Responsible varchar(100),in ResponsibleEmail varchar(200), in ResponsibleContactNumber varchar(100),in SystemInfo varchar(100),in PurchaseTime datetime,in Price varchar(100),in Project varchar(5000),in Comments varchar(5000),in MoveInTime datetime,in Purchaser varchar(100),in PurchaseMethod varchar(100),in Supplier varchar(100),in SupplierContact varchar(100),in SupplierContactNumber varchar(100),in MachineType varchar(100),in userid int,in responsibleid int,in PropertyName varchar(255),inout result varchar(100))
begin 
 declare newmachineinfoid int default 0;
 declare tran_error int default 0;
 declare tempusername varchar(100);
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;
  select username into tempusername from users where users.id=userid;
  insert into MachineInfo(PropertyNumber,MachineLocation ,Model ,IPAdd ,MachineUsage ,Department ,Responsible,ResponsibleEmail ,ResponsibleContactNumber,SystemInfo ,PurchaseTime,Price,Project,Comments,Registrant,MoveInTime,UpdateTime,Purchaser,PurchaseMethod,Supplier,SupplierContact,SupplierContactNumber,MachineType,PropertyName,state)values(PropertyNumber,MachineLocation ,Model ,IPAdd ,MachineUsage ,Department ,Responsible,ResponsibleEmail,ResponsibleContactNumber ,SystemInfo ,PurchaseTime,Price,Project,Comments,tempusername,MoveInTime,now(),Purchaser,PurchaseMethod,Supplier,SupplierContact,SupplierContactNumber,MachineType,PropertyName,'1');
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
create procedure machineinfo_update(in id int,in ProrertyNumber varchar(100),in MachineLocation varchar(100),in Model varchar(100),in IPAdd varchar(100),in MachineUsage varchar(100),in Department varchar(100),in Responsible varchar(100),in ResponsibleEmail varchar(200), in ResponsibleContactNumber varchar(100),in SystemInfo varchar(100),in PurchaseTime datetime,Price varchar(100),Project varchar(5000),in Comments varchar(5000),MoveInTime datetime,in Purchaser varchar(100),in PurchaseMethod varchar(100),in Supplier varchar(100),in SupplierContact varchar(100),in SupplierContactNumber varchar(100),in MachineType varchar(100),in userid int,in PropertyName varchar(255),inout result varchar(100))
begin 

 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;

 start transaction;
    update MachineInfo set PropertyNumber=ProrertyNumber,MachineLocation=MachineLocation,Model=Model,IPAdd=IPAdd, MachineUsage=MachineUsage, Department=Department, Responsible=Responsible,ResponsibleEmail=ResponsibleEmail,ResponsibleContactNumber=ResponsibleContactNumber, SystemInfo=SystemInfo, PurchaseTime=PurchaseTime,Price=Price,Project=Project,Comments=Comments,MoveInTime=MoveInTime,UpdateTime=now(),Purchaser=Purchaser,PurchaseMethod=PurchaseMethod,Supplier=Supplier,SupplierContact=SupplierContact,SupplierContactNumber=SupplierContactNumber,MachineType=MachineType,PropertyName=PropertyName where MachineInfo.id=id and state='1';
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



