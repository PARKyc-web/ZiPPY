<template>
  <div id="container">
    <section>
    <div id="map" class="map">
      <search-bar style="top: 10px; left: 10px;"></search-bar>
      <div class="hAddr">
        <span class="title">지도중심기준 행정동 주소정보</span>
        <span id="centerAddr"><input type="text" v-model="sigungu"></span>
      </div>
    </div>
    </section>
    <aside>
      <div v-if="houseProducts.length != 0" v-for="item in houseProducts" @click="goHouseDetail(item.productId)">
        <v-card>
          <div>매물번호 {{item.productId}}</div>
          <div>{{item.houseName}}</div>
          <div>{{item.saleType}} {{item.price}}</div>
          <div>{{item.sigungu}}</div>
          <div>{{item.areaExclusive}}m² {{item.floor}}층</div>
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
    mounted() {
      window.kakao && window.kakao.maps ?
        this.initMap() :
        this.addKakaoMapScript();
    },
    data() {
      return {
        data: chickenJson,
        houseProducts: [],
        sigungu: '',
      }
    },
    methods: {
      goHouseDetail(productId) {
        this.$router.push({
          name: 'HouseDetail',
          query: {
            productId: productId
          }
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
        const MIN_LEVEL = 4;
        var options = {
          //지도를 생성할 때 필요한 기본 옵션
          center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
          level: 7 //지도의 레벨(확대, 축소 정도)
        };

        var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
        map.setMaxLevel(8); // 지도 최소 축소 레벨
        map.setMinLevel(MIN_LEVEL);

        // 주소-좌표 변환 객체를 생성합니다
        var geocoder = new kakao.maps.services.Geocoder();


        // 마커 클러스터러를 생성합니다 
        var clusterer = new kakao.maps.MarkerClusterer({
          map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
          averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
          minLevel: MIN_LEVEL // 클러스터 할 최소 지도 레벨 
        });

        /* 현재 위치로 지도의 중심 위치 변경 */
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
          message = 'HTML5의 GeoLocation를 사용 할 수 없습니다.'
          console.log(message);
        }


        // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
        searchAddrFromCoords(map.getCenter(), displayCenterInfo);


        // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
        kakao.maps.event.addListener(map, 'idle', function () {
          searchAddrFromCoords(map.getCenter(), displayCenterInfo);
        });

        function searchAddrFromCoords(coords, callback) {
          // 좌표로 행정동 주소 정보를 요청합니다
          geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
        }

        function searchDetailAddrFromCoords(coords, callback) {
          // 좌표로 법정동 상세 주소 정보를 요청합니다
          geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
        }

        var initThis = this;
        // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
        function displayCenterInfo(result, status) {
          if (status === kakao.maps.services.Status.OK) {
            var infoDiv = document.getElementById('centerAddr');

            for (var i = 0; i < result.length; i++) {
              // 행정동의 region_type 값은 'H' 이므로
              if (result[i].region_type === 'H') {
                infoDiv.innerHTML = result[i].address_name;
                initThis.sigungu = result[i].address_name;

                // ex) 대구광역시 중구 남산1동, 남산1동 -> 대구광역시 중구 남산동
                for (let i = 1; i < 11; i++) {
                  initThis.sigungu = initThis.sigungu.replace(i, '');
                }
                console.log('현재 지도의 중심 위치: ', initThis.sigungu);
                break;
              }
            }
          }
        }

        var markers = [];

        for (var i = 0; i < this.data.positions.length; i++) {
          // 마커를 생성합니다
          var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: new kakao.maps.LatLng(this.data.positions[i].lat, this.data.positions[i].lng), // 마커를 표시할 위치
          });
          markers.push(marker);
        }

        // 클러스터를 만들기 위해 필요한 최소 마커 개수를 설정한다.
        clusterer.setMinClusterSize(1);
        // 클러스터러에 마커들을 추가합니다
        clusterer.addMarkers(markers);

        // 마커 클러스터러에 클릭이벤트를 등록합니다
        // 마커 클러스터러를 생성할 때 disableClickZoom을 true로 설정하지 않은 경우
        // 이벤트 헨들러로 cluster 객체가 넘어오지 않을 수도 있습니다
        kakao.maps.event.addListener(clusterer, 'clusterclick', function (cluster) {
            console.log(cluster.getCenter());

            // 현재 지도 레벨에서 1레벨 확대한 레벨
            var level = map.getLevel() - 1;

            // 지도를 클릭된 클러스터의 마커의 위치를 기준으로 확대합니다
            map.setLevel(level, {
              anchor: cluster.getCenter()
            });

            console.log("click: ", initThis.sigungu);
            initThis.getPropertyList(initThis.sigungu);
        });

      },
      getPropertyList(sigungu) {
        axios({
            url: "http://localhost:8090/zippy/property/getPropertyList",
            methods: "GET",
            params: {
              sigungu: sigungu
            }
          }).then(response => {
            // 성공했을 때
            console.log('getPropertyList success!');
            console.log(response);
            this.houseProducts = response.data;
          })
          .catch(error => {
            // 에러가 났을 때
            console.log('getPropertyList fail!');
            console.log(error);
          });
      }

    }
  };
</script>

<style scoped>
  #container {
    width: 100vw;
    height: calc(100vh - 70.8px);
    display: flex;
  }

  section {
    width: 75vw;
    height: 100%;
  }

  #map {
    width: 100%;
    height: 100%;
  }

  aside {
    width: 25vw;
    height: 100%;
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