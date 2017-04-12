-- search target machineinfo by id 
delimiter //
Drop procedure if exists machineinfo_search_by_id;
create procedure machineinfo_search_by_id(in `id` int)
begin 
   declare responsible varchar(200);
   declare responsibletemp varchar(200);
   declare emailtemp varchar(200);
   declare emaillist varchar(500);
   declare seplocation int;
   declare sqlstr varchar(2000);
   declare checker Boolean; 

  declare currentmonth int;
  declare currentyear int;

  set currentmonth=MONTH(CURRENT_DATE());
  set currentyear=YEAR(CURRENT_DATE());
   set checker=true;
   select machineinfo.Responsible into responsible from machineinfo where machineinfo.`id`=`id`;
   
   -- 替换掉中文逗号
   set responsible=REPLACE(responsible,'，',',');

   -- 替换掉空格
   set responsible=REPLACE(responsible,' ','');

   while checker do 
    if responsible<>'' then 
     if locate(',',responsible)>0 then 
       set responsibletemp='';
       set emailtemp='';
       set responsibletemp=SUBSTRING(responsible,1,(locate(',',responsible)-1));
       select users.email into emailtemp from users where users.`name`=responsibletemp;
       if emailtemp<>'' then 
         set emaillist=concat(emaillist,emailtemp,';');
       end if;
       set responsible=SUBSTRING(responsible,locate(',',responsible)+1,LENGTH(responsible));
     ELSE
      select users.email into emailtemp from users where users.`name`=responsible;
      set emaillist=concat(emaillist,emailtemp);
      set checker=false;
     end if;
    else 
     set checker=false;
    end if;
 end while;


 select machineinfo.*, 
  IFNULL((select checkrecorda.checkstate from checkrecorda inner join checkinfoa on checkrecorda.CheckInfoID=checkinfoa.id inner join machineinfo mi on mi.id=checkinfoa.MachineInfoID where checkinfoa.`year`=currentyear and  mi.id=machineinfo.id and checkrecorda.MonthNumber=currentmonth),'0') as checkstatea   
  , IFNULL((select checkrecordb.checkstate from checkrecordb inner join checkinfob on checkrecordb.CheckInfoID=checkinfob.id inner join machineinfo mi on mi.id=checkinfob.MachineInfoID where checkinfob.`year`=currentyear and  mi.id=machineinfo.id and mi.id=machineinfo.id and checkrecordb.MonthNumber=currentmonth),'0') as checkstateb 
  , users.name as r_name, users.Department as r_department, users.email as r_email,users.ContactNumber as r_contactnumber,users.id as r_id
  from machineinfo 
  inner join users_machineinforesponsible_r umr on umr.machineinfoid=machineinfo.id
  inner join users on users.id=umr.userid
  where machineinfo.id=`id`;

end//

delimiter ;
/*

call machineinfo_search_by_id(1);

*/