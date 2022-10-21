<template>
  <div>
    <nav-bar @click="categoryVal=$event.target.innerText"></nav-bar>
    <div id="container">
      <div>
        <div class="used-main-title">
          <h3>판매중인 중고제품</h3>
        </div>

        <div id="used-add-drop-search">
          <button id="used-addr">
            <i class="fa-solid fa-location-dot fa-2x"></i>
          </button>
          <div class="map_wrap">
            <div id="map"></div>
            <div class="hAddr">
              <span id="centerAddr"></span>
            </div>
          </div>
          <div>
            <div class="search">
              <input type="text" placeholder="검색어 입력" id="used-main-search-input" v-model="word"
                @keyup.enter="total();">
              <img @click="total()" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png">
            </div>
          </div>
        </div>
      </div>

      <hr>
      <div id="used-soldot-drop">
        <div class="form-check">
          <input @click="total()" class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
          <label class="form-check-label" for="flexCheckDefault">
            판매완료된 상품보기
          </label>
        </div>
        <div id="used-show-cate">
          <h2>{{this.categoryVal}}</h2>
        </div>
        <!-- <nav class="navbar navbar-expand-lg navbar-light"> -->
        <div id="used-main-dropbox">
          <v-select @change="total()" v-model="select" :items="items" item-text="name" item-value="value" label="정렬"
            color="#212529" persistent-hint single-line dense width="50"></v-select>
        </div>
      </div>
      <div id="noProduct" class="mx-auto" v-if="data.length == 0" style="text-align:center">
        <v-icon style="font-size:100px; color:#B3E3C3" class="mb-5">mdi-alert-circle-outline</v-icon>
        <h2 style="font-weight:bold">고객님께서 찾으시는 상품이 없습니다.</h2>
        <!-- <p>다시 검색해주세요.</p> -->
      </div>
      <div @click="goDetail(list.productNo)" class="used-main-card" v-if="data.length != 0" v-for="list in data">
        <div>
          <div><img
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkW5iRqvi6VdPWVWYswwWoUYhmW-AA2W8P0tExfMLx3wWPiwVFHegzq29vq8KoN1jKVxQ&usqp=CAU"
              width="194px" height="194px"></div>
          <div class="used-main-card-cont">
            <div class="used-main-card-title">{{list.productName}}</div>
            <div class="used-main-price-date">
              <div class="used-main-card-price">{{list.productPrice | comma}}원</div>
              <div><span>{{list.productDate}}</span></div>
              <div type="hidden"></div>
            </div>
          </div>
        </div>
      </div>
      <div class="text-center">
        <v-pagination v-model="page" :length="pageCount" circle color="#B3E3C3"></v-pagination>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import navBar from '../../components/used/navBar.vue';

  export default {
    components: {
      navBar
    },
    data: () => ({
      items: [{
          name: '최고가순',
          value: '최고가순'
        },
        {
          name: '최저가순',
          value: '최저가순'
        },
        {
          name: '최신등록일자순',
          value: '최신등록일자순'
        },
        {
          name: '오래된등록일자순',
          value: '오래된등록일자순'
        },
      ],
      data: [],
      word: "",
      select: '',
      categoryVal: '',
      searchValue: '',
      page: 1,
      isChecked: '',
      location: "",
      pageCount: 1,
      dropValue: '',

      // 주소 출력에 필요한 데이터들입니다.
      map: null,
      markers: [],
      latitude: 0,
      longitude: 0
    }),
    filters: {
      comma(val) {
        return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',')
      }
    },
    watch: {
      categoryVal() {
        this.findList({
          keyword: this.searchValue,
          location: this.location,
          category: this.categoryVal,
          checked: this.isChecked,
          dropbox: this.select,
          pageNum: this.page
        });
      },
      page() {
        this.findList({
          keyword: this.searchValue,
          location: this.location,
          category: this.categoryVal,
          checked: this.isChecked,
          dropbox: this.select,
          pageNum: this.page
        });
      }
    },
    created() {
      this.findList({
        location: this.location,
        keyword: "",
        category: "",
        checked: "",
        dropbox: "",
        pageNum: this.page
      })

      if (!("geolocation" in navigator)) {
        return;
      }

      // get position
      navigator.geolocation.getCurrentPosition(pos => {
        this.latitude = pos.coords.latitude;
        this.longitude = pos.coords.longitude;

        if (window.kakao && window.kakao.maps) {

          this.initMap();

        } else {
          const script = document.createElement("script");
          /* global kakao */
          script.onload = () => kakao.maps.load(this.initMap);
          document.head.appendChild(script);
        }

      }, err => {
        alert(err.message);
      })
    },
    methods: {
      total: function () {
        console.log(this.location);
        this.isChecked = document.querySelector(".form-check-input").checked;
        this.findList({
          location: this.location, // 0
          keyword: this.word, // 0
          category: this.categoryVal, // 0
          checked: this.isChecked, // 0
          dropbox: this.select,
          pageNum: this.page // 0
        })
      },
      goDetail(no) {
        console.log(no);
        this.$router.push('/used/detail?pNo=' + no);
      },
      getImgUrl(list) {
        return require(list.image);
      },

      findList(searchData) {
        axios({
          url: "http://localhost:8090/zippy/used/main",
          methods: "GET",
          params: searchData
        }).then(res => {
          console.log(res);
          this.data = res.data.list;
          this.pageCount = res.data.pages;
        }).catch(err => {
          console.log(err)
        })
      },
      initMap() {

        const container = document.getElementById("map");
        const options = {
          center: new kakao.maps.LatLng(33.450701, 126.570667),
          level: 5,
        };
        this.map = new kakao.maps.Map(container, options);
        this.displayMarker([
          [this.latitude, this.longitude]
        ]);
      },

      displayMarker(markerPositions) {
        var out = this;
        if (this.markers.length > 0) {
          this.markers.forEach((marker) => marker.setMap(null));
        }

        const positions = markerPositions.map(
          (position) => new kakao.maps.LatLng(...position)
        );

        if (positions.length > 0) {
          this.markers = positions.map(
            (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
          );

          const bounds = positions.reduce(
            (bounds, latlng) => bounds.extend(latlng),
            new kakao.maps.LatLngBounds()
          );

          this.map.setBounds(bounds);

          // 주소-좌표 변환 객체를 생성합니다
          var geocoder = new kakao.maps.services.Geocoder();

          // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
          searchAddrFromCoords(this.map.getCenter(), displayCenterInfo);

          function searchAddrFromCoords(coords, callback) {
            // 좌표로 행정동 주소 정보를 요청합니다
            geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
          }
          
          let outside = this;
          // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
          function displayCenterInfo(result, status) {
            if (status === kakao.maps.services.Status.OK) {
              var infoDiv = document.getElementById('centerAddr');

              for (var i = 0; i < result.length; i++) {
                // 행정동의 region_type 값은 'H' 이므로
                if (result[i].region_type === 'H') {
                  infoDiv.innerHTML = result[i].address_name;
                  outside.location = infoDiv.innerHTML;
                  console.log("asdfasfd"+outside.location)
                  out.location = outside.location;
                  break;
                }
              }
            }
          }
        }
      }
    }
  }
