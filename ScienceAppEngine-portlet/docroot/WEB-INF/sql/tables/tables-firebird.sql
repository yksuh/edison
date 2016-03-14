create table EDAPP_CommonLibrary (
	commonLibraryId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	libName varchar(75),
	libVersion varchar(75)
);

create table EDAPP_CommonModule (
	commonModuleId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	moduleName varchar(75),
	moduleVersion varchar(75)
);
