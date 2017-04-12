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
