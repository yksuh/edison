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

package com.kisti.scienceappstore.service.base;

import com.kisti.scienceappstore.model.CommonPackage;
import com.kisti.scienceappstore.service.CommonPackageLocalService;
import com.kisti.scienceappstore.service.persistence.CommonLibraryPersistence;
import com.kisti.scienceappstore.service.persistence.CommonModulePersistence;
import com.kisti.scienceappstore.service.persistence.CommonPackagePK;
import com.kisti.scienceappstore.service.persistence.CommonPackagePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.UserPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the common package local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.kisti.scienceappstore.service.impl.CommonPackageLocalServiceImpl}.
 * </p>
 *
 * @author yksuh
 * @see com.kisti.scienceappstore.service.impl.CommonPackageLocalServiceImpl
 * @see com.kisti.scienceappstore.service.CommonPackageLocalServiceUtil
 * @generated
 */
public abstract class CommonPackageLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements CommonPackageLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.kisti.scienceappstore.service.CommonPackageLocalServiceUtil} to access the common package local service.
	 */

	/**
	 * Adds the common package to the database. Also notifies the appropriate model listeners.
	 *
	 * @param commonPackage the common package
	 * @return the common package that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommonPackage addCommonPackage(CommonPackage commonPackage)
		throws SystemException {
		commonPackage.setNew(true);

		return commonPackagePersistence.update(commonPackage);
	}

	/**
	 * Creates a new common package with the primary key. Does not add the common package to the database.
	 *
	 * @param commonPackagePK the primary key for the new common package
	 * @return the new common package
	 */
	@Override
	public CommonPackage createCommonPackage(CommonPackagePK commonPackagePK) {
		return commonPackagePersistence.create(commonPackagePK);
	}

	/**
	 * Deletes the common package with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commonPackagePK the primary key of the common package
	 * @return the common package that was removed
	 * @throws PortalException if a common package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommonPackage deleteCommonPackage(CommonPackagePK commonPackagePK)
		throws PortalException, SystemException {
		return commonPackagePersistence.remove(commonPackagePK);
	}

	/**
	 * Deletes the common package from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commonPackage the common package
	 * @return the common package that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public CommonPackage deleteCommonPackage(CommonPackage commonPackage)
		throws SystemException {
		return commonPackagePersistence.remove(commonPackage);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(CommonPackage.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return commonPackagePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.scienceappstore.model.impl.CommonPackageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return commonPackagePersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.scienceappstore.model.impl.CommonPackageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return commonPackagePersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return commonPackagePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) throws SystemException {
		return commonPackagePersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public CommonPackage fetchCommonPackage(CommonPackagePK commonPackagePK)
		throws SystemException {
		return commonPackagePersistence.fetchByPrimaryKey(commonPackagePK);
	}

	/**
	 * Returns the common package with the primary key.
	 *
	 * @param commonPackagePK the primary key of the common package
	 * @return the common package
	 * @throws PortalException if a common package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CommonPackage getCommonPackage(CommonPackagePK commonPackagePK)
		throws PortalException, SystemException {
		return commonPackagePersistence.findByPrimaryKey(commonPackagePK);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return commonPackagePersistence.findByPrimaryKey(primaryKeyObj);
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
	@Override
	public List<CommonPackage> getCommonPackages(int start, int end)
		throws SystemException {
		return commonPackagePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of common packages.
	 *
	 * @return the number of common packages
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getCommonPackagesCount() throws SystemException {
		return commonPackagePersistence.countAll();
	}

	/**
	 * Updates the common package in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param commonPackage the common package
	 * @return the common package that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public CommonPackage updateCommonPackage(CommonPackage commonPackage)
		throws SystemException {
		return commonPackagePersistence.update(commonPackage);
	}

	/**
	 * Returns the common library local service.
	 *
	 * @return the common library local service
	 */
	public com.kisti.scienceappstore.service.CommonLibraryLocalService getCommonLibraryLocalService() {
		return commonLibraryLocalService;
	}

	/**
	 * Sets the common library local service.
	 *
	 * @param commonLibraryLocalService the common library local service
	 */
	public void setCommonLibraryLocalService(
		com.kisti.scienceappstore.service.CommonLibraryLocalService commonLibraryLocalService) {
		this.commonLibraryLocalService = commonLibraryLocalService;
	}

	/**
	 * Returns the common library remote service.
	 *
	 * @return the common library remote service
	 */
	public com.kisti.scienceappstore.service.CommonLibraryService getCommonLibraryService() {
		return commonLibraryService;
	}

	/**
	 * Sets the common library remote service.
	 *
	 * @param commonLibraryService the common library remote service
	 */
	public void setCommonLibraryService(
		com.kisti.scienceappstore.service.CommonLibraryService commonLibraryService) {
		this.commonLibraryService = commonLibraryService;
	}

	/**
	 * Returns the common library persistence.
	 *
	 * @return the common library persistence
	 */
	public CommonLibraryPersistence getCommonLibraryPersistence() {
		return commonLibraryPersistence;
	}

	/**
	 * Sets the common library persistence.
	 *
	 * @param commonLibraryPersistence the common library persistence
	 */
	public void setCommonLibraryPersistence(
		CommonLibraryPersistence commonLibraryPersistence) {
		this.commonLibraryPersistence = commonLibraryPersistence;
	}

	/**
	 * Returns the common module local service.
	 *
	 * @return the common module local service
	 */
	public com.kisti.scienceappstore.service.CommonModuleLocalService getCommonModuleLocalService() {
		return commonModuleLocalService;
	}

	/**
	 * Sets the common module local service.
	 *
	 * @param commonModuleLocalService the common module local service
	 */
	public void setCommonModuleLocalService(
		com.kisti.scienceappstore.service.CommonModuleLocalService commonModuleLocalService) {
		this.commonModuleLocalService = commonModuleLocalService;
	}

	/**
	 * Returns the common module remote service.
	 *
	 * @return the common module remote service
	 */
	public com.kisti.scienceappstore.service.CommonModuleService getCommonModuleService() {
		return commonModuleService;
	}

	/**
	 * Sets the common module remote service.
	 *
	 * @param commonModuleService the common module remote service
	 */
	public void setCommonModuleService(
		com.kisti.scienceappstore.service.CommonModuleService commonModuleService) {
		this.commonModuleService = commonModuleService;
	}

	/**
	 * Returns the common module persistence.
	 *
	 * @return the common module persistence
	 */
	public CommonModulePersistence getCommonModulePersistence() {
		return commonModulePersistence;
	}

	/**
	 * Sets the common module persistence.
	 *
	 * @param commonModulePersistence the common module persistence
	 */
	public void setCommonModulePersistence(
		CommonModulePersistence commonModulePersistence) {
		this.commonModulePersistence = commonModulePersistence;
	}

	/**
	 * Returns the common package local service.
	 *
	 * @return the common package local service
	 */
	public com.kisti.scienceappstore.service.CommonPackageLocalService getCommonPackageLocalService() {
		return commonPackageLocalService;
	}

	/**
	 * Sets the common package local service.
	 *
	 * @param commonPackageLocalService the common package local service
	 */
	public void setCommonPackageLocalService(
		com.kisti.scienceappstore.service.CommonPackageLocalService commonPackageLocalService) {
		this.commonPackageLocalService = commonPackageLocalService;
	}

	/**
	 * Returns the common package remote service.
	 *
	 * @return the common package remote service
	 */
	public com.kisti.scienceappstore.service.CommonPackageService getCommonPackageService() {
		return commonPackageService;
	}

	/**
	 * Sets the common package remote service.
	 *
	 * @param commonPackageService the common package remote service
	 */
	public void setCommonPackageService(
		com.kisti.scienceappstore.service.CommonPackageService commonPackageService) {
		this.commonPackageService = commonPackageService;
	}

	/**
	 * Returns the common package persistence.
	 *
	 * @return the common package persistence
	 */
	public CommonPackagePersistence getCommonPackagePersistence() {
		return commonPackagePersistence;
	}

	/**
	 * Sets the common package persistence.
	 *
	 * @param commonPackagePersistence the common package persistence
	 */
	public void setCommonPackagePersistence(
		CommonPackagePersistence commonPackagePersistence) {
		this.commonPackagePersistence = commonPackagePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("com.kisti.scienceappstore.model.CommonPackage",
			commonPackageLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.kisti.scienceappstore.model.CommonPackage");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return CommonPackage.class;
	}

	protected String getModelClassName() {
		return CommonPackage.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = commonPackagePersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.kisti.scienceappstore.service.CommonLibraryLocalService.class)
	protected com.kisti.scienceappstore.service.CommonLibraryLocalService commonLibraryLocalService;
	@BeanReference(type = com.kisti.scienceappstore.service.CommonLibraryService.class)
	protected com.kisti.scienceappstore.service.CommonLibraryService commonLibraryService;
	@BeanReference(type = CommonLibraryPersistence.class)
	protected CommonLibraryPersistence commonLibraryPersistence;
	@BeanReference(type = com.kisti.scienceappstore.service.CommonModuleLocalService.class)
	protected com.kisti.scienceappstore.service.CommonModuleLocalService commonModuleLocalService;
	@BeanReference(type = com.kisti.scienceappstore.service.CommonModuleService.class)
	protected com.kisti.scienceappstore.service.CommonModuleService commonModuleService;
	@BeanReference(type = CommonModulePersistence.class)
	protected CommonModulePersistence commonModulePersistence;
	@BeanReference(type = com.kisti.scienceappstore.service.CommonPackageLocalService.class)
	protected com.kisti.scienceappstore.service.CommonPackageLocalService commonPackageLocalService;
	@BeanReference(type = com.kisti.scienceappstore.service.CommonPackageService.class)
	protected com.kisti.scienceappstore.service.CommonPackageService commonPackageService;
	@BeanReference(type = CommonPackagePersistence.class)
	protected CommonPackagePersistence commonPackagePersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private CommonPackageLocalServiceClpInvoker _clpInvoker = new CommonPackageLocalServiceClpInvoker();
}