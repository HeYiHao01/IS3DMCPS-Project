/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * wms_gdxd_inEntity
 * @author xx
 * @version 2019-05-10
 */
@Table(name="wms_gdxd_in", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="wo_no", attrName="woNo", label="工单号"),
		@Column(name="batch_no", attrName="batchNo", label="生产批次号"),
		@Column(name="tech_section_cd", attrName="techSectionCd", label="工艺段编号"),
		@Column(name="in_line", attrName="inLine", label="入库线"),
		@Column(name="wo_state", attrName="woState", label="工单状态"),
		@Column(name="mat_class_cd", attrName="matClassCd", label="牌号分类编码"),
		@Column(name="mat_class_nm", attrName="matClassNm", label="牌号分类名称"),
		@Column(name="mat_cd", attrName="matCd", label="牌号编码"),
		@Column(name="mat_nm", attrName="matNm", label="牌号名称"),
		@Column(name="plan_amount", attrName="planAmount", label="柜存量"),
		@Column(name="wo_send_time", attrName="woSendTime", label="工单下达时间"),
		@Column(name="state", attrName="state", label="工单状态"),
		@Column(name="wo_start_time", attrName="woStartTime", label="工单开始时间"),
		@Column(name="wo_end_time", attrName="woEndTime", label="工单结束时间"),
		@Column(name="checkboxnum", attrName="checkboxnum", label="抽检箱号"),
		@Column(name="boxtotalnum", attrName="boxtotalnum", label="装箱总数"),
		@Column(name="station", attrName="station", label="入库站台"),
		@Column(name="type", attrName="type", label="工单类型"),
		@Column(name="batchweight", attrName="batchweight", label="批次总量"),
		@Column(name="boxloadweight", attrName="boxloadweight", label="单箱装箱量"),
		@Column(name="shift_cd", attrName="shiftCd", label="班次"),
		@Column(name="team_cd", attrName="teamCd", label="班组"),
		@Column(name="mat_class", attrName="matClass", label="班别"),
		@Column(name="wo_cancel_time", attrName="woCancelTime", label="工单取消时间", comment="工单取消时间（如果有）"),
		@Column(name="totalweight_in", attrName="totalweightIn", label="本系统统计的实际入库批次总量"),
	}, orderBy="a.id DESC"
)
public class WmsGdxdIn extends DataEntity<WmsGdxdIn> {
	
	private static final long serialVersionUID = 1L;
	private String woNo;		// 工单号
	private String batchNo;		// 生产批次号
	private String techSectionCd;		// 工艺段编号
	private String inLine;		// 入库线
	private String woState;		// 工单状态
	private String matClassCd;		// 牌号分类编码
	private String matClassNm;		// 牌号分类名称
	private String matCd;		// 牌号编码
	private String matNm;		// 牌号名称
	private Double planAmount;		// 柜存量
	private Date woSendTime;		// 工单下达时间
	private String state;		// 工单状态
	private Timestamp woStartTime;		// 工单开始时间
	private Timestamp woEndTime;		// 工单结束时间
	private Integer checkboxnum;		// 抽检箱号
	private Integer boxtotalnum;		// 装箱总数
	private String station;		// 入库站台
	private String type;		// 工单类型
	private Double batchweight;		// 批次总量
	private Double boxloadweight;		// 单箱装箱量
	private String shiftCd;		// 班次
	private String teamCd;		// 班组
	private String matClass;		// 班别
	private Date woCancelTime;		// 工单取消时间（如果有）
	private Double totalweightIn;		// 本系统统计的实际入库批次总量
	
	public WmsGdxdIn() {
		this(null);
	}

	public WmsGdxdIn(String id){
		super(id);
	}
	
	@NotBlank(message="工单号不能为空")
	@Length(min=0, max=30, message="工单号长度不能超过 30 个字符")
	public String getWoNo() {
		return woNo;
	}

	public void setWoNo(String woNo) {
		this.woNo = woNo;
	}
	
	@NotBlank(message="生产批次号不能为空")
	@Length(min=0, max=30, message="生产批次号长度不能超过 30 个字符")
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
	
	@Length(min=0, max=30, message="入库线长度不能超过 30 个字符")
	public String getInLine() {
		return inLine;
	}

