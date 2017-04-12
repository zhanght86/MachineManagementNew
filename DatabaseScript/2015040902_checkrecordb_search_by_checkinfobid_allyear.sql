-- checkrecordb_search_by_checkinfobid_allyear
delimiter //
Drop procedure if exists checkrecordb_search_by_checkinfobid_allyear;
create procedure checkrecordb_search_by_checkinfobid_allyear(in `id` int,in `year` int)
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
 select checkrecordb.* from checkrecordb inner join checkinfob on checkrecordb.CheckInfoID=checkinfob.id where checkinfob.MachineInfoID=`id` and checkinfob.`year`=`year` order by checkrecordb.MonthNumber asc;
end//

delimiter ;
/*

call checkrecordb_search_by_checkinfobid_allyear(1,'2015');

*/