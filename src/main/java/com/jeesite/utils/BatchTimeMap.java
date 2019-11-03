package com.jeesite.utils;

public class BatchTimeMap {
	private String batch;
	private double weight;
	private int timeCost;
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getTimeCost() {
		return timeCost;
	}
	public void setTimeCost(int timeCost) {
		this.timeCost = timeCost;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BatchTimeMap) {
			BatchTimeMap batchTimeMap = (BatchTimeMap) obj;
			if (batch.equals(batchTimeMap.getBatch()) && weight == batchTimeMap.getWeight()
					&& timeCost == batchTimeMap.getTimeCost()) {
				return true;
			}			
		}
		return false;
	}
}
