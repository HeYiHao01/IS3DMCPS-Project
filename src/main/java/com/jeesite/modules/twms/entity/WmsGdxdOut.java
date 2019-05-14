/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * wms_gdxd_outEntity
 * @author xx
 * @version 2019-05-14
 */
@Table(name="wms_gdxd_out", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="wo_no", attrName="woNo", label="工单号"),
		@Column(name="batch_no", attrName="batchNo", label="批次号"),
		@Column(name="tech_section_cd", attrName="techSectionCd", label="工艺段编号"),
		@Column(name="equ_id", attrName="equId", label="喂丝机号"),
		@Column(name="outstation", attrName="outstation", label="出库站台"),
		@Column(name="wo_state", attrName="woState", label="工单状态"),
		@Column(name="mat_cd", attrName="matCd", label="牌号编码", comment="牌号编码（固定）"),
		@Column(name="mat_nm", attrName="matNm", label="牌号名称", comment="牌号名称（固定）"),
		@Column(name="p_mat_cd", attrName="pmatCd", label="p_mat_cd"),
		@Column(name="p_mat_nm", attrName="pmatNm", label="p_mat_nm"),
		@Column(name="plan_amount", attrName="planAmount", label="计划量"),
		@Column(name="wo_send_time", attrName="woSendTime", label="工单下达时间"),
		@Column(name="wo_end_time", attrName="woEndTime", label="工单结束时间"),
		@Column(name="wo_start_time", attrName="woStartTime", label="工单开始时间"),
		@Column(name="boxtotalnum", attrName="boxtotalnum", label="boxtotalnum"),
		@Column(name="mat_class", attrName="matClass", label="mat_class"),
		@Column(name="wo_cancel_time", attrName="woCancelTime", label="工单取消时间"),
		@Column(name="shift_cd", attrName="shiftCd", label="班次"),
		@Column(name="team_cd", attrName="teamCd", label="班组"),
		@Column(name="lotnum_in", attrName="lotnumIn", label="lotnum_in"),
	}, orderBy="a.id DESC"
)
public class WmsGdxdOut extends DataEntity<WmsGdxdOut> {
	
	private static final long serialVersionUID = 1L;
	private String woNo;		// 工单号
	private String batchNo;		// 批次号
	private String techSectionCd;		// 工艺段编号
	private String equId;		// 喂丝机号
	private String outstation;		// 出库站台
	private String woState;		// 工单状态
	private String matCd;		// 牌号编码（固定）
	private String matNm;		// 牌号名称（固定）
	private String pmatCd;		// p_mat_cd
	private String pmatNm;		// p_mat_nm
	private Double planAmount;		// 计划量
	private Date woSendTime;		// 工单下达时间
	private Date woEndTime;		// 工单结束时间
	private Date woStartTime;		// 工单开始时间
	private Integer boxtotalnum;		// boxtotalnum
	private String matClass;		// mat_class
	private Date woCancelTime;		// 工单取消时间
	private String shiftCd;		// 班次
	private String teamCd;		// 班组
	private String lotnumIn;		// lotnum_in
	
	public WmsGdxdOut() {
		this(null);
	}

	public WmsGdxdOut(String id){
		super(id);
	}
	
	@NotBlank(message="工单号不能为空")
	@Length(min=0, max=60, message="工单号长度不能超过 60 个字符")
	public String getWoNo() {
		return woNo;
	}

	public void setWoNo(String woNo) {
		this.woNo = woNo;
	}
	
	@NotBlank(message="批次号不能为空")
	@Length(min=0, max=40, message="批次号长度不能超过 40 个字符")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	@NotBlank(message="工艺段编号不能为空")
	@Length(min=0, max=20, message="工艺段编号长度不能超过 20 个字符")
	public String getTechSectionCd() {
		return techSectionCd;
	}

	public void setTechSectionCd(String techSectionCd) {
		this.techSectionCd = techSectionCd;
	}
	
	@NotBlank(message="喂丝机号不能为空")
	@Length(min=0, max=20, message="喂丝机号长度不能超过 20 个字符")
	public String getEquId() {
		return equId;
	}

	public void setEquId(String equId) {
		this.equId = equId;
	}
	
	@Length(min=0, max=100, message="出库站台长度不能超过 100 个字符")
	public String getOutstation() {
		return outstation;
	}

	public void setOutstation(String outstation) {
		this.outstation = outstation;
	}
	
	@NotBlank(message="工单状态不能为空")
	@Length(min=0, max=20, message="工单状态长度不能超过 20 个字符")
	public String getWoState() {
		return woState;
	}

	public void setWoState(String woState) {
		this.woState = woState;
	}
	
	@NotBlank(message="牌号编码不能为空")
	@Length(min=0, max=20, message="牌号编码长度不能超过 20 个字符")
	public String getMatCd() {
		return matCd;
	}

	public void setMatCd(String matCd) {
		this.matCd = matCd;
	}
	
	@NotBlank(message="牌号名称不能为空")
	@Length(min=0, max=60, message="牌号名称长度不能超过 60 个字符")
	public String getMatNm() {
		return matNm;
	}

	public void setMatNm(String matNm) {
		this.matNm = matNm;
	}
	
	@NotBlank(message="p_mat_cd不能为空")
	@Length(min=0, max=40, message="p_mat_cd长度不能超过 40 个字符")
	public String getPmatCd() {
		return pmatCd;
	}

	public void setPmatCd(String pmatCd) {
		this.pmatCd = pmatCd;
	}
	
	@NotBlank(message="p_mat_nm不能为空")
	@Length(min=0, max=30, message="p_mat_nm长度不能超过 30 个字符")
	public String getPmatNm() {
		return pmatNm;
	}

	public void setPmatNm(String pmatNm) {
		this.pmatNm = pmatNm;
	}
	
	@NotNull(message="计划量不能为空")
	public Double getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(Double planAmount) {
		this.planAmount = planAmount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWoSendTime() {
		return woSendTime;
	}

	public void setWoSendTime(Date woSendTime) {
		this.woSendTime = woSendTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWoEndTime() {
		return woEndTime;
	}

	public void setWoEndTime(Date woEndTime) {
		this.woEndTime = woEndTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWoStartTime() {
		return woStartTime;
	}

	public void setWoStartTime(Date woStartTime) {
		this.woStartTime = woStartTime;
	}
	
	public Integer getBoxtotalnum() {
		return boxtotalnum;
	}

	public void setBoxtotalnum(Integer boxtotalnum) {
		this.boxtotalnum = boxtotalnum;
	}
	
	@Length(min=0, max=20, message="mat_class长度不能超过 20 个字符")
	public String getMatClass() {
		return matClass;
	}

	public void setMatClass(String matClass) {
		this.matClass = matClass;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWoCancelTime() {
		return woCancelTime;
	}

	public void setWoCancelTime(Date woCancelTime) {
		this.woCancelTime = woCancelTime;
	}
	
	@Length(min=0, max=20, message="班次长度不能超过 20 个字符")
	public String getShiftCd() {
		return shiftCd;
	}

	public void setShiftCd(String shiftCd) {
		this.shiftCd = shiftCd;
	}
	
	@Length(min=0, max=20, message="班组长度不能超过 20 个字符")
	public String getTeamCd() {
		return teamCd;
	}

	public void setTeamCd(String teamCd) {
		this.teamCd = teamCd;
	}
	
	@Length(min=0, max=30, message="lotnum_in长度不能超过 30 个字符")
	public String getLotnumIn() {
		return lotnumIn;
	}

	public void setLotnumIn(String lotnumIn) {
		this.lotnumIn = lotnumIn;
	}
	
}