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

import com.kisti.scienceappstore.model.CommonModule;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the common module service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author yksuh
 * @see CommonModulePersistenceImpl
 * @see CommonModuleUtil
 * @generated
 */
public interface CommonModulePersistence extends BasePersistence<CommonModule> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommonModuleUtil} to access the common module persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the common module in the entity cache if it is enabled.
	*
	* @param commonModule the common module
	*/
	public void cacheResult(
		com.kisti.scienceappstore.model.CommonModule commonModule);

	/**
	* Caches the common modules in the entity cache if it is enabled.
	*
	* @param commonModules the common modules
	*/
	public void cacheResult(
		java.util.List<com.kisti.scienceappstore.model.CommonModule> commonModules);

	/**
	* Creates a new common module with the primary key. Does not add the common module to the database.
	*
	* @param moduleName the primary key for the new common module
	* @return the new common module
	*/
	public com.kisti.scienceappstore.model.CommonModule create(
		java.lang.String moduleName);

	/**
	* Removes the common module with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param moduleName the primary key of the common module
	* @return the common module that was removed
	* @throws com.kisti.scienceappstore.NoSuchCommonModuleException if a common module with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.scienceappstore.model.CommonModule remove(
		java.lang.String moduleName)
		throws com.kisti.scienceappstore.NoSuchCommonModuleException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.kisti.scienceappstore.model.CommonModule updateImpl(
		com.kisti.scienceappstore.model.CommonModule commonModule)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the common module with the primary key or throws a {@link com.kisti.scienceappstore.NoSuchCommonModuleException} if it could not be found.
	*
	* @param moduleName the primary key of the common module
	* @return the common module
	* @throws com.kisti.scienceappstore.NoSuchCommonModuleException if a common module with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.scienceappstore.model.CommonModule findByPrimaryKey(
		java.lang.String moduleName)
		throws com.kisti.scienceappstore.NoSuchCommonModuleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the common module with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param moduleName the primary key of the common module
	* @return the common module, or <code>null</code> if a common module with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.kisti.scienceappstore.model.CommonModule fetchByPrimaryKey(
		java.lang.String moduleName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the common modules.
	*
	* @return the common modules
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.scienceappstore.model.CommonModule> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the common modules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.scienceappstore.model.impl.CommonModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of common modules
	* @param end the upper bound of the range of common modules (not inclusive)
	* @return the range of common modules
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.scienceappstore.model.CommonModule> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the common modules.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.scienceappstore.model.impl.CommonModuleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of common modules
	* @param end the upper bound of the range of common modules (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of common modules
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.kisti.scienceappstore.model.CommonModule> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the common modules from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of common modules.
	*
	* @return the number of common modules
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}