<template>
  <div>
    <move-nav-bar @click="categoryVal=$event.target.innerText"></move-nav-bar>
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
              
              <!-- <div id="wish-soldout-img" width="200px" height="200px" v-if="list.isSell == 1">판매완료</div> -->
              <div id="used-wish-info">
                <div>
                  <button class="used-wish-heart">
                    <i class="bi bi-heart-fill"></i>
                  </button>
                </div>
                <div>
                  <span>요청번호 : </span><span>NO.{{list.estimateNo}}</span><span>견적서번호 : </span><span>NO.{{list.movingResponseNo}}</span>
                </div>
                <div id="wish-card-ckbox">
                  <div id="wish-title-price"><span>{{list.compName}}</span></div>

              <div>    
            <v-rating :value="list.totalRating" color="amber" dense half-increments readonly size="20"></v-rating>

            <div class="grey--text ms-4">
              {{list.totalRating}} (413)
            </div>
          </div>
                  <div>
                    <v-checkbox id="ck" v-model="ckList" color="#b3e3c3" :value="list.bookmarkNo" @click.stop="test()">
                    </v-checkbox>
                  </div>
                </div>
                <div id="used-wish-date"><span>{{list.compIntro}}</span></div>
                <div id="used-wish-price-date">
                  <div id="wish-title-price" class="used-wish-card-cont">1차 견적비용 : <span>{{list.firstEstimatePrice | comma}}원</span>
                    <div id="wish-title-price" class="used-wish-card-cont">2차 견적비용 : <span>{{list.secondEstimatePrice | comma}}원</span>

                  </div>
                  <div id="used-wish-date"><span>견적요청일자 : {{list.requestDate}}</span></div>
                </div>
                <hr />
                <div>
                  <span>업체주소 : </span>
                  <span>{{list.compAddress}}</span>
                </div>
                <div>
                  <span>연락처 : </span>
                  <span>{{list.phone}}</span>
                </div>

                <v-card-actions>
          <v-btn color="#B3E3C3 lighten-2" text @click="reserve">
            예약요청
          </v-btn>

          <v-btn color="#B3E3C3 lighten-2" text @click="chat">
            채팅하기
          </v-btn>
        </v-card-actions>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  
</template>

<script>
  import axios from 'axios';
  import MoveNavBar from '../../components/move/MoveNavBar.vue';

export default {
  components: {
    MoveNavBar
  },
    data: () => ({
      
      ckList: [],
      data: [],
      uncheck: "",

      item:"",
      email: "move123@move.com",
          
      checked: "",
      dropbox: "",
      dropbox2: "",

      
      //
      select2:"",
        userEmail: "",
        email: "",
        estimateNo: "",
        movingResponseNo: "",
        compName : "",
        compAddress:"",
        compIntro:"",
        phone : "",
        requestDate:"",
        totalRating: "",
        firstEstimatePrice: "",
        secondEstimatePrice:"",
        reservStatus : "",
        serviceType : 3,
        serviceId: "",
        bookmarkNo: "",

        data:{
          email: "",
          serviceId: "",
          bookmarkNo: "",
          serviceType: 3
        },
    }),
    created() {
      axios({
        url: "http://localhost:8090/zippy/move/moveMyList",
        methods: "GET",
        params: {
          email: "move123@move.com",
          
          checked: "",
          dropbox: "",
          dropbox2: "",

          serviceType: "",
          serviceId: ""

        }
      }).then(res => {
        console.log(res);
        this.data = res.data;

        this.serviceId = this.email;
          // this.data.email = this.$store.state.loginInfo.email;

      }).catch(error => {
        console.log(error);
      })
      if (this.$store.state.loginInfo != null) {
      axios({
        url: "/common/wishAll",
        method: "GET",
        params: {
          email: this.$store.state.loginInfo.email,
          serviceType: 1
        }
      }).then(res => {
        console.log(res);
        this.data = res.data;
      }).catch(err => {
        console.log(err)
      })
    }
    },
    filters: {
      comma(val) {
        return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',')
      }
    },
    methods: {
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
      },
      reserve() {
        this.loading = true

        setTimeout(() => (this.loading = false), 2000)
      },
      chat() {
        this.loading = true

        setTimeout(() => (this.loading = false), 2000)
      },
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