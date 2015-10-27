package com.kisti.scienceappengine.dbaccess;

/**
 * Stores information about a foreign key associated with a table the DBMS.  
 * These objects are used when creating tables. 
 * They allow the user to specify referential integrity constraints.
 * @author Young-K. Suh (yksuh@kisti.re.kr)
 *
 */
public class ForeignKey {
	/**
	 * The columns in this foreign key
	 */
	public String[]		columns;
	/**
	 * The columns that are referenced in the foreign table.
	 */
	public String[]		columnsReferenced;
	/**
	 * The table that is referenced.
	 */
	public String		tableReferenced;
	/***
	 * Cascade option
	 */
	public String		strCascadeOption;
	
	/**
	 * Create a Foreign Key object
	 * @param columnNames The columns of the table that have referential integrity for this foreign key.
	 * @param tableReferenced A referenced foreign table
	 * @param columnsReferenced A set of referenced columns
	 */
	public ForeignKey(String[] cols, 
					  String tableRefs, 
					  String[] columnsRefs, 
					  String cascOption) {
		columns 			= cols;
		tableReferenced 	= tableRefs;
		columnsReferenced 	= columnsRefs;
		strCascadeOption	= cascOption;
	}

	/**
	 * Override equals() appropriate for this object
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (!(o instanceof ForeignKey))
			return false;
		ForeignKey other = (ForeignKey) o;

		// column length check
		if (other.columns.length != this.columns.length)
			return false;
		
		// column type check
		for (int i = 0; i < columns.length; i++)
			if (!columns[i].equals(other.columns[i]))
				return false;

		// referenced table check
		if (!other.tableReferenced.equals(tableReferenced))
			return false;
		
		// referenced column length check
		if (other.columnsReferenced.length != this.columnsReferenced.length)
			return false;
		
		// referenced column check
		for (int i = 0; i < columnsReferenced.length; i++)
			if (!columnsReferenced[i].equals(other.columnsReferenced[i]))
				return false;
		
		// cascade option check
		if (!strCascadeOption.equals(other.strCascadeOption)) {
			return false;
		}
		
		// if there's no difference, then return true
		return true;
	}
}
