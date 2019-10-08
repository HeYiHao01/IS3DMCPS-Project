package com.jeesite.utils;

public class ProductOutMap {	
	private String productionLineName;
	private int timeVariable;    	
	private double productionCapacity;
	public String getProductionLineName() {
		return productionLineName;
	}
	public void setProductionLineName(String productionLineName) {
		this.productionLineName = productionLineName;
	}
	public int getTimeVariable() {
		return timeVariable;
	}
	public void setTimeVariable(int timeVariable) {
		this.timeVariable = timeVariable;
	}
	public double getProductionCapacity() {
		return productionCapacity;
	}
	public void setProductionCapacity(double productionCapacity) {
		this.productionCapacity = productionCapacity;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProductOutMap) {
			ProductOutMap productOutMap = (ProductOutMap) obj;
			if (productionLineName.equals(productOutMap.getProductionLineName()) && timeVariable == productOutMap.getTimeVariable()
					&& productionCapacity == productOutMap.getProductionCapacity()) {
				return true;
			}			
		}
		return false;
	}
		
}
