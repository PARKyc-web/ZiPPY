<template>
  <div id="map" class="map" style="width: 100%; height: 350px"></div>
</template>

<script>
export default {
  mounted() {
    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addKakaoMapScript();
  },
  methods: {
    addKakaoMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
    },
    initMap() {
      var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      var options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
        level: 3, //지도의 레벨(확대, 축소 정도)
      };

      var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

      // 마커가 표시될 위치입니다
      var markerPosition = new kakao.maps.LatLng(33.450701, 126.570667);

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        position: markerPosition,
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(map);

      // 마커 위에 표시할 인포윈도우를 생성한다
      var infowindow = new kakao.maps.InfoWindow({
        content: '<div style="padding:5px;">건물명</div>', // 인포윈도우에 표시할 내용
      });

      // 인포윈도우를 지도에 표시한다
      infowindow.open(map, marker);
    },
  },
};
</script>
