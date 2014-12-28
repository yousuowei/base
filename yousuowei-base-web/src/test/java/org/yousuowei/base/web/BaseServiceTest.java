package org.yousuowei.base.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.yousuowei.base.ifc.TaskIfc;
import org.yousuowei.base.ifc.TaskInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:db-context.xml" })
//加入标记则数据将不添加到数据库
@Transactional
public class BaseServiceTest {

	@Autowired
	private TaskIfc taskService;

	@Test
	public void testAdd() {
		TaskInfo info = new TaskInfo();
		taskService.add(info);
	}
}
