-- login 
delimiter //
drop procedure if exists login;
create procedure login(in email varchar(100),in passwd varchar(100),inout result varchar(100))
BEGIN
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
  start transaction;
  set result='';
  select concat(users.id,',',users.email,',',users.`name`) as loginresult into result from users where users.email=email and users.`password`=passwd and state='1';
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
call login('chenzj@mail.las.ac.cn','7C4A8D09CA3762AF61E59520943DC26494F8941B',@result);
select @result;
*/


