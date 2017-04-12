-- checkrecorda_check
delimiter //

drop procedure if exists checkrecorda_check;
create procedure checkrecorda_check(in machineid int,in `year` int,in `month` int,inout checkresult varchar(5000))
begin 

 declare check_os varchar(100);
 declare check_Application varchar(100);
 declare check_DataBaseCheck varchar(100);
 declare check_360Check varchar(100);
 declare check_Antivirus varchar(100);
 declare check_AccountNormal varchar(100);
 declare check_AccountAbnormal varchar(100);
 declare check_EventLog varchar(100);
 declare check_WebLog varchar(100);
 declare check_DataBaseLog varchar(100);
 declare check_HardDriverUsage varchar(100);
 declare check_OSResponsibleSingnature varchar(100);
 declare check_OSAdminsitratorSignature varchar(100);

 set checkresult='';
 select checkrecorda.os,checkrecorda.Application,checkrecorda.DataBaseCheck,checkrecorda.360Check,checkrecorda.Antivirus,checkrecorda.AccountNormal,checkrecorda.AccountAbnormal,checkrecorda.EventLog,checkrecorda.WebLog,checkrecorda.DataBaseLog,checkrecorda.HardDriverUsage,checkrecorda.OSResponsibleSingnature,checkrecorda.OSAdminsitratorSignature into check_os,check_Application,check_DataBaseCheck,check_360Check,check_Antivirus,check_AccountNormal,check_AccountAbnormal,check_EventLog,check_WebLog,check_DataBaseLog,check_HardDriverUsage,check_OSResponsibleSingnature,check_OSAdminsitratorSignature from checkrecorda inner join checkinfoa on checkrecorda.CheckInfoID=checkinfoa.id where checkinfoa.MachineInfoID=machineid and checkrecorda.MonthNumber=`month` and checkinfoa.year=`year`;

  if check_os='' then set checkresult=concat(checkresult,'操作系统',';'); end if;
  if check_Application='0' then set checkresult=concat(checkresult,'应用程序',';'); end if;
  if check_DataBaseCheck='0' then set checkresult=concat(checkresult,'数据库',';'); end if;
  if check_360Check='0' then set checkresult=concat(checkresult,'360安全卫士',';'); end if;
  if check_Antivirus='0' then set checkresult=concat(checkresult,'杀毒软件',';'); end if;
  if check_AccountNormal='' then set checkresult=concat(checkresult,'正常使用账号',';'); end if;
  if check_AccountAbnormal='' then set checkresult=concat(checkresult,'非正常使用账号',';'); end if;
  if check_EventLog='0' then set checkresult=concat(checkresult,'事件日志',';'); end if;
  if check_WebLog='0' then set checkresult=concat(checkresult,'web日志',';'); end if;
  if check_OSResponsibleSingnature='' then set checkresult=concat(checkresult,'系统责任人签字',';'); end if;
  if check_OSAdminsitratorSignature='' then set checkresult=concat(checkresult,'系统管理员签字',';'); end if;
   

  if checkresult<>'' && RIGHT(checkresult,1)=';' then 
   set checkresult=left(checkresult,LENGTH(checkresult)-1);
  end if;
  
end//

delimiter;


/*
set @result="";
call checkrecorda_check(4,2015,4,@result);
select @result;
*/