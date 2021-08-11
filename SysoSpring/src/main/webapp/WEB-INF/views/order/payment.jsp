<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <script src="resources/js/jquery-3.1.1.min.js"></script> -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<title>import point test</title>
</head>
<body>
	<div class="card-body bg-white mt-0 shadow">
		<p style="font-weight: bold">${memVO.member_id }님의 포인트 충전
			창입니다.</p>
		<label class="box-radio-input"> <input type="radio"
			name="chargeMoney" value="5000"> 5,000원
		</label> <label class="box-radio-input"> <input type="radio"
			name="chargeMoney" value="10000"> 10,000원
		</label> <label class="box-radio-input"> <input type="radio"
			name="chargeMoney" value="20000"> 20,000원
		</label> <label class="box-radio-input"> <input type="radio"
			name="chargeMoney" value="30000"> 30,000원
		</label> <label class="box-radio-input"> <input type="radio"
			name="chargeMoney" value="40000"> 40,000원
		</label> <label class="box-radio-input"> <input type="radio"
			name="chargeMoney" value="50000"> 50,000원
		</label>
		<p style="color: #ac2925; margin-top: 30px">
			최소 충전금액은 5,000원이며 <br /> 최대 충전금액은 50,000원 입니다.
		</p>
		<button type="button" class="btn btn-lg btn-block  btn-custom"
			id="pointCharge">충 전 하 기</button>

		<%-- 		아이디1 : ${vo.member_id}  --%>
	</div>

	<script type="text/javascript">

		$("#pointCharge").click(function() {
			
			// merchant_uid 생성해서 넣어주기 
			var merchant_uid = "my_2"; // my_5121는 임의로 넣은 값임


			var payment_price = $('input[name="chargeMoney"]:checked').val(); 
			
			var IMP = window.IMP;
			IMP.init('imp67131243');

			IMP.request_pay({
				// 주문자 정보
				pg : 'html5_inicis', 
				pay_method : 'card',
				/*
				 'samsung':삼성페이,
				 'card':신용카드,
				 'trans':실시간계좌이체,
				 'vbank':가상계좌,
				 'phone':휴대폰소액결제
				 */
				merchant_uid : merchant_uid, //주문코드
				name : '아주 편안한 의자', // 상품 번호
				amount : payment_price, // 주문 금액 
				buyer_email : '${memVO.member_email}',
				buyer_name : '${memVO.member_name}',
				buyer_tel : '${memVO.member_name}',
				buyer_addr : '${memVO.member_address}',
				buyer_postcode : '${memVO.member_zipcode}',
			}, function(rsp) {
				console.log(rsp);
				if (rsp.success) {
					var msg = '결제가 완료되었습니다.';
					msg += '고유ID : ' + rsp.imp_uid;
					msg += '상점 거래ID : ' + rsp.merchant_uid; // syso 변경?
					msg += '결제 금액 : ' + rsp.paid_amount;
					msg += '카드 승인번호 : ' + rsp.apply_num;
					//고객결제완료 후, 결제금액 검증
					$.ajax({
						url : "/paymentComplete", // 충전 금액값을 보낼 url 설정
						type : "GET",
						success: function(data){
							if(data.ok){
								//여기에 구매성공 후, 다음 로직 값
							}else{
								//구매 실패 로직 작성.
							}
						}
					});
					
				} else {
					var msg = '결제에 실패하였습니다.';
					msg += '에러내용 : ' + rsp.error_msg;
					//document.location.href="/home";
					alert(msg);
				}
				alert(msg);
			});
		});
	</script>



</body>
</html>