package com.iwantrun.core.service.application.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.iwantrun.core.service.application.dao.LocationsDao;
import com.iwantrun.core.service.application.dao.ProductionInfoDao;
import com.iwantrun.core.service.application.dao.ProductionLocationRelationDao;
import com.iwantrun.core.service.application.domain.Locations;
import com.iwantrun.core.service.application.domain.ProductionInfo;
import com.iwantrun.core.service.application.domain.ProductionInfoAttachments;
import com.iwantrun.core.service.application.domain.ProductionLocationRelation;
import com.iwantrun.core.service.utils.ThumbnailatorUtils;

@Service
public class ProductionInfoService {
	@Autowired
	private Environment env;
	@Autowired
	private ProductionInfoDao productionInfoDao;
	@Autowired
	private LocationsDao locationsDao;
	@Autowired
	private ProductionLocationRelationDao pLocationRelationDao;

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
	public Page<ProductionInfo> findAllByParam(ProductionInfo param) {
		Pageable page = PageRequest.of(0, Integer.MAX_VALUE);
		return findAllByParam(param, page);
	}

	/**
	 * 查询产品信息 按照多个查询条件查询产品
	 * 
	 * @param sort
	 */
	public Page<ProductionInfo> findAllByParam(ProductionInfo param, Pageable page) {
		ExampleMatcher matcher = ExampleMatcher.matchingAll();
		GenericPropertyMatcher strMatcher = GenericPropertyMatchers.contains();
		GenericPropertyMatcher numMatcher = GenericPropertyMatchers.exact();

		if (!StringUtils.isEmpty(param.getName())) {
			matcher.withMatcher("name", strMatcher);
		}
		if (null != param.getActivityTypeCode()) {
			matcher.withMatcher("activityTypeCode", numMatcher);
		}
		if (null != param.getDuring()) {
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

		/*
		 * List<ProductionInfo> infos; if (page == null) { infos =
		 * productionInfoDao.findAll(example); } else { Page<ProductionInfo>
		 * pageProductionInfo = productionInfoDao.findAll(example, page); infos =
		 * pageProductionInfo.getContent(); }
		 */

		Page<ProductionInfo> pageProductionInfo = productionInfoDao.findAll(example, page);
		List<ProductionInfo> infos = pageProductionInfo.getContent();

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
		// 相关联的案例

		return pageProductionInfo;
	}

	public ProductionInfo findById(Integer id) {
		Optional<ProductionInfo> productionInfoOpt = productionInfoDao.findById(id);
		ProductionInfo info = productionInfoOpt.get();
		if (info != null) {
			ProductionLocationRelation pLocationRelation = pLocationRelationDao.findByProductionId(info.getId());
			if (pLocationRelation != null) {
				Optional<Locations> locationsOpt = locationsDao.findById(pLocationRelation.getLocationId());
				if (locationsOpt != null) {
					info.setLocations(locationsOpt.get());
				}
			}
		}
		return info;
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

	/**
	 * 创建新的产品 上架新的产品信息 保存产品附件
	 */
	public boolean add(ProductionInfo info, List<ProductionInfoAttachments> infoAttachments) {
		// TODO Auto-generated method stub

		return false;
	}

	/**
	 * 生成主图缩略图
	 * 
	 * @param mainImageLarge
	 *            主图全路径
	 * @param request 
	 * @return 
	 * @throws IOException
	 */
	public String thumbnailator(String mainImageLarge, HttpServletRequest request) throws IOException {
		String imageIconPath=ThumbnailatorUtils.thumbnailator(mainImageLarge);
		if(imageIconPath!=null) {
			String contextPath=request.getContextPath();
			String url=request.getRequestURL().toString();
			String basePath=url.split(contextPath)[0]+contextPath;
			return basePath+imageIconPath;
		}
		return null;
	}
}
