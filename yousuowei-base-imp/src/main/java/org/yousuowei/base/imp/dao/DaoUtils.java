package org.yousuowei.base.imp.dao;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: DaoUtils
 * @Description: TODO
 * @author: jie
 * @date: 2014-3-5 下午5:29:04
 */
@Repository
public class DaoUtils {

	protected SessionFactory sessionFactory;

	/**
	 * 取得sessionFactory.
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;

	}

	/**
	 * 采用@Resouce按类型注入SessionFactory, 当有多个SesionFactory的时候在子类重载本函数.
	 */
	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 取得当前Session.
	 */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

}
