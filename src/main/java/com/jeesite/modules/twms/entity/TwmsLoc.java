/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.twms.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * twms_locEntity
 * @author xx
 * @version 2019-05-13
 */
@Table(name="twms_loc", alias="a", columns={
		@Column(name="locnum", attrName="locnum", label="货位", isPK=true),
		@Column(name="objecttype", attrName="objecttype", label="类型", comment="类型（标识货位、站台和区域）"),
		@Column(name="prntnum", attrName="prntnum", label="prntnum"),
		@Column(name="sysnode", attrName="sysnode", label="sysnode"),
		@Column(name="locdcsnum", attrName="locdcsnum", label="locdcsnum"),
		@Column(name="logicloc", attrName="logicloc", label="logicloc"),
		@Column(name="relateloc", attrName="relateloc", label="relateloc"),
		@Column(name="onwaytemploc", attrName="onwaytemploc", label="onwaytemploc"),
		@Column(name="ifdummyloc", attrName="ifdummyloc", label="ifdummyloc"),
		@Column(name="locdesc", attrName="locdesc", label="货位位置描述"),
		@Column(name="address", attrName="address", label="address"),
		@Column(name="tel", attrName="tel", label="tel"),
		@Column(name="principalby", attrName="principalby", label="principalby"),
		@Column(name="reptype", attrName="reptype", label="reptype"),
		@Column(name="repbgndate", attrName="repbgndate", label="repbgndate"),
		@Column(name="rependdate", attrName="rependdate", label="rependdate"),
		@Column(name="loccmbnum", attrName="loccmbnum", label="loccmbnum"),
		@Column(name="iflogin", attrName="iflogin", label="iflogin"),
		@Column(name="if1vplt", attrName="if1vplt", label="if1vplt"),
		@Column(name="ifinvstat", attrName="ifinvstat", label="ifinvstat"),
		@Column(name="ifavailableitem", attrName="ifavailableitem", label="ifavailableitem"),
		@Column(name="ifloclimititem", attrName="ifloclimititem", label="ifloclimititem"),
		@Column(name="locstatus", attrName="locstatus", label="货位状态", comment="货位状态（正常，禁用（人工），禁出、禁入、系统锁定（货物位置锁定））"),
		@Column(name="i_priority", attrName="ipriority", label="i_priority"),
		@Column(name="o_priority", attrName="opriority", label="o_priority"),
		@Column(name="relatedistance", attrName="relatedistance", label="relatedistance"),
		@Column(name="mvinlimitlogg", attrName="mvinlimitlogg", label="mvinlimitlogg"),
		@Column(name="mvoutlimitlogg", attrName="mvoutlimitlogg", label="mvoutlimitlogg"),
		@Column(name="mvinunit", attrName="mvinunit", label="mvinunit"),
		@Column(name="mvoutunit", attrName="mvoutunit", label="mvoutunit"),
		@Column(name="binitemmaster", attrName="binitemmaster", label="binitemmaster"),
		@Column(name="enterby", attrName="enterby", label="enterby"),
		@Column(name="enterdate", attrName="enterdate", label="enterdate"),
		@Column(name="remark", attrName="remark", label="remark"),
		@Column(name="ifhavesonnode", attrName="ifhavesonnode", label="ifhavesonnode"),
		@Column(name="mvstock1", attrName="mvstock1", label="mvstock1"),
		@Column(name="areanode", attrName="areanode", label="areanode"),
		@Column(name="ifitemaverage", attrName="ifitemaverage", label="ifitemaverage"),
		@Column(name="bylanenum", attrName="bylanenum", label="bylanenum"),
		@Column(name="stntype", attrName="stntype", label="stntype"),
		@Column(name="locstorestatus", attrName="locstorestatus", label="货位存放状态", comment="货位存放状态（空闲、载货、入库锁定、出库锁定）"),
		@Column(name="ifstoreplt", attrName="ifstoreplt", label="ifstoreplt"),
		@Column(name="doublestorefirsttype", attrName="doublestorefirsttype", label="doublestorefirsttype"),
		@Column(name="pltqty", attrName="pltqty", label="pltqty"),
		@Column(name="totalbuffqty", attrName="totalbuffqty", label="totalbuffqty"),
		@Column(name="doublestoreflag", attrName="doublestoreflag", label="doublestoreflag"),
		@Column(name="line", attrName="line", label="货位【排】"),
		@Column(name="lie", attrName="lie", label="货位【列】"),
		@Column(name="layer", attrName="layer", label="货位【层】"),
		@Column(name="areatype", attrName="areatype", label="areatype"),
	}, orderBy="a.locnum DESC"
)
public class TwmsLoc extends DataEntity<TwmsLoc> {
	
