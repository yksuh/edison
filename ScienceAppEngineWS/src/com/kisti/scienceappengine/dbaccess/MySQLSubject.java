package com.kisti.scienceappengine.dbaccess;

public class MySQLSubject extends GeneralDBMS {
	/***
	 * Define driver class name.
	 */
	private static final String DBMS_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

	/***
	 * DBMS name.
	 */
	private static final String DBMS_NAME = "MySQL";
	/**
	 * The name of the data type to store integer numbers in MySQL.
	 */
	private static final String NUMBER = "INT";
	/**
	 * The name of the character data type for MySQL.
	 */
	private static final String VARCHAR = "VARCHAR";
	/**
	 * The name of the timestamp data type for MySQL.
	 */
	private static final String TIMESTAMP = "TIMESTAMP";
	
	/***
	 * Constructor for MySQL subject
	 * @param connectionString
	 */
	public MySQLSubject(String dbmsUserName, String dbmsPassword, String connectionString) {
		super(dbmsUserName, dbmsPassword, connectionString);
		// TODO Auto-generated constructor stub
	}
	
	/***
	 * Constructor for MySQL subject
	 * @param connectionString
	 */
	public MySQLSubject(String connectionString) {
		super(connectionString);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getDBMSDriverClassName() {
		return DBMS_DRIVER_CLASS_NAME;
	}

	/***
	 * Return this DBMS name.
	 * 
	 * @return
	 */
	public String getDBMSName() {
		return DBMS_NAME;
	}

	/**
	 * Given AZDBLAB's integer represenation of a data type, this produces a
	 * representation of the data type.
	 * 
	 * @param dataType
	 *            The data type
	 * @param length
	 *            The number of digits for this value.
	 * @return A string representation of this data type/length. This value can
	 *         be used in a create table statement.
	 */
	protected String getDataTypeAsString(int dataType, int length) {
		switch (dataType) {
			case GeneralDBMS.I_DATA_TYPE_NUMBER: {
				return NUMBER;
			}
			case GeneralDBMS.I_DATA_TYPE_VARCHAR: {
				return VARCHAR + "(" + length + ")";
			}
			case GeneralDBMS.I_DATA_TYPE_TIMESTAMP: {
				return TIMESTAMP;
			}
			case GeneralDBMS.I_DATA_TYPE_TIMESTAMP_DEFAULT_CURRENT: {
				return TIMESTAMP + " default current_timestamp ";
			}
			case GeneralDBMS.I_DATA_TYPE_TIMESTAMP_UPDATE_CURRENT: {
			//	return TIMESTAMP + " default current_timestamp on update current_timestamp ";
				return TIMESTAMP + " on update current_timestamp ";
			}
			default: {
				//ScienceAppEngine._logger.reportError("Unknown data type: " + dataType);
				System.err.println("Unknown data type: " + dataType);
				System.exit(1);
				// problem with xml schema. should have been caught
				return null;
			}
		}
	}

//	public int getRunID() {
//		String sql = "Select runID from " + Constants.TABLE_PREFIX
////				+ Constants.TABLE_EXPERIMENTRUN + " where startTime = '"
////				+ strStartTime + "'";
//				+ Constants.TABLE_EXPERIMENTRUN + " where StartTime = " 
//				+ "to_timestamp('" + strStartTime + "', '" + Constants.TIMESTAMPFORMAT + "')";
//		try {
//			ResultSet rs = LabShelfManager.getShelf().executeQuerySQL(sql);
//			if (rs.next()) {
//				return rs.getInt(1);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;
//		// return User.getUser(strUserName).getNotebook(strNotebookName)
//		// .getExperiment(strExperimentName).getRun(strStartTime)
//		// .getRunID();
//	}
	
	/*****
	 * SQL commands useful to create database and user on MySQL create DATABASE
	 * EDISON; CREATE USER 'edison'@'localhost' IDENTIFIED BY 'edison'; GRANT
	 * ALL PRIVILEGES ON *.* TO 'edison'@'%' IDENTIFIED BY 'edison' WITH GRANT
	 * OPTION; FLUSH PRIVILEGES;
	 * 
	 * CREATE TABLE EDAPP_SOLVER ( ID INT AUTO_INCREMENT not null, 
	 * Title VARCHAR(255) NOT NULL, Name VarChar(100), Description VARCHAR(4000), 
	 * PRIMARY KEY (ID)); 
	 * INSERT INTO EDAPP_SOLVER_TEST (title, description) values ('2D_Comp', 'Developed by SNU');
	 * 
	 * CREATE TABLE EDAPP_SOLVER_TEST ( 
	 * ID int not null, 
	 * TestStartTime TIMESTAMP default current_timestamp not null, 
	 * TestStatus varchar(4000) not null, 
	 * TestStatusUpdateTime TIMESTAMP default current_timestamp on update current_timestamp not null, 
	 * FOREIGN KEY (SolverID)
	 * REFERENCES EDADD_SOLVER(ID) ); 
	 * INSERT INTO EDAPP_SOLVER_TEST (SolverID, TestStatus) values (1, 'Test Started'); 
	 * INSERT INTO EDAPP_SOLVER_TEST_LOG (2, 'Library Installation');
	 * 
	 * CREATE TABLE EDAPP_SOLVER ( ID INT AUTO_INCREMENT NOT NULL, 
	 * 								Title VARCHAR(100) NOT NULL, 
	 * 								Name VARCHAR(255), 
	 * 								Description VARCHAR(4000), 
	 * 								PRIMARY KEY(ID))
	 * CREATE TABLE EDAPP_SOLVER_TEST ( TestID INT AUTO_INCREMENT NOT NULL, 
	 * 									ID INT NOT NULL, 
	 * 									TestStartTime TIMESTAMP default current_timestamp  NOT NULL, 
	 * 									PRIMARY KEY(TestID), UNIQUE(ID, TestStartTime), 
	 * 									FOREIGN KEY(ID) REFERENCES EDAPP_SOLVER (ID) ON DELETE CASCADE)
	 * CREATE TABLE EDAPP_SOLVER_TEST_LOG ( TestID INT NOT NULL, 
	 * 										TestStatusUpdateTime TIMESTAMP default current_timestamp  NOT NULL, 
	 * 										TestStatus VARCHAR(4000) NOT NULL, 
	 * 										PRIMARY KEY(TestID, TestStatusUpdateTime), 
	 * 										FOREIGN KEY(TestID) REFERENCES EDAPP_SOLVER_TEST (TestID) ON DELETE CASCADE)
	 *
	 * 
	 */
}
