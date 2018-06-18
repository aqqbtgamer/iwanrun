package com.iwantrun.core.service.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.iwantrun.core.service.application.enums.SQLConditions;
import com.mysql.jdbc.StringUtils;

public class NativeSQLUtils {
	
	public static final String BLANK = " ";
	
	private static final String PATTERN_LIKE = "%";
	
	private static final String PATTERN_VARCHAR = "'";
	
	private static final String PATTERN_DECIMAL_A = "CONVERT(";
	
	private static final String PATTERN_DECIMAL_B = " , DECIMAL(20,10))";
	
	private static final String PATTERN_DATE_A = "str_to_date(";
	
	private static final String PATTERN_DATE_B = ",'%Y-%m-%d %H:%i:%s')";
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public static final SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
	
	private static final String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
	
	public static String contractQueryCondition(String conditonName,String value ,SQLConditions opetation,String sql,SQLConditions condition) {
		if(sql == null || StringUtils.isNullOrEmpty(value)) {
			return sql ;
		}else if(value == null){
			return sql ;
		}else{
			if(!isSpecialChar(value) ) {
				if(SQLConditions.And.equals(opetation) || SQLConditions.Or.equals(opetation)) {
					sql = sql.concat(BLANK).concat(opetation.getSymbol()).concat(BLANK);
					sql = sql.concat(conditonName).concat(BLANK);
					sql = sql.concat(condition.getSymbol()).concat(BLANK);;
					if(SQLConditions.Like.equals(condition)) {
						sql = sql.concat(PATTERN_VARCHAR).concat(PATTERN_LIKE).concat(value).concat(PATTERN_LIKE).concat(PATTERN_VARCHAR).concat(BLANK);
					}else {
						sql = sql.concat(PATTERN_VARCHAR).concat(value).concat(PATTERN_VARCHAR).concat(BLANK);
					}
				}
			}			
			return sql ;
		}
	}
	
	public static String contractQueryCondition(String conditonName,Integer value ,SQLConditions opetation,String sql,SQLConditions condition) {
		if (sql == null) {
			return sql;
		} else if (value == null) {
			return sql;
		} else {
			if (SQLConditions.And.equals(opetation) || SQLConditions.Or.equals(opetation)) {
				sql = sql.concat(BLANK).concat(opetation.getSymbol()).concat(BLANK);
				sql = sql.concat(conditonName).concat(BLANK);
				sql = sql.concat(condition.getSymbol()).concat(BLANK);
				;
				if (SQLConditions.Like.equals(condition)) {
					sql = sql.concat(PATTERN_VARCHAR).concat(PATTERN_LIKE)
							.concat(value.toString()).concat(PATTERN_LIKE).concat(PATTERN_VARCHAR).concat(BLANK);
				} else {
					sql = sql.concat(value.toString());
				}
			}
			return sql;
		}
	}
	
	public static String contractQueryCondition(String conditonName,BigDecimal value ,SQLConditions opetation,String sql,SQLConditions condition) {
		if (sql == null) {
			return sql;
		} else if (value == null) {
			return sql;
		} else {
			if (SQLConditions.And.equals(opetation) || SQLConditions.Or.equals(opetation)) {
				sql = sql.concat(BLANK).concat(opetation.getSymbol()).concat(BLANK);
				sql = sql.concat(conditonName).concat(BLANK);
				sql = sql.concat(condition.getSymbol()).concat(BLANK);
				;
				if (SQLConditions.Like.equals(condition)) {
					sql = sql.concat(PATTERN_VARCHAR).concat(PATTERN_LIKE)
							.concat(value.toString()).concat(PATTERN_LIKE).concat(PATTERN_VARCHAR).concat(BLANK);
				} else {
					sql = sql.concat(PATTERN_DECIMAL_A).concat(PATTERN_VARCHAR).concat(value.toString()).concat(PATTERN_VARCHAR).concat(PATTERN_DECIMAL_B);
				}
			}
			return sql;
		}
	}
	
	public static String contractQueryCondition(String conditonName,Date value ,SQLConditions opetation,String sql,SQLConditions condition) {
		if (sql == null) {
			return sql;
		} else if (value == null) {
			return sql;
		} else {
			if (SQLConditions.And.equals(opetation) || SQLConditions.Or.equals(opetation)) {
				sql = sql.concat(BLANK).concat(opetation.getSymbol()).concat(BLANK);
				sql = sql.concat(conditonName).concat(BLANK);
				sql = sql.concat(condition.getSymbol()).concat(BLANK);
				;
				if (SQLConditions.Like.equals(condition)) {
					sql = sql.concat(PATTERN_VARCHAR).concat(PATTERN_LIKE)
							.concat(format.format(value)).concat(PATTERN_LIKE).concat(PATTERN_VARCHAR).concat(BLANK);
				} else {
					sql = sql.concat(PATTERN_DATE_A).concat(PATTERN_VARCHAR).concat(format.format(value)).concat(PATTERN_VARCHAR).concat(PATTERN_DATE_B);
				}
			}
			return sql;
		}
	}
	
	
	  public static boolean isSpecialChar(String str) {
	        Pattern p = Pattern.compile(regEx);
	        Matcher m = p.matcher(str);
	        return m.find();
	    }

}
