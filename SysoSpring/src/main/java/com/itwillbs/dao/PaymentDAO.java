package com.itwillbs.dao;

import com.itwillbs.domain.OrderItemVO;
import com.itwillbs.domain.PaymentVO;

public interface PaymentDAO {
	
	public void insertOrder(OrderItemVO oi_vo); // 주문작성 시 orderItem table  

	public void insertPayment(PaymentVO p_vo); // 주문작성 시 payment table 
	
	public PaymentVO paymentComplete(PaymentVO payment_vo); // 아임포트


}
