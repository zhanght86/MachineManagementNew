-- users_search_by_id
delimiter //
Drop procedure if exists users_search_by_id;
create procedure users_search_by_id(in userid int)
begin 
 select users.*,uur.roleid as roleid from users 
 inner join users_userrole_r uur on users.id=uur.userid
 where users.id=userid and users.state='1';
end//

delimiter ;
/*

call users_search_by_id(1);

*/