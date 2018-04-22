package com.iwantrun.core.service.application.enums;

public enum SQLConditions {
	
	Equals("="),
	NotEquals("!="),
	Greater(">="),
	GreaterThan(">"),
	Less("<="),
	LessThan("<"),
	Like("like"),
	NotNull("notnull"),
	Null("null"),
	And("and"),
	Or("or")
	;
	
	private String symbol ;
	
	SQLConditions(String symbol){
		this.setSymbol(symbol) ;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public static SQLConditions matchSymbol(String symbol) {
		for(SQLConditions condition :SQLConditions.values()) {
			if(condition.getSymbol().equals(symbol)) {
				return condition;
			}
		}
		return null ;
	}

}
