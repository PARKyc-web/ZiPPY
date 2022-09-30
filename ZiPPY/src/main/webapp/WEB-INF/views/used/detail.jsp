<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous">
	
</script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<style>
#container {
	width: 1200px;
	margin: 0 auto;
}

.used-main-title {
	margin: 50px;
}

.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

.b-example-divider {
	height: 3rem;
	background-color: rgba(0, 0, 0, .1);
	border: solid rgba(0, 0, 0, .15);
	border-width: 1px 0;
	box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em
		rgba(0, 0, 0, .15);
}

.b-example-vr {
	flex-shrink: 0;
	width: 1.5rem;
	height: 100vh;
}

.bi {
	vertical-align: -.125em;
	fill: currentColor;
}

.nav-scroller {
	position: relative;
	z-index: 2;
	height: 2.75rem;
	overflow-y: hidden;
}

.nav-scroller .nav {
	display: flex;
	flex-wrap: nowrap;
	padding-bottom: 1rem;
	margin-top: -1px;
	overflow-x: auto;
	text-align: center;
	white-space: nowrap;
	-webkit-overflow-scrolling: touch;
}

.nav-link {
	color: black;
}

.nav-link dropdown-toggle {
	float: right;
	width: 50px;
}

.nav-link:hover {
	color: #B3E3C3;
}

.nav-item dropdown {
	float: right;
}

.used-dropdown {
	list-style: none;
}

#navbarDropdown {
	color: #212529;
}

.thumbnail-wrap {
	display: block;
}

.container-fluid pt-5 {
	width: 1200px;
}

.form-control {
	width: 150px;
}

#used-cate-update {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 10px;
}

#used-cate-update button {
	border: 1px solid grey;
	background-color: white;
	border-radius: 10px;
}

#used-img-slide {
	display: flex;
	padding: 30px 0;
}

#used-detail-img {
	width: 450px;
	height: 500px;
}

#used-detail-info {
	color: black;
}

#used-product-title {
	font-size: 24px;
	padding-left: 30px;
}

#used-product-price {
	font-size: 40px;
}

#bi bi-heart {
	width: 20px;
	height: 20px;
}

#used-issell-location {
	font-size: 15px;
}

#used-issell-location ul li {
	color: rgb(153, 153, 153);
	margin-bottom: 20px;
}

#used-detail-report {
	color: rgb(204, 204, 204);
	font-size: 16px;
	display: inline-block;
	-webkit-box-align: center;
	align-items: center;
	border: none;
	margin-bottom: 20px;
}

#used-wish-eye {
	justify-content: space-between;
	display: flex;
	padding-left: 10px;
}

#used-detail-total {
	width: 750px;
}

#used-profile-chat {
	display: flex;
	margin-top: 120px;
	justify-content: space-between;
}

#used-view-wish {
	display: flex;
}

#used-detail-img {
	width: 430px;
	height: 430px;
	padding-right: 40px;
}

#used-detail-img img {
	width: 100%;
	height: 100%;
}

#used-detail-info-div {
	width: 1200px;
}

#used-detail-pofile {
	border: none;
	background-color: white;
	font-size: x-large;
}

#used-detail-main {
	margin-bottom: 60px;
}

.used-detail-wish {
	border: none;
	color: white;
	background-color: #B3E3C3;
	font-size: x-large;
	border-radius: 10px;
}

