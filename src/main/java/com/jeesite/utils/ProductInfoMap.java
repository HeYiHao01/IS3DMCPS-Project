package com.jeesite.utils;

public class ProductInfoMap {
	private String brand;
	private int finishWeight;
	private int timeVariable;
	private double totalWeightThis;
	private double totalWeightLast;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getFinishWeight() {
		return finishWeight;
	}
	public void setFinishWeight(int finishWeight) {
		this.finishWeight = finishWeight;
	}
	public int getTimeVariable() {
		return timeVariable;
	}
	public void setTimeVariable(int timeVariable) {
		this.timeVariable = timeVariable;
	}
	public double getTotalWeightThis() {
		return totalWeightThis;
	}
	public void setTotalWeightThis(double totalWeightThis) {
		this.totalWeightThis = totalWeightThis;
	}
	public double getTotalWeightLast() {
		return totalWeightLast;
	}
	public void setTotalWeightLast(double totalWeightLast) {
		this.totalWeightLast = totalWeightLast;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProductInfoMap) {
			ProductInfoMap productInfoMap = (ProductInfoMap) obj;
			if (brand.equals(productInfoMap.getBrand()) && finishWeight == productInfoMap.getFinishWeight()
					&& timeVariable == productInfoMap.getTimeVariable() && totalWeightThis == productInfoMap.getTotalWeightThis()
					&& totalWeightLast == productInfoMap.getTotalWeightLast()) {
				return true;
			}			
		}
		return false;
	}
		
}
