<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#container {
	margin: 0 auto;
	width: 70%;
}

#agent_profile {
	width: 100%;
	height: 500px;
}

#agent_profile_left {
	width: 50%;
	float: left;
	background-color: lightcoral;
}

#agent_profile_right {
	width: 50%;
	float: right;
	background-color: lightblue;
}

#map {
	width: 90%;
	height: 300px;
}

h2 {
	padding: 10px;
}
</style>

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
	</div>

	<div id="container">
		<div id="agent_profile">
			<table>
				<tr>
					<td>
						<h2>지피타워부동산중개법인</h2>
					</td>
					<td>
						<!-- Button trigger modal -->
						<button id="test_modal" type="button" class="btn btn-primary"
							data-toggle="modal" data-target="#myModal">수정하기</button>


					</td>
				</tr>
			</table>
			<div id="agent_profile_left">
				<h3>
					<i class="bi bi-person-circle"></i> 김부식
				</h3>
				<h3>
					<i class="bi bi-telephone-fill"></i> 02-123-1234
				</h3>
				<h3>인사말</h3>
				<p>Lorem ipsum dolor, sit amet consectetur adipisicing elit.
					Reprehenderit, possimus asperiores. Aspernatur aliquam ut iure
					molestias voluptas, quos officia voluptates nisi cumque modi amet
					porro libero quidem, molestiae ratione ea?</p>
			</div>
			<div id="agent_profile_right">
				<table>
					<tr>
						<td>
							<h3>오시는 길</h3>
						</td>
						<td>
							<p>서울 중랑구 면목로 92길</p>
						</td>
					</tr>
				</table>
				<div id="map"></div>
				<script type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e8a57933716f8366face6fc1f10e2f85&libraries=services">
					
				</script>
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
														content : '<div style="width:150px;text-align:center;padding:6px 0;">지피타워부동산중개법인</div>'
													});
											infowindow.open(map, marker);

											// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
											map.setCenter(coords);
										}
									});
				</script>
			</div>
		</div>
		<hr>
		<div>
			<h2>
				매물 목록 <i class="bi bi-plus-circle-fill"></i>
			</h2>
			<div class="row" style="margin: 10px;">
				<div class="col-sm-6">
					<div class="card">
						<div class="card-body">
							<table>
								<tr>
									<td>여기에 이미지</td>
									<td>
										<h4>월세1000/50</h4>
										<p>대전광역시 중구 문화동</p>
										<p>49.32m²▫ 2층</p>
										<p>매물 상세 설명</p>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="card">
						<div class="card-body">
							<table>
								<tr>
									<td>여기에 이미지</td>
									<td>
										<h4>월세1000/50</h4>
										<p>대전광역시 중구 문화동</p>
										<p>49.32m²▫ 2층</p>
										<p>매물 상세 설명</p>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<div>
			<h2>후기</h2>
			<div class="row" style="margin: 10px;">
				<div class="col-sm-6">
					<div class="card">
						<div class="card-body">
							<table>
								<tr>
									<td><i class="bi bi-person-circle"></i>
										<p>username</p></td>
									<td>
										<p>★ 4.5</p>
										<p>후기 내용</p>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="card">
						<div class="card-body">
							<table>
								<tr>
									<td><i class="bi bi-person-circle"></i>
										<p>username</p></td>
									<td>
										<p>★ 4.5</p>
										<p>후기 내용</p>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>