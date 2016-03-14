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

package com.kisti.scienceappstore.service.http;

import com.kisti.scienceappstore.service.CommonModuleServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.kisti.scienceappstore.service.CommonModuleServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.kisti.scienceappstore.model.CommonModuleSoap}.
 * If the method in the service utility returns a
 * {@link com.kisti.scienceappstore.model.CommonModule}, that is translated to a
 * {@link com.kisti.scienceappstore.model.CommonModuleSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author yksuh
 * @see CommonModuleServiceHttp
 * @see com.kisti.scienceappstore.model.CommonModuleSoap
 * @see com.kisti.scienceappstore.service.CommonModuleServiceUtil
 * @generated
 */
public class CommonModuleServiceSoap {
	public static com.kisti.scienceappstore.model.CommonModuleSoap[] retrieveAllModules()
		throws RemoteException {
		try {
			java.util.List<com.kisti.scienceappstore.model.CommonModule> returnValue =
				CommonModuleServiceUtil.retrieveAllModules();

			return com.kisti.scienceappstore.model.CommonModuleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Retrieve modules by keyword
	*
	* @param mod_name
	module name
	* @return a list of modules found
	*/
	public static com.kisti.scienceappstore.model.CommonModuleSoap[] retrieveModule(
		java.lang.String mod_name) throws RemoteException {
		try {
			java.util.List<com.kisti.scienceappstore.model.CommonModule> returnValue =
				CommonModuleServiceUtil.retrieveModule(mod_name);

			return com.kisti.scienceappstore.model.CommonModuleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Retrieve available modules
	*
	* @return a list of modules available
	*/
	public static com.kisti.scienceappstore.model.CommonModuleSoap[] retrieveAvailModules()
		throws RemoteException {
		try {
			java.util.List<com.kisti.scienceappstore.model.CommonModule> returnValue =
				CommonModuleServiceUtil.retrieveAvailModules();

			return com.kisti.scienceappstore.model.CommonModuleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Loading a specific module
	*
	* @param mod_name module name to be loaded
	* @return result message
	*/
	public static java.lang.String loadModule(java.lang.String mod_name)
		throws RemoteException {
		try {
			java.lang.String returnValue = CommonModuleServiceUtil.loadModule(mod_name);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommonModuleServiceSoap.class);
}