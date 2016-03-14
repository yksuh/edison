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

import com.kisti.scienceappstore.NoSuchCommonLibraryException;
import com.kisti.scienceappstore.model.CommonLibrary;
import com.kisti.scienceappstore.model.impl.CommonLibraryImpl;
import com.kisti.scienceappstore.model.impl.CommonLibraryModelImpl;

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
 * The persistence implementation for the common library service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author yksuh
 * @see CommonLibraryPersistence
 * @see CommonLibraryUtil
 * @generated
 */
public class CommonLibraryPersistenceImpl extends BasePersistenceImpl<CommonLibrary>
	implements CommonLibraryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CommonLibraryUtil} to access the common library persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CommonLibraryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CommonLibraryModelImpl.ENTITY_CACHE_ENABLED,
			CommonLibraryModelImpl.FINDER_CACHE_ENABLED,
			CommonLibraryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CommonLibraryModelImpl.ENTITY_CACHE_ENABLED,
			CommonLibraryModelImpl.FINDER_CACHE_ENABLED,
			CommonLibraryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CommonLibraryModelImpl.ENTITY_CACHE_ENABLED,
			CommonLibraryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CommonLibraryPersistenceImpl() {
		setModelClass(CommonLibrary.class);
	}

	/**
	 * Caches the common library in the entity cache if it is enabled.
	 *
	 * @param commonLibrary the common library
	 */
	@Override
	public void cacheResult(CommonLibrary commonLibrary) {
		EntityCacheUtil.putResult(CommonLibraryModelImpl.ENTITY_CACHE_ENABLED,
			CommonLibraryImpl.class, commonLibrary.getPrimaryKey(),
			commonLibrary);

		commonLibrary.resetOriginalValues();
	}

	/**
	 * Caches the common libraries in the entity cache if it is enabled.
	 *
	 * @param commonLibraries the common libraries
	 */
	@Override
	public void cacheResult(List<CommonLibrary> commonLibraries) {
		for (CommonLibrary commonLibrary : commonLibraries) {
			if (EntityCacheUtil.getResult(
						CommonLibraryModelImpl.ENTITY_CACHE_ENABLED,
						CommonLibraryImpl.class, commonLibrary.getPrimaryKey()) == null) {
				cacheResult(commonLibrary);
			}
			else {
				commonLibrary.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all common libraries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CommonLibraryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CommonLibraryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the common library.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommonLibrary commonLibrary) {
		EntityCacheUtil.removeResult(CommonLibraryModelImpl.ENTITY_CACHE_ENABLED,
			CommonLibraryImpl.class, commonLibrary.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CommonLibrary> commonLibraries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommonLibrary commonLibrary : commonLibraries) {
			EntityCacheUtil.removeResult(CommonLibraryModelImpl.ENTITY_CACHE_ENABLED,
				CommonLibraryImpl.class, commonLibrary.getPrimaryKey());
		}
	}

	/**
	 * Creates a new common library with the primary key. Does not add the common library to the database.
	 *
	 * @param commonLibraryPK the primary key for the new common library
	 * @return the new common library
	 */
	@Override
	public CommonLibrary create(CommonLibraryPK commonLibraryPK) {
		CommonLibrary commonLibrary = new CommonLibraryImpl();

		commonLibrary.setNew(true);
		commonLibrary.setPrimaryKey(commonLibraryPK);

		return commonLibrary;
	}

	/**
	 * Removes the common library with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commonLibraryPK the primary key of the common library
	 * @return the common library that was removed
	 * @throws com.kisti.scienceappstore.NoSuchCommonLibraryException if a common library with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CommonLibrary remove(CommonLibraryPK commonLibraryPK)
		throws NoSuchCommonLibraryException, SystemException {
		return remove((Serializable)commonLibraryPK);
	}

	/**
	 * Removes the common library with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the common library
	 * @return the common library that was removed
	 * @throws com.kisti.scienceappstore.NoSuchCommonLibraryException if a common library with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CommonLibrary remove(Serializable primaryKey)
		throws NoSuchCommonLibraryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			CommonLibrary commonLibrary = (CommonLibrary)session.get(CommonLibraryImpl.class,
					primaryKey);

			if (commonLibrary == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCommonLibraryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(commonLibrary);
		}
		catch (NoSuchCommonLibraryException nsee) {
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
	protected CommonLibrary removeImpl(CommonLibrary commonLibrary)
		throws SystemException {
		commonLibrary = toUnwrappedModel(commonLibrary);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commonLibrary)) {
				commonLibrary = (CommonLibrary)session.get(CommonLibraryImpl.class,
						commonLibrary.getPrimaryKeyObj());
			}

			if (commonLibrary != null) {
				session.delete(commonLibrary);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (commonLibrary != null) {
			clearCache(commonLibrary);
		}

		return commonLibrary;
	}

	@Override
	public CommonLibrary updateImpl(
		com.kisti.scienceappstore.model.CommonLibrary commonLibrary)
		throws SystemException {
		commonLibrary = toUnwrappedModel(commonLibrary);

		boolean isNew = commonLibrary.isNew();

		Session session = null;

		try {
			session = openSession();

			if (commonLibrary.isNew()) {
				session.save(commonLibrary);

				commonLibrary.setNew(false);
			}
			else {
				session.merge(commonLibrary);
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

		EntityCacheUtil.putResult(CommonLibraryModelImpl.ENTITY_CACHE_ENABLED,
			CommonLibraryImpl.class, commonLibrary.getPrimaryKey(),
			commonLibrary);

		return commonLibrary;
	}

	protected CommonLibrary toUnwrappedModel(CommonLibrary commonLibrary) {
		if (commonLibrary instanceof CommonLibraryImpl) {
			return commonLibrary;
		}

		CommonLibraryImpl commonLibraryImpl = new CommonLibraryImpl();

		commonLibraryImpl.setNew(commonLibrary.isNew());
		commonLibraryImpl.setPrimaryKey(commonLibrary.getPrimaryKey());

		commonLibraryImpl.setGroupId(commonLibrary.getGroupId());
		commonLibraryImpl.setCompanyId(commonLibrary.getCompanyId());
		commonLibraryImpl.setUserId(commonLibrary.getUserId());
		commonLibraryImpl.setUserName(commonLibrary.getUserName());
		commonLibraryImpl.setCreateDate(commonLibrary.getCreateDate());
		commonLibraryImpl.setModifiedDate(commonLibrary.getModifiedDate());
		commonLibraryImpl.setLibName(commonLibrary.getLibName());
		commonLibraryImpl.setCLibVer(commonLibrary.getCLibVer());
		commonLibraryImpl.setSysArch(commonLibrary.getSysArch());
		commonLibraryImpl.setKernelVer(commonLibrary.getKernelVer());
		commonLibraryImpl.setLibPath(commonLibrary.getLibPath());

		return commonLibraryImpl;
	}

	/**
	 * Returns the common library with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the common library
	 * @return the common library
	 * @throws com.kisti.scienceappstore.NoSuchCommonLibraryException if a common library with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CommonLibrary findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCommonLibraryException, SystemException {
		CommonLibrary commonLibrary = fetchByPrimaryKey(primaryKey);

		if (commonLibrary == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCommonLibraryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return commonLibrary;
	}

	/**
	 * Returns the common library with the primary key or throws a {@link com.kisti.scienceappstore.NoSuchCommonLibraryException} if it could not be found.
	 *
	 * @param commonLibraryPK the primary key of the common library
	 * @return the common library
	 * @throws com.kisti.scienceappstore.NoSuchCommonLibraryException if a common library with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CommonLibrary findByPrimaryKey(CommonLibraryPK commonLibraryPK)
		throws NoSuchCommonLibraryException, SystemException {
		return findByPrimaryKey((Serializable)commonLibraryPK);
	}

	/**
	 * Returns the common library with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the common library
	 * @return the common library, or <code>null</code> if a common library with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CommonLibrary fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		CommonLibrary commonLibrary = (CommonLibrary)EntityCacheUtil.getResult(CommonLibraryModelImpl.ENTITY_CACHE_ENABLED,
				CommonLibraryImpl.class, primaryKey);

		if (commonLibrary == _nullCommonLibrary) {
			return null;
		}

		if (commonLibrary == null) {
			Session session = null;

			try {
				session = openSession();

				commonLibrary = (CommonLibrary)session.get(CommonLibraryImpl.class,
						primaryKey);

				if (commonLibrary != null) {
					cacheResult(commonLibrary);
				}
				else {
					EntityCacheUtil.putResult(CommonLibraryModelImpl.ENTITY_CACHE_ENABLED,
						CommonLibraryImpl.class, primaryKey, _nullCommonLibrary);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(CommonLibraryModelImpl.ENTITY_CACHE_ENABLED,
					CommonLibraryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return commonLibrary;
	}

	/**
	 * Returns the common library with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commonLibraryPK the primary key of the common library
	 * @return the common library, or <code>null</code> if a common library with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public CommonLibrary fetchByPrimaryKey(CommonLibraryPK commonLibraryPK)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)commonLibraryPK);
	}

	/**
	 * Returns all the common libraries.
	 *
	 * @return the common libraries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<CommonLibrary> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the common libraries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.scienceappstore.model.impl.CommonLibraryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of common libraries
	 * @param end the upper bound of the range of common libraries (not inclusive)
	 * @return the range of common libraries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<CommonLibrary> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the common libraries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.kisti.scienceappstore.model.impl.CommonLibraryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of common libraries
	 * @param end the upper bound of the range of common libraries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of common libraries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<CommonLibrary> findAll(int start, int end,
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

		List<CommonLibrary> list = (List<CommonLibrary>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_COMMONLIBRARY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_COMMONLIBRARY;

				if (pagination) {
					sql = sql.concat(CommonLibraryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CommonLibrary>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<CommonLibrary>(list);
				}
				else {
					list = (List<CommonLibrary>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the common libraries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (CommonLibrary commonLibrary : findAll()) {
			remove(commonLibrary);
		}
	}

	/**
	 * Returns the number of common libraries.
	 *
	 * @return the number of common libraries
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

				Query q = session.createQuery(_SQL_COUNT_COMMONLIBRARY);

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
	 * Initializes the common library persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.kisti.scienceappstore.model.CommonLibrary")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<CommonLibrary>> listenersList = new ArrayList<ModelListener<CommonLibrary>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<CommonLibrary>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(CommonLibraryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_COMMONLIBRARY = "SELECT commonLibrary FROM CommonLibrary commonLibrary";
	private static final String _SQL_COUNT_COMMONLIBRARY = "SELECT COUNT(commonLibrary) FROM CommonLibrary commonLibrary";
	private static final String _ORDER_BY_ENTITY_ALIAS = "commonLibrary.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CommonLibrary exists with the primary key ";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CommonLibraryPersistenceImpl.class);
	private static CommonLibrary _nullCommonLibrary = new CommonLibraryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<CommonLibrary> toCacheModel() {
				return _nullCommonLibraryCacheModel;
			}
		};

	private static CacheModel<CommonLibrary> _nullCommonLibraryCacheModel = new CacheModel<CommonLibrary>() {
			@Override
			public CommonLibrary toEntityModel() {
				return _nullCommonLibrary;
			}
		};
}