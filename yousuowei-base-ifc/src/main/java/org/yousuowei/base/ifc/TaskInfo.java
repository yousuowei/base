/**
 * Copyright © 2014 yousuowei. All rights reserved.
 * @Title: TaskInfo.java
 * @Prject: yousuowei-base-ifc
 * @Package: org.yousuowei.base.ifc
 * @Description: TODO
 * @author: jie
 * @date: 2014-2-26 下午5:36:53
 * @version: V1.0
 */

package org.yousuowei.base.ifc;

import org.yousuowei.base.ifc.info.BaseInfo;

/**
 * @ClassName: TaskInfo
 * @Description: 示例model类
 * @author: jie
 * @date: 2014-2-26 下午5:36:53
 */

public class TaskInfo extends BaseInfo{

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
