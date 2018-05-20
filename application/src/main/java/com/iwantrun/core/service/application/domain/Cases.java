package com.iwantrun.core.service.application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="biz_cases")
public class Cases {
	/**
	 * id  
	 */
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id ;
	/**
	 * 缩略名称
	 */
	@Column(name="name")
	private String name ;
	/**
	 * 案例关联订单号
	 */
	@Column(name="order_id",nullable=false)
	private int orderId ;
	/**
	 * 案例状态 1-已关联订单 2-未关联 3-已下架 
	 */
	@Column(name="status",nullable=false)
	private int status ;
	/**
	 * 案例主宣传图存放位置
	 */
	@Column(name="main_image_large")
	private String mainImageLarge ;
	/**
	 * 案例主宣传缩略图放位置
	 */
	@Column(name="main_image_icon")
	private String mainImageIcon ;
	/**
	 * 策划周期
	 */
	@Column(name="design_during")
	private int designDuring ;
	/**
	 * 实施周期
	 */
	@Column(name="execute_during")
	private int executeDuring ;
	/**
	 * 策划周期code
	 */
	@Column(name="design_during_code")
	private int designDuringCode ;
	/**
	 * 实施周期code
	 */
	@Column(name="execute_during_code")
	private int executeDuringCode;
	/**
	 * 优先权重
	 */
	@Column(name="priority")
	private int priority;
	/**
	 *交通信息
	 */
	@Column(name="traffic_info")
	private String trafficInfo;
	/**
	 * 餐饮信息
	 */
	@Column(name="food_info")
	private String foodInfo;
	/**
	 * 住宿信息
	 */
	@Column(name="hotel_info")
	private String hotelInfo ;
	/**
	 * 上架时间
	 */
	@Column(name="shift_time")
	private Date shiftTime ;
	/**
	 * 二维码信息地址
	 */
	@Column(name="qrcode")
	private String qrcode ;
	/**
	 * 案例详情描述1
	 */
	@Column(name="descirbe_text1")
	private String descirbeText1;
	/**
	 * 案例详情描述2
	 */
	@Column(name="descirbe_text2")
	private String descirbeText2;
	/**
	 * 案例详情描述3
	 */
	@Column(name="descirbe_text3")
	private String descirbeText3;
	/**
	 * 导出pdf文件储存路径
	 */
	@Column(name="pdf_path")
	private String pdfPath ;
	/**
	 * 省编码
	 */
	@Column(name="activity_province_code")
	private String activityProvinceCode ;
	/**
	 * 市编码
	 */
	@Column(name="activity_city_code")
	private String activityCityCode ;
	/**
	 * 区编码
	 */
	@Column(name="activity_dist_code")
	private String activityDistCode ;
	/**
	 * 详细地址
	 */
	@Column(name="location")
	private String location ;
	/**
	 * 活动类型
	 */
	@Column(name="activity_type_code")
	private String activityTypeCode ;   
	/**
	 * 活动人数
	 */
	@Column(name="group_number")
	private String groupNumber ;
	/**
	 * 企业类型
	 */
	@Column(name="company_type_code")
	private String companyTypeCode ;
	/**
	 * 活动天数
	 */
	@Column(name="during")
	private int during ;
	/**
	 * 案例特色关键词
	 */
	@Column(name="special_key_word")
	private String specialKeyWord;
	/**
	 *  
	 *人均参考报价范围
	 */
	@Column(name="simulate_price_code")
	private int simulatePriceCode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public int getDesignDuring() {
		return designDuring;
	}
	public void setDesignDuring(int designDuring) {
		this.designDuring = designDuring;
	}
	public int getExecuteDuring() {
		return executeDuring;
	}
	public void setExecuteDuring(int executeDuring) {
		this.executeDuring = executeDuring;
	}
	public int getDesignDuringCode() {
		return designDuringCode;
	}
	public void setDesignDuringCode(int designDuringCode) {
		this.designDuringCode = designDuringCode;
	}
	public int getExecuteDuringCode() {
		return executeDuringCode;
	}
	public void setExecuteDuringCode(int executeDuringCode) {
		this.executeDuringCode = executeDuringCode;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getTrafficInfo() {
		return trafficInfo;
	}
	public void setTrafficInfo(String trafficInfo) {
		this.trafficInfo = trafficInfo;
	}
	public String getFoodInfo() {
		return foodInfo;
	}
	public void setFoodInfo(String foodInfo) {
		this.foodInfo = foodInfo;
	}
	public String getHotelInfo() {
		return hotelInfo;
	}
	public void setHotelInfo(String hotelInfo) {
		this.hotelInfo = hotelInfo;
	}
	public Date getShiftTime() {
		return shiftTime;
	}
	public void setShiftTime(Date shiftTime) {
		this.shiftTime = shiftTime;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
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
	public String getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	public String getActivityProvinceCode() {
		return activityProvinceCode;
	}
	public void setActivityProvinceCode(String activityProvinceCode) {
		this.activityProvinceCode = activityProvinceCode;
	}
	public String getActivityCityCode() {
		return activityCityCode;
	}
	public void setActivityCityCode(String activityCityCode) {
		this.activityCityCode = activityCityCode;
	}
	public String getActivityDistCode() {
		return activityDistCode;
	}
	public void setActivityDistCode(String activityDistCode) {
		this.activityDistCode = activityDistCode;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getActivityTypeCode() {
		return activityTypeCode;
	}
	public void setActivityTypeCode(String activityTypeCode) {
		this.activityTypeCode = activityTypeCode;
	}
	public String getGroupNumber() {
		return groupNumber;
	}
	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}
	public String getCompanyTypeCode() {
		return companyTypeCode;
	}
	public void setCompanyTypeCode(String companyTypeCode) {
		this.companyTypeCode = companyTypeCode;
	}
	public String getSpecialKeyWord() {
		return specialKeyWord;
	}
	public void setSpecialKeyWord(String specialKeyWord) {
		this.specialKeyWord = specialKeyWord;
	}
	public int getSimulatePriceCode() {
		return simulatePriceCode;
	}
	public void setSimulatePriceCode(int simulatePriceCode) {
		this.simulatePriceCode = simulatePriceCode;
	}
	public int getDuring() {
		return during;
	}
	public void setDuring(int during) {
		this.during = during;
	}
	
	

}
