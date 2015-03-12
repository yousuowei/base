package org.yousuowei.base.imp.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.reflection.ReflectionUtils;
import org.yousuowei.base.ifc.BaseIfc;
import org.yousuowei.common.utils.Utils;

public class BaseService<E, I> extends GenericService<E> implements BaseIfc<I> {

	private static Logger log = LoggerFactory.getLogger(BaseService.class);

	protected Class<E> infoClass;

	@SuppressWarnings("unchecked")
	public BaseService() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass(),
				0);
		this.infoClass = ReflectionUtils.getSuperClassGenricType(getClass(), 1);
	}

	@Transactional
	public boolean add(I info) {
		try {
			saveNow(infoToEntity(info, true));
			return true;
		} catch (Exception e) {
			log.error("" + e);
			return false;
		}

	}

	@Transactional
	public boolean del(String id) {
		delete(id);
		return true;
	}

	@Transactional
	public boolean update(I info) {
		try {
			saveNow(infoToEntity(info, true));
		} catch (Exception e) {
			log.error("" + e);
			return false;
		}
		return true;
	}

	public I read(String id) {
		try {
			return entityToInfo(load(id), true);
		} catch (Exception e) {
			log.error("" + e);
			return null;
		}
	}

	public List<I> findAll() {
		try {
			return listEntityToInfo(getAll());
		} catch (Exception e) {
			log.error("" + e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Page<I> findPageList(final Page<I> page,
			final List<PropertyFilter> filters) {
		Assert.notNull(page, "page不能为空");
		Criterion[] criterions = buildCriterionByPropertyFilter(filters);
		Criteria c = createCriteria(entityClass, criterions);

		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(c);
			page.setTotalCount(totalCount);
		}

		setPageParameterToCriteria(c, page);

		List<I> result = null;
		try {
			result = listEntityToInfo(c.list());
		} catch (HibernateException e) {
			log.error("" + e);
		} catch (Exception e) {
			log.error("" + e);
		}
		page.setResult(result);
		return page;
	}

	/**
	 * 
	 * @param info
	 * @param concatenation
	 * @return
	 * @throws Exception
	 * @author: jie
	 * @date: 2014-4-29 下午5:41:39
	 */
	public E infoToEntity(I info, boolean concatenation) throws Exception {
		return infoToEntity(info);
	}

	@SuppressWarnings("unchecked")
	public E infoToEntity(I info) {
		E entity = (E) Utils.toObject(entityClass, info);
		return entity;
	}

	/**
	 * 数据库实体类bean和业务实体bean转换方法
	 * 
	 * @param entity
	 * @param concatenation
	 *            是否加载级联（entity里面包含实体类）
	 * @return
	 * @throws Exception
	 * @author: jie
	 * @date: 2014-4-29 下午5:39:46
	 */
	public I entityToInfo(E entity, boolean concatenation) throws Exception {
		return entityToInfo(entity);
	}

	@SuppressWarnings("unchecked")
	public I entityToInfo(E entity) {
		I info = (I) Utils.toObject(infoClass, entity);
		return info;
	}

	public List<I> listEntityToInfo(List<E> list) throws Exception {
		List<I> infoList = null;
		if (null != list && list.size() > 0) {
			infoList = new ArrayList<I>(list.size());
			for (E e : list) {
				infoList.add(entityToInfo(e, true));
			}
		} else {
			infoList = new ArrayList<I>(0);
		}
		return infoList;
	}

}
