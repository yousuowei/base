package org.yousuowei.base.imp;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.yousuowei.base.ifc.TaskIfc;
import org.yousuowei.base.ifc.TaskInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:db-context.xml" })
// 加入标记则数据将不添加到数据库
@Transactional
public class BaseServiceTest {

	@Autowired
	private TaskIfc taskService;

	@Test
	public void testAll() {
		TaskInfo task = new TaskInfo();
		task.setName("test");
		Assert.isTrue(taskService.add(task));
		List<TaskInfo> list = taskService.findAll();
		Assert.notEmpty(list);
		task = taskService.read(taskService.findAll().get(0).getId());
		Assert.notNull(task);
		task = taskService.findAll().get(0);
		task.setName("test1");
		Assert.isTrue(taskService.update(task));
		boolean result = taskService.del(taskService.findAll().get(0).getId());
		Assert.isTrue(result);
	}

}
