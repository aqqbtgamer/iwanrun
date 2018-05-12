package com.iwantrun.core.service.application.config;

import java.util.function.Function;
import java.util.function.Supplier;

public class DictionnaryPageItemConfig {

	private String name ;
	
	private String desc ;
	
	private Function<String,Integer> idbyDESFuction ;
	
	private Supplier<String> dictionaryJsonSupplier ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Function<String, Integer> getIdbyDESFuction() {
		return idbyDESFuction;
	}

	public void setIdbyDESFuction(Function<String, Integer> idbyDESFuction) {
		this.idbyDESFuction = idbyDESFuction;
	}

	public Supplier<String> getDictionaryJsonSupplier() {
		return dictionaryJsonSupplier;
	}

	public void setDictionaryJsonSupplier(Supplier<String> dictionaryJsonSupplier) {
		this.dictionaryJsonSupplier = dictionaryJsonSupplier;
	}
	
	public int getIdByDESC(String desc) {
		return this.idbyDESFuction.apply(desc);
	}
	
	public String getDictionaryJson() {
		return this.dictionaryJsonSupplier.get();
	}
}
