-- emailconfiguration_search_by_userid
delimiter //
drop procedure if exists emailconfiguration_search_by_userid;
create procedure emailconfiguration_search_by_userid(in userid int)
BEGIN
 if exists (select * from users where users.id=userid) then 
  select emailconfiguration.*,users.id as userid,emailconfigurationID,users.name as loginname from users inner join emailconfiguration on users.EmailConfigurationID=emailconfiguration.id where users.id=userid;
 ELSE
  select emailconfiguration.*,users.id as userid,emailconfigurationID,users.name as loginname from users inner join emailconfiguration on users.EmailConfigurationID=emailconfiguration.id where users.id=0000;
 end if;
end//

delimiter ;

/*

call emailconfiguration_search_by_userid(1);

*/