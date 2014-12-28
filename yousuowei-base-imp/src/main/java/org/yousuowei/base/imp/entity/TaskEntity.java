/**
 * Copyright © 2014 yousuowei. All rights reserved.
 * @Title: TaskEntity.java
 * @Prject: yousuowei-base-imp
 * @Package: org.yousuowei.base.imp.entity
 * @Description: TODO
 * @author: jie
 * @date: 2014-2-26 下午5:18:48
 * @version: V1.0
 */

package org.yousuowei.base.imp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @ClassName: TaskEntity
 * @Description: base示例
 * @author: jie
 * @date: 2014-2-26 下午5:18:48
 */
@Entity
@Table(name = "t_task")
public class TaskEntity extends BaseEntity {

	private static final long serialVersionUID = 4580441965284054394L;

	private String name;

	@Column(name = "t_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
