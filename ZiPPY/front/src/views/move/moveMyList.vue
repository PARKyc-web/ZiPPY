<template>
  <div>
    <move-nav-bar @click="categoryVal=$event.target.innerText"></move-nav-bar>
    <div class="company-wrap">
      <div class="move-main-title">
        <h3>받은 견적 조회</h3>
      </div>

      <hr />
      <!-- 드롭박스 -->
      <div id="used-main-dropbox1">
        <v-select @change="dropVal2()" v-model="select2" :items="drops" item-text="name" item-value="value2" label="정렬"
          color="#212529" persistent-hint single-line dense width="50"></v-select>
      </div>

      <div id="noProduct" class="mx-auto" v-if="list.length == 0" style="text-align:center">
      <v-icon style="font-size:100px; color:#B3E3C3" class="mb-5">mdi-alert-circle-outline</v-icon>
      <h2 style="font-weight:bold">아직 받은 견적이 없습니다.</h2>
    </div>

      <div class="divv" v-for="(item, i) in list" :key="i">
        <v-card :loading="loading" class="mx-auto my-12" max-width="374" elevation="10">
          <template slot="progress">
            <v-progress-linear color="#B3E3C3" height="10" indeterminate></v-progress-linear>
          </template>

          <!-- 카드 -->
          <v-card-text>


            <div>견적서 번호 : <span>NO.{{item.movingResponseNo}}</span></div>
            <div>
              <div id="mus" v-if="item.reservStatus == 0">견적 상태 : <span>견적전</span></div>
              <div id="mus" v-if="item.reservStatus == 1">견적 상태 : <span>1차견적</span></div>
              <div id="mus" v-if="item.reservStatus == 2">견적 상태 : <span>2차견적</span></div>
              <div id="mus" v-if="item.reservStatus == 3">견적 상태 : <span>예약요청</span></div>
              <div id="mus" v-if="item.reservStatus == 4">견적 상태 : <span>예약완료</span></div>
              <div id="mus" v-if="item.reservStatus == 5">견적 상태 : <span>이사완료</span></div>
              <div id="mus" v-if="item.reservStatus == 9">견적 상태 : <span>취소</span></div>
            </div>
          </v-card-text>
            <v-card-title>업체명 : <span>{{item.compName}}</span></v-card-title>
          <v-card-text>
            <v-row text-align="center" class="mx-0">
              <v-rating :value="item.totalRating" color="amber" dense half-increments readonly size="20"></v-rating>
              <div class="grey--text ms-4">
                {{item.totalRating}} (413)
              </div>
            </v-row>
            <v-divider class="mx-4"></v-divider>
              <div>
                연락처 : <span>{{item.phone}}</span>
              </div>
              <div>
                주소 : <span>{{item.compAddress}}</span>
              </div>
              <div class="my-4 text-subtitle-1">{{item.compIntro}}</div>
          </v-card-text>
         
          <v-card-text>
            <div>견적요청 번호 : <span>NO.
                {{item.estimateNo}}</span></div>
          </v-card-text>
          <v-card-text>
            <div>견적요청 일자 : <span>{{item.requestDate}}</span></div>
          </v-card-text>
          <v-card-text>
            <div>1차 견적 타입 : <span>{{item.firstEstimateType}}</span></div>
          </v-card-text>
          <v-card-title>1차 예상견적 : <span>{{item.firstEstimatePrice | comma}}</span>원</v-card-title>
          <div v-if="item.secondEstimatePrice != null">
            <v-card-text>
              <div>2차 견적 타입 : <span>{{item.secondEstimateType}}</span></div>
            </v-card-text>
            <v-card-title>2차 예상견적 : <span>{{item.secondEstimatePrice | comma}}</span>원</v-card-title>
          </div>

          <!-- <v-card-text>
      <v-chip-group
        v-model="selection"
        active-class="deep-purple accent-4 white--text"
        column
      >
        <v-chip>5:30PM</v-chip>

        <v-chip>7:30PM</v-chip>

        <v-chip>8:00PM</v-chip>

        <v-chip>9:00PM</v-chip>
      </v-chip-group>
    </v-card-text> -->

          <v-card-actions>
            <v-btn v-if="item.reservStatus == 2 || item.reservStatus == 1" color="#B3E3C3 lighten-2" text @click="reserve(item.movingResponseNo)">
              예약요청
            </v-btn>

            <v-btn color="#B3E3C3 lighten-2" text @click="chat">
              채팅하기
            </v-btn>

          </v-card-actions>
        </v-card>
      </div>


    </div>

    <div class="text-center">
      <v-pagination v-model="page" :length="pageCount" circle color="#B3E3C3"></v-pagination>
    </div>

  </div>
</template>

