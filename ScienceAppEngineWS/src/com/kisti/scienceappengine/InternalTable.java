package com.kisti.scienceappengine;

public class InternalTable {
	/**
	 * Creates an InternalTable object.  
	 * The information here is used to create a DBMS table used in the EDISON platform
	 * @param TableName The name of the table to be created.
	 * @param columns The names of the columns.
	 * @param columnDataTypes The data types of the columns.  Uses the MetaData found in AZDBLAB.java.
	 * @param columnDataTypeLengths The lengths of the data types for each column above.
	 * @param primaryKey The names of the columns in the primary key.
	 * @param foreignKey An array of Foreign keys for this table.
	 */
	public InternalTable(	String 			TableName,
							String[] 		columns,
							int[] 			columnDataTypes,
							int[] 			columnDataTypeLengths,
							int[]			autoIncrColumns,
							int[]			columnNullable,
							String[]		uniqueConstraintColumns,
							String[] 		primaryKey,
							ForeignKey[] 	foreignKey,
							String			sequence) {
		this.TableName 					= TableName;
		this.columnNames 				= columns;
		this.columnDataTypes 			= columnDataTypes;
		this.columnDataTypeLengths 		= columnDataTypeLengths;
		this.autoIncrementColumns		= autoIncrColumns;
		this.columnNullable 			= columnNullable;
		this.uniqueConstraintColumns	= uniqueConstraintColumns;
		this.primaryKey 				= primaryKey;
		this.foreignKey 				= foreignKey;
		this.strSequenceName			= sequence;
	}
	/**
	 * The data type lengths for the columns of the tables.
	 */
	public int[] columnDataTypeLengths;
	/**
	 * The data types of each column.
	 * @see Constants
	 */
	public int[] columnDataTypes;
	/***
	 * Nullable columns
	 */
	public int[] columnNullable;
	/**
	 * The names of each column
	 */
	public String[] columnNames;
	/***
	 * Unique constraint columns
	 */
	public String[] uniqueConstraintColumns;
	/***
	 * Auto increment columns
	 */
	public int[] autoIncrementColumns;
	/**
	 * The foreign keys for the table
	 * @see ForeignKey
	 */
	public ForeignKey[] foreignKey;
	/**
	 * The name of the columns in the primary key.
	 */
	public String[] primaryKey;
	/**
	 * The name of the table.
	 */
	public String TableName;
	/***
	 * The name of sequence name
	 */
	public String strSequenceName;
}