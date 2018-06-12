package com.iwantrun.core.service.application.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

/**
 * 超级jpa实现类 
 * 实在搞不定jpa的复杂查询 可以使用本类直接呼叫sql
 * @author WXP22
 *
 * @param <T>
 */
@Repository
public class JPQLEnableRepository {
	
	@PersistenceContext 
    private EntityManager entityManager;

	public <T> List<T> findByJPQLAll(String jpql,Class<T> clazz){
		TypedQuery<T> query = this.entityManager.createQuery(jpql, clazz);
		List<T> resultList = query.getResultList();	
		return resultList;
	}
	
	public <T> List<T> findByJPQLPage(String jpql,Class<T> clazz,int page, int pageSize){
		TypedQuery<T> query = this.entityManager.createQuery(jpql, clazz);
		query.setFirstResult((page-1)*pageSize);
		query.setMaxResults(pageSize);
		List<T> resultList = query.getResultList();	
		return resultList;
	}
	
	public <T> T findOneJPQL(String jpql,Class<T> clazz){
		TypedQuery<T> query = this.entityManager.createQuery(jpql, clazz);
		return query.getSingleResult();
	}
	
	@SuppressWarnings("rawtypes")
	public List findByNativeSqlAll(String nativeSql){
		Query query = this.entityManager.createNativeQuery(nativeSql);
		return query.getResultList();
	}
	

	@SuppressWarnings("unchecked")
	public <T> List<T> findByNativeSqlAll(String nativeSql,Class<T> clazz){
		Query query = this.entityManager.createNativeQuery(nativeSql,clazz);
		return (List<T>)query.getResultList();
	}
	
	@SuppressWarnings("rawtypes")
	public List findByNativeSqlPage(String nativeSql,int page, int pageSize){
		Query query = this.entityManager.createNativeQuery(nativeSql);
		query.setFirstResult(page*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	
	@SuppressWarnings("rawtypes")
	public List findByNativeSqlPage(String nativeSql){
		Query query = this.entityManager.createNativeQuery(nativeSql);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> findByNativeSqlPage(String nativeSql,int page, int pageSize,Class<T> clazz){
		Query query = this.entityManager.createNativeQuery(nativeSql);
		query.setFirstResult((page-1)*pageSize);
		query.setMaxResults(pageSize);
		return (List<T>)query.getResultList();
	}	

}
