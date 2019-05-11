/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * twms_pltitemEntity
 * @author xx
 * @version 2019-05-10
 */
@Table(name="twms_pltitem", alias="a", columns={
		@Column(name="vpltnum", attrName="vpltnum", label="虚拟托盘号", isPK=true),
		@Column(name="linenum", attrName="linenum", label="物料明细行号", comment="物料明细行号（上下箱标识：10-为上箱；20-为下箱）"),
		@Column(name="objecttype", attrName="objecttype", label="objecttype"),
		@Column(name="itemautonum", attrName="itemautonum", label="物料自动编号", comment="物料自动编号（可不考虑）"),
		@Column(name="itemnum", attrName="itemnum", label="物料号", comment="物料号（可不考虑）"),
		@Column(name="itemtype", attrName="itemtype", label="itemtype"),
		@Column(name="lotnum", attrName="lotnum", label="批次号", comment="批次号（空箱为none，实箱为实际批次）"),
		@Column(name="wlotnum", attrName="wlotnum", label="wlotnum"),
		@Column(name="snnum", attrName="snnum", label="当前用作烟箱清洁标志和退库标志", comment="当前用作烟箱清洁标志和退库标志（600-退库，1-需清洁，0-不清洁）"),
		@Column(name="master", attrName="master", label="master"),
		@Column(name="producer", attrName="producer", label="producer"),
		@Column(name="vendor", attrName="vendor", label="当前用作区域标识", comment="当前用作区域标识（1-A区，2-B区）"),
		@Column(name="hive_days", attrName="hiveDays", label="hive_days"),
		@Column(name="shelflife", attrName="shelflife", label="shelflife"),
		@Column(name="itemqty", attrName="itemqty", label="箱数"),
		@Column(name="issueunit", attrName="issueunit", label="单位"),
		@Column(name="statitemqty", attrName="statitemqty", label="statitemqty"),
		@Column(name="statunit", attrName="statunit", label="statunit"),
		@Column(name="ponum", attrName="ponum", label="ponum"),
		@Column(name="poline", attrName="poline", label="poline"),
		@Column(name="spinv", attrName="spinv", label="spinv"),
		@Column(name="invtype", attrName="invtype", label="invtype"),
		@Column(name="qulityflg", attrName="qulityflg", label="qulityflg"),
		@Column(name="producedate", attrName="producedate", label="生产时间"),
		@Column(name="itemdesc", attrName="itemdesc", label="物料描述"),
		@Column(name="boxnum", attrName="boxnum", label="boxnum"),
		@Column(name="enterdate", attrName="enterdate", label="入库时间"),
		@Column(name="enterby", attrName="enterby", label="enterby"),
		@Column(name="sourcesnnum", attrName="sourcesnnum", label="sourcesnnum"),
		@Column(name="weight", attrName="weight", label="烟箱装丝量", comment="烟箱装丝量（kg）"),
		@Column(name="weight2", attrName="weight2", label="weight2"),
		@Column(name="weightunit", attrName="weightunit", label="weightunit"),
		@Column(name="extattr1", attrName="extattr1", label="扩展字段", comment="扩展字段（当前存放烟箱箱号）"),
		@Column(name="extattr2", attrName="extattr2", label="extattr2"),
		@Column(name="extattr3", attrName="extattr3", label="extattr3"),
		@Column(name="extattr4", attrName="extattr4", label="extattr4"),
		@Column(name="extattr5", attrName="extattr5", label="extattr5"),
		@Column(name="extattr6", attrName="extattr6", label="extattr6"),
	}, orderBy="a.vpltnum DESC"
)
public class TwmsPltitem extends DataEntity<TwmsPltitem> {
	
	private static final long serialVersionUID = 1L;
	private String vpltnum;		// 虚拟托盘号
	private Integer linenum;		// 物料明细行号（上下箱标识：10-为上箱；20-为下箱）
	private String objecttype;		// objecttype
	private String itemautonum;		// 物料自动编号（可不考虑）
	private String itemnum;		// 物料号（可不考虑）
	private String itemtype;		// itemtype
	private String lotnum;		// 批次号（空箱为none，实箱为实际批次）
	private String wlotnum;		// wlotnum
	private String snnum;		// 当前用作烟箱清洁标志和退库标志（600-退库，1-需清洁，0-不清洁）
	private String master;		// master
	private String producer;		// producer
	private String vendor;		// 当前用作区域标识（1-A区，2-B区）
	private Double hiveDays;		// hive_days
	private String shelflife;		// shelflife
	private Double itemqty;		// 箱数
	private String issueunit;		// 单位
	private Double statitemqty;		// statitemqty
	private String statunit;		// statunit
	private String ponum;		// ponum
	private String poline;		// poline
	private String spinv;		// spinv
	private String invtype;		// invtype
	private String qulityflg;		// qulityflg
	private String producedate;		// 生产时间
	private String itemdesc;		// 物料描述
	private String boxnum;		// boxnum
	private String enterdate;		// 入库时间
	private String enterby;		// enterby
	private String sourcesnnum;		// sourcesnnum
	private Double weight;		// 烟箱装丝量（kg）
	private Double weight2;		// weight2
	private String weightunit;		// weightunit
	private String extattr1;		// 扩展字段（当前存放烟箱箱号）
	private String extattr2;		// extattr2
	private String extattr3;		// extattr3
	private String extattr4;		// extattr4
	private String extattr5;		// extattr5
	private String extattr6;		// extattr6	
	
