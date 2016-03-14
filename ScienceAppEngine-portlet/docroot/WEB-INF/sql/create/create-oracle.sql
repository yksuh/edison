drop user &1 cascade;
create user &1 identified by &2;
grant connect,resource to &1;
connect &1/&2;
set define off;

create table EDAPP_CommonLibrary (
	commonLibraryId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	libName VARCHAR2(75 CHAR) null,
	libVersion VARCHAR2(75 CHAR) null
);

create table EDAPP_CommonModule (
	commonModuleId number(30,0) not null primary key,
	groupId number(30,0),
	companyId number(30,0),
	userId number(30,0),
	userName VARCHAR2(75 CHAR) null,
	createDate timestamp null,
	modifiedDate timestamp null,
	moduleName VARCHAR2(75 CHAR) null,
	moduleVersion VARCHAR2(75 CHAR) null
);


create index IX_62502F47 on EDAPP_CommonLibrary (libName);



quit