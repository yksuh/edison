database sysmaster;
drop database lportal;
create database lportal WITH LOG;

create procedure 'lportal'.isnull(test_string varchar)
returning boolean;
IF test_string IS NULL THEN
	RETURN 't';
ELSE
	RETURN 'f';
END IF
end procedure;


create table EDAPP_CommonLibrary (
	commonLibraryId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	libName varchar(75),
	libVersion varchar(75)
)
extent size 16 next size 16
lock mode row;

create table EDAPP_CommonModule (
	commonModuleId int8 not null primary key,
	groupId int8,
	companyId int8,
	userId int8,
	userName varchar(75),
	createDate datetime YEAR TO FRACTION,
	modifiedDate datetime YEAR TO FRACTION,
	moduleName varchar(75),
	moduleVersion varchar(75)
)
extent size 16 next size 16
lock mode row;


create index IX_62502F47 on EDAPP_CommonLibrary (libName);


