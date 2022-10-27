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
          찜한 견적보기
        </label>
      </div>
      <hr />
      <!--  -->



      <div id="used-main-dropbox1">
        <v-select @change="dropVal2()" v-model="select2" :items="drops" item-text="name" item-value="value2" label="정렬"
          color="#212529" persistent-hint single-line dense width="50"></v-select>
      </div>

      <div class="divv" v-for="(item, i) in list" :key="i">
        <v-card :loading="loading" class="mx-auto my-12" max-width="374" elevation="10">
          <template slot="progress">
            <v-progress-linear color="#B3E3C3" height="10" indeterminate></v-progress-linear>
          </template>

          <!-- <v-img
      height="250"
      src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
    ></v-img> -->

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

          <v-divider class="mx-4"></v-divider>
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
          <v-card-title>1차 예상견적 : <span>{{item.firstEstimatePrice}}</span>원</v-card-title>
          <div v-if="item.secondEstimatePrice != null">
            <v-card-text>
              <div>2차 견적 타입 : <span>{{item.secondEstimateType}}</span></div>
            </v-card-text>
            <v-card-title>2차 예상견적 : <span>{{item.secondEstimatePrice}}</span>원</v-card-title>
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
            <v-btn color="#B3E3C3 lighten-2" text @click="reserve(item)">
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
  import MoveNavBar from '../../components/move/MoveNavBar.vue';
 

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
      axios({
        url: "http://localhost:8090/zippy/move/moveMyList",
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
        console.log(this.list);

        // this.data.email = this.$store.state.loginInfo.email;
        // this.data.serviceId= this.list[i].email;
        // console.log(this.data.serviceId);
      }).catch(error => {
        console.log(error);
      })




      if (this.$store.state.loginInfo != null) {

        axios({
          url: "http://localhost:8090/zippy/move/wishOneList",
          method: "GET",
          params: {
            email: this.$store.state.loginInfo.email,
            sId: this.list.estimateNo,
            serviceType: this.data.serviceType
          }
        }).then(res => {
          this.wish = res.data;
          if (res.data != "") {
            this.heart = 1;
          } else if (res.data == "") {
            this.heart = 0;
          }
        }).catch(err => {
          console.log(err)
        })
      

        
      }
    },
    methods: {
      reserve(item) {
        this.loading = true



        setTimeout(() => (this.loading = false), 2000)

        this.estimateNo = item.estimateNo;
        this.reservStatus = item.reservStatus;
        console.log(this.reservStatus);

        //견적상태변경
        this.$axios({
          url: "http://localhost:8090/zippy/move/moveStatusThirdUpdate",
          method: "POST",

          params: {
            estimateNo: item.estimateNo,
            email: this.$store.state.loginInfo.email,
            reservStatus : 3

          },
          // data: formData
        }).then(res => {
          console.log(res);
          alert("견적서 보내기 완료!");
          console.log(this.reservStatus);

        }).catch(err => {
          console.log(err)
        })
      },
      chat() {
        this.loading = true

        setTimeout(() => (this.loading = false), 2000)
      },


      changeHeart(i) {
        if (this.$store.state.loginInfo != null) {
          if (this.heart == 0) { //찜x일때
            this.addWish();
            this.heart = 1; //찜on으로 변경
            swal.fire({
              icon: 'success',
              title: '찜 목록에 추가되었습니다.',
              showConfirmButton: false,
              timer: 1500
            });
          } else { //찜on 일 떄
            this.delWish();
            this.heart = 0; //찜x로 변경
            swal.fire({
              icon: 'success',
              title: '찜목록에서 삭제되었습니다.',
              showConfirmButton: false,
              timer: 1500
            });
          }
        } else {
          swal.fire({
            icon: 'warning',
            title: '로그인 정보가 필요합니다.',
            showConfirmButton: false,
            timer: 1500
          });
        }
      },
      rewrite() {
        axios({
          url: "http://localhost:8090/zippy/common/wishOneList",
          method: "GET",
          params: {
            email: this.$store.state.loginInfo.email,
            sId: this.list.estimateNo,
            serviceType: this.data.serviceType
          }
        }).then(res => {
          this.wish = res.data;
          if (res.data != "") {
            this.heart = 1;
          } else if (res.data == "") {
            this.heart = 0;
          }
        }).catch(err => {
          console.log(err)
        })
      },
      addWish: function (i) {
        

        // for(let i of this.list){
        //   this.data.serviceId= this.list[i].email;

        // }
        this.data.serviceId = this.list.estimateNo;
        console.log("데이터:"+this.data)
        console.log("서비스아이디:"+this.data.serviceId)
        console.log("리스트:"+this.list)
        console.log("리스트 서비스아이디:"+this.list.estimateNo)
        // this.data.email = this.$store.state.loginInfo.email;  
        
        axios({
          url: "http://localhost:8090/zippy/common/addWish",
          method: "POST",
          headers: {
            "Content-Type": "application/json; charset=utf-8"
          },
          data: JSON.stringify(this.data)
        }).then(res => {


          this.rewrite();
        }).catch(err => {
          console.log(err)
        })
      },
      delWish: function () {
        let bNo = [];
        bNo.push(this.wish.bookmarkNo);
        axios({
          url: "http://localhost:8090/zippy/common/delWish",
          method: "DELETE",
          data: {
            bNo: bNo
          }
        }).then(res => {
          console.log(res);
        }).catch(err => {
          console.log(err)
        })
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
            email: this.$store.state.loginInfo.email,
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