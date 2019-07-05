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
 * twms_transferloggEntity
 * @author xx
 * @version 2019-05-24
 */
@Table(name="twms_transferlogg", alias="a", columns={
		@Column(name="loggnum", attrName="loggnum", label="loggnum", isPK=true),
		@Column(name="jobnum", attrName="jobnum", label="jobnum"),
		@Column(name="objecttype", attrName="objecttype", label="objecttype"),
		@Column(name="lotnum", attrName="lotnum", label="lotnum"),
		@Column(name="conveytype", attrName="conveytype", label="conveytype"),
		@Column(name="priority", attrName="priority", label="priority"),
		@Column(name="vpltnum", attrName="vpltnum", label="vpltnum"),
		@Column(name="pltnum", attrName="pltnum", label="pltnum"),
		@Column(name="pltautotype", attrName="pltautotype", label="pltautotype"),
		@Column(name="plttype", attrName="plttype", label="plttype"),
		@Column(name="ssysnode", attrName="ssysnode", label="ssysnode"),
		@Column(name="sloc", attrName="sloc", label="sloc"),
		@Column(name="slocdcs", attrName="slocdcs", label="slocdcs"),
		@Column(name="dsysnode", attrName="dsysnode", label="dsysnode"),
		@Column(name="dloc", attrName="dloc", label="dloc"),
		@Column(name="dlocdcs", attrName="dlocdcs", label="dlocdcs"),
		@Column(name="bgndate", attrName="bgndate", label="bgndate"),
		@Column(name="enddate", attrName="enddate", label="enddate"),
		@Column(name="routeid", attrName="routeid", label="routeid"),
		@Column(name="subfromsysnode", attrName="subfromsysnode", label="subfromsysnode"),
		@Column(name="subfrom", attrName="subfrom", label="subfrom"),
		@Column(name="subtosysnode", attrName="subtosysnode", label="subtosysnode"),
		@Column(name="subto", attrName="subto", label="subto"),
		@Column(name="controlid", attrName="controlid", label="controlid"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="plterrcode", attrName="plterrcode", label="plterrcode"),
		@Column(name="prerequest", attrName="prerequest", label="prerequest"),
		@Column(name="hisroute", attrName="hisroute", label="hisroute"),
		@Column(name="remark", attrName="remark", label="remark"),
		@Column(name="mvstock1", attrName="mvstock1", label="mvstock1"),
		@Column(name="mvstock2", attrName="mvstock2", label="mvstock2"),
		@Column(name="onlydloc", attrName="onlydloc", label="onlydloc"),
		@Column(name="dcsexestep", attrName="dcsexestep", label="dcsexestep"),
		@Column(name="dcsgroup", attrName="dcsgroup", label="dcsgroup"),
		@Column(name="curreqp", attrName="curreqp", label="curreqp"),
		@Column(name="jobid", attrName="jobid", label="jobid"),
	}, orderBy="a.loggnum DESC"
)
public class TwmsTransferlogg extends DataEntity<TwmsTransferlogg> {
	
	private static final long serialVersionUID = 1L;
	private Long loggnum;		// loggnum
	private Long jobnum;		// jobnum
	private String objecttype;		// objecttype
	private String lotnum;		// lotnum
	private String conveytype;		// conveytype
	private Integer priority;		// priority
	private String vpltnum;		// vpltnum
	private String pltnum;		// pltnum
	private String pltautotype;		// pltautotype
	private String plttype;		// plttype
	private String ssysnode;		// ssysnode
	private String sloc;		// sloc
	private String slocdcs;		// slocdcs
	private String dsysnode;		// dsysnode
	private String dloc;		// dloc
	private String dlocdcs;		// dlocdcs
	private String bgndate;		// bgndate
	private String enddate;		// enddate
	private String routeid;		// routeid
	private String subfromsysnode;		// subfromsysnode
	private String subfrom;		// subfrom
	private String subtosysnode;		// subtosysnode
	private String subto;		// subto
	private String controlid;		// controlid
	private String plterrcode;		// plterrcode
	private String prerequest;		// prerequest
	private String hisroute;		// hisroute
	private String remark;		// remark
	private String mvstock1;		// mvstock1
	private String mvstock2;		// mvstock2
	private String onlydloc;		// onlydloc
	private String dcsexestep;		// dcsexestep
	private String dcsgroup;		// dcsgroup
	private String curreqp;		// curreqp
	private Integer jobid;		// jobid
	
