package com.iwantrun.core.service.application.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public List<ProductionInfo> findAllByParam(ProductionInfo param, Pageable page) {
		ExampleMatcher matcher = ExampleMatcher.matchingAll();
		GenericPropertyMatcher strMatcher = GenericPropertyMatchers.contains();
		GenericPropertyMatcher numMatcher = GenericPropertyMatchers.exact();
		
		if (!StringUtils.isEmpty(param.getName())) {
			matcher.withMatcher("name", strMatcher);
		}
		if (null != param.getActivityTypeCode()) {
			matcher.withMatcher("activityTypeCode", numMatcher);
		}
		if (null !=  param.getDuring()) {
			matcher.withMatcher("during", numMatcher);
		}
		if (null != param.getGroupNumber()) {
			matcher.withMatcher("groupNumber", numMatcher);
		}
		if (null != param.getOrderSimulatePriceCode()) {
			matcher.withMatcher("orderSimulatePriceCode", numMatcher);
		}
		if (null != param.getOrderGroupPriceCode()) {
			matcher.withMatcher("orderGroupPriceCode", numMatcher);
		}
		if (null != param.getActivityCityCode()) {
			matcher.withMatcher("activityCityCode", numMatcher);
		}
		if (!StringUtils.isEmpty(param.getDescirbeText1())) {
			matcher.withMatcher("descirbeText1", strMatcher);
		}
		Example<ProductionInfo> example = Example.of(param, matcher);
		
		List<ProductionInfo> infos;
		if (page == null) {
			infos = productionInfoDao.findAll(example);
		} else {
			Page<ProductionInfo> pageProductionInfo = productionInfoDao.findAll(example, page);
			infos =pageProductionInfo.getContent();
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

		return infos;
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