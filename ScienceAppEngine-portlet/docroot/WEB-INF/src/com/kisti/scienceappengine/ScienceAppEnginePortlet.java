package com.kisti.scienceappengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.kisti.scienceappstore.model.CommonLibrary;
import com.kisti.scienceappstore.model.CommonModule;
import com.kisti.scienceappstore.service.CommonLibraryServiceUtil;
import com.kisti.scienceappstore.service.CommonModuleServiceUtil;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class NewPortlet
 */
public class ScienceAppEnginePortlet extends MVCPortlet {

//	@Override
//	public void doView(RenderRequest renderRequest,
//			RenderResponse renderResponse) throws IOException, PortletException {
//		// TODO Auto-generated method stub
//		
//		//System.out.println("Control Received..");
//		List<CommonLibrary> clList = CommonLibraryLocalServiceUtil.retrieveAllLibraries();
////		System.out.println("lib name: " + libName);
//		
////		HashMap<String, String> libMap = new HashMap<String, String>();
////		for(int i=0;i<clList.size();i++){
////			CommonLibrary cl = clList.get(i);
////			String key = cl.getLibName()+Constants.libKeySeparator+cl.getKernelVer();
////			String val = cl.getLibPath();
////			libMap.put(key, val);
////System.out.println(key+"|"+val);
////		}
////		renderRequest.setAttribute("libMap", libMap);
//		renderRequest.setAttribute("libList", clList);
//		super.doView(renderRequest, renderResponse);
//	}

//	@Override
//    public void processAction(
//            ActionRequest actionRequest, ActionResponse actionResponse)
//        throws IOException, PortletException {
//		String reqLibName = actionRequest.getParameter("reqLibName");
//		System.out.println("action request - received name: " + reqLibName);
//		List<CommonLibrary> clList = CommonLibraryLocalServiceUtil.retrieveLibrary(reqLibName);
//		actionRequest.setAttribute("foundLibList", clList);
//		//actionResponse.setRenderParameter("jspPage", "/html/scienceappengine/lib_result.jsp");
//		super.processAction(actionRequest, actionResponse);
//    } 

//	public String toPlainString(List<CommonLibrary> clList) {
//		StringBuffer sb = new StringBuffer();
//
//		if(clList != null){
//			for(int i=0;i<clList.size();i++){
//				CommonLibrary cl = (CommonLibrary)clList.get(i);
//				sb.append(cl.getLibName()+Constants.LIB_STR_SEPARATOR);
//				sb.append(cl.getCLibVer()+Constants.LIB_STR_SEPARATOR);
//				sb.append(cl.getSysArch()+Constants.LIB_STR_SEPARATOR);
//				String kernelVer = cl.getKernelVer();
//				if(kernelVer.equalsIgnoreCase("")){
//					sb.append(" "+Constants.LIB_STR_SEPARATOR);
//				}else{
//					sb.append(kernelVer+Constants.LIB_STR_SEPARATOR);
//				}
//				
//				sb.append(cl.getLibPath()+"\n");
//			}
//		}
//		String finalStr = sb.toString();
//System.out.println(finalStr);
//		return finalStr;
//	}
	
	/****
	 * Register a metadata of an installed package
	 * @param actionRequest
	 * @param actionResponse
	 * @return installation message
	 */
	public void registerPkgAction(ActionRequest actionRequest,
			             		  ActionResponse actionResponse) throws PortletException, IOException{
//		List<CommonLibrary> clList = CommonLibraryServiceUtil.retrieveAllLibraries();
//		actionRequest.setAttribute("foundLibList", clList);
		String jspFileName = "/html/scienceappengine/register_pkg.jsp";
		actionResponse.setRenderParameter("jspPage", jspFileName);
	}
	
	/****
	 * Retrieve all libraries installed
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 */
	public void retrieveAllLibAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException {
		List<CommonLibrary> clList = CommonLibraryServiceUtil.retrieveAllLibraries();
		actionRequest.setAttribute("foundLibList", clList);
		String jspFileName = "/html/scienceappengine/lib_result.jsp";
		actionResponse.setRenderParameter("jspPage", jspFileName);
    } 
	
