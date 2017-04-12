-- machineinfo_search
delimiter //
drop procedure if exists machineinfo_search;
create procedure machineinfo_search(in keyword varchar(100),in pageamount int,in pagecounter int,in showall varchar(10),in searchcondition VARCHAR(2000),in orderstring varchar(100),in userright varchar(5000),inout result varchar(2000))
begin 
 declare str varchar(100);
 declare starter int default 0;
 declare ender int default 1;
 declare totalamount int default 0;
 declare totalpages int default 0;
 declare currentmonth int;
 declare currentyear int;

 set currentmonth=MONTH(CURRENT_DATE());
 set currentyear=YEAR(CURRENT_DATE());
 -- set str=CONCAT('%',keyword,'%');
 set str=CONCAT(keyword);
 if showall='0'||showall='' then
 set @totalamount=0;
 set @sqlstr=concat("select count(machineinfo.id) into @totalamount ",
                     " from machineinfo ",
                      " inner join users_machineinforesponsible_r umir on umir.machineinfoid=machineinfo.id inner join users u1 on u1.id=umir.userid ",
                     userright,
                     " where machineinfo.state='1' ",
                    " and ( machineinfo.PropertyName  REGEXP '",str,"' or   machineinfo.PropertyNumber REGEXP '",str,"' or machineinfo.MachineLocation REGEXP '",str,"' or machineinfo.Model REGEXP '",str,"' or machineinfo.IPAdd REGEXP '",str,"' or machineinfo.MachineUsage REGEXP '",str,"' or machineinfo.Department REGEXP '",str,"' or machineinfo.Responsible REGEXP '",str,"' or machineinfo.ResponsibleEmail REGEXP '",str,"' or machineinfo.SystemInfo REGEXP '",str,"' or machineinfo.PurchaseTime REGEXP binary '",str,"' or machineinfo.Price REGEXP '",str,"' or machineinfo.Project REGEXP '",str,"' or machineinfo.MoveInTime REGEXP binary '",str,"' or machineinfo.Comments REGEXP '",str,"' or machineinfo.Purchaser REGEXP '",str,"' or machineinfo.PurchaseMethod REGEXP '",str,"' or machineinfo.Supplier REGEXP '",str,"' or machineinfo.SupplierContact REGEXP '",str,"' or machineinfo.SupplierContactNumber REGEXP '",str,"')",
                     searchcondition,
                     orderstring,
                     " ;");
PREPARE finalsql from @sqlstr;
execute finalsql;
set totalamount=@totalamount;


  if totalamount<>0 then 
      if MOD(totalamount,pageamount)<>0 then 
        set totalpages=(totalamount DIV pageamount)+1;
      else 
       set totalpages=(totalamount DIV pageamount);
      end if;

  ELSE
    set totalpages=0;
  end if;
if pagecounter>totalpages THEN 
 if totalpages<>0 then
  set pagecounter=totalpages; 
 ELSE
   set pagecounter=1;
 end if;
end if;
  set result=concat('{"pagecounter":',pagecounter,',"pageamount":',pageamount,',"totalamount":',totalamount,',"totalpages":',totalpages,'}');


