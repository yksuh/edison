create table EDAPP_CommonLibrary (
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	libName VARCHAR(75) not null,
	cLibVer VARCHAR(75) null,
	sysArch VARCHAR(75) not null,
	kernelVer VARCHAR(75) null,
	libPath VARCHAR(75) null,
	primary key (libName, sysArch)
);

create table EDAPP_CommonModule (
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	moduleName VARCHAR(75) not null primary key,
	moduleRootDir VARCHAR(75) null
);

create table EDAPP_CommonPackage (
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	pkgName VARCHAR(75) not null,
	pkgVersion VARCHAR(75) not null,
	sysArch VARCHAR(75) null,
	installMethod VARCHAR(75) null,
	installPath VARCHAR(75) null,
	primary key (pkgName, pkgVersion)
);