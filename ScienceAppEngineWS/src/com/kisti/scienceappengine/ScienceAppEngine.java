package com.kisti.scienceappengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 * This class defines a module installed on our cluster. The object of a module
 * includes module name, c lib version, system architecture, kernel version, and
 * module path.
 * 
 * @author Young-Kyoon Suh (yksuh@kisti.re.kr)
 * 
 */
class Module {
	public String modName = "";
	public String modRootDir = "";

	/****
	 * Build a constructor for a Module instance
	 * 
	 * @param mod_name
	 * @param mod_path
	 */
	public Module(String mod_name, String mod_root_dir) {
		modName = mod_name;
		modRootDir = mod_root_dir;
	}

	/***
	 * Return the information of this library instance in XML
	 *
	 */
	public String toStringInXML() {
		// Declare a temporary string buffer variable.
		StringBuffer sb = new StringBuffer();
		// Declare a root element.
		sb.append("\t<ModuleInfo>\n");
		// Create a module name element using this module name data.
		sb.append("\t\t<modName>" + this.modName + "</modName>\n");
		if(!modRootDir.equalsIgnoreCase("")){
			sb.append("\t\t<modRootDir>" + this.modRootDir + "</modRootDir>\n");
		}
		else{
			// If there's no root directory information of this module instance, 
			// then it should be empty. 
			sb.append("\t\t<modRootDir />\n");
		}
		// Let's close module info element.
		sb.append("\t</ModuleInfo>");
		// Then return the final module info element.
		return sb.toString();
	}
}

/**
 * This class defines a library installed on our cluster. The object of a
 * library includes library name, c lib version, system architecture, kernel
 * version, and library path.
 * 
 * @author Young-Kyoon Suh (yksuh@kisti.re.kr)
 * 
 */
class Library {
	// Library name
	public String libName = "";
	// linux C library version
	public String linuxCLibVer = "";
	// System architecture: i386, i686, x86-64, ..., etc.
	public String sysArch = "";
	// Linux kernel version
	public String kernelVer = "";
	// Library installation path
	public String libPath = "";

	/****
	 * Build a constructor for a Library instance
	 * 
	 * @param lib_name library name
	 * @param linux_clib_ver Linux c library version
	 * @param sys_arch system architecture
	 * @param kernel_ver kernel version
	 * @param lib_path library installation path
	 */
	public Library(String lib_name, String linux_clib_ver, String sys_arch,
			String kernel_ver, String lib_path) {
		// Store the given library name.
		libName = lib_name;
		// Store the given linux c library version.
		linuxCLibVer = linux_clib_ver;
		// Store system architecture data.
		sysArch = sys_arch;
		// Store kernel version.
		kernelVer = kernel_ver;
		// Store library installation path.
		libPath = lib_path;
	}

	/***
	 * Return the information of this library instance in XML
	 * 
	 */
	public String toStringInXML() {
		// Declare a temporary string buffer variable.
		StringBuffer sb = new StringBuffer();
		// Declare a root element.
		sb.append("\t<LibraryInfo>\n");
		/***
		 * Any of these elements shouldn't be null.
		 * Otherwise, we should report an error message. 
		 * But for simplicity we'll take care of such a case as empty element.
		 */
		// Create a library name element using this library name data.
		if(this.libName != ""){
			sb.append("\t\t<libName>" + this.libName + "</libName>\n");
		}
		// Let's make an empty closed element.
		else{ 
			sb.append("\t\t<libName />\n");
		}
		// Create a Linux c library version element.
		if(this.linuxCLibVer != ""){
			sb.append("\t\t<libCLibVer>" + this.linuxCLibVer + "</libCLibVer>\n");
		}
		// Let's make an empty closed element.
		else{
			sb.append("\t\t<libCLibVer />\n");
		}
		// Create a system architecture
		if(this.sysArch != ""){
			sb.append("\t\t<sysArch>" + this.sysArch + "</sysArch>\n");
		}
		// Let's make an empty closed element.
		else{
			sb.append("\t\t<sysArch />\n");
		}
		// Create a kernel version element
		if(this.kernelVer != ""){
			sb.append("\t\t<kernelVer>" + this.kernelVer + "</kernelVer>\n");
		}
		else{// Let's make an empty closed element.
			sb.append("\t\t<libPath>" + this.libPath + "</libPath>\n");
		}
		// Let's close library info element.
		sb.append("\t</LibraryInfo>");
		// Then return the final module info element.
		return sb.toString();
	}
}

/****
 * Define ScienceApp Engine. The engine provides several APIs related to testing
 * a science app (solver) on our cluster. The APIs are to search for an
 * installed library(ies), to install a given library, to search for an
 * installed module(s), to install a module, and to make a testing simulation
 * for the science app to be enrolled in SpyGlass.
 * 
 * @author yksuh
 *
 */
// @Path here defines class level path. Identifies the URI path that
// a resource class will serve requests for.
@Path("SAEServ")
public class ScienceAppEngine {
	/**
	 * The definition of the batchset internal table.
	 * 
	 * @see InternalTable
	 */
	public static final InternalTable SOLVER = new InternalTable(
			Constants.TABLE_PREFIX + Constants.TABLE_SOLVER,
			new String[] { 
					"ID", 			// solver id
					"Title",		// solver title
					"Name",			// solver name
					"Description"	// solver description
			},
			new int[] { 
					GeneralDBMS.I_DATA_TYPE_NUMBER,
					GeneralDBMS.I_DATA_TYPE_VARCHAR,
					GeneralDBMS.I_DATA_TYPE_VARCHAR,
					GeneralDBMS.I_DATA_TYPE_VARCHAR
			}, 
			new int[] {10, 100, 255, 4000}, // column lengths
			new int[] {1,0,0,0}, // auto increment column index
			new int[] {0,0,1,1}, // nullable column index
			null, 				 // unique
			new String[] {"ID" },// primary key
			null,				 // foreign key
			"" // sequence name
	);
	/**
	 * The definition of the batch internal table.
	 * 
	 * @see InternalTable
	 */
	public static final InternalTable SOLVER_TEST  = new InternalTable(
			Constants.TABLE_PREFIX + Constants.TABLE_SOLVER_TEST,
			new String[] { 
					"TestID",			// test id (surrogate key)
					"ID", 				// solver id
					"TestStartTime"		// test start time
			},
			new int[] { 
					GeneralDBMS.I_DATA_TYPE_NUMBER,
					GeneralDBMS.I_DATA_TYPE_NUMBER,
					GeneralDBMS.I_DATA_TYPE_TIMESTAMP_DEFAULT_CURRENT
			}, 
			new int[] {0, 0, 0}, 	// column lengths
			new int[] {1, 0, 0}, 	// auto increment column index
			new int[] {0, 0, 0}, 	// nullable column index
			new String[] {"ID", "TestStartTime"},// unique
			new String[] {"TestID"},// primary key
			new ForeignKey[] { 
					new ForeignKey(
							new String[] { "ID" }, 
							Constants.TABLE_PREFIX + Constants.TABLE_SOLVER, 
							new String[] { "ID" }, 
							" ON DELETE CASCADE") 
			},				 					 // foreign key
			""		 // sequence name
	);
	
