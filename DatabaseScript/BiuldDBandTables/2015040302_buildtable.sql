-- DEFAULT CHARSET=UTF8;
-- 如果已存在外键，不检查，外键依赖性，
SET FOREIGN_KEY_CHECKS=0;

drop table if exists MachineInfo;
create table MachineInfo
(
 id INT auto_increment primary key not null,
 PropertyNumber varchar(100),
 MachineLocation varchar(100),
 Model varchar(100),
 IPAdd varchar(100),
 MachineUsage varchar(100),
 Department varchar(100),
 Responsible varchar(100),
 ResponsibleEmail varchar(200),
 ResponsibleContactNumber varchar(100),
 SystemInfo varchar(100),
 PurchaseTime datetime,
 Price varchar(100),
 Project varchar(5000),
 Comments varchar(5000),
 Registrant varchar(100),
 MoveInTime datetime,
 UpdateTime datetime,
 state varchar(100),
  PropertyName varchar(200)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

drop table if exists TransferRecord;
create table TransferRecord
( 
 id int auto_increment primary key,
 MachineInfoID INT not null,
 PreOwner varchar(100),
 PreOwnerEmail varchar(200),
 CurOwner varchar(100),
 CurOwnerEmail varchar(200),
 Preid int,
 department varchar(100),
 reason varchar(2000),
 HappenTime datetime,
 state varchar(100),
 index index_MachineInfoID (MachineInfoID),
 constraint TransferRecord_MachineInfo_fk_1 FOREIGN KEY index_MachineInfoID(MachineInfoID) REFERENCES MachineInfo(id) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

drop table if exists checkInfoA;
create table checkInfoA
(
 id int auto_increment primary key,
 FlowNumber varchar(100),
 PropertyNumber varchar(100), 
 SerialNumber varchar(100),
 ResponsibilityDepartment varchar(100),
 MachineLocation varchar(100),
 Model varchar(100),
 SystemInfo varchar(100),
 IPAdd varchar(100),
 MachineUsage varchar(100),
 MantainceStaff varchar(100), 
 BackupContent varchar(100),
 BackupContentChange1 varchar(100),
 BackupContentChange2 varchar(100),
 FileDirectory varchar(100),
 FileDirectoryChange1 varchar(100),
 FileDirectoryChange2 varchar(100),
 BackupPeriod varchar(100),
 BackupPeriodChange1 varchar(100),
 BackupPeriodChange2 varchar(100),
 `year` varchar(100),
 MachineInfoID INT not null,
 state varchar(100),
 index index_MachineInfoID(MachineInfoID),
 constraint checkInfoA_MachineInfo_pk_1 foreign key index_MachineInfoID(MachineInfoID) references MachineInfo(id) on delete cascade on update cascade
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

drop table if exists CheckRecordA;
create table CheckRecordA
(
 id int auto_increment primary key,
 CheckInfoID int(10) not null,
 os varchar(100),
 MonthNumber int,
 Application varchar(100),
 DataBaseCheck varchar(100),
 360Check varchar(100),
 Antivirus varchar(100),
 AccountNormal varchar(100),
 AccountAbnormal varchar(100),
 EventLog varchar(100),
 WebLog varchar(100),
 DataBaseLog varchar(100),
 HardDriverUsage varchar(100),
 OSResponsibleSingnature varchar(100),
 OSAdminsitratorSignature varchar(100),
 checkstate varchar(100),
 state varchar(100),
 index index_CheckInfoID (CheckInfoID),
 CONSTRAINT CheckRecordA_checkInfoA_FK_1 foreign key index_CheckInfoID(CheckInfoID) references checkInfoA(id) on delete cascade on update cascade
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

drop table if exists checkInfoB;
create table checkInfoB
(
 id int auto_increment primary key,
 FlowNumber varchar(100),
 PropertyNumber varchar(100), 
 SerialNumber varchar(100),
 ResponsibilityDepartment varchar(100),
 MachineLocation varchar(100),
 Model varchar(100),
 SystemInfo varchar(100),
 IPAdd varchar(100),
 MachineUsage varchar(100),
 MantainceStaff varchar(100),
 `year` varchar(100),
 MachineInfoID INT not null,
 Comments varchar(500),
 state varchar(100),
 index index_MachineInfoID(MachineInfoID),
 constraint checkInfoB_MachineInfo_pk_1 foreign key index_MachineInfoID(MachineInfoID) references MachineInfo(id) on delete cascade on update cascade
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

drop table if exists CheckRecordB;
create table CheckRecordB
(
 id int auto_increment primary key,
 CheckInfoID int(10) not null,
 MonthNumber int,
 NetworkBackup varchar(100),
 HarddriverBackup varchar(100),
 LogUploadAnalysis varchar(100),
 FirewallCheck varchar(100),
 MonthlyFloatAmount varchar(100),
 ServerStoppedInfo varchar(100),
 Signature varchar(100),
 checkstate varchar(100),
 state varchar(100),
 index index_CheckInfoID (CheckInfoID),
 CONSTRAINT CheckRecordB_checkInfoB_FK_1 foreign key index_CheckInfoID(CheckInfoID) references checkInfoB(id) on delete cascade on update cascade
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `EmailConfigurationID` int default 0,
  `name` varchar(100)default '',
  `age` varchar(100)default '' ,
  `gender` varchar(100) default '',
  `username` varchar(100) default '',
  `password` varchar(100),
  `email` varchar(100) default '',
  `islogin` varchar(10) default '0',
   Department varchar(500) default '',
   ContactNumber varchar(100) default '',
   state varchar(100),
  index index_EmailConfigurationID (EmailConfigurationID),
 CONSTRAINT users_EmailConfiguration_FK_1 foreign key index_EmailConfigurationID(EmailConfigurationID) references EmailConfiguration(id)on update cascade
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `EmailConfiguration`;
CREATE TABLE `EmailConfiguration` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `server` varchar(100),
  `port` varchar(100) ,
  `UseSSL` boolean,
  `UserName` varchar(100),
  `PassWord` varchar(100),
   state varchar(100)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS userrole;
CREATE TABLE userrole (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  parentroleid int,
  rolename varchar(200),
  description varchar(100),
  updatetime datetime,
  state varchar(100)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS users_userrole_r;
CREATE TABLE users_userrole_r (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  userid int,
  roleid int,
  description varchar(100),
  updatetime datetime,
  state varchar(100)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


DROP TABLE IF EXISTS functiongroup;
CREATE TABLE functiongroup (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  parentid int,
  description varchar(100),
  updatetime datetime,
  displayorder int,
  state varchar(100)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS functions;
CREATE TABLE functions (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  functionname varchar(100),
  htmlid varchar(200),
  functionurl varchar(2000),
  functionicon varchar(2000),
  description varchar(200),
  displayorder int,
  updatetime datetime,
  state varchar(100)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS users_machineinfo_r;
CREATE TABLE users_machineinfo_r (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  machineinfoid varchar(100),
  userid varchar(2000),
  state varchar(100)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


DROP TABLE IF EXISTS users_machineinfo_r;
CREATE TABLE users_machineinfo_r (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  machineinfoid varchar(100),
  userid varchar(2000),
  state varchar(100)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS userrole_functions_r;
CREATE TABLE userrole_functions_r (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  userroleid varchar(100),
  functionid varchar(2000),
  state varchar(100)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


DROP TABLE IF EXISTS users_machineinforesponsible_r;
CREATE TABLE users_machineinforesponsible_r (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  machineinfoid varchar(100),
  userid varchar(2000),
  state varchar(100)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


DROP TABLE IF EXISTS machineType;
CREATE TABLE machineType (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  machineType varchar(200),
  state varchar(100)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

