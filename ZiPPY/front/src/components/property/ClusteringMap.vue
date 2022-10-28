<template>
  <div id="container">
    <section>
      <div id="map" class="map">
        <!-- 검색창 -->
        <search-bar style="top: 10px; left: 10px;" />
      </div>
    </section>
    <aside>
      <p>list</p>
    </aside>
  </div>
</template>

<script>
  import SearchBar from "./SearchBar.vue";
  import chickenJson from "@/assets/chicken.json";

  export default {
    components: {
      SearchBar,
    },
    mounted() {
      window.kakao && window.kakao.maps ?
        this.initMap() :
        this.addKakaoMapScript();
    },
    data() {
      return {
        data: chickenJson
        }
    },
    methods: {
      addKakaoMapScript() {
        const script = document.createElement("script");
        /* global kakao */
        script.onload = () => kakao.maps.load(this.initMap);
        document.head.appendChild(script);
      },
      initMap() {
        var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
        var options = {
          center: new kakao.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표 
          level: 12 // 지도의 확대 레벨 
        };

        var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

        map.setMaxLevel(12); // 지도 최소 축소 레벨

        // 마커 클러스터러를 생성합니다 
        var clusterer = new kakao.maps.MarkerClusterer({
          map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
          averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
          minLevel: 10 // 클러스터 할 최소 지도 레벨 
        });

        var markers = [];

        for (var i = 0; i < this.data.positions.length; i++) {
          // 마커를 생성합니다
          var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: new kakao.maps.LatLng(this.data.positions[i].lat, this.data.positions[i].lng), // 마커를 표시할 위치
          });
          markers.push(marker);
        }

        // 클러스터러에 마커들을 추가합니다
        clusterer.addMarkers(markers);
      }
    }
  };
</script>

<style scoped>
  #container {
    width: 100vw;
    height: 100vh;
    display: flex;
  }

  section {
    width: 75vw;
    height: 100vh;
  }

  #map {
    width: 100%;
    height: 100%;
  }

  aside {
    width: 25vw;
    height: 100vh;
    overflow-y: auto;
    background-color: lightblue;
  }

  ::-webkit-scrollbar {
    width: 8px;
    /* 스크롤바의 너비 */
  }

  ::-webkit-scrollbar-thumb {
    height: 10%;
    /* 스크롤바의 길이 */
    background: #B3E3C3;
    /* 스크롤바의 색상 */
    border-radius: 10px;
  }

  ::-webkit-scrollbar-track {
    /*스크롤바 뒷 배경 색상*/
    background: rgb(255, 255, 255);
  }
</style>