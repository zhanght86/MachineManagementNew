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



