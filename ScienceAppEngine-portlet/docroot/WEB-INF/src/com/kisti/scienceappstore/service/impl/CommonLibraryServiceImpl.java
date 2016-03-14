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

import com.kisti.scienceappengine.Constants;
import com.kisti.scienceappstore.model.CommonLibrary;
import com.kisti.scienceappstore.model.impl.CommonLibraryImpl;
import com.kisti.scienceappstore.service.base.CommonLibraryServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the common library remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.kisti.scienceappstore.service.CommonLibraryService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author yksuh
 * @see com.kisti.scienceappstore.service.base.CommonLibraryServiceBaseImpl
 * @see com.kisti.scienceappstore.service.CommonLibraryServiceUtil
 */
public class CommonLibraryServiceImpl extends CommonLibraryServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.kisti.scienceappstore.service.CommonLibraryServiceUtil} to access the common library remote service.
	 */
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.kisti.scienceappstore.service.CommonLibraryLocalServiceUtil} to
	 * access the common library local service.
	 */
	public List<CommonLibrary> retrieveAllLibraries() {
//System.out.println("The installed library list has been requested!");
		// CommonLibrary resLib = commonLibraryPersistence.;
		// resLib = this.commonLibraryPersistence.findByLibName(name);
		
		String command = "";
		//if(!Constants.DEBUG_MODE){
			command = "ldconfig -p"; 
		///}else{
		//	command = "cat " + Constants.SAMPLE_LIBRARY_LIST_PATH;
		//}
System.out.println("retriving ...");
		try {
			Runtime rt = Runtime.getRuntime();
			Process p1 = rt.exec(command);
			InputStream instd = p1.getInputStream();
			BufferedReader buf_reader = new BufferedReader(new InputStreamReader(instd));
			//System.out.println("success");
			String str = "";
			int lineCnt = 0;
			while ((str = buf_reader.readLine()) != null) {
				//System.out.println(str);
				lineCnt++;
				if(lineCnt == 1) continue;
//System.out.println(lineCnt+": " + str);
				String libName = "";
				String linuxCLibVer = "";
				String sysArch = "";
				String kernelVer = "";
				String libPath = "";
				int tokenIdx = 0;
				StringTokenizer st = new StringTokenizer(str, "=>|\\(|\\)");
				while(st.hasMoreTokens()){
					String token = (st.nextToken()).trim();
					if(token.equalsIgnoreCase("")) continue;
					tokenIdx++;
//							System.out.println(token);
					if(tokenIdx == 1){
						libName = token;
					}
//							i686 = 32-bit Intel x86 arch 
//							x86_64 = 64-bit Intel x86 arch
					else if(tokenIdx == 2){
						String[] result = token.split(",");
						int subTokenIdx = 0;
						for (int x=0; x<result.length; x++){
					    	 String temp = (result[x]).trim();
					    	 if(temp.equalsIgnoreCase("")){ continue; }
					    	 subTokenIdx++;
					    	 if(subTokenIdx == 1){
					    		 linuxCLibVer = temp;
					    		 sysArch = "x86";
					    	 }else if(subTokenIdx == 2){
					    		 if(temp.startsWith("x")){
				    				 sysArch = temp;
//						    				 libm.so.6 (libc6, hwcap: 0x0018000000000000, OS ABI: Linux 2.6.9) => /lib/i686/nosegneg/libm.so.6
				    			 }
					    		 else if(temp.startsWith("h")){
				    				 sysArch = "i686";
				    			 }
					    		 else{
				    				 sysArch = "x86";
				    				 temp = (temp.replaceAll("OS ABI: Linux", "")).trim();
				    				 kernelVer = temp;
				    			 }
					    	 }else if(subTokenIdx == 3){
					    		 temp = (temp.replaceAll("OS ABI: Linux", "")).trim();
					    		 kernelVer = temp;
					    	 }
						}
					}else if(tokenIdx == 3){
						libPath = token;	
					}
				}
//System.out.println(libName+"|"+linuxCLibVer+"|"+sysArch+"|"+kernelVer+"|"+libPath);
				CommonLibraryImpl cl = new CommonLibraryImpl();
				cl.setLibName(libName);
				cl.setCLibVer(linuxCLibVer);
				cl.setSysArch(sysArch);
				cl.setKernelVer(kernelVer);
				cl.setLibPath(libPath);
				cl.persist();
			}
			buf_reader.close();
//					InputStream errstd = p1.getErrorStream();
//					BufferedReader buf_err_reader = new BufferedReader(
//							new InputStreamReader(errstd));
//					System.out.println("error");
//					while ((str = buf_err_reader.readLine()) != null) {
//						System.out.println(str);
//					}
//					buf_err_reader.close();
			p1.waitFor();
		} catch (Exception ex) {
			//ex.printStackTrace();
			System.err.println(ex.getMessage());
		}
				
		List<CommonLibrary> resList = new ArrayList<CommonLibrary>();
		try {
			resList = this.commonLibraryPersistence.findAll();
System.out.println("The installed library list has been returned!");
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resList;
	}

//	public List<CommonLibrary> retrieveLibrary(String name) {
//		List<CommonLibrary> resLibList = new ArrayList<CommonLibrary>();
//		if(name == null || name == ""){ return resLibList; }
//		
//		try {
//			List<CommonLibrary> resList = this.commonLibraryPersistence.findAll();
//			// if there's no common lib found, then retrieve again.
//			if(resList.size() == 0){
//				retrieveAllLibraries();
//			}
//			for(int i=0;i<resList.size();i++){
//				CommonLibrary cl = (CommonLibrary)resList.get(i);
//				String libName = cl.getLibName();
//				if(libName.contains(name)){
//					resLibList.add(cl);
//				}
//			}
//System.out.println("The installed library list has been returned!:" + resLibList.size());
//		} catch (SystemException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//for(int i=0;i<resLibList.size();i++){
//	System.out.println(resLibList.get(i).getLibName());
//}
//		return resLibList;
//	}
	
	
	public List<CommonLibrary> retrieveLibrary(String name) {
		ArrayList<CommonLibrary> resLibList = new ArrayList<CommonLibrary>();
		if(name == null || name == ""){ return null; }
		
		try {
			List<CommonLibrary> resList = this.commonLibraryPersistence.findAll();
			// if there's no common lib found, then retrieve again.
			if(resList.size() == 0){
				retrieveAllLibraries();
			}
			for(int i=0;i<resList.size();i++){
				CommonLibrary cl = (CommonLibrary)resList.get(i);
				String libName = cl.getLibName();
				if(libName.contains(name)){
					resLibList.add(cl);
				}
			}
//System.out.println("The installed library list has been returned!:" + resLibList.size());
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resLibList;
	}
}