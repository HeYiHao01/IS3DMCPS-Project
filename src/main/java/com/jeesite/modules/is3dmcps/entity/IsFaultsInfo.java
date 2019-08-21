/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * is_faults_infoEntity
 * @author yh
 * @version 2019-08-19
 */
@Table(name="is_faults_info", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="fault_no", attrName="faultNo", label="序号"),
		@Column(name="address", attrName="address", label="地址"),
		@Column(name="fault_code", attrName="faultCode", label="报警代码"),
		@Column(name="info", attrName="info", label="报警信息"),
		@Column(name="remark", attrName="remark", label="扩展备注"),
	}, orderBy="a.id DESC"
)
public class IsFaultsInfo extends DataEntity<IsFaultsInfo> {
	
	private static final long serialVersionUID = 1L;
	private String faultNo;		// 序号
	private String address;		// 地址
	private String faultCode;		// 报警代码
	private String info;		// 报警信息
	private String remark;		// 扩展备注
	
	public IsFaultsInfo() {
		this(null);
	}

	public IsFaultsInfo(String id){
		super(id);
	}
	
	@NotBlank(message="序号不能为空")
	@Length(min=0, max=20, message="序号长度不能超过 20 个字符")
	public String getFaultNo() {
		return faultNo;
	}

	public void setFaultNo(String faultNo) {
		this.faultNo = faultNo;
	}
	
	@NotBlank(message="地址不能为空")
	@Length(min=0, max=20, message="地址长度不能超过 20 个字符")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@NotBlank(message="报警代码不能为空")
	@Length(min=0, max=20, message="报警代码长度不能超过 20 个字符")
	public String getFaultCode() {
		return faultCode;
	}

	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}
	
	@NotBlank(message="报警信息不能为空")
	@Length(min=0, max=200, message="报警信息长度不能超过 200 个字符")
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	@Length(min=0, max=200, message="扩展备注长度不能超过 200 个字符")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}