package com.jeesite.modules.isopc.entity;

/**
 * 小车运行统计结果实体类
 * @author HAO
 *
 */
public class CarCount {
	private String timeVariable;
	private int taskTimeCount;
	public String getTimeVariable() {
		return timeVariable;
	}
	public void setTimeVariable(String timeVariable) 
	{
		this.timeVariable = timeVariable;
	}
	public int getTaskTimeCount() {
		return taskTimeCount;
	}
	public void setTaskTimeCount(int taskTimeCount) {
		this.taskTimeCount = taskTimeCount;
	}	
}
