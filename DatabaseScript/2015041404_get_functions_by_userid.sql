-- 通过人员的ID给出人员被授权的功能

delimiter //

drop procedure if exists get_functions_by_userid;
create procedure get_functions_by_userid(in inuserid int)
begin 
 select f.* from functions f 
 inner join userrole_functions_r ufr on ufr.functionid=f.id
 inner join userrole ur on ur.id=ufr.userroleid
 inner join users_userrole_r uur on uur.roleid=ur.id
 inner join users on users.id=uur.userid
 where users.id=inuserid 
 order by f.displayorder asc;

end //

delimiter ;

/*

call get_functions_by_userid(1);

*/