	/**
	 * The definition of the batch internal table.
	 * 
	 * @see InternalTable
	 */
	public static final InternalTable SOLVER_TEST_LOG  = new InternalTable(
			Constants.TABLE_PREFIX + Constants.TABLE_SOLVER_TEST_LOG,
			new String[] { 
					"TestID", 				// solver test id
					"TestStatusUpdateTime",	// test status update time
					"TestStatus"			// test status
			},
			new int[] { 
					GeneralDBMS.I_DATA_TYPE_NUMBER,
					GeneralDBMS.I_DATA_TYPE_TIMESTAMP_DEFAULT_CURRENT,
					GeneralDBMS.I_DATA_TYPE_VARCHAR
			}, 
			new int[] {0, 0, 4000}, // column lengths
			new int[] {0, 0, 0}, // auto increment column index
			new int[] {0, 0, 0}, // nullable column index
			null, 				 // unique
			new String[] {"TestID", "TestStatusUpdateTime"},// primary key
			new ForeignKey[] { 
					new ForeignKey(
							new String[] { "TestID" }, 
							Constants.TABLE_PREFIX + Constants.TABLE_SOLVER_TEST, 
							new String[] { "TestID" }, 
							" ON DELETE CASCADE") 
			},				 					 // foreign key
			"" 									 // sequence name
	);
	
	/***
	 * DBMS subject managing science app test data
	 */
	MySQLSubject mysqlDBMS;
	/**
	 * Installed libraries
	 */
	Vector<Library> libList = new Vector<Library>();
	/***
	 * Installed modules 
	 */
	Vector<Module> installedModList = new Vector<Module>();
	/***
	 * Modules available for installation
	 */
	Vector<Module> availModList = new Vector<Module>();
	/****
	 * ScienceAppEngine logger for logging
	 */
	public static ScienceAppEngineLogger _logger;

	public ScienceAppEngine() {
		setScienceAppEngineLogger(Constants.SCIENCE_APP_ENGINE);

		// connect to DB
		connectToDB();
		
		// create solver test tables
		createTestTables();
	}
	
	/***
	 * ScienceAppEngine constructor for JUnit test
	 */
	public ScienceAppEngine(boolean UNDER_TEST) {
//		if(!UNDER_TEST){
//			setScienceAppEngineLogger(Constants.SCIENCE_APP_ENGINE);
//		}

		// connect to DB
		connectToDB();
		
		// create solver test tables
		//createTestTables();
	}

	/****
	 * Set either an observer or executor logger for its own logging.
	 * 
	 * @param loggerTypeName
	 *            observer or executor
	 */
	public static void setScienceAppEngineLogger(String loggerTypeName) {
		_logger = new ScienceAppEngineLogger(loggerTypeName);
		String computername = "";
		try {
			computername = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.err.println("The executable cannot get host name");
			System.err.println("Please retry again.");
			System.exit(-1);
		}
		SimpleDateFormat logsdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String strLoggingTime = logsdf.format(new Date(System
				.currentTimeMillis()));

		String logFileName = Constants.SAE_LOG_DIR_NAME + File.separator
				+ computername + "." + loggerTypeName + "." + strLoggingTime
				+ ".log";

		try {
			_logger.setScienceAppEngineAppender(computername, strLoggingTime,
					loggerTypeName, logFileName);
		} catch (IOException e) {
			// e.printStackTrace();
			System.err.println("Appender cannot be created due to permission.");
			System.err.println(loggerTypeName + " shuts down. ");
			System.err.println("Please retry again.");
			System.exit(-1);
		}
		_logger.setScienceAppEngineLoggerName(logFileName);
	}

