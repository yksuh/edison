create table EDAPP_CommonLibrary (
	commonLibraryId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	libName varchar(75) null,
	libVersion varchar(75) null
) engine InnoDB;

create table EDAPP_CommonModule (
	commonModuleId bigint not null primary key,
	groupId bigint,
	companyId bigint,
	userId bigint,
	userName varchar(75) null,
	createDate datetime null,
	modifiedDate datetime null,
	moduleName varchar(75) null,
	moduleVersion varchar(75) null
) engine InnoDB;
