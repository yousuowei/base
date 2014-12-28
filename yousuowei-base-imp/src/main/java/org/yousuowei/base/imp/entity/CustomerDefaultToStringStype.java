package org.yousuowei.base.imp.entity;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.builder.ToStringStyle;

@SuppressWarnings("serial")
public class CustomerDefaultToStringStype extends ToStringStyle {
	public static final ToStringStyle CUSTOMER_DEFAULT_STYLE = new CustomerDefaultToStringStype();

	/**
	 * 构造器
	 */
	CustomerDefaultToStringStype() {
		super();
		this.setUseShortClassName(true);
		this.setFieldNameValueSeparator(":");
		this.setDefaultFullDetail(true);
		this.setContentStart("[");
		this.setFieldSeparator(SystemUtils.LINE_SEPARATOR + "  ");
		this.setFieldSeparatorAtStart(true);
		this.setContentEnd(SystemUtils.LINE_SEPARATOR + "]");
	}

	/**
	 * 确保单太实例
	 */
	private Object readResolve() {
		return CustomerDefaultToStringStype.CUSTOMER_DEFAULT_STYLE;
	}

}