	public TwmsPltitem() {
		this(null);
	}

	public TwmsPltitem(String id){
		super(id);
	}
	
	public String getVpltnum() {
		return vpltnum;
	}

	public void setVpltnum(String vpltnum) {
		this.vpltnum = vpltnum;
	}
	
	@NotNull(message="物料明细行号不能为空")
	public Integer getLinenum() {
		return linenum;
	}

	public void setLinenum(Integer linenum) {
		this.linenum = linenum;
	}
	
	@NotBlank(message="objecttype不能为空")
	@Length(min=0, max=36, message="objecttype长度不能超过 36 个字符")
	public String getObjecttype() {
		return objecttype;
	}

	public void setObjecttype(String objecttype) {
		this.objecttype = objecttype;
	}
	
	@NotBlank(message="物料自动编号不能为空")
	@Length(min=0, max=36, message="物料自动编号长度不能超过 36 个字符")
	public String getItemautonum() {
		return itemautonum;
	}

	public void setItemautonum(String itemautonum) {
		this.itemautonum = itemautonum;
	}
	
	@NotBlank(message="物料号不能为空")
	@Length(min=0, max=36, message="物料号长度不能超过 36 个字符")
	public String getItemnum() {
		return itemnum;
	}

	public void setItemnum(String itemnum) {
		this.itemnum = itemnum;
	}
	
