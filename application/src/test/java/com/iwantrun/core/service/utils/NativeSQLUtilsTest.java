package com.iwantrun.core.service.utils;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import com.iwantrun.core.service.application.enums.SQLConditions;

public class NativeSQLUtilsTest {
	

	@Test
	public void testContractQueryConditionStringStringSQLConditionsStringSQLConditions() {
		String sql = "select * from Orders order where 1 =1 ";
		System.out.println(NativeSQLUtils.contractQueryCondition("order.id", "123", SQLConditions.And, sql, SQLConditions.Equals));
		System.out.println(NativeSQLUtils.contractQueryCondition("order.id", "123", SQLConditions.Or, sql, SQLConditions.Equals));
		System.out.println(NativeSQLUtils.contractQueryCondition("order.id", "123", SQLConditions.And, sql, SQLConditions.Like));
	}

	@Test
	public void testContractQueryConditionStringIntegerSQLConditionsStringSQLConditions() {
		String sql = "select * from Orders order where 1 =1 ";
		System.out.println(NativeSQLUtils.contractQueryCondition("order.id", 123, SQLConditions.And, sql, SQLConditions.Equals));
		System.out.println(NativeSQLUtils.contractQueryCondition("order.id", 123, SQLConditions.Or, sql, SQLConditions.Equals));
		System.out.println(NativeSQLUtils.contractQueryCondition("order.id", 123, SQLConditions.And, sql, SQLConditions.Like));
	}

	@Test
	public void testContractQueryConditionStringBigDecimalSQLConditionsStringSQLConditions() {
		String sql = "select * from Orders order where 1 =1 ";
		BigDecimal num = new BigDecimal("12.321");
		System.out.println(NativeSQLUtils.contractQueryCondition("order.id", num, SQLConditions.And, sql, SQLConditions.Equals));
		System.out.println(NativeSQLUtils.contractQueryCondition("order.id", num, SQLConditions.Or, sql, SQLConditions.Equals));
		System.out.println(NativeSQLUtils.contractQueryCondition("order.id", num, SQLConditions.And, sql, SQLConditions.Like));
	}

	
	@Test
	public void testContractQueryConditionDateConditionsStringSQLConditions() {
		String sql = "select * from Orders order where 1 =1 ";
		System.out.println(NativeSQLUtils.contractQueryCondition("order.id", new Date(), SQLConditions.And, sql, SQLConditions.Equals));
		System.out.println(NativeSQLUtils.contractQueryCondition("order.id", new Date(), SQLConditions.And, sql, SQLConditions.GreaterThan));
		System.out.println(NativeSQLUtils.contractQueryCondition("order.id", new Date(), SQLConditions.Or, sql, SQLConditions.Equals));
		System.out.println(NativeSQLUtils.contractQueryCondition("order.id", new Date(), SQLConditions.And, sql, SQLConditions.Like));
	}
}
