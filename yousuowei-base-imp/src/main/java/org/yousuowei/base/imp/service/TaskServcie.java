/**
 * Copyright © 2014 yousuowei. All rights reserved.
 * @Title: TaskServcie.java
 * @Prject: yousuowei-base-imp
 * @Package: org.yousuowei.base.imp.service
 * @Description: TODO
 * @author: jie
 * @date: 2014-2-26 下午5:38:37
 * @version: V1.0
 */

package org.yousuowei.base.imp.service;

import org.springframework.stereotype.Service;
import org.yousuowei.base.ifc.TaskIfc;
import org.yousuowei.base.ifc.TaskInfo;
import org.yousuowei.base.imp.entity.TaskEntity;

/**
 * @ClassName: TaskServcie
 * @Description: 示例接口实现类
 * @author: jie
 * @date: 2014-2-26 下午5:38:37
 */

@Service
public class TaskServcie extends BaseService<TaskEntity, TaskInfo> implements
		TaskIfc {

}
