package com.itwillbs.service;

import java.sql.Timestamp;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.PaymentDAO;
import com.itwillbs.domain.OrderItemVO;
import com.itwillbs.domain.PaymentVO;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Inject
	PaymentDAO pay_dao;

	@Override
	public void insertOrder(OrderItemVO oi_vo) { // 주문작성 시 orderItem table

		oi_vo.setOrderItem_date(new Timestamp(System.currentTimeMillis()));

		// 메서드 호출
		pay_dao.insertOrder(oi_vo);

	}

	@Override
	public void insertPayment(PaymentVO p_vo) { // 주문작성 시 payment table
		System.out.println("PaymentServiceImpl - insertPayment");

		p_vo.setPayment_date(new Timestamp(System.currentTimeMillis()));

		// 메서드 호출
		pay_dao.insertPayment(p_vo);
	}

	@Override // 아임포트
	public PaymentVO paymentComplete(PaymentVO payment_vo) {
		System.out.println("PaymentServiceImpl_paymentComplete");
		return pay_dao.paymentComplete(payment_vo);
	}

}
