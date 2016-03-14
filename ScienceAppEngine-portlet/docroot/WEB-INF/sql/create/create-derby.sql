drop database lportal;
create database lportal;
connect to lportal;
create table EDAPP_CommonLibrary (
	commonLibraryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	libName varchar(75),
	libVersion varchar(75)
);

create table EDAPP_CommonModule (
	commonModuleId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	moduleName varchar(75),
	moduleVersion varchar(75)
);


create index IX_62502F47 on EDAPP_CommonLibrary (libName);