<script>
  import axios from 'axios';
  import swal from 'sweetalert2';
  import MoveNavBar from '@/components/move/MoveNavBar.vue';
 

  export default {
    components: {
      MoveNavBar
    },



    data: function () {
      return {

        icons: {
          iconfont: 'mdi', // 'mdi' || 'mdiSvg' || 'md' || 'fa' || 'fa4' || 'faSvg'
        },

        loading: false,
        selection: 1,
        heart: 0,
        wish: "",
        bNo: "",
        page: 1,
        pageCount: 1,

        list: [],

        //받은정보
        item: "",
        select2: "",
        userEmail: "",
        email: "",
        estimateNo: "",
        movingResponseNo: "",
        compName: "",
        totalRating: "",
        firstEstimatePrice: "",
        firstEstimateType: "",
        secondEstimatePrice: "",
        secondEstimateType: "",
        reservStatus: "",
        serviceType: 3,
        serviceId: "",

        vo:{
          userEmail: "",
        email: "",
        estimateNo: "",
        movingResponseNo: "",
        compName: "",
        totalRating: "",
        firstEstimatePrice: "",
        firstEstimateType: "",
        secondEstimatePrice: "",
        secondEstimateType: "",
        reservStatus: "",
        serviceType: 3,
        serviceId: "",
        },
        bookmarkNo: "",
        selectData: {},
        data: {
          email: this.$store.state.loginInfo.email,
          serviceId: "",
          bookmarkNo: "",
          serviceType: 3
        },

        drops: [{
            name: '전체',
            value: '전체'
          },
          {
            name: '평점순',
            value: '평점순'
          },
          {
            name: '높은가격순',
            value: '높은가격순'
          },
          {
            name: '낮은가격순',
            value: '낮은가격순'
          },

        ],
      }
    },
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
      //받은 견적 정보 가져오기 
      axios({
        url: "/zippy/move/moveMyList",
        methods: "GET",
        params: {
          email: "",
          checked: "",
          dropbox: "",
          dropbox2: "",
          serviceType: "",
          serviceId: "",
          userEmail: this.$store.state.loginInfo.email,
          reservStatus: ""
        }
      }).then(res => {
        console.log(res);
        this.list = res.data;
      }).catch(error => {
        console.log(error);
      })




      if (this.$store.state.loginInfo != null) {

        

        
      }
    },
    methods: {
      //예약요청
      reserve: function(i) {
        this.loading = true
        setTimeout(() => (this.loading = false), 2000)
        
        //예약요청
        swal.fire({
            title: '견적입력정보를 확인해주세요.',
            text: "이대로 견적서를 보내시겠습니까?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#96e5b8',
            cancelButtonColor: '#a9a9a9',
            confirmButtonText: 'YES'
          }).then((result) => {
            if (result.isConfirmed) { 
            //견적상태변경
            this.$axios({
              url: "/zippy/move/moveStatusThirdUpdate",
              method: "POST",
              params: {
                movingResponseNo : i
              },
            }).then(res => {
              console.log(res);
              swal.fire("예약요청 완료!");
              console.log(res.data.reservStatus);
            }).catch(err => {
              console.log(err)
            })
          }
          })
        },
      //채팅
      chat() {
        this.loading = true

        setTimeout(() => (this.loading = false), 2000)
      },

      checkbox: function () {
        const ckbox = document.querySelector(".form-check-input");
        const is_cked = ckbox.checked;
        var isChecked = document.querySelector(".form-check-input").innerText = is_cked
        console.log(isChecked);
        axios({
          url: "/zippy/used/main",
          methods: "GET",
          params: {
            keyword: this.searchValue,
            location: "",
            category: this.categoryVal,
            checked: isChecked,
            dropbox: ""
          }
        }).then(res => {
          console.log(res);
          this.data = res.data;
        }).catch(err => {
          console.log(err)
        })
      },

      dropVal2: function () {
        //드롭박스
        var dropValue2 = this.select2;
        console.log(dropValue2);
        axios({
          url: "/zippy/move/moveMyList",
          methods: "GET",
          params: {
            dropbox2: dropValue2, 
            UserEmail: this.$store.state.loginInfo.email,
            requestDate: this.requestDate,
            totalRating: this.list.totalRating,
            compAddress: this.compAddress,
            email: this.email,
            firstEstimatePrice: this.list.firstEstimatePrice
          }
        }).then(res => {
          console.log(res);
          console.log(this.list.totalRating);
          console.log(this.list.firstEstimatePrice);
          this.list = res.data;

        }).catch(err => {
          console.log(err);
        })
      },

      },

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
* {
  font-family: 'GmarketSans';
}


  .company-wrap {

    margin: 50px;
  }

  .mx-auto{
    margin: 200px;
  }

  .divv {
    display: inline-flex;
    justify-content: space-between;
    padding: 40px;
    margin-top: 50px;
  }

  /*  */
  #used-main-dropbox1 {
    margin-top: 30px;
    width: 100px;
    float: right;
    margin-right: 100px;
  }

  #used-main-dropbox2 {
    margin-top: 30px;
    width: 100px;
    float: right;
    margin-right: 100px;
  }

  span {
    font-weight: bold;
  }

  .form-check {
    margin-top: 30px;
    margin-bottom: 20px;
    margin-left: 50px;
    float: left;
  }

  .form-check-input {
    border: 1px solid #B3E3C3;
  }

  .form-check-input:checked {
    background-color: #B3E3C3;
    border: 1px solid #B3E3C3;
  }

  .v-btn {
    color: #96daac;
  }

  /* wish */
  #used-wish-heart {
    display: flex;
    justify-content: space-between;
  }

  #used-wish-eye {
    justify-content: space-between;
    display: flex;
    padding-left: 10px;
  }

  #used-view-wish {
    display: flex;
  }

  .used-detail-wish {
    border: none;
    color: white;
    background-color: #b3e3c3;
    font-size: x-large;
    border-radius: 10px;
    margin-left: 10px;
    width: 150px;
  }

  #used-detail-info-div {
    margin-bottom: 30px;
  }

  .used-wish-view-img {
    margin-right: 5px;
    margin-top: 5px;
  }

  .used-wish-view-count {
    font-size: medium;
    color: rgb(204, 204, 204);
    margin-right: 5px;
  }
</style>