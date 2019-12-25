/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.is3dmcps.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import com.jeesite.common.entity.BaseEntity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 员工表Entity
 * @author hy
 * @version 2019-12-25
 */
@Table(name="js_sys_employee", alias="a", columns={
		@Column(name="emp_code", attrName="empCode", label="员工编码", isPK=true),
		@Column(name="emp_name", attrName="empName", label="员工姓名", queryType=QueryType.LIKE),
		@Column(name="emp_name_en", attrName="empNameEn", label="英文名", queryType=QueryType.LIKE),
		@Column(name="office_code", attrName="officeCode", label="机构编码"),
		@Column(name="office_name", attrName="officeName", label="机构名称", queryType=QueryType.LIKE),
		@Column(name="company_code", attrName="companyCode", label="公司编码"),
		@Column(name="company_name", attrName="companyName", label="公司名称", queryType=QueryType.LIKE),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=BaseEntity.class),
	}, orderBy="a.update_date DESC"
)
public class SelfDefEmployee extends DataEntity<SelfDefEmployee> {
	
	private static final long serialVersionUID = 1L;
	private String empCode;		// 员工编码
	private String empName;		// 员工姓名
	private String empNameEn;		// 英文名
	private String officeCode;		// 机构编码
	private String officeName;		// 机构名称
	private String companyCode;		// 公司编码
	private String companyName;		// 公司名称
	
	public SelfDefEmployee() {
		this(null);
	}

	public SelfDefEmployee(String id){
		super(id);
	}
	
	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	
	@NotBlank(message="员工姓名不能为空")
	@Length(min=0, max=100, message="员工姓名长度不能超过 100 个字符")
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	@Length(min=0, max=100, message="英文名长度不能超过 100 个字符")
	public String getEmpNameEn() {
		return empNameEn;
	}

	public void setEmpNameEn(String empNameEn) {
		this.empNameEn = empNameEn;
	}
	
	@NotBlank(message="机构编码不能为空")
	@Length(min=0, max=64, message="机构编码长度不能超过 64 个字符")
	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	
	@NotBlank(message="机构名称不能为空")
	@Length(min=0, max=100, message="机构名称长度不能超过 100 个字符")
	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	@Length(min=0, max=64, message="公司编码长度不能超过 64 个字符")
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	@Length(min=0, max=200, message="公司名称长度不能超过 200 个字符")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}