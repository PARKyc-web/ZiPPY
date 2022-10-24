<template>
  <div>
    <div id="container">
      <div>
        <div class="used-main-title">
          <h3>찜 목록</h3>
        </div>
      </div>
      <div id="used-usedwishckdel">
        <div id="used-wish-chkbox">
          <v-checkbox v-model="uncheck" @click="selectAll()" id="ckAll" color="#b3e3c3"></v-checkbox>
        </div>
        <div id="used-wish-del-div">
          <button @click="delWish()" class="used-wish-del-btn">선택삭제</button>
        </div>
      </div>
      <hr />
      <div id="used-div-cont">
        <div class="used-wish-cont-div" @click="goDetail(list.productNo)" v-for="list in data">
          <div id="used-wish-product">
            <div class="used-wish-img-1">
              <img
                src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcnRs2T%2FbtrG3rPqyGx%2FgvSvyKfokqo8yQomsVjuQK%2Fimg.jpg"
                width="200px" height="200px" />
              <div id="wish-soldout-img" width="200px" height="200px" v-if="list.isSell == 1">판매완료</div>
              <div id="used-wish-info">
                <div>
                  <button class="used-wish-heart">
                    <i class="bi bi-heart-fill"></i>
                  </button>
                </div>
                <div id="wish-card-ckbox">
                  <div id="wish-title-price"><span>{{list.productName}}</span></div>
                  <div>
                    <v-checkbox id="ck" v-model="ckList" color="#b3e3c3" :value="list.bookmarkNo" @click.stop="test()">
                    </v-checkbox>
                  </div>
                </div>
                <div id="used-wish-price-date">
                  <div id="wish-title-price" class="used-wish-card-cont"><span>{{list.productPrice | comma}}원</span>
                  </div>
                  <div id="used-wish-date"><span>{{list.productDate}}</span></div>
                </div>
                <hr />
                <div>
                  <span>거래지역 : </span>
                  <span>{{list.productLocation}}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="text-center">
          <v-pagination v-model="page" :length="pageCount" circle color="#B3E3C3"></v-pagination>
        </div>
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
      data: [],
      ckList: [],
      uncheck: "",
      page: 1,
      pageCount: 1
    }),
    watch: {
      page(){
        this.pagenation();
      }
    },
    created() {
      axios({
        url: "/common/wishAll",
        method: "GET",
        params: {
          email: this.$store.state.loginInfo.email,
          serviceType: 1,
          pageNum: this.page
        }
      }).then(res => {
        console.log(res);
        this.data = res.data.list;
        this.pageCount = res.data.pages;
      }).catch(err => {
        console.log(err)
      })
    },
    filters: {
      comma(val) {
        return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',')
      }
    },
    methods: {
      pagenation() {
        axios({
          url: "/common/wishAll",
          method: "GET",
          params: {
            email: this.$store.state.loginInfo.email,
            serviceType: 1,
            pageNum: this.page
          }
        }).then(res => {
          console.log(res);
          this.data = res.data.list;
          this.pageCount = res.data.pages;
        }).catch(err => {
          console.log(err)
        })
      },
      test: function () {
        var ck = document.querySelector("#ck").checked;
        console.log(ck);
        // console.log(this.data.length)
        if (ck == true) {
          this.uncheck = false; 
        }
      },
      goDetail(no) {
        console.log(no);
        this.$router.push('/used/detail?pNo=' + no);
      },
      selectAll: function () {
        console.log(this.ckList)
        let isCheck = document.querySelector('#ckAll').checked;
        // console.log(isCheck)
        if (isCheck == false) {
          for (let i of this.data) {
            this.ckList.push(i.bookmarkNo);
          }
        } else if (isCheck == true) {
          this.ckList.splice(0);
        }
      },
      // 선택 삭제 & 전체 삭제 ?
      delWish: function () {
        axios({
          url: "common/delWish",
          method: "DELETE",
          data: {
            bNo: this.ckList
          }
        }).then(res => {
          console.log(res);
          let arr = [];
          for (let i of this.data) {
            if (this.ckList.indexOf(i.bookmarkNo) < 0) {
              arr.push(i);
            }
          }
          this.data = arr;
          this.ckList = [];
        }).catch(err => {
          console.log(err)
        })
      }
    }
  };