</script>
<style scoped>
  #container {
    width: 1200px;
    margin: 0 auto;
  }

  #used-show-cate {
    margin-top: 30px;
  }

  #used-main-dropbox {
    margin-top: 40px;
  }

  .search {
    position: relative;
    width: 300px;
  }

  .search img:hover {
    cursor: pointer;
  }

  .search input {
    width: 100%;
    border: 1px solid #bbb;
    border-radius: 8px;
    padding: 10px 12px;
    font-size: 14px;
  }

  .search img {
    position: absolute;
    width: 17px;
    top: 10px;
    right: 12px;
    margin: 0;
  }

  .used-main-card {
    border: 1px solid #eeeeee;
    width: 194px;
    height: auto;
    display: inline-block;
    margin: 20px;
  }

  v-container:hover {
    cursor: pointer;
  }

  .v-overflow-btn {
    float: right;
    width: 150px;
  }

  ::v-deep .my-2 .v-label {
    font-size: 13px;
    color: rgb(0, 0, 0, 0.87)
  }

  .used-main-card:hover {
    cursor: pointer;
  }

  .used-main-title {
    margin: 50px;
  }

  .used-main-search-btn {
    background-color: #B3E3C3;
  }

  .used-main-card-title {
    overflow: hidden;
    padding-bottom: 20px;
    font-weight: 500;
    text-overflow: ellipsis;
  }

  .used-main-card-cont {
    padding: 15px 10px
  }

  .used-main-price-date {
    display: flex;
    justify-content: space-between;
  }

  .used-main-card-price {
    font-size: 16px;
    font-weight: 600;
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
  }

  .used-main-price-date span {
    color: #888888;
    font-size: smaller;
  }

  .form-check {
    margin-top: 30px;
    margin-bottom: 20px;
  }

  .form-check-input {
    border: 1px solid #B3E3C3;
  }

  .form-check-input:checked {
    background-color: #B3E3C3;
    border: 1px solid #B3E3C3;
  }

  .bd-placeholder-img {
    font-size: 1.125rem;
    text-anchor: middle;
    -webkit-user-select: none;
    -moz-user-select: none;
    user-select: none;
  }

  @media (min-width : 768px) {
    .bd-placeholder-img-lg {
      font-size: 3.5rem;
    }
  }

  #used-add-drop-search {
    display: flex;
    justify-content: space-between;
    height: 40px;
    margin-top: 10px;
  }

  .b-example-divider {
    height: 3rem;
    background-color: rgba(0, 0, 0, .1);
    border: solid rgba(0, 0, 0, .15);
    border-width: 1px 0;
    box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
  }

  .b-example-vr {
    flex-shrink: 0;
    width: 1.5rem;
    height: 100vh;
  }

  .bi {
    vertical-align: -.125em;
    fill: currentColor;
  }

  .nav-scroller {
    position: relative;
    z-index: 2;
    height: 2.75rem;
    overflow-y: hidden;
  }

  .nav-scroller .nav {
    display: flex;
    flex-wrap: nowrap;
    padding-bottom: 1rem;
    margin-top: -1px;
    overflow-x: auto;
    text-align: center;
    white-space: nowrap;
    -webkit-overflow-scrolling: touch;
  }

  .nav-link {
    color: black;
  }

  .nav-link dropdown-toggle {
    float: right;
    width: 50px;
  }

  .nav-link:hover {
    color: #B3E3C3;
  }

  .nav-item dropdown {
    float: right;
  }

  .used-dropdown {
    list-style: none;
  }

  .dropdown-menu a:hover {
    background-color: #B3E3C3;
  }

  #navbarDropdown {
    color: #212529;
  }

  .thumbnail-wrap {
    display: block;
  }



  .card-li {
    border: 1px solid black;
    list-style: none;
    float: left;
    margin: 30px 10px 10px 10px;
  }

  #used-price-date {
    display: flex;
    justify-content: space-between;

  }

  .used-img-price {
    align-items: center;
  }

  .container-fluid pt-5 {
    width: 1200px;
  }

  .form-control {
    width: 150px;
  }

  .used-addr {
    cursor: pointer;
  }

  .cat-item d-flex flex-column border mb-4 {
    border-radius: 10px;
  }

  #used-addr {
    border: none;
    background-color: white;
  }


  #dropdownMenuButton {
    text-align: left;
  }

  #dropdown-opt {
    width: 300px;
  }

  #dropdown-opt a:hover {
    color: #212529;
    background-color: #B3E3C3;
  }

  #used-soldot-drop {
    display: flex;
    justify-content: space-between;
  }

  input#chk_top {
    display: none;

  }

  /*input 바로 다음의 label*/
  input#chk_top+label {
    cursor: pointer;
  }

  /*input 바로 다음의 label:before 에 체크하기 전 CSS 설정*/
  input#chk_top+label:before {
    content: "";
    display: inline-block;
    width: 17px;
    height: 17px;
    line-height: 17px;
    border: 1px solid #cbcbcb;
    vertical-align: middle;
    /*체크 전과 체크 후 높이 차이 때문에 설정*/
  }

  .chk_box {
    margin-top: 30px;
  }

  /*checked된 input 바로 다음의 label:before 에 체크 후 CSS 설정*/
  input#chk_top:checked+label:before {
    content: "\f00c";
    /*폰트어썸 유니코드*/
    font-family: "Font Awesome 5 free";
    /*폰트어썸 아이콘 사용*/
    font-weight: 900;
    /*폰트어썸 설정*/
    color: #fff;
    background-color: #B3E3C3;
    border-color: #B3E3C3;
    font-size: 13px;
    text-align: center;
  }

  .map_wrap {
    position: relative;
    width: 70%;
    height: fit-content;
  }

  /* 주소 폰트는 여기서 조절하시면 됩니다. */
  .hAddr {
    position: absolute;
    left: 10px;
    top: 10px;
    border-radius: 2px;
    z-index: 1;
  }

  #centerAddr {
    display: block;
    margin-top: 2px;
    font-weight: normal;
  }

  .bAddr {
    padding: 5px;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
  }
</style>