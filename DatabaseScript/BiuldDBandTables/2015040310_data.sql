-- machineinfo 插入假数据
-- delete from MachineInfo;
-- insert into MachineInfo(PropertyNumber ,MachineLocation ,Model ,IPAdd ,MachineUsage ,Department ,Responsible,ResponsibleEmail,ResponsibleContactNumber,SystemInfo ,PurchaseTime,Price, project,Comments,Registrant,MoveInTime,UpdateTime ,state) values('02-080605001','a04 6=6','IBM X3250 M2','124.16.154.6','网络监测评价','系统部','刘建华，张智雄','test@xx.com','1234567890','win2k3',now(),'123','project1','','user1',now(),now(),'1');
-- insert into MachineInfo(PropertyNumber ,MachineLocation ,Model ,IPAdd ,MachineUsage ,Department ,Responsible,ResponsibleEmail,ResponsibleContactNumber,SystemInfo ,PurchaseTime,Price, project,Comments,Registrant,MoveInTime,UpdateTime ,state) values('02-080605001','a04 18=17','IBM X3250','124.16.154.12','网络监测评价','系统部','刘建华，张智雄','test@xx.com','1234567890','win2k3',now(),'123','project1','','user1',now(),now(),'1');
-- insert into MachineInfo(PropertyNumber ,MachineLocation ,Model ,IPAdd ,MachineUsage ,Department ,Responsible,ResponsibleEmail,ResponsibleContactNumber,SystemInfo ,PurchaseTime,Price, project,Comments,Registrant,MoveInTime,UpdateTime ,state) values('03/080605002','a16 31=28','DELL R905','124.16.154.6','科技监测','系统部','刘建华','test@xx.com','1234567890','win2k3',now(),'123','project1','','user1',now(),now(),'1');
-- insert into MachineInfo(PropertyNumber ,MachineLocation ,Model ,IPAdd ,MachineUsage ,Department ,Responsible,ResponsibleEmail,ResponsibleContactNumber,SystemInfo ,PurchaseTime,Price, project,Comments,Registrant,MoveInTime,UpdateTime ,state) values('02/080605002','a04 21=20','DELL R510','124.16.154.108','检测服务','系统部','刘建华','test@xx.com','1234567890','win2k3',now(),'123','project1','','user1',now(),now(),'1');

insert into EmailConfiguration (`server`,`port`,`UseSSL`,`UserName`,`Password`,state) VALUES
('smtp.cstnet.cn',25,false,'it-department@mail.las.ac.cn','itmail2014','1');

-- 登陆用户
insert into `users`(`name`,EmailConfigurationID,age,gender,username,`password`,email,islogin,Department,ContactNumber,state)
     values('张智雄',1,'','','zhangzhx','7C4A8D09CA3762AF61E59520943DC26494F8941B','zhangzhx@mail.las.ac.cn','0','信息系统部','010-82628382','1');

insert into `users`(`name`,EmailConfigurationID,age,gender,username,`password`,email,islogin,Department,ContactNumber,state)
     values('季毅',1,'30','','jiy','7C4A8D09CA3762AF61E59520943DC26494F8941B','jiy@mail.las.ac.cn','0','信息系统部','010-82628382','1');

 insert into `users`(`name`,EmailConfigurationID,age,gender,username,`password`,email,islogin,Department,ContactNumber,state)
     values('钱力',1,'','','qianl','7C4A8D09CA3762AF61E59520943DC26494F8941B','qianl@mail.las.ac.cn','0','信息系统部','010-82628382','1');

 insert into `users`(`name`,EmailConfigurationID,age,gender,username,`password`,email,islogin,Department,ContactNumber,state)
     values('陈子俊',1,'','','chenzj','7C4A8D09CA3762AF61E59520943DC26494F8941B','chenzj@mail.las.ac.cn','0','信息系统部','15011351201','1');

     
   --  新增功能 functions
 insert into functions(functionname,functionurl,htmlid,functionicon,description,displayorder,updatetime,state)
       VALUES('资产信息管理','../../GetMachineInfoList.do?&userid=','propertymanagement','<span class="glyphicon glyphicon-asterisk"></span>','资产信息管理',1,now(),'1'); 
       
insert into functions(functionname,functionurl,htmlid,functionicon,description,displayorder,updatetime,state)
       VALUES('人员管理','../../systemUserManagement.do?&userid=','systemusermanagement','<span class="glyphicon glyphicon-user"></span>','人员管理',2,now(),'1'); 

-- 新增角色     
insert userrole(parentroleid,rolename,description,updatetime,state) VALUES (0,'admin','系统内置管理员',now(),'1');
insert userrole(parentroleid,rolename,description,updatetime,state) VALUES  (1,'systemuser','系统普通用户',now(),'1');

                    
 -- 新增用户与角色的关系
insert into users_userrole_r (userid, roleid, description,updatetime,state) values (1,1,'',now(),'1');
insert into users_userrole_r (userid, roleid, description,updatetime,state) values (2,1,'',now(),'1');
insert into users_userrole_r (userid, roleid, description,updatetime,state) values (3,1,'',now(),'1');
insert into users_userrole_r (userid, roleid, description,updatetime,state) values (4,1,'',now(),'1');


-- 新增功能与角色的关系
insert into userrole_functions_r (userroleid,functionid,state) values (1,1,'1');
insert into userrole_functions_r (userroleid,functionid,state) values (1,2,'1');

insert into userrole_functions_r (userroleid,functionid,state) values (2,1,'1');
-- insert into userrole_functions_r (userroleid,functionid,state) values (3,1,'1');


