package com.iwantrun.core.service.application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

@Entity
@Table(name = "biz_productions")
public class ProductionInfo extends JpaRepositoriesAutoConfiguration{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 系统主键

	@Column(name = "activity_type_code",nullable=false)
	private Integer activityTypeCode; // 活动类型

	@Column(name = "during", nullable=false)
	private Integer during; // 活动天数

	@Column(name = "during_code")
	private Integer duringCode; // 活动天数范围

	@Column(name = "group_number")
	private Integer groupNumber; // 活动人数

	@Column(name = "group_number_code")
	private Integer groupNumberCode; // 活动人数范围

	@Column(name = "priority")
	private Integer priority; // 优先权重

	@Column(name = "status",nullable=false)
	private Integer status; // 状态 0-正常 1-下架'

	@Column(name = "order_simulate_price_code")
	private Integer orderSimulatePriceCode; // 订单人均参考报价范围

	@Column(name = "order_group_price_code")
	private Integer orderGroupPriceCode; // 订单团体参考报价范围

	@Column(name = "activity_province_code")
	private Integer activityProvinceCode; // 产品省编码

	@Column(name = "activity_city_code", nullable = true)
	private Integer activityCityCode; // 产品市编码

	@Column(name = "activity_dist_code")
	private Integer activityDistCode; // 产品区编码

	@Column(name = "shift_time",nullable=false)
	private Date shiftTime; // 上架时间

	@Column(name = "create_time",nullable=false)
	private Date createTime; // 创建时间

	@Column(name = "name", nullable=false)
	private String name; // 缩略名称

	@Column(name = "main_image_large")
	private String mainImageLarge; // 产品宣传图存放位置

	@Column(name = "main_image_icon")
	private String mainImageIcon; // 产品主宣传缩略图放位置

	@Column(name = "descirbe_text1", nullable=true)
	private String descirbeText1; /// 详情描述1

	@Column(name = "descirbe_text2")
	private String descirbeText2; // 详情描述2

	@Column(name = "descirbe_text3")
	private String descirbeText3; // 详情描述3

	@Column(name = "location")
	private String location; // 详细地址

	@Column(name = "qrcode")
	private String qrcode; // 二维码信息地址
	
	@Transient
	private Locations locations;
	
	public Locations getLocations() {
		return locations;
	}

	public void setLocations(Locations locations) {
		this.locations = locations;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActivityTypeCode() {
		return activityTypeCode;
	}

	public void setActivityTypeCode(Integer activityTypeCode) {
		this.activityTypeCode = activityTypeCode;
	}

	public Integer getDuring() {
		return during;
	}

	public void setDuring(Integer during) {
		this.during = during;
	}

	public Integer getDuringCode() {
		return duringCode;
	}

	public void setDuringCode(Integer duringCode) {
		this.duringCode = duringCode;
	}

	public Integer getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(Integer groupNumber) {
		this.groupNumber = groupNumber;
	}

	public Integer getGroupNumberCode() {
		return groupNumberCode;
	}

	public void setGroupNumberCode(Integer groupNumberCode) {
		this.groupNumberCode = groupNumberCode;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrderSimulatePriceCode() {
		return orderSimulatePriceCode;
	}

	public void setOrderSimulatePriceCode(Integer orderSimulatePriceCode) {
		this.orderSimulatePriceCode = orderSimulatePriceCode;
	}

	public Integer getOrderGroupPriceCode() {
		return orderGroupPriceCode;
	}

	public void setOrderGroupPriceCode(Integer orderGroupPriceCode) {
		this.orderGroupPriceCode = orderGroupPriceCode;
	}

	public Integer getActivityProvinceCode() {
		return activityProvinceCode;
	}

	public void setActivityProvinceCode(Integer activityProvinceCode) {
		this.activityProvinceCode = activityProvinceCode;
	}

	public Integer getActivityCityCode() {
		return activityCityCode;
	}

	public void setActivityCityCode(Integer activityCityCode) {
		this.activityCityCode = activityCityCode;
	}

	public Integer getActivityDistCode() {
		return activityDistCode;
	}

	public void setActivityDistCode(Integer activityDistCode) {
		this.activityDistCode = activityDistCode;
	}

	public Date getShiftTime() {
		return shiftTime;
	}

	public void setShiftTime(Date shiftTime) {
		this.shiftTime = shiftTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMainImageLarge() {
		return mainImageLarge;
	}

	public void setMainImageLarge(String mainImageLarge) {
		this.mainImageLarge = mainImageLarge;
	}

	public String getMainImageIcon() {
		return mainImageIcon;
	}

	public void setMainImageIcon(String mainImageIcon) {
		this.mainImageIcon = mainImageIcon;
	}

	public String getDescirbeText1() {
		return descirbeText1;
	}

	public void setDescirbeText1(String descirbeText1) {
		this.descirbeText1 = descirbeText1;
	}

	public String getDescirbeText2() {
		return descirbeText2;
	}

	public void setDescirbeText2(String descirbeText2) {
		this.descirbeText2 = descirbeText2;
	}

	public String getDescirbeText3() {
		return descirbeText3;
	}

	public void setDescirbeText3(String descirbeText3) {
		this.descirbeText3 = descirbeText3;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

}
