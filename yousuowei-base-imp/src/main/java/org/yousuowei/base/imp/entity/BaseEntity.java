package org.yousuowei.base.imp.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Copyright (C), 2011-2011, Shanghai Atlas Tiger Medical Information System Co.,Ltd.
 * @author jie
 *
 * 创建时间：2012-2-24 下午03:37:31
 * 类的说明：实体类基类
 */

/**
 * 最后修改人：WEIJL 最后修改时间：2012-2-24 下午03:37:31 最后修改内容：新建
 */
@SuppressWarnings("serial")
public class BaseEntity extends IdEntity {

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				CustomerDefaultToStringStype.CUSTOMER_DEFAULT_STYLE);
	}

}
