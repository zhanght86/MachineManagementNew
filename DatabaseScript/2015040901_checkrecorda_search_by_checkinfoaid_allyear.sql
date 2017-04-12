-- checkrecorda_search_by_checkinfoaid_allyear
delimiter //
Drop procedure if exists checkrecorda_search_by_checkinfoaid_allyear;
create procedure checkrecorda_search_by_checkinfoaid_allyear(in `id` int,in `year` int)
begin 
--    declare tempid int default 0;
--    declare monthnumber int default 1;
-- 
--    select checkrecorda.`id` into tempid from checkrecorda inner join checkinfoa on checkinfoa.`id`=checkrecorda.CheckInfoID inner join machineinfo on machineinfo.`id`=checkinfoa.MachineInfoID where machineinfo.`id`=`id` and checkinfoa.`year`=`year`;
--   if tempid=0 THEN 
--      select checkinfoa.`id` into tempid from checkinfoa where checkinfoa.MachineInfoID=`id` and checkinfoa.`year`=`year`;
--      
--      while monthnumber<13 do
--       set @result='';
--       if not exists (select * from checkrecorda where CheckInfoID=tempid and checkrecorda.MonthNumber=monthnumber) then 
--       call checkrecorda_insert(tempid,'',monthnumber,'0','0','0','0','','','0','0','0','0','','',@result);
--       end if;
--       set monthnumber=monthnumber+1;
--      end while;
--   end IF;
 select checkrecorda.* from checkrecorda inner join checkinfoa on checkrecorda.CheckInfoID=checkinfoa.id where checkinfoa.MachineInfoID=`id` and checkinfoa.`year`=`year` order by checkrecorda.MonthNumber asc;
end//

delimiter ;
/*

call checkrecorda_search_by_checkinfoaid_allyear(1,'2015');

*/