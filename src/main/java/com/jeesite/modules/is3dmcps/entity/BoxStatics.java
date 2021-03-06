package com.jeesite.modules.is3dmcps.entity;

public class BoxStatics {
	private int lineNum;	
	private int line;
	private int lie;
	private int layer;
	private Double weight;
	private Double locationX;
	private Double locationY;
	private Double locationZ;
	private String boxNum;
	private String vplnum;
	private String pltnum;
	private String currloc;
	private String itemdesc;
	private String lotnum;
	private String enterdate;
	public int getLineNum() {
		return lineNum;
	}
	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public int getLie() {
		return lie;
	}
	public void setLie(int lie) {
		this.lie = lie;
	}
	public int getLayer() {
		return layer;
	}
	public void setLayer(int layer) {
		this.layer = layer;
	}
	public Double getLocationX() {
		return locationX;
	}
	public void setLocationX(Double locationX) {
		this.locationX = locationX;
	}
	public Double getLocationY() {
		return locationY;
	}
	public void setLocationY(Double locationY) {
		this.locationY = locationY;
	}
	public Double getLocationZ() {
		return locationZ;
	}
	public void setLocationZ(Double locationZ) {
		this.locationZ = locationZ;
	}
	public String getVplnum() {
		return vplnum;
	}
	public void setVplnum(String vplnum) {
		this.vplnum = vplnum;
	}
	public String getPltnum() {
		return pltnum;
	}
	public void setPltnum(String pltnum) {
		this.pltnum = pltnum;
	}
	public String getCurrloc() {
		return currloc;
	}
	public void setCurrloc(String currloc) {
		this.currloc = currloc;
	}
	public String getItemdesc() {
		return itemdesc;
	}
	public void setItemdesc(String itemdesc) {
		this.itemdesc = itemdesc;
	}
	public String getLotnum() {
		return lotnum;
	}
	public void setLotnum(String lotnum) {
		this.lotnum = lotnum;
	}
	public String getEnterdate() {
		return enterdate;
	}
	public void setEnterdate(String enterdate) {
		this.enterdate = enterdate;
	}
	public String getBoxNum() {
		return boxNum;
	}
	public void setBoxNum(String boxNum) {
		this.boxNum = boxNum;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BoxStatics) {
			BoxStatics boxStatics = (BoxStatics) obj;
			if (line == boxStatics.getLine() && lie == boxStatics.getLie() && layer == boxStatics.getLayer()
					&& locationX == boxStatics.getLocationX() && locationY == boxStatics.getLocationY()
					&& locationZ == boxStatics.getLocationZ() && lotnum.equals(boxStatics.getLotnum())
					&& vplnum.equals(boxStatics.getVplnum()) && itemdesc.equals(boxStatics.getItemdesc())
					&& currloc.equals(boxStatics.getCurrloc()) && enterdate.equals(boxStatics.getEnterdate())) {
				return true;
			}
		}
		return false;
	}		
}