end if;


 if showall='0'||showall='' then
 set @sqlstr =concat("select machineinfo.*,",
                     " IFNULL((select checkrecorda.checkstate from checkrecorda inner join checkinfoa on checkrecorda.CheckInfoID=checkinfoa.id inner join machineinfo mi on mi.id=checkinfoa.MachineInfoID where  checkinfoa.`year`=",currentyear," and mi.id=machineinfo.id and checkrecorda.MonthNumber=",currentmonth,"),'0') as checkstatea,",
                     " IFNULL((select checkrecordb.checkstate from checkrecordb inner join checkinfob on checkrecordb.CheckInfoID=checkinfob.id inner join machineinfo mi on mi.id=checkinfob.MachineInfoID where  checkinfob.`year`=",currentyear," and mi.id=machineinfo.id and checkrecordb.MonthNumber=",currentmonth,"),'0') as checkstateb, ", 
                     " u1.name as r_name, u1.Department as r_department, u1.email as r_email,u1.ContactNumber as r_contactnumber,u1.id as r_id ",
                     " from machineinfo ",
                     " inner join users_machineinforesponsible_r umir on umir.machineinfoid=machineinfo.id inner join users u1 on u1.id=umir.userid ",
                     userright,
                     " where machineinfo.state='1' ",
                     " and ( machineinfo.PropertyName  REGEXP '",str,"' or   machineinfo.PropertyNumber REGEXP '",str,"' or machineinfo.MachineLocation REGEXP '",str,"' or machineinfo.Model REGEXP '",str,"' or machineinfo.IPAdd REGEXP '",str,"' or machineinfo.MachineUsage REGEXP '",str,"' or machineinfo.Department REGEXP '",str,"' or machineinfo.Responsible REGEXP '",str,"' or machineinfo.ResponsibleEmail REGEXP '",str,"' or machineinfo.SystemInfo REGEXP '",str,"' or machineinfo.PurchaseTime REGEXP binary '",str,"' or machineinfo.Price REGEXP '",str,"' or machineinfo.Project REGEXP '",str,"' or machineinfo.MoveInTime REGEXP binary '",str,"' or machineinfo.Comments REGEXP '",str,"' or machineinfo.Purchaser REGEXP '",str,"' or machineinfo.PurchaseMethod REGEXP '",str,"' or machineinfo.Supplier REGEXP '",str,"' or machineinfo.SupplierContact REGEXP '",str,"' or machineinfo.SupplierContactNumber REGEXP '",str,"')",
                     searchcondition,
                     orderstring,
                     "limit ",
                     (pagecounter-1)*pageamount,
                     ",",
                     pageamount,
                     ";");
elseif showall='1' then
 set @sqlstr =concat("select machineinfo.*,",
                     " IFNULL((select checkrecorda.checkstate from checkrecorda inner join checkinfoa on checkrecorda.CheckInfoID=checkinfoa.id inner join machineinfo mi on mi.id=checkinfoa.MachineInfoID where   checkinfoa.`year`=",currentyear," and mi.id=machineinfo.id and checkrecorda.MonthNumber=",currentmonth,"),'0') as checkstatea,",
                     " IFNULL((select checkrecordb.checkstate from checkrecordb inner join checkinfob on checkrecordb.CheckInfoID=checkinfob.id inner join machineinfo mi on mi.id=checkinfob.MachineInfoID where   checkinfob.`year`=",currentyear,"  and mi.id=machineinfo.id and checkrecordb.MonthNumber=",currentmonth,"),'0') as checkstateb, ", 
                     " u1.name as r_name, u1.Department as r_department, u1.email as r_email,u1.ContactNumber as r_contactnumber,u1.id as r_id ",
                     " from machineinfo ",
                     "  inner join users_machineinforesponsible_r umir on umir.machineinfoid=machineinfo.id inner join users u1 on u1.id=umir.userid ",
                     userright,
                     " where machineinfo.state='1' ",
                     " and ( machineinfo.PropertyName  REGEXP '",str,"' or  machineinfo.PropertyNumber REGEXP '",str,"' or machineinfo.MachineLocation REGEXP '",str,"' or machineinfo.Model REGEXP '",str,"' or machineinfo.IPAdd REGEXP '",str,"' or machineinfo.MachineUsage REGEXP '",str,"' or machineinfo.Department REGEXP '",str,"' or machineinfo.Responsible REGEXP '",str,"' or machineinfo.ResponsibleEmail REGEXP '",str,"' or machineinfo.SystemInfo REGEXP '",str,"' or machineinfo.PurchaseTime REGEXP binary '",str,"' or machineinfo.Price REGEXP '",str,"' or machineinfo.Project REGEXP '",str,"' or machineinfo.MoveInTime REGEXP binary '",str,"' or machineinfo.Comments REGEXP '",str,"' or machineinfo.Purchaser REGEXP '",str,"' or machineinfo.PurchaseMethod REGEXP '",str,"' or machineinfo.Supplier REGEXP '",str,"' or machineinfo.SupplierContact REGEXP '",str,"' or machineinfo.SupplierContactNumber REGEXP '",str,"')",
                     searchcondition,
                     orderstring,
                     ";");
end if;

PREPARE finalsql from @sqlstr;
execute finalsql;
end //


delimiter;
/*

set @result="";
call machineinfo_search('()',10,1,'0','',' order by machineinfo.moveintime desc ','',@result);
select @result;

*/
