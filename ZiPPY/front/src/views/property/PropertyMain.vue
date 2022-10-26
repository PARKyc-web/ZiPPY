<template>
  <div>
    <property-main-toolbar :sigungu="sigungu" @search-property-list="searchPropertyList" />
    <div id="container">
      <section>
        <div id="map" class="map">
          <!-- <div class="hAddr">
            <span class="title">지도중심기준 행정동 주소정보</span>
            <span id="centerAddr"><input type="text" v-model="sigungu"></span>
          </div> -->
        </div>
      </section>
      <aside>
        <div v-if="houseProducts.length != 0">
          <div v-for="item in houseProducts" @click="goHouseDetail(item.productId)">
            <div id="propertyCard">
              <table style="width: 100%;">
                <tr>
                  <td style="width: 35%;"><img :src="'http://localhost:8090/zippy/common/img/property/' + item.mainImg"
                      style="width: 100%; height: 100%" /></td>

                  <td style="width: 65%;">
                    <v-row align="center" class="mx-0">
                      <div>매물번호 {{item.productId}}</div>
                    </v-row>
                    <v-card-title style="font-weight: bold;">{{item.saleType}} {{item.price | oneHundredMillion}}
                    </v-card-title>
                    <table style="font-size: medium; margin-left: 20px;">
                      <tr>
                        {{item.houseType}} · {{item.houseName}}
                      </tr>
                      <tr>{{item.sigungu}}</tr>
                      <tr>
                        <v-icon>mdi-border-outside</v-icon>{{item.areaExclusive}}m² · <v-icon>mdi-stairs</v-icon>
                        {{item.floor}}층
                      </tr>
                      <tr>
                        {{item.detailContents}}
                      </tr>
                    </table>
                  </td>
                </tr>
              </table>
            </div>
          </div>
        </div>
        <div v-else id="emptyList">
          <v-icon id="emptyIcon" color="#B3E3C3">mdi-alert-circle-outline</v-icon>
          <h6 style="text-align:center">검색된 매물이 없습니다.<br>위치 및 맞춤필터를 조정해보세요.</h6>
        </div>
      </aside>
    </div>
  </div>
</template>

