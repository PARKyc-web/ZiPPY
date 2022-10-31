<template>
  <div>
    <move-nav-bar @click="categoryVal=$event.target.innerText"></move-nav-bar>
  <div class="company-wrap">
    <div class="move-main-title">
      <h3>예약목록 조회</h3>
    </div>

    <hr />
    <!-- 드롭박스 -->
    <div id="used-main-dropbox1">
      <v-select @change="dropVal2()" v-model="select2" :items="drops" item-text="name" item-value="value2" label="예약상태"
        color="#212529" persistent-hint single-line dense width="50"></v-select>
    </div>

    <div id="noProduct" class="mx-auto" v-if="list.length == 0" style="text-align:center">
      <v-icon style="font-size:100px; color:#B3E3C3" class="mb-5">mdi-alert-circle-outline</v-icon>
      <h2 style="font-weight:bold">아직 예약한 견적이 없습니다.</h2>
    </div>

    <div class="divv" v-for="(item, i) in list">
      <v-card :loading="loading" class="mx-auto my-12" max-width="374" elevation="10" >
        <template slot="progress">
          <v-progress-linear color="deep-purple" height="10" indeterminate></v-progress-linear>
        </template>
     
        <v-card-text>
          <div >
              <div id="mus" v-if="item.reservStatus == 0">견적 상태 : <span>견적전</span></div>
              <div id="mus" v-if="item.reservStatus == 1">견적 상태 : <span>1차견적</span></div>
              <div id="mus" v-if="item.reservStatus == 2">견적 상태 : <span>2차견적</span></div>
              <div id="mus" v-if="item.reservStatus == 3">견적 상태 : <span>예약요청</span></div>
              <div id="mus" v-if="item.reservStatus == 4">견적 상태 : <span>예약완료</span></div>
              <div id="mus" v-if="item.reservStatus == 5">견적 상태 : <span>이사완료</span></div>
              <div id="mus" v-if="item.reservStatus == 9">견적 상태 : <span>후기작성완료</span></div>
          </div>
          <div>견적서 번호 : <span>NO.{{item.movingResponseNo}}</span></div>
        </v-card-text>
        <v-card-title>업체명 : <span>{{item.compName}}</span></v-card-title>
        <v-card-text>
          <v-row text-align="center" class="mx-0">
            <v-rating :value="4.5" color="amber" dense half-increments readonly size="20"></v-rating>
            <div class="grey--text ms-4">
              평점 {{Math.round(item.totalRating * 10)/10}}
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
        <v-divider class="mx-4"></v-divider>
        <v-card-text>
          <div>견적요청 번호 : <span>NO.{{item.estimateNo}}</span></div>
        </v-card-text>
        <v-card-text>
              <div>견적 타입 : <span>{{item.firstEstimateType}}</span></div>
            </v-card-text>
        <v-card-title>예상견적 : <span>{{item.firstEstimatePrice | comma}}</span>원</v-card-title>
        <div v-if="item.secondEstimatePrice != null">
            <v-card-text>
              <div>최종 견적 타입 : <span>{{item.secondEstimateType}}</span></div>
            </v-card-text>
            <v-card-title>최종 예상견적 : <span>{{item.secondEstimatePrice | comma}}</span>원</v-card-title>
          </div>

        <v-card-actions>        
          <v-btn  id="reviewBtn" text @click="chat(i)"  depressed color=#B3E3C3 v-bind="attrs" v-on="on" width="100px">
            채팅하기
          </v-btn>

          <!-- 후기 작성 모달 -->
          <MoveReviewModal :email="item.email" :movingResponseNo="item.movingResponseNo" :reservStatus="item.reservStatus">
          </MoveReviewModal>
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
import MoveNavBar from '@/components/move/MoveNavBar.vue';
import MoveReviewModal from '@/components/move/MoveReviewModal.vue';

export default {
  components: {
    MoveNavBar, MoveReviewModal
  },
 
    
    data : function(){
      return{

  icons: {
    iconfont: 'mdi', // 'mdi' || 'mdiSvg' || 'md' || 'fa' || 'fa4' || 'faSvg'
  },
    //페이징
    selection: 1,
        heart: 0,
        wish: "",
        bNo: "",
        page: 1,
        pageCount: 1,


      loading: false,
      selection: 1,
      heart : 0,
      wish: "",
      on: "",
      attrs : "",
      list: [],

      //받은정보
        item: "",
        select2:"",

        email: "",
        estimateNo: "",
        movingResponseNo: "",
        compName : "",
        totalRating: "",
        reviewCount:"",
        firstEstimatePrice: "",
        reservStatus : "",
        
        drops: [{
            name: '전체',
            value: '전체'
          },
          {
            name: '예약요청',
            value: '예약요청'
          },
          {
            name: '예약완료',
            value: '예약완료'
          },
          {
            name: '이사완료',
            value: '이사완료'
          },
         
        ],
      }
    },

    created(){
      //예약목록 가져오기 - 예약요청, 예약완료, 이사완료 모두 
      axios({
        url: "/zippy/move/moveReserve",
        methods: "GET",
        params: {
          userEmail: this.$store.state.loginInfo.email,
          checked: "",
          dropbox: "",
          dropbox2: "",
          serviceType: "",
          serviceId: ""
        }
      }).then(res => {
        console.log(res);
        this.list = res.data;
      }).catch(error => {
        console.log(error);
      })
    },
    filters: {
      comma(val) {
        return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',')
      }
    },
    methods: {
      
      async chat(num) {
        var out = this;
        this.loading = true
        setTimeout(() => (this.loading = false), 2000)
        var temp = await this.$axios({
            url: "/zippy/chat/room",
            method: "POST",
            data: {
              user1: this.$store.state.loginInfo.email,
              user2: this.list[num].email,
              chatType: 3
            }
          }).then(res => {
            out.$router.push({
              name: "chatDetail",
              query: {
                roomId: res.data
              }
            })
          })
      },
      cancle(){

      },
      review(){

      },

      //드롭박스
      dropVal2: function () {
        var dropValue2 = this.select2;
        axios({
          url: "/zippy/move/moveReserve",
          methods: "GET",
          params: {    
            dropbox2: dropValue2, //지역
            userEmail: this.$store.state.loginInfo.email
          }
        }).then(res => {
          console.log(res);
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

/* 버튼 */
.v-btn {
    font-weight: bold;
  }
/*  */
  .company-wrap {
    
    margin: 50px;
  }

  .divv {
    display: inline-flex;
    justify-content: space-between;
    padding: 40px;
    margin-top: 50px;
  }

  .mx-auto{
    margin: 200px;
  }

  /*  */
  #used-main-dropbox1 {
    margin-top: 30px;
    width: 200px;
    float: right;
    margin-right: 100px;
  }

  #used-main-dropbox2 {
    margin-top: 30px;
    width: 200px;
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
</style>