	/****
	 * Retrieve libraries matching library name keyword
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 */
	public void retrieveLibAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException {
//		String reqLibName = actionRequest.getParameter("reqLibName");
		String reqLibName = ParamUtil.getString(actionRequest, "reqLibName");
System.out.println("action request - received name: " + reqLibName);
		List<CommonLibrary> clList = CommonLibraryServiceUtil.retrieveLibrary(reqLibName);
		//actionRequest.setAttribute("foundLibList", clList);
		//String paramCommonLibListStr = toPlainString(clList);
		//actionRequest.setAttribute("foundLibList", paramCommonLibListStr);
		actionRequest.setAttribute("foundLibList", clList);
		String jspFileName = "/html/scienceappengine/lib_result.jsp";
		//String url = actionRequest.getContextPath()+jspFileName;
//System.out.println(url);
//		actionResponse.sendRedirect(url);
		actionResponse.setRenderParameter("jspPage", jspFileName);
		//actionResponse.setRenderParameter("jspPage", "/html/scienceappengine/lib_result.jsp");
		//super.processAction(actionRequest, actionResponse);
    } 
	
	/****
	 * Retrieve all modules installed
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 */
	public void retrieveAllModAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException {
		List<CommonModule> cmList = CommonModuleServiceUtil.retrieveAllModules();
		actionRequest.setAttribute("foundModList", cmList);
		String jspFileName = "/html/scienceappengine/mod_result.jsp";
		actionResponse.setRenderParameter("jspPage", jspFileName);
    } 
	
	/****
	 * Retrieve all modules installed
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 */
	public void retrieveAvailModAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException {
		List<CommonModule> cmList = CommonModuleServiceUtil.retrieveAvailModules();
		actionRequest.setAttribute("foundModList", cmList);
		String jspFileName = "/html/scienceappengine/mod_result.jsp";
		actionResponse.setRenderParameter("jspPage", jspFileName);
    } 
	
	/****
	 * Retrieve modules matching library name keyword
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 */
	public void retrieveModAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException {
		String reqModName = ParamUtil.getString(actionRequest, "reqModName");
System.out.println("action request - received mod name: " + reqModName);
		List<CommonModule> cmList = CommonModuleServiceUtil.retrieveModule(reqModName);
		actionRequest.setAttribute("foundModList", cmList);
		String jspFileName = "/html/scienceappengine/mod_result.jsp";
		actionResponse.setRenderParameter("jspPage", jspFileName);
    } 
	
	/****
	 * Load a selected modules 
	 * @param actionRequest
	 * @param actionResponse
	 * @throws IOException
	 * @throws PortletException
	 */
	public void loadModAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws IOException, PortletException {
		String reqModName = ParamUtil.getString(actionRequest, "reqModName");
		System.out.println("action request - received mod name: " + reqModName);
		String res = CommonModuleServiceUtil.loadModule(reqModName);
		actionRequest.setAttribute("loadModRes", res);
		String jspFileName = "/html/scienceappengine/load_mod.jsp";
		actionResponse.setRenderParameter("jspPage", jspFileName);
    } 
		
