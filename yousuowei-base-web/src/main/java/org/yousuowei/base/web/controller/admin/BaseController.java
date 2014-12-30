package org.yousuowei.base.web.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.reflection.ReflectionUtils;
import org.yousuowei.base.ifc.BaseIfc;

/**
 * 所有Action业务基类 ：提供公共的基本的增删改查（使用restful url架构） 根据method区分 add:post delete:delete
 * update:put read:get
 * 
 * @ClassName: BaseController
 * @Description: TODO
 * @author: jie
 * @date: 2014-3-3 下午4:24:04
 */
public abstract class BaseController<T> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BaseIfc<T> service;

    public void setService(BaseIfc<T> service) {
	this.service = service;
    }

    private static final int PAGE_NUM = 15;// 每页显示数目

    protected static final String PAGE_RETURN_LIST = "redirect:list";
    protected static final String PAGE_RETURN_SUFFIX_READ = "-read";
    protected static final String PAGE_RETURN_SUFFIX_LIST = "-list";

    protected static final String PAGE_RETURN_MODEL = "model";
    protected static final String PAGE_RETURN_PAGE = "page";

    protected static final String TAG_READ_ID_NULL = "NULL";

    protected abstract String getViewName();

    /**
     * Action函数,显示Entity列表界面. 相当与菜单模块的权限
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(Page<T> page, HttpServletRequest req,
	    ModelAndView model) throws Exception {
	List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(req);
	if (null == page) {
	    page = new Page<T>(PAGE_NUM);
	} else {
	    page.setPageSize(PAGE_NUM);
	}

	// 设置默认排序方式
	if (!page.isOrderBySetted()) {
	    page.setOrderBy("id");
	    page.setOrder(Page.ASC);
	}
	page = service.findPageList(page, filters);
	model.addObject(PAGE_RETURN_PAGE, page);
	model.setViewName(getViewName() + PAGE_RETURN_SUFFIX_LIST);
	return model;
    }

    /**
     * 等同于prepare()的内部函数,供prepardMethodName()函数调用.
     */
    @SuppressWarnings("unchecked")
    protected T prepareModel(String id) throws Exception {
	T model = null;
	if (StringUtils.isNotEmpty(id)
		&& !TAG_READ_ID_NULL.equalsIgnoreCase(id)) {
	    model = service.read(id);
	} else {
	    model = (T) ReflectionUtils.getSuperClassGenricType(getClass())
		    .newInstance();
	}
	return model;
    }

    /**
     * Action函数,显示新增或修改Entity界面. 被create,update,delete隐含 建议return
     * BaseAction.OPER_TYPE_READ
     */
    @RequestMapping(value = { "/{id}", "/read/{id}" }, method = RequestMethod.GET)
    public ModelAndView read(@PathVariable String id, ModelAndView model)
	    throws Exception {
	model.addObject(PAGE_RETURN_MODEL, prepareModel(id));
	model.setViewName(getViewName() + PAGE_RETURN_SUFFIX_READ);
	return model;
    }

    /**
     * Action函数,新增Entity 隐含read权限 建议return BaseAction.OPER_TYPE_LIST.
     */
    @RequestMapping(value = { "/add", "/" }, method = RequestMethod.POST)
    public String create(T model) throws Exception {
	service.add(model);
	return PAGE_RETURN_LIST;
    }

    /**
     * Action函数,修改Entity. 隐含read权限 建议return BaseAction.OPER_TYPE_LIST
     */
    @RequestMapping(value = { "/update", "/" }, method = RequestMethod.PUT)
    public String update(T model) throws Exception {
	service.update(model);
	return PAGE_RETURN_LIST;
    }

    /**
     * Action函数,删除Entity. 隐含read权限 建议return BaseAction.OPER_TYPE_LIST.
     */
    @RequestMapping(value = { "/del", "/" }, method = RequestMethod.DELETE)
    public String delete(@RequestParam String id) throws Exception {
	service.del(id);
	return PAGE_RETURN_LIST;
    }

    @RequestMapping(value = { "/del/{id}", "/" }, method = RequestMethod.GET)
    public String deleteByGet(@PathVariable String id) throws Exception {
	service.del(id);
	return PAGE_RETURN_LIST;
    }

    @ResponseBody
    @RequestMapping(value = { "/checkUnique" }, method = RequestMethod.GET)
    public String checkUniqueProperty(@RequestParam String property,
	    @RequestParam String oldValue, HttpServletRequest req) {
	String newValue = req.getParameter(property);

	String result = String.valueOf(service.isPropertyUnique(property,
		newValue, oldValue));
	return result;
    }

}