</script>

<style scoped>
  #container {
    width: 1200px;
    margin: 0 auto;
  }

  #used-go-right {
    float: right;
  }

  #wish-soldout-img {
    font-size: 1rem;
    color: white;
    text-align: center;
    line-height: 200px;
    width: 200px;
    height: 100%;
    background: rgba(0, 0, 0, 0.4);
    position: absolute;
    top: 0;
    left: 0;
  }

  #used-wish-price-date {
    display: flex;
    justify-content: space-between;
  }

  #used-wish-date {
    height: fit-content;
    margin-top: 20px;
  }

  #used-wish-date span {
    color: #888888;
    font-size: smaller;
  }

  #wish-card-ckbox {
    display: flex;
    justify-content: space-between;
  }

  #used-div-cont {
    margin: 20px 0 0 100px;
    margin-bottom: 50px;
  }

  #wish-title-price {
    width: 70%;
    height: fit-content;
    margin-top: 20px;
  }

  #wish-title-price span {
    font-size: large;
  }

  .used-wish-cont-div {
    display: inline-block;

  }

  .used-main-title {
    margin: 50px;
  }

  .bd-placeholder-img {
    font-size: 1.125rem;
    text-anchor: middle;
    -webkit-user-select: none;
    -moz-user-select: none;
    user-select: none;
  }

  @media (min-width: 768px) {
    .bd-placeholder-img-lg {
      font-size: 3.5rem;
    }
  }

  .b-example-divider {
    height: 3rem;
    background-color: rgba(0, 0, 0, 0.1);
    border: solid rgba(0, 0, 0, 0.15);
    border-width: 1px 0;
    box-shadow: inset 0 0.5em 1.5em rgba(0, 0, 0, 0.1),
      inset 0 0.125em 0.5em rgba(0, 0, 0, 0.15);
  }

  .b-example-vr {
    flex-shrink: 0;
    width: 1.5rem;
    height: 100vh;
  }

  .bi {
    vertical-align: -0.125em;
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
    color: #b3e3c3;
  }

  .nav-item dropdown {
    float: right;
  }

  .used-dropdown {
    list-style: none;
  }

  #navbarDropdown {
    color: #212529;
  }

  .thumbnail-wrap {
    display: block;
  }

  .container-fluid pt-5 {
    width: 1200px;
  }

  .form-control {
    width: 150px;
  }

  .used-wish-del-btn {
    padding: 0px 10px px;
    height: 28px;
    border: 1px solid rgb(238, 238, 238);
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: center;
    justify-content: center;
    font-size: 13px;
    background: rgb(255, 255, 255);
    color: rgb(136, 136, 136);
    margin-top: 19px;
  }

  .used-wish-del-btn:hover {
    background-color: #b3e3c3;
  }

  #used-wish-del-div {
    margin-left: 10px;
    float: right;
  }

  #used-usedwishckdel {
    display: flex;
  }

  #used-wish-product {
    display: inline-block;
    border: 1px solid grey;
    width: 500px;
    height: 200px;
    margin-right: 20px;
  }

  #used-wish-product:hover {
    cursor: pointer;
  }

  .used-wish-heart {
    float: right;
    background-color: white;
    border: none;
  }

  #used-wish-info {
    padding: 15px;
    width: 500px;

  }

  .used-wish-img-1 {
    display: flex;
    position: relative;
  }

  .used-wish-cont {
    width: 1100px;
    /* margin: 0 auto; */
    display: inline;
  }

  .used-wish-card-cont {
    font-size: large;
    font-weight: bolder;
    margin-top: 5px;
  }
</style>