	private static final long serialVersionUID = 1L;
	private String locnum;		// 货位
	private String objecttype;		// 类型（标识货位、站台和区域）
	private String prntnum;		// prntnum
	private String sysnode;		// sysnode
	private String locdcsnum;		// locdcsnum
	private String logicloc;		// logicloc
	private String relateloc;		// relateloc
	private String onwaytemploc;		// onwaytemploc
	private String ifdummyloc;		// ifdummyloc
	private String locdesc;		// 货位位置描述
	private String address;		// address
	private String tel;		// tel
	private String principalby;		// principalby
	private String reptype;		// reptype
	private String repbgndate;		// repbgndate
	private String rependdate;		// rependdate
	private String loccmbnum;		// loccmbnum
	private String iflogin;		// iflogin
	private String if1vplt;		// if1vplt
	private String ifinvstat;		// ifinvstat
	private String ifavailableitem;		// ifavailableitem
	private String ifloclimititem;		// ifloclimititem
	private String locstatus;		// 货位状态（正常，禁用（人工），禁出、禁入、系统锁定（货物位置锁定））
	private Long ipriority;		// i_priority
	private Long opriority;		// o_priority
	private Long relatedistance;		// relatedistance
	private Double mvinlimitlogg;		// mvinlimitlogg
	private Double mvoutlimitlogg;		// mvoutlimitlogg
	private String mvinunit;		// mvinunit
	private String mvoutunit;		// mvoutunit
	private String binitemmaster;		// binitemmaster
	private String enterby;		// enterby
	private String enterdate;		// enterdate
	private String remark;		// remark
	private String ifhavesonnode;		// ifhavesonnode
	private String mvstock1;		// mvstock1
	private String areanode;		// areanode
	private String ifitemaverage;		// ifitemaverage
	private String bylanenum;		// bylanenum
	private String stntype;		// stntype
	private String locstorestatus;		// 货位存放状态（空闲、载货、入库锁定、出库锁定）
	private String ifstoreplt;		// ifstoreplt
	private String doublestorefirsttype;		// doublestorefirsttype
	private Integer pltqty;		// pltqty
	private Integer totalbuffqty;		// totalbuffqty
	private String doublestoreflag;		// doublestoreflag
	private Integer line;		// 货位【排】
	private Integer lie;		// 货位【列】
	private Integer layer;		// 货位【层】
	private Integer areatype;		// areatype
	
	public TwmsLoc() {
		this(null);
	}

	public TwmsLoc(String id){
		super(id);
	}
	
	public String getLocnum() {
		return locnum;
	}

	public void setLocnum(String locnum) {
		this.locnum = locnum;
	}
	
	@NotBlank(message="类型不能为空")
	@Length(min=0, max=36, message="类型长度不能超过 36 个字符")
	public String getObjecttype() {
		return objecttype;
	}

	public void setObjecttype(String objecttype) {
		this.objecttype = objecttype;
	}
	
	@NotBlank(message="prntnum不能为空")
	@Length(min=0, max=36, message="prntnum长度不能超过 36 个字符")
	public String getPrntnum() {
		return prntnum;
	}

	public void setPrntnum(String prntnum) {
		this.prntnum = prntnum;
	}
	
	@NotBlank(message="sysnode不能为空")
	@Length(min=0, max=36, message="sysnode长度不能超过 36 个字符")
	public String getSysnode() {
		return sysnode;
	}

	public void setSysnode(String sysnode) {
		this.sysnode = sysnode;
	}
	
	@Length(min=0, max=36, message="locdcsnum长度不能超过 36 个字符")
	public String getLocdcsnum() {
		return locdcsnum;
	}

	public void setLocdcsnum(String locdcsnum) {
		this.locdcsnum = locdcsnum;
	}
	
	@Length(min=0, max=36, message="logicloc长度不能超过 36 个字符")
	public String getLogicloc() {
		return logicloc;
	}

	public void setLogicloc(String logicloc) {
		this.logicloc = logicloc;
	}
	
	@Length(min=0, max=36, message="relateloc长度不能超过 36 个字符")
	public String getRelateloc() {
		return relateloc;
	}

	public void setRelateloc(String relateloc) {
		this.relateloc = relateloc;
	}
	
	@Length(min=0, max=36, message="onwaytemploc长度不能超过 36 个字符")
	public String getOnwaytemploc() {
		return onwaytemploc;
	}

	public void setOnwaytemploc(String onwaytemploc) {
		this.onwaytemploc = onwaytemploc;
	}
	
	@Length(min=0, max=1, message="ifdummyloc长度不能超过 1 个字符")
	public String getIfdummyloc() {
		return ifdummyloc;
	}

	public void setIfdummyloc(String ifdummyloc) {
		this.ifdummyloc = ifdummyloc;
	}
	