	/****
	 * Test a science app on our cluster
	 * 
	 * @param sa_name
	 * @return
	 */
	// @POST here defines, this method will method will process HTTP POST
	// requests.
	@POST
	// @Path here defines method level path. Identifies the URI path that a
	// resource class method will serve requests for.
	@Path("/apptest")
	// @Produces here defines the media type(s) that the methods
	// of a resource class can produce.
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_HTML)
	// @PathParam injects the value of URI parameter that defined in @Path
	// expression, into the method.
	public Response doScienceAppTesting(
			@FormDataParam("app_title") String app_title,
			@FormDataParam("app_binary") InputStream uploadedInputStream,
	        @FormDataParam("app_binary") FormDataContentDisposition fileDetail,
	        @FormDataParam("app_input") InputStream uploadedInputStream2,
	        @FormDataParam("app_input") FormDataContentDisposition fileDetail2) {
		//_logger.outputLog(sa_name + " is requested for testing.");
		StringBuffer sb = new StringBuffer();
		sb.append("The title of the given Science app: " + app_title);
		
		// Initialize the title of a science app under test to that of the given science app.
		Constants.SCIENCE_APP_TITLE_UNDER_TEST = app_title;
		
		if(fileDetail == null || fileDetail.getFileName().equals("")){
			return Response.status(200).entity("No Science App Uploaded").build();
		}
		
		if(fileDetail2 == null || fileDetail2.getFileName().equals("")){
			return Response.status(200).entity("No Science App Input File Uploaded").build();
		}
		
		// Store the file name of the given science app.
		String scienceAppFileName = fileDetail.getFileName();

System.out.println("science app name: " + scienceAppFileName);
		
		// Store the input file name of the given science app.
		String scienceAppInputFileName = fileDetail2.getFileName();

System.out.println("science input file name: " + scienceAppInputFileName);

		// Specify a directory to store an uploaded Science App file.
        String scienceAppPath = Constants.SCIENCE_APP_LOCATION + scienceAppFileName;
        //System.out.println(uploadedFileLocation);
        
        // save this science app file
        saveToFile(uploadedInputStream, scienceAppPath);
        
        // change permission for execute
		changeExecPermission("x", scienceAppPath);
     	
        // Specify a directory to store an uploaded Science App file.
        String scienceAppInputFilePath = Constants.SCIENCE_APP_INPUT_FILE_LOCATION + scienceAppInputFileName;
        //System.out.println(uploadedFileLocation);
        
        // save this science app file
        saveToFile(uploadedInputStream2, scienceAppInputFilePath);
//        try{
//        	File execFile = new File(scienceAppPath);
//        	execFile.setExecutable(true);
//System.out.println(scienceAppPath + " has been granted for executable.");
//        }catch(Exception ex){
//        	System.err.println(ex.getMessage());
//        }
        
        String solverName = "science app engine sample solver";
        String solverDescription = "science app engine is being tested.";
        
        // Insert a test status message into the solver table.
     	insertRowsIntoTables(app_title, solverName, solverDescription);
     	
        // Submit a job related to this science app.
        // Read the output.
        // We need to invoke Icebreaker's job management routine.
        //String scienceAppOutputFilePath = Constants.SCIENCE_APP_OUTPUT_FILE_LOCATION + "output.txt";
     	String scienceAppOutputFileName = "output.txt";
        String value = submitJob(scienceAppFileName, 
        						 scienceAppInputFileName, 
        						 scienceAppOutputFileName);     
		
		if(value.contains("error")){
			return Response.status(200).entity("<html>" + scienceAppPath + " has no output, which seems problematic ("+value+"</html>").build();
		}
		else{
			String response = "<a href=\""+value+"\">result</a>";
			System.out.println(response);
			return Response.status(200).entity("<html>"+response+"</html>").build();
		}
        // Show a successful message.
        //return Response.status(200).entity("<html>Successfully saved '"+scienceAppPath+"' and '" + scienceAppInputFilePath +"'</html>").build();
	}

	/*****
	 * Give execute permission to a given solver executable file
	 * @param filePath solver location 
	 */
	private void changeExecPermission(String permission, String filePath) {
		// TODO Auto-generated method stub
		// change permission to the uploaded science app for execute
        String command = "sudo chmod +"+permission+" " + filePath;
        try {
			// Create a Runtime instance.
			Runtime rt = Runtime.getRuntime();
			// Execute the command.
			Process p1 = rt.exec(command);
System.out.println(command);
			// Read the input stream.
			InputStream instd = p1.getInputStream();
			// Create a buffered reader
			BufferedReader buf_reader = new BufferedReader(
					new InputStreamReader(instd));
			// Declare a temporary variable to contain a line. 
			String line = "";
			// Declare a temporary variable to store a line count.
			// Begin to read each line from given output (or given file).
			while ((line = buf_reader.readLine()) != null) {
				// Increment line count.
				//System.out.println(line);
			}
			// Close the buffered reader instance.
			buf_reader.close();
			// Let's wait for the Runtime instance to be done.
			p1.waitFor();
			
			InputStream errstd = p1.getErrorStream();
		    BufferedReader buf_err_reader = new BufferedReader(new InputStreamReader(errstd));
		    while ((line = buf_err_reader.readLine()) != null) {  
		    	System.err.println(line);
		    }
		    buf_err_reader.close();
		} catch (Exception ex) {
			// ex.printStackTrace();
			// Print out any message when an error(s) occurs.
			System.err.println(ex.getMessage());
		}      
	}

	/****
	 * Submit a job related to a given science app.
	 * @param sciAppFileName science app file name
	 * @param sciAppInputFileName science app input file name
	 * @param sciAppOutputFileName science app output file name
	 * @return final execution result value
	 */
	public String submitJob(String sciAppFileName, 
							String sciAppInputFileName,
							String sciAppOutputFileName) {
		// move into the science app test directory
//		
//		String command = "sudo cd " + Constants.SCIENCE_APP_LOCATION + "; ";
		// execute the given science app
        String exec = "./" + sciAppFileName + " " + sciAppInputFileName;
		String[] cmd = { "/bin/sh", "-c", "cd "+Constants.SCIENCE_APP_LOCATION+"; "+exec};
System.out.println("exec:" + cmd[2]);
		try {
			// Create a Runtime instance.
			Runtime rt = Runtime.getRuntime();
			// Execute the command.
			Process p1 = rt.exec(cmd);
			// Read the input stream.
			InputStream instd = p1.getInputStream();
			// Create a buffered reader
			BufferedReader buf_reader = new BufferedReader(
					new InputStreamReader(instd));
			// Declare a temporary variable to contain a line. 
			String line = "";
			// Declare a temporary variable to store a line count.
			// Begin to read each line from given output (or given file).
			while ((line = buf_reader.readLine()) != null) {
				// Increment line count.
				//System.out.println(line);
			}
			// Close the buffered reader instance.
			buf_reader.close();
			// Let's wait for the Runtime instance to be done.
			p1.waitFor();
			
			InputStream errstd = p1.getErrorStream();
		    BufferedReader buf_err_reader = new BufferedReader(new InputStreamReader(errstd));
		    while ((line = buf_err_reader.readLine()) != null) {  
		    	System.err.println(line);
		    }
		    buf_err_reader.close();
//		    p1.waitFor();
		} catch (Exception ex) {
			// ex.printStackTrace();
			// Print out any message when an error(s) occurs.
			System.err.println(ex.getMessage());
		}      
        
		// output check
		String line = "";
		String value = "";
		String outputFilePath = Constants.SCIENCE_APP_OUTPUT_FILE_LOCATION + sciAppOutputFileName;
		String command = "cat " + outputFilePath;
        //String command = "module list";
		try {
			// Create a Runtime instance.
			Runtime rt = Runtime.getRuntime();
			// Execute the command.
			Process p1 = rt.exec(command);
System.out.println(command);
			// Read the input stream.
			InputStream instd = p1.getInputStream();
			// Create a buffered reader
			BufferedReader buf_reader = new BufferedReader(
					new InputStreamReader(instd));
			// Begin to read each line from given output (or given file).
			while ((line = buf_reader.readLine()) != null) {
System.out.println("output: " + line);
				value = line;
			}
			// Close the buffered reader instance.
			buf_reader.close();
			// Let's wait for the Runtime instance to be done.
			p1.waitFor();
		} catch (Exception ex) {
			// ex.printStackTrace();
			// Print out any message when an error(s) occurs.
			System.err.println(ex.getMessage());
			return "error: " + ex.getMessage();
		} 
		
		// change permission for execute
		changeExecPermission("x", outputFilePath);
		
		return Constants.SCIENCE_APP_OUTPUT_FILE_LOCATION + sciAppOutputFileName;
	}

	/****
	 * Create a test record for this science app
	 */
	public void connectToDB() {
		// create database access object
		mysqlDBMS = new MySQLSubject(Constants.DBMS_USERNAME, 
									  Constants.DBMS_PASSWORD, 
									  Constants.DBMS_CONNECT_STRING);
		// let's make a connection to MySQL DBMS.
		mysqlDBMS.open(false);
		System.out.println("A connection to the MySQL DBMS instance has been successfully created.");
	}

	/***
	 * Create solver test tables for testing solvers.
	 */
	public void createTestTables(){
		// If mysql dbms is not created, then
		if(mysqlDBMS == null){
			// report an error.
			System.err.println("No MySQL DBMS instance has been created.");
			// let's connect to DB.
			connectToDB();
		}
		
		// Create test tables.
		// Create solver table.
		// In service code the solver table in the EDISON portal db should be accessed.
		mysqlDBMS.createTable(SOLVER.TableName, 
							  SOLVER.columnNames, 
							  SOLVER.columnDataTypes, 
							  SOLVER.columnDataTypeLengths, 
							  SOLVER.autoIncrementColumns, 
							  SOLVER.columnNullable, 
							  SOLVER.uniqueConstraintColumns, 
							  SOLVER.primaryKey, 
							  SOLVER.foreignKey);
		
		// Create solver test table.
		// In service code the solver test table in the EDISON portal db should be accessed.
		mysqlDBMS.createTable(SOLVER_TEST.TableName, 
								SOLVER_TEST.columnNames, 
								SOLVER_TEST.columnDataTypes, 
								SOLVER_TEST.columnDataTypeLengths, 
								SOLVER_TEST.autoIncrementColumns, 
								SOLVER_TEST.columnNullable, 
								SOLVER_TEST.uniqueConstraintColumns, 
								SOLVER_TEST.primaryKey, 
								SOLVER_TEST.foreignKey);
		// Create solver test log table.
		// In service code the solver test log table in the EDISON portal db should be accessed.
		mysqlDBMS.createTable(SOLVER_TEST_LOG.TableName, 
							SOLVER_TEST_LOG.columnNames, 
							SOLVER_TEST_LOG.columnDataTypes, 
							SOLVER_TEST_LOG.columnDataTypeLengths, 
							SOLVER_TEST_LOG.autoIncrementColumns, 
							SOLVER_TEST_LOG.columnNullable, 
							SOLVER_TEST_LOG.uniqueConstraintColumns, 
							SOLVER_TEST_LOG.primaryKey, 
							SOLVER_TEST_LOG.foreignKey);
		mysqlDBMS.commit();
//disconnectToDB();
	}
	
	/***
	 * Insert test rows into tables
	 */
	private void insertRowsIntoTables(String title, String name, String description){
		// If mysql dbms is not created, then
		if(mysqlDBMS == null){
			// report an error.
			System.err.println("No MySQL DBMS instance has been created.");
			// let's connect to DB.
			connectToDB();	
		}
		
		String[] colNames = {"title", "name", "description"};
		String[] colValues = {title, name, description};
		int[] colTypes = {GeneralDBMS.I_DATA_TYPE_VARCHAR,
						  GeneralDBMS.I_DATA_TYPE_VARCHAR, 
						  GeneralDBMS.I_DATA_TYPE_VARCHAR};
		
		/****
		 * Insert a row into SOLVER.
		 */
		mysqlDBMS.buildInsertSQL(SOLVER.TableName, 
								 colNames, 
								 colValues, 
								 colTypes);
		
		String sql = "select " + SOLVER.columnNames[0] 
				  + " from " + SOLVER.TableName 
				  + " order by " + SOLVER.columnNames[0];
		int solverID = -1;
		ResultSet rs = mysqlDBMS.executeQuery(sql);
		if(rs != null){
			try {
				while(rs.next()){
					solverID = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// if solver id is returned, then do the following.
		if(solverID != -1){
			String[] testColNames = {"SolverID", "TestStatus"};
			String[] testColValues = {Integer.toString(solverID), "Test Started"};
			int[] testColTypes = {GeneralDBMS.I_DATA_TYPE_NUMBER, GeneralDBMS.I_DATA_TYPE_VARCHAR};
			
			/****
			 * Insert a row into SOLVER TEST.
			 */
			mysqlDBMS.buildInsertSQL(SOLVER_TEST.TableName, 
									 testColNames, 
									 testColValues, 
									 testColTypes);
		}	
		
		
		String test_sql = "select testID, testStartTime"
						  + " from " + SOLVER_TEST.TableName 
						  + " where ID = " + solverID;
		int testID = -1;
		rs = mysqlDBMS.executeQuery(test_sql);
		if(rs != null){
			try {
				while(rs.next()){
					testID = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String status =  "Test Started";
		// if solver id is returned, then do the following.
		if(testID != -1){
			String[] testColNames = {"TestID", "TestStatus"};
			String[] testColValues = {Integer.toString(solverID), status};
			int[] testColTypes = {GeneralDBMS.I_DATA_TYPE_NUMBER, 
								  GeneralDBMS.I_DATA_TYPE_VARCHAR};
			
			/****
			 * Insert a row into SOLVER TEST.
			 */
			mysqlDBMS.buildInsertSQL(SOLVER_TEST_LOG.TableName, 
									 testColNames, 
									 testColValues, 
									 testColTypes);
		}	
		
		String updateTime = "";
		status = "";
		String testLogSQL = "select * "
						  + " from " + SOLVER_TEST_LOG.TableName
						  + " order by testID asc, TestStatusUpdateTime desc";
		rs = mysqlDBMS.executeQuery(testLogSQL);
		if(rs != null){
			try {
				while(rs.next()){
					testID = rs.getInt(1);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

// Disconnect a connection to DB after finishing some work.
//disconnectToDB();
	}
	
	/***
	 * Insert test rows into tables
	 */
	public void retrieveStatusForSolverInTest(){
		// If mysql dbms is not created, then
		if(mysqlDBMS == null){
			// report an error.
			System.err.println("No MySQL DBMS instance has been created.");
			connectToDB();
		}
		
		String sql = "select t0.Title as solver_title, t1.TestStartTime, t2.TestStatus, t2.TestStatusUpdateTime " + SOLVER.columnNames[0] 
				  + " from " + SOLVER.TableName + " as t0, " + SOLVER_TEST.TableName + " as t1, " + SOLVER_TEST_LOG.TableName + " as t2 "
				  + " where t0.ID = t1.ID and t1.TestID = t2.TestID"
				  + " order by t1.TestStartTime asc";
		//ScienceAppEngine._logger.outputLog(sql);
		System.out.println(sql);

		int solverID = -1;
		String title = "";
		String testStartTime = "";
		String testStatus = "";
		String testStatusUpdateTime = "";
		
		ResultSet rs = mysqlDBMS.executeQuery(sql);
		if(rs != null){
			try {
				String tblHead = "ID | Title | Test Start Time | Test Status | Test Status Update Time";
				//ScienceAppEngine._logger.outputLog(tblHead);
				System.out.println(tblHead);
				while(rs.next()){
					solverID = rs.getInt(1);
					title = rs.getString(2);
					testStartTime = new SimpleDateFormat(Constants.TIMEFORMAT).format(rs.getTimestamp(3));
					testStatus = rs.getString(4);
					testStatusUpdateTime = new SimpleDateFormat(Constants.TIMEFORMAT).format(rs.getTimestamp(5)); 
					System.out.format("%d | %s | %s | %s | %s", solverID, title, testStartTime, testStatus, testStatusUpdateTime);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("no output.");
		}
// Disconnect a connection to DB after finishing some work.
//disconnectToDB();
	}
	
	public void dropTestTables(){
		// If mysql dbms is not created, then
		if(mysqlDBMS == null){
			connectToDB();
		}
		
		// then, drop solver table.
		mysqlDBMS.dropTable(SOLVER_TEST_LOG.TableName);
		// first drop solver test table.
		mysqlDBMS.dropTable(SOLVER_TEST.TableName);
		// then, drop solver table.
		mysqlDBMS.dropTable(SOLVER.TableName);
		// disconnect to DB.
		//disconnectToDB();
	}
	
	/****
	 * Disconnect a connection to DB by making an external call.
	 */
	public void disconnectToDB() {
		// If mysql dbms is not created, then
		if(mysqlDBMS == null){
			//connectToDB();
			// return 
			return;
		}
		// let's now close an open connection to DB.
		mysqlDBMS.close();
		System.out.println("We have successfully disconnected from MySQL DBMS.");
	}

	/*****
	 * Modules....
	 *  * */
	// @GET here defines, this method will method will process HTTP GET
	// requests.
	@GET
	// @Path here defines method level path. Identifies the URI path that a
	// resource class method will serve requests for.
	@Path("/module/list/{mod_name}")
	// @Produces here defines the media type(s) that the methods
	// of a resource class can produce.
	@Produces(MediaType.TEXT_XML)
	// @PathParam injects the value of URI parameter that defined in @Path
	// expression, into the method.
	public String retrieveModuleByKeyword(@PathParam("mod_name") String mod_name) {
		// We internally invoke retrieveLibrary() and
		// then search for a library(ies) to contain a given library name
		// (libname)
		listModules();
		// Create a root element for representing an available module list
		String res = "<ModuleList>\n";
		// If there's any module, 
		if (installedModList.size() > 0) {
			// then let's iterate each element
			for (int i = 0; i < installedModList.size(); i++) {
				Module mod = installedModList.get(i);
				if (mod.modName.contains(mod_name)) {
					// Convert this module's data into an XML document 
					res += mod.toStringInXML();
					res += "\n";
				}
			}
		}
		// Let's close this module list root element.
		res += "</ModuleList>";
		System.out.println(res);
		return res;
	}

	// @GET here defines, this method will method will process HTTP GET
	// requests.
	@GET
	// @Path here defines method level path. Identifies the URI path that a
	// resource class method will serve requests for.
	@Path("/module/available")
	// @Produces here defines the media type(s) that the methods
	// of a resource class can produce.
	@Produces(MediaType.TEXT_XML)
	// @PathParam injects the value of URI parameter that defined in @Path
	// expression, into the method.
	public String showAvailableModules() {
		// declare a vector of modules listed by the command.
		availModList = new Vector<Module>();
		// The next two lines should be replaced by the next third line when being in service.
		String mod_list_path = Constants.SAMPLE_MODULE_AVAIL_PATH;
		String command = "cat " + mod_list_path;
		//String command = "module avail";
		try {
			// Create a Runtime instance.
			Runtime rt = Runtime.getRuntime();
			// Execute the command.
			Process p1 = rt.exec(command);
			// Read the input stream.
			InputStream instd = p1.getInputStream();
			// Create a buffered reader
			BufferedReader buf_reader = new BufferedReader(
					new InputStreamReader(instd));
			// Declare a temporary variable to contain a line. 
			String line = "";
			// Declare a temporary variable to store a line count.
			int lineCnt = 0;
			// Print out each line for a check
			//System.out.println(lineCnt+": " + line);
			// Declare a temp. var. for storing a module name.
			String modName = "";
			// Declare a temp. var. for storing a module path.
			String modPath = "";
			// Declare a temp. var. for indicating whether to have a root directory for a module.
			boolean isNewModuleRootPath = false;
			// Begin to read each line from given output (or given file).
			while ((line = buf_reader.readLine()) != null) {
				// Trim this line.
				line = line.trim();
				// If there's nothing then skip this line.
				if(line.equalsIgnoreCase("")) continue;
				// Increment line count.
				lineCnt++;
				// If line count is one, then
				if (lineCnt % 2 == 1){
					// Use tokenizer to parse a line.
					// An example of a line to be parsed:
					//  ------------------------------------------------------------------------------------ /usr/share/Modules/modulefiles --------
					// , and so on.
					StringTokenizer st = new StringTokenizer(line, "-|\t| ");
					// Let's use a separator like ')'.
					while (st.hasMoreTokens()) {
						// Get a parsed token
						String token = (st.nextToken()).trim();
						// If this token doesn't have anything, then skip it.
						if (token.equalsIgnoreCase("")) continue;
						// Check if the current root path is not null, and token starts with a directory name
						if(!isNewModuleRootPath && token.startsWith("/")){
							// This will be a module parent path. 
							modPath = token;
							// Set the flag to indicate we're in this root path.
							isNewModuleRootPath = true;
							// Let' get out this loop.
							break;
						}
					}	
				}else{
					// Use tokenizer to parse a line.
					// An example of a line to be parsed:
					// dot         module-git  module-info modules     null        use.own 
					StringTokenizer st = new StringTokenizer(line, "\t| ");
					// Let's use a separator like ')'.
					while (st.hasMoreTokens()) {
						// Get a parsed token
						String token = (st.nextToken()).trim();
						if (token.equalsIgnoreCase("")) continue;
						// Get the module name 
						modName = token;
						// If the module name is equal to 'null', then we skip it.
						if(modName.equalsIgnoreCase("null")) continue;
						// Let's print out the name of the parsed module.
						//System.out.println("module name: "+modName + ", path: " + modPath);
						// Create an instance of Module.
						Module mod = new Module(modName, modPath);
						// Add this module to the available vector
						availModList.add(mod);
					}
					// Reset this flag for iterating the next root path.
					isNewModuleRootPath = false;
				}
			}
			// Close the buffered reader instance.
			buf_reader.close();
			// Let's wait for the Runtime instance to be done.
			p1.waitFor();
		} catch (Exception ex) {
			// ex.printStackTrace();
			// Print out any message when an error(s) occurs.
			System.err.println(ex.getMessage());
		}
		/*** 
		 * 
		 * Return installed libraries.
		 * 
		 * ***/
		// Declare a string to contain the result module list string.
		StringBuffer resList = new StringBuffer();
		// Let's append the root element string.
		resList.append("<ModuleList>\n");
		// Let's iterate each module stored. 
		for (int i = 0; i < availModList.size(); i++) {
			// Let's construct module element using the module name
			resList.append((availModList.get(i)).toStringInXML() + "\n");
		}
		// Let's close module list element.
		resList.append("</ModuleList>\n");
		// Make the list as the string for return.
		String finalResList = resList.toString();
		// Let's print it out.
		System.out.println(finalResList);
		// Return the XML document.
		return finalResList;
	}
	
	// @GET here defines, this method will method will process HTTP GET
	// requests.
	@GET
	// @Path here defines method level path. Identifies the URI path that a
	// resource class method will serve requests for.
	@Path("/module/list")
	// @Produces here defines the media type(s) that the methods
	// of a resource class can produce.
	@Produces(MediaType.TEXT_XML)
	// @PathParam injects the value of URI parameter that defined in @Path
	// expression, into the method.
	public String listModules() {
		// declare a vector of modules listed by the command.
		installedModList = new Vector<Module>();
		// The next two lines should be replaced by the next third line when being in service.
		String mod_list_path = Constants.SAMPLE_MODULE_LIST_PATH;
		String command = "cat " + mod_list_path;
		//String command = "module list";
		try {
			// Create a Runtime instance.
			Runtime rt = Runtime.getRuntime();
			// Execute the command.
			Process p1 = rt.exec(command);
			// Read the input stream.
			InputStream instd = p1.getInputStream();
			// Create a buffered reader
			BufferedReader buf_reader = new BufferedReader(
					new InputStreamReader(instd));
			// Declare a temporary variable to contain a line. 
			String line = "";
			// Declare a temporary variable to store a line count.
			int lineCnt = 0;
			// Begin to read each line from given output (or given file).
			while ((line = buf_reader.readLine()) != null) {
				// Increment line count.
				lineCnt++;
				// If line count is one, then
				if (lineCnt == 1){
					// the first line will be either of the following two:
					// a) "No Modulefiles Currently Loaded."
					// b) "Currently Loaded Modulefiles:"
					// So we'll not parse this line but instead skip it.
					continue;
				}
				// Print out each line for a check
				//System.out.println(lineCnt+": " + line);
				// Declare a temp. var. for storing a module name.
				String modName = "";
				// Declare a temp. var. for storing a module path.
				String modPath = "";
				// Declare a token index
				int tokenIdx = 0;
				// Use tokenizer to parse a line.
				// An example of a line to be parsed:
				//  1) intel/intel_11            3) gamess/gamess             5) mpi/gnu/openmpi-1.6.4     7) module-git                9) modules
				//  2) mpi/gnu/openmpi-1.6.5     4) mpi/intel/mpich-1.2.7p1   6) dot                       8) module-info              10) use.own . 
				StringTokenizer st = new StringTokenizer(line, ")|\t| ");
				// Let's use a separator like ')'.
				while (st.hasMoreTokens()) {
					// Get a parsed token
					String token = (st.nextToken()).trim();
					if (token.equalsIgnoreCase("")) continue;
					// Increment token index.
					tokenIdx++;
					// Check the token string.
					//System.out.println(token);
					// If a token index is odd, 
					if (tokenIdx % 2 == 1) {
						// then it will be module number such as 1, 2, ..., and 10. 
						// So we should skip this.
						continue;
					}else{ // Otherwise, it will be module name.
						modName = token;
						// Let's print out the name of the parsed module.
						//System.out.println("module name: "+modName);
						// Create an instance of Module.
						Module mod = new Module(modName, "");
						// Add this module to the module vector
						installedModList.add(mod);
					}
				}
			}
			// Close the buffered reader instance.
			buf_reader.close();
			// Let's wait for the Runtime instance to be done.
			p1.waitFor();
		} catch (Exception ex) {
			// ex.printStackTrace();
			// Print out any message when an error(s) occurs.
			System.err.println(ex.getMessage());
		}
		/*** 
		 * 
		 * Return installed libraries.
		 * 
		 * ***/
		// Declare a string to contain the result module list string.
		StringBuffer resList = new StringBuffer();
		// Let's append the root element string.
		resList.append("<ModuleList>\n");
		// Let's iterate each module stored. 
		for (int i = 0; i < installedModList.size(); i++) {
			// Let's construct module element using the module name
			resList.append((installedModList.get(i)).toStringInXML() + "\n");
		}
		resList.append("</ModuleList>\n");
		String finalResList = resList.toString();
		System.out.println(finalResList);
		return finalResList;
	}
	
	// @GET here defines, this method will method will process HTTP GET
	// requests.
	@GET
	// @Path here defines method level path. Identifies the URI path that a
	// resource class method will serve requests for.
	@Path("/module/load/{mod_name}")
	// @Produces here defines the media type(s) that the methods
	// of a resource class can produce.
	@Produces(MediaType.TEXT_HTML)
	// @PathParam injects the value of URI parameter that defined in @Path
	// expression, into the method.
	public String loadModuleByKeyword(@PathParam("mod_name") String mod_name) {
		// We internally invoke retrieveLibrary() and
		// then search for a library(ies) to contain a given library name
		// (libname)
		System.out.println("Let's install '" +  mod_name + "'");
		String res = "";
		try {
			/****
			 * Check if more than one core is enabled.
			 */
			_logger.outputLog("We start to install module '"+ mod_name +"'");
			String command = "module load " + mod_name;
			_logger.outputLog("command: " + command);
			// Construct a command and its arguments
			String[] commandAndArgs = new String[]{ "/bin/sh", "-c", command};
			Runtime rt = Runtime.getRuntime();
		    Process p0 = rt.exec(commandAndArgs);
		    InputStream instd = p0.getInputStream();
		    BufferedReader buf_reader = new BufferedReader(new InputStreamReader(instd));
		    String temp=null, tmp_data = "";
		    while ((temp = buf_reader.readLine()) != null) {  
		    	tmp_data += temp;
		    }
		    if(tmp_data.equalsIgnoreCase("")){
		    	res = "<html>Success!</html>";
		    }
		    _logger.outputLog(tmp_data);
		    buf_reader.close();
		    InputStream errstd = p0.getErrorStream();
		    BufferedReader buf_err_reader = new BufferedReader(new InputStreamReader(errstd));
		    String tmp_err_data = "";
		    temp = null;
		    while ((temp = buf_err_reader.readLine()) != null) {  
		    	tmp_err_data += temp;
		    }
		    if(!tmp_err_data.equalsIgnoreCase("")){
		    	res = "<html>Fail: " + tmp_data + "</html>";
		    }
		    _logger.reportErrorNotOnConsole(tmp_err_data);
		    buf_err_reader.close();
		    p0.waitFor();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(res);
		return res;
	}
		
/*****
 * Libraries....
 *  * */
	
	// @GET here defines, this method will method will process HTTP GET
	// requests.
	@GET
	// @Path here defines method level path. Identifies the URI path that a
	// resource class method will serve requests for.
	@Path("/library/search/{libName}")
	// @Produces here defines the media type(s) that the methods
	// of a resource class can produce.
	@Produces(MediaType.TEXT_XML)
	// @PathParam injects the value of URI parameter that defined in @Path
	// expression, into the method.
	public String retrieveLibraryByKeyword(@PathParam("libName") String lib_name) {
		// We internally invoke retrieveLibrary() and
		// then search for a library(ies) to contain a given library name
		// (libname)
		retrieveLibrary();

		// Create a library list root element
		String res = "<LibraryList>\n";
		// If this list has some elements, 
		if (libList.size() > 0) {
			// then let's iterate each element and add it to the list.
			for (int i = 0; i < libList.size(); i++) {
				// Let's get an indexed library item.
				Library lib = libList.get(i);
				// If this library matches the given lib_name,
				if (lib.libName.contains(lib_name)) {
					// then let's add this library and convert it into XML.
					res += lib.toStringInXML();
					res += "\n";
				}
			}
		}
		// Close the root element.
		res += "</LibraryList>";
		System.out.print(res);
		// Return the final library list string.
		return res;
	}
		
	@POST
	@Path("/library/install/")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response installLibrary(
        @FormDataParam("libFile") InputStream uploadedInputStream,
        @FormDataParam("libFile") FormDataContentDisposition fileDetail) {
 
		if(fileDetail == null || fileDetail.getFileName().equals("")){
			return Response.status(200).entity("No File Selected").build();
		}

		// Specify a directory to store an uploaded library file.
        //String uploadedFileLocation = "/usr/local/apache-tomcat-7.0.63/webapps/data/" + fileDetail.getFileName();
		String uploadedFileLocation = Constants.TOMCAT_LOCATION + Constants.WEBAPP_LOCATION + Constants.UPLOAD_LOCATION + fileDetail.getFileName();
        //System.out.println(uploadedFileLocation);
        
        // save this library file
        saveToFile(uploadedInputStream, uploadedFileLocation);
        // 
        //String output = "File uploaded via Jersey based RESTFul Webservice to: " + uploadedFileLocation;
 
        // Let's remember any output while being installed.
        String normalOutput="";
        String errorOutput="";
        // Build a command for installing a given library.
        //String command = "sudo yum install " + fileDetail.getFileName();
        //String command = "cat " + uploadedFileLocation;
      	String command = "rpm -i " + uploadedFileLocation;
      	//String command2 = "rpm --checksig " + uploadedFileLocation;
      	//String command2 = "rpm -e " + uploadedFileLocation;
      	//String command3 = "rpm -qpR " + uploadedFileLocation;
      	//String command4 = "rpm -qa | grep -i"
      	//String command = "more " + Constants.SAMPLE_MODULE_LIST_PATH;
        // Construct a command string.
        //String[] commandAndArgs = new String[]{ "/bin/sh", "-c", command};
        // Get Runtime instance.
		Runtime rt = Runtime.getRuntime();
		// Declare a process.
	    Process p0 = null;
		try {
			// Let's execute the command.
			//p0 = rt.exec(commandAndArgs);
// RPM installation 
System.out.println(command);
			p0 = rt.exec(command);
			// Get any input stream.
		    InputStream instd = p0.getInputStream();
		    // Let's get it through buffered reader.
		    BufferedReader buf_reader = new BufferedReader(new InputStreamReader(instd));
		    String temp="";
		    //System.out.println("new line executed command: " + command);
	    	while ((temp = buf_reader.readLine()) != null) {  
			   //System.out.println("temp: " + temp);
	    		normalOutput += temp +"\n";
			}
	    	// Let's close buffered reader
	    	buf_reader.close();
	    	
	    	 // Get any error stream.
		    InputStream errstd = p0.getErrorStream();
		    // Let's get it through buffered reader.
		    BufferedReader buf_err_reader = new BufferedReader(new InputStreamReader(errstd));
		    // Initialize a temporary variable.
		    temp = "";
		   	// Until there's no more error message,
			while ((temp = buf_err_reader.readLine()) != null) {  
				// Append a current error message to the error message container.
				errorOutput += temp+"\n";
			}
			// Report an error.
		    _logger.reportErrorNotOnConsole(errorOutput);
		    // Close buffered error reader.
		    buf_err_reader.close();
			// Let's wait p0 for completion.
			p0.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		// If there's no error, then send the success message.
		if(errorOutput.equalsIgnoreCase("")){
			return Response.status(200).entity("<html>Successful Installation of '"+fileDetail.getFileName()+"' !" + normalOutput +"</html>").build();
		}
		// If there's any error, 
		else{
			// then copy the error output and notify the user about it.
			return Response.status(200).entity("<html>Installation Error Occurred: " + errorOutput+ "</html>").build();
		}
    }
 
    // save uploaded file to new location
    private void saveToFile(InputStream uploadedInputStream,
    						String uploadedFileLocation) {
        try {
        	// Declare an output stream. 
            OutputStream out = null;
            // number of bytes read
            int read = 0;
            // 1024-byte buffer
            byte[] bytes = new byte[1024];
            // Assign file output stream.
            out = new FileOutputStream(new File(uploadedFileLocation));
            // Read the file until no more bytes are read.
            while ((read = uploadedInputStream.read(bytes)) != -1) {
            	// Write these bytes. 
                out.write(bytes, 0, read);
            }
            // Flush it.
            out.flush();
            // Close it.
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
	
	// invoking URL:
	// http://localhost:8080/ScienceAppEngineWS/rest/SAEServ/library/search
	// @GET here defines, this method will method will process HTTP GET
	// requests.
	@GET
	// @Path here defines method level path. Identifies the URI path that a
	// resource class method will serve requests for.
	@Path("/library/search")
	// @Produces here defines the media type(s) that the methods
	// of a resource class can produce.
	@Produces(MediaType.TEXT_XML)
	// @PathParam injects the value of URI parameter that defined in @Path
	// expression, into the method.
	public String retrieveLibrary() {
		// Initialize the library list vector.
		libList = new Vector<Library>();
		//System.out.println("The installed library list has been requested!");
		// The following two lines should be replaced by the third line when in service.
		String lib_path = Constants.SAMPLE_LIBRARY_LIST_PATH;
		String command = "cat " + lib_path;
		// Actual command to retrieve all the library list
		// String command = "ldconfig -p";
		try {
			// Create an instance of Runtime.
			Runtime rt = Runtime.getRuntime();
			// Execute the command. 
			Process p1 = rt.exec(command);
			// Get input stream from the command execution.
			InputStream instd = p1.getInputStream();
			// Let's make a buffered reader construct.
			BufferedReader buf_reader = new BufferedReader(
					new InputStreamReader(instd));
			// Declare a line variable.
			String line = "";
			// Declare a line count variable.
			int lineCnt = 0;
			// Now let's read each line from the given inputstream.
			while ((line = buf_reader.readLine()) != null) {
				// Increment the current line count.
				lineCnt++;
				// The first line will be something like:
				// 988 libs found in cache `/etc/ld.so.cache'
				if (lineCnt == 1){
					// So we'll exclude this line for parsing.
					continue;
				}
				// Let's see what's printed out on the terminal.
				// System.out.println(lineCnt+": " + str);
				// library name
				String libName = "";
				// linux c libray version
				String linuxCLibVer = "";
				// system architecture
				String sysArch = "";
				// kernel version
				String kernelVer = "";
				// library path
				String libPath = "";
				// token index
				int tokenIdx = 0;
				// Let's use string tokenizer
				StringTokenizer st = new StringTokenizer(line, "=>|\\(|\\)");
				// Iterate until we consume all tokens
				while (st.hasMoreTokens()) {
					String token = (st.nextToken()).trim();
					if (token.equalsIgnoreCase(""))
						continue;
					tokenIdx++;
					// System.out.println(token);
					if (tokenIdx == 1) {
						libName = token;
					}
					// i686 = 32-bit Intel x86 arch
					// x86_64 = 64-bit Intel x86 arch
					else if (tokenIdx == 2) {
						String[] result = token.split(",");
						int subTokenIdx = 0;
						for (int x = 0; x < result.length; x++) {
							String temp = (result[x]).trim();
							if (temp.equalsIgnoreCase("")) {
								continue;
							}
							subTokenIdx++;
							if (subTokenIdx == 1) {
								linuxCLibVer = temp;
								sysArch = "x86";
							} else if (subTokenIdx == 2) {
								if (temp.startsWith("x")) {
									sysArch = temp;
									// libm.so.6 (libc6, hwcap:
									// 0x0018000000000000, OS ABI: Linux 2.6.9)
									// => /lib/i686/nosegneg/libm.so.6
								} else if (temp.startsWith("h")) {
									sysArch = "i686";
								} else {
									sysArch = "x86";
									temp = (temp
											.replaceAll("OS ABI: Linux", ""))
											.trim();
									kernelVer = temp;
								}
							} else if (subTokenIdx == 3) {
								temp = (temp.replaceAll("OS ABI: Linux", ""))
										.trim();
								kernelVer = temp;
							}
						}
					} else if (tokenIdx == 3) {
						libPath = token;
					}
				}
//				System.out.println(libName + "|" + linuxCLibVer + "|" + sysArch
//						+ "|" + kernelVer + "|" + libPath);
				// Create a library instance using the parsed data
				Library lib = new Library(libName, linuxCLibVer, sysArch,
						kernelVer, libPath);
				// Let's add this instance to the final list.
				libList.add(lib);
			}
			// close this buffered reader instance.
			buf_reader.close();
			// Let's wait for the process until it's done.
			p1.waitFor();
		} catch (Exception ex) {
			// ex.printStackTrace();
			// Print an error message if any.
			System.err.println(ex.getMessage());
		}

		/*** Return installed libraries ***/
		StringBuffer resList = new StringBuffer();
		resList.append("<LibraryList>\n");
		// Iterate each library data.
		for (int i = 0; i < libList.size(); i++) {
			// Convert the library instance data into the corresponding XML document.
			resList.append((libList.get(i)).toStringInXML() + "\n");
		}
		resList.append("</LibraryList>\n");
		// Let's print out the final string.
		String resFinalStr = resList.toString();
		//System.out.println(resFinalStr);
		return resFinalStr;
	}
	
//	// @GET here defines, this method will method will process HTTP GET
//	// requests.
//	@GET
//	// @Path here defines method level path. Identifies the URI path that a
//	// resource class method will serve requests for.
//	@Path("/helloworld/{j}")
//	@Produces(MediaType.TEXT_HTML)
//	public String printHelloWorld(@PathParam("j") String j) {
//		return "<html>" + j + ": Hello World!</html>";
//	}

//	 // @GET here defines, this method will method will process HTTP GET
//	 // requests.
//	 @GET
//	 // @Path here defines method level path. Identifies the URI path that a
//	 // resource class method will serve requests for.
//	 @Path("/name/{i}")
//	 // @Produces here defines the media type(s) that the methods
//	 // of a resource class can produce.
//	 @Produces(MediaType.TEXT_XML)
//	 // @PathParam injects the value of URI parameter that defined in @Path
//	 // expression, into the method.
//	 public String userName(@PathParam("i") String i) {
//	
//	 String name = i;
//	 System.out.println("Hello World!");
//	 return "<User>" + "<Name>" + name + "</Name>" + "</User>";
//	 }
//	
//	 @GET
//	 @Path("/age/{j}")
//	 @Produces(MediaType.TEXT_XML)
//	 public String userAge(@PathParam("j") int j) {
//	
//	 int age = j;
//	 return "<User>" + "<Age>" + age + "</Age>" + "</User>";
//	 }
}