package com.itwillbs.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.OrderItemVO;
import com.itwillbs.domain.PaymentVO;

@Repository
public class PaymentDAOImpl implements PaymentDAO {

	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "com.itwillbs.paymentMapper";

	@Override // 주문작성 시 orderItem table
	public void insertOrder(OrderItemVO oi_vo) {

		sqlSession.insert(namespace + ".insertOrder", oi_vo);
	}

	@Override // 주문작성 시 payment table
	public void insertPayment(PaymentVO p_vo) {

		System.out.println("PaymentDAOImpl___insertPayment()");

		sqlSession.insert(namespace + ".insertPayment", p_vo);
	}

	@Override // 아임포트
	public PaymentVO paymentComplete(PaymentVO payment_vo) {
		// TODO Auto-generated method stub
		return null;
	}
}
