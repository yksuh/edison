/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.kisti.scienceappstore.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import com.kisti.scienceappengine.Constants;
import com.kisti.scienceappstore.model.CommonModule;
import com.kisti.scienceappstore.model.impl.CommonModuleImpl;
import com.kisti.scienceappstore.service.base.CommonModuleServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the common module remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.kisti.scienceappstore.service.CommonModuleService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author yksuh
 * @see com.kisti.scienceappstore.service.base.CommonModuleServiceBaseImpl
 * @see com.kisti.scienceappstore.service.CommonModuleServiceUtil
 */
public class CommonModuleServiceImpl extends CommonModuleServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.kisti.scienceappstore.service.CommonModuleServiceUtil} to access the
	 * common module remote service.
	 */
	public List<CommonModule> retrieveAllModules() {
		// The next two lines should be replaced by the next third line when
		// being in service.
		String mod_list_path = Constants.SAMPLE_MODULE_LIST_PATH;
		String command = "";
		if (!Constants.DEBUG_MODE) {
			command = "module list";
		} else {
			command = "cat " + mod_list_path;
		}

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
				if (lineCnt == 1) {
					// the first line will be either of the following two:
					// a) "No Modulefiles Currently Loaded."
					// b) "Currently Loaded Modulefiles:"
					// So we'll not parse this line but instead skip it.
					continue;
				}
				// Print out each line for a check
				// System.out.println(lineCnt+": " + line);
				// Declare a temp. var. for storing a module name.
				String modName = "";
				// Declare a token index
				int tokenIdx = 0;
				// Use tokenizer to parse a line.
				// An example of a line to be parsed:
				// 1) intel/intel_11 3) gamess/gamess 5) mpi/gnu/openmpi-1.6.4
				// 7) module-git 9) modules
				// 2) mpi/gnu/openmpi-1.6.5 4) mpi/intel/mpich-1.2.7p1 6) dot 8)
				// module-info 10) use.own .
				StringTokenizer st = new StringTokenizer(line, ")|\t| ");
				// Let's use a separator like ')'.
				while (st.hasMoreTokens()) {
					// Get a parsed token
					String token = (st.nextToken()).trim();
					if (token.equalsIgnoreCase(""))
						continue;
					// Increment token index.
					tokenIdx++;
					// Check the token string.
					// System.out.println(token);
					// If a token index is odd,
					if (tokenIdx % 2 == 1) {
						// then it will be module number such as 1, 2, ..., and
						// 10.
						// So we should skip this.
						continue;
					} else { // Otherwise, it will be module name.
						modName = token;
						// Let's print out the name of the parsed module.
						// System.out.println("module name: "+modName);
						// Create an instance of Module.
						CommonModuleImpl mod = new CommonModuleImpl();
						mod.setModuleName(modName);
						mod.setModuleRootDir("");
						// mod.setM(modName);
						mod.persist();
						// System.out.println(modName);
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

		List<CommonModule> resList = new ArrayList<CommonModule>();
		try {
			resList = this.commonModulePersistence.findAll();
			System.out.println("The installed module list has been returned!");
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resList;
	}

	/****
	 * Retrieve modules by keyword
	 * 
	 * @param mod_name
	 *            module name
	 * @return a list of modules found
	 */
	public List<CommonModule> retrieveModule(String mod_name) {
		// declare a vector of modules listed by the command.
		List<CommonModule> foundModList = new Vector<CommonModule>();
		if (mod_name == null) {
			return foundModList;
		}

		// We internally invoke retrieveAllModules() and
		// then search for a library(ies) to contain a given module name
		// (modname)
		List<CommonModule> allList = retrieveAllModules();

		// If this list has some modules,
		if (allList.size() > 0) {
			// then let's iterate each element and add it to the list.
			for (int i = 0; i < allList.size(); i++) {
				// Let's get an indexed library item.
				CommonModule mod = allList.get(i);
				String modName = mod.getModuleName();
				// If this module matches the given mod_name,
				if (modName.contains(mod_name)) {
					// then let's add this module.
					foundModList.add(mod);
				}
			}
		}
		return foundModList;
	}

	/****
	 * Retrieve available modules
	 * 
	 * @return a list of modules available
	 */
	public List<CommonModule> retrieveAvailModules() {
		// declare a vector of modules listed by the command.
		List<CommonModule> availModList = new Vector<CommonModule>();
		// The next two lines should be replaced by the next third line when
		// being in service.
		String mod_list_path = Constants.SAMPLE_MODULE_AVAIL_PATH;
		String command = "";
		if (!Constants.DEBUG_MODE) {
			command = "module avail";
		} else {
			command = "cat " + mod_list_path;
		}
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
			// System.out.println(lineCnt+": " + line);
			// Declare a temp. var. for storing a module name.
			String modName = "";
			// Declare a temp. var. for storing a module path.
			String modPath = "";
			// Declare a temp. var. for indicating whether to have a root
			// directory for a module.
			boolean isNewModuleRootPath = false;
			// Begin to read each line from given output (or given file).
			while ((line = buf_reader.readLine()) != null) {
				// Trim this line.
				line = line.trim();
				// If there's nothing then skip this line.
				if (line.equalsIgnoreCase(""))
					continue;
				// Increment line count.
				lineCnt++;
				// If line count is one, then
				if (lineCnt % 2 == 1) {
					// Use tokenizer to parse a line.
					// An example of a line to be parsed:
					// ------------------------------------------------------------------------------------
					// /usr/share/Modules/modulefiles --------
					// , and so on.
					StringTokenizer st = new StringTokenizer(line, "-|\t| ");
					// Let's use a separator like ')'.
					while (st.hasMoreTokens()) {
						// Get a parsed token
						String token = (st.nextToken()).trim();
						// If this token doesn't have anything, then skip it.
						if (token.equalsIgnoreCase(""))
							continue;
						// Check if the current root path is not null, and token
						// starts with a directory name
						if (!isNewModuleRootPath && token.startsWith("/")) {
							// This will be a module parent path.
							modPath = token;
							// Set the flag to indicate we're in this root path.
							isNewModuleRootPath = true;
							// Let' get out this loop.
							break;
						}
					}
				} else {
					// Use tokenizer to parse a line.
					// An example of a line to be parsed:
					// dot module-git module-info modules null use.own
					StringTokenizer st = new StringTokenizer(line, "\t| ");
					// Let's use a separator like ')'.
					while (st.hasMoreTokens()) {
						// Get a parsed token
						String token = (st.nextToken()).trim();
						if (token.equalsIgnoreCase(""))
							continue;
						// Get the module name
						modName = token;
						// If the module name is equal to 'null', then we skip
						// it.
						if (modName.equalsIgnoreCase("null"))
							continue;
						// Let's print out the name of the parsed module.
						// System.out.println("module name: "+modName +
						// ", path: " + modPath);
						// Create an instance of Module.
						CommonModuleImpl mod = new CommonModuleImpl();
						mod.setModuleName(modName);
						mod.setModuleRootDir(modPath);
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
		return availModList;
	}

	/****
	 * Loading a specific module
	 * @param mod_name module name to be loaded
	 * @return result message
	 */
	public String loadModule(String mod_name) {
System.out.println("Let's install '" + mod_name + "'");
		String res = "";
System.out.println("We start to install module '" + mod_name + "'");
		if (Constants.DEBUG_MODE) {
			return "Loading '" + mod_name + "' Succeeded!";
		} else {
			try {
				String command = "module load " + mod_name;
System.out.println("command: " + command);
				// Construct a command and its arguments
				String[] commandAndArgs = new String[] { "/bin/sh", "-c",
						command };
				Runtime rt = Runtime.getRuntime();
				Process p0 = rt.exec(commandAndArgs);
				InputStream instd = p0.getInputStream();
				BufferedReader buf_reader = new BufferedReader(
						new InputStreamReader(instd));
				String temp = null, tmp_data = "";
				while ((temp = buf_reader.readLine()) != null) {
					tmp_data += temp;
				}
				if (tmp_data.equalsIgnoreCase("")) {
					res = "Loading '" + mod_name + "' Succeeded!";
				}
System.out.println(tmp_data);
				buf_reader.close();
				InputStream errstd = p0.getErrorStream();
				BufferedReader buf_err_reader = new BufferedReader(
						new InputStreamReader(errstd));
				String tmp_err_data = "";
				temp = null;
				while ((temp = buf_err_reader.readLine()) != null) {
					tmp_err_data += temp;
				}
				if (!tmp_err_data.equalsIgnoreCase("")) {
					res = "Loading '" + mod_name + "' Failed, due to "
							+ tmp_data + "";
				}
System.err.println(tmp_err_data);
				buf_err_reader.close();
				p0.waitFor();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			System.out.println(res);
			return res;
		}
	}
}