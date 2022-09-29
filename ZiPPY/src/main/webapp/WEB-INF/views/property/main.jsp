<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script src="https://cdn.jsdelivr.net/npm/vue@2.7.8/dist/vue.js"></script>
<title>부동산 메인</title>
<link href="<%=request.getContextPath()%>/resources/css/property/propertyMain.css" rel="stylesheet">
</head>
<body>
	<div id="container">
		<section>
			<div id="map" style="width: 100%; height: 100%;"></div>
			<script type="text/javascript"
				src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e8a57933716f8366face6fc1f10e2f85"></script>
			<script>
				var container = document.getElementById('map'); // 지도를 표시할 div 
				var options = {
					center : new kakao.maps.LatLng(35.869079273181676,
							128.59329737007934), // 지도의 중심좌표 (예담)
					level : 3
				// 지도의 확대 레벨
				};

				var map = new kakao.maps.Map(container, options); // 지도 생성

				// 마커가 표시될 위치
				var markerPosition = new kakao.maps.LatLng(35.869079273181676,
						128.59329737007934);

				// 마커 생성
				var marker = new kakao.maps.Marker({
					position : markerPosition
				});

				// 지도 위 마커 표시
				marker.setMap(map);

				// 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
				var iwContent = '<div style="padding:5px;">클릭하면 해당 매물의 정보 출력</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
				iwRemoveable = flase; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

				// 인포윈도우를 생성합니다
				var infowindow = new kakao.maps.InfoWindow({
					content : iwContent,
					removable : iwRemoveable
				});

				// 마커에 클릭이벤트를 등록합니다
				kakao.maps.event.addListener(marker, 'click', function() {
					// 마커 위에 인포윈도우를 표시합니다
					infowindow.open(map, marker);
				});
			</script>
		</section>

		<aside>

			<h1>매물 리스트</h1>
			<hr>


		</aside>
	</div>
</body>
</html>