package com.iwantrun.core.service.application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import com.iwantrun.core.service.application.annotation.DictionaryField;
import com.iwantrun.core.service.utils.DictionaryConfigParams;

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
	private Integer orderId ;
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
	private Integer designDuring ;
	/**
	 * 实施周期
	 */
	@Column(name="execute_during")
	private Integer executeDuring ;
	/**
	 * 策划周期code
	 */
	@Column(name="design_during_code")
	@DictionaryField(name=DictionaryConfigParams.CASE_DICTIONARY_NAME ,usedField=DictionaryConfigParams.CASE_PLOT_PERIOD_TYPE,aliasField="designDuringCo")
	private Integer designDuringCode ;
	
	@Transient
	private String designDuringCo;
	/**
	 * 实施周期code
	 */
	@Column(name="execute_during_code")
	@DictionaryField(name=DictionaryConfigParams.CASE_DICTIONARY_NAME ,usedField=DictionaryConfigParams.CASE_IMPL_PERIOD_TYPE,aliasField="executeDuringCo")
	private Integer executeDuringCode;
	
	@Transient
	private String executeDuringCo;
	/**
	 * 优先权重
	 */
	@Column(name="priority")
	private int priority;
	/**
	 *交通信息
	 */
	@Column(name="traffic_info")
	@DictionaryField(name=DictionaryConfigParams.CASE_DICTIONARY_NAME ,usedField=DictionaryConfigParams.CSAE_TRAFFIC_TYPE)
	private String trafficInfo;
	/**
	 * 餐饮信息
	 */
	@Column(name="food_info")
	@DictionaryField(name=DictionaryConfigParams.CASE_DICTIONARY_NAME ,usedField=DictionaryConfigParams.CSAE_FOOD_TYPE)
	private String foodInfo;
	/**
	 * 住宿信息
	 */
	@Column(name="hotel_info")
	@DictionaryField(name=DictionaryConfigParams.CASE_DICTIONARY_NAME ,usedField=DictionaryConfigParams.CSAE_RESTURANT_TYPE)
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
	@DictionaryField(name=DictionaryConfigParams.COMMON_DICTIONARY_NAME ,usedField=DictionaryConfigParams.COMMON_PROVINCE_TYPE)
	private String activityProvinceCode ;
	/**
	 * 市编码
	 */
	@Column(name="activity_city_code")
	@DictionaryField(name=DictionaryConfigParams.COMMON_DICTIONARY_NAME ,usedField=DictionaryConfigParams.COMMON_CITY_TYPE)
	private String activityCityCode ;
	/**
	 * 区编码
	 */
	@Column(name="activity_dist_code")
	@DictionaryField(name=DictionaryConfigParams.COMMON_DICTIONARY_NAME ,usedField=DictionaryConfigParams.COMMON_DIST_TYPE)
	private String activityDistCode ;
	/**
	 * 详细地址
	 */
	@Column(name="location")
	private String location ;
	/**
	 * 活动类型
	 */
	@Column(name="activity_type_code",nullable=false)
	@DictionaryField(name=DictionaryConfigParams.COMMON_DICTIONARY_NAME ,usedField=DictionaryConfigParams.COMMON_ACTIVITY_TYPE)
	private String activityTypeCode ;   
	/**
	 * 活动人数
	 */
	@Column(name="group_number")
	@DictionaryField(name=DictionaryConfigParams.COMMON_DICTIONARY_NAME ,usedField=DictionaryConfigParams.COMMON_ACTIVITY_PERSON_NUMBER_TYPE)
	private String groupNumber ;
	/**
	 * 企业类型
	 */
	@Column(name="company_type_code")
	@DictionaryField(name=DictionaryConfigParams.COMMON_DICTIONARY_NAME ,usedField=DictionaryConfigParams.COMMON_COMPANY_TYPE)
	private String companyTypeCode ;
	/**
	 * 活动天数
	 */
	@Column(name="during")
	@DictionaryField(name=DictionaryConfigParams.COMMON_DICTIONARY_NAME ,usedField=DictionaryConfigParams.COMMON_ACTIVITY_PERIOD_TYPE,aliasField="dur")
	private Integer during ;
	
	@Transient
	private String dur;
	
	/**
	 * 案例特色关键词
	 */
	@Column(name="special_key_word")
	@DictionaryField(name=DictionaryConfigParams.CASE_DICTIONARY_NAME ,usedField=DictionaryConfigParams.CASE_TAGS_TYPE)
	private String specialKeyWord;
	
	private String[] tips;//特色关键字  多个
	/**
	 *  
	 *人均参考报价范围
	 */
	@Column(name="simulate_price_code")
	private Integer simulatePriceCode;
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
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
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
	public Integer getDesignDuring() {
		return designDuring;
	}
	public void setDesignDuring(Integer designDuring) {
		this.designDuring = designDuring;
	}
	public Integer getExecuteDuring() {
		return executeDuring;
	}
	public void setExecuteDuring(Integer executeDuring) {
		this.executeDuring = executeDuring;
	}
	public Integer getDesignDuringCode() {
		return designDuringCode;
	}
	public void setDesignDuringCode(Integer designDuringCode) {
		this.designDuringCode = designDuringCode;
	}
	public Integer getExecuteDuringCode() {
		return executeDuringCode;
	}
	public void setExecuteDuringCode(Integer executeDuringCode) {
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
	public Integer getDuring() {
		return during;
	}
	public void setDuring(Integer during) {
		this.during = during;
	}
	public String getSpecialKeyWord() {
		return specialKeyWord;
	}
	public void setSpecialKeyWord(String specialKeyWord) {
		this.specialKeyWord = specialKeyWord;
	}
	public Integer getSimulatePriceCode() {
		return simulatePriceCode;
	}
	public void setSimulatePriceCode(Integer simulatePriceCode) {
		this.simulatePriceCode = simulatePriceCode;
	}
	public String getDesignDuringCo() {
		return designDuringCo;
	}
	public void setDesignDuringCo(String designDuringCo) {
		this.designDuringCo = designDuringCo;
	}
	public String getExecuteDuringCo() {
		return executeDuringCo;
	}
	public void setExecuteDuringCo(String executeDuringCo) {
		this.executeDuringCo = executeDuringCo;
	}
	public String getDur() {
		return dur;
	}
	public void setDur(String dur) {
		this.dur = dur;
	}
	public String[] getTips() {
		return tips;
	}
	public void setTips(String[] tips) {
		this.tips = tips;
	}

}