	/****
	 * Install a library by make install
	 * @param actionRequest
	 * @param actionResponse
	 * @return installation message
	 */
	public void makeInstallAction(ActionRequest actionRequest,
			             		   ActionResponse actionResponse) throws PortletException, IOException
	{
		String res = "";
		
		/**
		 * A library file
		 */
		String libName = ParamUtil.getString(actionRequest, "reqLibName");
		if (libName == null) {
			res = "lib name not available";
		}else{
			return;
		}	
		
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		/**
		 * A library file
		 */
		File uploadedTarFile = uploadRequest.getFile("testTarFileName");
		if (uploadedTarFile == null) {
			res = "File Unavailable";
		}else{
			System.out.println("src file size bytes:" + uploadedTarFile.length());
			System.out.println("src file name:" + uploadedTarFile.getName());
			System.out.println("lib name:" + uploadedTarFile.getName());
			
			String tarFileName = uploadedTarFile.getName();
			//String solverTestDirPath = Constants.SCIENCE_APP_PARENT_LOCATION + solverTestDirName;
			// create solver directory
			String libDirName = Constants.INSTALL_LIB_PATH+libName+"/";
			String result = makeDir(libDirName);
			if(!result.equalsIgnoreCase("")){
				res += ", Library Install Directory, named '"+Constants.INSTALL_LIB_PATH+"', cannot be created, due to '"+result+"'";
			}
			// System.out.println(uploadedFileLocation);
			
			// Specify a directory to store an uploaded library file.
			String uploadedTarFileLocation = libDirName+tarFileName;
System.out.println(uploadedTarFileLocation);

			//Create file input stream
			FileInputStream uploadedInputStream = new FileInputStream(uploadedTarFile);
			//Save this file
			File newFile = saveToFile(uploadedInputStream, uploadedTarFileLocation);
			if(!newFile.isFile() || newFile.length() == 0){
				res += ", " + Constants.FILE_OPEN_FAILURE;
			}
			//change the permission for execute
			changeExecPermission("x", uploadedTarFileLocation);

			// Let's remember any output while being installed.
			String normalOutput = "";
			String errorOutput = "";
			// Build a command for installing a given library.
			// String command = "sudo yum install " + fileDetail.getFileName();
			// String command = "cat " + uploadedFileLocation;
			String command = "untar " + libDirName + uploadedTarFile + ";"+ "cd " + uploadedTarFileLocation+";./make install";
			Runtime rt = Runtime.getRuntime();
			// Declare a process.
			Process p0 = null;
			try {
				// Let's execute the command.
				// p0 = rt.exec(commandAndArgs);
				// RPM installation
				System.out.println(command);
				p0 = rt.exec(command);
				// Get any input stream.
				InputStream instd = p0.getInputStream();
				// Let's get it through buffered reader.
				BufferedReader buf_reader = new BufferedReader(
						new InputStreamReader(instd));
				String temp = "";
				// System.out.println("new line executed command: " + command);
				while ((temp = buf_reader.readLine()) != null) {
					// System.out.println("temp: " + temp);
					normalOutput += temp + "\n";
				}
				// Let's close buffered reader
				buf_reader.close();
	
				// Get any error stream.
				InputStream errstd = p0.getErrorStream();
				// Let's get it through buffered reader.
				BufferedReader buf_err_reader = new BufferedReader(
						new InputStreamReader(errstd));
				// Initialize a temporary variable.
				temp = "";
				// Until there's no more error message,
				while ((temp = buf_err_reader.readLine()) != null) {
					// Append a current error message to the error message
					// container.
					errorOutput += temp + "\n";
				}
				// Report an error.
				System.err.println(errorOutput);
				// Close buffered error reader.
				buf_err_reader.close();
				// Let's wait p0 for completion.
				p0.waitFor();
			} catch (IOException e){
				e.printStackTrace();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
	
			res = "";
			// If there's no error, then send the success message.
			if (errorOutput.equalsIgnoreCase("")) {
				res += "Successful Installation of '" + tarFileName + "' !" + normalOutput;
			}
			// If there's any error,
			else {
				res += ", Installation Error Occurred: " + errorOutput;
			}
		}
		
		// DB access information: science app path, 
		actionRequest.setAttribute("makeInstallRes", res);
		String jspFileName = "/html/scienceappengine/make_install_res.jsp";
		actionResponse.setRenderParameter("jspPage", jspFileName);
	}
	
	/****
	 * Install a library
	 * @param actionRequest
	 * @param actionResponse
	 * @return installation message
	 */
	public void installLibAction(ActionRequest actionRequest,
			             		   ActionResponse actionResponse) throws PortletException, IOException
	{
//		String folder = getInitParameter("uploadFolder");
//		String realPath = getPortletContext().getRealPath("/");
		String res = "";
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		/**
		 * A library file
		 */
		File uploadedlibFile = uploadRequest.getFile("testLibFileName");
		if (uploadedlibFile == null) {
			res = "File Unavailable";
		}else{
			System.out.println("src file size bytes:" + uploadedlibFile.length());
			System.out.println("src file name:" + uploadedlibFile.getName());
			
			String libFileName = uploadedlibFile.getName();
			//String solverTestDirPath = Constants.SCIENCE_APP_PARENT_LOCATION + solverTestDirName;
			// create solver directory
			String result = makeDir(Constants.INSTALL_LIB_PATH);
			if(!result.equalsIgnoreCase("")){
				res += ", Library Install Directory, named '"+Constants.INSTALL_LIB_PATH+"', cannot be created, due to '"+result+"'";
			}
			// System.out.println(uploadedFileLocation);
			
			// Specify a directory to store an uploaded library file.
			String uploadedLibFileLocation = Constants.INSTALL_LIB_PATH + libFileName;
System.out.println(uploadedLibFileLocation);

			//Create file input stream
			FileInputStream uploadedInputStream = new FileInputStream(uploadedlibFile);
			//Save this file
			File newFile = saveToFile(uploadedInputStream, uploadedLibFileLocation);
			if(!newFile.isFile() || newFile.length() == 0){
				res += ", " + Constants.FILE_OPEN_FAILURE;
			}
			//change the permission for execute
			changeExecPermission("x", uploadedLibFileLocation);

			// Let's remember any output while being installed.
			String normalOutput = "";
			String errorOutput = "";
			// Build a command for installing a given library.
			// String command = "sudo yum install " + fileDetail.getFileName();
			// String command = "cat " + uploadedFileLocation;
			String command = "rpm -i " + uploadedLibFileLocation;
			// String command2 = "rpm --checksig " + uploadedFileLocation;
			// String command2 = "rpm -e " + uploadedFileLocation;
			// String command3 = "rpm -qpR " + uploadedFileLocation;
			// String command4 = "rpm -qa | grep -i"
			// String command = "more " + Constants.SAMPLE_MODULE_LIST_PATH;
			// Construct a command string.
			// String[] commandAndArgs = new String[]{ "/bin/sh", "-c", command};
			// Get Runtime instance.
			Runtime rt = Runtime.getRuntime();
			// Declare a process.
			Process p0 = null;
			try {
				// Let's execute the command.
				// p0 = rt.exec(commandAndArgs);
				// RPM installation
				System.out.println(command);
				p0 = rt.exec(command);
				// Get any input stream.
				InputStream instd = p0.getInputStream();
				// Let's get it through buffered reader.
				BufferedReader buf_reader = new BufferedReader(
						new InputStreamReader(instd));
				String temp = "";
				// System.out.println("new line executed command: " + command);
				while ((temp = buf_reader.readLine()) != null) {
					// System.out.println("temp: " + temp);
					normalOutput += temp + "\n";
				}
				// Let's close buffered reader
				buf_reader.close();
	
				// Get any error stream.
				InputStream errstd = p0.getErrorStream();
				// Let's get it through buffered reader.
				BufferedReader buf_err_reader = new BufferedReader(
						new InputStreamReader(errstd));
				// Initialize a temporary variable.
				temp = "";
				// Until there's no more error message,
				while ((temp = buf_err_reader.readLine()) != null) {
					// Append a current error message to the error message
					// container.
					errorOutput += temp + "\n";
				}
				// Report an error.
				System.err.println(errorOutput);
				// Close buffered error reader.
				buf_err_reader.close();
				// Let's wait p0 for completion.
				p0.waitFor();
			} catch (IOException e){
				e.printStackTrace();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
	
			res = "";
			// If there's no error, then send the success message.
			if (errorOutput.equalsIgnoreCase("")) {
				res += "Successful Installation of '" + libFileName + "' !" + normalOutput;
			}
			// If there's any error,
			else {
				res += ", Installation Error Occurred: " + errorOutput;
			}
		}
		
		// DB access information: science app path, 
		actionRequest.setAttribute("insLibRes", res);
		String jspFileName = "/html/scienceappengine/lib_ins_res.jsp";
		actionResponse.setRenderParameter("jspPage", jspFileName);
	}
	
	/***
	 * Save uploaded file to new location
	 * @param uploadedInputStream
	 * @param uploadedFileLocation
	 * @return
	 */
	private File saveToFile(InputStream uploadedInputStream,
							String uploadedFileLocation) {
		// Declare an output stream.
		File newFile = null;
		try {
			OutputStream out = null;
			// number of bytes read
			int read = 0;
			// 1024-byte buffer
			byte[] bytes = new byte[1024];
			// Assign file output stream.
			newFile = new File(uploadedFileLocation);
			out = new FileOutputStream(newFile);
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
		return newFile;
	}
		
	/*****
	 * Give execute permission to a given solver executable file
	 * 
	 * @param filePath
	 *            solver location
	 */
	private void changeExecPermission(String permission, String filePath) {
		// TODO Auto-generated method stub
		// change permission to the uploaded science app for execute
		//String command = "sudo chmod +" + permission + " " + filePath;
		String command = "chmod +" + permission + " " + filePath;
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
				// System.out.println(line);
			}
			// Close the buffered reader instance.
			buf_reader.close();
			// Let's wait for the Runtime instance to be done.
			p1.waitFor();

			InputStream errstd = p1.getErrorStream();
			BufferedReader buf_err_reader = new BufferedReader(
					new InputStreamReader(errstd));
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
	 * Test a Sci App
	 * @param actionRequest
	 * @param actionResponse
	 * @return installation message
	 */
	public void testSciAppAction(ActionRequest actionRequest,
			             		   ActionResponse actionResponse) throws PortletException, IOException
	{
		String res = "";
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		
		/***
		 * The code below should be replaced by the real routine to get the solver name from DB.
		 */
		String scienceAppName = uploadRequest.getParameter("testSciAppName");
		/**
		 * Science App File
		 */
		File srcFile = uploadRequest.getFile("testSciAppFileName");
		
		if (srcFile == null || scienceAppName == null || (scienceAppName != null && scienceAppName.equalsIgnoreCase(""))) {
			res = "No File Selected";
		}

		System.out.println("science app name: " + scienceAppName);
		System.out.println("src file size bytes:" + srcFile.length());
		System.out.println("src file name:" + srcFile.getName());
		
		// Get the solver name related to this solver
		//String solverName = getSolverName();
		String solverTestDirName = getSolverDirName();
		String solverTestDirPath = Constants.SCIENCE_APP_PARENT_LOCATION + solverTestDirName;
		// create solver directory
		String result = makeDir(solverTestDirPath);
		if(!result.equalsIgnoreCase("")){
			res += "Solver Directory, named '"+solverTestDirPath+"', cannot be created, due to '"+result+"'";
		}
		// System.out.println(uploadedFileLocation);

		// Create file input stream
		FileInputStream uploadedInputStream = new FileInputStream(srcFile);
		// Science App to be tested
		Constants.SCIENCE_APP_UNDER_TEST_FULL_PATH = solverTestDirPath + "/"+scienceAppName;
		// Save this file
		saveToFile(uploadedInputStream, Constants.SCIENCE_APP_UNDER_TEST_FULL_PATH);

		// change the permission for execute
		changeExecPermission("x", Constants.SCIENCE_APP_UNDER_TEST_FULL_PATH);
		
		File newFile = new File(Constants.SCIENCE_APP_UNDER_TEST_FULL_PATH);
		if(!newFile.isFile() || newFile.length() == 0){
			res += Constants.FILE_OPEN_FAILURE;
		}else{
			res = Constants.SCIENCE_APP_UNDER_TEST_FULL_PATH;
		}
		// DB access information: science app path, 
		actionRequest.setAttribute("uploadFileRes", res);
		String jspFileName = "/html/scienceappengine/file_upload.jsp";
		actionResponse.setRenderParameter("jspPage", jspFileName);
	}

	/****
	 * Unzip a solver file directory into a designated directory
	 * Assume that the name of the root directory of an uploaded one should be equal to the file name except extension.
	 * @param actionRequest
	 * @param actionResponse
	 * @return installation message
	 */
	public void unzipSciAppFileAction(ActionRequest actionRequest,
									  ActionResponse actionResponse) throws PortletException, IOException
	{
		String message ="";
		/***
		 * The code for getUnzipDirPath() below should be replaced by the real routine to get the solver name from DB.
		 */
		String unzipDirPath = "";
		boolean DB_ACCESS = false;
		if(DB_ACCESS){
			getUnzipDirPath();
		}else{
			unzipDirPath = "/EDISON/SOLVERS/TEST/sci_apps";
		}
		if(unzipDirPath == null || unzipDirPath.equalsIgnoreCase("")){
			message = "The directory path for unzip is not avaiable. Please check.";
		}else{
			UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
			
/***
 * test code for writing an uploaded zip file
 */
//String solverTestDirPath = Constants.SCIENCE_APP_PARENT_LOCATION;
//// Create file input stream
//FileInputStream uploadedInputStream = new FileInputStream(zipFile);
//// Science App to be tested
//Constants.SCIENCE_APP_UNDER_TEST_FULL_PATH = solverTestDirPath + strZipFileName;
//// Save this file
//saveToFile(uploadedInputStream, Constants.SCIENCE_APP_UNDER_TEST_FULL_PATH);
//
//// change the permission for execute
//changeExecPermission("x", Constants.SCIENCE_APP_UNDER_TEST_FULL_PATH);
//
//File newFile = new File(Constants.SCIENCE_APP_UNDER_TEST_FULL_PATH);
//if(!newFile.isFile() || newFile.length() == 0){
//	message += Constants.FILE_OPEN_FAILURE;
//}else{
//	message = "Unzip Successful!";
//}

			// Let's start unzipping the uploaded file!
			//get the zip file content
			try{
				/**
				 * solver zip File
				 */
				File zipFile = uploadRequest.getFile("zipFileName");
				if (zipFile == null || !zipFile.exists()) {
					message = "No File Uploaded";
					new Exception(message);
				}
				String strZipFileName = zipFile.getName();
				System.out.println("src file size bytes:" + zipFile.length());
				System.out.println("src file name:" + strZipFileName);

//				ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
//				//get the zipped file list entry
//				ZipEntry ze = zis.getNextEntry();
//				
//				while(ze != null){
//					String fileName = ze.getName();
//					File newFile = new File(unzipDirPath + Constants.FILE_SEPARATOR + fileName);
//		                
//					System.out.println("file unzip : "+ newFile.getAbsoluteFile());
//		                
//		            // Let's first create all non exists folders.
//		            // Otherwise, we will hit FileNotFoundException for compressed folder
//		            new File(newFile.getParent()).mkdirs();
//		              
//		            FileOutputStream fos = new FileOutputStream(newFile);             
//		            byte[] buffer = new byte[1024];
//		            int len;
//		            while ((len = zis.read(buffer)) > 0) {
//		            	fos.write(buffer, 0, len);
//		            }
//		        		
//		            fos.close();   
//		            ze = zis.getNextEntry();
//				}
//				
//				zis.closeEntry();
//		    	zis.close();
				//String solverTestDirPath = Constants.SCIENCE_APP_PARENT_LOCATION;
				// Create file input stream
				FileInputStream uploadedInputStream = new FileInputStream(zipFile);
				// Science App to be tested
				String zipFilePath = unzipDirPath + Constants.FILE_SEPARATOR;// + strZipFileName;
				// Save this file
				saveToFile(uploadedInputStream, zipFilePath+ strZipFileName);
				
				// change the permission for execute
				changeExecPermission("x", zipFilePath+ strZipFileName);
				
				File newZipFile = new File(zipFilePath+strZipFileName);
				if(!newZipFile.isFile() || newZipFile.length() == 0){
					message += Constants.FILE_OPEN_FAILURE;
					new Exception(message);
				}
				
				String zipcommand = "";
				if(strZipFileName.endsWith("gz")){
					zipcommand = "cd " + zipFilePath + "; tar -xvfz " + zipFilePath+strZipFileName;
				}else if(strZipFileName.endsWith("tar")){
					zipcommand = "cd " + zipFilePath + "; tar -xvf " + zipFilePath+strZipFileName;
				}else if(strZipFileName.endsWith("zip")){
					zipcommand = "cd " + zipFilePath + "; unzip -o " + zipFilePath+strZipFileName + " -d " + zipFilePath;
				}else{
					new Exception("file extension not ending with *.gz or *.zip");
				}
				String removeCommand = "rm -f " + zipFilePath+strZipFileName;
				zipcommand += "; " + removeCommand;
System.out.println(zipcommand);
				// unzip file
				executeCommand(zipcommand);
//				/***
//				 * The code below should be replaced by the real routine to get the solver name from DB.
//				 */
//				String zipRootDirName = uploadRequest.getParameter("zipRootDirName");
//				System.out.println("ziproot dir: " + zipRootDirName);
//				/**
//				 * zipped file's root directory
//				 */
//				File zipRootDirFile = new File(zipFilePath+zipRootDirName);
//				if (zipRootDirFile == null || !zipRootDirFile.exists()) {
//					executeCommand(removeCommand);
//					message = "Check 1) your zip file or 2) the name of the root directory of that file that you just typed";
//				}else{
//					message = "Unzip '" + zipFilePath+zipRootDirName + "' Successful!";
//				}
				//System.out.println(message);
				
				String zipRootDirName = uploadRequest.getFileName("zipFileName");
System.out.println("upload file name:"+zipRootDirName);
				String[] list = zipRootDirName.split("\\.");
				for(String str : list){
					System.out.println(str);
					zipRootDirName = str;
					break;
				}
System.out.println("zip root dir:"+zipFilePath+zipRootDirName);
				/**
//				 * zipped file's root directory
//				 */
				File zipRootDirFile = new File(zipFilePath+zipRootDirName);
				if (zipRootDirFile == null || !zipRootDirFile.exists()) {
					executeCommand(removeCommand);
					message = "Check 1) your zip file or 2) the name of the root directory of that file that you just typed";
				}else{
					message = "Unzip '" + zipFilePath+zipRootDirName + "' Successful!";
				}
			}catch(Exception ex){
				message = ex.getMessage();
			}
		}
		actionRequest.setAttribute("unzipSciAppRes", message);
		String jspFileName = "/html/scienceappengine/unzip_result.jsp";
		actionResponse.setRenderParameter("jspPage", jspFileName);
	}
	
	private String getUnzipDirPath() {
		String strUnzipDirPath = "";
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName(Constants.MYSQL_DRIVER_CLASS_NAME);
			conn = DriverManager.getConnection(Constants.connectString, Constants.userName, Constants.password);
			stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException sqlex) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1); // programemer/dbsm error
		}
		
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("SELECT * FROM EDAPP_TEST_PATH");
			if(rs.next()){
				strUnzipDirPath = rs.getString(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("unzip directory: " + strUnzipDirPath);
		return strUnzipDirPath;
	}

	/****
	 * Unzip a given file
	 * @param givenPath a given path
	 * @return install message
	 */
	private String executeCommand(String zipCommand) {
		String res = "";
		//String command = zipCommand + " " + givenFile + targetDir;
		String command = zipCommand;
		String[] commandAndArgs = new String[]{ "/bin/sh", "-c", command};
		//System.out.println(commandAndArgs);
		// Get Runtime instance.
		Runtime rt = Runtime.getRuntime();
		// Declare a process.
		Process p0 = null;
		try {
			// Let's execute the command.
			p0 = rt.exec(commandAndArgs);
			// Get any input stream.
			InputStream instd = p0.getInputStream();
			// Let's get it through buffered reader.
			BufferedReader buf_reader = new BufferedReader(
					new InputStreamReader(instd));
			String temp = "";
			// System.out.println("new line executed command: " + command);
			while ((temp = buf_reader.readLine()) != null) {
				// System.out.println("temp: " + temp);
				//res += temp + "\n";
			}
			// Let's close buffered reader
			buf_reader.close();

			// Get any error stream.
			InputStream errstd = p0.getErrorStream();
			// Let's get it through buffered reader.
			BufferedReader buf_err_reader = new BufferedReader(
					new InputStreamReader(errstd));
			// Initialize a temporary variable.
			temp = "";
			// Until there's no more error message,
			while ((temp = buf_err_reader.readLine()) != null) {
				// Append a current error message to the error message
				// container.
				res += temp + "\n";
			}
			// Report an error.
			System.err.println(res);
			// Close buffered error reader.
			buf_err_reader.close();
			// Let's wait p0 for completion.
			p0.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	/****
	 * Create a directory
	 * @param givenPath a given path
	 * @return install message
	 */
	private String makeDir(String givenPath) {
		String res = "";
		String command = "mkdir -p " + givenPath;
		String[] commandAndArgs = new String[]{ "/bin/sh", "-c", command};
		// Get Runtime instance.
		Runtime rt = Runtime.getRuntime();
		// Declare a process.
		Process p0 = null;
		try {
			// Let's execute the command.
			p0 = rt.exec(commandAndArgs);
			// Get any input stream.
			InputStream instd = p0.getInputStream();
			// Let's get it through buffered reader.
			BufferedReader buf_reader = new BufferedReader(
					new InputStreamReader(instd));
			String temp = "";
			// System.out.println("new line executed command: " + command);
			while ((temp = buf_reader.readLine()) != null) {
				// System.out.println("temp: " + temp);
				//res += temp + "\n";
			}
			// Let's close buffered reader
			buf_reader.close();

			// Get any error stream.
			InputStream errstd = p0.getErrorStream();
			// Let's get it through buffered reader.
			BufferedReader buf_err_reader = new BufferedReader(
					new InputStreamReader(errstd));
			// Initialize a temporary variable.
			temp = "";
			// Until there's no more error message,
			while ((temp = buf_err_reader.readLine()) != null) {
				// Append a current error message to the error message
				// container.
				res += temp + "\n";
			}
			// Report an error.
			System.err.println(res);
			// Close buffered error reader.
			buf_err_reader.close();
			// Let's wait p0 for completion.
			p0.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	private String getSolverDirName() {
		// Should be updated with the correct solver name
		String solverName = "yksuh";
		return solverName;
	}

	@Override
	public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException, PortletException 
	{
//		String resourceID = request.getResourceID();
//		if (resourceID.equals("libSearch")) {
//			String reqLibName = request.getParameter("reqLibName");
//			System.out.println("received name2: " + reqLibName);
//			List<CommonLibrary> clList = CommonLibraryLocalServiceUtil.retrieveLibrary(reqLibName);
////			.setAttribute("foundLibList", clList);
//			response
//			
////			String invoice = request.getParameter("invoice");
////            if (invoice == null) {
////                throw new PortletException(
////                     "Required parameter, invoice, is missing.");
////            } else {
////                String path = "/html/" + invoice + ".html";
////                String content = getContents(path, locale, true);
////                PrintWriter writer = response.getWriter();
////                writer.print(content);
////           }
//        } else if (resourceID.equals("js")) {
////            String content = getContents(jsPage, locale, false);
////            PrintWriter writer = response.getWriter();
////            writer.print(content);
//        }
//		String resourceID = request.getResourceID();
//		if (resourceID.equals("libSearch")) {
//			String reqLibName = request.getParameter("reqLibName");
//			List<CommonLibrary> clList = CommonLibraryLocalServiceUtil.retrieveLibrary(reqLibName);
//			request.getPortletSession().setAttribute("foundLibList", clList);
//		}
	}
	
	@Override
	public void doView(RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {
		// TODO Auto-generated method stub
		//String reqLibName = (String) renderRequest.getPortletSession().getAttribute("libName");
//		String reqLibName = renderRequest.getParameter("reqLibName");
//System.out.println("received name: " + reqLibName);
//		List<CommonLibrary> clList = CommonLibraryLocalServiceUtil.retrieveLibrary(reqLibName);
//		// 
//	//	CommonScienceEnginge.copyFile("pwLab");
		//renderRequest.setAttribute("foundLibList", clList);
//		List<CommonLibrary> clList = (List<CommonLibrary>)renderRequest.getPortletSession().getAttribute("foundLibList");
//		String clList = (String)renderRequest.getPortletSession().getAttribute("foundLibList");
//		renderRequest.setAttribute("foundLibList", clList);
		super.doView(renderRequest, renderResponse);
	}
}