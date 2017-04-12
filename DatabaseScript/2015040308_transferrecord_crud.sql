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