	@Length(min=0, max=60, message="货位位置描述长度不能超过 60 个字符")
	public String getLocdesc() {
		return locdesc;
	}

	public void setLocdesc(String locdesc) {
		this.locdesc = locdesc;
	}
	
	@Length(min=0, max=60, message="address长度不能超过 60 个字符")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=36, message="tel长度不能超过 36 个字符")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=36, message="principalby长度不能超过 36 个字符")
	public String getPrincipalby() {
		return principalby;
	}

	public void setPrincipalby(String principalby) {
		this.principalby = principalby;
	}
	
	@Length(min=0, max=36, message="reptype长度不能超过 36 个字符")
	public String getReptype() {
		return reptype;
	}

	public void setReptype(String reptype) {
		this.reptype = reptype;
	}
	
	@Length(min=0, max=36, message="repbgndate长度不能超过 36 个字符")
	public String getRepbgndate() {
		return repbgndate;
	}

	public void setRepbgndate(String repbgndate) {
		this.repbgndate = repbgndate;
	}
	
	@Length(min=0, max=36, message="rependdate长度不能超过 36 个字符")
	public String getRependdate() {
		return rependdate;
	}

	public void setRependdate(String rependdate) {
		this.rependdate = rependdate;
	}
	
	@NotBlank(message="loccmbnum不能为空")
	@Length(min=0, max=36, message="loccmbnum长度不能超过 36 个字符")
	public String getLoccmbnum() {
		return loccmbnum;
	}

	public void setLoccmbnum(String loccmbnum) {
		this.loccmbnum = loccmbnum;
	}
	
	@NotBlank(message="iflogin不能为空")
	@Length(min=0, max=1, message="iflogin长度不能超过 1 个字符")
	public String getIflogin() {
		return iflogin;
	}

	public void setIflogin(String iflogin) {
		this.iflogin = iflogin;
	}
	
	@NotBlank(message="if1vplt不能为空")
	@Length(min=0, max=1, message="if1vplt长度不能超过 1 个字符")
	public String getIf1vplt() {
		return if1vplt;
	}

	public void setIf1vplt(String if1vplt) {
		this.if1vplt = if1vplt;
	}
	
	@NotBlank(message="ifinvstat不能为空")
	@Length(min=0, max=1, message="ifinvstat长度不能超过 1 个字符")
	public String getIfinvstat() {
		return ifinvstat;
	}

	public void setIfinvstat(String ifinvstat) {
		this.ifinvstat = ifinvstat;
	}
	
	@NotBlank(message="ifavailableitem不能为空")
	@Length(min=0, max=1, message="ifavailableitem长度不能超过 1 个字符")
	public String getIfavailableitem() {
		return ifavailableitem;
	}

	public void setIfavailableitem(String ifavailableitem) {
		this.ifavailableitem = ifavailableitem;
	}
	
	@NotBlank(message="ifloclimititem不能为空")
	@Length(min=0, max=1, message="ifloclimititem长度不能超过 1 个字符")
	public String getIfloclimititem() {
		return ifloclimititem;
	}

	public void setIfloclimititem(String ifloclimititem) {
		this.ifloclimititem = ifloclimititem;
	}
	
	@NotBlank(message="货位状态不能为空")
	@Length(min=0, max=36, message="货位状态长度不能超过 36 个字符")
	public String getLocstatus() {
		return locstatus;
	}

	public void setLocstatus(String locstatus) {
		this.locstatus = locstatus;
	}
	
	@NotNull(message="i_priority不能为空")
	public Long getIpriority() {
		return ipriority;
	}

	public void setIpriority(Long ipriority) {
		this.ipriority = ipriority;
	}
	
	@NotNull(message="o_priority不能为空")
	public Long getOpriority() {
		return opriority;
	}

	public void setOpriority(Long opriority) {
		this.opriority = opriority;
	}
	
	public Long getRelatedistance() {
		return relatedistance;
	}

	public void setRelatedistance(Long relatedistance) {
		this.relatedistance = relatedistance;
	}
	
	public Double getMvinlimitlogg() {
		return mvinlimitlogg;
	}

	public void setMvinlimitlogg(Double mvinlimitlogg) {
		this.mvinlimitlogg = mvinlimitlogg;
	}
	
	public Double getMvoutlimitlogg() {
		return mvoutlimitlogg;
	}

	public void setMvoutlimitlogg(Double mvoutlimitlogg) {
		this.mvoutlimitlogg = mvoutlimitlogg;
	}
	
	@Length(min=0, max=36, message="mvinunit长度不能超过 36 个字符")
	public String getMvinunit() {
		return mvinunit;
	}

	public void setMvinunit(String mvinunit) {
		this.mvinunit = mvinunit;
	}
	
	@Length(min=0, max=36, message="mvoutunit长度不能超过 36 个字符")
	public String getMvoutunit() {
		return mvoutunit;
	}

	public void setMvoutunit(String mvoutunit) {
		this.mvoutunit = mvoutunit;
	}
	
	@NotBlank(message="binitemmaster不能为空")
	@Length(min=0, max=36, message="binitemmaster长度不能超过 36 个字符")
	public String getBinitemmaster() {
		return binitemmaster;
	}

	public void setBinitemmaster(String binitemmaster) {
		this.binitemmaster = binitemmaster;
	}
	
	@Length(min=0, max=36, message="enterby长度不能超过 36 个字符")
	public String getEnterby() {
		return enterby;
	}

	public void setEnterby(String enterby) {
		this.enterby = enterby;
	}
	
	@Length(min=0, max=36, message="enterdate长度不能超过 36 个字符")
	public String getEnterdate() {
		return enterdate;
	}

	public void setEnterdate(String enterdate) {
		this.enterdate = enterdate;
	}
	
	@Length(min=0, max=300, message="remark长度不能超过 300 个字符")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=1, message="ifhavesonnode长度不能超过 1 个字符")
	public String getIfhavesonnode() {
		return ifhavesonnode;
	}

	public void setIfhavesonnode(String ifhavesonnode) {
		this.ifhavesonnode = ifhavesonnode;
	}
	
	@Length(min=0, max=60, message="mvstock1长度不能超过 60 个字符")
	public String getMvstock1() {
		return mvstock1;
	}

	public void setMvstock1(String mvstock1) {
		this.mvstock1 = mvstock1;
	}
	
	@Length(min=0, max=36, message="areanode长度不能超过 36 个字符")
	public String getAreanode() {
		return areanode;
	}

	public void setAreanode(String areanode) {
		this.areanode = areanode;
	}
	
	@Length(min=0, max=36, message="ifitemaverage长度不能超过 36 个字符")
	public String getIfitemaverage() {
		return ifitemaverage;
	}

	public void setIfitemaverage(String ifitemaverage) {
		this.ifitemaverage = ifitemaverage;
	}
	
	@Length(min=0, max=36, message="bylanenum长度不能超过 36 个字符")
	public String getBylanenum() {
		return bylanenum;
	}

	public void setBylanenum(String bylanenum) {
		this.bylanenum = bylanenum;
	}
	
	@Length(min=0, max=36, message="stntype长度不能超过 36 个字符")
	public String getStntype() {
		return stntype;
	}

	public void setStntype(String stntype) {
		this.stntype = stntype;
	}
	
	@Length(min=0, max=36, message="货位存放状态长度不能超过 36 个字符")
	public String getLocstorestatus() {
		return locstorestatus;
	}

	public void setLocstorestatus(String locstorestatus) {
		this.locstorestatus = locstorestatus;
	}
	
	@Length(min=0, max=36, message="ifstoreplt长度不能超过 36 个字符")
	public String getIfstoreplt() {
		return ifstoreplt;
	}

	public void setIfstoreplt(String ifstoreplt) {
		this.ifstoreplt = ifstoreplt;
	}
	
	@Length(min=0, max=36, message="doublestorefirsttype长度不能超过 36 个字符")
	public String getDoublestorefirsttype() {
		return doublestorefirsttype;
	}

	public void setDoublestorefirsttype(String doublestorefirsttype) {
		this.doublestorefirsttype = doublestorefirsttype;
	}
	
	public Integer getPltqty() {
		return pltqty;
	}

	public void setPltqty(Integer pltqty) {
		this.pltqty = pltqty;
	}
	
	public Integer getTotalbuffqty() {
		return totalbuffqty;
	}

	public void setTotalbuffqty(Integer totalbuffqty) {
		this.totalbuffqty = totalbuffqty;
	}
	
	@Length(min=0, max=10, message="doublestoreflag长度不能超过 10 个字符")
	public String getDoublestoreflag() {
		return doublestoreflag;
	}

	public void setDoublestoreflag(String doublestoreflag) {
		this.doublestoreflag = doublestoreflag;
	}
	
	@NotNull(message="货位【排】不能为空")
	public Integer getLine() {
		return line;
	}

	public void setLine(Integer line) {
		this.line = line;
	}
	
	@NotNull(message="货位【列】不能为空")
	public Integer getLie() {
		return lie;
	}

	public void setLie(Integer lie) {
		this.lie = lie;
	}
	
	@NotNull(message="货位【层】不能为空")
	public Integer getLayer() {
		return layer;
	}

	public void setLayer(Integer layer) {
		this.layer = layer;
	}
	
	@NotNull(message="areatype不能为空")
	public Integer getAreatype() {
		return areatype;
	}

	public void setAreatype(Integer areatype) {
		this.areatype = areatype;
	}
	
}