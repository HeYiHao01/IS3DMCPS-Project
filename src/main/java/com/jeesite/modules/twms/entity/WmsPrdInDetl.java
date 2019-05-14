/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * wms_prd_in_detlEntity
 * @author xx
 * @version 2019-05-14
 */
@Table(name="wms_prd_in_detl", alias="a", columns={
		@Column(name="wo_no", attrName="woNo", label="工单号", isPK=true),
		@Column(name="batch_no", attrName="batchNo", label="批次号"),
		@Column(name="team_cd", attrName="teamCd", label="班组"),
		@Column(name="shift_cd", attrName="shiftCd", label="班次"),
		@Column(name="locnum", attrName="locnum", label="入库货位"),
		@Column(name="mat_cd", attrName="matCd", label="牌号编码"),
		@Column(name="mat_nm", attrName="matNm", label="牌号名称"),
		@Column(name="weight", attrName="weight", label="烟箱装丝量"),
		@Column(name="boxid", attrName="boxid", label="箱号"),
		@Column(name="prod_time", attrName="prodTime", label="装箱入库时间"),
	}, orderBy="a.wo_no DESC"
)
public class WmsPrdInDetl extends DataEntity<WmsPrdInDetl> {
	
	private static final long serialVersionUID = 1L;
	private String woNo;		// 工单号
	private String batchNo;		// 批次号
	private String teamCd;		// 班组
	private String shiftCd;		// 班次
	private String locnum;		// 入库货位
	private String matCd;		// 牌号编码
	private String matNm;		// 牌号名称
	private String weight;		// 烟箱装丝量
	private String boxid;		// 箱号
	private Date prodTime;		// 装箱入库时间
	
	public WmsPrdInDetl() {
		this(null);
	}

	public WmsPrdInDetl(String id){
		super(id);
	}
	
	public String getWoNo() {
		return woNo;
	}

	public void setWoNo(String woNo) {
		this.woNo = woNo;
	}
	
	@Length(min=0, max=30, message="批次号长度不能超过 30 个字符")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	@Length(min=0, max=20, message="班组长度不能超过 20 个字符")
	public String getTeamCd() {
		return teamCd;
	}

	public void setTeamCd(String teamCd) {
		this.teamCd = teamCd;
	}
	
	@Length(min=0, max=20, message="班次长度不能超过 20 个字符")
	public String getShiftCd() {
		return shiftCd;
	}

	public void setShiftCd(String shiftCd) {
		this.shiftCd = shiftCd;
	}
	
	@Length(min=0, max=30, message="入库货位长度不能超过 30 个字符")
	public String getLocnum() {
		return locnum;
	}

	public void setLocnum(String locnum) {
		this.locnum = locnum;
	}
	
	@Length(min=0, max=30, message="牌号编码长度不能超过 30 个字符")
	public String getMatCd() {
		return matCd;
	}

	public void setMatCd(String matCd) {
		this.matCd = matCd;
	}
	
	@Length(min=0, max=40, message="牌号名称长度不能超过 40 个字符")
	public String getMatNm() {
		return matNm;
	}

	public void setMatNm(String matNm) {
		this.matNm = matNm;
	}
	
	@Length(min=0, max=20, message="烟箱装丝量长度不能超过 20 个字符")
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	@Length(min=0, max=20, message="箱号长度不能超过 20 个字符")
	public String getBoxid() {
		return boxid;
	}

	public void setBoxid(String boxid) {
		this.boxid = boxid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProdTime() {
		return prodTime;
	}

	public void setProdTime(Date prodTime) {
		this.prodTime = prodTime;
	}
	
}