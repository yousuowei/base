/**
 * Copyright © 2014 yousuowei. All rights reserved.
 * @Title: BaseApi.java
 * @Prject: yousuowei-base-web
 * @Package: org.yousuowei.base.web.controller.admin
 * @Description: TODO
 * @author: jie
 * @date: 2014-9-20 下午6:15:02
 * @version: V1.0
 */

package org.yousuowei.base.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.yousuowei.base.ifc.BaseIfc;

/**
 * @ClassName: BaseApi
 * @Description: TODO
 * @author: jie
 * @date: 2014-9-20 下午6:15:02
 */
public class BaseApi<T> {
    @Autowired
    protected BaseIfc<T> service;

    public void setService(BaseIfc<T> service) {
	this.service = service;
    }

}
