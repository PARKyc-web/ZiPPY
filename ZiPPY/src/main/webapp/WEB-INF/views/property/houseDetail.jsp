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

	<div id="house_datail_container">
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
				<div id="container">
					<div id="rvWrapper">
						<div id="roadview" style="width: 100%; height: 100%;"></div>
						<!-- 로드뷰를 표시할 div 입니다 -->
						<div id="close" title="로드뷰닫기" onclick="closeRoadview()">
							<span class="img">닫기</span>
						</div>
					</div>
					<div id="mapWrapper">
						<div id="map" style="width: 100%; height: 100%"></div>
						<!-- 지도를 표시할 div 입니다 -->
						<div id="roadviewControl" onclick="setRoadviewRoad()"></div>
					</div>
				</div>

				<script type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e8a57933716f8366face6fc1f10e2f85&libraries=services"></script>
				<script>
					var overlayOn = false, // 지도 위에 로드뷰 오버레이가 추가된 상태를 가지고 있을 변수
					container = document.getElementById('container'), // 지도와 로드뷰를 감싸고 있는 div 입니다
					mapWrapper = document.getElementById('mapWrapper'), // 지도를 감싸고 있는 div 입니다
					mapContainer = document.getElementById('map'), // 지도를 표시할 div 입니다 
					rvContainer = document.getElementById('roadview'); //로드뷰를 표시할 div 입니다

					var mapCenter = new kakao.maps.LatLng(33.45042, 126.57091), // 지도의 중심좌표
					mapOption = {
						center : mapCenter, // 지도의 중심좌표
						level : 3
					// 지도의 확대 레벨
					};

					// 지도를 표시할 div와 지도 옵션으로 지도를 생성합니다
					var map = new kakao.maps.Map(mapContainer, mapOption);

					/****** 주소로 장소 표시하기  ******/
					// 주소-좌표 변환 객체를 생성합니다
					var geocoder = new kakao.maps.services.Geocoder();

					// 주소로 좌표를 검색합니다
					geocoder
							.addressSearch(
									'대구광역시 중구 남일동 중앙대로 403',
									function(result, status) {

										// 정상적으로 검색이 완료됐으면 
										if (status === kakao.maps.services.Status.OK) {

											//var coords = new kakao.maps.LatLng(
											mapCenter = new kakao.maps.LatLng(
													result[0].y, result[0].x);

											// 결과값으로 받은 위치를 마커로 표시합니다
											var marker = new kakao.maps.Marker(
													{
														map : map,
														position : mapCenter
													});

											// 인포윈도우로 장소에 대한 설명을 표시합니다
											var infowindow = new kakao.maps.InfoWindow(
													{
														content : '<div style="width:150px;text-align:center;padding:6px 0;">건물명</div>'
													});
											infowindow.open(map, marker);

											// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
											map.setCenter(mapCenter);
										}
									});
					/****** end - 주소로 장소 표시하기  ******/

					// 로드뷰 객체를 생성합니다 
					var rv = new kakao.maps.Roadview(rvContainer);

					// 좌표로부터 로드뷰 파노라마 ID를 가져올 로드뷰 클라이언트 객체를 생성합니다 
					var rvClient = new kakao.maps.RoadviewClient();

					// 로드뷰에 좌표가 바뀌었을 때 발생하는 이벤트를 등록합니다 
					kakao.maps.event.addListener(rv, 'position_changed',
							function() {

								// 현재 로드뷰의 위치 좌표를 얻어옵니다 
								var rvPosition = rv.getPosition();

								// 지도의 중심을 현재 로드뷰의 위치로 설정합니다
								map.setCenter(rvPosition);

								// 지도 위에 로드뷰 도로 오버레이가 추가된 상태이면
								if (overlayOn) {
									// 마커의 위치를 현재 로드뷰의 위치로 설정합니다
									marker.setPosition(rvPosition);
								}
							});

					// 마커 이미지를 생성합니다
					var markImage = new kakao.maps.MarkerImage(
							'https://t1.daumcdn.net/localimg/localimages/07/2018/pc/roadview_minimap_wk_2018.png',
							new kakao.maps.Size(26, 46), {
								// 스프라이트 이미지를 사용합니다.
								// 스프라이트 이미지 전체의 크기를 지정하고
								spriteSize : new kakao.maps.Size(1666, 168),
								// 사용하고 싶은 영역의 좌상단 좌표를 입력합니다.
								// background-position으로 지정하는 값이며 부호는 반대입니다.
								spriteOrigin : new kakao.maps.Point(705, 114),
								offset : new kakao.maps.Point(13, 46)
							});

					// 드래그가 가능한 마커를 생성합니다
					var marker = new kakao.maps.Marker({
						image : markImage,
						position : mapCenter,
						draggable : true
					});

					// 마커에 dragend 이벤트를 등록합니다
					kakao.maps.event.addListener(marker, 'dragend', function(
							mouseEvent) {

						// 현재 마커가 놓인 자리의 좌표입니다 
						var position = marker.getPosition();

						// 마커가 놓인 위치를 기준으로 로드뷰를 설정합니다
						toggleRoadview(position);
					});

					//지도에 클릭 이벤트를 등록합니다
					kakao.maps.event.addListener(map, 'click', function(
							mouseEvent) {

						// 지도 위에 로드뷰 도로 오버레이가 추가된 상태가 아니면 클릭이벤트를 무시합니다 
						if (!overlayOn) {
							return;
						}

						// 클릭한 위치의 좌표입니다 
						var position = mouseEvent.latLng;

						// 마커를 클릭한 위치로 옮깁니다
						marker.setPosition(position);

						// 클락한 위치를 기준으로 로드뷰를 설정합니다
						toggleRoadview(position);
					});

					// 전달받은 좌표(position)에 가까운 로드뷰의 파노라마 ID를 추출하여
					// 로드뷰를 설정하는 함수입니다
					function toggleRoadview(position) {
						rvClient.getNearestPanoId(position, 50,
								function(panoId) {
									// 파노라마 ID가 null 이면 로드뷰를 숨깁니다
									if (panoId === null) {
										toggleMapWrapper(true, position);
									} else {
										toggleMapWrapper(false, position);

										// panoId로 로드뷰를 설정합니다
										rv.setPanoId(panoId, position);
									}
								});
					}

					// 지도를 감싸고 있는 div의 크기를 조정하는 함수입니다
					function toggleMapWrapper(active, position) {
						if (active) {

							// 지도를 감싸고 있는 div의 너비가 100%가 되도록 class를 변경합니다 
							container.className = '';

							// 지도의 크기가 변경되었기 때문에 relayout 함수를 호출합니다
							map.relayout();

							// 지도의 너비가 변경될 때 지도중심을 입력받은 위치(position)로 설정합니다
							map.setCenter(position);
						} else {

							// 지도만 보여지고 있는 상태이면 지도의 너비가 50%가 되도록 class를 변경하여
							// 로드뷰가 함께 표시되게 합니다
							if (container.className.indexOf('view_roadview') === -1) {
								container.className = 'view_roadview';

								// 지도의 크기가 변경되었기 때문에 relayout 함수를 호출합니다
								map.relayout();

								// 지도의 너비가 변경될 때 지도중심을 입력받은 위치(position)로 설정합니다
								map.setCenter(position);
							}
						}
					}

					// 지도 위의 로드뷰 도로 오버레이를 추가,제거하는 함수입니다
					function toggleOverlay(active) {
						if (active) {
							overlayOn = true;

							// 지도 위에 로드뷰 도로 오버레이를 추가합니다
							map
									.addOverlayMapTypeId(kakao.maps.MapTypeId.ROADVIEW);

							// 지도 위에 마커를 표시합니다
							marker.setMap(map);

							// 마커의 위치를 지도 중심으로 설정합니다 
							marker.setPosition(map.getCenter());

							// 로드뷰의 위치를 지도 중심으로 설정합니다
							toggleRoadview(map.getCenter());
						} else {
							overlayOn = false;

							// 지도 위의 로드뷰 도로 오버레이를 제거합니다
							map
									.removeOverlayMapTypeId(kakao.maps.MapTypeId.ROADVIEW);

							// 지도 위의 마커를 제거합니다
							marker.setMap(null);
						}
					}

					// 지도 위의 로드뷰 버튼을 눌렀을 때 호출되는 함수입니다
					function setRoadviewRoad() {
						var control = document
								.getElementById('roadviewControl');

						// 버튼이 눌린 상태가 아니면
						if (control.className.indexOf('active') === -1) {
							control.className = 'active';

							// 로드뷰 도로 오버레이가 보이게 합니다
							toggleOverlay(true);
						} else {
							control.className = '';

							// 로드뷰 도로 오버레이를 제거합니다
							toggleOverlay(false);
						}
					}

					// 로드뷰에서 X버튼을 눌렀을 때 로드뷰를 지도 뒤로 숨기는 함수입니다
					function closeRoadview() {
						var position = marker.getPosition();
						toggleMapWrapper(true, position);
					}
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