package com.iwantrun.core.service.application.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
		// 多条件组装
		Specification<ProductionInfo> specification = new Specification<ProductionInfo>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ProductionInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				String name=param.getName();
				Integer activityCityCode =param.getActivityCityCode();
				String descirbeText1 = param.getDescirbeText1();
				
				Path<String> namePath = root.get("name");
				Path<Integer> activityCityCodePath = root.get("activityCityCode");
				Path<String> descirbeText1Path = root.get("descirbeText1");
				
				List<Predicate> list = new ArrayList<Predicate>();
				if (!StringUtils.isEmpty(name)) {
					list.add(cb.like(namePath, "%" + name + "%"));
				}
				if (null != activityCityCodePath) {
					list.add(cb.ge(activityCityCodePath, activityCityCode));
				}
				if (!StringUtils.isEmpty(descirbeText1)) {
					list.add(cb.like(descirbeText1Path, "%" + descirbeText1 + "%"));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
		return productionInfoDao.findAll(specification);
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
