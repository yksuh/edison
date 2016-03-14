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

package com.kisti.scienceappstore.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommonLibraryService}.
 *
 * @author yksuh
 * @see CommonLibraryService
 * @generated
 */
public class CommonLibraryServiceWrapper implements CommonLibraryService,
	ServiceWrapper<CommonLibraryService> {
	public CommonLibraryServiceWrapper(
		CommonLibraryService commonLibraryService) {
		_commonLibraryService = commonLibraryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _commonLibraryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_commonLibraryService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _commonLibraryService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public java.util.List<com.kisti.scienceappstore.model.CommonLibrary> retrieveAllLibraries() {
		return _commonLibraryService.retrieveAllLibraries();
	}

	@Override
	public java.util.List<com.kisti.scienceappstore.model.CommonLibrary> retrieveLibrary(
		java.lang.String name) {
		return _commonLibraryService.retrieveLibrary(name);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public CommonLibraryService getWrappedCommonLibraryService() {
		return _commonLibraryService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedCommonLibraryService(
		CommonLibraryService commonLibraryService) {
		_commonLibraryService = commonLibraryService;
	}

	@Override
	public CommonLibraryService getWrappedService() {
		return _commonLibraryService;
	}

	@Override
	public void setWrappedService(CommonLibraryService commonLibraryService) {
		_commonLibraryService = commonLibraryService;
	}

	private CommonLibraryService _commonLibraryService;
}