<script>
  import SearchBar from "../../components/property/SearchBar.vue";
  import chickenJson from "../../assets/property/chicken.json";
  import axios from "axios";
  import PropertyMainToolbar from '../../components/property/PropertyMainToolbar.vue';

  export default {
    components: {
      SearchBar,
      PropertyMainToolbar,
    },
    data() {
      return {
        test: "image1.jpg",
        data: chickenJson,
        houseProducts: [],
        sigungu: '',
        streetAddress: [],
        map: 0,
        productPosition: [],
        price: '',
        clusterer: 0,
        markers: []
      }
    },
    filters: {
      oneHundredMillion: function (price) {
        let result = "";

        if (price.length > 4) {
          result += price.substr(0, price.length - 4) + '억';
          price = price.substr(price.length - 4, price.length);
          if (price == '0000') return result;
        }
        price = price.substr(price.length - 4, price.length - 3) + ',' + price.substr(price.length - 3, price.length);
        if(price[0]=='0') {
          price = price.substr(price.length - 3, price.length);
        }
        result += price;

        return result;
      }
    },
    created() {
      axios({
          url: "http://localhost:8090/zippy/property/main",
          method: "GET"
        }).then(response => {
          // 성공했을 때
          this.streetAddress = response.data;

          window.kakao && window.kakao.maps ?
            this.initMap() :
            this.addKakaoMapScript();
        })
        .catch(error => {
          // 에러가 났을 때
          console.log(error);
        });
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
        const MIN_LEVEL = 2;
        var options = {
          //지도를 생성할 때 필요한 기본 옵션
          center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
          level: 5 //지도의 레벨(확대, 축소 정도)
        };

        this.map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
        var map = this.map;

        map.setMaxLevel(8); // 지도 최소 축소 레벨
        map.setMinLevel(MIN_LEVEL);

        // 주소-좌표 변환 객체를 생성합니다
        var geocoder = new kakao.maps.services.Geocoder();

        // 마커 클러스터러를 생성합니다 
        this.clusterer = new kakao.maps.MarkerClusterer({
          map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
          averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
          minLevel: MIN_LEVEL // 클러스터 할 최소 지도 레벨 
        });

        var clusterer = this.clusterer;

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

        var initThis = this;
        // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
        function displayCenterInfo(result, status) {
          if (status === kakao.maps.services.Status.OK) {
            for (var i = 0; i < result.length; i++) {
              // 행정동의 region_type 값은 'H' 이므로
              if (result[i].region_type === 'H') {
                initThis.sigungu = result[i].address_name;

                // ex) 대구광역시 중구 남산1동 -> 대구광역시 중구 남산동
                for (let i = 1; i < 10; i++) {
                  initThis.sigungu = initThis.sigungu.replace(i.toString() + '.' + (i + 1).toString() + '가동', '동');
                  initThis.sigungu = initThis.sigungu.replace(i.toString() + '가동', '동');
                  initThis.sigungu = initThis.sigungu.replace(i.toString() + '.' + (i + 1).toString() + '동', '동');
                  initThis.sigungu = initThis.sigungu.replace(i + '동', '동');
                }
                // currentPositionAptList(initThis.sigungu);
                break;
              }
            }
          }
        }

        // 클러스터를 만들기 위해 필요한 최소 마커 개수를 설정한다.
        clusterer.setMinClusterSize(1);

        var markers = [];

        for (let i = 0; i < this.streetAddress.length; i++) {
          // 주소로 좌표를 검색합니다
          geocoder.addressSearch(this.streetAddress[i].streetAddress, function (result, status) {
            // 정상적으로 검색이 완료됐으면 
            if (status === kakao.maps.services.Status.OK) {
              var marker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(result[0].y, result[0].x), // 마커를 표시할 위치
              });
              markers.push(marker);
            }
          });
        }
        this.markers = markers;
        //////////////////////////////////////
        // function makeClusterer() {
        //   // 클러스터러에 마커들을 추가합니다
        //   clusterer.addMarkers(markers);
        // }

        // let setClusterer = setInterval(function () {
        //   makeClusterer();
        //   if (markers.length == initThis.streetAddress.length) {
        //     clearInterval(setClusterer)
        //   };
        // }, 100);
        //////////////////////////////
        function makeClusterer() {
          // 클러스터러에 마커들을 추가합니다
          clusterer.addMarkers(initThis.markers);
        }

        let setClusterer = setInterval(function () {
          makeClusterer();
          if (initThis.markers.length == initThis.streetAddress.length) {
            clearInterval(setClusterer)
          };
        }, 100);

        // 마커 클러스터러에 클릭이벤트를 등록합니다
        // 마커 클러스터러를 생성할 때 disableClickZoom을 true로 설정하지 않은 경우
        // 이벤트 헨들러로 cluster 객체가 넘어오지 않을 수도 있습니다
        kakao.maps.event.addListener(clusterer, 'clusterclick', function (cluster) {
          console.log('cluster get center', cluster.getCenter());

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
              sigungu: sigungu + '%'
            }
          }).then(response => {
            // 성공했을 때
            console.log(response);
            this.houseProducts = response.data;
          })
          .catch(error => {
            // 에러가 났을 때
            console.log(error);
          });
      },
      getStreetAddress() {
        axios({
            url: "http://localhost:8090/zippy/property/main",
            methods: "GET"
          }).then(response => {
            // 성공했을 때
            console.log(response);
            this.streetAddress = response.data;
            return response.data;
          })
          .catch(error => {
            // 에러가 났을 때
            console.log(error);
          });
      },
      searchEvent(sigungu) {
        let map = this.map;

        // 주소-좌표 변환 객체를 생성합니다
        var geocoder = new kakao.maps.services.Geocoder();

        // 주소로 좌표를 검색합니다
        geocoder.addressSearch(sigungu, function (result, status) {

          // 정상적으로 검색이 완료됐으면 
          if (status === kakao.maps.services.Status.OK) {
            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(new kakao.maps.LatLng(result[0].y, result[0].x));
          }
        });
      },
      currentPositionAptList(sigungu) {
        axios({
            url: "http://localhost:8090/zippy/property/currentPositionAptList",
            methods: "GET",
            params: {
              sigungu: sigungu + '%'
            }
          }).then(response => {
            // 성공했을 때
            console.log(response);
            this.houseProducts = response.data;
          })
          .catch(error => {
            // 에러가 났을 때
            console.log(error);
          });
      },
      searchPropertyList(data) {
        axios({
            url: "http://localhost:8090/zippy/property/searchPropertyList",
            methods: "GET",
            params: {
              houseType: data.houseType,
              saleType: data.saleType + '%',
              constructionYear: data.year,
              minPrice: data.range[0],
              maxPrice: data.range[1],
              minSize: data.minSize,
              maxSize: data.maxSize,
              sigungu: data.sigungu + '%'
            }
          }).then(response => {
            // 성공했을 때
            console.log(response);
            this.houseProducts = response.data;

            //////// 마크 클러스터러 모두 지우고, 새로운 마크 클러스터러 추가
            this.clusterer.clear();
            this.showclusterer();
          })
          .catch(error => {
            // 에러가 났을 때
            console.log(error);
          });

        this.searchEvent(data.sigungu);
      },
      showclusterer() {

        var outside = this;

        var geocoder = new kakao.maps.services.Geocoder();

        var clusterer = this.clusterer;

        // 클러스터를 만들기 위해 필요한 최소 마커 개수를 설정한다.
        clusterer.setMinClusterSize(1);

        this.markers = [];

        var markers = [];

        for (let i = 0; i < this.houseProducts.length; i++) {
          // 주소로 좌표를 검색합니다
          geocoder.addressSearch(this.houseProducts[i].streetAddress, function (result, status) {
            // 정상적으로 검색이 완료됐으면 
            if (status === kakao.maps.services.Status.OK) {
              var marker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(result[0].y, result[0].x), // 마커를 표시할 위치
              });
              markers.push(marker);
            }
          });
        }

        this.markers = markers;

        function makeClusterer() {
          // 클러스터러에 마커들을 추가합니다
          clusterer.addMarkers(outside.markers);
        }

        let setClusterer = setInterval(function () {
          makeClusterer();
          if (markers.length == outside.streetAddress.length) {
            clearInterval(setClusterer)
          };
        }, 100);

      },

    }
  };
</script>

<style scoped>
  #container {
    width: 100vw;
    /* height: calc(100vh - 70.8px); */
    height: calc(100vh - 139.8px);
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

    position: relative;
  }

  #emptyList {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }

  #emptyIcon {
    left: 32%;
    font-size: 80px;
    margin-bottom: 10px;
  }

  #propertyCard {
    border-bottom: 1px solid;
    border-color: #E8F5E9;
  }

  #propertyCard :hover {
    background-color: #E8F5E9;
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