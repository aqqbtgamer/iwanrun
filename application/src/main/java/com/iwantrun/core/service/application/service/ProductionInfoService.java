package com.iwantrun.core.service.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iwantrun.core.service.application.dao.LocationsDao;
import com.iwantrun.core.service.application.dao.ProductionInfoDao;
import com.iwantrun.core.service.application.dao.ProductionLocationRelationDao;
import com.iwantrun.core.service.application.domain.Locations;
import com.iwantrun.core.service.application.domain.ProductionInfo;
import com.iwantrun.core.service.application.domain.ProductionLocationRelation;

@Service
public class ProductionInfoService {
	@Autowired
	ProductionInfoDao productionInfoDao;
	@Autowired
	LocationsDao locationsDao;
	@Autowired
	ProductionLocationRelationDao pLocationRelationDao;

	public ProductionInfoDao getProductionInfoDao() {
		return productionInfoDao;
	}

	public void setProductionInfoDao(ProductionInfoDao productionInfoDao) {
		this.productionInfoDao = productionInfoDao;
	}

	public LocationsDao getLocationsDao() {
		return locationsDao;
	}

	public void setLocationsDao(LocationsDao locationsDao) {
		this.locationsDao = locationsDao;
	}

	/**
	 * 查询产品信息 按照多个查询条件查询产品
	 */
	public List<ProductionInfo> findAllByParam(ProductionInfo param) {
		return findAllByParam(param, null);
	}

	/**
	 * 查询产品信息 按照多个查询条件查询产品
	 * 
	 * @param sort
	 */
	public List<ProductionInfo> findAllByParam(ProductionInfo param, Sort sort) {
		// 多条件组装
		Specification<ProductionInfo> specification = new Specification<ProductionInfo>() {

			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ProductionInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				String name = param.getName();
				Integer activityTypeCode = param.getActivityTypeCode();
				Integer during = param.getDuring();
				Integer groupNumber = param.getGroupNumber();
				Integer orderSimulatePriceCode = param.getOrderSimulatePriceCode();
				Integer orderGroupPriceCode = param.getOrderGroupPriceCode();
				Integer activityCityCode = param.getActivityCityCode();
				String descirbeText1 = param.getDescirbeText1();

				Path<String> namePath = root.get("name");
				Path<Integer> activityTypeCodePath = root.get("activityTypeCode");
				Path<Integer> duringPath = root.get("during");
				Path<Integer> groupNumberPath = root.get("groupNumber");
				Path<Integer> orderSimulatePriceCodePath = root.get("orderSimulatePriceCode");
				Path<Integer> orderGroupPriceCodePath = root.get("orderGroupPriceCode");
				Path<Integer> activityCityCodePath = root.get("activityCityCode");
				Path<String> descirbeText1Path = root.get("descirbeText1");

				List<Predicate> list = new ArrayList<Predicate>();

				if (!StringUtils.isEmpty(name)) {
					list.add(cb.like(namePath, "%" + name + "%"));
				}
				if (null != activityTypeCodePath) {
					list.add(cb.equal(activityCityCodePath, activityTypeCode));
				}
				if (null != duringPath) {
					list.add(cb.equal(activityCityCodePath, during));
				}
				if (null != groupNumberPath) {
					list.add(cb.equal(activityCityCodePath, groupNumber));
				}
				if (null != orderSimulatePriceCodePath) {
					list.add(cb.equal(activityCityCodePath, orderSimulatePriceCode));
				}
				if (null != orderGroupPriceCodePath) {
					list.add(cb.equal(activityCityCodePath, orderGroupPriceCode));
				}
				if (null != activityCityCodePath) {
					list.add(cb.equal(activityCityCodePath, activityCityCode));
				}
				if (!StringUtils.isEmpty(descirbeText1)) {
					list.add(cb.like(descirbeText1Path, "%" + descirbeText1 + "%"));
				}

				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};

		List<ProductionInfo> infos;

		if (sort == null) {
			infos = productionInfoDao.findAll(specification);
		} else {
			infos = productionInfoDao.findAll(specification, sort);
		}

		// 封装产品的场地信息
		for (ProductionInfo info : infos) {
			ProductionLocationRelation pLocationRelation = pLocationRelationDao.findByProductionId(info.getId());
			if (pLocationRelation != null) {
				Optional<Locations> locationsOpt = locationsDao.findById(pLocationRelation.getLocationId());
				if (locationsOpt != null) {
					info.setLocations(locationsOpt.get());
				}
			}
		}

		return productionInfoDao.findAll(specification);
	}

	/**
	 * 创建新的产品 上架新的产品信息
	 */
	public ProductionInfo save(ProductionInfo param) {
		return productionInfoDao.saveAndFlush(param);
	}

	/**
	 * 编辑产品信息 编辑修改产品信息
	 */
	@Transactional
	@Modifying
	public void edit(ProductionInfo param) {
		Optional<ProductionInfo> infoOptional = productionInfoDao.findById(param.getId());
		if (infoOptional != null && infoOptional.get() != null) {
			ProductionInfo info = infoOptional.get();
			if (!StringUtils.isEmpty(param.getName())) {
				info.setName(param.getName());
			}
			if (!StringUtils.isEmpty(param.getStatus())) {
				info.setStatus(param.getStatus());
			}
		}
	}

	/**
	 * 下架产品 将产品放入回收站（随时可还原恢复），客户端无法查看该产品
	 */
	@Transactional
	@Modifying
	public void unShift(ProductionInfo param) {
		param.setStatus(1);
		edit(param);
	}
}
