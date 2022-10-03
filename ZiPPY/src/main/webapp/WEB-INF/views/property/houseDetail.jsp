<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>매물 상세페이지</title>
<!-- houseDetail CSS -->
<link
	href="<%=request.getContextPath()%>/resources/css/property/houseDetail.css"
	rel="stylesheet">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<!-- 부트스트랩 아이콘 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />

<!-- Vue -->
<script src="https://cdn.jsdelivr.net/npm/vue@2.7.8/dist/vue.js"></script>
<script type="module"
	src="<%=request.getContextPath()%>/resources/js/app.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/style.css">
</head>

<body>
	<!-- header & nav -->
	<div id="app">
		<main-header></main-header>
		<!-- <main-nav></main-nav> -->
	</div>

	<div id="container">
		<!-- 매물 사진 -->
		<div id="carouselExampleIndicators" class="carousel slide"
			data-bs-ride="carousel" data-bs-interval="false">
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="0" class="active" aria-current="true"
					aria-label="Slide 1"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="1" aria-label="Slide 2"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="2" aria-label="Slide 3"></button>
			</div>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="잠만보2.jpg" class="d-block w-100" alt="매물사진">
				</div>
				<div class="carousel-item">
					<img src="잠만보2.jpg" class="d-block w-100" alt="매물사진">
				</div>
				<div class="carousel-item">
					<img src="잠만보2.jpg" class="d-block w-100" alt="매물사진">
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>

		<section>
			<article>
				<h4>가격정보</h4>
				<table>
					<tr>
						<th>월세</th>
						<td>500/35</td>
					</tr>
					<tr>
						<th>관리비</th>
						<td>매월 8만원</td>
					</tr>
					<tr>
						<th>주차</th>
						<td>불가능</td>
					</tr>
					<tr>
						<th>한달 예상 주거비용</th>
						<td>43만원<br> <small>월세 + 관리비 별도 금액으로 부과되는 항목 제외</small>
						</td>
					</tr>
				</table>
				<hr>
			</article>
			<article>
				<h4>상세정보</h4>
				<table>
					<tr>
						<th>방종류</th>
						<td>원룸</td>
					</tr>
					<tr>
						<th>층수</th>
						<td>반지층</td>
					</tr>
					<tr>
						<th>전용면적</th>
						<td>11.28m²</td>
					</tr>
					<tr>
						<th>난방종류</th>
						<td>개별난방</td>
					</tr>
					<tr>
						<th>빌트인</th>
						<td>빌트인 주방</td>
					</tr>
					<tr>
						<th>건축년도</th>
						<td>2022년</td>
					</tr>
				</table>
				<hr>
			</article>
			<article>
				<h4>위치 및 주변 시설</h4>
				<p>서울시 은평구 진관동 66</p>
				<div id="map"></div>

				<script type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e8a57933716f8366face6fc1f10e2f85&libraries=services"></script>
				<script>
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					mapOption = {
						center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
						level : 3
					// 지도의 확대 레벨
					};

					// 지도를 생성합니다    
					var map = new kakao.maps.Map(mapContainer, mapOption);

					// 주소-좌표 변환 객체를 생성합니다
					var geocoder = new kakao.maps.services.Geocoder();

					// 주소로 좌표를 검색합니다
					geocoder
							.addressSearch(
									'제주특별자치도 제주시 첨단로 242',
									function(result, status) {

										// 정상적으로 검색이 완료됐으면 
										if (status === kakao.maps.services.Status.OK) {

											var coords = new kakao.maps.LatLng(
													result[0].y, result[0].x);

											// 결과값으로 받은 위치를 마커로 표시합니다
											var marker = new kakao.maps.Marker(
													{
														map : map,
														position : coords
													});

											// 인포윈도우로 장소에 대한 설명을 표시합니다
											var infowindow = new kakao.maps.InfoWindow(
													{
														content : '<div style="width:150px;text-align:center;padding:6px 0;">건물명</div>'
													});
											infowindow.open(map, marker);

											// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
											map.setCenter(coords);
										}
									});
				</script>
				<hr>
			</article>
			<article>
				<h4>시세/실거래가</h4>
				아파트의 경우
				<hr>
			</article>
			<article>
				<h4>상세 설명</h4>
				<p>보라매역 도보 3분 역세권, 저렴한 풀옵션 원룸입니다. ★ 특징 ★ ✔ 7호선 보라매역 도보 3분거리 역세권!
					✔ 공동현관 보안 등으로 안전합니다. ✔ 즉시입주 풀옵션 매물로 너무 좋습니다. ✔ 카페, 편의점, 병원, 은행이 가까워
					살기 좋습니다.</p>
			</article>
		</section>

		<aside>
			<div class="card" style="width: 18rem;">
				<div class="card-body">
					<p>매물번호 2358</p>
					<h3>월세 500/35</h3>
					<p>서울특별시 동작구 대방동 415-52</p>
					<p>원룸</p>
					<p>11.28m²</p>
					<p>반지층</p>
					<hr>
					<h4>
						<i class="bi bi-person-circle"></i> <a
							href="/zippy/property/agentDetail">중개법인명</a> >
					</h4>
					<button type="button" class="btn btn-primary" style="width: 100%">문의하기</button>
				</div>
			</div>
		</aside>


	</div>
	<!-- END container -->

	<!-- Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous">
		
	</script>
</body>
</html>