package org.activiti;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

public class MyUnitTest {
	
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	
	
	@Test
	@Deployment(resources = {"org/activiti/test/my-process.bpmn20.xml"})
	public void testAsync() throws InterruptedException {
		assertNotNull(activitiRule.getRuntimeService());
		ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
		assertNotNull(processInstance);
		
		// Should be at the single User Task
		Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
		System.out.println("Current task: " + task);
		assertEquals("Activiti is awesome!", task.getName());
		activitiRule.getTaskService().complete(task.getId());
	
		// Wait for MySleepClass to complete
		Thread.sleep(15000);

		// Should have no executions
		List<Execution> ee = activitiRule.getRuntimeService().createExecutionQuery().processInstanceId(processInstance.getId()).list();
		System.out.println("Executions: " + ee);
		assertEquals(0, ee.size());
	}

}
