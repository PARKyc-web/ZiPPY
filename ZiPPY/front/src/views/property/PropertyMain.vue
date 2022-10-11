<template>
  <div id="container">
    <section>
      <div id="map" class="map">
        <!-- 검색창 -->
        <search-bar style="top: 10px; left: 10px;" />
      </div>
    </section>
    <aside>
      <div v-if="houseProducts.length != 0" v-for="item in houseProducts" @click="goHouseDetail(item.productId)">
        <v-card>
          <div>매물번호 {{item.productId}}</div>
          <div>{{item.houseName}}</div>
          <div>{{item.saleType}} {{item.price}}</div>
          <div>{{item.sigungu}}</div>
          <div>{{item.areaExclusive}}m^2 {{item.floor}}층</div>
          <div>{{item.detailContents}}</div>
        </v-card>
      </div>
    </aside>
  </div>
</template>

<script>
  import SearchBar from "../../components/property/SearchBar.vue";
  import chickenJson from "../../assets/chicken.json";
  import axios from "axios";

  export default {
    components: {
      SearchBar,
    },
    data() {
      return {
        data: chickenJson,
        houseProducts: [],
      }
    },
    created() {
      axios({
          url: "http://localhost:8090/zippy/property/main",
          methods: "GET",
        }).then(response => {
          // 성공했을 때
          console.log('success!');
          console.log(response);
          this.houseProducts = response.data;
        })
        .catch(error => {
          // 에러가 났을 때
          console.log('fail!');
          console.log(error);

        });
    },
    mounted() {
      window.kakao && window.kakao.maps ?
        this.initMap() :
        this.addKakaoMapScript();
    },
    methods: {
      goHouseDetail(productId) {
        this.$router.push({
          name: 'HouseDetail',
          query: {productId: productId}
        })
      },
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
          level: 3 // 지도의 확대 레벨 
        };

        var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
        map.setMaxLevel(4); // 지도 최소 축소 레벨

        /* 현재 위치로 지도의 중심 위치를 변경 */
        // HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
        if (navigator.geolocation) {

          // GeoLocation을 이용해서 접속 위치를 얻어옵니다
          navigator.geolocation.getCurrentPosition(function (position) {

            var lat = position.coords.latitude, // 위도
              lon = position.coords.longitude; // 경도

            var locPosition = new kakao.maps.LatLng(lat, lon) // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
            map.setCenter(locPosition);
          });

        } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
          message = 'geolocation을 사용할수 없어요..'
          console.log(message);
        }

        // 마커 클러스터러를 생성합니다 
        var clusterer = new kakao.maps.MarkerClusterer({
          map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
          averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
          minLevel: 1 // 클러스터 할 최소 지도 레벨 
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
      },

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