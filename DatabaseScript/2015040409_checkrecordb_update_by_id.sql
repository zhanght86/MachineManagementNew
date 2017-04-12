-- checkrecordb_update_by_id
delimiter //
Drop procedure if exists checkrecordb_update_by_id;
create procedure checkrecordb_update_by_id(in id int,in CheckInfoID int,in MonthNumber int,in NetworkBackup varchar(100),in HarddriverBackup varchar(100),in LogUploadAnalysis varchar(100),in FirewallCheck varchar(100),in MonthlyFloatAmount varchar(100),in ServerStoppedInfo varchar(100),in Signature varchar(100),inout result varchar(100))
begin 
   declare tempid int default 0;
   select checkrecordb.`id` into tempid from checkrecordb inner join checkinfob on checkinfob.`id`=checkrecordb.CheckInfoID where checkinfob.`id`=CheckInfoID and checkrecordb.`id`=`id` and checkrecordb.MonthNumber=MonthNumber;
if tempid<>0 THEN 
    call checkrecordb_update(`id`,CheckInfoID,MonthNumber,NetworkBackup,HarddriverBackup,LogUploadAnalysis,FirewallCheck,MonthlyFloatAmount,ServerStoppedInfo,Signature,result);
 end IF;
end//

delimiter ;

/*

set @result='';
call checkrecordb_update_by_id(1,1,1,'NetworkBackup','HarddriverBackup','LogUploadAnalysis','FirewallCheck','MonthlyFloatAmount','ServerStoppedInfo','Signature',@result);
select @result;

*/