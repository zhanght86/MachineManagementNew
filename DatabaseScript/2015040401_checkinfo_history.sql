-- search target checkinfo a 
delimiter //
Drop procedure if exists checkinfo_history;
create procedure checkinfo_history(in checkinfotype varchar(100),in machineid varchar(100),in checkyear varchar(100))
begin 
  declare str1 varchar(100);
  declare str2 varchar(100);
  set str1=CONCAT('%',machineid,'%');
  set str2=CONCAT('%',checkyear,'%');

  (select "A" as type,machineinfo.id as machindid,year as checkyear from checkinfoa inner join machineinfo on machineinfo.id=checkinfoa.MachineInfoID where checkinfoa.state="1" and CONCAT(machineinfo.id) like str1  and CONCAT(checkinfoa.year) like str2) UNION (select "B" as type,machineinfo.id as machindid,year as checkyear from checkinfob inner join machineinfo on machineinfo.id=checkinfob.MachineInfoID where checkinfob.state="1" and CONCAT(machineinfo.id) like str1 and CONCAT(checkinfob.year) like str2) order by machindid desc; 
   
end//
delimiter ;


/*

call checkinfo_history('','1',2015);

*/

