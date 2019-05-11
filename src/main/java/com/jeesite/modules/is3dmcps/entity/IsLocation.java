/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.entity;


import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * is_locationEntity
 * @author xx
 * @version 2019-05-11
 */
@Table(name="is_location", alias="a", columns={
		@Column(name="location_id", attrName="locationId", label="location_id", isPK=true),
		@Column(name="location_x", attrName="locationX", label="location_x"),
		@Column(name="location_y", attrName="locationY", label="location_y"),
		@Column(name="location_z", attrName="locationZ", label="location_z"),
		@Column(name="roww", attrName="roww", label="roww"),
		@Column(name="col", attrName="col", label="col"),
		@Column(name="layerr", attrName="layerr", label="layerr"),
	}, orderBy="a.location_id DESC"
)
public class IsLocation extends DataEntity<IsLocation> {
	
	private static final long serialVersionUID = 1L;
	private Integer locationId;		// location_id
	private Double locationX;		// location_x
	private Double locationY;		// location_y
	private Double locationZ;		// location_z
	private Integer roww;		// roww
	private Integer col;		// col
	private Integer layerr;		// layerr
	
	public IsLocation() {
		this(null);
	}

	public IsLocation(String id){
		super(id);
	}
	
	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
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
	
	public Integer getRoww() {
		return roww;
	}

	public void setRoww(Integer roww) {
		this.roww = roww;
	}
	
	public Integer getCol() {
		return col;
	}

	public void setCol(Integer col) {
		this.col = col;
	}
	
	public Integer getLayerr() {
		return layerr;
	}

	public void setLayerr(Integer layerr) {
		this.layerr = layerr;
	}
	
}