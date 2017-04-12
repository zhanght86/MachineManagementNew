-- transferrecord_add_by_id
delimiter //
drop procedure if exists transferrecord_add_by_machineid;
create procedure transferrecord_add_by_machineid(in MachineId int,in CurOwner varchar(100),in CurOwnerEmail varchar(200),in department varchar(100),in reason varchar(500),inout result varchar(100))
BEGIN
 declare lastTransferRecordID int;
 declare lastTransferRecordOwner varchar(100);
 declare PreOwnerEmail varchar(200);
 declare tran_error int default 0;
 declare continue handler for SQLEXCEPTION begin set tran_error=@@error_count; end;
 start transaction;

 select max(transferrecord.`id`) into lastTransferRecordID from transferrecord where transferrecord.MachineInfoID=MachineId and state=1;
 select transferrecord.CurOwner,transferrecord.CurOwnerEmail into lastTransferRecordOwner,PreOwnerEmail from transferrecord where transferrecord.`id`=lastTransferRecordID;
 set @result='';
 call transferrecord_insert(MachineId,lastTransferRecordOwner,PreOwnerEmail,CurOwner,CurOwnerEmail,lastTransferRecordID,department,reason,now(),@result);

 if tran_error<>0 then
  rollback;
  set result='false';
 ELSE 
  commit;
  set result=@result;
 end if;

end//

delimiter ;

/*
set @result="";
call transferrecord_add_by_machineid(1,'张三1','zhangsan@xx.com',@result);
select @result;
*/