	public TwmsTransferlogg() {
		this(null);
	}

	public TwmsTransferlogg(String id){
		super(id);
	}
	
	public Long getLoggnum() {
		return loggnum;
	}

	public void setLoggnum(Long loggnum) {
		this.loggnum = loggnum;
	}
	
	@NotNull(message="jobnum不能为空")
	public Long getJobnum() {
		return jobnum;
	}

	public void setJobnum(Long jobnum) {
		this.jobnum = jobnum;
	}
	
	@NotBlank(message="objecttype不能为空")
	@Length(min=0, max=36, message="objecttype长度不能超过 36 个字符")
	public String getObjecttype() {
		return objecttype;
	}

	public void setObjecttype(String objecttype) {
		this.objecttype = objecttype;
	}
	
	@Length(min=0, max=36, message="lotnum长度不能超过 36 个字符")
	public String getLotnum() {
		return lotnum;
	}

	public void setLotnum(String lotnum) {
		this.lotnum = lotnum;
	}
	
	@Length(min=0, max=36, message="conveytype长度不能超过 36 个字符")
	public String getConveytype() {
		return conveytype;
	}

	public void setConveytype(String conveytype) {
		this.conveytype = conveytype;
	}
	
	@NotNull(message="priority不能为空")
	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	@NotBlank(message="vpltnum不能为空")
	@Length(min=0, max=36, message="vpltnum长度不能超过 36 个字符")
	public String getVpltnum() {
		return vpltnum;
	}

	public void setVpltnum(String vpltnum) {
		this.vpltnum = vpltnum;
	}
	
	@Length(min=0, max=36, message="pltnum长度不能超过 36 个字符")
	public String getPltnum() {
		return pltnum;
	}

	public void setPltnum(String pltnum) {
		this.pltnum = pltnum;
	}
	
	@Length(min=0, max=36, message="pltautotype长度不能超过 36 个字符")
	public String getPltautotype() {
		return pltautotype;
	}

	public void setPltautotype(String pltautotype) {
		this.pltautotype = pltautotype;
	}
	
	@Length(min=0, max=36, message="plttype长度不能超过 36 个字符")
	public String getPlttype() {
		return plttype;
	}

	public void setPlttype(String plttype) {
		this.plttype = plttype;
	}
	
	@Length(min=0, max=36, message="ssysnode长度不能超过 36 个字符")
	public String getSsysnode() {
		return ssysnode;
	}

	public void setSsysnode(String ssysnode) {
		this.ssysnode = ssysnode;
	}
	
	@Length(min=0, max=36, message="sloc长度不能超过 36 个字符")
	public String getSloc() {
		return sloc;
	}

	public void setSloc(String sloc) {
		this.sloc = sloc;
	}
	
	@Length(min=0, max=36, message="slocdcs长度不能超过 36 个字符")
	public String getSlocdcs() {
		return slocdcs;
	}

	public void setSlocdcs(String slocdcs) {
		this.slocdcs = slocdcs;
	}
	
	@Length(min=0, max=36, message="dsysnode长度不能超过 36 个字符")
	public String getDsysnode() {
		return dsysnode;
	}

	public void setDsysnode(String dsysnode) {
		this.dsysnode = dsysnode;
	}
	
	@Length(min=0, max=36, message="dloc长度不能超过 36 个字符")
	public String getDloc() {
		return dloc;
	}

	public void setDloc(String dloc) {
		this.dloc = dloc;
	}
	
	@Length(min=0, max=36, message="dlocdcs长度不能超过 36 个字符")
	public String getDlocdcs() {
		return dlocdcs;
	}

	public void setDlocdcs(String dlocdcs) {
		this.dlocdcs = dlocdcs;
	}
	
