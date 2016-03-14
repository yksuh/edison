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

import com.kisti.scienceappstore.model.CommonLibrary;
import com.kisti.scienceappstore.model.impl.CommonLibraryImpl;
import com.kisti.scienceappstore.service.base.CommonPackageServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * The implementation of the common package remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.kisti.scienceappstore.service.CommonPackageService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author yksuh
 * @see com.kisti.scienceappstore.service.base.CommonPackageServiceBaseImpl
 * @see com.kisti.scienceappstore.service.CommonPackageServiceUtil
 */
public class CommonPackageServiceImpl extends CommonPackageServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.kisti.scienceappstore.service.CommonPackageServiceUtil} to access the common package remote service.
	 */
	//registerPkgAction
	public List<CommonLibrary> retrieveAllPackages() {
		return null;
	}
}