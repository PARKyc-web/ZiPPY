<template>
  <div>
    <div id="map" class="map"></div>
  </div>
</template>

<script>
  export default {
    mounted() {
      window.kakao && window.kakao.maps ?
        this.initMap() :
        this.addKakaoMapScript();
    },
    methods: {
      addKakaoMapScript() {
        const script = document.createElement("script");
        /* global kakao */
        script.onload = () => kakao.maps.load(this.initMap);
        script.src =
          "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=본인이 발급받은 appkey 등록";
        document.head.appendChild(script);
      },
      initMap() {
        var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
        var options = {
          center: new kakao.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표 
          level: 14 // 지도의 확대 레벨 
        };

        var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

        var clusterer = new kakao.maps.MarkerClusterer({
          map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
          averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
          minLevel: 10 // 클러스터 할 최소 지도 레벨 
        });

      }

    }
  };
</script>

<style>
  .map {
    width: 100%;
    height: 400px;
  }
</style>