	@NotBlank(message="bgndate不能为空")
	@Length(min=0, max=36, message="bgndate长度不能超过 36 个字符")
	public String getBgndate() {
		return bgndate;
	}

	public void setBgndate(String bgndate) {
		this.bgndate = bgndate;
	}
	
	@Length(min=0, max=36, message="enddate长度不能超过 36 个字符")
	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	@Length(min=0, max=36, message="routeid长度不能超过 36 个字符")
	public String getRouteid() {
		return routeid;
	}

	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}
	
	@Length(min=0, max=36, message="subfromsysnode长度不能超过 36 个字符")
	public String getSubfromsysnode() {
		return subfromsysnode;
	}

	public void setSubfromsysnode(String subfromsysnode) {
		this.subfromsysnode = subfromsysnode;
	}
	
	@Length(min=0, max=36, message="subfrom长度不能超过 36 个字符")
	public String getSubfrom() {
		return subfrom;
	}

	public void setSubfrom(String subfrom) {
		this.subfrom = subfrom;
	}
	
	@Length(min=0, max=36, message="subtosysnode长度不能超过 36 个字符")
	public String getSubtosysnode() {
		return subtosysnode;
	}

	public void setSubtosysnode(String subtosysnode) {
		this.subtosysnode = subtosysnode;
	}
	
	@Length(min=0, max=36, message="subto长度不能超过 36 个字符")
	public String getSubto() {
		return subto;
	}

	public void setSubto(String subto) {
		this.subto = subto;
	}
	
	@Length(min=0, max=36, message="controlid长度不能超过 36 个字符")
	public String getControlid() {
		return controlid;
	}

	public void setControlid(String controlid) {
		this.controlid = controlid;
	}
	
	@Length(min=0, max=10, message="plterrcode长度不能超过 10 个字符")
	public String getPlterrcode() {
		return plterrcode;
	}

	public void setPlterrcode(String plterrcode) {
		this.plterrcode = plterrcode;
	}
	
	@Length(min=0, max=50, message="prerequest长度不能超过 50 个字符")
	public String getPrerequest() {
		return prerequest;
	}

	public void setPrerequest(String prerequest) {
		this.prerequest = prerequest;
	}
	
	@Length(min=0, max=200, message="hisroute长度不能超过 200 个字符")
	public String getHisroute() {
		return hisroute;
	}

	public void setHisroute(String hisroute) {
		this.hisroute = hisroute;
	}
	
	@Length(min=0, max=200, message="remark长度不能超过 200 个字符")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=60, message="mvstock1长度不能超过 60 个字符")
	public String getMvstock1() {
		return mvstock1;
	}

	public void setMvstock1(String mvstock1) {
		this.mvstock1 = mvstock1;
	}
	
	@Length(min=0, max=60, message="mvstock2长度不能超过 60 个字符")
	public String getMvstock2() {
		return mvstock2;
	}

	public void setMvstock2(String mvstock2) {
		this.mvstock2 = mvstock2;
	}
	
	@Length(min=0, max=36, message="onlydloc长度不能超过 36 个字符")
	public String getOnlydloc() {
		return onlydloc;
	}

	public void setOnlydloc(String onlydloc) {
		this.onlydloc = onlydloc;
	}
	
	@Length(min=0, max=36, message="dcsexestep长度不能超过 36 个字符")
	public String getDcsexestep() {
		return dcsexestep;
	}

	public void setDcsexestep(String dcsexestep) {
		this.dcsexestep = dcsexestep;
	}
	
	@Length(min=0, max=36, message="dcsgroup长度不能超过 36 个字符")
	public String getDcsgroup() {
		return dcsgroup;
	}

	public void setDcsgroup(String dcsgroup) {
		this.dcsgroup = dcsgroup;
	}
	
	@Length(min=0, max=36, message="curreqp长度不能超过 36 个字符")
	public String getCurreqp() {
		return curreqp;
	}

	public void setCurreqp(String curreqp) {
		this.curreqp = curreqp;
	}
	
	public Integer getJobid() {
		return jobid;
	}

	public void setJobid(Integer jobid) {
		this.jobid = jobid;
	}
	
}