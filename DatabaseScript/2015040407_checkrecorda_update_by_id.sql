-- checkrecorda_update_by_id
delimiter //
Drop procedure if exists checkrecorda_update_by_id;
create procedure checkrecorda_update_by_id(in `id` int,in CheckInfoID int,in os varchar(100),in MonthNumber int,in Path varchar(100),in Application varchar(100),in DataBaseCheck varchar(100),in 360Check varchar(100),in Antivirus varchar(100),in PasswordStrength varchar(255),in AccountNormal varchar(100),in AccountAbnormal varchar(100),in EventLog varchar(100),in WebLog varchar(100),in DataBaseLog varchar(100),in HardDriverUsage varchar(100),in PageException varchar(255),in ReactionaryException varchar(255),in PatchScanExist varchar(255),in PatchScanHandle varchar(255),in PatchScanOperator varchar(255),in Service varchar(255),in OSResponsibleSingnature varchar(100),in OSAdminsitratorSignature varchar(100),inout result varchar(100))
begin 
   declare tempid int default 0;
   select checkrecorda.`id` into tempid from checkrecorda inner join checkinfoa on checkinfoa.`id`=checkrecorda.CheckInfoID where checkinfoa.`id`=CheckInfoID and checkrecorda.`id`=`id` and checkrecorda.MonthNumber=MonthNumber;
if tempid<>0 THEN 
    call checkrecorda_update(`id`,CheckInfoID,os,MonthNumber,Path,Application,DataBaseCheck,360Check,Antivirus,PasswordStrength,AccountNormal,AccountAbnormal,EventLog,WebLog,DataBaseLog,HardDriverUsage,PageException,ReactionaryException,PatchScanExist,PatchScanHandle,PatchScanOperator,Service,OSResponsibleSingnature,OSAdminsitratorSignature,result);
 end IF;
end//

delimiter ;

/*

set @result='';
call checkrecorda_update_by_id(544,1,'os',1,'path','Application','DataBaseCheck','360Check','Antivirus','passwordstrength','AccountNormal','AccountAbnormal','EventLog','WebLog','DataBaseLog','HardDriverUsage','','','','','','','OSResponsibleSingnature','OSAdminsitratorSignature',@result);
select @result;

*/