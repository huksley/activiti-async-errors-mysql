package org.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class MySleepClass implements JavaDelegate {

	@Override
	public void execute(DelegateExecution e) throws Exception {
		System.out.println("MySleepClass start!");
		Thread.sleep(10000);
		System.out.println("MySleepClass done!");
	}
}
