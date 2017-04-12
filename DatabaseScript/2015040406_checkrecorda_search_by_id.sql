-- search target checkinfo a 
delimiter //
Drop procedure if exists checkrecorda_search_by_id;
create procedure checkrecorda_search_by_id(in `id` int,in `year` int,in monthnumber int)
begin 
   declare tempid int default 0;
   select checkrecorda.`id` into tempid from checkrecorda inner join checkinfoa on checkinfoa.`id`=checkrecorda.CheckInfoID inner join machineinfo on machineinfo.`id`=checkinfoa.MachineInfoID where machineinfo.`id`=`id` and checkinfoa.`year`=`year` and checkrecorda.MonthNumber=monthnumber;
  if tempid=0 THEN 
     select checkinfoa.`id` into tempid from checkinfoa where checkinfoa.MachineInfoID=`id` and checkinfoa.`year`=`year`;
     set @result='';
     call checkrecorda_insert(tempid,'',monthnumber,'0','0','0','0','0','','','','0','0','0','','','','','','','','','',@result);
  end IF;
 select checkrecorda.* from checkrecorda inner join checkinfoa on checkrecorda.CheckInfoID=checkinfoa.id where checkinfoa.MachineInfoID=`id` and checkinfoa.`year`=`year` and checkrecorda.MonthNumber=monthnumber;
end//

delimiter ;
/*

call checkrecorda_search_by_id(1,'2015',2);

*/