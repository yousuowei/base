package org.yousuowei.base.ifc;

import java.util.List;

import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

public interface BaseIfc<I> {
    public boolean add(I info);

    public boolean del(String id);

    public boolean update(I info);

    public I read(String id);

    public boolean isPropertyUnique(final String propertyName,
	    final Object newValue, final Object oldValue);

    public List<I> findAll();

    public Page<I> findPageList(final Page<I> page,
	    final List<PropertyFilter> filters);

}
