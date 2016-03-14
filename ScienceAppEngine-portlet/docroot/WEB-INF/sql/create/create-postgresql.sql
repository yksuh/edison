drop database lportal;
create database lportal encoding = 'UNICODE';
\c lportal;

create table EDAPP_CommonLibrary (
	commonLibraryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	libName varchar(75) null,
	libVersion varchar(75) null
);

create table EDAPP_CommonModule (
	commonModuleId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	moduleName varchar(75) null,
	moduleVersion varchar(75) null
);


create index IX_62502F47 on EDAPP_CommonLibrary (libName);


