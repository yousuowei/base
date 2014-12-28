/**
 * Copyright © 2014 yousuowei. All rights reserved.
 * @Title: TaskController.java
 * @Prject: yousuowei-base-web
 * @Package: org.yousuowei.base.web.controller.admin
 * @Description: TODO
 * @author: jie
 * @date: 2014-2-27 下午3:18:21
 * @version: V1.0
 */

package org.yousuowei.base.web.controller.admin;

import java.util.concurrent.Callable;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.yousuowei.base.ifc.TaskInfo;

/**
 * @ClassName: TaskController
 * @Description: 页面访问示例类
 * @author: jie
 * @date: 2014-2-27 下午3:18:21
 */

@Controller
@RequestMapping(value = "/task")
public class TaskController extends BaseController<TaskInfo> {

	@Override
	protected String getViewName() {
		return "task";
	}

	/**
	 * http://localhost:8080/yousuowei-base-web/admin/task/read/123/peths/jie;
	 * q=xialing;r=a,b,c,d
	 * 
	 * @param id
	 * @param taskId
	 * @param taskName
	 * @param taskDiscribe
	 *            使用@MatrixVariable 必须要context.xml 中设置 <mvc:annotation-driven
	 *            enable-matrix-variables="true" />
	 * @param task
	 * @return
	 * @author: jie
	 * @date: 2014-2-28 上午10:41:28
	 */
	@RequestMapping(value = "/testPathVariable/{taskId}/peths/{taskName}", method = RequestMethod.GET)
	@ResponseBody
	public String testPathVariable(
			@PathVariable Long id,
			@PathVariable Long taskId,
			@PathVariable String taskName,
			@MatrixVariable(value = "q", pathVar = "taskName") String taskDiscribe,
			@MatrixVariable(value = "r", pathVar = "taskName") String[] names,
			TaskInfo task) {
		StringBuilder str = new StringBuilder();
		str.append("showForm id=");
		str.append(id);
		str.append(" taskId=");
		str.append(taskId);
		str.append(" taskName=");
		str.append(taskName);
		str.append(" taskDiscribe=");
		str.append(taskDiscribe);
		str.append(" names=");
		str.append(names.length);
		return str.toString();
	}

	@ResponseBody
	@RequestMapping(value = "testReqParams")
	public String testReqParams(@RequestParam String name) {
		return " name=" + name;
	}

	@ResponseBody
	@RequestMapping(value = "testReqAttr", method = RequestMethod.POST)
	public String testReqAttr(@ModelAttribute TaskInfo info,
			BindingResult result) {
		if (result.hasErrors()) {

		}
		return "error count:" + result.getErrorCount();
	}

	// response

	@ResponseBody
	@RequestMapping(value = "testRsp")
	public String testRsp(@CookieValue("CKFinder_Path") String cookie,
			@RequestHeader("Accept-Encoding") String head) {
		return "testRsp cookie:" + cookie + " head:" + head;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String checkPersonInfo(@Validated TaskInfo person,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/page/task/form";
		}
		return "redirect:/page/task/results.html";
	}

	// Asynchronous 多线程
	@RequestMapping(value = "/asyn/page")
	public Callable<String> testAsynPage() {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				return "test";
			}
		};
	}

	@ResponseBody
	@RequestMapping(value = "/asyn/rsp")
	public DeferredResult<String> testAsynRsp() {
		DeferredResult<String> strs = new DeferredResult<String>();

		return strs;

	}

}
