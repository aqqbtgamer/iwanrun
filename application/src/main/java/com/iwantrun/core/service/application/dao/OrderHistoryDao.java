package com.iwantrun.core.service.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iwantrun.core.service.application.domain.OrderHistory;

public interface OrderHistoryDao extends JpaRepository<OrderHistory, Integer> {
	
	@Query(value="select history.id ,history.orderId,history.changeInfo,history.change_by ,history.changeTime, "
			+ "login.mobileNumber "
			+ "from OrderHistory history  "
			+ "left join PurchaserAccount login on history.change_by = login.id "
			+ "order by history.changeTime desc ")
	public List<Object[]> queryOrderHistory() ;

}
