package com.kisti.scienceappengine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * <p>
 * The GeneralDBMS abstract class provides an abstraction of representing a
 * central DBMS (MySQL or PostgreSQL, ...) used in the EDISON system. JDBC is
 * the underlying interface used by EDISON to access the DBMS. Operations
 * performed by EDISON through JDBC may vary from DBMS to DBMS. JDBC in general
 * provides a common interface to all DBMSes. However, many operations take a
 * simple String parameter. If the string has to contain DBMS specific commands,
 * then ScienceAppEngine provides an additional layer of indirection to provide
 * a truly DBMS-independent interface. This abstract class must be implemented
 * by each DBMS that EDISON supports.
 * </p>
 */
public abstract class GeneralDBMS {
	/***
	 * EDISON DBMS user name
	 */
	protected static String strUserName = "";
	/***
	 * EDISON DBMS user password
	 */
	protected static String strPassword = "";
	/***
	 * EDISON DBMS connect string
	 */
	protected static String strConnectString = "";
	/***
	 * EDISON DBMS Connection variable
	 */
	protected static Connection _connection;
	/***
	 * EDISON DBMS Statement variable
	 */
	protected static Statement _statement;
	/**
	 * The number data type for a column data type.
	 */
	public static final int I_DATA_TYPE_NUMBER = 0;
	/**
	 * The character data type for a column data type.
	 */
	public static final int I_DATA_TYPE_VARCHAR = 1;
	/**
	 * The clob data type for a column data type.
	 */
	public static final int I_DATA_TYPE_CLOB = 2;
	/**
	 * The date data type for a column data type.
	 */
	public static final int I_DATA_TYPE_DATE = 3;
	/***
	 * The sequence data type for a column data type
	 */
	public static final int I_DATA_TYPE_SEQUENCE = 4;
	/**
	 * The XMLdata type for a column data type.
	 */
	public static final int I_DATA_TYPE_XML = 5;
	/**
	 * The date data type for a column data type.
	 */
	public static final int I_DATA_TYPE_TIMESTAMP = 6;
	/***
	 * For MySQL
	 */
	/**
	 * The "default timestamp" data type for a column data type.
	 */
	public static final int I_DATA_TYPE_TIMESTAMP_DEFAULT_CURRENT = 7;
	/**
	 * The "default timestamp with update" data type with update for a column
	 * data type.
	 */
	public static final int I_DATA_TYPE_TIMESTAMP_UPDATE_CURRENT = 8;

	// public static final String XMLTYPE = "XMLType";
	// public static final String CLOB = "CLOB";
	// public static final String NUMBER = "NUMBER";
	// public static final String VARCHAR = "VARCHAR2";
	// public static final String DATE = "DATE";
	// public static final String TIMESTAMP = "TIMESTAMP";

	/****
	 * Set user name for this DBMS
	 */
	public void setUsername(String userName) {
		strUserName = userName;
	}

	/****
	 * Set password for this DBMS
	 */
	public void setPassword(String password) {
		strPassword = password;
	}

	/****
	 * Set password for this DBMS
	 */
	public void setConnectString(String connectString) {
		strConnectString = connectString;
	}

	/**
	 * Get DBMS driver class name.
	 * 
	 * @return DBMS driver class name.
	 */
	public abstract String getDBMSDriverClassName();

	/**
	 * Given integer representation of a data type, this produces a
	 * DBMS-specific representation of the data type.
	 * 
	 * @param dataType
	 *            The data type
	 * @param length
	 *            The number of digits for this value.
	 * @return A string representation of this data type/length. This value can
	 *         be used in a CREATE table statement.
	 */
	protected abstract String getDataTypeAsString(int dataType, int length);

	/**
	 * This constructor specifies the connection string to the JDBC driver
	 * 
	 * @param userName
	 *            A user name for this database's account that stores the tables
	 *            of EDISON.
	 * @param password
	 *            The password for login into the DBMS.
	 * @param connectionString
	 *            The connectionString contains the drivers, port, IP, and other
	 *            information to connect using JDBC. See DBMS-specific
	 *            documentation to refer to what information should be included
	 *            here.
	 */
	public GeneralDBMS(String userName, String password, String connectionString) {
		strUserName = userName;
		strPassword = password;
		strConnectString = connectionString;
	}

	/***
	 * Constructor taking one parameter
	 * 
	 * @param connectionString
	 *            DBMS connection string
	 */
	public GeneralDBMS(String connectionString) {
		strConnectString = connectionString;
	}

