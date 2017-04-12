-- checkrecordb_check
delimiter //

drop procedure if exists checkrecordb_check;
create procedure checkrecordb_check(in machineid int,in `year` int,in `month` int,inout checkresult varchar(5000))
begin 
 declare check_NetworkBackup varchar(100);
 declare check_HarddriverBackup varchar(100);
 declare check_LogUploadAnalysis varchar(100);
 declare check_FirewallCheck varchar(100);
 declare check_MonthlyFloatAmount varchar(100);
 declare check_ServerStoppedInfo varchar(100);
 declare check_Signature varchar(100);

 set checkresult='';
 select checkrecordb.NetworkBackup,checkrecordb.HarddriverBackup,checkrecordb.LogUploadAnalysis,checkrecordb.FirewallCheck,checkrecordb.MonthlyFloatAmount,checkrecordb.ServerStoppedInfo,checkrecordb.Signature
 into check_NetworkBackup,check_HarddriverBackup,check_LogUploadAnalysis,check_FirewallCheck,check_MonthlyFloatAmount,check_ServerStoppedInfo,check_Signature from checkrecordb inner join checkinfob on checkrecordb.CheckInfoID=checkinfob.id where checkinfob.MachineInfoID=machineid and checkrecordb.MonthNumber=`month` and checkinfob.`year`=`year`;

  if check_NetworkBackup='' then set checkresult=concat(checkresult,'网络备份（系统数据）',';'); end if;
  if check_HarddriverBackup='' then set checkresult=concat(checkresult,'光盘备份（系统数据）',';'); end if;
  if check_LogUploadAnalysis='' then set checkresult=concat(checkresult,'日志上在分析',';'); end if;
  if check_FirewallCheck='' then set checkresult=concat(checkresult,'防火墙检查',';'); end if;
  if check_MonthlyFloatAmount='' then set checkresult=concat(checkresult,'本月流量',';'); end if;
  if check_ServerStoppedInfo='' then set checkresult=concat(checkresult,'停止服务情况',';'); end if;
  if check_Signature='' then set checkresult=concat(checkresult,'签字',';'); end if;
  
  if checkresult<>'' && RIGHT(checkresult,1)=';' then 
   set checkresult=left(checkresult,LENGTH(checkresult)-1);
  end if;
  
end//

delimiter;


/*
set @result="";
call checkrecordb_check(4,2015,4,@result);
select @result;
*/