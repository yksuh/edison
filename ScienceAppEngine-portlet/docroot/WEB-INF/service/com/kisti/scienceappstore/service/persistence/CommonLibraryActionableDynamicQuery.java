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

package com.kisti.scienceappstore.service.persistence;

import com.kisti.scienceappstore.model.CommonLibrary;
import com.kisti.scienceappstore.service.CommonLibraryLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author yksuh
 * @generated
 */
public abstract class CommonLibraryActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public CommonLibraryActionableDynamicQuery() throws SystemException {
		setBaseLocalService(CommonLibraryLocalServiceUtil.getService());
		setClass(CommonLibrary.class);

		setClassLoader(com.kisti.scienceappstore.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("commonLibraryId");
	}
}