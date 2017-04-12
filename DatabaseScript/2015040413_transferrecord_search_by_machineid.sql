-- transferrecord_search_by_machineid
delimiter //
drop procedure if exists transferrecord_search_by_machineid;
create procedure transferrecord_search_by_machineid(in machineid int)
begin 
  declare machineowner varchar(200);
  declare responsibleEmail varchar(200);
  declare department varchar(100);
  set machineowner='';

  select machineinfo.Responsible,machineinfo.ResponsibleEmail,machineinfo.Department into machineowner,responsibleEmail,department from machineinfo where machineinfo.`id`=machineid;


    if not exists (select * from transferrecord where MachineInfoID=machineid) then 
      set @result='';
      call transferrecord_insert(machineid,machineowner,responsibleEmail,machineowner,responsibleEmail,0,department,'',now(),@result);
    end if;
   select * from transferrecord where MachineInfoID=machineid and state='1' order by `id` asc; 

end//

delimiter ;


/*

call transferrecord_search_by_machineid(2);

*/