	/**
	 * Closes the DBMS connection that was opened by the open call.
	 */
	public void close() {
		try {
			// If connection is still open,
			if (_connection != null)
				_connection.commit(); // then, first commit.
			// If statement is still open,
			if (_statement != null)
				_statement.close(); // then, first close.
			// If connection isn't closed,
			if (_connection != null)
				_connection.close(); // then, let's close it.
		} catch (SQLException e) {
			// If error occurs, then print out all the error message.
			e.printStackTrace();
		}
		// Initialize this conneciton to null.
		_connection = null;
	}

	/**
	 * Make a commit to all accumulated update operations done to this DBMS.
	 * This must be called for INSERT statements to be exposed.
	 */
	public void commit() {
		try {
			// If connection is not null and still open,
			if (_connection != null && !_connection.isClosed())
				_connection.commit(); // then, let's commit first.
		} catch (SQLException e) {
			// If error occurs, then print out error message.
			e.printStackTrace();
		}
	}

	/****
	 * Make a connection to the DBMS.
	 * 
	 * @param auto_commit
	 *            flag of whether to turn on auto commit function
	 * @throws DBMSInvalidConnectionParameterException
	 */
	public void open(boolean auto_commit) {
		boolean isOpened = false;

		int numTryCounts = 10;
		int count = 1;
		int waitTime = 1000; // ms
		do {
			try {
				String strdrvname = getDBMSDriverClassName();
				System.out.println("login details: " + strConnectString + ", "
						+ strUserName + ", " + strPassword + ", " + strdrvname);
				Class.forName(strdrvname);
				_connection = DriverManager.getConnection(strConnectString,
						strUserName, strPassword);
				// turn off auto-commit. If this is turned on there will be a
				// huge performance hit for inserting tuples
				// into the DBMS.
				_connection.setAutoCommit(auto_commit);
				_statement = _connection
						.createStatement(ResultSet.TYPE_FORWARD_ONLY,
								ResultSet.CONCUR_UPDATABLE);
				isOpened = true;
				System.out.println("DB is successfully opened.");
				return;
			} catch (SQLException sqlex) {
				// sqlex.printStackTrace();
				isOpened = false;
				// Main._logger.outputLog("login details: " + strConnectString +
				// ", " + strUserName + ", " + strPassword);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.exit(1); // programemer/dbsm error
			}
			count++;
			waitTime *= 2;
			// wait 2^(count) seconds
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				continue;
			}
		} while (count <= numTryCounts && !isOpened);

