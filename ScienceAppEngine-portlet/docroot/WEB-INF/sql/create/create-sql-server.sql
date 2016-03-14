drop database lportal;
create database lportal;

go

use lportal;

create table EDAPP_CommonLibrary (
	commonLibraryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	libName nvarchar(75) null,
	libVersion nvarchar(75) null
);

create table EDAPP_CommonModule (
	commonModuleId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName nvarchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	moduleName nvarchar(75) null,
	moduleVersion nvarchar(75) null
);


create index IX_62502F47 on EDAPP_CommonLibrary (libName);


