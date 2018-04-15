package com.iwantrun.core.service.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import com.iwantrun.core.service.application.dao.ProductionInfoDao;
import com.iwantrun.core.service.application.domain.ProductionInfo;

@Service
public class ProductionInfoService {
	@Autowired
	ProductionInfoDao productionInfoDao;

	public ProductionInfoDao getProductionInfoDao() {
		return productionInfoDao;
	}

	public void setProductionInfoDao(ProductionInfoDao productionInfoDao) {
		this.productionInfoDao = productionInfoDao;
	}

	/**
	 * 查询产品信息 按照多个查询条件查询产品
	 */
	public List<ProductionInfo> queryByCondition(ProductionInfo param) {
		/**
		 * .(param.getActivityCityCode(), GenericPropertyMatchers.exact())
		 * .withMatcher(param.getActivityDistCode(), eg)
		 * .withMatcher(param.getActivityProvinceCode(), eg)
		 * .withMatcher(param.getActivityTypeCode(), eg)
		 * .withMatcher(param.getCreateTime(), eg) .withMatcher(param.getDuring(), eg)
		 * .withMatcher(param.getDuringCode(), eg) .withMatcher(param.getGroupNumber(),
		 * eg) .withMatcher(param.getGroupNumberCode(), eg))
		 */
		ExampleMatcher.GenericPropertyMatcher eg = GenericPropertyMatchers.contains();
		/*ExampleMatcher matcher = ExampleMatcher.matching().withMatcher(param.getDescirbeText1(), eg)
				.withMatcher(param.getDescirbeText2(), eg).withMatcher(param.getDescirbeText3(), eg)
				.withMatcher(param.getLocation(), eg).withMatcher(param.getMainImageIcon(), eg)
				.withMatcher(param.getMainImageLarge(), eg).withMatcher(param.getName(), eg)
				.withMatcher(param.getQrcode(), eg).withIgnorePaths("id");*/
		//ExampleMatcher matcher = ExampleMatcher.matching().withMatcher(param.getDescirbeText1(), eg);
//		/Example<ProductionInfo> example = Example.of(param, matcher);
		return productionInfoDao.findAll();
	}

	/**
	 * 创建新的产品 上架新的产品信息
	 */
	public boolean createdAndShift(ProductionInfo param) {
		return false;
	}

	/**
	 * 产品权重排序 设定产品在客户端列表页面中展示的前后顺序
	 */
	public void sortPriority() {

	}

	/**
	 * 编辑产品信息 编辑修改产品信息
	 */
	public boolean edit(ProductionInfo param) {
		return false;
	}

	/**
	 * 下架产品 将产品放入回收站（随时可还原恢复），客户端无法查看该产品
	 */
	/*
	 * public boolean delisting(ProductionInfo param) { return false; }
	 */

	/**
	 * 关联案例 将与该产品相关的案例与之建立关联关系
	 */

}
