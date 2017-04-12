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