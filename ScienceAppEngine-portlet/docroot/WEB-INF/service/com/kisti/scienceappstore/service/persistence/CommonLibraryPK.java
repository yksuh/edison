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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author yksuh
 * @generated
 */
public class CommonLibraryPK implements Comparable<CommonLibraryPK>,
	Serializable {
	public String libName;
	public String sysArch;

	public CommonLibraryPK() {
	}

	public CommonLibraryPK(String libName, String sysArch) {
		this.libName = libName;
		this.sysArch = sysArch;
	}

	public String getLibName() {
		return libName;
	}

	public void setLibName(String libName) {
		this.libName = libName;
	}

	public String getSysArch() {
		return sysArch;
	}

	public void setSysArch(String sysArch) {
		this.sysArch = sysArch;
	}

	@Override
	public int compareTo(CommonLibraryPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		value = libName.compareTo(pk.libName);

		if (value != 0) {
			return value;
		}

		value = sysArch.compareTo(pk.sysArch);

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CommonLibraryPK)) {
			return false;
		}

		CommonLibraryPK pk = (CommonLibraryPK)obj;

		if ((libName.equals(pk.libName)) && (sysArch.equals(pk.sysArch))) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (String.valueOf(libName) + String.valueOf(sysArch)).hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(10);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("libName");
		sb.append(StringPool.EQUAL);
		sb.append(libName);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("sysArch");
		sb.append(StringPool.EQUAL);
		sb.append(sysArch);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}