		if (!isOpened) {
			ScienceAppEngine._logger
					.reportError("Failed to fetch a result from AZDBLAB due to network failure");
			try {
				throw new SQLException();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Used to determine whether a table exists in the DBMS.
	 * 
	 * @param tableName
	 *            The name of the table that is tested for existence.
	 * @return true - if the table exists.<BR>
	 *         false - if the table does not exist.
	 */
	public boolean tableExists(String tableName) {
		try {
			// attempts to create the table. If it fails, the table exists and
			// an exception will be thrown.
			_statement.executeUpdate("CREATE TABLE " + tableName
					+ " (Name char(1))");
			commit();
			// if the table was created, drop it again.
			_statement.executeUpdate("DROP TABLE " + tableName);
			commit();
			return false;
		} catch (SQLException e) {
			String errMsg = (e.getMessage()).toLowerCase();
			if (!(errMsg.contains("already") || errMsg.contains("exist") || errMsg
					.contains("creat"))) { // this is not a real error!
				ScienceAppEngine._logger.reportError(e.getMessage());
			}
			// try {
			if (_statement == null) {
				// || _statement.isClosed()){
				reset();
			} else {
				commit();
			}
			return true;
		}
	}

	/****
	 * Disconnect the current connection. Re-try to make a new connection. This
	 * could occur when the EDISON DBMS is full of jobs in queue.
	 */
	public void reset() {
		// First, close.
		close();
		// Second, open with auto-committ off.
		open(false);
	}

	/**
	 * Creates a DBMS table named tableName with the characteristics described
	 * by the parameters. All parameters that are arrays must be the same
	 * length. If they are not the same length an AZDBLab will terminate.
	 * 
	 * @param tableName
	 *            The name of the table that will be created.
	 * @param columnNames
	 *            The names of the columns that belong to the table.
	 * @param columnDataTypes
	 *            The data types of the columns that belong to the table.
	 * @param columnDataTypeLengths
	 *            The number of characters/digits that each column will use.
	 * @param primaryKey
	 *            The columns that will be part of the primary key.
	 * @param foreignKeys
	 *            The foreign keys for this table.
	 * @param Table_Record_Table
	 *            The table used to keep track of all relevant tables in the
	 *            AZDBLab
	 */
	public void createTable(String tableName, // table name
			String[] columnNames,// column names
			int[] columnDataTypes, // column data type
			int[] columnDataTypeLengths, // column data type lengths
			int[] autoIncrCols, // auto increment columns
			int[] nullableCols, // nullable columns
			String[] uniqueCols, // unique columns
			String[] primaryKey, // primary key columns
			ForeignKey[] foreignKeys // foreign key columns
	) {
		// check if a table exists
		if (tableExists(tableName)) {
			return;
		}
		// If all arrays are not the same length exit
		if ((columnNames.length != columnDataTypes.length)
				|| (columnDataTypes.length != columnDataTypeLengths.length)) {
			ScienceAppEngine._logger
					.outputLog("createTable: Parameter Arrays must have same length");
			System.exit(1); // programmer bug, should be able to create a table
		}
		// assemble the CREATE TABLE statement
		String createTable = "CREATE TABLE " + tableName + " ( ";
		for (int i = 0; i < columnNames.length; i++) {
			boolean isAutoIncrementable = true;
			if (autoIncrCols != null) {
				if (autoIncrCols[i] == 0) {
					isAutoIncrementable = false;
				}
			}

			boolean isNullable = true;
			String strCol = columnNames[i];
			if (nullableCols != null) {
				if (nullableCols[i] == 0) {
					isNullable = false;
				}
			}

			createTable += strCol
					+ " "
					+ getDataTypeAsString(columnDataTypes[i],
							columnDataTypeLengths[i]);
			if (isAutoIncrementable)
				createTable += " AUTO_INCREMENT";

			if (!isNullable)
				createTable += " NOT NULL";

			if (i == columnNames.length - 1) {
				break;
			}
			createTable += ", ";
		}

		// creating the primary key SQL
		if (primaryKey != null) {
			createTable += ", PRIMARY KEY(";
			for (int i = 0; i < primaryKey.length; i++) {
				createTable += primaryKey[i];
				if (i == primaryKey.length - 1) {
					break;
				}
				createTable += ", ";
			}
			createTable += ")";
		}

		// unique columns
		if (uniqueCols != null) {
			createTable += ", UNIQUE(";
			for (int i = 0; i < uniqueCols.length; i++) {
				createTable += uniqueCols[i];
				if (i == uniqueCols.length - 1) {
					break;
				}
				createTable += ", ";
			}
			createTable += ")";
		}

		// creating the Foreign Key SQL
		if (foreignKeys != null) {
			for (int i = 0; i < foreignKeys.length; i++) {
				createTable += ", FOREIGN KEY(";
				if (foreignKeys[i].columns.length != foreignKeys[i].columnsReferenced.length) {
					ScienceAppEngine._logger
							.reportError("The two arrays in a Foreign Key Object must be the same length");
					System.exit(1);
				}
				for (int j = 0; j < foreignKeys[i].columns.length; j++) {
					createTable += foreignKeys[i].columns[j];
					if (j == foreignKeys[i].columns.length - 1) {
						break;
					}
					createTable += ", ";
				}
				createTable += ") REFERENCES " + foreignKeys[i].tableReferenced
						+ " (";
				for (int j = 0; j < foreignKeys[i].columnsReferenced.length; j++) {
					createTable += foreignKeys[i].columnsReferenced[j];
					if (j == foreignKeys[i].columnsReferenced.length - 1) {
						break;
					}
					createTable += ", ";
				}
				createTable += ")";
				if (foreignKeys[i].strCascadeOption != null) {
					createTable += foreignKeys[i].strCascadeOption;
				}
			}
		}
		createTable += ")";
//		ScienceAppEngine._logger.outputLog("Creating Table: " + tableName);
		System.out.println("Creating Table: " + tableName);

		// Executing the SQL to create the table
		try {
			ScienceAppEngine._logger.outputLog("sql to create table: "
					+ createTable);
			System.out.println("sql to create table: " + createTable);
			_statement.executeUpdate(createTable);
			commit();
		} catch (SQLException e) {
//			ScienceAppEngine._logger.reportError(createTable);
			System.out.println(createTable);
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Drops a given table <code>tableName</code> from a database.
	 * 
	 * @param tableName
	 *            The name of the table that will be dropped from a given database.
	 */
	public void dropTable(String tableName) {
		//ScienceAppEngine._logger.outputLog("Dropping Table: " + tableName);
		System.out.println("Dropping Table: " + tableName);
		try {
			// drop the table from the DBMS.
			_statement.executeUpdate("DROP TABLE " + tableName);
			// let's do a commit.
			commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//ScienceAppEngine._logger.outputLog(tableName + " Dropped.");
		System.out.println(tableName + " Dropped.");
	}

	/**
	 * The method transforms the parameters into a valid SQL statement that
	 * performs an insert statement
	 * 
	 * @param tableName
	 *            The name of the table.
	 * @param columnNames
	 *            The name of the columns for the row to be inserted.
	 * @param columnValues
	 *            The values to be inserted.
	 * @param columnDataTypes
	 *            The data types of the columns
	 * @return An SQL string that will insert the specified tuple into the
	 *         database.
	 */
	protected String buildInsertSQL(String tableName, String[] columnNames,
			String[] columnValues, int[] columnDataTypes) {
		if ((columnNames.length != columnValues.length)
				|| (columnValues.length != columnDataTypes.length)) {
			ScienceAppEngine._logger
					.reportError("build insertRow: Parameter arrays must be same length");
			System.exit(1); // this is a programmers bug
		}
		// assembling the INSERT SQL statement
		String insertSQL = "INSERT INTO " + tableName + "(";
		for (int i = 0; i < columnNames.length; i++) {
			insertSQL += columnNames[i];
			if (i == columnNames.length - 1) {
				break;
			}
			insertSQL += ", ";
		}
		insertSQL += ") VALUES (";
		for (int i = 0; i < columnValues.length; i++) {
			insertSQL += formatColumnValue(columnValues[i], columnDataTypes[i]);
			if (i == columnNames.length - 1) {
				break;
			}
			insertSQL += ", ";
		}
		insertSQL += ")";
		System.out.println(insertSQL);
		
		try{
			_statement.executeUpdate(insertSQL);
			commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return insertSQL;
	}

	/**
	 * Augments a value with appropriate extra syntax.
	 * 
	 * @param value
	 *            The column value
	 * @param dataType
	 *            The data type of the column value.
	 * @return The augmented value.
	 */
	protected String formatColumnValue(String value, int dataType) {
		if (dataType == GeneralDBMS.I_DATA_TYPE_VARCHAR) {
			return "'" + value + "'";
		} else if (dataType == GeneralDBMS.I_DATA_TYPE_DATE) {
			return "to_date('" + value + "', '" + Constants.DATEFORMAT + "')";
		} else if (dataType == GeneralDBMS.I_DATA_TYPE_TIMESTAMP) {
			return "to_timestamp('" + value + "', '"
					+ Constants.TIMESTAMPFORMAT + "')";
		} else if (dataType == GeneralDBMS.I_DATA_TYPE_CLOB) {
			return "empty_clob()";
		} else {
			return value;
		}
	}

	/****
	 * Execute a given query and then receives its resultset.
	 * 
	 * @param sql
	 *            a query to get executed
	 * @return a result set
	 */
	protected ResultSet executeQuery(String sql) {
		// check if sql is null
		if (sql == null || sql.equalsIgnoreCase("")) {
			return null;
		}

		// initialize
		ResultSet rs = null;
		try {
			rs = _statement.executeQuery(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// return result set
		return rs;
	}

//	 public void createSequence(String seqName) {
//		 String createSequence = "CREATE SEQUENCE " + seqName
//		 + " START WITH 1 NOMAXVALUE";
//		 try {
//		 ScienceAppEngine._logger.outputLog("Creating sequence: " + seqName);
//		 	_statement.execute(createSequence);
//		 } catch (SQLException e) {
//			 e.printStackTrace();
//		 }
//	 }
//	
//	 public void dropSequence(String seqName) {
//		 String dropSequence = "DROP SEQUENCE " + seqName;
//		 try {
//			 ScienceAppEngine._logger.outputLog("Dropping sequence: " + seqName);
//			 _statement.execute(dropSequence);
//		 } catch (SQLException e) {
//			e.printStackTrace();
//		 }
//	 }
//	
//	 public int getSequencialID(String seqName) {
//		 String getSeqID = "SELECT " + seqName + ".NEXTVAL FROM DUAL";
//		 // System.out.println(getSeqID);
//		 try {
//			 ResultSet rs = _statement.executeQuery(getSeqID);
//			 int id = -1;
//			 if (rs.next()) {
//				 id = rs.getInt(1);
//			 }
//			 rs.close();
//			 return id;
//		 } catch (SQLException e) {
//			 e.printStackTrace();
//			 return -1;
//		 }
//	 }
}