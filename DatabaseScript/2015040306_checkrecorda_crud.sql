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
create procedure checkrecorda_insert(in CheckInfoID int,in os varchar(100),in MonthNumber int,in Patch varchar(255),in Application varchar(100),in DataBaseCheck varchar(100),in 360Check varchar(100),in Antivirus varchar(100),in PasswordStrength varchar(255),in AccountNormal varchar(100),in AccountAbnormal varchar(100),in EventLog varchar(100),in WebLog varchar(100),in DataBaseLog varchar(100),in HardDriverUsage varchar(100),in PageException varchar(255),in ReactionaryException varchar(255),in PatchScanExist varchar(255),in PatchScanHandle varchar(255),in PatchScanOperator varchar(255),in Service varchar(255),in OSResponsibleSingnature varchar(100),in OSAdminsitratorSignature varchar(100),inout result varchar(100))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;
  insert into checkrecorda(CheckInfoID,os,MonthNumber,Patch,Application,DataBaseCheck,360Check,Antivirus,PasswordStrength,AccountNormal,AccountAbnormal,EventLog,WebLog,DataBaseLog,HardDriverUsage,PageException,ReactionaryException,PatchScanExist,PatchScanHandle,PatchScanOperator,Service,OSResponsibleSingnature,OSAdminsitratorSignature,checkstate,state)values(CheckInfoID,os,MonthNumber,Patch,Application,DataBaseCheck,360Check,Antivirus,PasswordStrength,AccountNormal,AccountAbnormal,EventLog,WebLog,DataBaseLog,HardDriverUsage,PageException,ReactionaryException,PatchScanExist,PatchScanHandle,PatchScanOperator,Service,OSResponsibleSingnature,OSAdminsitratorSignature,'0','1');
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
create procedure checkrecorda_update(in id int,in CheckInfoID int,in os varchar(100),in MonthNumber int,in Patch varchar(255),in Application varchar(100),in DataBaseCheck varchar(100),in 360Check varchar(100),in Antivirus varchar(100),in PasswordStrength varchar(255),in AccountNormal varchar(100),in AccountAbnormal varchar(100),in EventLog varchar(100),in WebLog varchar(100),in DataBaseLog varchar(100),in HardDriverUsage varchar(100),in PageException varchar(255),in ReactionaryException varchar(255),in PatchScanExist varchar(255),in PatchScanHandle varchar(255),in PatchScanOperator varchar(255),in Service varchar(255),in OSResponsibleSingnature varchar(100),in OSAdminsitratorSignature varchar(100),inout result varchar(100))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;

 start transaction;
    if exists (select * from checkrecorda where checkrecorda.id=id and state='1') then
     update checkrecorda set CheckInfoID=CheckInfoID,os=os,MonthNumber=MonthNumber,Patch=Patch,Application=Application, DataBaseCheck=DataBaseCheck, 360Check=360Check, Antivirus=Antivirus, PasswordStrength=PasswordStrength,AccountNormal=AccountNormal, AccountAbnormal=AccountAbnormal,EventLog=EventLog,WebLog=WebLog,DataBaseLog=DataBaseLog,HardDriverUsage=HardDriverUsage,PageException=PageException,ReactionaryException=ReactionaryException,PatchScanExist=PatchScanExist,PatchScanHandle=PatchScanHandle,PatchScanOperator=PatchScanOperator,Service=Service,OSResponsibleSingnature=OSResponsibleSingnature,OSAdminsitratorSignature=OSAdminsitratorSignature where checkrecorda.id=id and state='1';
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
call checkrecorda_update(309,223,'os',1,'path','Application','DataBaseCheck','360Check','Antivirus','passwordstrength','AccountNormal','AccountAbnormal','EventLog','WebLog','DataBaseLog','HardDriverUsage','','','','','','','OSResponsibleSingnature','OSAdminsitratorSignature',@result);
select @result;
*/