	public void setInLine(String inLine) {
		this.inLine = inLine;
	}
	
	@NotBlank(message="工单状态不能为空")
	@Length(min=0, max=12, message="工单状态长度不能超过 12 个字符")
	public String getWoState() {
		return woState;
	}

	public void setWoState(String woState) {
		this.woState = woState;
	}
	
	@NotBlank(message="牌号分类编码不能为空")
	@Length(min=0, max=20, message="牌号分类编码长度不能超过 20 个字符")
	public String getMatClassCd() {
		return matClassCd;
	}

	public void setMatClassCd(String matClassCd) {
		this.matClassCd = matClassCd;
	}
	
	@NotBlank(message="牌号分类名称不能为空")
	@Length(min=0, max=30, message="牌号分类名称长度不能超过 30 个字符")
	public String getMatClassNm() {
		return matClassNm;
	}

	public void setMatClassNm(String matClassNm) {
		this.matClassNm = matClassNm;
	}
	
	@NotBlank(message="牌号编码不能为空")
	@Length(min=0, max=25, message="牌号编码长度不能超过 25 个字符")
	public String getMatCd() {
		return matCd;
	}

	public void setMatCd(String matCd) {
		this.matCd = matCd;
	}
	
	@NotBlank(message="牌号名称不能为空")
	@Length(min=0, max=30, message="牌号名称长度不能超过 30 个字符")
	public String getMatNm() {
		return matNm;
	}

	public void setMatNm(String matNm) {
		this.matNm = matNm;
	}
	
	@NotNull(message="柜存量不能为空")
	public Double getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(Double planAmount) {
		this.planAmount = planAmount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="工单下达时间不能为空")
	public Date getWoSendTime() {
		return woSendTime;
	}

	public void setWoSendTime(Date woSendTime) {
		this.woSendTime = woSendTime;
	}
	
	@NotBlank(message="工单状态不能为空")
	@Length(min=0, max=30, message="工单状态长度不能超过 30 个字符")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@JsonFormat(pattern = "yyyy.MM.dd.HH:mm:ss.ff6")	
	public Timestamp getWoStartTime() {
		return woStartTime;
	}

	public void setWoStartTime(Timestamp woStartTime) {
		this.woStartTime = woStartTime;
	}
	
	@JsonFormat(pattern = "yyyy.MM.dd.HH:mm:ss.ff6")
	public Timestamp getWoEndTime() {
		return woEndTime;
	}

	public void setWoEndTime(Timestamp woEndTime) {
		this.woEndTime = woEndTime;
	}
	
	public Integer getCheckboxnum() {
		return checkboxnum;
	}

	public void setCheckboxnum(Integer checkboxnum) {
		this.checkboxnum = checkboxnum;
	}
	
	public Integer getBoxtotalnum() {
		return boxtotalnum;
	}

	public void setBoxtotalnum(Integer boxtotalnum) {
		this.boxtotalnum = boxtotalnum;
	}
	
	@Length(min=0, max=20, message="入库站台长度不能超过 20 个字符")
	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}
	
	@Length(min=0, max=50, message="工单类型长度不能超过 50 个字符")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Double getBatchweight() {
		return batchweight;
	}

	public void setBatchweight(Double batchweight) {
		this.batchweight = batchweight;
	}
	
	public Double getBoxloadweight() {
		return boxloadweight;
	}

	public void setBoxloadweight(Double boxloadweight) {
		this.boxloadweight = boxloadweight;
	}
	
	@Length(min=0, max=4, message="班次长度不能超过 4 个字符")
	public String getShiftCd() {
		return shiftCd;
	}

	public void setShiftCd(String shiftCd) {
		this.shiftCd = shiftCd;
	}
	
	@Length(min=0, max=4, message="班组长度不能超过 4 个字符")
	public String getTeamCd() {
		if (teamCd == null || teamCd.equals("")) {
			teamCd = "null";
		}
		return teamCd;
	}

	public void setTeamCd(String teamCd) {
		this.teamCd = teamCd;
	}
	
	@Length(min=0, max=20, message="班别长度不能超过 20 个字符")
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
	
	public Double getTotalweightIn() {
		return totalweightIn;
	}

	public void setTotalweightIn(Double totalweightIn) {
		this.totalweightIn = totalweightIn;
	}
	
}