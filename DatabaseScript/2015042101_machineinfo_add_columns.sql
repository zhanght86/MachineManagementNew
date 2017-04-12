-- machineinfo_add_columns_20150421
delimiter //
Drop procedure if exists machineinfo_add_columns_20150421;
create procedure machineinfo_add_columns_20150421()
begin 

-- 购买人
if not exists (select * from INFORMATION_SCHEMA.COLUMNS where table_name='machineinfo' and TABLE_SCHEMA='machinemanagement' and COLUMN_NAME='Purchaser') then 
 alter table machineinfo add Purchaser varchar(100) after updatetime;
end if;
-- 购买方式
if not exists (select * from INFORMATION_SCHEMA.COLUMNS where table_name='machineinfo' and TABLE_SCHEMA='machinemanagement' and COLUMN_NAME='PurchaseMethod') then 
  alter table machineinfo add PurchaseMethod varchar(100) after Purchaser;
end if;
-- 供应商
if not exists (select * from INFORMATION_SCHEMA.COLUMNS where table_name='machineinfo' and TABLE_SCHEMA='machinemanagement' and COLUMN_NAME='Supplier') then 
alter table machineinfo add Supplier varchar(100) after PurchaseMethod;
end if;
-- 供应商联系人
if not exists (select * from INFORMATION_SCHEMA.COLUMNS where table_name='machineinfo' and TABLE_SCHEMA='machinemanagement' and COLUMN_NAME='SupplierContact') then 
alter table machineinfo add SupplierContact varchar(100) after Supplier;
end if;
-- 供应商联系人电话
if not exists (select * from INFORMATION_SCHEMA.COLUMNS where table_name='machineinfo' and TABLE_SCHEMA='machinemanagement' and COLUMN_NAME='SupplierContactNumber') then 
alter table machineinfo add SupplierContactNumber varchar(100) after SupplierContact;
end if;

-- 设备类型 pc 1 server 2 laptop 3
if not exists (select * from INFORMATION_SCHEMA.COLUMNS where table_name='machineinfo' and TABLE_SCHEMA='machinemanagement' and COLUMN_NAME='MachineType') then 
alter table machineinfo add MachineType varchar(100) after SupplierContactNumber;
end if;


end//

delimiter ;

call machineinfo_add_columns_20150421();

/*

call machineinfo_add_columns_20150421();

*/