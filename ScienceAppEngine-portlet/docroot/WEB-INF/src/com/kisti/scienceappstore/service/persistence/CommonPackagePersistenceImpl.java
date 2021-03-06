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

import com.kisti.scienceappstore.NoSuchCommonPackageException;
import com.kisti.scienceappstore.model.CommonPackage;
import com.kisti.scienceappstore.model.impl.CommonPackageImpl;
import com.kisti.scienceappstore.model.impl.CommonPackageModelImpl;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the common package service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author yksuh
 * @see CommonPackagePersistence
 * @see CommonPackageUtil
 * @generated
 */
public class CommonPackagePersistenceImpl extends BasePersistenceImpl<CommonPackage>
	implements CommonPackagePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommonPackageUtil} to access the common package persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommonPackageImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommonPackageModelImpl.ENTITY_CACHE_ENABLED,
			CommonPackageModelImpl.FINDER_CACHE_ENABLED,
			CommonPackageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommonPackageModelImpl.ENTITY_CACHE_ENABLED,
			CommonPackageModelImpl.FINDER_CACHE_ENABLED,
			CommonPackageImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommonPackageModelImpl.ENTITY_CACHE_ENABLED,
			CommonPackageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CommonPackagePersistenceImpl() {
		setModelClass(CommonPackage.class);
	}

	/**
	 * Caches the common package in the entity cache if it is enabled.
	 *
	 * @param commonPackage the common package
	 */
	@Override
	public void cacheResult(CommonPackage commonPackage) {
		EntityCacheUtil.putResult(CommonPackageModelImpl.ENTITY_CACHE_ENABLED,
			CommonPackageImpl.class, commonPackage.getPrimaryKey(),
			commonPackage);

		commonPackage.resetOriginalValues();
	}

	/**
	 * Caches the common packages in the entity cache if it is enabled.
	 *
	 * @param commonPackages the common packages
	 */
	@Override
	public void cacheResult(List<CommonPackage> commonPackages) {
		for (CommonPackage commonPackage : commonPackages) {
			if (EntityCacheUtil.getResult(
						CommonPackageModelImpl.ENTITY_CACHE_ENABLED,
						CommonPackageImpl.class, commonPackage.getPrimaryKey()) == null) {
				cacheResult(commonPackage);
			}
			else {
				commonPackage.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all common packages.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CommonPackageImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CommonPackageImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the common package.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommonPackage commonPackage) {
		EntityCacheUtil.removeResult(CommonPackageModelImpl.ENTITY_CACHE_ENABLED,
			CommonPackageImpl.class, commonPackage.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CommonPackage> commonPackages) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommonPackage commonPackage : commonPackages) {
			EntityCacheUtil.removeResult(CommonPackageModelImpl.ENTITY_CACHE_ENABLED,
				CommonPackageImpl.class, commonPackage.getPrimaryKey());
		}
	}

	/**
	 * Creates a new common package with the primary key. Does not add the common package to the database.
	 *
	 * @param commonPackagePK the primary key for the new common package
	 * @return the new common package
	 */
	@Override
	public CommonPackage create(CommonPackagePK commonPackagePK) {
		CommonPackage commonPackage = new CommonPackageImpl();

		commonPackage.setNew(true);
		commonPackage.setPrimaryKey(commonPackagePK);

		return commonPackage;
	}

	/**
	 * Removes the common package with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commonPackagePK the primary key of the common package
	 * @return the common package that was removed
	 * @throws com.kisti.scienceappstore.NoSuchCommonPackageException if a common package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CommonPackage remove(CommonPackagePK commonPackagePK)
		throws NoSuchCommonPackageException, SystemException {
		return remove((Serializable)commonPackagePK);
	}

	/**
	 * Removes the common package with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the common package
	 * @return the common package that was removed
	 * @throws com.kisti.scienceappstore.NoSuchCommonPackageException if a common package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CommonPackage remove(Serializable primaryKey)
		throws NoSuchCommonPackageException, SystemException {
		Session session = null;

		try {
			session = openSession();

			CommonPackage commonPackage = (CommonPackage)session.get(CommonPackageImpl.class,
					primaryKey);

			if (commonPackage == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCommonPackageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commonPackage);
		}
		catch (NoSuchCommonPackageException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CommonPackage removeImpl(CommonPackage commonPackage)
		throws SystemException {
		commonPackage = toUnwrappedModel(commonPackage);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commonPackage)) {
				commonPackage = (CommonPackage)session.get(CommonPackageImpl.class,
						commonPackage.getPrimaryKeyObj());
			}

			if (commonPackage != null) {
				session.delete(commonPackage);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commonPackage != null) {
			clearCache(commonPackage);
		}

		return commonPackage;
	}

	@Override
	public CommonPackage updateImpl(
		com.kisti.scienceappstore.model.CommonPackage commonPackage)
		throws SystemException {
		commonPackage = toUnwrappedModel(commonPackage);

		boolean isNew = commonPackage.isNew();

		Session session = null;

		try {
			session = openSession();

			if (commonPackage.isNew()) {
				session.save(commonPackage);

				commonPackage.setNew(false);
			}
			else {
				session.merge(commonPackage);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(CommonPackageModelImpl.ENTITY_CACHE_ENABLED,
			CommonPackageImpl.class, commonPackage.getPrimaryKey(),
			commonPackage);

		return commonPackage;
	}

	protected CommonPackage toUnwrappedModel(CommonPackage commonPackage) {
		if (commonPackage instanceof CommonPackageImpl) {
			return commonPackage;
		}

		CommonPackageImpl commonPackageImpl = new CommonPackageImpl();

		commonPackageImpl.setNew(commonPackage.isNew());
		commonPackageImpl.setPrimaryKey(commonPackage.getPrimaryKey());

		commonPackageImpl.setGroupId(commonPackage.getGroupId());
		commonPackageImpl.setCompanyId(commonPackage.getCompanyId());
		commonPackageImpl.setUserId(commonPackage.getUserId());
		commonPackageImpl.setUserName(commonPackage.getUserName());
		commonPackageImpl.setCreateDate(commonPackage.getCreateDate());
		commonPackageImpl.setModifiedDate(commonPackage.getModifiedDate());
		commonPackageImpl.setPkgName(commonPackage.getPkgName());
		commonPackageImpl.setPkgVersion(commonPackage.getPkgVersion());
		commonPackageImpl.setSysArch(commonPackage.getSysArch());
		commonPackageImpl.setInstallMethod(commonPackage.getInstallMethod());
		commonPackageImpl.setInstallPath(commonPackage.getInstallPath());

		return commonPackageImpl;
	}

	/**
	 * Returns the common package with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the common package
	 * @return the common package
	 * @throws com.kisti.scienceappstore.NoSuchCommonPackageException if a common package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CommonPackage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCommonPackageException, SystemException {
		CommonPackage commonPackage = fetchByPrimaryKey(primaryKey);

		if (commonPackage == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCommonPackageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commonPackage;
	}

	/**
	 * Returns the common package with the primary key or throws a {@link com.kisti.scienceappstore.NoSuchCommonPackageException} if it could not be found.
	 *
	 * @param commonPackagePK the primary key of the common package
	 * @return the common package
	 * @throws com.kisti.scienceappstore.NoSuchCommonPackageException if a common package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CommonPackage findByPrimaryKey(CommonPackagePK commonPackagePK)
		throws NoSuchCommonPackageException, SystemException {
		return findByPrimaryKey((Serializable)commonPackagePK);
	}

	/**
	 * Returns the common package with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the common package
	 * @return the common package, or <code>null</code> if a common package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CommonPackage fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		CommonPackage commonPackage = (CommonPackage)EntityCacheUtil.getResult(CommonPackageModelImpl.ENTITY_CACHE_ENABLED,
				CommonPackageImpl.class, primaryKey);

		if (commonPackage == _nullCommonPackage) {
			return null;
		}

		if (commonPackage == null) {
			Session session = null;

			try {
				session = openSession();

				commonPackage = (CommonPackage)session.get(CommonPackageImpl.class,
						primaryKey);

				if (commonPackage != null) {
					cacheResult(commonPackage);
				}
				else {
					EntityCacheUtil.putResult(CommonPackageModelImpl.ENTITY_CACHE_ENABLED,
						CommonPackageImpl.class, primaryKey, _nullCommonPackage);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(CommonPackageModelImpl.ENTITY_CACHE_ENABLED,
					CommonPackageImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commonPackage;
	}

	/**
	 * Returns the common package with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commonPackagePK the primary key of the common package
	 * @return the common package, or <code>null</code> if a common package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CommonPackage fetchByPrimaryKey(CommonPackagePK commonPackagePK)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)commonPackagePK);
	}

	/**
	 * Returns all the common packages.
	 *
	 * @return the common packages
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<CommonPackage> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<CommonPackage> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	@Override
	public List<CommonPackage> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<CommonPackage> list = (List<CommonPackage>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_COMMONPACKAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMONPACKAGE;

				if (pagination) {
					sql = sql.concat(CommonPackageModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommonPackage>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<CommonPackage>(list);
				}
				else {
					list = (List<CommonPackage>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the common packages from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (CommonPackage commonPackage : findAll()) {
			remove(commonPackage);
		}
	}

	/**
	 * Returns the number of common packages.
	 *
	 * @return the number of common packages
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_COMMONPACKAGE);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the common package persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.kisti.scienceappstore.model.CommonPackage")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<CommonPackage>> listenersList = new ArrayList<ModelListener<CommonPackage>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<CommonPackage>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(CommonPackageImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_COMMONPACKAGE = "SELECT commonPackage FROM CommonPackage commonPackage";
	private static final String _SQL_COUNT_COMMONPACKAGE = "SELECT COUNT(commonPackage) FROM CommonPackage commonPackage";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commonPackage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommonPackage exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CommonPackagePersistenceImpl.class);
	private static CommonPackage _nullCommonPackage = new CommonPackageImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<CommonPackage> toCacheModel() {
				return _nullCommonPackageCacheModel;
			}
		};

	private static CacheModel<CommonPackage> _nullCommonPackageCacheModel = new CacheModel<CommonPackage>() {
			@Override
			public CommonPackage toEntityModel() {
				return _nullCommonPackage;
			}
		};
}