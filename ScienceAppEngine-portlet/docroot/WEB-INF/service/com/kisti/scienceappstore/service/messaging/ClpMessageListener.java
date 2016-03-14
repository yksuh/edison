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

package com.kisti.scienceappstore.service.messaging;

import com.kisti.scienceappstore.service.ClpSerializer;
import com.kisti.scienceappstore.service.CommonLibraryLocalServiceUtil;
import com.kisti.scienceappstore.service.CommonLibraryServiceUtil;
import com.kisti.scienceappstore.service.CommonModuleLocalServiceUtil;
import com.kisti.scienceappstore.service.CommonModuleServiceUtil;
import com.kisti.scienceappstore.service.CommonPackageLocalServiceUtil;
import com.kisti.scienceappstore.service.CommonPackageServiceUtil;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author yksuh
 */
public class ClpMessageListener extends BaseMessageListener {
	public static String getServletContextName() {
		return ClpSerializer.getServletContextName();
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");
		String servletContextName = message.getString("servletContextName");

		if (command.equals("undeploy") &&
				servletContextName.equals(getServletContextName())) {
			CommonLibraryLocalServiceUtil.clearService();

			CommonLibraryServiceUtil.clearService();
			CommonModuleLocalServiceUtil.clearService();

			CommonModuleServiceUtil.clearService();
			CommonPackageLocalServiceUtil.clearService();

			CommonPackageServiceUtil.clearService();
		}
	}
}