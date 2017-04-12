-- CRUD operationtype -- 

-- select operationtype 
drop procedure if exists operationtype_select;
create procedure operationtype_select()
begin 
   select * from OperationType where state='1';
end; 

-- call operationtype_select();

-- insert operationtype 
drop procedure if exists operationtype_insert;
create procedure operationtype_insert(in typeCode varchar(255),in typeName varchar(255),in typeDescription varchar(2000),in updateTime datetime,in state varchar(100),inout result varchar(100))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;
  IF exists (select * from operationtype where operationtype.TypeCode=typeCode) then 
    set result='exist';
  ELSE
    insert into operationtype (TypeCode,TypeName,TypeDescription,UpdateTime,State) values (typeCode,typeName,typeDescription,updateTime,state);
 end if;
 
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
call operationtype_insert('00000001','登录成功','用户登录成功','2016-07-08 09:41:10','1',@result);
select @result;

select * from operationtype;
*/

-- delete operationtype
drop procedure if exists operationtype_delete; 
create procedure operationtype_delete(in id int,in typeCode varchar(100),inout result varchar(500))
begin 
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;
  if EXISTS (select * from operationtype where state='1' and (operationtype.id=id or (macoperationtypehineinfo.id=id and operationtype.typeCode=typeCode))) then
    update operationtype set state='2',UpdateTime=now() where state='1' and (operationtype.id=id or (macoperationtypehineinfo.id=id and operationtype.typeCode=typeCode));
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
call operationtype_delete(1,'00000001',@result);
select @result;

select * from operationtype;
*/

-- update operationtype
drop procedure if exists operationtype_update;
create procedure operationtype_update(in id int,in typeCode varchar(255),in typeName varchar(255),in typeDescription varchar(2000),in updateTime datetime,in state varchar(100),inout result varchar(100))
begin 

 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;

 start transaction;
    update operationtype set TypeCode=typeCode,TypeName=typeName,TypeDescription=typeDescription,UpdateTime=updateTime where operationtype.id=id and operationtype.state='1';
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
call operationtype_update('1','00000001','登录成功','用户登录成功','2016-07-08 09:41:10','1',@result);
select @result;
*/



