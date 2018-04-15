package com.iwantrun.core.service.application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "biz_productions")
public class ProductionInfo {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; // 系统主键

	@Column(name = "activity_type_code")
	private int activityTypeCode; // 活动类型

	@Column(name = "during")
	private int during; // 活动天数

	@Column(name = "during_code")
	private int duringCode; // 活动天数范围

	@Column(name = "group_number")
	private int groupNumber; // 活动人数

	@Column(name = "group_number_code")
	private int groupNumberCode; // 活动人数范围

	@Column(name = "priority")
	private int priority; // 优先权重

	@Column(name = "status")
	private int status; // 状态 0-正常 1-下架'

	@Column(name = "order_simulate_price_code")
	private int orderSimulatePriceCode; // 订单人均参考报价范围

	@Column(name = "order_group_price_code")
	private int orderGroupPriceCode; // 订单团体参考报价范围

	@Column(name = "activity_province_code")
	private int activityProvinceCode; // 产品省编码

	@Column(name = "activity_city_code")
	private int activityCityCode; // 产品市编码

	@Column(name = "activity_dist_code")
	private int activityDistCode; // 产品区编码

	@Column(name = "shift_time")
	private Date shiftTime; // 上架时间

	@Column(name = "create_time")
	private Date createTime; // 创建时间

	@Column(name = "name")
	private String name; // 缩略名称

	@Column(name = "main_image_large")
	private String mainImageLarge; // 产品宣传图存放位置

	@Column(name = "main_image_icon")
	private String mainImageIcon; // 产品主宣传缩略图放位置

	@Column(name = "descirbe_text1")
	private String descirbeText1; /// 详情描述1

	@Column(name = "descirbe_text2")
	private String descirbeText2; // 详情描述2

	@Column(name = "descirbe_text3")
	private String descirbeText3; // 详情描述3

	@Column(name = "location")
	private String location; // 详细地址

	@Column(name = "qrcode")
	private String qrcode; // 二维码信息地址

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActivityTypeCode() {
		return activityTypeCode;
	}

	public void setActivityTypeCode(int activityTypeCode) {
		this.activityTypeCode = activityTypeCode;
	}

	public int getDuring() {
		return during;
	}

	public void setDuring(int during) {
		this.during = during;
	}

	public int getDuringCode() {
		return duringCode;
	}

	public void setDuringCode(int duringCode) {
		this.duringCode = duringCode;
	}

	public int getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}

	public int getGroupNumberCode() {
		return groupNumberCode;
	}

	public void setGroupNumberCode(int groupNumberCode) {
		this.groupNumberCode = groupNumberCode;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOrderSimulatePriceCode() {
		return orderSimulatePriceCode;
	}

	public void setOrderSimulatePriceCode(int orderSimulatePriceCode) {
		this.orderSimulatePriceCode = orderSimulatePriceCode;
	}

	public int getOrderGroupPriceCode() {
		return orderGroupPriceCode;
	}

	public void setOrderGroupPriceCode(int orderGroupPriceCode) {
		this.orderGroupPriceCode = orderGroupPriceCode;
	}

	public int getActivityProvinceCode() {
		return activityProvinceCode;
	}

	public void setActivityProvinceCode(int activityProvinceCode) {
		this.activityProvinceCode = activityProvinceCode;
	}

	public int getActivityCityCode() {
		return activityCityCode;
	}

	public void setActivityCityCode(int activityCityCode) {
		this.activityCityCode = activityCityCode;
	}

	public int getActivityDistCode() {
		return activityDistCode;
	}

	public void setActivityDistCode(int activityDistCode) {
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
