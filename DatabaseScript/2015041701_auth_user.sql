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