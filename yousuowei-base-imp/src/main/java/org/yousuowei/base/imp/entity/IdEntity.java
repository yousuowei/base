package org.yousuowei.base.imp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * 统一定义id的entity基类. 基类统一定义id的属性名称、数据类型、列名映射及生成策略. 子类可重载getId()函数重定义id的列名映射和生成策略.
 * 
 * @author WEIJL
 */
// JPA 基类的标识
@MappedSuperclass
public abstract class IdEntity implements Serializable {

	private static final long serialVersionUID = -3040157230048683502L;
	protected String id;

	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 36, unique = true)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdEntity other = (IdEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
