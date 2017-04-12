-- laod_users
delimiter // 
drop procedure if exists laod_users;
create procedure laod_users()
begin 
 
select * from users where state='1';

end //

delimiter ;

/*

call laod_users();

*/