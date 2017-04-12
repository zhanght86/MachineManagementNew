-- search target checkinfo a 
delimiter //
Drop procedure if exists checkrecordb_search_by_id;
create procedure checkrecordb_search_by_id(in `id` int,in `year` int,in monthnumber int)
begin 
   declare tempid int default 0;
if not exists (select * from checkrecordb inner join checkinfob on checkinfob.id=checkrecordb.CheckInfoID inner join machineinfo on machineinfo.id=checkinfob.MachineInfoID where machineinfo.id=`id` and checkinfob.year=`year` and checkrecordb.MonthNumber=monthnumber) THEN 
     select checkinfob.id into tempid from checkinfob where checkinfob.MachineInfoID=`id` and checkinfob.year=`year`;
     set @result='';
     call checkrecordb_insert(tempid,monthnumber,'','','','','','','',@result);
  end IF;
 select checkrecordb.* from checkrecordb inner join checkinfob on checkrecordb.CheckInfoID=checkinfob.id where checkinfob.MachineInfoID=`id` and checkinfob.year=`year` and checkrecordb.MonthNumber=monthnumber;
end//
delimiter ;


/*

call checkrecordb_search_by_id(1,'2015',1);

*/

