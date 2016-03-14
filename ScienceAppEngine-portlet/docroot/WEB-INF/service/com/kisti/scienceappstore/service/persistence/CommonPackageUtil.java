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

import com.kisti.scienceappstore.model.CommonPackage;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the common package service. This utility wraps {@link CommonPackagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author yksuh
 * @see CommonPackagePersistence
 * @see CommonPackagePersistenceImpl
 * @generated
 */
public class CommonPackageUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(CommonPackage commonPackage) {
		getPersistence().clearCache(commonPackage);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CommonPackage> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommonPackage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommonPackage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static CommonPackage update(CommonPackage commonPackage)
		throws SystemException {
		return getPersistence().update(commonPackage);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static CommonPackage update(CommonPackage commonPackage,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(commonPackage, serviceContext);
	}

	/**
	* Caches the common package in the entity cache if it is enabled.
	*
	* @param commonPackage the common package
	*/
	public static void cacheResult(
		com.kisti.scienceappstore.model.CommonPackage commonPackage) {
		getPersistence().cacheResult(commonPackage);
	}

	/**
	* Caches the common packages in the entity cache if it is enabled.
	*
	* @param commonPackages the common packages
	*/
	public static void cacheResult(
		java.util.List<com.kisti.scienceappstore.model.CommonPackage> commonPackages) {
		getPersistence().cacheResult(commonPackages);
	}

	/**
	* Creates a new common package with the primary key. Does not add the common package to the database.
	*
	* @param commonPackagePK the primary key for the new common package
	* @return the new common package
	*/
	public static com.kisti.scienceappstore.model.CommonPackage create(
		com.kisti.scienceappstore.service.persistence.CommonPackagePK commonPackagePK) {
		return getPersistence().create(commonPackagePK);
	}

	/**
	* Removes the common package with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commonPackagePK the primary key of the common package
	* @return the common package that was removed
	* @throws com.kisti.scienceappstore.NoSuchCommonPackageException if a common package with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.scienceappstore.model.CommonPackage remove(
		com.kisti.scienceappstore.service.persistence.CommonPackagePK commonPackagePK)
		throws com.kisti.scienceappstore.NoSuchCommonPackageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(commonPackagePK);
	}

	public static com.kisti.scienceappstore.model.CommonPackage updateImpl(
		com.kisti.scienceappstore.model.CommonPackage commonPackage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(commonPackage);
	}

	/**
	* Returns the common package with the primary key or throws a {@link com.kisti.scienceappstore.NoSuchCommonPackageException} if it could not be found.
	*
	* @param commonPackagePK the primary key of the common package
	* @return the common package
	* @throws com.kisti.scienceappstore.NoSuchCommonPackageException if a common package with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.scienceappstore.model.CommonPackage findByPrimaryKey(
		com.kisti.scienceappstore.service.persistence.CommonPackagePK commonPackagePK)
		throws com.kisti.scienceappstore.NoSuchCommonPackageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(commonPackagePK);
	}

	/**
	* Returns the common package with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commonPackagePK the primary key of the common package
	* @return the common package, or <code>null</code> if a common package with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.kisti.scienceappstore.model.CommonPackage fetchByPrimaryKey(
		com.kisti.scienceappstore.service.persistence.CommonPackagePK commonPackagePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(commonPackagePK);
	}

	/**
	* Returns all the common packages.
	*
	* @return the common packages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.scienceappstore.model.CommonPackage> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the common packages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.scienceappstore.model.impl.CommonPackageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of common packages
	* @param end the upper bound of the range of common packages (not inclusive)
	* @return the range of common packages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.scienceappstore.model.CommonPackage> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the common packages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.scienceappstore.model.impl.CommonPackageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of common packages
	* @param end the upper bound of the range of common packages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of common packages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.kisti.scienceappstore.model.CommonPackage> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the common packages from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of common packages.
	*
	* @return the number of common packages
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static CommonPackagePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (CommonPackagePersistence)PortletBeanLocatorUtil.locate(com.kisti.scienceappstore.service.ClpSerializer.getServletContextName(),
					CommonPackagePersistence.class.getName());

			ReferenceRegistry.registerReference(CommonPackageUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(CommonPackagePersistence persistence) {
	}

	private static CommonPackagePersistence _persistence;
}