	@NotBlank(message="itemtype不能为空")
	@Length(min=0, max=36, message="itemtype长度不能超过 36 个字符")
	public String getItemtype() {
		return itemtype;
	}

	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}
	
	@NotBlank(message="批次号不能为空")
	@Length(min=0, max=36, message="批次号长度不能超过 36 个字符")
	public String getLotnum() {
		return lotnum;
	}

	public void setLotnum(String lotnum) {
		this.lotnum = lotnum;
	}
	
	@Length(min=0, max=36, message="wlotnum长度不能超过 36 个字符")
	public String getWlotnum() {
		return wlotnum;
	}

	public void setWlotnum(String wlotnum) {
		this.wlotnum = wlotnum;
	}
	
	@NotBlank(message="当前用作烟箱清洁标志和退库标志不能为空")
	@Length(min=0, max=50, message="当前用作烟箱清洁标志和退库标志长度不能超过 50 个字符")
	public String getSnnum() {
		return snnum;
	}

	public void setSnnum(String snnum) {
		this.snnum = snnum;
	}
	
	@NotBlank(message="master不能为空")
	@Length(min=0, max=36, message="master长度不能超过 36 个字符")
	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}
	
	@NotBlank(message="producer不能为空")
	@Length(min=0, max=36, message="producer长度不能超过 36 个字符")
	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	@NotBlank(message="当前用作区域标识不能为空")
	@Length(min=0, max=36, message="当前用作区域标识长度不能超过 36 个字符")
	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
	public Double getHiveDays() {
		return hiveDays;
	}

	public void setHiveDays(Double hiveDays) {
		this.hiveDays = hiveDays;
	}
	
	@Length(min=0, max=36, message="shelflife长度不能超过 36 个字符")
	public String getShelflife() {
		return shelflife;
	}

	public void setShelflife(String shelflife) {
		this.shelflife = shelflife;
	}
	
	@NotNull(message="箱数不能为空")
	public Double getItemqty() {
		return itemqty;
	}

	public void setItemqty(Double itemqty) {
		this.itemqty = itemqty;
	}
	
	@NotBlank(message="单位不能为空")
	@Length(min=0, max=36, message="单位长度不能超过 36 个字符")
	public String getIssueunit() {
		return issueunit;
	}

	public void setIssueunit(String issueunit) {
		this.issueunit = issueunit;
	}
	
	@NotNull(message="statitemqty不能为空")
	public Double getStatitemqty() {
		return statitemqty;
	}

	public void setStatitemqty(Double statitemqty) {
		this.statitemqty = statitemqty;
	}
	
	@NotBlank(message="statunit不能为空")
	@Length(min=0, max=36, message="statunit长度不能超过 36 个字符")
	public String getStatunit() {
		return statunit;
	}

	public void setStatunit(String statunit) {
		this.statunit = statunit;
	}
	
	@Length(min=0, max=36, message="ponum长度不能超过 36 个字符")
	public String getPonum() {
		return ponum;
	}

	public void setPonum(String ponum) {
		this.ponum = ponum;
	}
	
	@Length(min=0, max=36, message="poline长度不能超过 36 个字符")
	public String getPoline() {
		return poline;
	}

	public void setPoline(String poline) {
		this.poline = poline;
	}
	
	@NotBlank(message="spinv不能为空")
	@Length(min=0, max=10, message="spinv长度不能超过 10 个字符")
	public String getSpinv() {
		return spinv;
	}

	public void setSpinv(String spinv) {
		this.spinv = spinv;
	}
	
	@NotBlank(message="invtype不能为空")
	@Length(min=0, max=10, message="invtype长度不能超过 10 个字符")
	public String getInvtype() {
		return invtype;
	}

	public void setInvtype(String invtype) {
		this.invtype = invtype;
	}
	
	@NotBlank(message="qulityflg不能为空")
	@Length(min=0, max=36, message="qulityflg长度不能超过 36 个字符")
	public String getQulityflg() {
		return qulityflg;
	}

	public void setQulityflg(String qulityflg) {
		this.qulityflg = qulityflg;
	}
	
	@Length(min=0, max=36, message="生产时间长度不能超过 36 个字符")
	public String getProducedate() {
		return producedate;
	}

	public void setProducedate(String producedate) {
		this.producedate = producedate;
	}
	
	@Length(min=0, max=240, message="物料描述长度不能超过 240 个字符")
	public String getItemdesc() {
		return itemdesc;
	}

	public void setItemdesc(String itemdesc) {
		this.itemdesc = itemdesc;
	}
	
	@Length(min=0, max=36, message="boxnum长度不能超过 36 个字符")
	public String getBoxnum() {
		return boxnum;
	}

	public void setBoxnum(String boxnum) {
		this.boxnum = boxnum;
	}
	
	@Length(min=0, max=36, message="入库时间长度不能超过 36 个字符")
	public String getEnterdate() {
		return enterdate;
	}

	public void setEnterdate(String enterdate) {
		this.enterdate = enterdate;
	}
	
	@Length(min=0, max=36, message="enterby长度不能超过 36 个字符")
	public String getEnterby() {
		return enterby;
	}

	public void setEnterby(String enterby) {
		this.enterby = enterby;
	}
	
	@Length(min=0, max=50, message="sourcesnnum长度不能超过 50 个字符")
	public String getSourcesnnum() {
		return sourcesnnum;
	}

	public void setSourcesnnum(String sourcesnnum) {
		this.sourcesnnum = sourcesnnum;
	}
	
	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	public Double getWeight2() {
		return weight2;
	}

	public void setWeight2(Double weight2) {
		this.weight2 = weight2;
	}
	
	@Length(min=0, max=36, message="weightunit长度不能超过 36 个字符")
	public String getWeightunit() {
		return weightunit;
	}

	public void setWeightunit(String weightunit) {
		this.weightunit = weightunit;
	}
	
	@Length(min=0, max=60, message="扩展字段长度不能超过 60 个字符")
	public String getExtattr1() {
		return extattr1;
	}

	public void setExtattr1(String extattr1) {
		this.extattr1 = extattr1;
	}
	
	@Length(min=0, max=60, message="extattr2长度不能超过 60 个字符")
	public String getExtattr2() {
		return extattr2;
	}

	public void setExtattr2(String extattr2) {
		this.extattr2 = extattr2;
	}
	
	@Length(min=0, max=60, message="extattr3长度不能超过 60 个字符")
	public String getExtattr3() {
		return extattr3;
	}

	public void setExtattr3(String extattr3) {
		this.extattr3 = extattr3;
	}
	
	@Length(min=0, max=60, message="extattr4长度不能超过 60 个字符")
	public String getExtattr4() {
		return extattr4;
	}

	public void setExtattr4(String extattr4) {
		this.extattr4 = extattr4;
	}
	
	@Length(min=0, max=60, message="extattr5长度不能超过 60 个字符")
	public String getExtattr5() {
		return extattr5;
	}

	public void setExtattr5(String extattr5) {
		this.extattr5 = extattr5;
	}
	
	@Length(min=0, max=60, message="extattr6长度不能超过 60 个字符")
	public String getExtattr6() {
		return extattr6;
	}

	public void setExtattr6(String extattr6) {
		this.extattr6 = extattr6;
	}
	
}