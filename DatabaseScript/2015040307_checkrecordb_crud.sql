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



