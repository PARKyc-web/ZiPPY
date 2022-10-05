<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>부동산 메인</title>
<!-- propertyMain CSS -->
<link
	href="<%=request.getContextPath()%>/resources/css/property/propertyMain.css"
	rel="stylesheet">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<!-- ICON CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Vue -->
<script src="https://cdn.jsdelivr.net/npm/vue@2.7.8/dist/vue.js"></script>
<script type="module"
	src="<%=request.getContextPath()%>/resources/js/app.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/style.css">

<!-- 제이쿼리 -->
<script src="https://code.jquery.com/jquery-3.6.1.min.js"
	integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
	crossorigin="anonymous"></script>
</head>
<body style="overflow-y: hidden">
	<!-- header & nav -->
	<div id="app">
		<main-header></main-header>
		<property-nav></property-nav>
	</div>

	<div id="container">
		<section>
		
			<div id="map" style="width: 100%; height: 100%;"></div>
			<script type="text/javascript"
				src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e8a57933716f8366face6fc1f10e2f85&libraries=clusterer"></script>

			<!-- 마커 클러스터러 생성 -->
			<script>
				
				var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
					center : new kakao.maps.LatLng(35.869079273181676,
							128.59329737007934), // 지도의 중심좌표 
					level : 7
				// 지도의 확대 레벨 
				});

				// 마커 클러스터러를 생성합니다 
				var clusterer = new kakao.maps.MarkerClusterer({
					map : map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
					averageCenter : true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
					minLevel : 5
				// 클러스터 할 최소 지도 레벨 
				});
				
				// 데이터를 가져오기 위해 jQuery를 사용합니다
				// 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
				$.get("/zippy/resources/propertyData/chicken.json", function(data) {
					// 데이터에서 좌표 값을 가지고 마커를 표시합니다
					// 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
					console.log(data);
					var markers = $(data.positions).map(
							function(i, position) {
								return new kakao.maps.Marker({
									position : new kakao.maps.LatLng(
											position.lat, position.lng)
								});
							});

					// 클러스터러에 마커들을 추가합니다
					clusterer.addMarkers(markers);
				});
			</script>
			<!-- end 마커 클러스터러 생성 -->
		</section>

		<aside style="overflow-y: auto;">
			<c:forEach items="${propertyList }" var="property">
				<table class="table table-hover">
					<tr>
						<td>여기에 이미지</td>
						<td>
							<h4>${property.saleType }${property.price }</h4>
							<p>${property.sigungu }</p>
							<p>${property.areaExclusive }m²▫${property.floor}층</p>
							<p>${property.detailContents }</p>
						</td>
					</tr>
				</table>
			</c:forEach>
		</aside>
	</div>
</body>
</html>