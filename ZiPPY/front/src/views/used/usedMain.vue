<template>
  <div>
    <nav-bar @click="categoryVal=$event.target.innerText"></nav-bar>
    <div>
      <v-carousel cycle height="500" hide-delimiter-background :show-arrows="false" class="mt-5">
        <v-carousel-item v-for="(slide, i) in slides" :key="i">
          <v-img :src="imgs[i]" height="100%">
            <v-row class="fill-height" align="center" justify="center">
              <div class="text-h3" id="slide-text">
                {{ slide }}
                <div class="text-h6" id="info-text">
                  {{ info[i] }}
                </div>
              </div>
            </v-row>
          </v-img>
        </v-carousel-item>
      </v-carousel>
    </div>
    <div id="main-background"></div>
    <div id="container">
      <div>
        <div id="used-main-title" class="font-all">
          <h3>판매중인 중고제품</h3>
          <div>
            <v-btn width="160" depressed color=#B3E3C3 @click="insert()">
              상품등록
            </v-btn>
          </div>
        </div>

        <div id="used-add-drop-search">
          <button id="used-addr">
            <i class="fa-solid fa-location-dot fa-2x" style="display: inline-block"></i>
            <p style="display: inline-block; margin-left: 20px">대구광역시 중구 남일동</p>
          </button>
          <!-- <current-position-label @sigu="test"></current-position-label> -->
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
            color="#212529" persistent-hint single-line dense width="50px"></v-select>
        </div>
      </div>
      <div id="noProduct" class="mx-auto" v-if="data.length == 0" style="text-align:center">
        <v-icon style="font-size:100px; color:#B3E3C3" class="mb-5">mdi-alert-circle-outline</v-icon>
        <h2 style="font-weight:bold">고객님께서 찾으시는 상품이 없습니다.</h2>
        <!-- <p>다시 검색해주세요.</p> -->
      </div>
      <div @click="goDetail(list.productNo)" class="used-main-card" v-if="data.length != 0" v-for="list in data">
        <div>
          <div><img :src="'/zippy/common/img/used/'+list.mainImg" width="191px" height="194px"></div>
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
  import navBar from '@/components/used/navBar.vue';
  import CurrentPositionLabel from '@/components/used/CurrentPositionLabel.vue';
  import swal from 'sweetalert2';

  export default {
    components: {
      navBar,
      CurrentPositionLabel
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
      isChecked: '',
      location: "",
      dropValue: '',
      page: 1,
      pageCount: 1,
      cate: "",
      imgs: [
        'https://images.pexels.com/photos/4430925/pexels-photo-4430925.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',
        'https://images.pexels.com/photos/1571459/pexels-photo-1571459.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',
        'https://images.pexels.com/photos/312029/pexels-photo-312029.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1'
      ],
      slides: [
        '싼 값에 좋은 물건을',
        '중고제품으로 꾸미는 집안',
        '쓰지않는 물건은 보내기'
      ],
      info: [
        '중고거래만의 묘미',
        '셀프 인테리어의 재미',
        '갖고싶은 물건은 들여오기'
      ],

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
      this.test();
    },
    methods: {

      total: function () {
        console.log(this.cate)
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
        this.$router.push('/used/detail?pNo=' + no);
      },
      getImgUrl(list) {
        return require(list.image);
      },
      findList(searchData) {
        axios({
          url: "/zippy/used/main",
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
      // test(sigu) {
        test() {
        const out = this;
        // out.location = sigu;
        out.location = '대구광역시 중구';

        let cate = sessionStorage.getItem("cate");
        out.findList({
          location: out.location,
          keyword: "",
          category: cate || "",
          checked: "",
          dropbox: "",
          pageNum: out.page
        })
      },
      insert() {
        if (this.$store.state.loginInfo == null || this.$store.state.loginInfo.email == "") {
          swal.fire({
            title: '로그인이 필요합니다.',
            text: "로그인 하시겠습니까 ?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#96e5b8',
            cancelButtonColor: '#a9a9a9',
            confirmButtonText: 'YES'
          }).then((result) => {
            if (result.isConfirmed) {
              this.$router.push('/login')
            }
          })
          // this.$router.push('/login');
        } else {
          window.location.assign('/mypage/used/insert');
        }

      }
    }
  }
</script>
<style scoped>
  @font-face {
    font-family: 'GmarketSans';
    font-weight: 500;
    font-style: normal;
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.eot');
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.eot?#iefix') format('embedded-opentype'),
      url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.woff2') format('woff2'),
      url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.woff') format('woff'),
      url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.ttf') format("truetype");
    font-display: swap;
  }

  .font-all {
    font-family: 'GmarketSans';
  }

  #container {
    width: 1200px;
    margin: 0 auto;
  }

  #slide-text {
    color: white;
    /* color:#191919; */
    margin: 0 0 0 300px;
    font-weight: bold;
  }

  #info-text {
    /*font-weight: bold;*/
    padding: 30px 0 0 5px;
  }

  .v-carousel {
    width: 70%;
    border-radius: 1.5em;
    margin: 0 auto;
    position: relative;
    z-index: 1;
    top: 10px;
  }

  #main-background {
    width: 100%;
    margin-top: 64px;
    height: 450px;
    background-color: #B3E3C3;
    position: absolute;
    top: 76px;
    z-index: 0;
  }

  #used-show-cate {
    margin-top: 30px;
  }

  #used-main-dropbox {
    margin-top: 40px;
    width: 160px;
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
    border-radius: 0.8em;
  }

  .used-main-card img {
    border-radius: 0.8rem;
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

  #used-main-title {
    margin: 150px 50px 50px 50px;
    display: flex;
    justify-content: space-between;
  }

  #insertBtn {
    background-color: #B3E3C3;
    border-radius: 0.8rem;
  }

  .used-main-search-btn {
    background-color: #B3E3C3;
  }

  .used-main-card-title {
    /* overflow: hidden; */
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
</style>