package com.kisti.scienceappengine;

/****
 * Define constant variables used for Science App Web Service
 * @author yksuh
 *
 */
public class Constants {
	/***
	 * The Title of the Science App Under Test 
	 */
	public static String SCIENCE_APP_TITLE_UNDER_TEST = "";
	/***
	 * Science App Engine web service name
	 */
	public static final String SCIENCE_APP_ENGINE = "ScienceAppEngine";
	/***
	 * Tomcat Installation Directory on the development server
	 */
	public static final String TOMCAT_LOCATION="/usr/local/apache-tomcat-7.0.64/";
	//public static final String TOMCAT_LOCATION="/usr/local/apache-tomcat-7.0.63/";
	/***
	 * Webapp Directory
	 */
	public static final String WEBAPP_LOCATION="webapps/";
	/***
	 * Log file directory name
	 */
	public static String SAE_LOG_DIR_NAME = TOMCAT_LOCATION + WEBAPP_LOCATION + "log-files";
	/***
	 * Upload File Directory
	 */
	public static final String UPLOAD_LOCATION="data/";
	/***
	 * Uploaded Science App directory
	 */
	public static final String SCIENCE_APP_LOCATION = TOMCAT_LOCATION + WEBAPP_LOCATION + "sci_apps/";  
	/***
	 * Uploaded Science App Input File directory
	 */
	public static final String SCIENCE_APP_INPUT_FILE_LOCATION = SCIENCE_APP_LOCATION;  
	/***
	 * Uploaded Science App Result Folder Name
	 */
	public static final String OUTPUT_DIRECTORY_NAME="result/";
	/***
	 * Uploaded Science App Result Axis Directory
	 */
	public static final String SCIENCE_APP_OUTPUT_FILE_LOCATION = SCIENCE_APP_INPUT_FILE_LOCATION + OUTPUT_DIRECTORY_NAME; 
	/***
	 * Sample library list
	 */
	public static final String SAMPLE_LIBRARY_LIST_PATH = TOMCAT_LOCATION + WEBAPP_LOCATION + UPLOAD_LOCATION + "lib_list"; 
	/****
	 * Sample module list 
	 */
	public static final String SAMPLE_MODULE_LIST_PATH = TOMCAT_LOCATION + WEBAPP_LOCATION + UPLOAD_LOCATION + "module_list";
	/***
	 * Sample an available module list
	 */
	public static final String SAMPLE_MODULE_AVAIL_PATH = TOMCAT_LOCATION + WEBAPP_LOCATION + UPLOAD_LOCATION + "module_avail"; 
	/***
	 * A sample science app parent directory for local JUnit test
	 */
	public static final String TEST_SCIAPP_DIR = "/Users/yksuh/sci_apps/";
	/***
	 * A sample science app output directory for local JUnit test
	 */
	public static final String TEST_SCIAPP_OUTPUT_DIR = TEST_SCIAPP_DIR + OUTPUT_DIRECTORY_NAME;
	
	/***
	 * A sample science app output directory for local JUnit test
	 */
	public static final String Table = TEST_SCIAPP_DIR + OUTPUT_DIRECTORY_NAME;
	/***
	 * DBMS user name
	 */
	public static final String DBMS_USERNAME = "edison";
	/***
	 * DBMS user password
	 */
	public static final String DBMS_PASSWORD = "edison";
	/***
	 * DBMS connect string
	 */
	public static final String DBMS_CONNECT_STRING = "jdbc:mysql://150.183.247.46/EDISON";
	/***
	 * Solver Table Name
	 */
	public static final String SOLVER_TABLE_NAME = "EDAPP_SOLVER";
	/***
	 * Solver Test Table Name
	 */
	public static final String SOLVER_TEST_TABLE_NAME = "EDAPP_SOLVER_TEST";
	/***
	 * Timestamp format
	 */
	public static final String TIMESTAMPFORMAT = "DY, MON DD YYYY HH24:MI:SS:FF";
	/****
	 * Date format
	 */
	public static final String DATEFORMAT = "DY, MON DD YYYY HH24:MI:SS";
	/****
	 * Time format for display
	 */
	public static final String TIMEFORMAT = "EEE, MMM dd yyyy HH:mm:ss:SSS";
	/****
	 * EDISON table's prefix
	 */
	public static String TABLE_PREFIX = "EDAPP_";
	/****
	 * EDISON solver table
	 */
	public static String TABLE_SOLVER = "SOLVER";
	/****
	 * EDISON solver test table
	 */
	public static String TABLE_SOLVER_TEST = "SOLVER_TEST";
	/****
	 * EDISON solver test table
	 */
	public static String TABLE_SOLVER_TEST_LOG = "SOLVER_TEST_LOG";
//	/****
//	 * EDISON solver sequence
//	 */
//	public static String SEQUENCE_SOLVER = "SEQ_SOLVER";
//	/****
//	 * EDISON solver sequence
//	 */
//	public static String SEQUENCE_SOLVER_TEST = "SEQ_SOLVER_TEST";
//	/****
//	 * EDISON solver test sequence
//	 */
//	public static String SEQUENCE_SOLVER_TEST_LOG = "SEQ_SOLVER_TEST_LOG";
//	/****
//	 * Solver Table CREATE Statement
//	 */
//	public static final String SOLVER_CREATE_STATEMENT = "CREATE TABLE " + SOLVER_TABLE_NAME + " ( ID INT AUTO_INCREMENT PRIMARY KEY, Title VARCHAR(255) NOT NULL, Description VARCHAR(4000) )";
//	/****
//	 * Solver INSERT Statement
//	 */
//	public static final String SOLVER_INSERT_STATEMENT = "INSERT INTO " + SOLVER_TABLE_NAME + " EDAPP_SOLVER (title, description) values ('2D_Comp', 'Developed by SNU') " + SOLVER_TABLE_NAME + " ( ID INT AUTO_INCREMENT PRIMARY KEY, Title VARCHAR(255) NOT NULL, Description VARCHAR(4000) )";
//	/***
//	 * Solver Test Table Name
//	 */
//	public static final String SOLVER_TEST_TABLE_NAME = "EDAPP_SOLVER_TEST";
//	/****
//	 * Solver Table CREATE Statement
//	 */
//	public static final String SOLVER_TEST_CREATE_STATEMENT = "CREATE TABLE " + SOLVER_TEST_TABLE_NAME 
//			+ "( SolverID INT not null, "
//			+ " TestStartTime TIMESTAMP not null default current_timestamp, "
//			+ "	TestStatus varchar(4000) not null, "
//			+ "	TestStatusUpdateTime TIMESTAMP not null default current_timestamp on update current_timestamp, "
//			+ "	PRIMARY KEY (SolverID, TestStartTime), "
//			+ "	FOREIGN KEY (SolverID) REFERENCES EDADD_SOLVER(ID) "
//			+ ")";
}
