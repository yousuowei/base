/**
 * Copyright © 2014 yousuowei. All rights reserved.
 * @Title: JsonInfo.java
 * @Prject: yousuowei-base-web
 * @Package: org.yousuowei.base.web.controller
 * @Description: TODO
 * @author: jie
 * @date: 2014-9-20 下午6:17:13
 * @version: V1.0
 */

package org.yousuowei.base.web.controller;

import org.yousuowei.base.ifc.info.BaseInfo;

/**
 * @ClassName: JsonInfo
 * @Description: TODO
 * @author: jie
 * @date: 2014-9-20 下午6:17:13
 */

public class JsonInfo {
    private int status;
    private int errorCode;
    private BaseInfo info;

    public int getStatus() {
	return status;
    }

    public void setStatus(int status) {
	this.status = status;
    }

    public int getErrorCode() {
	return errorCode;
    }

    public void setErrorCode(int errorCode) {
	this.errorCode = errorCode;
    }

    public BaseInfo getInfo() {
	return info;
    }

    public void setInfo(BaseInfo info) {
	this.info = info;
    }

}
