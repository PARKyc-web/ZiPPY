<template>
  <div>
    <move-nav-bar @click="categoryVal=$event.target.innerText"></move-nav-bar>
  <div class="company-wrap">
    <div class="move-main-title">
      <h3>견적요청 조회</h3>
    </div>

    <div class="form-check">
      <input @click="checkbox ()" class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
      <label class="form-check-label" for="flexCheckDefault">
        견적완료된 요청보기
      </label>
    </div>
    <hr />
    <!--  -->
 


    <div id="used-main-dropbox1">
      <v-select @change="dropVal2()" v-model="select2" :items="drops" item-text="name" item-value="value2" label="예약상태"
        color="#212529" persistent-hint single-line dense width="50"></v-select>
    </div>

    <div class="divv" v-for="item in list">
      <v-card :loading="loading" class="mx-auto my-12" max-width="374" elevation="10" >
        <template slot="progress">
          <v-progress-linear color="deep-purple" height="10" indeterminate></v-progress-linear>
        </template>

        <!-- <v-img
      height="250"
      src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
    ></v-img> -->
        
    <!-- 카드 -->
        <v-card-text>
          
          
          <div >
              <div id="mus" v-if="item.reservStatus == 0">견적 상태 : <span>견적전</span></div>
              <div id="mus" v-if="item.reservStatus == 1">견적 상태 : <span>1차견적</span></div>
              <div id="mus" v-if="item.reservStatus == 2">견적 상태 : <span>2차견적</span></div>
              <div id="mus" v-if="item.reservStatus == 3">견적 상태 : <span>예약요청</span></div>
              <div id="mus" v-if="item.reservStatus == 4">견적 상태 : <span>예약완료</span></div>
              <div id="mus" v-if="item.reservStatus == 5">견적 상태 : <span>이사완료</span></div>
              <div id="mus" v-if="item.reservStatus == 9">견적 상태 : <span>취소</span></div>
          </div>
          <div>견적서 번호 : <span>NO.{{item.movingResponseNo}}</span></div>
        </v-card-text>
        
              <!--heart-->
                <!--<div>
                   <button>
                    <div>
                      <v-btn v-if="heart==0" class="mx-2" color='#D6D6D6' fab depressed dark small
                        @click="changeHeart()">
                        <v-icon dark>
                          mdi-heart
                        </v-icon>
                      </v-btn>
                      <v-btn v-if="heart==1" class="mx-2" color='#FF4063' fab depressed dark small
                        @click="changeHeart()">
                        <v-icon dark>
                          mdi-heart
                        </v-icon>
                      </v-btn>
                    </div>
                  </button>
                </div> -->

        <v-card-title>업체명 : <span>{{item.compName}}</span></v-card-title>

        <v-card-text>
          <v-row text-align="center" class="mx-0">
            <v-rating :value="4.5" color="amber" dense half-increments readonly size="20"></v-rating>

            <div class="grey--text ms-4">
              평점{{item.totalRating}} (413)
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
        <v-card-title>예상견적 : <span>{{item.firstEstimatePrice}}</span>원</v-card-title>

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
          
          <v-btn  id="reviewBtn" text @click="chat"  depressed color=#B3E3C3 v-bind="attrs" v-on="on" width="100px">
            채팅하기
          </v-btn>

          <v-btn id="reviewBtn" text @click="cancle" depressed color=#B3E3C3 v-bind="attrs" v-on="on" width="100px">
            취소요청
          </v-btn>

          <!-- 후기 작성 모달 -->
          <Move-Review-Modal :serviceId="item.email" :movingResponseNo="item.movingResponseNo">
          </Move-Review-Modal>
          <!-- <v-btn v-if="item.reservStatus == 5" color="deep-purple lighten-2" text @click="review">
            후기작성
          </v-btn> -->
        </v-card-actions>
      </v-card>
    </div>


</div>
  </div>
</template>

<script>
import axios from 'axios';
import MoveNavBar from '../../components/move/MoveNavBar.vue';
import MoveReviewModal from '../../components/move/MoveReviewModal.vue';

export default {
  components: {
    MoveNavBar, MoveReviewModal
  },
 
    
    data : function(){
      return{

  icons: {
    iconfont: 'mdi', // 'mdi' || 'mdiSvg' || 'md' || 'fa' || 'fa4' || 'faSvg'
  },

      loading: false,
      selection: 1,
      heart : 0,
      wish: "",

      list: [],

      //받은정보
        item: "",
        select2:"",

        email: "",
        estimateNo: "",
        movingResponseNo: "",
        compName : "",
        totalRating: "",
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
            name: '예약중',
            value: '예약중'
          },
          {
            name: '예약완료',
            value: '예약완료'
          },
         
        ],
      }
    },

    created(){
      axios({
        url: "http://localhost:8090/zippy/move/moveReserve",
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
    methods: {
      
      chat() {
        this.loading = true

        setTimeout(() => (this.loading = false), 2000)
      },
      cancle(){

      },
      review(){

      },

      checkbox: function () {
        const ckbox = document.querySelector(".form-check-input");
        const is_cked = ckbox.checked;
        var isChecked = document.querySelector(".form-check-input").innerText = is_cked
        console.log(isChecked);
        axios({
          url: "http://localhost:8088/zippy/used/main",
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

        var dropValue2 = this.select2;
        console.log(dropValue2);
        console.log(this.vo.email);
        axios({
          url: "http://localhost:8090/zippy/move/moveEstimate",
          methods: "GET",
          params: {
            dropbox: this.select,
            dropbox2: dropValue2, //지역
            email: this.vo.email,
            requestDate: this.vo.requestDate,
            departAddress: this.vo.departAddress,
            arriveAddress: this.vo.arriveAddress,
            compAddress: this.vo.compAddress
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
</style>