#used-detail-info-div {
	margin-bottom: 30px;
}
</style>
</head>
<body>
	<div id="container">
		<div class="nav-scroller bg-body shadow-sm">
			<nav class="nav" aria-label="Secondary navigation">
				<a class="nav-link active" aria-current="page" href="#">전체</a> <a
					class="nav-link" href="#">침실</a> <a class="nav-link" href="#">옷장/수납</a>
				<a class="nav-link" href="#">주방</a> <a class="nav-link" href="#">욕실</a>
				<a class="nav-link" href="#">서재</a> <a class="nav-link" href="#">다용도실</a>
			</nav>
		</div>
		<div>
			<div class="used-main-title">
				<h3>판매중인 중고제품</h3>
			</div>
		</div>
		<hr>
		<div id="used-cate-update">
			<div>
				<button>침대</button>
			</div>
			<div>
				<button>수정하기</button>
			</div>
		</div>
		<div id="used-detail-main">
			<div id="used-img-slide">
				<div id="used-detail-img">
					<img id="used-detail-img"
						src="https://blog.kakaocdn.net/dn/bGu5TC/btrGZLBBodm/QunLukZnzgvWcoygDFziO0/img.jpg">
				</div>
				<div id="used-detail-total">
					<div id="used-product-title">
						햄찌
						<div id="used-product-price">
							1,000,000원
							<hr>
							<div 찜 조회수 신고하기 판매여부 거래지역>
								<div id="used-wish-eye">
									<div id="used-view-wish">
										<img
											src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAAjhJREFUWAnFl1uPKUEUhbdCxF2Iu7h78f9/ixdexANeSNxCkJnz1ZwSRncrM0OvhK6ufVmrdiu1O/DxD/INq9VKFouFbDYbOR6PEggEJBKJSDqdlkKhIKFQ6FvE1+3pdJL5fC7EHw4HIXU4HJZEIiHZbFZSqdRdXOBaAEHj8VjW6/Wdo5kIBoNSLBalVCppYcxDNJvNNPn5fDaud9dkMimNRkMvxhgvAna7nQyHQ2EVNmBV3W5Xu45GI10tmziq1+v1JBaLaXctANLBYKDLbZPE+MTjcT3cbrdmyurKY+n3+/pRKiImk8nT5MRB/Cw5cfyu4ARqv9/LcrnUN+/8ghNuxcBhI7xcC5xwK7aMX4BbsfX8AtzKa9++Whjcij8WvwC3Yk/6BbiV+TPxQwTcKpPJ+MGtOeFWHBB+PAY44VYctZxu7waccOuzIJ/Pv7UKrB5OoAUopfQ5/a4q0BPAeRHAgG4nl8vpyVd+wQGXwZeM/3f1el2i0aix/fmV3HBc40YAZel0Oq4933Xgs2M6IXKb0pv4GwFM0ny22+1Lv2ccf3Pl195qtW56QZPvTgAG9mez2fwTEZCTy6kjhstRAAba6FqtxvBXIAe53OAqgADeASqVilvsw3liyeEFTwEElstl/fFK4mSzjXsogOSshBcRW+BrWzkrARBXq1UrEZDjawtrAUYEpXXDs+TkeUoAAZTWScRPyMnn/JqLxQPm+U6nU+2FIDPnEeZourycOlofTF4LeODqav4EUxqvNxGf2nsAAAAASUVORK5CYII="
											width="16" height="16" alt="상품 상태 아이콘"> <img
											src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACwAAAAaCAYAAADMp76xAAAAAXNSR0IArs4c6QAABAdJREFUWAm9mFtIFFEYx9tZ11UW1tLoaoGEPShqq3ahgogyIgnqQXqIgih6qKgEH4JIqCgIIoowIrSn6i0irOxCQdAN7wb2IiSlSUZuGJGyumu/b9lZZo8zs7ObdeBwvvNd/uc/53zznWFcs9Js7e3tczVNWzs1NbUKiErGfJfLNYcxVyCRg8g/GAeZdiC3eTyeN2VlZd/Enm5zpRLY09Pjm5yc3EnMbghUMbpTiYd8BP8X9Dt+v/9uYWHhz1TixdcR4YGBgezh4eFD+J+gz5XAGWijYFzKycm5nArxpIQ5+hqAr9AXzgBJM4ggqXWyvLz8uplR1VkShmgOR3iVo9+jBv2LOWs9pu+H+JAdvilhyC4j6AldxqSNhT7g1Oh2u59mZWV9loDx8fGl4XB4C+IBHrpIdA7ad7C2V1RUvLPynUa4u7s7wIvVQsB8qyCDfgK5jgUaWChs0MdFyLo7OjoOo7hI98QN1sJvsHaB+cDMJYFwV1fXCnblJY5+M2dFN8GOVgcCgWeK3nQKdhXYDzE6IR2GdA2k76lgmq7o7OxcBGAzcydkJazOKVlxjvnWieyguTmZ25y21PiEFt3h/v7+rJGRkddYyhOsFhOe/gMvR6lVGliEzZL0YGPep5DTw16vd2VJScmAjhnd4WAweBaFI7KxwEaVLCQyIHOafB2ULrLo9IVkjMU0GnVJ5PmhUOim0UejIqwGuNaoTCZLNVB9yNFTkUikHqzF0kUWnepnFqv6GOdgbWYDDuo6jaduYOLWFU5Gvgk+qX4A73ei08ue6ms3B/ui3LbiozExLUd2AOxSQnWx850h2+f8/PyQYGksfoRxMhVguRRUf06qyYnOLFaNM87BjdAP0KMbq1Fu2phcMDolk2M3WIIbOGf5JjgD1hfpIosuwYmJWazqo8yvGG++6NH29vZmjo2NPcdxveJsOoXQ/yprXcKpsrLyt04kWtaKi4tDPp9vB0T6dIPdSN4Xxa5bO7dpNomR2GkGEwVchjIyMrYbyYpbwstDGSqkHL0CdJ4Jhqr6l1ezfNhvhGynumj8ahYDOSc7vI7+UeZJmke+DajjR3lAy7IoNvERX/CcfEd8pRBsMCMrfBJ2WCdITi8gpx8xD+g6u1FyGvtff15KSlLjt5aWllpumClhIdfX1+cdHR09D0gtu2TpZ/cgKdqasrOzj/M+/bKLS0qEb4JN5PU1QJbbAaVrY0M+UQKPkY73nWAkJSwgkoe84fsQ6+lLRDcD7Stkz3FV35Aq5RTPEWEdLFavt7HQXnTVPEimbnM4ThDbQtytvLy85oKCgnGHcXG3lAjHoxAogbNJlTWIq6VDQn6k5DLmih+y/EgJMsqPlFaOvZW3/y0v1A+xp9v+ADhPuomDsZuZAAAAAElFTkSuQmCC"
											width="21" height="13" alt="상품 상태 아이콘">
									</div>
									<button id="used-detail-report">
										<img
											src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAbBJREFUeNrsls9LAkEcxb+au+JSGIEQyGbBdg881CmConsE9Qd07S/p0qG69RcUdOzSoUt0UujS3c0kEQULU7R17c2wyhazNbvaesgHH9xZh3kzb35tpNfrUV/5fJ7+Stls9ks5FqCNKTDv/L6AjyAdiUrWi4AdcAXewDMwwTu4AQdAHbXxCrh3TJm55vpPAVvgHDyC7VEZM6M7sCbRlgGuwaGM8U9zvAkunbn0M/8nzvNpkBHr4MKnqVvHYDWI8RGY84wpFiNFUX5L8sxZlNJRL4E9UeVkMknpdJoSiQQvt9ttKpfLVK1WhVsXbIBb2RHvi96nUikyDGNgyhSPxymTyZCu614D2/UT9bqooqZpnrm6OyNYoNLGy6KKxWKRR/td3W6XCoWCV/sLfoyFJ5Bt29zAfbb3O9TpdLzaZzFND3NkcjUaDapUKoNyvV6nWq0ms7eHM2YqlUrUarXIsiwyTTPwbeX7dmJRs8hVVeXmoRkzNZtNzjCK0pg0MQ5NosX1BKwRetjCb6lcLje2ES+GHDk72G1m/MCu2hCNZ8HrZDv9j33MVtlMiJ58X38KMADfFnDPWur9bAAAAABJRU5ErkJggg=="
											width="15" height="15" alt="신고 아이콘">신고하기
									</button>
								</div>
								<div id="used-issell-location">
									<ul>
										<li>판매여부 판매 중</li>
										<li>거래지역 대구</li>
									</ul>
								</div>
								<div id="used-profile-chat">
									<div>
										<button id="used-detail-pofile">
											<i class="bi bi-person-circle">사용자</i>
										</button>
									</div>
									<div>
										<button class="used-detail-wish">
											<i class="bi bi-heart">찜</i>
										</button>
										<button class="used-detail-wish" width="30px">
											<i class="bi bi-chat">채팅하기</i>
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<hr>
		</div>
		<div id="used-detail-info-div">
			<h3 id="used-detail-info">상품정보</h3>
			<hr>
		</div>
		<div>대신귀 여운햄 찌를보 여드리 겠습니 다ㅇㅇ</div>
	</div>
</body>
</html>